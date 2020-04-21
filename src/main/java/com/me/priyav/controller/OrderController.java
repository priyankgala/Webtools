package com.me.priyav.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.priyav.dao.CartDao;
import com.me.priyav.dao.CustomerDao;
import com.me.priyav.dao.OrderDao;
import com.me.priyav.dao.ProductDao;
import com.me.priyav.pojo.Cart;
import com.me.priyav.pojo.CartItem;
import com.me.priyav.pojo.Customer;
import com.me.priyav.pojo.Product;
import com.me.priyav.pojo.CustOrder;

@Controller
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	private Path path;

	@Autowired
	ProductDao pDao;

	@Autowired
	CustomerDao cstDao;

	@Autowired
	CartDao cDao;

	@Autowired
	OrderDao oDao;

	@RequestMapping("/customer/order/order.htm")
	public String confirmOrder(Model model, HttpServletRequest request) {
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		logger.info("Inside Order Controller");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		String username = (String) request.getSession().getAttribute("userName");
		logger.info("Username is: " + username);

		logger.info("Getting the customer object and then cart object from customer");
		Customer cust = cstDao.getCustomer(username);

		cust.getCart();
		CustOrder order = new CustOrder();
		order.setCart(cust.getCart());
		order.setCustomer(cust);
		order.setBillingAddress(cust.getBillingAddress());
		oDao.saveOrder(order);
		request.setAttribute("grandTotal", order.getCart().getGrandTotal());
		model.addAttribute("order", order);

		return "orderConfirmation";
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * Clear cart
	 * 
	 * 
	 * 
	 * 
	 */

	@RequestMapping("/customer/clearOrder/{id}")
	public String clearOrder(@PathVariable int id, Model model, HttpServletRequest request) {
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		logger.info("Got the customers cart here");
		String username = (String) request.getSession().getAttribute("userName");
		logger.info("Username is: " + username);
		double total = 0;
		logger.info("Getting the customer object and then cart object from customer");
		Customer cust = cstDao.getCustomer(username);
		Cart cart = cust.getCart();
		logger.info("cart id is: " + id);
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info("Removing from the cart");
		List<CartItem> list = cart.getCartItems();
		for (int i = 0; i < list.size(); i++) {
			list.remove(i);
		}
		cart.setGrandTotal(0);
		cDao.save(cart);

		int res = cDao.removeProdcutByCartId(id);
		if (res == 1) {
			logger.info(" ");
			logger.info(" ");
			logger.info(" ");
			logger.info(" ");
			logger.info(" ");
			logger.info("Deleted the item from cart");
		}

		logger.info("redirecting to the cart now");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");

		model.addAttribute("cart", list);

		return "cart";
	}

	@RequestMapping("/customer/clearOrder/")
	public String showCart(HttpServletRequest request, Model model, HttpServletResponse response) {
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		logger.info("seeing the cart");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		String username = (String) request.getSession().getAttribute("userName");
		logger.info("Username is: " + username);
		double total = 0;
		logger.info("Getting the customer object and then cart object from customer");
		Customer cust = cstDao.getCustomer(username);
		Cart cart = cust.getCart();
		List<CartItem> cartItems = cart.getCartItems();
		logger.info("Size of the customers cart is:" + cartItems.size());

		for (int i = 0; i < cartItems.size(); i++) {
			total += cartItems.get(i).getTotalPrice();
		}

		request.setAttribute("grandTotal", total);
		request.setAttribute("custCatId", cart.getCartId());
		request.setAttribute("cartEmpty", "Cart already empty");
		model.addAttribute("cart", cartItems);

		return "cart";
	}

	@RequestMapping("/customer/order/submitorder.htm")
	public String submitOrder(Model model, HttpServletRequest request) {
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		logger.info("Got the customers cart here");
		String username = (String) request.getSession().getAttribute("userName");
		logger.info("Username is: " + username);
		double total = 0;
		logger.info("Getting the customer object and then cart object from customer");
		Customer cust = cstDao.getCustomer(username);
		Cart cart = cust.getCart();
		if (cart.getGrandTotal() == 0) {
			request.setAttribute("custCartId", cart.getCartId());
			request.setAttribute("cartEmpty", "You don't have anything in your cart..!!!");
			return "cart";
		}
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info("Removing from the cart");
		List<CartItem> list = cart.getCartItems();
		List<CartItem> emaillist = cart.getCartItems();

		logger.info("**************************************Email List is:" + emaillist);
		for (int i = 0; i < emaillist.size(); i++) {
			logger.info("Product unit in cost is:" + emaillist.get(i).getProduct().getUnitInStock());
			if (cart.getCartItems().get(i).getQuantity() <= emaillist.get(i).getProduct().getUnitInStock()) {
				logger.info("Product size is less or equal to the Units available. ");
				Product prd = emaillist.get(i).getProduct();
				int stock = prd.getUnitInStock();
				prd.setUnitInStock(stock - 1);
				pDao.updateProduct(prd);
				logger.info("****************************Update stock is:" + prd.getUnitInStock());
			} else {

				/*
				 * 
				 * 
				 * going back to cart with product list
				 * 
				 */
				logger.info("*********************Inside the else loop");
				for (int j = 0; j < emaillist.size(); j++) {
					total += emaillist.get(i).getTotalPrice();
				}

				request.setAttribute("grandTotal", total);
				request.setAttribute("custCartId", cart.getCartId());
				request.setAttribute("cartEmpty",
						"Product is out of Stock. Please try buying in few days !! Thank you..");
				model.addAttribute("cart", emaillist);

				return "cart";
			}
		}
		logger.info("**************************************Email List is:" + emaillist);
		// Sending Email
		try {
			logger.info("data successfully stored in tables: Customer, User, BillingAddress");
			logger.info("sending confirmation email");
			SendEmail(username, emaillist);
		} catch (EmailException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			list.remove(i);
		}

		logger.info("**************************************Email List is:" + emaillist);
		cart.setGrandTotal(0);
		cDao.save(cart);
		logger.info("Cart ID is order confirmation is:" + cart.getCartId());
		int res = cDao.removeProdcutByCartId(cart.getCartId());

		return "thankCustomer";
	}

	public void SendEmail(String emailID, List<CartItem> list) throws EmailException {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("webtoolsproject2020@gmail.com", "1q_2w_3e_4r"));
			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu");
			email.setSubject("Your Order has been successfully placed");
			String message = "";
			double cost = 0;
			for (CartItem c : list) {
				String productName = c.getProduct().getProductName();
				int quantity = c.getQuantity();
				double productCost = c.getProduct().getProductPrice();
				cost = cost + c.getTotalPrice();
				message = message + quantity + "* " + productName + " @ " + productCost + "$ each\n";
				System.out.println("MEssage is *********************************************8" + message);
			}
			message = message + "\n Grand Total: " + cost;
			email.setMsg("Thank you for shopping with us. Here are your order details\n" + message);
			email.addTo(emailID);
			email.send();
		} catch (Exception ex) {
			logger.info("Unable to send email");
			System.out.println(ex);
		}
	}
}

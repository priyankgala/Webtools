package com.me.priyav.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.me.priyav.dao.CartDao;
import com.me.priyav.dao.CustomerDao;
import com.me.priyav.dao.ProductDao;
import com.me.priyav.pojo.*;

@Controller
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	ProductDao pDao;

	@Autowired
	CustomerDao cstDao;

	@Autowired
	CartDao cDao;

	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request, Model model) {

		logger.info("seeing the cart");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		String username = (String) request.getSession().getAttribute("userName");
		logger.info("Username is: " + username);

//		CustomerDao cstDao = new CustomerDao();
//		CartDao cDao = new CartDao();
		double total = 0;
		logger.info("Getting the customer object and then cart object from customer");
		Customer cust = cstDao.getCustomer(username);
		Cart cart = cust.getCart();
		List<CartItem> cartItems = cart.getCartItems();
		logger.info("Size of the customers cart is:" + cartItems.size());
		
		
		for(int i=0;i<cartItems.size();i++) {
			total += cartItems.get(i).getTotalPrice();
		}
		
		request.setAttribute("grandTotal", total);
		model.addAttribute("cart", cartItems);
		
		return "cart";
	}

	@RequestMapping("/product/addCart/{id}")
	public String addProduct(@PathVariable int id, Model model, HttpServletRequest request) {
		logger.info("Redirecting to ViewProduct after adding the product in the cart");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		Product product = pDao.getProductById(id);
		logger.info("Got product by id: " + product.getProductId());

		String username = (String) request.getSession().getAttribute("userName");
		logger.info("Username is: " + username);

//		CustomerDao cstDao = new CustomerDao();
//		CartDao cDao = new CartDao();
		logger.info("Getting the customer object and then cart object from customer");
		Customer cust = cstDao.getCustomer(username);
		Cart cart = cust.getCart();
		List<CartItem> cartItems = cart.getCartItems();
		logger.info("Size of the customers cart is:" + cartItems.size());
		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getProductId() == cartItems.get(i).getProduct().getProductId()) {
				logger.info("Inside my for loop to check if PID is already added for the customers cart");
				CartItem cartItem = cartItems.get(i);
				logger.info("Duplicate Cart Item is: " + cartItem.getCartItemId());
				int qnty = cartItem.getQuantity() + 1;
				cartItem.setQuantity(qnty);
				logger.info("Cart items quantity is: " + cartItem.getQuantity());
				cartItem.setTotalPrice(product.getProductPrice() * cartItem.getQuantity());
				logger.info("Cart items new price is: " + cartItem.getTotalPrice());
				int res1 = cDao.updateCartItem(cartItem);
				if (res1 == 1) {
					model.addAttribute("product", product);
					request.setAttribute("cartAdd", "Again Sucessfully added in Cart");
					return "viewProduct";
				}
			}
		}

		logger.info("Item is not in the list");
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getProductPrice() * cartItem.getQuantity());
		cartItem.setCart(cart);
		int res2 = cDao.addCartItem(cartItem);

		if (res2 == 1) {
			model.addAttribute("product", product);
			request.setAttribute("cartAdd", "Sucessfully added in Cart");
		} else {
			model.addAttribute("product", product);
			request.setAttribute("cartAdd", "Cannot add item in Cart");
		}
		return "viewProduct";
	}
}

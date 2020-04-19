package com.me.priyav.controller;

import java.util.List;
import com.me.priyav.pojo.*;
import org.apache.commons.mail.*;
import org.mindrot.jbcrypt.BCrypt;
import com.me.priyav.dao.UserLoginDao;
import com.me.priyav.dao.CustomerDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@RequestMapping("/register.htm")
	public String register(Model model, HttpServletRequest request) {
		logger.info("redirect to Register Page");
		request.setAttribute("URI", request.getRequestURI());
		return "registerPage";
	}

	@RequestMapping("/registersuccess.htm")
	public String registerSuccess(HttpServletRequest request, Model model) {
		logger.info("Checking if registration is successful or not");

		// taking username and encrypting password
		String username = request.getParameter("emailId");
		String password = request.getParameter("password");
		String newPswd = BCrypt.hashpw(password, BCrypt.gensalt());

		Customer customer = new Customer();
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String emailId = request.getParameter("emailId");
		String phone = request.getParameter("phoneNumber");
		// password take the same variable newPswd

		// Billing Address values
		String street = request.getParameter("street");
		String aptNo = request.getParameter("aptNo");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");

		// Billing address obj set values
		BillingAddress address = new BillingAddress();
		address.setStreetName(street);
		address.setAptNo(aptNo);
		address.setCity(city);
		address.setState(state);
		address.setZipCode(zipcode);
		
		Cart cart = new Cart();
		cart.setGrandTotal(0);
		cart.setCustomer(customer);

		// Customer obj set values
		customer.setCustFName(fName);
		customer.setCustLName(lName);
		customer.setCustEmail(emailId);
		customer.setCustPhone(phone);
		customer.setCustPassword(newPswd);
		customer.setBillingAddress(address);
		customer.setCart(cart);

		CustomerDao custDao = new CustomerDao();
		List<Customer> customerList = custDao.getAllCustomers();

		if (!customerList.isEmpty()) {
			for (int i = 0; i < customerList.size(); i++) {
				if (customer.getCustEmail().equalsIgnoreCase(customerList.get(i).getCustEmail())) {
					model.addAttribute("emailMsg", "Email already exists");
					return "registerPage";
				}
			}
		}

		int res2 = custDao.addCustomer(customer);
		
		UserLoginDao login = new UserLoginDao();
		String userType = "customer";
//		logger.info("Encrypted password is: " + newPswd);
		int res = login.registerUser(username, newPswd, userType);

		if (res == 1 && res2 == 1) {
			try {
				logger.info("data successfully stored in tables: Customer, User, BillingAddress");
				logger.info("sending confirmation email");
				SendEmail(username, username);
			} catch (EmailException e) {
				e.printStackTrace();
			}
			return "registerSuccessPage";
		} else {
			return "registerPage";
		}
	}

	// Login controller
	@RequestMapping("/login.htm")
	public String login(Model model, HttpServletRequest request) {
		logger.info("redirect to login Page");
		request.setAttribute("URI", request.getRequestURI());
		return "login";
	}

	@RequestMapping("/loginsuccess.htm")
	public String loginSuccess(HttpServletRequest request, Model model, HttpSession session) {
		logger.info("checking the credentials");
		UserLoginDao loginDao = new UserLoginDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<Users> user = loginDao.authenticateUser(username, password);
		if (user != null) {
			session.setAttribute("userType", user.get(0).getUserType());
			session.setAttribute("userName", user.get(0).getUsername());
			return "home";
		} else {
			model.addAttribute("error", "Invalid username and password.");
			return "login";
		}
	}
	
	@RequestMapping("/logout.htm")
	public String logout(HttpServletRequest request, Model model, HttpSession session) {
		session.removeAttribute("userType");
		session.removeAttribute("userName");
		session.invalidate();
		request.setAttribute("URI", request.getRequestURI());
		logger.info(""+request.getRequestURI());
		return "home";
	}

	// Sending email
	public void SendEmail(String emailID, String username) throws EmailException {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("webtoolsproject2020@gmail.com", "1q_2w_3e_4r"));
			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu");
			email.setSubject("Successfully Registered");
			email.setMsg("Registeration successful	" + "\nYour username is:\t" + username);
			email.addTo(emailID);
			email.send();
		} catch (Exception ex) {
			logger.info("Unable to send email");
			System.out.println(ex);
		}
	}

}

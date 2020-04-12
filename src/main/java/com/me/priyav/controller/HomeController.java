package com.me.priyav.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		logger.info("Welcome to my HomePage!");
//		request.getSession().invalidate();
		String userType = (String) request.getSession().getAttribute("userType");
		logger.info("UserType logged in is: "+userType);
		return "home";
	}

	@RequestMapping(value = "/about.htm", method = RequestMethod.GET)
	public String about(Model model) {
		logger.info("Know a little bit more about us!");
		return "aboutUs";
	}

}

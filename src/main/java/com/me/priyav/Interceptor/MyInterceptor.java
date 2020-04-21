package com.me.priyav.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("Intercepted even when Back button in browser is clicked");
		request.setAttribute("unsafe_check", "false");
		if (request.getSession().getAttribute("userType") == null) {
			logger.info("If loop: Not authenticated");
			request.setAttribute("unsafe_check", "true");
			logger.info("" + request.getAttribute("unsafe_check"));
//			response.sendRedirect("/priyav/login.htm");
		}
		logger.info(request.getRequestURI());
		logger.info(""+request.getSession().getAttribute("userType"));
		if(request.getRequestURI().contains("admin") && request.getSession().getAttribute("userType").equals("customer")) {
			logger.info("Customer logged in : Not authenticated");
			request.setAttribute("unsafe_check", "true");
			logger.info("" + request.getAttribute("unsafe_check"));
		}
		
		if(request.getRequestURI().contains("customer") && request.getSession().getAttribute("userType").equals("admin")) {
			logger.info("Admin logged in : Not authenticated");
			request.setAttribute("unsafe_check", "true");
			logger.info("" + request.getAttribute("unsafe_check"));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}

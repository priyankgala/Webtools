package com.me.priyav.Filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.priyav.Interceptor.MyInterceptor;

public class MyFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
		String URI = req.getRequestURI();
		PrintWriter out = res.getWriter();
		logger.info("Inside Filter");
		chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

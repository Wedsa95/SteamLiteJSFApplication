package com.jensen.steamlite.controller.authenticationfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckUserSession implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//		
//		HttpSession session = req.getSession();
//		
//		Object isLoggedIn =  session.getAttribute("isLoggedIn");
//		
//		if(isLoggedIn != null && (boolean) isLoggedIn) {
//			res.sendRedirect(req.getContextPath());
//		}else {
//			res.sendRedirect("/faces/store.xhtml?faces-redirect=true");
//		}
	
	}

	@Override
	public void init(FilterConfig conf) throws ServletException {
		
	}
	
	
}

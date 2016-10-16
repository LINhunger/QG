package com.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;

/**
 * 权限控制过滤器
 * 
 * @author seawind
 * 
 */
public class PrivilegeFilter implements Filter {

	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 权限控制
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse  httpServletResponse=(HttpServletResponse)response;
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		if (user!=null&&user.isAdmin()) {
			chain.doFilter(httpServletRequest, httpServletResponse);
			return;
			}
		 else {
			 httpServletRequest.setAttribute("message", "您没有访问权限");
			 httpServletRequest.getRequestDispatcher("/success.jsp").forward(httpServletRequest, response);
			 return;
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

}

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
import javax.servlet.http.HttpSession;

public class SignInFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 使用与HTTP协议相关 API，需要将参数转为子类型
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession  session =httpServletRequest.getSession();	
		if(session.getAttribute("user")==null){
			httpServletRequest.setAttribute("message", "请先登陆!");
			httpServletRequest.getRequestDispatcher("/success.jsp").forward(httpServletRequest, httpServletResponse);
		}
		else{
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}

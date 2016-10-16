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

public class HotLinkingFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 使用与HTTP协议相关 API，需要将参数转为子类型
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		//防止盗链
		String referer=httpServletRequest.getHeader("referer");  
		System.out.println("hotlinking runs the referer is "+referer);
        if(referer==null||!referer.startsWith("http://localhost")) 
        {  
        	httpServletResponse.sendRedirect("/QG/success.jsp");  
            return;  
        }
      
		else{
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

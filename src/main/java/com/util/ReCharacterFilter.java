package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ReCharacterFilter implements Filter {
	/*
	 * 第二次HTML过滤标签
	 */
	private Map<String, String> escapeMap;
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest requestWrapper  = new CharacteRequestWrapper((HttpServletRequest)request, escapeMap);
		chain.doFilter(requestWrapper, response);

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		BufferedReader reader = null;
		try {
			String escapeListFile = filterConfig.getInitParameter("REESCAPE_LIST");
			reader = new BufferedReader(
					new InputStreamReader(
					filterConfig.getServletContext()
					.getResourceAsStream(escapeListFile) ) );
			String input =null;
			escapeMap = new HashMap<String, String>();
			while((input = reader.readLine())!=null){
				String[] tokens = input.split("\t");
				System.out.println(tokens[0]+"  "+tokens[1]);
				escapeMap.put(tokens[0], tokens[1]);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger(CharacterFilter.class.getName()).log(Level.SEVERE, null, e);
		}
		finally{
			try {
				reader.close();
			} catch (Exception e2) {
				// TODO: handle exception
				Logger.getLogger(CharacterFilter.class.getName()).log(Level.SEVERE, null, e2);
			}
		}
	}

}

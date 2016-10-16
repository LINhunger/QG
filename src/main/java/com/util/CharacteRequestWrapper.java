package com.util;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/*
 * 封装器重写request的getParameter(String name)方法
 */
public class CharacteRequestWrapper extends HttpServletRequestWrapper {
	private Map<String, String> escapeMap;
	public CharacteRequestWrapper(HttpServletRequest request,Map<String, String> escapeMap) {
		super(request);
		this.escapeMap=escapeMap;
	}
	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
	//	return super.getParameter(name);
		return doEscape(this.getRequest().getParameter(name));
	}
	private String doEscape(String parameter){
		if(parameter==null){
			return null;
		}
		String result =parameter;
		Iterator<String> iterator = escapeMap.keySet().iterator();
		while(iterator.hasNext()){
			String origin = iterator.next();
			String escape = escapeMap.get(origin);
			result =result.replaceAll(origin, escape);
		}
		return result;
	}
	

}

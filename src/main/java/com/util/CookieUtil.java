package com.util;

import javax.servlet.http.Cookie;
/*
 * 获取Cookie
 */
public class CookieUtil {
	public static Cookie findCookie(Cookie[] cookies,String name)
	{
		 if(cookies==null){
			 return null;
		 }
		 else{
			 for (Cookie cookie : cookies) {
				 System.out.println(cookie.getPath());
				 if(cookie.getName().equals(name))
					 return cookie;
			 }
		 }
		 return null;
	}
}

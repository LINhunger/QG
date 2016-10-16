package com.util.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineCountHttpSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent he) {
			//创建session，人数加一
			HttpSession session = he.getSession();
			ServletContext context = session.getServletContext();
			int onlinenum =(Integer)context.getAttribute("onlinenum");
			context.setAttribute("onlinenum", onlinenum+1);
			System.out.println(session.getId()+"创建");
	}

	public void sessionDestroyed(HttpSessionEvent he) {
			//销毁session，人数减一
		HttpSession session = he.getSession();
		ServletContext context = session.getServletContext();
		int onlinenum =(Integer)context.getAttribute("onlinenum");
		context.setAttribute("onlinenum", onlinenum-1);
		System.out.println(session.getId()+"销毁");
	}

}

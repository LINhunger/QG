package com.util.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OnlineCountServletContextListener implements
		ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//初始化在线为0
		ServletContext context  =sce.getServletContext();
		context.setAttribute("onlinenum", 0);
		System.out.println("OnlineCountServletContextListener");
	}

}

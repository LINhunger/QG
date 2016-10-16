package com.util.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import com.model.User;
/*
 * 对全局对象进行初始化
 */
public class MyServletContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
				// 所有在线用户数据集合
				Map<User, HttpSession> map = new HashMap<User, HttpSession>();
				// 将集合保存ServletContext 数据范围
				ServletContext servletContext = sce.getServletContext();
				servletContext.setAttribute("map", map);
	}

}

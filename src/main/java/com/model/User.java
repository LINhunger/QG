package com.model;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener{
		public void valueBound(HttpSessionBindingEvent event) {
			// 将新建立Session 和 用户 保存ServletContext 的Map中
			HttpSession session = event.getSession();
			ServletContext servletContext = session.getServletContext();
			Map<User, HttpSession> map = (Map<User, HttpSession>) servletContext
					.getAttribute("map");
			// 将新用户加入map
			map.put(this, session);
		
	}
	public void valueUnbound(HttpSessionBindingEvent event) {
				// 根据user对象，从Map中移除Session
				HttpSession session = event.getSession();
				ServletContext servletContext = session.getServletContext();

				Map<User, HttpSession> map = (Map<User, HttpSession>) servletContext
						.getAttribute("map");

				// 从map移除
				map.remove(this);
		
	}
		private int id;
		private String username;
		private String password;
		private String email;
		private String sex;
		private String picture;
		private int limit;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getPicture() {
			return picture;
		}
		public void setPicture(String picture) {
			this.picture = picture;
		}
		private boolean admin=false;
		
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public boolean isAdmin() {
			return admin;
		}
		public void setAdmin(boolean admin) {
			this.admin = admin;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public int getLimit() {
			return limit;
		}
		public void setLimit(int limit) {
			this.limit = limit;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", password="
					+ password + ", email=" + email + ", sex=" + sex
					+ ", picture=" + picture + ", limit=" + limit + ", admin="
					+ admin + "]";
		}
		
}

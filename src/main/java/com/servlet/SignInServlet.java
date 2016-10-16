package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;
import com.service.UserService;
import com.util.*;

public class SignInServlet extends HttpServlet {

	private UserService userService = new UserService();//创建UserService实例
	private String message	=null; //用于表示注册信息
	private  boolean  flag=false;//判断标记
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		HttpSession  session = request.getSession();
		String num=request.getParameter("num");
		String random=(String)session.getAttribute("valcode");
		if(num!=null&&random!=null){
			if(!num.equals(random)){
				request.setAttribute("message", "验证码输入错误！");
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
				return;
			}
			//如果正确，则进行下一步的检查操作
			else{
				session.invalidate();// 销毁之前状态
				User user =null;
				//获取request请求的用户基本信息并存入User中
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				user=userService.getUserByName(username);
				//判断用户名是否存在
				if(user!=null)
				{
					//存在，则检查密码
					if(userService.checkPassword(username,password)){
						message="登录成功";
						// 先判断该用户是否已经登陆，如果已经登陆，将Session销毁
						Map<User, HttpSession> map = (Map<User, HttpSession>)  
								getServletContext().getAttribute("map");
						for (User hasLoginUser : map.keySet()) {
							if (hasLoginUser.getUsername().equals(username)){
								// 此用户之前登陆过 --- 消灭Session
								HttpSession hasLoginSession = map.get(hasLoginUser);
								hasLoginSession.invalidate();// session 被摧毁，移除所有对象
								break;
							}
						}
						request.getSession().setAttribute("user", user);
						// 判断用户是否勾选记住用户名和密码checkbox
						if ("remember".equals(request.getParameter("remember"))) {
							// 勾选了 ---将用户名和密码 写入cookie
							// 对中文编码保存
							Cookie usernameCookie = new Cookie("username", URLEncoder.encode(user.getUsername(), "utf-8")); 
							usernameCookie.setMaxAge(60 * 60 );
							usernameCookie.setPath("/QG");
							response.addCookie(usernameCookie);

							Cookie passwordCookie = new Cookie("password", user.getPassword());
							passwordCookie.setMaxAge(60 * 60 * 24);
							passwordCookie.setPath("/QG");
							response.addCookie(passwordCookie);
						}
						request.setAttribute("message", "登录成功");//设置登录信息
						if(user.isAdmin()){
							request.getRequestDispatcher("/admin/adminpage.jsp").forward(request, response);
							return;
						}
						request.getRequestDispatcher("/success.jsp").forward(request, response);//将登录信息放回给jsp页面
					}
					else{
						message="密码错误";
						request.setAttribute("message", message);//设置登录信息
						request.getRequestDispatcher("/index.jsp").forward(request, response);//将登录信息放回给jsp页面
					}
				}
				else
				{
					message="不存在的用户名";
					request.setAttribute("message", message);//设置登录信息
					request.getRequestDispatcher("/index.jsp").forward(request, response);//将登录信息放回给jsp页面
				}
			}
		}
		
	

	}
}

	



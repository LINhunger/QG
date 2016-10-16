package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;
import com.service.UserService;

public class ChangePasswordServlet extends HttpServlet {
	
	private UserService userService = new UserService();//创建UserService实例
	private String message=null;//用于表示注册信息

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		//设置字符编码格式
		request.setCharacterEncoding("utf-8");
		//获取request请求的用户基本信息并存入User中
		String username = request.getParameter("username");
		String Opassword = request.getParameter("Opassword");
		String Npassword = request.getParameter("Npassword");
		System.out.println(username+" "+Opassword+" "+Npassword);
		//判断用户名是否存在
		if(username!=""&&Opassword!=""&&Npassword!=""
				&&userService.checkUser(username)//判断用户名是否存在
				&&userService.checkPassword(username, Opassword))//判断密码是否正确
		{
			//销毁session
			HttpSession  session = request.getSession();
			session.invalidate();
			message="修改密码成功";
			request.setAttribute("message", message);//设置信息
			userService.changePassword(username, Opassword, Npassword);
			request.getRequestDispatcher("/index.jsp").forward(request, response);//将注册信息放回给jsp页面
		}
		else
		{
			message="修改密码失败，请检查密码是否正确";//设置信息
			request.setAttribute("message", message);//
			request.getRequestDispatcher("/personal/changepassword.jsp").forward(request, response);//将注册信息放回给jsp页面
		}
	
	}

}

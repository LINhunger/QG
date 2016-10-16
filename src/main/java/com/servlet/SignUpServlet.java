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

public class SignUpServlet extends HttpServlet {
	//数据域
	private UserService userService = new UserService();//创建UserService实例
	private String message=null;//用于表示注册信息
	//方法域
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
				request.setAttribute("message", "注册失败,验证码输入错误！");
				request.getRequestDispatcher("/failure.jsp").forward(request,
						response);
				return;
			}
			//如果正确，则进行注册操作
			else{
				User u = new User();
				//获取request请求的用户基本信息并存入User中
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String email = request.getParameter("email");
				String sex = request.getParameter("sex");
				u.setUsername(username);
				u.setPassword(password);
				u.setEmail(email);
				u.setSex(sex);
				//判断用户名是否存在和email格式
				if(userService.checkFormat(username,password,email))
				{
					message="注册成功";
					request.setAttribute("message", message);//设置注册信息
					userService.saveUser(u);//注册用户
					request.getRequestDispatcher("/failure.jsp").forward(request, response);//将注册信息放回给jsp页面
					return;
				}
				else
				{
					message="注册失败，已存在的用户名!";//设置注册信息
					request.setAttribute("message", message);//注册用户
					request.getRequestDispatcher("/failure.jsp").forward(request, response);//将注册信息放回给jsp页面
					return;
				}
			
			}
		}
		
	

	
	}
}


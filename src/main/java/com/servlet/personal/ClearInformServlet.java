package com.servlet.personal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.service.InformService;

public class ClearInformServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		InformService informService = new InformService();
		User user =(User)request.getSession().getAttribute("user");
		int r_id=user.getId();
		informService.deleteAllInform(r_id);
		response.sendRedirect("/QG/personal/inform.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}

}

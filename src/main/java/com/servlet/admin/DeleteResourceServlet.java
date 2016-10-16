package com.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Article;
import com.service.ArticleService;
import com.service.CommentService;
import com.service.ResourceService;
/*
 * 删除资源……的路径
 */
public class DeleteResourceServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		ResourceService resourceService = new ResourceService();
		int id = Integer.parseInt((String)request.getParameter("id"));
		resourceService.deleteResource(id);
		response.sendRedirect("/QG/admin/allresource.jsp");
	//	request.getRequestDispatcher("/").forward(request, response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

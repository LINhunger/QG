package com.servlet.article;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Article;
import com.service.ArticleService;

public class SelectServlet extends HttpServlet {

	private ArticleService articleService = new ArticleService();//创建Service实例
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		String a_title =request.getParameter("a_title");
		List<Article> articles = articleService.getAllArticleByTitle(a_title);
		request.setAttribute("articles", articles);
		System.out.println("runs");
		request.getRequestDispatcher("/article/selectpage.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}

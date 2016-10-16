package com.servlet.article;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.print.resources.serviceui;

import com.model.Article;
import com.service.ArticleService;

public class BrowseArticleServlet extends HttpServlet {

	private ArticleService articleService = new ArticleService();//创建Service实例
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		int a_id =Integer.parseInt(request.getParameter("a_id"));
		Article article=articleService.getArticleById(a_id);
		if(article.getStatus().equals("limits")){
			request.setAttribute("message", "该文章已被封禁");
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}
		else{
		request.setAttribute("article", article);
		request.getRequestDispatcher("/article/article.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

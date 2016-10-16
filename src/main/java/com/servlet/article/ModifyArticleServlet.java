package com.servlet.article;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Article;
import com.service.ArticleService;

public class ModifyArticleServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArticleService ArticleService = new ArticleService();
		int a_id =Integer.parseInt(request.getParameter("a_id"));
		Article article = ArticleService.getArticleById(a_id);
		String a_title=article.getA_title();
		//String a_type = article.getA_type();
		String a_content=article.getA_content();
		request.setAttribute("a_id", a_id);
		request.setAttribute("a_title", a_title);
//		request.setAttribute("a_type", a_type);
		a_content=a_content.replaceAll("<br/>", "\r\n");
		a_content=a_content.replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", " ");
		System.out.println(a_content);
		request.setAttribute("a_content", a_content);
		request.getRequestDispatcher("/personal/modifyarticle.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


	}

}

package com.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Article;
import com.service.ArticleService;

public class LimitArticleServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleService articleService = new ArticleService();
		//获取文章对象id;
		String a_id = request.getParameter("a_id");
		//获取文章对象
		int u_id =Integer.parseInt((String)request.getParameter("u_id"));
		articleService.publishArticle(Integer.parseInt(a_id), "limits");
		articleService.inform(Integer.parseInt(a_id), "文章含有敏感信息，已被封");
		//跳转回原页面，回到原页面许携带u_id
		response.sendRedirect("/QG/admin/detailspage.jsp?u_id="+u_id);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

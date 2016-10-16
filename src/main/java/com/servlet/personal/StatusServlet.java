package com.servlet.personal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Article;
import com.model.User;
import com.service.ArticleService;

public class StatusServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleService articleService = new ArticleService();
		//获取文章对象id;
		String a_id = request.getParameter("a_id");
		//获取文章对象
		Article article = articleService.getArticleById(Integer.parseInt(a_id));
		//先判断用户有无被禁
		HttpSession  session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user.getLimit()==0){
			if(article.getStatus().equals("saves")){
				articleService.publishArticle(Integer.parseInt(a_id), "publish");
			}
			if(article.getStatus().equals("publish")){
				articleService.publishArticle(Integer.parseInt(a_id), "saves");
			}
			response.sendRedirect("/QG/personal/myarticle.jsp");
			return;
		}
		else{
			request.setAttribute("message", "你被禁了，嘿嘿嘿");
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


	}

}

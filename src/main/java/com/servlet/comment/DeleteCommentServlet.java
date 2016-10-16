package com.servlet.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Article;
import com.service.ArticleService;
import com.service.CommentService;
/*
 * 删除评论
 */
public class DeleteCommentServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		CommentService commentService = new CommentService();
		ArticleService articleService = new ArticleService();
		int c_id = Integer.parseInt((String)request.getParameter("c_id"));
		commentService.deleteComment(c_id);
		int a_id =Integer.parseInt(request.getParameter("a_id"));
		Article article=articleService.getArticleById(a_id);
		request.setAttribute("article", article);
		request.getRequestDispatcher("/article/article.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

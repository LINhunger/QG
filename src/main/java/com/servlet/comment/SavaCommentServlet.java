package com.servlet.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.*;
import com.service.*;

public class SavaCommentServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/*
	 * (non-Javadoc)
	 * 发表评论函数
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		
		HttpSession  session = request.getSession();
		User user =(User)session.getAttribute("user");//获取发表用户
		ArticleService articleService = new ArticleService();
		CommentService commentService = new CommentService();
		InformService informService = new InformService();
		
		int a_id =Integer.parseInt((String)request.getParameter("a_id"));//获取评论文章id(5)
		Article article=articleService.getArticleById(a_id);
		int u_id=article.getU_id();//获取文章作者id(6)
		int cu_id=user.getId();//评论用户id(2)
		String c_content =request.getParameter("c_content");//获取评论内容(3)
		//保存评论到数据库
		Comment comment= new Comment();
		comment.setCu_id(cu_id);
		comment.setC_content(c_content);
		comment.setA_id(a_id);
		comment.setU_id(u_id);
		commentService.saveComment(comment);
		//保存提示消息到数据库
		Inform inform = new Inform();
		inform.setP_username(user.getUsername());//获取发表人用户名
		inform.setR_id(u_id);//接收人id
		inform.setA_id(a_id);//文章id
		informService.saveInform(inform);
		response.sendRedirect("/QG/com/servlet/article/BrowseArticleServlet?a_id="+a_id);
	}

}

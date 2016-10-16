package com.servlet.recomment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.*;
import com.service.*;


public class SaveRecommentServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/*
	 * (non-Javadoc)
	 * 发表回复函数
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		
		HttpSession  session = request.getSession();
		CommentService commentService = new CommentService();
		RecommentService  recommentService  = new RecommentService();
		InformService informService = new InformService();
		int a_id =Integer.parseInt((String)request.getParameter("a_id"));//获取评论文章id
		User user =(User)session.getAttribute("user");//获取发表用户
		
		int publishid = user.getId();//获取回复用户的id
		String r_username=user.getUsername();//获取回复用户的用户名
		int c_id =Integer.parseInt((String)request.getParameter("c_id"));//获取评论id
		String r_content = request.getParameter("r_content");//获取回复内容
		//保存回复到数据库
		Recomment  recomment = new Recomment();
		recomment.setR_content(r_content);
		recomment.setPublishid(publishid);
		recomment.setReceiveid(0);
		recomment.setC_id(c_id);
		recomment.setR_username(r_username);
		recommentService.saveRecomment(recomment);
		//保存提醒消息到数据库
		Comment comment=commentService.getCommentByC_Id(c_id);
		int r_id=comment.getCu_id();
		Inform inform = new Inform();
		inform.setP_username(user.getUsername());
		inform.setR_id(r_id);
		inform.setA_id(a_id);
		informService.saveInform(inform);
		response.sendRedirect("/QG/com/servlet/article/BrowseArticleServlet?a_id="+a_id);
	}

}

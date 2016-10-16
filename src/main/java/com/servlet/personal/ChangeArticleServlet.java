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
/*
 * 获取草稿修改文章
 */
public class ChangeArticleServlet extends HttpServlet {
	//数据域
	private ArticleService articleService = new ArticleService();//创建Service实例
	private String message=null;//用于表示注册信息

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		
		int a_id=Integer.parseInt((String)request.getParameter("a_id"));
		String a_title =request.getParameter("a_title");
		String a_type = request.getParameter("a_type");
		String a_content = request.getParameter("a_content");
		
		articleService.changeArticle(a_id, a_title, a_type, a_content);
		message="文章修改成功";
		request.setAttribute("message", message);//设置信息返回
		request.getRequestDispatcher("/personal/myarticle.jsp").forward(request, response);//将注册信息放回给jsp页面
	}

}

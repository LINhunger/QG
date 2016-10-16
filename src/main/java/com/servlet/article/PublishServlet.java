package com.servlet.article;

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
import com.service.UserService;
/*
 * 保存文章
 */
public class PublishServlet extends HttpServlet {
	
	//数据域
	private ArticleService articleService = new ArticleService();//创建Service实例
	private String message=null;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		HttpSession  session = request.getSession();
		User user =(User)session.getAttribute("user");//获取发表用户
		Article article = new Article();//将获取的参数和用户信息存入文章对象
		
		String a_title =request.getParameter("a_title");
		String a_type = request.getParameter("a_type");
		String a_content = request.getParameter("a_content");
		
		article.setA_title(a_title);
		article.setA_type(a_type);
		article.setA_content(a_content);
		article.setU_id(user.getId());
		article.setUsername(user.getUsername());
		article.setStatus("saves");
		articleService.saveArticle(article);
		message="文章保存成功";
		request.setAttribute("message", message);//设置信息返回
		request.getRequestDispatcher("/success.jsp").forward(request, response);//将注册信息放回给jsp页面
	}

}

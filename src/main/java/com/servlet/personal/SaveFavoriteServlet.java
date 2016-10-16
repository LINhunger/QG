package com.servlet.personal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Favorite;
import com.model.User;
import com.service.ArticleService;

import com.service.FavoriteService;


public class SaveFavoriteServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		
		FavoriteService favoriteService = new FavoriteService();
		ArticleService articleService = new ArticleService();
		HttpSession  session = request.getSession();
		User user =(User)session.getAttribute("user");//获取用户对象
		int a_id =Integer.parseInt((String)request.getParameter("a_id"));//获取文章id
		int u_id=user.getId();//用户id
		String a_title =articleService.getArticleById(a_id).getA_title();
		//保存收藏到数据库
		Favorite favorite  = new Favorite();
		favorite.setU_id(u_id);
		favorite.setA_id(a_id);
		favorite.setA_title(a_title);
		favoriteService.saveFavorite(favorite);
		response.sendRedirect("/QG/com/servlet/article/BrowseArticleServlet?a_id="+a_id);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

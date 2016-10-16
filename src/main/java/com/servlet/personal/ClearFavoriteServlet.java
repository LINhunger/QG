package com.servlet.personal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.service.FavoriteService;

public class ClearFavoriteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//设置字符编码格式
		response.setCharacterEncoding("UTF-8");//设置字符编码格式
		FavoriteService favoriteService = new FavoriteService();
		User user =(User)request.getSession().getAttribute("user");
		int u_id=user.getId();
		favoriteService.deleteAllFavorite(u_id);
		response.sendRedirect("/QG/personal/favorite.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

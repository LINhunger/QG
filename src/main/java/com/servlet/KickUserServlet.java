package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;

public class KickUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");// 被踢人 id

		Map<User, HttpSession> map = (Map<User, HttpSession>) getServletContext()
				.getAttribute("map");
		// 查找目标id
		for (User hasLoginUser : map.keySet()) {
			if (hasLoginUser.getId() == Integer.parseInt(id)) {
				// 找到被踢用户记录
				HttpSession hasLoginSession = map.get(hasLoginUser);
				hasLoginSession.invalidate();
				break;
			}
		}
		// 跳转回 列表页面
		response.sendRedirect("/QG/admin/kickuser.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

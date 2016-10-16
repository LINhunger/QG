<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.service.AdminService"  %>
<%@ page import="com.model.User"  %>

<!-- 好像这个没用上 -->


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户详细信息</title>
		<link type="text/css" href="/QG/css/picture.css" rel="stylesheet" />
		<link type="text/css" href="/QG/css/menu.css" rel="stylesheet" />
	</head>
	<%
			AdminService  service = new AdminService();
			int id =Integer.parseInt((String)request.getParameter("id"));
			User user = service.getUserById(id);
			pageContext.setAttribute("user", user);
	%>
<body>
<div class="myphoto">
<c:choose>
	<c:when test="${empty user.picture}">
			<img src="/QG/jpg/default.jpg"/>	
	</c:when>
	<c:otherwise>
			<img src="/QG/jpg/${user.picture}"/>
	</c:otherwise>
</c:choose>
	<div class="one">用户名：${user.username}</div>
	<div class="one">性别：${user.sex}</div>
	<div class="one">邮箱：${user.email}</div>
	<div class="one"><br/><br/></div>
</div>
<div class="test">
		 <ul>
		<li><a href="/QG/admin/adminpage.jsp">首页</a></li>
		<li><a href="/QG/admin/userarticle.jsp">文章列表</a></li>
		<li><a href="/QG/admin/resourcepage.jsp?id=${user.id}">资源列表</a></li>
		<li><a href="/QG/picture.jsp">个人信息</a></li>
		</ul> 
</div>
</body>
</html>

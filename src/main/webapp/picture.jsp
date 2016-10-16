<%@page import="com.model.*"%>
<%@page import="com.service.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人界面</title>
<link type="text/css" href="/QG/css/picture.css" rel="stylesheet" />
<link type="text/css" href="/QG/css/menu.css" rel="stylesheet" />
</head>
<%
	User user =(User) session.getAttribute("user");
	pageContext.setAttribute("user",user);
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
</div>

</body>
</html>
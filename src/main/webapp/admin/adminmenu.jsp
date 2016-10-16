<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>管理员主界面</title>
		<link type="text/css" href="/QG/css/homepage.css" rel="stylesheet" />
		<link type="text/css" href="/QG/css/menu.css" rel="stylesheet" />
	</head>
	<body>
	<div class="test"> <ul>
		<li><a href="/QG/success.jsp">首页</a></li>
		<li><a href="/QG/admin/userpage.jsp" >用户信息</a></li>
		<li><a href="/QG/admin/allresource.jsp"  target="_blank">资源信息</a></li>
		<li><a href="/QG/admin/kickuser.jsp" >踢人下线</a></li>
	</ul> </div>
		<div>
		<!-- 根据session中是否还有 用户数据，判断用户是否登陆 -->
  	<c:if test="${empty user}">
 		<p>您还未登陆 ,<a href="/QG/index.jsp">去登陆</a></p>
  	</c:if>
  	<c:if test="${not empty user}">
		<p ><font size="5">  &nbsp;&nbsp;您好！${user.username } <a href="/QG/invalidate.jsp">注销用户</a></font></p>
  	</c:if>
  		<h2>${message}</h2> 
		</div>
	
	</body>
</html>

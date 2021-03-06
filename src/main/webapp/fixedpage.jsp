<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>主界面</title>
		<link type="text/css" href="/QG/css/menu.css" rel="stylesheet" />
		<link type="text/css" href="/QG/css/homepage.css" rel="stylesheet" />
	</head>
	<body>
	<div class="test"> <ul>
		<li><a href="/QG/success.jsp">首页</a></li>
		<li><a href="/QG/article/allarticle.jsp">所有文章</a></li>
		<li><a href="/QG/uploadfile.jsp">资源上传</a></li>
		<li><a href="/QG/downloadfile.jsp">资源下载</a></li>
		<li><a href="/QG/personal/myarticle.jsp">个人信息</a></li>
		<li><a href="/QG/upload.jsp">上传照片</a></li>
		<li><a href="/QG/article/publish.jsp">编辑文章</a></li>
	</ul> </div>
	<div>
			<p class="but">
			<input type="button" value="注册" class="submitbutton" onclick="location.href='/QG/signUp.jsp';"/>
			</p>
	</div>
		<div>
		<!-- 根据session中是否还有 用户数据，判断用户是否登陆 -->
  	<c:if test="${empty user}">
  		<p><font size="5">您还未登陆 ,<a href="/QG/index.jsp">去登陆</a></font></p>
  	</c:if>
  	<c:if test="${not empty user}">
		<p ><font size="5">${user.username } <a href="/QG/invalidate.jsp">注销用户</a></font></p>
			 <c:if test="${user.admin==true}">
				 <a href="/QG/admin/adminpage.jsp">管理员界面</a>
  		</c:if>
  	</c:if>
  		<h2 style="color:green;">${message}</h2> 
		</div>
	</body>
</html>

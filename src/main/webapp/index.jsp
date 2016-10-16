<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link type="text/css" href="css/signup.css" rel="stylesheet" />
    <title>个人博客登录注册页面</title>
  
  <body>
				<jsp:include page="signIn.jsp"></jsp:include>
				<a href="signUp.jsp">还没账号？点击注册</a>
  </body>
</html>

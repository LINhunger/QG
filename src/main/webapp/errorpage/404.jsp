<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta content="3;url=/QG/success.jsp" http-equiv="refresh">
    <title>不存在的页面</title>

  
  <body>
<h1>对不起，您访问的资源不存在，服务器在三秒后跳转回主页面</h1>
  </body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link type="text/css" href="/QG/css/changepassword.css" rel="stylesheet" />
<script type="text/javascript" src="/QG/js/changepassword.js"></script>
</head>
<body>
<div class="box">
	<h1>用户密码修改表单</h1>
	<h2 style="color:red;">${message}</h2>
	<form action="/QG/com/servlet/ChangePasswordServlet"  onsubmit="return checkform(this);"  method="post">
	<table>
	<tr>
		<th>用户名: </th>
			<td>
				<input name="username" value="${user.username}" /> 
			</td>
	</tr>
	<tr>
	<th>原密码: </th>
		<td>
			<input type="password" name="Opassword" value=""/> 
		</td>
	</tr>
	<tr>
	<th>新密码: </th>
		<td>
			<input type="password" name="Npassword" value="" id="password"/> 
		</td>
	</tr>
			<tr>
				<td><input type="submit" value="提交" class="submitbutton"/></td>
			</tr>
			</table>
			</form>
	</div>
</body>
</html>

<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>注册界面</title>
		<link type="text/css" href="/QG/css/signup.css" rel="stylesheet" />
		<script type="text/javascript" src="/QG/js/signUp.js"></script>
		<script type="text/javascript">
				function change()
				{
					//切换验证码
					document.getElementById("myimg").src= "/QG/com/servlet/Verification?"+new Date().getTime();
				}
		</script>
	</head>
	<body>
	<div class="box">
	<h1>请填写您的基本信息</h1>
	<h2 style="color:red;">${message}</h2>
	<form action="/QG/com/servlet/SignUpServlet"  onsubmit="return validate_form(this);" method="post">
<table >
	<tr>
		<th>请输入您的用户名</th>
		<td>
			<input name="username"  value=""  id="name"/>
		</td>
	</tr>
    <tr>
		<th>请输入您的密码</th>
		<td>
			<input name="password" type="password"  value="" id="password"/>
		</td>
	</tr>
    <tr>
		<th>请输入您的邮箱</th>
		<td>
			<input name="email" value="" id="email"/>
		</td>
	</tr>
	<tr>
		<th>   请问您的性别是？</th>
	<td>
		男<input name="sex" type="radio" value="male" checked="checked"/>
	
		女<input name="sex" type="radio" value="female"	  />
	</td>
	</tr>
	<tr>
		<th>验证码: </th>
		<td>
			<input type="text" name="num"  id="num"/>
		</td>
		<td>
			<img src="/QG/com/servlet/Verification" id="myimg" onclick="change();" style= "cursor: pointer;"/ > 
		</td>
	</tr>
</table>
			<input type="submit" value="注册" class="submitbutton"/>
</form>
	</div>
	</body>
</html>

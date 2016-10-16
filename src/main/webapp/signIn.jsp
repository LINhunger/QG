<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>登录界面</title>
		<link type="text/css" href="css/signup.css" rel="stylesheet" />	
		<script type="text/javascript">
				function change()
				{
					//切换验证码
					document.getElementById("myimg").src= "/QG/com/servlet/Verification?"+new Date().getTime();
				}
				function init(){
					var s = "${cookie.username.value}";
					// 用js对 cookie 解码
					document.getElementById("username").value = decodeURIComponent(s);
				}
		</script>
	</head>
	<body onload="init();">
	<div class="">
	<h1>登录表单（此处应该有LOGO）</h1>
	<h2 style="color:red;">${message}</h2>
	<form action="com/servlet/SignInServlet" method="post">
	<table>
	<tr>
		<th>用户名: </th>
			<td>
				<input name="username" value="" /> 
			</td>
	</tr>
	<tr>
	<th>密码: </th>
		<td>
			<input type="password" name="password" value="${cookie.password.value }" /> 
		</td>
	</tr>
	<tr>
		<th>验证码: </th>
		<td>
			<input type="text" name="num" />
		</td>
		<td>
			<img src="/QG/com/servlet/Verification" id="myimg" onclick="change();" style= "cursor: pointer;"/>
		</td>
	</tr>
	<tr>
			<th> </th>
			<td><input type="checkbox" name="remember" value="remember" />记住用户名和密码 </td>
	</tr>
			<tr>
				<td><input type="submit" value="登录" class="submitbutton"/></td>
			</tr>
			</table>
			</form>
	</div>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>上传界面</title>
		<link type="text/css" href="/QG/css/signup.css" rel="stylesheet" />
	</head>
	<body>
	<div class="box">
	<h1>上传表单</h1>
	<h2 style="color:red;">${message}</h2>
	<form action="/QG/com/servlet/UploadServlet"   method="post" enctype="multipart/form-data">
<table >
	<tr>
		<th>上传资源: </th>
		<td>
			 <input type="file" name="upload"/><br/>
		</td>
	</tr>
	<tr>
		<th>资源描述: </th>
		<td>
			 <textarea name="description" rows="5"  cols="60"></textarea>
		</td>
	</tr>
</table>
			<input type="submit" value=" 提交" class="submitbutton"/>
</form>
	</div>
	</body>
</html>

<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>发表文章</title>
<link type="text/css" href="/QG/css/publish.css" rel="stylesheet" />
</head>
<body>
<div class="box">
	<h1>发表文章</h1>
	<form action="/QG/com/servlet/personal/ChangeArticleServlet" method="post">
	<h2 style="color:red;">${message}</h2>
	<table>
	<tr>
		<th>文章标题: </th>
			<td>
				<input name="a_title" value="${a_title}" /> 
			</td>
	</tr>
	<tr>
		<th>文章类别：</th>
		<td>
		<select name="a_type"style="width:100px">
				<option>JAVA</option>
				<option>C</option>
				<option >HTML</option>
				<option>WEB</option>
		</select>
		</td>
		
	</tr>
	
	<tr>
		<th>文章正文：</th>
		<td>
			<textarea name="a_content" style="width:320px; height:500px">${a_content}</textarea>
		</td>
	</tr>
			<tr>
				<input type="hidden"  name="a_id" value="${a_id}"/>
				<td><input type="submit" value="保存" class="submitbutton"/></td>
			</tr>
	
			</table>
	</form>
</div>
</body>
</html>
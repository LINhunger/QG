<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="com.service.ArticleService"  %>
<%@ page import="com.db.SplitPage" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>文章列表</title>
		<link type="text/css" href="/QG/css/signup.css" rel="stylesheet" />
	</head>
	<%
		ArticleService service = new ArticleService();//操作数据库
		//设置分页 
		SplitPage sp = new SplitPage();
		sp.setTotalRows(service.getTotalRows());
		String flag=request.getParameter("flag");
		int currentPage=sp.confirmPage(flag);
		request.setAttribute("articles", service.getAllArticle(sp));
	%>
	<body>
	<div class="box">
	<h1>文章列表</h1>
	<form action="/QG/com/servlet/article/SelectServlet"  method="get">
				搜索文章
					<input name="a_title"   value="" />
					<input type="submit" value="搜索" />
	</form>
	<br/>
	<c:if test="${empty articles}">
	<h2>没有文章可以浏览！</h2>
	</c:if>
<table >
	<c:if test="${not empty articles}">
		<c:forEach items="${articles}" var="article">
			<tr>
				<td>
			 		<a href="/QG/com/servlet/article/BrowseArticleServlet?a_id=${article.a_id}">文章标题： ${article.a_title} </a>
				</td>
			</tr>
			<tr>
				<td>
			 		  作者 ：${article.username}
				</td>
			</tr>
			<tr>
				<td>
			 		文章类型 ：${article.a_type}
				</td>
			</tr>
			<tr>
				<td>
			 		发布时间 ：${article.a_createtime}
				</td>
			</tr>
			<tr>
				<td>
			 		<hr/>
				</td>
			</tr>
			</c:forEach>
	</c:if>
</table>
</div>
	</body>
</html>

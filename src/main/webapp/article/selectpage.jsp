<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="com.service.ArticleService"  %>
<%@ page import="com.db.SplitPage" %>
<%@ page import="com.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>文章列表</title>
		<link type="text/css" href="/QG/css/selectpage.css" rel="stylesheet" />
<%
		ArticleService service = new ArticleService();//操作数据库
		//设置分页 
		SplitPage sp = new SplitPage();
		List<Article> articles = service.getAllArticle(sp);
		application.setAttribute("articles", articles);
%>
	</head>
	<body>
	<jsp:include page="/fixedpage.jsp"></jsp:include>
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
				<td colspan="2" style="width:800px">
			 		<a href="/QG/com/servlet/article/BrowseArticleServlet?a_id=${article.a_id}" target="_blank">文章标题： ${article.a_title} </a>
				</td>
			</tr>
			<tr>
				<td colspan="2">
			 		  作者 ：${article.username}
				</td>
			</tr>
			<tr>
				<td>
			 		文章类型 ：${article.a_type}
				</td>
				<td>
			 		发布时间 ：${article.a_createtime}
				</td>
			</tr>

			<tr>
			 		<td colspan="2"><hr/></td>
			</tr>
			</c:forEach>
	</c:if>
</table>
</div>
<jsp:include page="/article/decoration.jsp"></jsp:include>
	</body>
</html>

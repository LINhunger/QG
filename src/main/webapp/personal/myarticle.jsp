<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="com.service.ArticleService"  %>
<%@ page import="com.db.SplitPage" %>
<%@ page import="com.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>我的文章</title>
		<link type="text/css" href="/QG/css/selectpage.css" rel="stylesheet" />
<%
		ArticleService service = new ArticleService();//操作数据库
		User user = (User)session.getAttribute("user");
		int u_id=user.getId();
		//设置分页 
		SplitPage sp = null;
		if(session.getAttribute("masp")==null){
				sp=new SplitPage();
		}else{
				sp=(SplitPage)session.getAttribute("masp");
		}
		sp.setTotalRows(service.getTotalRows(u_id));
		String flag=request.getParameter("flag");
		int currentPage=sp.confirmPage(flag);
		session.setAttribute("masp", sp);
		List<Article> articles = service.getAllArticleByU_id(u_id, sp);
		pageContext.setAttribute("articles", articles);
%>
	</head>
	<body>
	<jsp:include page="/picture.jsp"></jsp:include>
	<jsp:include page="/personal/menu.jsp"></jsp:include>
	<div class="box">
	<h1>我的文章</h1>
	
	<br/>
	<c:if test="${empty articles}">
	<h2>没有文章可以浏览！</h2>
	</c:if>
<table >
	<c:if test="${not empty articles}">
		<c:forEach items="${articles}" var="article">
			<tr>
				<td colspan="2" style="width:700px">
			 		<a href="/QG/com/servlet/article/BrowseArticleServlet?a_id=${article.a_id}">文章标题： ${article.a_title} </a>
				</td>
			</tr>
			<tr>
				<td colspan="2">
			 		  作者 ：${article.username}
				</td>
				<td><a href="/QG/com/servlet/article/ModifyArticleServlet?a_id=${article.a_id }">编辑文章</a></td>
			</tr>
			<tr>
				<td>
			 		文章类型 ：${article.a_type}
				</td>
				<td>
			 		发布时间 ：${article.a_createtime}
				</td>
				<td><a href="/QG/com/servlet/personal/DeleteArticleServlet?a_id=${article.a_id }">删除文章</a></td>
			</tr>
			<tr>
					<td>
							文章状态：${article.status}
					</td>
					<td>
							文章通知：${article.inform}
					</td>
					<td><a href="/QG/com/servlet/personal/StatusServlet?a_id=${article.a_id }">发布\撤回</a></td>
			</tr>
			<tr>
			 		<td colspan="3"><hr/></td>
			</tr>
			</c:forEach>
	</c:if>
</table>
	<table>
		<tr>
		<td>第<%=currentPage %>页</td>
		<td><a href="/QG/personal/myarticle.jsp?flag=<%=SplitPage.FIRSTPAGE%>">首页</a></td>
    	<td><a href="/QG/personal/myarticle.jsp?flag=<%=SplitPage.PREVIOUSEPAGE %>">上一页</a></td>     
    	<td><a href="/QG/personal/myarticle.jsp?flag=<%=SplitPage.NEXTPAGE %>">下一页</a></td>
    	<td><a href="/QG/personal/myarticle.jsp?flag=<%=SplitPage.LASTPAGE %>">最后页</a></td>
    	<td>
    	<form action="/QG/personal/myarticle.jsp" method="get">
    	<td>
    			<input type="text" name="flag" style="width: 25px"></input>
    			<input type="submit" value="前往"/>
    	</td>
    	</form>
    	</td>
    </tr>
    </table>
</div>
	</body>
</html>

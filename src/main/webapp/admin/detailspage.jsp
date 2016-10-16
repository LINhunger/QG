<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="com.service.*" %>
<%@ page import="com.db.SplitPage" %>
<%@ page import="com.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户文章</title>
		<link type="text/css" href="/QG/css/userpage.css" rel="stylesheet" />
		<link type="text/css" href="/QG/css/picture.css" rel="stylesheet" />
<%
		ArticleService service = new ArticleService();//操作数据库
		int u_id=Integer.parseInt((String)request.getParameter("u_id"));//获取用户id
		//设置分页 
		SplitPage sp = null;
		if(session.getAttribute("aasp"+u_id)==null){
				sp=new SplitPage();
		}else{
				sp=(SplitPage)session.getAttribute("aasp"+u_id);
		}
		sp.setTotalRows(service.getTotalRows(u_id));
		String flag=request.getParameter("flag");
		int currentPage=sp.confirmPage(flag);
		session.setAttribute("aasp"+u_id, sp);
		List<Article> articles = service.getAllArticleByU_id(u_id, sp);
		pageContext.setAttribute("articles", articles);
%>
	</head>
	<body>
	<jsp:include page="/admin/adminmenu.jsp"></jsp:include>
	<br/><br/>
	<div class="box">
	<h1>用户文章</h1>	
	<br/>
	<c:if test="${empty articles}">
	<h2>没有文章可以浏览！</h2>
	</c:if>
<table >
	<c:if test="${not empty articles}">
		<c:forEach items="${articles}" var="article">
			<tr>
				<td colspan="2" style="width:650px">
			 		<a href="/QG/com/servlet/article/BrowseArticleServlet?a_id=${article.a_id}" target="_blank">文章标题： ${article.a_title} </a>
				</td>
			</tr>
			<tr>
				<td colspan="2">
			 		  作者 ：${article.username}
				</td>
				<td><a href="/QG/com/servlet/admin/LimitArticleServlet?a_id=${article.a_id }&u_id=${param.u_id}">查封文章</a></td>
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
					<td>
							文章状态：${article.status}
					</td>
					<td>
							文章通知：${article.inform}
					</td>
			</tr>
			<tr>
			 		<td colspan="3"><hr/></td>
			</tr>
			</c:forEach>
	</c:if>
</table>
</div>
<!-- 个人简介部分 -->
<%
		//获取指定用户对象
		UserService userService = new UserService();
		User user = userService.getUserById(u_id);
		pageContext.setAttribute("user", user);
%>
<div class="myphoto">
<c:choose>
	<c:when test="${empty user.picture}">
			<img src="/QG/jpg/default.jpg"/>	
	</c:when>
	<c:otherwise>
			<img src="/QG/jpg/${user.picture}"/>
	</c:otherwise>
</c:choose>
	<div class="one">用户名：${user.username}</div>
	<div class="one">性别：${user.sex}</div>
	<div class="one">邮箱：${user.email}</div>
</div>
<!-- 个人简介部分 -->
	<table>
		<tr>
		<td>第<%=currentPage %>页</td>
		<td><a href="/QG/admin/detailspage.jsp?u_id=${param.u_id }&flag=<%=SplitPage.FIRSTPAGE%>">首页</a></td>
    	<td><a href="/QG/admin/detailspage.jsp?u_id=${param.u_id }&flag=<%=SplitPage.PREVIOUSEPAGE %>">上一页</a></td>     
    	<td><a href="/QG/admin/detailspage.jsp?u_id=${param.u_id }&flag=<%=SplitPage.NEXTPAGE %>">下一页</a></td>
    	<td><a href="/QG/admin/detailspage.jsp?u_id=${param.u_id }&flag=<%=SplitPage.LASTPAGE %>">最后页</a></td>
		<td>
    	<form action="/QG/admin/detailspage.jsp" method="get">
    	<td>
    			<input type="text" name="flag" style="width: 25px"></input>
    			<input type="hidden" name="u_id" value="${param.u_id}"></input>
    			<input type="submit" value="前往"/>
    	</td>
    	</form>
    	</td>
    </tr>
    </table>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="com.service.*"  %>
<%@ page import="com.db.SplitPage" %>
<%@ page import="com.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>消息通知</title>
		<link type="text/css" href="/QG/css/selectpage.css" rel="stylesheet" />
		<link type="text/css" href="/QG/css/picture.css" rel="stylesheet" />
<%
		FavoriteService service = new FavoriteService();//操作数据库
		User user =(User)request.getSession().getAttribute("user");
		int u_id=user.getId();
		//设置分页 
		SplitPage sp = null;
		if(session.getAttribute("fsp"+u_id)==null){
			sp=new SplitPage();
		}else{
			sp=(SplitPage)session.getAttribute("fsp"+u_id);
		}
		sp.setTotalRows(service.getTotalRowsByU_id(u_id));
		String flag=request.getParameter("flag");
		int currentPage=sp.confirmPage(flag);
		session.setAttribute("fsp"+u_id, sp);
		pageContext.setAttribute("favorites", service.getAllFavoriteByU_id(u_id, sp));
%>
	</head>
	<body>
	<jsp:include page="/personal/menu.jsp"></jsp:include>
	<jsp:include page="/picture.jsp"></jsp:include>
	<div class="box">
	<h1>我的收藏</h1>
	<br/>
	<c:if test="${empty favorites}">
	<h2>还没有收藏！</h2>
	</c:if>
<table >
	<c:if test="${not empty favorites}">
		<c:forEach items="${favorites}" var="favorite">
			<tr>
				<td colspan="2" style="width:700px">
			 	<font size="4">${favorite.a_title}</font>	
				</td>
				<td><a href="/QG/article/article.jsp?a_id=${favorite.a_id}">查看详情</a></td>
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
		<td><a href="/QG/personal/favorite.jsp?flag=<%=SplitPage.FIRSTPAGE%>">首页</a></td>
    	<td><a href="/QG/personal/favorite.jsp?flag=<%=SplitPage.PREVIOUSEPAGE %>">上一页</a></td>     
    	<td><a href="/QG/personal/favorite.jsp?flag=<%=SplitPage.NEXTPAGE %>">下一页</a></td>
    	<td><a href="/QG/personal/favorite.jsp?flag=<%=SplitPage.LASTPAGE %>">最后页</a></td>
    	<td><a href="/QG/com/servlet/personal/ClearFavoriteServlet">清空收藏栏</a></td>
    </tr>
    </table>
</div>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="com.service.ResourceService"  %>
<%@ page import="com.db.SplitPage" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>下载界面</title>
		<link type="text/css" href="/QG/css/signup.css" rel="stylesheet" />
	</head>
	<%
		//设置分页功能的代码
		ResourceService service = new ResourceService();
		SplitPage sp = null;
		if(session.getAttribute("rsp")==null){
			sp=new SplitPage();
		}else{
			sp=(SplitPage)session.getAttribute("rsp");
		}
		sp.setTotalRows(service.getTotalRows());
		String flag=request.getParameter("flag");
		int currentPage=sp.confirmPage(flag);
		session.setAttribute("rsp", sp);
		pageContext.setAttribute("resources",service.getAllResource(sp));
	%>
	<body>
	<div class="box">
	<h1>下载列表</h1>
	<c:if test="${empty resources}">
	<h2>没有资源可以下载！</h2>
	</c:if>
<table >
	<c:if test="${not empty resources}">
		<c:forEach items="${resources}" var="resource">
			<tr>
				<td>文件名： ${resource.realname} </td>
				<td>
			 		<a href="/QG/com/servlet/DownloadServlet?id=${resource.id}">下载</a>
				</td>
			</tr>
			<tr>
				<td>
			 		上传时间 ：${resource.uploadtime}
				</td>
			</tr>
			<tr>
				<td>
			 		文件描述 ：${resource.description}
				</td>
			</tr>
			<tr>
				<td>
			 		发布者 ：${resource.username}
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
<table>
	<tr>
		<td>第<%=currentPage %>页</td>
		<td><a href="/QG/downloadfile.jsp?flag=<%=SplitPage.FIRSTPAGE%>">首页</a></td>
    	<td><a href="/QG/downloadfile.jsp?flag=<%=SplitPage.PREVIOUSEPAGE %>">上一页</a></td>     
    	<td><a href="/QG/downloadfile.jsp?flag=<%=SplitPage.NEXTPAGE %>">下一页</a></td>
    	<td><a href="/QG/downloadfile.jsp?flag=<%=SplitPage.LASTPAGE %>">最后页</a></td>
    	<td>
    	<form action="/QG/downloadfile.jsp" method="get">
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

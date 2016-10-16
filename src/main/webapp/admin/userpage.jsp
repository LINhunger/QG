<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.service.AdminService"%>
<%@ page import="com.model.User"  %>
<%@ page import="com.db.SplitPage" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>全部用户信息</title>
		<link type="text/css" href="/QG/css/userpage.css" rel="stylesheet" />
	</head>
	<%
			AdminService service = new AdminService();
			SplitPage sp = null;
			if(session.getAttribute("usp")==null){
				sp=new SplitPage();
			}else{
				sp=(SplitPage)session.getAttribute("usp");
			}
			sp.setTotalRows(service.getTotalRows());
			String flag=request.getParameter("flag");
			int currentPage=sp.confirmPage(flag);
			session.setAttribute("usp", sp);
			pageContext.setAttribute("users", service.getAllUser(sp));
	%>
	<body>
	<jsp:include page="/admin/adminmenu.jsp"></jsp:include>
	<div class="box"> 
		<h1>用户列表</h1>
		<table>
				<c:if test="${not empty users}">
						<c:forEach items="${users}" var="user">
								<tr>
										<td>用户名： ${user.username} </td><td style="width:500px">ID：${user.id} </td>
										<td>
			 									<a href="/QG/admin/detailspage.jsp?u_id=${user.id}">查看详情</a>
										</td>
								</tr>
								<tr>
									<td>
			 								用户密码 ：${user.password}
									</td>
									<td></td>
									<td>
			 									<a href="/QG/com/servlet/admin/KickServlet?id=${user.id}">关小黑屋</a>
									</td>
								</tr>
								<tr>
									<td>
			 								性别 ：${user.sex}
									</td>
									<td></td>
									<td>
			 									<a href="/QG/com/servlet/admin/ReleaseServlet?id=${user.id}">放他出来</a>
									</td>
								</tr>
								<tr>
									<td>
			 							权限 ：${user.admin}
									</td>
								</tr>
								<tr>
										<td>
			 								是否被禁 ：${user.limit}
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
		<td><a href="/QG/admin/userpage.jsp?flag=<%=SplitPage.FIRSTPAGE%>">首页</a></td>
    	<td><a href="/QG/admin/userpage.jsp?flag=<%=SplitPage.PREVIOUSEPAGE %>">上一页</a></td>     
    	<td><a href="/QG/admin/userpage.jsp?flag=<%=SplitPage.NEXTPAGE %>">下一页</a></td>
    	<td><a href="/QG/admin/userpage.jsp?flag=<%=SplitPage.LASTPAGE %>">最后页</a></td>
    	<td>
    	<form action="/QG/admin/userpage.jsp" method="get">
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

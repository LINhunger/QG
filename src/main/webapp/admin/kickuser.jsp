<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员开始踢人了</title>
<link type="text/css" href="/QG/css/selectpage.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="/admin/adminmenu.jsp"></jsp:include>
<div class="box">
<h1>在线用户列表</h1>
<h2>当前用户 ${user.username}</h2>
<table>
<c:forEach items="${map}" var="entry">
	<tr>
	<td colspan="2" style="width:680px">${entry.key.username } </td>
	<c:if test="${user.admin== 'true' && entry.key.admin== 'false' }">
		<td><a href="/QG/com/servlet/KickUserServlet?id=${entry.key.id}">踢他下线</a> </td>
	</c:if>
	</tr>
	<br/>
</c:forEach>
</table>
</div>
</body>
</html>

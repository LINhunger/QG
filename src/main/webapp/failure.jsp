<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>

<html>
	<head>
		<!-- Page title -->
		<title>Reaction</title>
		<!-- End of Page title -->
		<!-- Libraries -->
		<!-- End of Libraries -->	
	</head>
	<body>
	<div>
		
		<h1><%
		 String message=(String)request.getAttribute("message");
		 out.println(message);
		  %></h1>
		  <p><a href="/QG/index.jsp">返回主界面</a></p>   
	</div>
	</body>
</html>

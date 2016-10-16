<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	//删除Cookie信息
	/*
	Cookie usernamecookie = new Cookie("username", "");
	usernamecookie.setMaxAge(0);
	usernamecookie.setPath("/QG");
	response.addCookie(usernamecookie);
	Cookie passwordCookie = new Cookie("password", "");
	passwordCookie.setMaxAge(0);
	passwordCookie.setPath("/QG");
	response.addCookie(passwordCookie);*/
	// 销毁session对象
	session.invalidate();
%>
<c:redirect url="/index.jsp" context="/QG"></c:redirect>
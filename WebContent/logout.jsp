<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp" %>
<%@ include file="./include/head.jsp" %>
<%@ include file="./include/header.jsp" %>
<%
	//세션에 기록된 정보를 삭제
	session.removeAttribute("Login");
	
	// 세션에 기록된 정보를 null로 치환
	session.setAttribute("Login", null);
	
	// 메인 페이지로 이동
	response.sendRedirect("main.jsp");

%>
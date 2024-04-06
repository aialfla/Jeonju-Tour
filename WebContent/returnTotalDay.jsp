<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp" %>

<%
String res = request.getParameter("res");
System.out.println(res);
if(res==null) {
	out.print(0);
	
} else {
	out.print(res);
}

%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp" %>

<%
UsersVo LoginVo = (UsersVo)session.getAttribute("Login");
if(LoginVo==null)
{
	out.println("ERROR");
	return;
}

String uNO  = LoginVo.getuNO();
String rNO = request.getParameter("rNO");
// 유효성 검사
if (uNO == null || uNO.equals("") || rNO == null || rNO.equals("") )
{
	out.println("ERROR");
	return;
}

ReplyDao rd = new ReplyDao();

rd.Delete(rNO, uNO);
out.println("DEL");
%>

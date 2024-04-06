<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%@page import="dao.UsersDao"%>

<%
	String mail = request.getParameter("mail");
	if(mail == null || mail.equals(""))
	{
		out.println("ERROR");
		return;
	}
	
	// 이메일 중복 검사
	UsersDao dao = new UsersDao();
	
	int code = dao.IsDuplicate(mail);
	
	if(code == UsersDao.MAIL_ERROR)
	{
		out.println("Error");
	} else if (code == UsersDao.NOT_DUPLICATE)
	{
		out.println("NOT_DUPLICATE");		
	} else if (code == UsersDao.DUPLICATE)
	{
		out.println("DUPLICATE");			
	}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<% 
request.setCharacterEncoding("UTF-8");
SearchVo search = new SearchVo(request);
%>
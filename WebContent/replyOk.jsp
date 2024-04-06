<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>

<%
String tbNO = request.getParameter("tbNO");
String rNote = request.getParameter("rNote");
if (LoginVo == null) {
	%> 
	<script>
	alert("로그인 후 이용해주세요");
	document.location="login.jsp";
	</script>
	 <%
	return;
}
ReplyVo rv = new ReplyVo();
rv.setTbNO(tbNO);
rv.setrNote(rNote);
rv.setuNO(LoginVo.getuNO());
rv.setuName(LoginVo.getuName());

ReplyDao rd = new ReplyDao();
rd.Insert(rv);

%>

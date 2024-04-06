<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ include file="./config/config.jsp" %>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp" %>
<%
	// 파라메타 데이터
	String uNO = request.getParameter("uNO");
	String cbNO = request.getParameter("cbNO");
	// 로그인 유효성 검사
	if( uNO == null ||  uNO.equals("") ||
	   cbNO == null || cbNO.equals("") )
	{
		return;
	}
	LikesDao ld = new LikesDao();
	
/* 	System.out.println(uNO);
	System.out.println(cbNO);
 */	

	// 검사 통과 ㅡ> 추천 insert / delete
	LikesVo lv = new LikesVo();
	
	// 파라메타 데이터 lv정보 입력
	lv.setuNO(uNO);
	lv.setCbNO(cbNO);
	
	ld.Inquiry(lv);
	out.print(ld.boardInquiry(cbNO));

%>

<script>
	location.href = "view_course.jsp?cbNO=<%= cbNO %>";
</script>
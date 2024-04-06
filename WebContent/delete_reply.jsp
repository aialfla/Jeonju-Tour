<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>
<%
// 삭제 대상 게시글 번호를 받는다
ReplyDao rd = new ReplyDao();
String rNO = request.getParameter("rNO");

// 삭제 메소드 불러오기
rd.Deletemine(rNO,LoginVo.getuNO());

// 유효성체크 
if( rNO == null || rNO.equals("") )
{
	response.sendRedirect("board_mypage_reply.jsp");
	return;
}

%>

<script>
// 	alert("삭제가 완료되었습니다");
	location.href = "board_mypage_reply.jsp";
</script>


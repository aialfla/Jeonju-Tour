<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>
<%
// 삭제 대상 게시글 번호를 받는다
KeepDao kd = new KeepDao();
String tbNO = request.getParameter("tbNO");

// 삭제 메소드 불러오기
kd.Deletemine(tbNO,LoginVo.getuNO());

// 유효성체크 
if( tbNO == null || tbNO.equals("") || LoginVo == null)
{
	response.sendRedirect("board_mypage_keep.jsp");
	return;
}

%>

<script>
// 	alert("담기가 취소되었습니다");
	location.href = "board_mypage_keep.jsp";
</script>


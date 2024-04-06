<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>
<%
// 추천 대상 게시글 번호를 받는다
LikesDao ld = new LikesDao();
String cbNO = request.getParameter("cbNO");

// 추천 취소 메소드 불러오기
ld.Deletemine(cbNO,LoginVo.getuNO());

// 유효성체크 
if( cbNO == null || cbNO.equals("") )
{
	response.sendRedirect("board_mypage_like.jsp");
	return;
}

%>

<script>
// 	Swal.fire('취소가 완료되었습니다.', '', 'success');
	location.href = "board_mypage_like.jsp";
</script>


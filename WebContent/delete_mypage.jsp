<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>
<%
// 삭제 대상 게시글 번호를 받는다
CbDao cd = new CbDao();
String cbNO = request.getParameter("cbNO");

// 삭제 메소드 불러오기
cd.Deletemine(cbNO,LoginVo.getuNO());

// 유효성체크 
if( cbNO == null || cbNO.equals("") )
{
	response.sendRedirect("board_mypage.jsp");
	return;
}

%>

<script>
//	Swal.fire('삭제가 완료되었습니다.', '', 'success');
	location.href = "board_mypage.jsp";
</script>


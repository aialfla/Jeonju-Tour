<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	String mail = request.getParameter("mail");
	String pw = request.getParameter("pw");
	String iskakao = request.getParameter("kakao");
	
	if( iskakao != null && iskakao.equals("Y") )
	{
		// 카카오로그인
		// 파라메타로 받은 이메일 이름을
		UsersDao ud = new UsersDao();
		UsersVo uv = ud.Login(mail, "1234");
		if (uv != null) {
		//로그인 성공
		// userVo에 넣어서 vo를 세션에 넣는다
			session.setAttribute("Login",uv);
		%>
<!-- <!-- 		 뒤로 갈 히스토리가 있는 경우 및 우리 시스템에서 링크를 통해 유입된 경우 --> -->
<!-- 		if (document.referrer && document.referrer.indexOf("main.jsp") !== -1) { -->
<!-- 			history.back();     뒤로가기 -->
<!-- 		} -->
<!-- <!-- 		 히스토리가 없는 경우 (URL을 직접 입력하여 유입된 경우) --> -->
<!-- 		else { -->
<!-- 			location.href = "main.jsp";     메인페이지로  -->
<!-- 		} -->
		<%
			response.sendRedirect("main.jsp");
			
		return;
		} else {
			%>
			<script>
			Swal.fire(
				'로그인 실패',
				'',
				'error'
			);
//			alert("로그인 실패");
			</script>
			<%
			return;
		}
	}else
	{	// 일반 로그인
		if(mail == null || mail.equals("") || pw == null || pw.equals(""))
		{
			%>
			<script>
// 			Swal.fire(
// 				'아이디 또는 비밀번호가 입력되지 않았습니다',
// 				'',
// 				'warning'
// 			);
			alert("아이디 또는 비밀번호가 입력되지 않았습니다");
			document.location("login.jsp");
			</script>
			<%
			return;
		}
	}
	// 파라메타로 아이디 비번이 넘어옴
	UsersDao ud = new UsersDao();
	UsersVo uv = ud.Login(mail, pw);
	if (uv!=null) {
		//로그인 성공
		session.setAttribute("Login",uv);
		response.sendRedirect("main.jsp");
		return;
	} else {
		%>
		System.out.println(mail);
		System.out.println(pw);
		<script>
// 		Swal.fire(
// 			'로그인 실패',
// 			'',
// 			'error'
// 		);
		alert("로그인 실패");
		document.location="login.jsp";
		</script>
		<%
	}
		%>
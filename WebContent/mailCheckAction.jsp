<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.mail.Authenticator"%>
<%@ page import="javax.mail.Transport"%>
<%@ page import="javax.mail.Message"%>
<%@ page import="javax.mail.internet.InternetAddress"%>
<%@ page import="javax.mail.Address"%>
<%@ page import="javax.mail.internet.MimeMessage"%>
<%@ page import="javax.mail.Session"%>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="vo.*" %>
<%@ page import="util.*" %>
<%@ include file="./config/config.jsp" %>
<%@ include file="./include/head.jsp" %>

<%	
	// 파라메타로 받은 CODE 값
	String code = request.getParameter("code");
	
	SearchVo sv = new SearchVo();
	sv.setCode(code);
	
	CodeDao CodeDao = new CodeDao();
	String uMail = CodeDao.joinCode(sv);
	boolean flag = false;
	if( uMail != null )
	{
		flag = CodeDao.joinCodeOk(uMail);
		System.out.println("uv.getuMail(): "+uMail);
		%>
		<script>
		alert("인증이 완료되었습니다. 이제 로그인이 가능합니다!");
		//document.location = "main.jsp";
		window.close();
		</script>
		<%
	}else
	{
		%>
		<script>
		alert("올바른 인증코드가 아닙니다.");
		window.close();
// 		document.location = "main.jsp";
		</script>
		<%	
	}
%>
`<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

		<script>
			/* <script src="경로설정.js"> */
		</script>  
		
<%@ include file="./include/header.jsp" %>
<%
	// 파라메타 데이터
	String mail = request.getParameter("mail");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String kakao = request.getParameter("kakao");
	String level = request.getParameter("level");
	
	// 유효성검사
	
	// 중복 검사
	UsersDao dao = new UsersDao();
	if( dao.IsDuplicate(mail) == UsersDao.DUPLICATE)
	{	%>
		<script>
		alert("중복된 이메일입니다");
		document.location = "join.jsp";
		</script>
		<%
		return;
	}
	
	// 중복 검사 통과 ㅡ> 가입 진행
	dao = new UsersDao();

	// 회원가입  vo 객체 생성
	UsersVo vo = new UsersVo();

	// 파라메타로 받은 데이터를 vo에 정보를 입력
	vo.setuMail(mail);
	vo.setuPW(pw);
	vo.setuName(name);
	vo.setuIsKakao(kakao);
	vo.setuLevel(level);
	
	// <회원가입> 메일 인증 코드 생성
	String code = UUID.randomUUID().toString().replace("-", "");
	vo.setuCerti(code);
	
	// 넘어온 값이 카카오 가입하기가 아닐 때 메일 전송
	if(!kakao.equals("Y"))
	{
		// <회원가입> 메일 전송
		String host = "http://192.168.0.70:8080/ver02/";							// 나만의 전주여행 URL 서버 주소
		String from = "awsteamc@gmail.com";											// 나만의 전주여행 구글 계정
		String to = mail;															// <회원가입> 유저가 가입한 mail 값
		String subject = "회원가입을 위한 이메일 인증메일 입니다";								// 메일 제목
		String content = "아래 링크를 클릭하여 인증을 완료해주세요<br>"							// 메일 내용
					   + "<a href='"+ host +"mailCheckAction.jsp?code="+ code 		// URL 링크 생성
					   + "'>이메일 인증하기</a>";
						 
						 
		Properties p = new Properties();											// 구글 메일 라이브러리
		p.put("mail.smtp.user", from);												// 구글 메일 아이디
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "456");												// 구글 전용 포트 (고정값:456)
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.sockerFactory.fallback", "false");
		
		try
		{
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html; charset=UTF8");
			Transport.send(msg);
		} catch (Exception e)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일 인증 오류')");
			script.println("history.back();");
			script.println("</script>");
			return;
		}
	}
	
	
	
	if(dao.Join(vo) == false)
	{ %>
		<script>
		alert("joinOK::회원가입 실패");
		document.location = "join.jsp";
		return;
		</script>
		<%
	}
	%>
	<script>
	<%
	
	if(kakao.equals("Y") == true)
	{%>
		document.location = "main.jsp";
// 		Swal.fire(
// 			'회원가입 완료',
// 			'카카오회원가입이 완료되었습니다',
// 			'success'
//  		);
		alert("[카카오회원가입 성공]");
		return false;
	<%
	} else {
		%>
		document.location = "main.jsp";
// 		Swal.fire(
// 			'회원가입 성공',
// 			'메일 인증 후 로그인 가능합니다',
// 			'success'
// 		);
		alert("[회원가입 성공] 메일 인증 후 로그인 가능합니다");
		</script>		
	<%
	}%>
%>	

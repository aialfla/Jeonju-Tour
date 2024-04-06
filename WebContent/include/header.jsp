<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<% UsersVo LoginVo = (UsersVo)session.getAttribute("Login");
// if (LoginVo == null) {
// 	response.sendRedirect("main.jsp");
// }
%>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<!-- <script src="./js/jquery-3.7.1.js"></script> -->
	<script src="./js/tour_data.js"></script>
<!-- <script src="./js/reply.js"></script> -->
	<script src="./js/keep.js"></script>
	<script src="./js/common.js"></script>


</head>
<body>
    <header id="header">
		<div class="header_warp">
			<h1 class="logo"><a href="main.jsp"><span class="blind">홈으로</span></a></h1>
			<nav class="gnb">
				<h2 class="blind">주메뉴</h2>
				<ul class="depth01">
					<li><a href="write_course.jsp">일정 작성</a></li>
					<li><a href="board_course.jsp">추천 일정</a></li>
				</ul>
			</nav>
			<div class="btn_list">
				<ul>
					<%if (LoginVo == null) { %><li><a href="join.jsp"><i class="icon_join fi fi-rr-user-add"></i><span>회원가입</span></a></li> <% } %>
					<%if (LoginVo != null) { %><li><a href="board_mypage.jsp"><i class="icon_my fi fi-rr-user"></i><span>마이페이지</span></a></li> <% } %>
					<%if (LoginVo == null) { %><li><a href="login.jsp"><i class="icon_login fi fi-rr-lock"></i><span>로그인</span></a></li> <% } %>
					<%if (LoginVo != null) { %><li><a href="logout.jsp"><i class="icon_logout fi fi-rr-unlock"></i><span>로그아웃</span></a></li> <% } %>
				</ul>
			</div>
		</div>
	</header>
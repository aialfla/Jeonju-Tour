<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/head.jsp" %>
		<script>
		function Login(){
			let flag = false;
			if (flag==true) {
				return;
			}
			
			let mail = $("#mail");
			let pw = $("#pw");
			if (mail.val()=="") {
				Swal.fire(
					'이메일 주소를 입력하세요',
					'',
					'warning'
				);
////			alert("이메일 주소를 입력하세요");
				mail.focus();
				return false;
			}
			//비번이 입력되었는가
			if (pw.val()=="") {
				Swal.fire(
					'비밀번호를 입력하세요',
					'',
					'warning'
				);
//				alert("비밀번호를 입력하세요");
				pw.focus();
				return false;
			}
			
			document.login.submit();
			return true;
			}
			window.onload = function(){
				mail.focus();
			}
		</script>
		
		<!-- 카카오 간편 로그인 -->
		<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
		<script>
		window.Kakao.init('7b35d98f318af7a17e68ae83c9b285d7');
		// 카카오 로그인
		function kakaoLogin() {
			window.Kakao.Auth.login({
				scope: 'profile_nickname, account_email', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
				success: function(response){
					console.log(response); // 로그인 성공하면 받아오는 데이터
					window.Kakao.API.request({
						url: '/v2/user/me',
                        success: (res) => {
                            console.log(res);
                            const mail = res.kakao_account.email;
                            $("#kakao").val('Y');
                            $("#mail").val(mail);
                            document.login.submit();
                        }
					});
					//window.location.href='joinOk.jsp' //리다이렉트 되는 코드
				},
				fail: function(error) {
                    console.log(error);
                }
			});
		}
		</script>

<body>
<%@ include file="./include/header.jsp" %>
	<main id="main" class="sub_main">
		<div class="container">
			<h3 class="sub_tit">로그인</h2>
			<div class="input_wrap">
				<form action="loginOk.jsp" method="post" id="login" name="login" onsubmit="return Login();">
				<input type="hidden" name="kakao" id="kakao" value="N">
					<ul class="input_list">
						<li>
							<label for="mail">이메일</label>
							<input id="mail" name="mail" type="email" value="" placeholder="이메일을 입력해주세요">
						</li>
						<li>
							<label for="pw">비밀번호</label>
							<input id="pw" name="pw" type="password" value="" placeholder="비밀번호를 입력해주세요">
						</li>
					</ul>
					<ul class="btn_list">
						<li>
							<button type="submit">로그인</button>
						</li>
						<li class="btn_kakao" onclick="kakaoLogin();">
							<button type="button">카카오 로그인</button>
						</li>
					</ul>
					<ul class="link_list">
<!-- 						<li><a href="findPW.jsp"><span>비밀번호 찾기</span></a></li> -->
						<li><a href="join.jsp"><span>회원가입 바로가기</span><i class="fi fi-rr-angle-small-right"></i></a></li>
					</ul>
				</form>
			</div>
		</div>
	</main>
<%@ include file="./include/footer.jsp" %>


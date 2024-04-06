<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp" %>   
<%@ include file="./include/head.jsp" %>

		<script src="./js/join.js"></script>
		<script src="./js/mailcheck.js"></script>
		<script>
			/* <script src="경로설정.js"> */
			$(document).ready(function()
			{
				$("#mail").focus();
			});
		</script>
		<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
		
		<!-- 카카오 간편 로그인 -->
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
                            //console.log(res);
                            const mail = res.kakao_account.email;
                            const name = res.properties.nickname;
                            //console.log(mail);
                            //console.log(name);
                            $("#kakao").val('Y');
                            $("#level").val('U');
                            $("#mail").val(mail);
                            $("#name").val(name);
                            $("#pw").val('1234');
                            document.join.submit();
                        }
					});
					//window.location.href='kakaoOk.jsp' //리다이렉트 되는 코드
				},
				fail: function(error) {
                    console.log(error);
                }
			});
		}
		</script>
		
		<!-- JavaScript SDK로 Kakao.Auth.authorize 함수를 호출할 때는 SDK 초기화 시 사용된 JavaScript 키를 사용합니다. 하지만 REST API로 토큰 받기를 요청할 때는 REST API 키를 사용해야 합니다.  -->
		

<%@ include file="./include/header.jsp" %>
	<main id="main" class="sub_main">
		<div class="container">
			<h3 class="sub_tit">회원가입</h3>
				<div class="input_wrap">
					<form action="joinOk.jsp" method="post" id="join" name="join" onsubmit="return DoSubmit();">
					<input type="hidden" name="kakao" id="kakao" value="N">
					<input type="hidden" name="level" id="level" value="T">
					<ul class="input_list">
						<li>
							<label for="mail">이메일</label>
							<input class="joinID" type="email" name="mail" id="mail" placeholder="이메일을 입력해주세요">
							<button type="button" class="btn_check" onclick="mailcheck();">중복</button>
						</li>
						<li>
							<label for="pw">비밀번호</label>
							<input type="password" name="pw" id="pw" placeholder="비밀번호를 입력해주세요">
						</li>
						<li>
							<label for="pwc">비밀번호 확인</label>
							<input type="password" name="pwc" id="pwc" placeholder="비밀번호를 다시 입력해주세요">
						</li>
						<li>
							<label for="name">이름</label>
							<input id="name" type="text" name="name" id="name" placeholder="이름을 입력해주세요">
						</li>
					</ul>
					<ul class="btn_list">
						<li>
							<button type="submit">가입하기</button>
						</li>
						<li class="btn_kakao" onclick="kakaoLogin();">
							<button type="button">카카오 로그인</button>
						</li>
					</ul>
				</form>
			</div>
		</div>
	</main>
<%@ include file="./include/footer.jsp" %>

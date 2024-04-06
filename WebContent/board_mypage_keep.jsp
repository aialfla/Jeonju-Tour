<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>
<%
if (LoginVo == null) { response.sendRedirect("main.jsp"); return; } // 로그인 정보가 없으면 메인화면으로 보냄

//KeepDao 객체 생성 
KeepDao kd = new KeepDao();
kd.SelectKeep(LoginVo.getuNO()); // 추천일정 게시판 글 모두 불러오기
ArrayList<KeepVo> klist = kd.GetListAll(); // 불러온 게시글 리스트에 담기
//추천 일정 게시판의 게시글 수
int max = 0;
if (klist != null) max = klist.size();

%>
<!-- SCRIPT -->
<script>
function DoDelete(tbNO)
{
	Swal.fire({
	title: '담기를 취소하시겠습니까?',
	text: '',
	icon: 'error',
	
	showCancelButton: true,			// cancel버튼 보이기. 기본은 원래 없음
	confirmButtonColor: '#3085d6',	// confrim 버튼 색깔 지정
	cancelButtonColor: '#d33',		// cancel 버튼 색깔 지정
	confirmButtonText: '확인',		// confirm 버튼 텍스트 지정
	cancelButtonText: '뒤로',			// cancel 버튼 텍스트 지정
	
//	reverseButtons: true,			// 버튼 순서 거꾸로
	
	}).then((result) => {
	  if (result.value) {	// confirm 버튼 클릭시
		  document.location = "delete_keep.jsp?tbNO=" + tbNO;
	  }
	})
}


// function DoDelete(tbNO)
// {
// 	if( confirm("담기를 취소하시겠습니까?") == 1 )
// 	{
// 		document.location = "delete_keep.jsp?tbNO=" + tbNO;
// 	}
// }
</script>
	<main id="main" class="sub_main">
		<div class="container">
			<h3 class="sub_tit">마이페이지</h3>
			<div class="sub_menu">
					<ul>
						<li><a href="board_mypage.jsp">나의 일정</a></li>
						<li><a href="board_mypage_like.jsp">나의 추천일정</a></li>
						<li class="on"><a href="board_mypage_keep.jsp">담은 여행지</a></li>
						<li><a href="board_mypage_reply.jsp">나의 리뷰</a></li>
					</ul>
				</div>
			<section class="bcourse_warp MT40">
				<ul class="bcourse_list">
				
				<%
					for (int i=0; i<max; i++) { // 추천 일정 게시판 글 수 만큼 반복
						KeepVo kv = klist.get(i); // 리스트의 i번째 인덱스 객체를 꺼냄
				%>
					<li>
			<form action="delete_keep.jsp" method="post">
						<a href="view_mypage_keep.jsp?tbNO=<%= kv.getTbNO() %>">
							<span class="list_img" style="background-image: url(./img/dataimg/<%=kv.getTbPic() %>);"></span>
							<strong><%= kv.getTbTitle() %></strong>
							<p class="address"><%= kv.getTbAddr() %></p>
						</a>
<!-- 						<button type="button" class="btn_del"> -->
<!-- 							<i class="fi fi-rr-cross-small"></i> -->
<!-- 							<span class="hidden">삭제하기</span> -->
<!-- 						</button> -->
						
						<a href="javascript:DoDelete(<%= kv.getTbNO() %>);" class="btn_del">
							<i class="fi fi-rr-cross-small"></i>
							<span class="hidden">삭제하기</span>
						</a>
						
<%-- 						<input type="hidden" class="get_tbNO" name="tbNO" value="<%=kv.getTbNO()%>"> --%>
			</form>
					</li>
				<% } %>
				</ul>
			</section>
		</div>
	</main>
	<!------------------------------- Model --------------------------->
	<div id="modal_box" class="hidden modal_error">
		<div id="modal_con">
			<i class="fi fi-rr-triangle-warning"></i>
			<strong>담은 여행지 삭제</strong>
			<p>정말 여행지를 삭제하시겠습니까?</p>
			<ul class="btn_list">
				<li>
					<button id="modalbtn_close">취소</button>
				</li>
				<li>
					<button id="modalbtn_ok">삭제</button>
				</li>
			</ul>
		</div>
	</div>
<%@ include file="./include/footer.jsp" %>
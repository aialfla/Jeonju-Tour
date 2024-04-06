<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>
<!-- SCRIPT -->
<%
if (LoginVo == null) { response.sendRedirect("main.jsp"); return; } // 로그인 정보가 없으면 메인화면으로 보냄

//ReplyDao 객체 생성 
ReplyDao rd = new ReplyDao();
rd.Selectmine(LoginVo.getuNO()); // 내가 쓴 리뷰 모두 불러오기
ArrayList<ReplyVo> rlist = rd.GetListAll(); // 불러온 리뷰 리스트에 담기
//리뷰 게시판의 게시글 수
int max = 0;
if (rlist != null) max = rlist.size();

%>
<!-- SCRIPT -->
<script>
function DoDelete(rNO)
{
	Swal.fire({
	title: '리뷰를 삭제하시겠습니까?',
	text: '',
	icon: 'error',
	
	showCancelButton: true,			// cancel버튼 보이기. 기본은 원래 없음
	confirmButtonColor: '#3085d6',	// confrim 버튼 색깔 지정
	cancelButtonColor: '#d33',		// cancel 버튼 색깔 지정
	confirmButtonText: '삭제',		// confirm 버튼 텍스트 지정
	cancelButtonText: '취소',			// cancel 버튼 텍스트 지정
	
//	reverseButtons: true,			// 버튼 순서 거꾸로
	
	}).then((result) => {
	  if (result.value) {	// confirm 버튼 클릭시
		  document.location = "delete_reply.jsp?rNO=" + rNO;
	  }
	})
}


// function DoDelete(rNO)
// {	// 삭제 여부 얼럿
// 	if( confirm("리뷰를 삭제하시겠습니까?") == 1 )
// 	{
// 		document.location = "delete_reply.jsp?rNO=" + rNO;
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
						<li><a href="board_mypage_keep.jsp">담은 여행지</a></li>
						<li class="on"><a href="board_mypage_reply.jsp">나의 리뷰</a></li>
					</ul>
				</div>
			<section class="bcourse_warp MT40">
				<ul class="bcourse_list bcourse_review">
				
					<%
					for (int i=0; i<max; i++) { // 추천 일정 게시판 글 수 만큼 반복
						ReplyVo rv = rlist.get(i); // 리스트의 i번째 인덱스 객체를 꺼냄
						%>
					<li>
						<a href="view_mypage_reply.jsp?tbNO=<%= rv.getTbNO() %>">
							<p class="txt">
								<%= rv.getrNote().replace("<","&lt;").replace(">","&gt;").replace("\n","\n<br>") %>
							</p>
							<div class="review_info cf">
								<div class="place"><%= rv.getTbTitle() %></div>
								<div class="date"><%= rv.getrDate() %></div>
							</div>
						</a>
						<a href="javascript:DoDelete(<%= rv.getrNO() %>);" class="btn_del">
							<i class="fi fi-rr-cross-small"></i>
							<span class="hidden">삭제하기</span>
						</a>
					</li>
					<% } %>
				</ul>
			</section>
		</div>
	</main>
<%@ include file="./include/footer.jsp" %>
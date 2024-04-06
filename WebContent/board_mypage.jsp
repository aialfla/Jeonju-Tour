<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>
<!-- SCRIPT -->
<%
if (LoginVo == null) { response.sendRedirect("main.jsp"); return; } // 로그인 정보가 없으면 메인화면으로 보냄

//CbDao 객체 생성 
CbDao cd = new CbDao();
cd.SelectMine(LoginVo.getuNO()); // 추천일정 게시판 글 모두 불러오기
ArrayList<CbVo> clist = cd.GetListAll(); // 불러온 게시글 리스트에 담기
//추천 일정 게시판의 게시글 수
int max = 0;
if (clist != null) max = clist.size();

SdDao sd = new SdDao();

%>
<!-- SCRIPT -->
<script>
function DoDelete(cbNO)
{
	Swal.fire({
	title: '일정을 삭제하시겠습니까?',
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
		  document.location = "delete_mypage.jsp?cbNO=" + cbNO;
	  }
	})
}

// function DoDelete(cbNO)
// {	// 삭제 여부 얼럿
// 	if( confirm("일정을 삭제하시겠습니까?") == 1 )
// 	{
// 		document.location = "delete_mypage.jsp?cbNO=" + cbNO;
// 	}
// }
</script>
	<main id="main" class="sub_main">
		<div class="container">
			<h3 class="sub_tit">마이페이지</h3>
			<div class="sub_menu">
					<ul>
						<li class="on"><a href="board_mypage.jsp">나의 일정</a></li>
						<li><a href="board_mypage_like.jsp">나의 추천일정</a></li>
						<li><a href="board_mypage_keep.jsp">담은 여행지</a></li>
						<li><a href="board_mypage_reply.jsp">나의 리뷰</a></li>
					</ul>
				</div>
			<section class="bcourse_warp MT40">
				<form action="">
					<ul class="bcourse_list">
					<%
						for (int i=0; i<max; i++) { // 추천 일정 게시판 글 수 만큼 반복
							CbVo cv = clist.get(i); // 리스트의 i번째 인덱스 객체를 꺼냄
							// tagDao 객체 생성
							TagDao tagd = new TagDao(); 
							// taglist 리스트에 태그를 담는다
							ArrayList<TagVo> taglist = tagd.SelectCbTag(cv.getCbNO()); 
							
							%>
						<li>
							<a href="view_mypage.jsp?cbNO=<%= cv.getCbNO() %>">
								<span class="list_img" style="background-image: url(./img/dataimg/<%=sd.GetTbPic(cv.getCbNO())%>);"></span>
								<strong><%= cv.getCbTitle() %></strong>
								<div class="list_tag">
									<ul>
									<%
									// 추가되어있는 태그리스트 부르기
									if (taglist != null) { // 태그리스트에 뭔가 들어있으면
									for (TagVo tagv : taglist) { // 돌면서 전부 다 꺼낸다
									%>
										<li class="label_tag"><%= tagv.getTagWhat() %></li>
										<% } } %>
									</ul>
								</div>
							<a href="javascript:DoDelete(<%= cv.getCbNO() %>);" class="btn_del" id="confirm">
								<i class="fi fi-rr-cross-small"></i>
								<span class="hidden">삭제하기</span>
							</a>
							<script>
								$(function () {
								    $(document).on("click", "#confirm", function () {
								        action_popup.confirm("일정을 삭제하시겠습니까?", function (res) {
								            if (res) {
								            	document.location = "delete_mypage.jsp?cbNO=" + <%= cv.getCbNO() %>;
								            }
								        })
								    });
								});
							</script>
						</li>
						<% } %>
					</ul>
				</form>
			</section>
		</div>
	</main>
<%@ include file="./include/footer.jsp" %>
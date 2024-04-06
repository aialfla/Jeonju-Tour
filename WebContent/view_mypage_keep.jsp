<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>
<!-- SCRIPT -->
<% // 상세 내역
String tbNO = request.getParameter("tbNO");
if (tbNO == null || tbNO.equals("")) {
	return;
}
TbDao td = new TbDao();
TbVo tv = td.SelectOne(tbNO);

ReplyDao rd = new ReplyDao();
ArrayList <ReplyVo> rlist = rd.SelectAll(tbNO);
int max = 0;
if (rlist != null) {
	max = rlist.size();
}
%>
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


// 	function DoDelete(tbNO)
// 	{	// 게시글을 삭제할것인지 물어봄
// 		if( confirm("담기를 취소하시겠습니까?") == 1 )
// 		{
// 			document.location = "delete_keep.jsp?tbNO=" + tbNO;
// 		}
// 	}
</script>

<main id="main" class="sub_main">
	
	<div class="container view_mypage cf">
		<!-- 관광지 상세정보-->    
		<div class="more_group on">
			<div class="more_img"><img src="./img/dataimg/<%=tv.getTbPic() %>" alt=""></div>
			<ul class="more_list">
				<li class="more_tit">
					<dl>
						<dt class="more_title"><%=tv.getTbTitle() %></dt>
						<dd class="more_add"><%=tv.getTbAddr() %></dd>
					</dl>
				</li>
				<li class="more_con">
					<ul>
						<li>
							<dl class="MB10">
								<dt><i class="icon16 fi fi-rr-circle-phone"></i><span class="blind">전화번호</span></dt>
								<dd><%=tv.getTbTel() %></dd>
							</dl>
							<dl class="MB10">
								<dt><i class="icon16 fi fi-rr-clock"></i><span class="blind">운영시간</span></dt>
								<dd><%=tv.getTbTime() %></dd>
							</dl>
							<dl>
								<dt><i class="icon16 fi fi-rr-comment"></i><span class="blind">설명</span></dt>
								<dd><%=tv.getTbDetail() %></dd>
							</dl>
						</li>
					</ul>
				</li>
				<li class="review_box">
					<form action="#" method="get">
						<fieldset>
							<legend class="blind">댓글</legend>
							<ul class="review_list">
								<% 
									if (rlist != null) {
										for (ReplyVo rv : rlist) 
										{ %>
									<li>
										<p class="rfoot"><%=rv.getuName() %></p>
										<span class="rtime"><%=rv.getrDate()%></span>
										<p class="rcon"><%= rd._R(rv.getrNote().replace("<","&lt;").replace(">","&gt;").replace("\n","\n<br>")) %></p>
									</li>
									<%  }
									}else {%>
									<li class="noreview"></li>
								<%	} %>
							</ul>
						</fieldset>
					</form>
				</li>
			</ul>
		</div>
		<!-- 지도 -->
		<section class="map_wrap">
			<div id="map" class="map"></div>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b35d98f318af7a17e68ae83c9b285d7&libraries=services"></script>
			<script src="./js/kakaomap.js"></script>
		</section>
		
		<ul class="btn_board">
			<li>
				<a href="board_mypage_keep.jsp" class="btn_secound">목록으로</a>
			</li>
			<li>
				<a href="javascript:DoDelete(<%= tv.getTbNO() %>);" class="btn_third">담기 취소</a>
			</li>
	</div>
</main>
<%@ include file="./include/footer.jsp" %>

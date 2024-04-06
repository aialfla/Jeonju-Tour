<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<!-- SCRIPT -->

<!-- 담은 여행지 검색 -->

<%@ include file="./include/header.jsp" %> 
<%
if (LoginVo == null) {
	%>
	<script>
		Swal.fire(
			'로그인 후 이용해 주세요',
			'',
			'warning'
		);
//	alert("로그인 후 이용해주세요!");
	</script>
	<%
	response.sendRedirect("login.jsp");
	return;
}

TbDao td = new TbDao();
ArrayList<TbVo> tlist = new ArrayList<TbVo>();
td.SelectKeepAll(LoginVo.getuNO(), "T");
tlist = td.GetListAll();
%>
	<main id="main" class="sub_main">
		<div class="container">
			<h3 class="sub_tit">일정 작성</h3>
			<div class="tourcouse_wrap">
			<form method="get" id="write_course" name="write_course" action="write_ok.jsp" class="cf" onsubmit="return writeCheck();">
			<input type ="hidden" name="total_day" id="total_day" value="">
			<input type ="hidden" name="max_order" id="max_order" value="">
				<!-- 기본 입력정보 -->
				<section class="info_group">
					<ul class="info_list">
						<li>
							<label for="info_tit">제목</label>
							<input id="info_tit" name="title" type="text" class="col_w100">
						</li>
						<li class="info_date">
							<label for="info_date">기간</label>
							<input id="info_date_dep" name="from" type="date" class="col_w47" onchange="javascript:TotalDay();" >
							<span> - </span>
							<input id="info_date_arr" name="to" type="date" class="col_w47" onchange="javascript:TotalDay();">
						</li>
						<li class="info_two cf">
							<div>
								<label for="prps">목적</label>
								<select id="prps" name="prps" class="col_w100">
									<option value="">선택</option>
									<option value="D1">관광</option>
									<option value="D2">식도락</option>
									<option value="D3">체험</option>
									<option value="D4">휴식</option>
								</select>
							</div>
							<div>
								<label for="age">나이</label>
								<select id="age" name="age" class="col_w100">
									<option value="">선택</option>
									<option value="A1">20대 미만</option>
									 <option value="A2">20대</option>
									<option value="A3">30대</option>
									<option value="A4">40대</option>
									<option value="A5">50대</option>
									<option value="A6">60대 이상</option>
								</select>
							</div>
							<div>
								<label for="head">인원</label>
								<select id="head" name="head" class="col_w100">
									<option value="">선택</option>
									<option value="B1">혼자</option>
									<option value="B2">2인</option>
									<option value="B3">3~4인</option>
									<option value="B4">5인 이상</option>
								</select>
							</div>
						</li>

						<li>
							<label for="info_txt">내용</label>
							<textarea name="info_txt" id="info_txt" class="col_w100"></textarea>
						</li>
						<li class="info_radio">
							<label for="">추천코스 공개</label>
							<ul>
								<li>
									<input type="radio" name="public" id="public" value="Y" checked>
									<label for="public">공개</label>
								</li>
								<li>
									<input type="radio" name="public" id="private" value="N">
									<label for="private">비공개</label>
								</li>
							</ul>
								
							</li>
						</li>
					</ul>
				</section>
				<!-- 지도 -->
				<section class="map_group">
					<div id="map" class="map"></div>
					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b35d98f318af7a17e68ae83c9b285d7&libraries=services"></script>
					<script src="./js/kakaomap_list_tour.js"></script>
				</section>
				<!-- 관광지 목록 -->
				<section class="tourcon_group">
					<div class="tourcon_top">
						<div class="depth02">
							<ul>
								<li class="on" id="keep_list"><a href="javascript:">담은 여행지</a></li>
								<li id="total_list"><a href="javascript:()">전체 보기</a></li>
							</ul>
						</div>
					</div>
					<div class="depth03">
						<ul>
							<li class="on" id="catT"><a href="javascript:()">관광지</a></li>
							<li id="catR"><a href="javascript:()">음식점</a></li>
							<li id="catA"><a href="javascript:()">숙소</a></li>
						</ul>
					</div>
					
					<div class="list_group" id="show_area">
							<ul> 
							<!-- 추천 관광지 목록 출력 영역 -->
							</ul>
					</div>
				</section>

				<!-- 일정 계획표 -->
				<section class="tourplan_group">
					<div class="tourplan_box swiper mySwiper">
						<div class="btn_controll">
							<div class="swiper-button-next"></div>
							<div class="swiper-button-prev"></div>
						</div>
						<ul class="tourplan_list cf swiper-wrapper" id="add_day_here">
							<li class="swiper-slide nodate">
								<div class="tourplan_day">
									<button type= button class="btn_day"><strong>DAY <span></span></strong></button>
								</div>
								<div class="tourplan_con">
									<ul>
									</ul>
								</div>
							</li>
							<li class="swiper-slide nodate">
								<div class="tourplan_day">
									<button type= button class="btn_day"><strong>DAY <span></span></strong></button>
								</div>
								<div class="tourplan_con">
									<ul>
									</ul>
								</div>
							</li>
						</ul>
					</div>
				</section>
			</div>
			<!-- Initialize Swiper -->
			<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
			<!-- Swiper JS -->
			<script>
			  var swiper = new Swiper(".mySwiper",
				{
				  slidesPerView: 2,
			      slidesPerGroup: 1,
			      observer: true,
			      observeParents: true,
			      spaceBetween: 0,
				  navigation: {
					  nextEl: ".swiper-button-next",
			      	  prevEl: ".swiper-button-prev"
			      	  }
				}
			  );
			</script>
			
			<ul class="btn_board">
				<li>
					<a href="board_course.jsp" class="btn_secound" >취소하기</a>
				</li>
				<li>
					<button type="submit" class="btn_primary">등록하기</button>
				</li>
			</ul>
		</div>
	</main>
</form>
<script>
	$.ajax({
		url : "./write_search.jsp",
		type : "post",
		datatype : "json",
		data : {
			"type":"keep",
			"cat":"T"
			},
		success : function(result) {
			$("#show_area").html(result);
		}
	});
</script>		
<script src="./js/total_day.js"></script>
<script src="./js/write.js"></script>
<%@ include file="./include/footer.jsp" %> 

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
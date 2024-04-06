<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<!-- SCRIPT -->

<!-- 담은 여행지 검색 -->

<%@ include file="./include/header.jsp" %> 
<%
if (LoginVo == null) { // 로그인이 안돼있으면
	%>
	<script>
	Swal.fire(
			'로그인 후 이용해 주세요',
			'',
			'warning'
		);
// 	alert("로그인 후 이용해주세요!");
	</script>
	<%
	response.sendRedirect("login.jsp"); // 로그인 화면으로 
	return;
}

String cbNO = request.getParameter("cbNO"); // cbNO값을 파라미터로 받아와서 cbNO에 저장

TbDao td = new TbDao();
ArrayList<TbVo> tlist = new ArrayList<TbVo>();
td.SelectKeepAll(LoginVo.getuNO(), "T"); // 내가 담았던 모든 내역을  tlist에 담는 메소드
tlist = td.GetListAll(); // 

CbDao cd = new CbDao();
CbVo cv = cd.SelectOne(cbNO); // 받아온 cbNO를 특정해서 해당 게시글을 가져오는 메소드
String cbPeriod = cv.getCbPeriod();
// System.out.println(cbPeriod);
String[] period = cbPeriod.split("~");

DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Date dep = format.parse(period[0]); // 출발시간
Date arr = null;
if (period.length == 1) {
	arr = format.parse(period[0]);
} else { 
	arr = format.parse(period[1]); // 도착시간
}
long max_day_long = (arr.getTime() - dep.getTime())/(24*60*60*1000)+1;
int max_day = Long.valueOf(max_day_long).intValue();
System.out.println(max_day);


ArrayList<TagVo> taglist = new ArrayList<TagVo>();
TagDao tagd = new TagDao();
taglist = tagd.SelectCbTag(cbNO); // 받아온 cbNO를 특정해서 해당 게시글의 태그 목록을 가져오는 메소드

String age = ""; // 나이 태그를 담을 곳
String head = ""; // 인원 태그를 담을 곳
String prps = ""; // 목적 태그를 담을 곳
if(taglist!=null) {
	for (TagVo tagv : taglist) {
	// 	System.out.println("TAG : " + tagv.getTagNO());
		switch (tagv.getTagNO().charAt(0)) {
		case 'A': age = tagv.getTagNO(); break;
		case 'B': head = tagv.getTagNO(); break;
		case 'D': prps = tagv.getTagNO(); break;
		}
	}
}

SdDao sd = new SdDao();

int max = 1;
if (sd.MaxDay(cbNO) != null) max = Integer.parseInt(sd.MaxDay(cbNO));
int max_order = 1;

%>
	<main id="main" class="sub_main">
		<div class="container">
			<h3 class="sub_tit">일정 수정</h3>
			<div class="tourcouse_wrap">
			<form method="get" id="modify_course" name="modify_course" action="modify_ok.jsp" class="cf" onsubmit="return writeCheck();">
			<input type ="hidden" name="max_order" id="max_order" value="">
			<input type ="hidden" name="total_day" id="total_day" value="">
			<input type="hidden" name="cbNO" value="<%=cbNO %>">
				<!-- 기본 입력정보 -->
				<section class="info_group">
					<ul class="info_list">
						<li>
							<label for="info_tit">제목</label>
							<input id="info_tit" name="title" type="text" class="col_w100" value="<%=cv.getCbTitle() %>">
						</li>
						<li class="info_date">
							<label for="info_date">기간</label>
							<input id="info_date_dep" readonly name="from" type="date" class="col_w47" value="<%=period[0].trim()%>">
							<span> - </span>
							<input id="info_date_arr" readonly name="to" type="date" class="col_w47" value="<%=(period.length == 1)? period[0].trim():period[1].trim() %>">
						</li>
						<li class="info_two cf">
							<div>
								<label for="prps">목적</label>
								<select id="prps" name="prps" class="col_w100">
									<option value="" <%=prps==null? "selected":"" %>>선택</option>
									<option value="D1" <%=prps.equals("D1")||prps.equals("d1")? "selected":"" %>>관광</option>
									<option value="D2" <%=prps.equals("D2")||prps.equals("d2")? "selected":"" %>>식도락</option>
									<option value="D3" <%=prps.equals("D3")||prps.equals("d3")? "selected":"" %>>체험</option>
									<option value="D4" <%=prps.equals("D4")||prps.equals("d4")? "selected":"" %>>휴식</option>
								</select>
							</div>
							<div>
								<label for="age">나이</label>
								<select id="age" name="age" class="col_w100">
									<option value="" <%=age==null? "selected":"" %>>선택</option>
									<option value="A1" <%=age.equals("A1")||age.equals("a1")? "selected":"" %>>20대 미만</option>
									<option value="A2" <%=age.equals("A2")||age.equals("a2")? "selected":"" %>>20대</option>
									<option value="A3" <%=age.equals("A3")||age.equals("a3")? "selected":"" %>>30대</option>
									<option value="A4" <%=age.equals("A4")||age.equals("a4")? "selected":"" %>>40대</option>
									<option value="A5" <%=age.equals("A5")||age.equals("a5")? "selected":"" %>>50대</option>
									<option value="A6" <%=age.equals("A6")||age.equals("a6")? "selected":"" %>>60대 이상</option>
								</select>
							</div>
							<div>
								<label for="head">인원</label>
								<select id="head" name="head" class="col_w100">
									<option value="" <%=head==null? "selected":"" %>>선택</option>
									<option value="B1" <%=head.equals("B1")||head.equals("b1")? "selected":"" %>>혼자</option>
									<option value="B2" <%=head.equals("B2")||head.equals("b2")? "selected":"" %>>2인</option>
									<option value="B3" <%=head.equals("B3")||head.equals("b3")? "selected":"" %>>3~4인</option>
									<option value="B4" <%=head.equals("B4")||head.equals("b4")? "selected":"" %>>5인 이상</option>
								</select>
							</div>
						</li>

						<li>
							<label for="info_txt">내용</label>
							<textarea name="info_txt" id="info_txt" class="col_w100"><%= cv.getCbNote() %></textarea>
						</li>
						<li class="info_radio">
							<label for="">추천코스 공개</label>
							<ul>
								<li>
									<input type="radio" name="public" id="public" value="Y" <%=cv.getCbPublic().equals("Y")? "checked" : "" %>>
									<label for="public">공개</label>
								</li>
								<li>
									<input type="radio" name="public" id="private" value="N" <%=cv.getCbPublic().equals("N")? "checked" : "" %>>
									<label for="private">비공개</label>
								</li>
							</ul>
								
							</li>
						</li>
					</ul>
				</section>
				<!-- 지도 -->
				<section class="map_group">
					    <div id="map" class="map">
					    </div>
					    <div id="menu_wrap" class="bg_white">
					    </div>
					<!-- 7b35d98f318af7a17e68ae83c9b285d7 -->
					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b35d98f318af7a17e68ae83c9b285d7&libraries=services"></script>
					<script src="./js/kakaomap_list_tour.js"></script>
				</section>
				<!-- 관광지 목록 -->
				<section class="tourcon_group">
					<div class="tourcon_top">
						<div class="depth02">
							<ul>
								<li class="on" id="keep_list"><a href="javascript:">담은 여행지</a></li>
								<li id="total_list"><a href="javascript:">전체 보기</a></li>
							</ul>
						</div>
					</div>
					<div class="depth03">
						<ul>
							<li class="on" id="catT"><a href="javascript:">관광지</a></li>
							<li id="catR"><a href="javascript:">음식점</a></li>
							<li id="catA"><a href="javascript:">숙소</a></li>
						</ul>
					</div>
					
					<div class="list_group" id="show_area">
							<ul>
							
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
						<ul class="tourplan_list cf swiper-wrapper" >
							<% 
							for (int i = 1; i <= max_day; i++) {
							if (sd.MaxOrder(cbNO, i) != null) 
							max_order = Integer.parseInt(sd.MaxOrder(cbNO, i));
							System.out.println("modify_course[195]\nday"+i+"의 max_order : " +max_order);
							%>
							<li class="swiper-slide">
								<div class="tourplan_day d<%=i %>">
									<button type= button class='btn_day' onclick='chooseDay(this);'><strong>DAY <span><%=i %></span></strong></button>
								</div>
								<div class="tourplan_con d<%=i %>">
									<ul class="tour_add_here">
										<% for (int j = 1; j <= max_order; j++) {
											SdVo sv = sd.Read(cbNO, i, j);
											if (sv != null) {
											%>
										<li>
											<ul>
												<li class="tour_box">
													<div class="tnum"><span><%=sv.getsOrder() %></span></div>
													<input type="hidden" name="sorder" id="sorder" value="<%=sv.getsOrder() %>">
													<div class="tname"><%= sv.getTbTitle() %></div>
													<input type="hidden" name="tbNO" value="<%=sv.getTbNO() %>">
													<ul class="btn_list">
<!-- 														<li> -->
<!-- 															<button type="button"><i class="fi fi-rr-angle-small-up"></i><span class="blind">위로</span></button> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<button type="button"><i class="fi fi-rr-angle-small-down"></i><span class="blind">아래로</span></button> -->
<!-- 														</li> -->
														<li>
															<button type="button" class="btn_delete" onclick="day1_del(this);"><i class="fi fi-rr-cross-small"></i><span class="blind">삭제</span></button>
														</li>
													</ul>
													<% } %>
												</li>
											</ul>
										</li>
										<% } %>
									</ul>
								</div>
							</li>
						<% if (max_day == 1) { %>
							<li class='swiper-slide'>
								<div class='tourplan_day'>
									<strong> <span></span></strong>
								</div>
								<div class='tourplan_con'>
									<ul>
									</ul>
								</div>
							</li>
							<% } %>
							<%  } %>
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
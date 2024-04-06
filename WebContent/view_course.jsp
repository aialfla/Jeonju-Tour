<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp" %>

<%
// 파라메타로 넘어온 게시글 번호 확인
String cbNO = request.getParameter("cbNO");
//CbDao 객체 생성 
CbDao cd = new CbDao();
CbVo cv = cd.Read(cbNO);

//SdDao 객체 생성 
SdDao sd = new SdDao();
ArrayList<SdVo> slist = new ArrayList<SdVo> ();
int max = 0;
if (sd.MaxDay(cbNO) != null) {
	max = Integer.parseInt(sd.MaxDay(cbNO));
} else {
	max = 0;
}
System.out.println("maxday : " + max);
if (max != 0) {
	for (int i = 1; i <= max; i++) {
		SdVo sv = sd.Read(cbNO, i);
// 		System.out.println("sd.Read에 넣을 cbNO : " + cbNO);
		slist.add(sv);
	}
}

//유효성 체크
if( cbNO == null || cbNO.equals("") )
{
	%>
	<script>
		alert("유효하지 않은 접근입니다.");
		document.location = "board_course.jsp";
	</script>
	<%
	return;
}

if( cv == null )
{
	%>
	<script>
		alert("올바른 게시물 정보가 아닙니다.");
		document.location = "board_course.jsp?type=<%= search.getType() %>";
	</script>
	<%
	return;	
}



/* 추천 수 total */
LikesDao ld = new LikesDao();
LikesVo lv = new LikesVo();
String s_on = "";

%>

<!-- SCRIPT -->
<script>
	function DoDelete(cbNO)
	{	
		Swal.fire({
		title: '일정을 삭제하시겠습니까?',
		text: '',
		icon: 'error',
		
		showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
		confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
		cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
		confirmButtonText: '확인', // confirm 버튼 텍스트 지정
		cancelButtonText: '취소', // cancel 버튼 텍스트 지정
		
			reverseButtons: true, // 버튼 순서 거꾸로
		
		}).then((result) => {
		  if (result.value) {	// confirm 버튼 클릭시
			document.location = "delete_course.jsp?cbNO=" + cbNO;
		  }
		})
	}


// 	function DoDelete(cbNO)
// 	{	// 삭제 여부 얼럿
// 		if( confirm("나의 일정을 삭제하시겠습니까?") == 1 )
// 		{
// 			document.location = "delete_course.jsp?cbNO=" + cbNO;
// 		}
// 	}
</script>
	<main id="main" class="sub_main">
		<div class="container">
			<h3 class="sub_tit">여행 일정</h3>
			<div class="tourcouse_wrap view_tour cf">
				<!-- 기본 입력정보 -->
				<section class="info_group">
					<h4><%= cv.getCbTitle() %></h4>
					<ul class="view_tit cf">
						<li>
							<strong>작성자</strong> 
							<p><%= cv.getuName() %></p>
						</li>
						<li>
							<strong>등록일</strong> 
							<p><%= cv.getCbDate() %></p>
						</li>
						<li class="info_like">
							<strong class="btn_like">
								<i class="icon16 fi fi-rr-social-network"></i>추천
							</strong> 
							<p><%= cv.getlCnt() %></p>
						</li>
					</ul>
					<ul class="view_tit cf">
						<li>
							<strong>기간</strong> 
							<p><%= cv.getCbPeriod() %></p>
						</li>
						<li>
							<strong>나이</strong> 
							<p><%= cv.getAge() %></p>
						</li>
						<li>
							<strong>인원</strong> 
							<p><%= cv.getPerson() %></p>
						</li>
						<li>
							<strong>목적</strong> 
							<p><%= cv.getPurpose() %></p>
						</li>
					</ul>
					<div class="view_txt">
						<strong class="hidden">
							내용
						</strong> 
						<p><%= cv.getCbNote().replace("<","&lt;").replace(">","&gt;").replace("\n","<br>\n") %></p>
					</div>
				</section>
				<!-- 일정 계획표 -->
				<section class="tourplan_group">

					<div class="tourplan_box swiper mySwiper">
						<div class="btn_controll">
							<div class="swiper-button-next"></div>
							<div class="swiper-button-prev"></div>
						</div>
						<ul class="tourplan_list cf swiper-wrapper">
						<%
						if (max != 0) {
							for (int i = 1; i <= max; i++) { 
								SdVo sv = slist.get(i-1);
								int max_order = 0; 
								if (sd.MaxOrder(cbNO, i)!=null) {
									max_order = Integer.parseInt(sd.MaxOrder(cbNO, i));
						%>
							<li class="swiper-slide">
								<div class="tourplan_day">
									<strong>DAY <span><%= i %></span></strong>
								</div>
								<div class="tourplan_con">
									<ul>
									<%
									for (int j = 1; j <= max_order ; j++) { // 일정 테이블 수 만큼 반복
										SdVo each_sv = sd.Read(cbNO, i, j);
											if (each_sv != null) {
											
									%>
										<li>
											<ul>
												<li class="tour_box">
													<div class="tnum"><span><%=each_sv.getsOrder() %></span></div>
													<div class="tname">
														<input type="hidden" value=<%= each_sv.getTbNO() %> class="tbNO">
														<button type="button" class="more_title"><%=each_sv.getTbTitle() %></button>
													</div>
												</li>
											</ul>
										</li>
									<%
										}
									}
									%>
									</ul>
								</div>
							</li>
							<%	}
								 else {
									max_order = 0;
								}
							}
						}	
						%>
						</ul>
					</div>
				</section>
				<!-- Initialize Swiper -->
				<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
				<!-- Swiper JS -->
				<script>
				  var swiper = new Swiper(".mySwiper",
					{
					  navigation: {
						  nextEl: ".swiper-button-next",
				      	  prevEl: ".swiper-button-prev"
				      	  }
					}
				  );
				</script>
				
				<!-- 지도 -->
				<section class="map_group">
					<div id="map" class="map"></div>
					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b35d98f318af7a17e68ae83c9b285d7&libraries=services"></script>
					<script src="./js/kakaomap_course.js"></script>
				</section>
			</div>
			<ul class="btn_board">
				<li>
					<a href="./board_course.jsp?type=<%= search.getType()%>" class="btn_secound">목록으로</a>
				</li>
				<%
				if (LoginVo == null) {  
					
				} else if (!LoginVo.getuNO().equals(cv.getuNO())) { 
					// 게시글 추천인 목록 안에 로그인 유저 값 uNO 존재여부 확인 후 class 'ON' 변경
					if(ld.checkLike(cbNO, LoginVo.getuNO()) =="Y")
					{
						s_on = "on";
					} else {
						s_on = "";
					}
				%>
					<li>
						<a href="likeOk_view.jsp?cbNO=<%= cbNO %>&uNO=<%= LoginVo.getuNO() %>" class="btn_like <%= s_on %>">
							<i class="fi fi-rr-social-network"></i>
							<span class="txt">추천 </span>
						</a>
					</li>
				<% } else if (LoginVo.getuNO().equals(cv.getuNO())) { %>
					<li>
						<a href="modify_course.jsp?cbNO=<%= cbNO %>" class="btn_primary">수정하기</a>
					</li>
					<li>
						<a href="javascript:DoDelete(<%= cbNO %>);" class="btn_third">삭제하기</a>
					</li>
				<% } %>
			</ul>
		</div>


		<!-- 정보 더보기 -->
		<div id="tourinfo_warp">
			<div id="tourinfo_box">
				<div id="tourinfo_con">
						<!-- 관광지 상세정보-->    
<!-- 						<div class="more_group on"> -->
<!-- 							<div class="more_img"><img src="./img/list_img.jpg" alt=""></div> -->
<!-- 							<ul class="more_list"> -->
<!-- 								<li class="more_tit"> -->
<!-- 									<dl> -->
<!-- 										<dt>전주 한옥마을</dt> -->
<!-- 										<dd class="more_add">전주 완산구 남노송동</dd> -->
<!-- 									</dl> -->
<!-- 								</li> -->
<!-- 								<li class="more_con"> -->
<!-- 									<ul> -->
<!-- 										<li> -->
<!-- 											<dl class="MB10"> -->
<!-- 												<dt><i class="icon16 fi fi-rr-circle-phone"></i><span class="blind">전화번호</span></dt> -->
<!-- 												<dd>063-282-1330</dd> -->
<!-- 											</dl> -->
<!-- 											<dl class="MB10"> -->
<!-- 												<dt><i class="icon16 fi fi-rr-clock"></i><span class="blind">운영시간</span></dt> -->
<!-- 												<dd>24시간 운영 / 연중무휴</dd> -->
<!-- 											</dl> -->
<!-- 											<dl> -->
<!-- 												<dt><i class="icon16 fi fi-rr-comment"></i><span class="blind">설명</span></dt> -->
<!-- 												<dd>전주 풍남동 일대에 700여 채의 한옥이 군락을 이루고  -->
<!-- 													있는 국내 최대 규모의 전통 한옥촌이며, 전국 유일의  -->
<!-- 													도심 한옥군입니다.</dd> -->
<!-- 											</dl> -->
<!-- 										</li> -->
<!-- 									</ul> -->
<!-- 								</li> -->
<!-- 								<li class="review_box"> -->
<!-- 									<form action="#" method="get"> -->
<!-- 										<fieldset> -->
<!-- 											<legend class="blind">댓글</legend> -->
<!-- 											<ul class="review_list"> -->
<!-- 												<li> -->
<!-- 													<p class="rcon">먹거리 볼거리 천국 전주 한옥마을. -->
<!-- 														고즈넉한 한국 가옥의 멋스러움과 우리것의  -->
<!-- 														멋스러움을 충분히 감상할 수 있는 지역입니다. -->
<!-- 														아직도 안가보신 분들은 꼭 가보시기를 권합니다.</p> -->
<!-- 													<p class="rfoot">ezen123 <span class="rtime">23.08.31</span></p>                                          -->
<!-- 												</li> -->
<!-- 												<li> -->
<!-- 													<p class="rcon">먹거리 볼거리 천국 전주 한옥마을. -->
<!-- 														고즈넉한 한국 가옥의 멋스러움과 우리것의  -->
<!-- 														멋스러움을 충분히 감상할 수 있는 지역입니다. -->
<!-- 														아직도 안가보신 분들은 꼭 가보시기를 권합니다.</p> -->
<!-- 													<p class="rfoot">ezen123 <span class="rtime">23.08.31</span></p>                                          -->
<!-- 												</li> -->
<!-- 												<li> -->
<!-- 													<p class="rcon">먹거리 볼거리 천국 전주 한옥마을. -->
<!-- 														고즈넉한 한국 가옥의 멋스러움과 우리것의  -->
<!-- 														멋스러움을 충분히 감상할 수 있는 지역입니다. -->
<!-- 														아직도 안가보신 분들은 꼭 가보시기를 권합니다.</p> -->
<!-- 													<p class="rfoot">ezen123 <span class="rtime">23.08.31</span></p>                                          -->
<!-- 												</li> -->
<!-- 												<li> -->
<!-- 													<p class="rcon">먹거리 볼거리 천국 전주 한옥마을. -->
<!-- 														고즈넉한 한국 가옥의 멋스러움과 우리것의  -->
<!-- 														멋스러움을 충분히 감상할 수 있는 지역입니다. -->
<!-- 														아직도 안가보신 분들은 꼭 가보시기를 권합니다.</p> -->
<!-- 													<p class="rfoot">ezen123 <span class="rtime">23.08.31</span></p>                                          -->
<!-- 												</li> -->
<!-- 											</ul> -->
<!-- 										</fieldset> -->
<!-- 									</form> -->
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
						<!-- 지도 -->
<!-- 						<section class="map_wrap"> -->
<!-- 							<div id="map2" class="map"></div> -->
<!-- 							<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b35d98f318af7a17e68ae83c9b285d7&libraries=services"></script> -->
<!-- 							<script src="./js/kakaomap.js"></script> -->
<!-- 						</section> -->
					</div>
					<button id="btn_close">
						<i class="fi fi-rr-cross-small"></i>
						<span class="hidden">닫기</span>
					</button>
				</div>
			</div>
		</div>
		<script>
		$("#btn_close").click(function () {
			$("#tourinfo_warp").removeClass("on");
		});
		

		</script>
	</main>
<%@ include file="./include/footer.jsp" %>
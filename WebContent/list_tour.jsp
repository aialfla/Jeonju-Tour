<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="list_tour_top"></div>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>
<% // 왼쪽 관광지 목록 관련 자바영역
String cat    = request.getParameter("cat");
String age    = request.getParameter("age");
String head   = request.getParameter("head");
String period = request.getParameter("period");
String prps   = request.getParameter("prps");

// taglist_str에 null이 아닌 태그관련 파라미터들을 넣는다.
ArrayList<String> taglist_str = null;
if ((age    != null && !age.equals("")) 
 ||(head    != null && !head.equals(""))
 ||(period  != null && !period.equals("")) 
 ||(prps    != null && !prps.equals(""))) {
	taglist_str = new ArrayList<String>();
	taglist_str.add(age);
	taglist_str.add(head);
	taglist_str.add(period);
	taglist_str.add(prps);
}

// taglist_str의 내용을 taglist에 tagvo 형식으로 넣는다.
ArrayList<TagVo> taglist = null;
String tagParam = "";
if (taglist_str!=null) {
	taglist = new ArrayList<TagVo>();
	TbDao td = new TbDao();
	for (String tag:taglist_str) {
		TagVo tagv = new TagVo();
		tagv.setTagNO(tag);
// 		System.out.println(tag);
		taglist.add(tagv);
	}
	tagParam = td.GetTagParam(taglist);
	session.setAttribute("tagParam", tagParam);
}

// 카테고리 파라미터 기본값은 "T(관광지)"로 부여함
if (cat == null) cat = "T"; 

// TbDao 객체 생성
TbDao td = new TbDao();
td.SelectAll(taglist,cat);
ArrayList<TbVo> tlist = td.GetListAll();

// 추천 관광지 게시판에 작성된 게시글 수
int max = 0;
if (tlist != null) {max = tlist.size();}
%>

<% 
// 담기 관련 자바 영역
KeepDao kd = new KeepDao();
%>
	<main class="main_full" id="main">
		<!-- 관광지 목록 -->
		<div class="tourlist_container">
			<div class="tourlist_wrap">
				<div class="depth03">
					<ul>
						<li <%= (cat.equals("T")) ? "class='on'":"" %>><a href="list_tour.jsp?cat=T<%=tagParam%>">관광지</a></li>
						<li <%= (cat.equals("R")) ? "class='on'":"" %>><a href="list_tour.jsp?cat=R<%=tagParam%>">음식점</a></li>
						<li <%= (cat.equals("A")) ? "class='on'":"" %>><a href="list_tour.jsp?cat=A<%=tagParam%>">숙소</a></li>
					</ul>
				</div>
				<div class="list_group">
					<ul>
					<%
						if( tlist != null )
						{
							for( TbVo tv : tlist )
							{
								%>
								<li>
									<div class="list_con list_click">
											<input type="hidden" value="<%=tv.getTbNO()%>" class="tbNO">
										<dl>
											<dt>
												<strong class="list_tit more_title"><%=tv.getTbTitle() %></strong>
											</dt>
											<dd class="list_add"><%=tv.getTbAddr() %></dd>
											<dd class="list_img"><img src="./img/dataimg/<%=tv.getTbPic()%>" alt=""></dd>
										</dl>
									</div>
									<%if (LoginVo != null) { %>
									<button class="list_put keep put <%= kd.Kept(LoginVo.getuNO(), tv.getTbNO())? "on":"" %>">
									<% } else { %>
									<button class="list_put keep">
									<% } %>
										<input type="hidden" value="<%=LoginVo %>" class="LoginVo">
										<input type="hidden" value="<%=tv.getTbNO() %>" class="tbNO">
										<i class="icon_put fi fi-rr-map-marker-plus"></i>
										<span class="blind">관광지 담기</span>
									</button>
								</li>
								<%
							}
						} %>
					</ul>
				</div>
				<!-- 관광지 상세정보-->
				<div class="more_group">
					<!-- 닫기 버튼 -->
					<div class="btn_close">
						<button type="button"><i class="fi fi-rr-cross-small"></i><span class="blind">닫기</span></button>
					</div>
					<div class="more_img"><img src="" alt=""></div>
					<ul class="more_list">
						<li class="more_tit">
							<dl>
								<dt class="more_title"></dt>
								<dd class="more_add"></dd>
								<dd class="more_btn">
									<a href="#review" class="btn_review">
										<i class="icon16 fi fi-rr-pen-field"></i>리뷰쓰기
									</a>
									<input type="hidden" name="tbNO" id="tbNO" value="">
									<button class="btn_put"><i class="icon_put fi fi-rr-calendar-plus">
										<input type="hidden" value="" class="tbNO">
									</i><span>담기</span></button>
								</dd>
							</dl>
						</li>
						<li class="more_con">	
							<ul>
								<li>
									<dl class="MB10">
										<dt><i class="icon16 fi fi-rr-circle-phone"></i><span class="blind">전화번호</span></dt>
										<dd></dd>
									</dl>
									<dl class="MB10">
										<dt><i class="icon16 fi fi-rr-clock"></i><span class="blind">운영시간</span></dt>
										<dd></dd>
									</dl>
									<dl>
										<dt><i class="icon16 fi fi-rr-comment"></i><span class="blind">설명</span></dt>
										<dd></dd>
									</dl>
								</li>
							</ul>
							<ul>
								<li class="review_box">
									<form action="replyOk.jsp" method="post" id="reply">
									<input type="hidden" name="tbNO" id="tbNO" value="">
									<input type="hidden" name="uNO" id="uNO" value="">
										<fieldset>
											<legend class="blind">댓글</legend>
											<ul class="review_list">
												<li>
													<p class="rcon"></p>
													<p class="rfoot"><span class="rtime"></span></p>
													<a href="#" class="btn_rdel on" >
														<i class="fi fi-rr-cross-circle"></i>
														<span class="hidden">삭제하기</span>
													</a>
												</li>
											</ul>
											<div id="review" class="review_write">
												<label for="rwrite" class="blind">댓글 작성</label>
												<textarea id="rwrite" name="rNote" placeholder="소중한 후기를 남겨주세요."></textarea>
												<div><button type="button" class="btn_rOK" onclick="replyCheck();">등록</button></div>
											</div>
										</fieldset>
									</form>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- -------------------------------------------------------------- -->	
			</div>
		</div>
		<div class="map_wrap">
		    <div id="map" class="map"></div>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b35d98f318af7a17e68ae83c9b285d7&libraries=services"></script>
			<script src="./js/kakaomap_list_tour.js"></script>
		</div>
		
		<div class="btn_write"><button type="button" onclick="location.href='write_course.jsp';">일정 만들기</button></div>
	</main>
<%-- <%@ include file="./include/footer.jsp" %>  --%>
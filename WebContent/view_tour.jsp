<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script src="./js/tour_data.js"></script>
<!-- <script src="./js/reply.js"></script> -->
	<script src="./js/keep.js"></script>
	<script src="./js/common.js"></script>
	<!-- Swiper JS -->
	<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
	
</head>
<!-- SCRIPT -->
<% // 상세 내역
String tbNO = request.getParameter("tbNO");
System.out.println(tbNO);
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


<!-- 		<div class="more_group on" style="float: left;"> -->
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
										for (ReplyVo rv : rlist) { %>
									<li>
										<p class="rfoot"><%=rv.getuName() %> </p>
										<span class="rtime"><%=rv.getrDate()%></span>
										<p class="rcon"><%= rd._R(rv.getrNote().replace("<","&lt;").replace(">","&gt;").replace("\n","\n<br>")) %></p>
										                                        
									</li>
									<%  }
									}else {%>
									<li class="noreview">
<!-- 										<img src="./img/noreview.png" alt="작성된 리뷰가 없습니다"> -->
									</li>
								<%	} %>
							</ul>
						</fieldset>
					</form>
				</li>
			</ul>
<!-- 		</div> -->
<!-- 		<section class="map_wrap"> -->
<!-- 			<div id="map" class="map"></div> -->
<!-- 			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b35d98f318af7a17e68ae83c9b285d7&libraries=services"></script> -->
<!-- 			<script src="./js/kakaomap.js"></script> -->
<!-- 		</section> -->



<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<% session.removeAttribute("tagParam"); %>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>
	<main id="main" class="main_full main_bg">
		<form action="list_tour.jsp" method="get" name="tagform" id="tagform">
			<div class="container">
				<div class="main_warp">
					<div class="main_title">
						<h3><img src="./img/main_title.png" alt="나만의 전주여행"></h3>
						<p class="hidden">원하는 항목을 선택하면, 추천 여행지를 확인할 수 있습니다.</p>
					</div>
					<div class="main_tag">
						<h3 class="blind">추천여행지 맞춤서비스</h3>
						<ul>
							<li>
									<div class="checkbox_title">기간</div>
									<div class="checkbox_wrap">
										<div class="c-checkbox">
											<input type="checkbox" name="period" value="C1" class="c-checkbox_input" id="period_1">
											<label for="period_1" class="c-checkbox_label">당일치기 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="period" value="C2" class="c-checkbox_input" id="period_2">
											<label for="period_2" class="c-checkbox_label">1박 2일 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="period" value="C3" class="c-checkbox_input" id="period_3">
											<label for="period_3" class="c-checkbox_label">일주일 이내 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="period" value="C4" class="c-checkbox_input" id="period_4">
											<label for="period_4" class="c-checkbox_label">일주일 이상</label>
										</div>
									</div>
							</li>
							<li>
									<div class="checkbox_title">목적</div>
									<div class="checkbox_wrap">
										<div class="c-checkbox">
											<input type="checkbox" name="prps" value="D1" class="c-checkbox_input" id="prps_1">
											<label for="prps_1" class="c-checkbox_label">관광 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="prps" value="D2" class="c-checkbox_input" id="prps_2">
											<label for="prps_2" class="c-checkbox_label">식도락 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="prps" value="D3" class="c-checkbox_input" id="prps_3">
											<label for="prps_3" class="c-checkbox_label">체험</label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="prps" value="D4" class="c-checkbox_input" id="prps_4">
											<label for="prps_4" class="c-checkbox_label">휴식</label>
										</div>
									</div>
							</li>
							<li>
								<div class="checkbox_title">나이</div>
									<div class="checkbox_wrap">
										<div class="c-checkbox">
											<input type="checkbox" name="age" value="A1" class="c-checkbox_input" id="age_1">
											<label for="age_1" class="c-checkbox_label">20대 미만</label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="age" value="A2" class="c-checkbox_input" id="age_2">
											<label for="age_2" class="c-checkbox_label">20대 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="age" value="A3" class="c-checkbox_input" id="age_3">
											<label for="age_3" class="c-checkbox_label">30대 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="age" value="A4" class="c-checkbox_input" id="age_4">
											<label for="age_4" class="c-checkbox_label">40대 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="age" value="A5" class="c-checkbox_input" id="age_5">
											<label for="age_5" class="c-checkbox_label">50대 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="age" value="A6" class="c-checkbox_input" id="age_6">
											<label for="age_6" class="c-checkbox_label">60대 이상 </label>
										</div>
								</div>
							</li>
							<li>
									<div class="checkbox_title">인원</div>
									<div class="checkbox_wrap">
										<div class="c-checkbox">
											<input type="checkbox" name="head" value="B1" class="c-checkbox_input" id="head_1">
											<label for="head_1" class="c-checkbox_label">혼자 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="head" value="B2" class="c-checkbox_input" id="head_2">
											<label for="head_2" class="c-checkbox_label">2인 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="head" value="B3" class="c-checkbox_input" id="head_3">
											<label for="head_3" class="c-checkbox_label">3~4인 </label>
										</div>
										<div class="c-checkbox">
											<input type="checkbox" name="head" value="B4" class="c-checkbox_input" id="head_4">
											<label for="head_4" class="c-checkbox_label">5인 이상 </label>
										</div>
									</div>

							</li>
						</ul>
						<div class="btn_sumit">
							<button type="submit">
<!-- 								<a href="./list_tour.jsp"> -->
									<i class="fi fi-rr-angle-small-right"></i>
									<span class="hidden">검색하기</span>
<!-- 								</a> -->
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="guide">
			<img src="./img/bg_txtbox.png" alt="직접 여행 일정짜기가 어렵다면
			추천일정을 참고해보세요!">
			<a class="Jalnan" href="./board_course.jsp">?<span class="hidden">추천일정</span></a>
		</div>
	</main>
<script src="./js/main.js"></script>
</body>
</html>
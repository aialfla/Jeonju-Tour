<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<script src="./js/tour_data.js"></script>
<script src="./js/reply.js"></script>
</head>
<% // 상세 내역
String tbNO = request.getParameter("tbNO");
if (tbNO == null || tbNO.equals("")) {
	return;
}
TbDao td = new TbDao();
TbVo tv = td.SelectOne(tbNO);
%>

<% // 리뷰
UsersVo LoginVo = (UsersVo)session.getAttribute("Login");
if (LoginVo == null) {
	LoginVo = new UsersVo();
}
ReplyDao rd = new ReplyDao();
ArrayList <ReplyVo> rlist = rd.SelectAll(tbNO);
int max = 0;
if (rlist != null) {
	max = rlist.size();
}
%>
<% 
// 담기 관련 자바 영역
KeepDao kd = new KeepDao();
%>

		<!-- 닫기 버튼 -->
		<div class="btn_close">
			<button type="button"><i class="fi fi-rr-cross-small"></i><span class="blind">닫기</span></button>
		</div>
		<div class="more_img"><img src="./img/dataimg/<%=tv.getTbPic() %>" alt=""></div>
		<ul class="more_list">
			<li class="more_tit">
				<dl>
					<dt class="more_title"><%=tv.getTbTitle() %></dt>
					<dd class="more_add"><%=tv.getTbAddr() %></dd>
					<dd class="more_btn">
						<a href="#review" class="btn_review">
							<i class="icon16 fi fi-rr-pen-field"></i>리뷰쓰기
						</a>
						<input type="hidden" name="tbNO" id="tbNO" value=<%=tv.getTbNO() %>>
						<button class="btn_put <%= (LoginVo.getuNO() != null) ? "put" : "" %> <%= kd.Kept(LoginVo.getuNO(), tv.getTbNO())? "on":"" %>"><i class="icon16 fi fi-rr-map-marker-plus">
							<%-- <input type="hidden" value="<%=LoginVo %>" class="LoginVo"> --%>
							<input type="hidden" value="<%=tbNO %>" class="tbNO">
						</i><span>담기</span></button>
					</dd>
					<script>
						$(".btn_close").click(function () {
							$(".more_group").removeClass("on");
						});
					</script>
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
					<li class="review_box">
						<form action="replyOk.jsp" method="post" id="reply">
						<input type="hidden" name="tbNO" id="tbNO" value=<%=tv.getTbNO() %>>
						<input type="hidden" name="uNO" id="uNO" value=<%=LoginVo.getuNO() %>>
							<fieldset>
								<legend class="blind">댓글</legend>
								<ul class="review_list">
								<% 
									if (rlist != null) {
										for (ReplyVo rv : rlist) { %>
									<li>
										<p class="rfoot"><%=rv.getuName() %></p>	
										<span class="rtime"><%=rv.getrDate()%></span>
										<p class="rcon"><%= rv.getrNote().replace("<","&lt;").replace(">","&gt;").replace("\n","\n<br>") %></p>
																	
										<%
										
										if (rv.getuNO().equals(LoginVo.getuNO())) { %>
											<a href="javascript:;" class="btn_rdel on" >
											<input type="hidden" id="rNO" value=<%= rv.getrNO() %>>
												<i class="fi fi-rr-cross-circle"></i>
												<span class="hidden">삭제하기</span>
											</a>
										<% } %>
									</li>
								<% }
								}else {%>
									<li class="noreview"></li>
							<%	} %>
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
				

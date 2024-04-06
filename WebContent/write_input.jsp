<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<li>
<%@ include file="./config/config.jsp" %>


<%
UsersVo LoginVo = (UsersVo)session.getAttribute("Login");
if(LoginVo==null) return;

String tbNO = request.getParameter("tbNO");
String count = request.getParameter("count");
String sDay = request.getParameter("sDay");

// 유효성 검사
if (tbNO == null || tbNO.equals("") ) return;
TbDao td = new TbDao();
TbVo tv = td.SelectOne(tbNO);

%>
	<ul>
		<li class="tour_box">
			<div class="tnum"><span><%=count %></span></div>
			<input type="hidden" name="sorder" id="sorder" value="<%=count%>">
			<input type="hidden" name="sday" id="sday" value="<%=sDay%>">
			<div class="tname more_title"><%= tv.getTbTitle() %></div>
			<input type="hidden" name="tbNO" value="<%=tv.getTbNO() %>">
			<ul class="btn_list">
<!-- 				<li> -->
<!-- 					<button type="button"><i class="fi fi-rr-angle-small-up"></i><span class="blind">위로</span></button> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<button type="button"><i class="fi fi-rr-angle-small-down"></i><span class="blind">아래로</span></button> -->
<!-- 				</li> -->
				<li>
					<button type="button" class="btn_delete" onclick="day1_del(this);"><i class="fi fi-rr-cross-small"></i><span class="blind">삭제</span></button>
				</li>
			</ul>
		</li>
	</ul>
<script src="./js/write_input.js"></script>
</li>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./config/config.jsp"%>
<%
// loginvo 세션 불러오기
UsersVo LoginVo = (UsersVo)session.getAttribute("Login");
// System.out.println("uNO : " +LoginVo.getuNO());
String cat  = request.getParameter("cat");
String type = request.getParameter("type");
if (cat == null || cat.equals("")) {
	cat = "T";
}
if (type == null || type.equals("")) {
	type = "all";
}
// System.out.println(type);
// System.out.println(cat);
// 객체 생성
	TbDao td = new TbDao();
	ArrayList<TbVo> tlist = new ArrayList<TbVo>();
if(type.equals("all")){
	td.SelectAll(cat);
	tlist = td.GetListAll();
} else if (type.equals("keep")) {
	td.SelectKeepAll(LoginVo.getuNO(), cat);
	tlist = td.GetListAll();
}
%>

<!-- <div class="list_group"> -->
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
								<strong class="list_tit"><%=tv.getTbTitle() %></strong>
							</dt>
							<dd class="list_add"><%=tv.getTbAddr() %></dd>
							<dd class="list_img"><img src="./img/dataimg/<%=tv.getTbPic()%>" alt=""></dd>
						</dl>
						<button type="button" class="list_put onschedule on">
							<input type="hidden" value="<%=tv.getTbNO()%>" class="tbNO">
							<i class="icon_put fi fi-rr-calendar-plus""></i>
							<span class="blind">관광지 담기</span>
						</button>
					</div>
				</li>
				<%
			}
		} %>
	</ul>
	
<script>
$(".list_con").click(function(){							// 해당 객체가 클릭되는 이벤트가 발생하면
	var tbNO = $(this).find(".tbNO").val();					
	var count = $('.tourplan_con.d1').find(".tour_box").length+1;
	var sDay;
	if ($('.tourplan_con.selected').hasClass("d1") == true) {
		sDay = "1";
		count = $('.tourplan_con.d1').find(".tour_box").length+1;
	} else if ($('.tourplan_con.selected').hasClass("d2") == true) {
		sDay = "2";
		count = $('.tourplan_con.d2').find(".tour_box").length+1;
	} else if ($('.tourplan_con.selected').hasClass("d3") == true) {
		sDay = "3";
		count = $('.tourplan_con.d3').find(".tour_box").length+1;
	} else if ($('.tourplan_con.selected').hasClass("d4") == true) {
		sDay = "4";
		count = $('.tourplan_con.d4').find(".tour_box").length+1;
	} else if ($('.tourplan_con.selected').hasClass("d5") == true) {
		sDay = "5";
		count = $('.tourplan_con.d5').find(".tour_box").length+1;
	} else if ($('.tourplan_con.selected').hasClass("d6") == true) {
		sDay = "6";
		count = $('.tourplan_con.d6').find(".tour_box").length+1;
	} else if ($('.tourplan_con.selected').hasClass("d7") == true) {
		sDay = "7";
		count = $('.tourplan_con.d7').find(".tour_box").length+1;
	} else if ($('.tourplan_con.selected').hasClass("d8") == true) {
		sDay = "8";
		count = $('.tourplan_con.d8').find(".tour_box").length+1;
	} else if ($('.tourplan_con.selected').hasClass("d9") == true) {
		sDay = "9";
		count = $('.tourplan_con.d9').find(".tour_box").length+1;
	} else if ($('.tourplan_con.selected').hasClass("d10") == true) {
		sDay = "10";
		count = $('.tourplan_con.d10').find(".tour_box").length+1;
	}
	var order_size = $(".tourplan_con").length;
// 	alert("왜 두번 뜰까요?");
	$.ajax({
		url : "./write_input.jsp",
		type : "post",
		datatype : "json",
		data : 
		{
			"tbNO":tbNO,
			"count":count,
			"sDay":sDay
		},
		success : function(result) {
			$(".tour_add_here.on").parent().parent().removeClass("noplan");
			$(".tour_add_here.on").append(result);
		}
	});
	
	var title = $(this).parent().find(".list_tit").text().trim();
	var keyword = $(this).parent().find(".list_add").text().trim();
//	var title = $(".list_tit").text().trim();
//	var keyword = $(".list_add").text().trim();
// 	alert("test")
	$.ajax({
		url : "./write_map.jsp",
		type : "post",
		datatype : "json",
		data : 
		{
			"title":title,
			"keyword":keyword
		},
		success : function(result) {
			$(".map_group").html(result);
		}
	});
	return false;
}); 
// function day1_del( obj )
// {
// 	if(confirm("이 일정을 삭제하시겠습니까?")==1) {
// //		console.log($(obj).parent().parent().parent().parent().parent())
// 		$(obj).parent().parent().parent().parent().parent().remove();
// 	}
// 	// 번호들 다시 넘버링
// 	$("div.tnum").each(function(index,item){
// 		$(item).find("span").text(++index);
// 		$(this).parent().find("#sorder").val(index);
// 	});
// 	return;
// }

function day1_del( obj )
{ 
	var t_ul = $(obj).parents(".tour_add_here");
	if(confirm("이 일정을 삭제하시겠습니까?")==1) {
		$(obj).parent().parent().parent().parent().parent().remove();
	}
	// 번호들 다시 넘버링
	$(t_ul).find("div.tnum").each(function(index,item){
		$(this).find("span").text(++index);
		$(this).parents(".tour_box").find("#sorder").val(index);
	});
	return;
}
</script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

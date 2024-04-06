window.onload = function() {

	// list_tour 상세정보 열고 닫기
	$(".list_click").click(function () {
		var tbNO = $(this).find(".tbNO").val();
		var more_tbNO = $(".more_group").find("input.tbNO").val();
		if (tbNO == more_tbNO) {
			$(".more_group").toggleClass("on");
		} else {
			$(".more_group").addClass("on");
			$.ajax({
				url : "./tour_data.jsp",
				type : "post",
				datatype : "jsp",
				data : "tbNO="+tbNO,
				success : function(result) {
					$(".more_group").html(result);
					var title = $(".more_group").find(".more_title").text();
					var keyword = $(".more_group").find(".more_add").text();
					setPlace(keyword, title);
				}
			});
		}
		
	});
	
	
	$(".btn_close").click(function () {
		$(".more_group").removeClass("on");
	});
	
	
	
	// tourinfo_warp 열고 닫기
	$(".tname").click(function () {
		var tbNO = $(this).find(".tbNO").val();
			$("#tourinfo_warp").removeClass("on");
			$("#tourinfo_warp").addClass("on");
			$.ajax({
				url : "./view_tour.jsp",
				type : "post",
				datatype : "jsp",
				data : "tbNO="+tbNO,
				success : function(result) {
					$("#tourinfo_con").html(result);
//					var keyword = $(".more_group").find(".more_add").text();
//					var title = $(".more_group").find(".more_title").text();
//					setPlace(keyword, title);
				}
			});
	});
	
	$("#btn_close").click(function () {
		$(".tourinfo_warp").removeClass("on");
	});
	
	
	
	
	// 이동수단 값의 따른 아이콘 클래스 추가
	$('.move_icon').each(function(index, item) {
		console.log($(item));
		switch(item.innerText) 
		{
			case "버스":
				$(item).addClass("move_bus");
				break;
			case "자동차":
				$(item).addClass("move_car");
				break;
			case "자전거":
				$(item).addClass("move_bycle");
				break;
			case "도보":
				$(item).addClass("move_walk");
				break;
		}
	});


	// toTop button
	$(function() {
		//스크롤 위치에 따라 버튼 표출
		$(window).scroll(function() {
			if ($(this).scrollTop() > 200) {
				$('#toTop').fadeIn();
			} else {
				$('#toTop').fadeOut();
			}
		});
		// 부드럽게 스크롤
		$("#toTop").click(function() {
			$('html, body').animate({
				scrollTop : 0
			}, 300);
			return false;
		});
	});





}
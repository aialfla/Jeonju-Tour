$(function() {
	$("#info_tit").focus();
	
	$("#total_list").click(function () {
		var cat = "";
		if ($(this).parent().parent().parent().parent().find("#catT").hasClass("on")) {
			cat = "T";
		} else if ($(this).parent().parent().parent().parent().find("#catR").hasClass("on")) {
			cat = "R";
		} else if ($(this).parent().parent().parent().parent().find("#catA").hasClass("on")) {
			cat = "A";
		}
		$(this).addClass("on");
		$(this).parent().find("#keep_list").removeClass("on");
		$.ajax({
			url : "./write_search.jsp",
			type : "post",
			datatype : "json",
			data : {
				"type":"all",
				"cat":cat
				},
			success : function(result) {
				$("#show_area").html(result);
			}
		});
	});
	
	$("#keep_list").click(function () {
		var cat = "";
		if ($(this).parent().parent().parent().parent().find("#catT").hasClass("on")) {
			cat = "T";
		} else if ($(this).parent().parent().parent().parent().find("#catR").hasClass("on")) {
			cat = "R";
		} else if ($(this).parent().parent().parent().parent().find("#catA").hasClass("on")) {
			cat = "A";
		}
		$(this).addClass("on");
		$(this).parent().find("#total_list").removeClass("on");
		$.ajax({
			url : "./write_search.jsp",
			type : "post",
			datatype : "json",
			data : {
				"type":"keep",
				"cat":cat
				},
			success : function(result) {
				$("#show_area").html(result);
			}
		});
	});
	$("#catT").click(function () {
		var type = "";
		if ($(this).parent().parent().parent().find("#keep_list").hasClass("on")) {
			type = "keep";
		} else if ($(this).parent().parent().parent().find("#total_list").hasClass("on")) {
			type = "all";
		}
		$(this).addClass("on");
		$(this).parent().find("#catR").removeClass("on");
		$(this).parent().find("#catA").removeClass("on");
		$.ajax({
			url : "./write_search.jsp",
			type : "post",
			datatype : "json",
			data : {
				"type":type,
				"cat":"T"
				},
			success : function(result) {
				$("#show_area").html(result);
			}
		});
	});
	$("#catR").click(function () {
		var type = "";
		if ($(this).parent().parent().parent().find("#keep_list").hasClass("on")) {
			type = "keep";
		} else if ($(this).parent().parent().parent().find("#total_list").hasClass("on")) {
			type = "all";
		}
		$(this).addClass("on");
		$(this).parent().find("#catT").removeClass("on");
		$(this).parent().find("#catA").removeClass("on");
		$.ajax({
			url : "./write_search.jsp",
			type : "post",
			datatype : "json",
			data : {
				"type":type,
				"cat":"R"
				},
			success : function(result) {
				$("#show_area").html(result);
			}
		});
	});
	$("#catA").click(function () {
		var type = "";
		if ($(this).parent().parent().parent().find("#keep_list").hasClass("on")) {
			type = "keep";
		} else if ($(this).parent().parent().parent().find("#total_list").hasClass("on")) {
			type = "all";
		}
		$(this).addClass("on");
		$(this).parent().find("#catT").removeClass("on");
		$(this).parent().find("#catR").removeClass("on");
		$.ajax({
			url : "./write_search.jsp",
			type : "post",
			datatype : "json",
			data : {
				"type":type,
				"cat":"A"
				},
			success : function(result) {
				$("#show_area").html(result);
			}
		});
	});
	
	
//	$(".list_con").click(function(){
//		var tbNO = $(this).find(".tbNO").val();
//		var count = $(".tour_box").length+1;
//		alert("왜 두번 뜨냐고요? 너때문이었어요");
//		$.ajax({
//			url : "./write_input.jsp",
//			type : "post",
//			datatype : "json",
//			data : 
//			{
//				"tbNO":tbNO,
//				"count":count
//			},
//			success : function(result) {
//				$(".tour_add_here").append(result);
//			}
//		});
////		return false;
//	}); 
	


	// map 에이작스로 불러오기
//	$(".list_con").click(function(){
//		var title = $(".more_title").text().trim();
//		console.log(title);
//		alert("테스트");
//		$.ajax({
//			url : "./write_map.jsp",
//			type : "post",
//			datatype : "json",
//			data : 
//			{
//				"title":title
//			},
//			success : function(result) {
//				$(".map_group").html(result);
//			}
//		});
//	}); 
	

	
	$(".btn_delete").click(function(){
		if(confirm("이 일정을 삭제하시겠습니까?")==1) {
			$(this).parent().parent().parent().parent().find(".move_box").remove();
			$(this).parent().parent().parent().remove();
		}
		return;
	});
	
	
});


const date = new Date();

function dateDiff(start_date, end_date) {
    let dateDiff =
    (new Date(end_date).getTime() - new Date(start_date).getTime())
    / (1000 * 60 * 60 * 24);
    return dateDiff;
}

function chooseDay(obj) {
	$(obj).parent().parent().parent().find(".tour_add_here").removeClass("on");
	$(obj).parent().parent().find(".tour_add_here").addClass("on");
	$(obj).parent().parent().parent().find("li").removeClass("on");
	$(obj).parent().parent().addClass("on");
	$(obj).parent().parent().parent().find(".tourplan_con").removeClass("selected");
	$(obj).parent().parent().find(".tourplan_con").addClass("selected");
}


function writeCheck() {
	var max_order = $("input[name=sorder]").length
	$("#total_day").val(dateDiff($("#info_date_dep").val(),$("#info_date_arr").val()));
	$("#max_order").val(max_order);
	var max_day = dateDiff($("#info_date_dep").val(),$("#info_date_arr").val())+1;
	
	if ($("#info_tit").val() == "") {
		Swal.fire(
				'제목을 입력해주세요',
				'',
				'warning'
			);
//		alert("제목을 입력해주세요");
		return false;
	}
	if ($("#info_date_dep").val() == "" || $("#info_date_arr").val() == "" )  {
		Swal.fire(
			'기간을 입력해주세요',
			'',
			'warning'
		);
		
//		alert("여행 기간을 입력해주세요");
		return false;
	}
//	const oldDate = new Date($("#info_date_dep").val());
//	const newDate = new Date($("#info_date_arr").val());
//	if (oldDate > newDate) {
//		alert("여행 종료 일자가 여행 시작 일자보다 빠를 수 없습니다");
//		return false;
//	}
	if ($("#info_date_dep").val() > $("#info_date_arr").val()) {
		Swal.fire(
				'다른 일자를 선택해 주세요',
				'여행 종료 일자가 시작 일자보다 빠를 수 없습니다',
				'warning'
			);
//		alert("여행 종료 일자가 여행 시작 일자보다 빠를 수 없습니다");
		return false;
	}
	
	if ($("#prps").val() == "") {
		Swal.fire(
				'목적을 입력해주세요',
				'',
				'warning'
			);
//		alert("목적을 입력해주세요");
		return false;
	}
	if ($("#age").val() == "") {
		Swal.fire(
				'나이를 입력해주세요',
				'',
				'warning'
			);
//		alert("여행자의 나이를 입력해주세요");
		return false;
	}
	if ($("#head").val() == "") {
		Swal.fire(
				'인원을 입력해주세요',
				'',
				'warning'
			);
//		alert("여행인원을 입력해주세요");
		return false;
	}
	if ($("#info_txt").val() == "") {
		Swal.fire(
				'내용을 입력해주세요',
				'',
				'warning'
			);
//		alert("내용을 입력해주세요");
		return false;
	}
	for (var i = 0; i < max_day; i++) {
		if ($('.tourplan_con.d'+(i+1)).find(".tour_add_here").children().length==0) {
			Swal.fire(
					'빈 일정이 없도록 작성해주세요',
					'',
					'warning'
				);
//			alert("여행일정이 없는 날이 없도록 작성해주세요")
			return false;
		}
	}
	
//	Swal.fire({
//		title: '일정을 등록하시겠습니까?',
//		text: '',
//		icon: 'question',
//		
//		showCancelButton: true,			// cancel버튼 보이기. 기본은 원래 없음
//		confirmButtonColor: '#3085d6',	// confrim 버튼 색깔 지정
//		cancelButtonColor: '#d33',		// cancel 버튼 색깔 지정
//		confirmButtonText: '등록',		// confirm 버튼 텍스트 지정
//		cancelButtonText: '취소',		// cancel 버튼 텍스트 지정
//		
//		reverseButtons: true			// 버튼 순서 거꾸로
//		
//	}).then((result) => {
//		if (result.isConfirmed) {	// confirm 버튼 클릭시
//			return true;
//	    } else if (result.isDismissed) { // cancel 버튼 클릭시
//	    	return false;
//	    }		
//	})
	if ( confirm("이대로 작성을 완료하시겠습니까?")==0 ) {
		return false;
	}
		return true;
	
}

// 오늘 이전 날짜 선택 불가
var now_utc = Date.now() // 지금 날짜를 밀리초로
// getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
var timeOff = new Date().getTimezoneOffset()*60000; // 분단위를 밀리초로 변환
// new Date(now_utc-timeOff).toISOString()은 '2022-05-11T18:09:38.134Z'를 반환
var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
document.getElementById("info_date_dep").setAttribute("min", today);
document.getElementById("info_date_arr").setAttribute("min", today);




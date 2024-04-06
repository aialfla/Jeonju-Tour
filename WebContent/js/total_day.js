//	const date = new Date();
//	function dateDiff(start_date, end_date) {
//	    let dateDiff =
//	    (new Date(end_date).getTime() - new Date(start_date).getTime())
//	    / (1000 * 60 * 60 * 24);
//	    return dateDiff;
//	}



	
	function TotalDay() {
		var dep = $("#info_date_dep").val();
		var arr = $("#info_date_arr").val();
		var res = dateDiff(dep,arr);
		if ($("#info_date_dep").val() > $("#info_date_arr").val() && dep!="" && arr!="") {
			Swal.fire(
					'다른 일자를 선택해 주세요',
					'여행 종료 일자가 시작 일자보다 빠를 수 없습니다',
					'warning'
				);
//			alert("여행 종료 일자가 여행 시작 일자보다 빠를 수 없습니다");
			return;
		}
		if (Number.isInteger(res) == true) {
			var html = 
				"<li class='swiper-slide noplan on'>"+
				"	<div class='tourplan_day d1'>"+
				"		<button type= button class='btn_day' onclick='chooseDay(this);'><strong>DAY <span>1</span></strong></button>"+
				"	</div>"+
				"	<div class='tourplan_con d1 selected'>"+
				"		<ul class='tour_add_here on'>"+
				"		</ul>"+
				"	</div>"+
				"</li>";
			if(res == 0) {
				html += 
				"<li class='swiper-slide'>"+
				"	<div class='tourplan_day'>"+
				"		<strong> <span></span></strong>"+
				"	</div>"+
				"	<div class='tourplan_con'>"+
				"		<ul>"+
				"		</ul>"+
				"	</div>"+
				"</li>";
			}
			for (var i=1; i <= res; i++) {
				html += 
				"<li class='swiper-slide noplan'>"+
				"	<div class='tourplan_day d"+(i+1)+"'>"+
				"		<button type= button class='btn_day' onclick='chooseDay(this);'><strong>DAY <span>"+(i+1)+"</span></strong></button>"+
				"	</div>"+
				"	<div class='tourplan_con d"+(i+1)+"'>"+
				"		<ul class='tour_add_here'>"+
				"		</ul>"+
				"	</div>"+
				"</li>";
			}
			$("#add_day_here").children().remove();
			$("#add_day_here").append(html);
			
		}
	}
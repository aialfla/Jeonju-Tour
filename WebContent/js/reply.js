$("#rwrite").focus(function () {
	if ($("#uNO").val() == 'null') {
		$("#rwrite").blur();
		Swal.fire(
			'로그인 후 이용해 주세요',
			'',
			'warning'
		);
//		alert("로그인 후 이용해주세요");
		return false;
	}
})

function replyCheck() {
	if ($("#uNO").val() == 'null') {
		Swal.fire(
				'로그인 후 이용해 주세요',
				'',
				'warning'
			);
//		alert("로그인 후 사용해 주세요");
		return false;
	}
	if ($("#rwrite").val()=="") {
		Swal.fire(
				'내용을 입력해 주세요',
				'',
				'warning'
			);
//		alert("내용을 입력하세요");
		$("#rwrite").focus();
		return false;
	}
	Swal.fire({
	title: '리뷰를 등록하시겠습니까?',
	text: '',
	icon: 'question',
	
	showCancelButton: true,			// cancel버튼 보이기. 기본은 원래 없음
	confirmButtonColor: '#3085d6',	// confrim 버튼 색깔 지정
	cancelButtonColor: '#d33',		// cancel 버튼 색깔 지정
	confirmButtonText: '등록',		// confirm 버튼 텍스트 지정
	cancelButtonText: '취소',		// cancel 버튼 텍스트 지정
	
	reverseButtons: true			// 버튼 순서 거꾸로
	
	}).then((result) => {
	  if (result.value) {	// confirm 버튼 클릭시
		writeReply();
		return false;
	  }
	})
	
	
	
//	if(confirm("작성하시겠습니까?") == 1) {
//		writeReply();
//		return false;
//	}
	return false;
}

function writeReply() {
	var tbNO = $("#tbNO").val();
	var rNote = $("#rwrite").val();
		$.ajax({
		type 	: "post",
		url 	: "./replyOk.jsp",
		data	: {
			"tbNO" : tbNO, 
			"rNote" : rNote
		},
		dataType : "HTML",
		success : function ( data ) {
			$.ajax({
				url : "./tour_data.jsp",
				type : "post",
				datatype : "jsp",
				data : "tbNO="+tbNO,
				success : function(result) {
					$(".more_group").html(result);
				}
			});
			$("#rwrite").val("");
		}
	});
}
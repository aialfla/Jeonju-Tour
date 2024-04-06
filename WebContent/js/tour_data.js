$(function() { 
//	function deleteReply(rNO) {
//		alert("?");
//		if(confirm("리뷰를 삭제하시겠습니까?") == 1) {
//			$.ajax({
//				url : "./delete_reply_for_list_tour.jsp",
//				type : "post",
//				data : "rNO="+rNO,
//				datatype : "jsp",
//				success : function(result) {
//					result = result.trim();
//					if( result == "ERROR" )
//					{
//						alert("오류가 발생했습니다.");
//						return;
//					}
//					if( result == "DEL")
//					{
//						alert("리뷰가 삭제되었습니다");
//					}
//				}
//			});
//		}
//	}

	$(".btn_rdel").click(function () {
		Swal.fire({
			title: '리뷰를 삭제하시겠습니까?',
			text: '',
			icon: 'error',
			
			showCancelButton: true,			// cancel버튼 보이기. 기본은 원래 없음
			confirmButtonColor: '#3085d6',	// confrim 버튼 색깔 지정
			cancelButtonColor: '#d33',		// cancel 버튼 색깔 지정
			confirmButtonText: '삭제',		// confirm 버튼 텍스트 지정
			cancelButtonText: '취소'			// cancel 버튼 텍스트 지정
			
//			reverseButtons: true,			// 버튼 순서 거꾸로
			
			}).then((result) => {
			  if (result.value) {	// confirm 버튼 클릭시
				  var rNO = $(this).find("#rNO").val();
					$(this).parent().remove();
					$.ajax({
						url : "./delete_reply_for_list_tour.jsp",
						type : "post",
						data : "rNO="+rNO,
						datatype : "jsp",
						success : function(result) {
							result = result.trim();
							if( result == "ERROR" )
							{
								Swal.fire(
									'오류가 발생했습니다.',
									'',
									'warning'
						 		);
//								alert("오류가 발생했습니다.");
								return;
							}
							if( result == "DEL")
							{
								Swal.fire(
									'리뷰가 삭제되었습니다',
									'',
									'success'
						 		);
//								alert("리뷰가 삭제되었습니다");
							}
						}
					});
			  }
			})
			
//		if(confirm("리뷰를 삭제하시겠습니까?") == 1) {
//			var rNO = $(this).find("#rNO").val();
//			$(this).parent().remove();
//			$.ajax({
//				url : "./delete_reply_for_list_tour.jsp",
//				type : "post",
//				data : "rNO="+rNO,
//				datatype : "jsp",
//				success : function(result) {
//					result = result.trim();
//					if( result == "ERROR" )
//					{
//						alert("오류가 발생했습니다.");
//						return;
//					}
//					if( result == "DEL")
//					{
//						alert("리뷰가 삭제되었습니다");
//					}
//				}
//			});
//		}
	});
	
	
	$(".btn_put").click(function () {
		var obj = $(this);
		if(obj.hasClass("put")==false)
		{ 
			Swal.fire(
					'로그인 후 이용해 주세요',
					'',
					'warning'
				);
//			alert("로그인 후 이용해 주세요"); 
			return; 
		}
		var tbNO = $(this).find(".tbNO").val();
		$.ajax({
			url : "./keepOk.jsp",
			type : "post",
			datatype : "jsp",
			data : "tbNO="+tbNO,
			success : function(result)
			{
				result = result.trim();
				if( result == "ERROR" )
				{
					Swal.fire(
						'오류가 발생했습니다.',
						'',
						'warning'
			 		);
//					alert("오류가 발생했습니다.");
					return;
				}
				if( result == "DEL")
				{
					Swal.fire(
						'담기가 취소되었습니다',
						'',
						'success'
					);
//					alert("담기가 취소되었습니다");
				}
				if( result == "PUT" )
				{		
					Swal.fire(
						'관광지를 담았습니다',
						'',
						'success'
					);
//					alert("관광지를 담았습니다")
				}
				obj.toggleClass("on");
				$(".list_put").each(function(index,item){
					if($(item).find("input.tbNO").val() == tbNO)
					{
						$(item).toggleClass("on");
					}
				});
			}
		});
	});
});
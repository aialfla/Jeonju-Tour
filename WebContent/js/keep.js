$( document ).ready(function() {
	$(".list_put.keep").click(function () {
		var obj = $(this);
		if(obj.hasClass("put")==false)
		{
			Swal.fire(
					'로그인 후 이용해 주세요',
					'',
					'warning'
				);
//			alert("로그인 후 사용해주세요"); 
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
				$(".btn_put").each(function(index,item){
					if($(item).find("input.tbNO").val() == tbNO)
					{
						$(item).toggleClass("on");
					}
				});
			}
		});
	});
});
function mailcheck()
{
	// 이메일 중복검사

	// <회원가입> input mail 입력 값 여부 확인
	let mail = $("#mail");
	
	if(mail.val() == "" || mail.val() == null)
	{
		mail.focus();
 		Swal.fire(
 				'이메일 주소를 입력하세요',
 				'',
 				'warning'
 	 		);
//		alert("이메일 주소를 입력하세요");
		return;
	}
	
	// <회원가입> 중복 검사 시 작성한 mail 값 중복 여부 확인
	$.ajax({
		type : "get",
		url	 : "./mailcheck.jsp?mail=" + mail.val(),
		dataType: "HTML",
		success : function(data){
			data = data.trim();
			if( data == "ERROR" )
			{	
				alert("중복검사 오류가 발생했습니다");
				return data;
			}
			
			// <회원가입> mail 중복O ㅡ> mail로 커서 이동
			if( data == "DUPLICATE" )
			{	
				$("#mail").focus();
		 		Swal.fire(
		 				'중복된 이메일입니다',
		 				'',
		 				'warning'
		 	 		);
//				alert("중복된 이메일입니다");
				CheckState = "2";
			}
			
			// <회원가입> mail 중복X
			if( data == "NOT_DUPLICATE" )
			{
		 		Swal.fire(
		 				'사용 가능한 이메일입니다',
		 				'',
		 				'success'
		 	 		);
//				alert("사용 가능한 이메일입니다");
				CheckState = "1";
			}
		}
	});
}
let flag = false;
let isDuple = false;
let CheckState = "0";

function DoSubmit()
{	
	if (CheckState != 1) {
		mailcheck();
	} 
	
	//alert("call");
	if(flag == true)
	{
		//alert("flag가 true임");
		return;
	}
	
	// 1. 이메일 입력 확인
	let mail = $("#mail");
	if(mail.val() == "" || mail.val() == null)
	{
		mail.focus();
		Swal.fire(
			'이메일 주소를 입력하세요',
			'',
			'warning'
		);
////	alert("이메일 주소를 입력하세요");
		return false;
	}
	
	/*
	// 2. 중복 이메일 확인
	if (CheckState != "1") {
		if(mailcheck() == true)
		{	
			mail.focus();
	 		Swal.fire(
 				'중복된 이메일입니다',
 				'',
 				'warning'
 	 		);
//			alert("중복된 이메일입니다");
			return false;
		}
	}
	*/
	if( CheckState == "2")
	{
		Swal.fire(
				'중복된 이메일입니다',
				'',
				'warning'
			);
//		alert("중복된 이메일 주소입니다. 비밀번호 찾기를 수행하여 주세요.");
		return false;
	}	
	if( CheckState != "1")
	{
		Swal.fire(
				'이메일 중복 검사를 해주세요',
				'',
				'warning'
			);
//		alert("이메일 중복 검사를 수행하여 주세요.");
		return false;
	}
	
	// 3. 비밀번호	
	let pw = $("#pw");
	let pwc = $("#pwc");

	if(pw.val() == "")
	{
		pw.focus();
		Swal.fire(
			'비밀번호를 입력하세요',
			'',
			'warning'
		);
//		alert("비밀번호를 입력하세요");
		return false;
	}
	
	// 4. 비밀번호 확인
	if(pw.val() != pwc.val())
	{
		pw.val("");
		pwc.val("");
		pw.focus();
		Swal.fire(
			'비밀번호가 일치하지 않습니다',
			'',
			'warning'
		);
//		alert("비밀번호가 일치하지 않습니다");
		return false;
	}
	
	// 5. 이름
	let name = $("#name");
	if(name.val() == "")
	{
		name.focus();
		Swal.fire(
			'이름을 입력하세요',
			'',
			'warning'
		);
//		alert("이름을 입력하세요")
		return false;
	}

	flag = true;
}


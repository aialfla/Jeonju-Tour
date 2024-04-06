<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<%@ include file="./include/header.jsp"%>

<%
if (LoginVo == null) {
	%> 
	<script>
	Swal.fire(
		'로그인 후 이용해 주세요',
		'',
		'warning'
	);
//	alert("로그인 후 이용해주세요");
	document.location="login.jsp";
	</script>
	<%
	return;
}

String cbNO = request.getParameter("cbNO");
String cbTitle = request.getParameter("title");
String uNO = LoginVo.getuNO();
String date_from = request.getParameter("from");
String date_to = request.getParameter("to");
String cbNote = request.getParameter("info_txt");
String cbPublic = request.getParameter("public");
String head = request.getParameter("head");
String prps = request.getParameter("prps");
String age = request.getParameter("age");

String period = "";
String cbPeriod = "";

String total_day = request.getParameter("total_day");
String max_order = request.getParameter("max_order");

String[] sDay = request.getParameterValues("sday");
String[] sOrder = request.getParameterValues("sorder");
String[] tbNO = request.getParameterValues("tbNO");
// String[] sTrans = request.getParameterValues("sTrans");

ArrayList<Integer> order_list = new ArrayList<Integer>(); // 날짜별로 max order를 계산하는 기능
int count = 0;
for (int i = 0; i < Integer.parseInt(max_order); i++) {
	if (sOrder[i].equals("1")) {		// sorder가 1일때마다
		order_list.add(count);			// 리스트에 count를 넣어주고
		count = 0;						// count를 초기화 시켜줌
	}
	count++;							// 리스트에 넣은 이후로 sorder가 1이 나올때까지 계속 count에 1씩 더해주면서 루프를 돌림
	if (i == Integer.parseInt(max_order)-1) { // 마지막 반복때 남은 숫자도 리스트에 넣어주면서 완료시킴
		order_list.add(count);
	}
}

// 기간을 빼서 나온 값으로 몇박인지 체크해서 태그를 넣어줌
int forDay = Integer.parseInt(total_day);
if (forDay == 0) {
	period = "C1";
} else if (forDay == 1) {
	period = "C2";
} else if (forDay < 7) {
	period = "C3";
} else {
	period = "C4";
}

// 받은 기간 정보를 하나로 합쳐서 넣음
// 하루인 경우에는 물결표를 없애고 당일만 표시함
if (date_from != null && date_to != null && date_from.equals(date_to)) {
	cbPeriod = date_from ;
} else if (date_from != null && date_to != null && !date_from.equals(date_to)) {
	cbPeriod = date_from + " ~ " + date_to;
}

CbVo cv = new CbVo();
cv.setCbTitle(cbTitle);
cv.setCbPeriod(cbPeriod);
cv.setCbNote(cbNote);
cv.setCbPublic(cbPublic);
cv.setuNO(uNO);

CbDao cd = new CbDao();
cd.Modify(cbNO,cv);

TagDao tagd = new TagDao();
tagd.Insert(cbNO, age);
tagd.Insert(cbNO, head);
tagd.Insert(cbNO, period);
tagd.Insert(cbNO, prps);

SdDao sd = new SdDao();
System.out.println(cbNO);
System.out.println(sd.MaxDay(cbNO));

// writeOk에서 긁어온 새로운 modify
int maxDay = Integer.parseInt(total_day)+1;
int cycle = 0;
for (int i = 1; i <= maxDay; i++) {
	for (int j = 1; j <= order_list.get(i); j++) {
		SdVo sv = new SdVo();
		sv.setCbNO(cbNO);
		sv.setsDay(Integer.toString(i));
		sv.setsOrder(Integer.toString(j));
		sv.setTbNO(tbNO[cycle]);
		sv.setsTrans("도보");
		sv.setsDistance("0.0 KM");
		sv.setsTime("0시간 0분");
		sd.Insert(sv);
		cycle ++;
	}
}
cycle=0;

// 기존 modify_ok
// int maxDay = 1;
// for (int i = 1; i <= maxDay; i++) {
// 	for (int j = 1; j <= Integer.parseInt(max_order); j++) {
// 		SdVo sv = new SdVo();
// 		sv.setCbNO(cbNO);
// 		sv.setsDay(Integer.toString(i));
// 		sv.setsOrder(Integer.toString(j));
// 		sv.setTbNO(tbNO[j-1]);
// 		sv.setsTrans("");
// 		sv.setsDistance("0.0 KM");
// 		sv.setsTime("0시간 0분");
// 		sd.Insert(sv);
// 	}
// }
response.sendRedirect("view_course.jsp?cbNO="+ cbNO);
%>

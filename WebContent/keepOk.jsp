<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp" %>
<%
UsersVo LoginVo = (UsersVo)session.getAttribute("Login");
if(LoginVo==null)
{
	out.println("ERROR");
	return;
}

String uNO  = LoginVo.getuNO();
String tbNO = request.getParameter("tbNO");
// 유효성 검사
if (uNO == null || uNO.equals("") || tbNO == null || tbNO.equals("") )
{
	out.println("ERROR");
	return;
}
KeepDao kd = new KeepDao();
KeepVo kv = new KeepVo();
kv.setTbNO(tbNO);
kv.setuNO(uNO);

if (kd.Kept(uNO, tbNO)) { 	// 담기를 한적이 있으면
	int result = kd.Delete(kv); 			// 담기 내역 삭제
	if( result < 1 )
	{
		out.print("ERROR");
	}else
	{
		out.print("DEL");
	}
} else {				 	// 없으면
	int result = kd.Insert(kv); 			// 담기 수행
	if( result < 1)
	{
		out.print("ERROR");
	}else
	{
		out.print("PUT");
	}
}
%>
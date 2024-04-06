<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>
<% 
String title = request.getParameter("title");
String keyword = request.getParameter("keyword");


System.out.println("title:"+title);
System.out.println("keyword:"+keyword);
%>

<input type="hidden" name="para_title" value="<%= title %>">
<input type="hidden" name="para_keyword" value="<%= keyword %>">
<div id="map" class="map"></div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b35d98f318af7a17e68ae83c9b285d7&libraries=services"></script>
<script src="./js/kakaomap_list_tour.js"></script>

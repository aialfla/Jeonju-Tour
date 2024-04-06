<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./config/config.jsp"%>
<%@ include file="./include/head.jsp"%>

<!-- script -->
<%@ include file="./include/header.jsp"%>
	<!-- <script src="./js/common.js"></script> -->
	<%
	SdDao sd = new SdDao();
	
	// 게시판 정렬 <class> on off
	String on = "on";
	String off = "";
	%>
	<script>		
		<!-- 추천 -->
		$(document).ready(function(){
			// 버튼찾기
			var likeBTN = $(".btn_like");
			
			// 클릭 (이벤트 리스너)
			likeBTN.click( function(){
				
				// 로그인 유저 번호 받아오기
				var uNO = $(this).find("input.HDN_uNO").val();
				if(uNO == 0 || uNO == "")
				{
					Swal.fire(
						'로그인 후 이용해 주세요',
						'',
						'warning'
					);
// 					alert("로그인 후 이용가능합니다");
					return;
				}
				// 추천 일정 게시글 번호 받아오기
				var cbNO = $(this).find("input.HDN_cbNO").val();
				
				$(this).toggleClass('on');
				
				likeflip(uNO,cbNO, $(this).find(".likecount"));
			});
		});
		
	function likeflip(uNO,cbNO,obj)
	{
 		$.ajax({
			type 	: "get",
			url 	: "./likeOk.jsp?cbNO=" + cbNO + "&uNO=" + uNO,
			dataType : "HTML",
			success : function ( data ) {
				// 추천 후 변경된 (실시간) 추천 수 적용
				obj.text(data.trim());					
			}  
		});
	}
	</script>
<% 
	/* 전체 게시글 */
	// CbDao 객체 생성 
	CbDao cd = new CbDao(search);
	//추천일정 게시판 글 모두 불러오기
	cd.SelectAll();
	//불러온 게시글 리스트에 담기
	ArrayList<CbVo> clist = cd.GetListAll(); 
	// 추천 일정 게시판의 게시글 수
	int max = 0;
	if (clist != null) max = clist.size();
%>	
<%
	/* 추천 수 total */
	LikesDao ld = new LikesDao();
	String s_on = "";
%>

	<main id="main" class="sub_main">
		<div class="container">
			<h3 class="sub_tit">추천 일정</h3>
			<div class="sub_menu">
				<ul>
					<li class="<%= (search.getType().equals("D1")) ? on : off %>">
						<a href="./board_course.jsp?type=D1">
							<button value="D1">관광</button>
						</a>
					</li>
					<li class="<%= (search.getType().equals("D2")) ? on : off %>">
						<a href="./board_course.jsp?type=D2">
							<button value="D2">식도락</button>
						</a>
					</li>
					<li class="<%= (search.getType().equals("D3")) ? on : off %>">
						<a href="./board_course.jsp?type=D3">
							<button value="D3">체험</button>
						</a>
					</li>
					<li class="<%= (search.getType().equals("D4")) ? on : off %>">
						<a href="./board_course.jsp?type=D4">
							<button value="D4">휴식</button>
						</a>
					</li>
				</ul>
			</div>
			<section class="bcourse_warp">
				<div class="sort_list">
					<ul >
						<li class="<%= (search.getDivision().equals("new")) ? on : off %>">
							<a href="./board_course.jsp?type=<%= search.getType() %>&list=new">
								<button value="cbdate">최신순</button>
							</a>
						</li>
						<li class="<%= (search.getDivision().equals("like")) ? on : off %>">
							<a href="./board_course.jsp?type=<%= search.getType() %>&list=like">
								<button value="lCnt">추천순</button>
							</a>
						</li>
					</ul>
				</div>
				
				<ul class="bcourse_list">
					<%
					for (int i=0; i<max; i++)										// 추천 일정 게시판 글 수 만큼 반복
					{
						CbVo cv = clist.get(i);										// 리스트의 i번째 인덱스 객체를 꺼냄
						TagDao tagd = new TagDao(); 								// tagDao 객체 생성
						ArrayList<TagVo> taglist = tagd.SelectCbTag(cv.getCbNO()); 	// taglist 리스트에 태그를 담는다
						
					%>
					<li>
						<a href="view_course.jsp?cbNO=<%= cv.getCbNO() %>">
							<span class="list_img" style="background-image: url(./img/dataimg/<%=sd.GetTbPic(cv.getCbNO())%>);"></span>
							<strong><%= cv.getCbTitle() %></strong>
							<div class="list_tag">
								<ul>
									<%													// 추가되어있는 태그리스트 호출을 하면서
									if (taglist != null)
									{ 													// 태그리스트에 값이 있으면
										for (TagVo tagv : taglist) 						// 돌면서 전부 다 꺼낸다
										{
									%>	<li class="label_tag"><%= tagv.getTagWhat() %></li>
									<%	}
									}
									%>
								</ul>
							</div>
						</a>
						<%
						// 게시글 추천인 목록 안에 로그인 유저 값 uNO 존재여부 확인 후 class 'ON' 변경
						ArrayList<String> uNoList = cv.getUserLike();
						if(uNoList != null && LoginVo != null)
						{
							for(String item : uNoList)
							{
								if(item.equals(LoginVo.getuNO()))
								{
									s_on = "on";
 									break;
								}
							}
						}
						%>
						<div class="btn_like <%= s_on %>">
							<input type="hidden" class="HDN_uNO" name="uNO" value="${Login.getuNO()}">
							<input type="hidden" class="HDN_cbNO" name="cbNO" value="<%=cv.getCbNO()%>">
							<button type="button" class="rec_update">
								<i class="fi fi-rr-social-network"></i>
								<span class="txt">추천</span><span class="likecount"><%=cv.getlCnt() %></span>
							</button>
						</div>
					</li>
					<%
					s_on = "";
					}
					%>
				</ul>
			</section>
		</div>
	</main>
<%@ include file="./include/footer.jsp" %> 
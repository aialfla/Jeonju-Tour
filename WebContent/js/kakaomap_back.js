
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };   
 

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

var keyword = "전북 전주시 완산구 기린대로 99";
var title = "전주한옥마을";



//마커 이미지의 이미지 주소입니다
var imageSrc = "./img/icon_marker.png"; 

//마커 이미지의 이미지 크기 입니다
var imageSize = new kakao.maps.Size(24, 36); 

//마커 이미지를 생성합니다    
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 

var keyword = $("#main").find(".more_add").text();
// console.log(keyword);
var title = $("#main").find(".more_title").text();
// console.log(title);

//주소로 좌표를 검색합니다
function setPlace(keyword, title)
{
	if(keyword == null || keyword == "")
	{
		return;
	}
	geocoder.addressSearch(keyword, function(result, status) {
	
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords,
	            image : markerImage // 마커 이미지 
	        });
	
	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+title+'</div>'
	        });
	        infowindow.open(map, marker);
	
	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);  
	    } 
	});
}

setPlace(keyword, title);


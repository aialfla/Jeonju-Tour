var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };
 

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();
//console.log(geocoder);

var keywords = "";
keywords = $("input[name=para_keyword]").val();
var title = "";
title = $("input[name=para_title]").val();

if( keywords == null || keywords == "" )
{ keywords = "전북 전주시 완산구 기린대로 99"; }
if( title == null || title == "" )
{ title = "전주한옥마을"; }

//console.log(keywords);
//console.log(title);


//장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();
//키워드로 장소를 검색합니다
ps.keywordSearch(title, placesSearchCB); 
//키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        displayMarker(data[0]);
        bounds.extend(new kakao.maps.LatLng(data[0].y, data[0].x));
    }
}

//마커 이미지 
var imageSrc =
	"./img/icon_marker.png",    
    imageSize = new kakao.maps.Size(24, 36),
    imageOption = {
        offset: new kakao.maps.Point(17, 40)
    };
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)


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
	        kakao.maps.event.addListener(marker, 'click', function () {
	            var position = this.getPosition();
	            var url = 'https://map.kakao.com/link/map/' + place.id;
	            window.open(url, '_blank');
	        });
	        // 커스텀 오버레이
	        var content= '<div class="customoverlay" style="position: relative;left: -5px;bottom: 76px;border-radius: 6px;border: 1px solid #ccc;border-bottom: 2px solid #ddd;float: left;">' +
	        '  <div style="display: block;text-decoration: none;color: #666666;text-align: center;border-radius: 6px;font-size: 14px;font-weight: 600;overflow: hidden;">' +
	        '    <span class="title" style="display: block;text-align: center;background: #fff;padding: 8px 10px;font-size: 13px;font-weight: 600;">'
	        + title + '</span>' +
	        '  </div>' +
	        '</div>';
	        var customOverlay = new kakao.maps.CustomOverlay({
	            map: map,
	            position: new kakao.maps.LatLng(result[0].y, result[0].x),
	            content: content,
	            yAnchor: 0.11
	        });
	
	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);  
	    } 
	});
}

setPlace(keywords, title);


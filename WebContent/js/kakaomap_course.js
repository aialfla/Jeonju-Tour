var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
mapOption = { 
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};

// 클래스로 검색해서 타이틀 찾기
var title = $("#main").find(".more_title").text();
console.log(title);

// 여러 개의 타이틀 담을 배열 정의
var titles = [];

// 찾은 타이틀 배열에 추가
$(".more_title").each(function() {
    var title = $(this).text();
    titles.push(String(title));
});

// 각 배열 쉼표로 나누기
var titlesText = "";
var titlesArray = titlesText.split(', ');

// 배열을 inputData로 설정
var inputData = titlesArray;
var inputData = titles; 



var mapContainer = document.getElementById('map'),
    mapOption = {
        center: new kakao.maps.LatLng(37.54699, 127.09598),
        level: 3
    };
var map = new kakao.maps.Map(mapContainer, mapOption);  
var count = 0;
var ps = new kakao.maps.services.Places();
var bounds = new kakao.maps.LatLngBounds();
//var mapTypeControl = new kakao.maps.MapTypeControl();
//map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
var imageSrc =
	"./img/icon_marker.png",    
    imageSize = new kakao.maps.Size(24, 36),
    imageOption = {
        offset: new kakao.maps.Point(17, 40)
    };
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)
if (inputData != null) {
    kewwordSearch(inputData[count]);
}
function kewwordSearch(keword) {
    ps.keywordSearch(keword, placesSearchCB);
    count = count + 1;
}
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        displayMarker(data[0]);
        bounds.extend(new kakao.maps.LatLng(data[0].y, data[0].x));
        if (count < inputData.length) {
            kewwordSearch(inputData[count])
        } else if (count == inputData.length) {
            setBounds();
            applyStyle();
        }
    }
}
function displayMarker(place) {
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
        image: markerImage
    });
    kakao.maps.event.addListener(marker, 'click', function () {
        var position = this.getPosition();
        var url = 'https://map.kakao.com/link/map/' + place.id;
        window.open(url, '_blank');
    });
    var content =
        '<div class="customoverlay" style="position: relative;bottom: 76px;border-radius: 6px;border: 1px solid #ccc;border-bottom: 2px solid #ddd;float: left;">' +
        '  <a href="https://map.kakao.com/link/map/' + place.id + '"' +
        ' target="_blank" style="display: block;text-decoration: none;color: #666666;text-align: center;border-radius: 6px;font-size: 14px;font-weight: 600;overflow: hidden;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;">' +
        '    <span class="title" style="display: block;text-align: center;background: #fff;margin-right: 35px;padding: 8px 10px;font-size: 13px;font-weight: 600;">' +
        count + '. ' + place.place_name + '</span>' +
        '  </a>' +
        '</div>';
    var customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
        content: content,
        yAnchor: 0.11
    });
}
function setBounds() {
    map.setBounds(bounds, 90, 30, 10, 30);
}
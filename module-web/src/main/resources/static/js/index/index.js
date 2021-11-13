// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

var mapInputKeyword = '누리꿈스퀘어';

if (checkNullOrEmptyValue(keyword)) {
    mapInputKeyword = keyword;
}

// 키워드로 장소를 검색합니다
ps.keywordSearch(mapInputKeyword, placesSearchCB);

const imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
const imageSize = new kakao.maps.Size(24, 35);
const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

$.each(marks, function(markIdx, mark){

    console.log("mark", mark);

    let marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: new kakao.maps.LatLng(mark.x, mark.y), // 마커를 표시할 위치
        title :mark.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image : markerImage // 마커 이미지
    });

    let iwContent = '';
        iwContent += '<div class="display-flex-column" style="padding:5px; padding-left: 20px;">';

        $.each(mark.infoWindows, function(infoIdx, info) {
            iwContent += '<a href="/mark/view/' + info.markDetailId + '" class="text-decoration-none fw-normal" style="color: black;">' + info.title + '</a>';
        })
        iwContent += '</div>';


    let infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : true
    });

    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커 위에 인포윈도우를 표시합니다
        infowindow.open(map, marker);
    });
});



// 지도를 클릭했을때 클릭한 위치에 마커를 추가하도록 지도에 클릭이벤트를 등록합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
    // 클릭한 위치에 마커를 표시합니다
    addMarker(mouseEvent.latLng);
});


// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new kakao.maps.LatLngBounds();

        for (var i=0; i<data.length; i++) {
            // displayMarker(data[i]);
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
    }
}

// 지도에 마커를 표시하는 함수입니다
function displayMarker(place) {

    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x)
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
        infowindow.open(map, marker);
    });
}

// 마커를 생성하고 지도위에 표시하는 함수입니다
function addMarker(position) {

    console.log("position", position);

    // 마커를 생성합니다
    let marker = new kakao.maps.Marker({
        position: position
    });

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);

    let iwContent = '<div class="text-center" style="padding:5px; padding-left: 20px;">' +
        '<a href="/mark/new?x=' + position.Ma +'&y=' + position.La + '" class="text-decoration-none fw-normal" style="color: black;">마크 등록하기</a>' +
        '</div>';

// 인포윈도우를 생성합니다
    let infowindow = new kakao.maps.InfoWindow({
        position : position,
        content : iwContent,
        removable : true
    });

// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
    infowindow.open(map, marker);
    // 생성된 마커를 배열에 추가합니다
    // markers.push(marker);
}

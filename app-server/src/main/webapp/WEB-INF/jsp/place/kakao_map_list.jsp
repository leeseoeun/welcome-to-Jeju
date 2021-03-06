<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  
<style>
  body {
    background-color: #FDBB2D;
  }

  .theme-detail {
    float: left;
  }
  
  form {
    float: right;
  }
  
  input {
    border: none;
    border-radius: 0.25rem;
    background-color: white;
    padding: 0.375rem 0.75rem;
    margin-top: 1px;
    color: #212529;
    font-size: 1rem;
  }
  
  .place-search, .place {
    border: none;
    border-radius: 0.25rem;
    background-color: white;
    padding: 0.375rem 0.75rem;
    color: #212529;
    font-size: 1rem;
    text-align: center;
  }
  
  .place-list {
    position: absolute;
    float: left;
  }
  
  .place {
    border: 1px solid #212529;
    background-color: transparent;
  }
  
  #map {
    /* position: relative; */
    float: right;
    border-radius: 0.25rem;
  }
</style>
</head>

<body>
<br>

<div class="theme-detail">
  <p>
    <button class="btn btn-outline-dark" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    π ${theme.title} μμΈ λ³΄κΈ°
    </button>
  </p>
  <div class="collapse" id="collapseExample">
    <div class="card card-body" style="background-color:#FEFEFE; z-index:2">
      <input id='f-no' type='hidden' name='no' value='${theme.no}'><br>
      
      <label for='f-title'>μ λͺ©
        <a href='../likedtheme/add?themeNo=${theme.no}&userNo=${loginUser.no}'>π</a>
      </label>
      <input id='f-title' type='text' name='title' value='${theme.title}' readonly><br>
      
      <label for='f-nickname'>λλ€μ
        <a href='../likeduser/add?userNo=${theme.owner.no}&themeNo=${theme.no}'>π</a>
      </label>
      <input id='f-nickname' type='text' name='nickname' value='${theme.owner.nickname}' readonly><br>
      
      <label for='f-category'>μΉ΄νκ³ λ¦¬</label>
      <input id='f-category' type='text' name='category' value='${theme.category.name}' readonly><br>
    
      <button type="button" class="btn btn-outline-dark" onclick="history.back();">λͺ©λ‘</button>
    </div>
  </div>
</div>  <!-- .theme-detail -->

<form action="search">
  <input id="f-no" type="hidden" name="no" value="${theme.no}">
	<input id="f-place" type="text" name="keyword" class="place-search">
	<a href="search?no=${theme.no}&keyword=" class="btn btn-outline-dark">π¨ μ₯μ λ±λ‘νκΈ°</a>
</form>

<br><br><br>

<div class="place-list">
  <c:if test="${not empty placeList}">
  <div>
	  <c:forEach items="${placeList}" var="place">
	    <div class="place" style="z-index:1;">
	      <a href="detail?no=${theme.no}&id=${place.id}">
          ${place.place_name}<br>
          ${fn:split(place.address_name, ',')[0]}<br>
        </a>
      </div>
      <br>
    </c:forEach>
  </div>
  </c:if>
  
  <c:if test="${empty placeList}">
  </c:if>
</div>  <!-- .place-list -->

<div id="map" style="width:75%; height:480px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=10e27bbc088ef2c82002c09d3c881402&libraries=services"></script>

<script>
var mapContainer = document.getElementById('map'), // μ§λλ₯Ό νμν  div
mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // μ§λμ μ€μ¬ μ’ν
    level: 10 // μ§λμ νλ λ λ²¨
};  
   
// μ§λλ₯Ό μμ±ν©λλ€
var map = new kakao.maps.Map(mapContainer, mapOption);

// μ£Όμ-μ’ν λ³ν κ°μ²΄λ₯Ό μμ±ν©λλ€
var geocoder = new kakao.maps.services.Geocoder();

//μ₯μ λͺ©λ‘
var arr = new Array();
<c:forEach items="${placeList}" var="place">
    var placeMap = new Map();
    var originLocation = "${place.address_name}";
    var editLocation = originLocation.split(",")[0];
    
    var placeMap = new Map(); // MapμΌλ‘ μ΄κΈ°νμν¨ κ°μ²΄λ iterable κ°μ²΄μ΄λ€.
    placeMap.set("no", "${place.id}");
    placeMap.set("name", "${place.place_name}");
    placeMap.set("location", editLocation);
    
    arr.push(placeMap);
</c:forEach>

//μ’ν νκ· 
const average = array => array.reduce( ( p, c ) => p + c, 0 ) / array.length;
const result = average( arr );

for (let i = 0; i < arr.length; i++) {

// μ£Όμλ‘ μ’νλ₯Ό κ²μν©λλ€
geocoder.addressSearch(arr[i].get("location"), function(result, status) {

    // μ μμ μΌλ‘ κ²μμ΄ μλ£λμΌλ©΄
    if (status === kakao.maps.services.Status.OK) {
    	  
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        arr[i].set("latlng", coords);
        
    }
    
    // λ§μ»€λ₯Ό μμ±ν©λλ€
    var marker = new kakao.maps.Marker({
    	  map: map, // λ§μ»€λ₯Ό νμν  μ§λ
    	  position: arr[i].get("latlng") // λ§μ»€λ₯Ό νμν  μμΉ
    });
    
    // μΈν¬μλμ°λ‘ μ₯μμ λν μ€λͺμ νμν©λλ€
    var infowindow = new kakao.maps.InfoWindow({
    	  content: '<div style="width:150px;text-align:center;padding:6px 0;font-size:14px;">'+arr[i].get("name")+'</div>'
    });
    
    infowindow.open(map, marker);
    
    // μ§λμ μ€μ¬μ κ²°κ³Όκ°μΌλ‘ λ°μ μμΉλ‘ μ΄λμν΅λλ€
    map.setCenter(arr[result].get("latlng"));
    });
}
</script>

<!-- <div style="clear:left"></div> -->
<br><br><br><br>

</body>

</html>
<script setup>
import { ref, onMounted, watch } from "vue";
var map;
const positions = ref([]);
const markers = ref([]);
const props = defineProps({ selectedPlaces: Array, markPlace: Object });
const mmarkPlace = ref({});
const mmarker = ref([]);
const ooverlay = ref([]);

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);
  console.log("아악", props.markPlace);
};

watch(
  () => props.markPlace.value,
  () => {
    console.log("변경감지");
    if (window.kakao && window.kakao.maps) {
      mmarkPlace.value = {};
      mmarkPlace.latlng = new kakao.maps.LatLng(
        props.markPlace.latitude,
        props.markPlace.longitude
      );
      (mmarkPlace.title = props.markPlace.title),
        (mmarkPlace.firstImage = props.markPlace.firstImage);

      const tmpMarker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: mmarkPlace.latlng, // 마커를 표시할 위치
        clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
        // image: markerImage, // 마커의 이미지
      });

      // content HTMLElement 생성
      var content = document.createElement("div");

      const vwrap = makeHtmlElement("div", { class: "wrap" });
      const vinfo = makeHtmlElement("div", { class: "info" });

      const vname = makeHtmlElement("div", { class: "name" }, { textContent: mmarkPlace.title });
      const vclose = makeHtmlElement("div", { class: "close" });

      const vbody = makeHtmlElement("div", { class: "body" });
      const vimg = makeHtmlElement("div", { class: "img" });
      const iimg = makeHtmlElement(
        "img",
        { src: mmarkPlace.firstImage },
        { width: "73" },
        { height: "70" }
      );

      const vdesc = makeHtmlElement("div", { class: "desc" });
      const vdiv = makeHtmlElement("div");
      const va = makeHtmlElement(
        "a",
        { href: "https://search.naver.com/search.naver?query=" + mmarkPlace.title },
        { target: "_blank" },
        { class: "link" },
        { innerText: "네이버 검색" }
      );
      // vtitle.append(vclose);

      vimg.append(iimg);
      vdiv.append(va);
      vdesc.append(vname);
      vdesc.append(vdiv);

      vbody.append(vimg, vclose, vdesc);

      vinfo.append(vbody);

      vwrap.append(vinfo);

      // 닫기 이벤트 추가
      vclose.onclick = function () {
        ooverlay.value.forEach((overlay) => overlay.setMap(null));
      };

      content.appendChild(vwrap);

      // customoverlay 생성, 이때 map을 선언하지 않으면 지도위에 올라가지 않습니다.
      var tmpOverlay = new daum.maps.CustomOverlay({
        position: mmarkPlace.latlng,
        map: map,
        content: content,
      });

      //기존 마커 제거
      if (mmarker.value.length > 0) {
        mmarker.value.forEach((marker) => marker.setMap(null));
        mmarker.value.splice(0);
      }
      //기존 오버레이 제거
      if (ooverlay.value.length > 0) {
        ooverlay.value.forEach((overlay) => overlay.setMap(null));
        ooverlay.value.splice(0);
      }

      //새로운 마커 추가
      mmarker.value.push(tmpMarker);
      //새로운 오버레이 추가
      ooverlay.value.push(tmpOverlay);
      loadMarker();
    }
  },
  { deep: true }
);

const makeHtmlElement = function (tagName, ...attr) {
  const element = document.createElement(tagName);
  for (let prop of attr) {
    const [key, value] = Object.entries(prop)[0];
    if (key == "textContent" || key == "innerText") {
      element.textContent = value;
    } else {
      element.setAttribute(key, value);
    }
  }
  return element;
};

const loadMarker = () => {
  if (mmarker.value.length > 0) {
    mmarker.value.forEach((marker) => {
      marker.setPosition(mmarkPlace.latlng);
      console.log("하이", marker);
      marker.setMap(map);
      map.panTo(mmarkPlace.latlng);
      ooverlay.value.forEach((overlay) => overlay.setMap(map));
    });
  }
};
</script>

<template>
  <div id="map"></div>
  <div></div>
</template>

<style>
#map {
  width: 100%;
  height: 700px;
}

.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  height: 112px;
  transform: translateX(-50%);
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: fit-content;
  height: 100px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 15px;
  font-weight: bold;
}
.info .name {
  /* padding: 14px 10px 0 0; */
  font-size: 15px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 11px;
  color: #888;
  width: 10px;
  height: 10px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
  background-size: contain;
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  height: 100%;
  position: relative;
  overflow: hidden;
}
.info .desc {
  padding: 14px 14px 0 0;
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 14%;
  left: 10px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
</style>

<script setup>
import { ref, onMounted, defineProps } from "vue";
import VKakaoMapSearch from "@/components/common/VKakaoMapSearch.vue";
import api from "axios";

const attrList = ref([]);
const sidoList = ref([
  { sidoCode: 1, sidoName: "서울" },
  { sidoCode: 2, sidoName: "인천" },
  { sidoCode: 3, sidoName: "대전" },
  { sidoCode: 4, sidoName: "대구" },
  { sidoCode: 5, sidoName: "광주" },
  { sidoCode: 6, sidoName: "부산" },
  { sidoCode: 7, sidoName: "울산" },
  { sidoCode: 8, sidoName: "세종특별자치시" },
  { sidoCode: 31, sidoName: "경기도" },
  { sidoCode: 32, sidoName: "강원도" },
  { sidoCode: 33, sidoName: "충청북도" },
  { sidoCode: 34, sidoName: "충청남도" },
  { sidoCode: 35, sidoName: "경상북도" },
  { sidoCode: 36, sidoName: "경상남도" },
  { sidoCode: 37, sidoName: "전라북도" },
  { sidoCode: 36, sidoName: "전라남도" },
  { sidoCode: 39, sidoName: "제주도" },
]);
const contentTypeList = ref([
  { contentTypeId: 12, contentTypeName: "관광지" },
  { contentTypeId: 14, contentTypeName: "문화시설" },
  { contentTypeId: 15, contentTypeName: "축제공연행사" },
  { contentTypeId: 25, contentTypeName: "여행코스" },
  { contentTypeId: 28, contentTypeName: "레포츠" },
  { contentTypeId: 32, contentTypeName: "숙박" },
  { contentTypeId: 38, contentTypeName: "쇼핑" },
  { contentTypeId: 39, contentTypeName: "음식점" },
]);

const gugunList = ref([]);

const sortType = ref(0);
const contentTypeId = ref(0);
const sidoCode = ref(0);
const gugunCode = ref(0);
const title = ref("");

const getGugun = async (sido) => {
  if (sido == 0) {
  } else {
    await api
      .get(`${import.meta.env.VITE_VUE_API_URL}/attraction/search/${sido}`)
      .then(({ data }) => {
        gugunList.value = data;
        console.log(gugunList.value);
      })
      .catch((e) => {
        console.log(e);
      });
  }
};

const changeSido = () => {
  const sidoSelect = document.querySelector(".search-sido");
  sidoCode.value = sidoSelect.options[sidoSelect.selectedIndex].value;
  console.log("시도코드는");
  console.log(sidoCode.value);
  getGugun(sidoCode.value);
};

const setGugun = () => {
  const gugunSelect = document.querySelector(".search-gugun");
  gugunCode.value = gugunSelect.options[gugunSelect.selectedIndex].value;
};

const setType = () => {
  const typeSelect = document.querySelector(".search-type");
  contentTypeId.value = typeSelect.options[typeSelect.selectedIndex].value;
};

const attrlist = async () => {
  await api
    .post(`${import.meta.env.VITE_VUE_API_URL}/attraction/list`, {
      sortType: sortType.value,
      contentTypeId: contentTypeId.value,
      sidoCode: sidoCode.value,
      gugunCode: gugunCode.value,
      title: title.value,
    })
    .then(({ data }) => {
      console.log(data);
      attrList.value = data;
    })
    .catch((e) => {
      console.log(e);
    });
};
</script>

<template>
  <div id="form-search" class="d-flex my-3">
    <!-- <input type="hidden" id="ccommand" value="ssearch" /> -->
    <select id="search-area" class="form-select me-2 search-sido" @change="changeSido()">
      <option value="0" selected>시도선택</option>
      <option v-for="sido in sidoList" :value="sido.sidoCode">
        {{ sido.sidoName }}
      </option>
    </select>
    <select id="search-sub-area" class="form-select me-2 search-gugun" @change="setGugun()">
      <option value="0" selected>구군선택</option>
      <option v-for="gugun in gugunList" :value="gugun.gugunCode">
        {{ gugun.gugunName }}
      </option>
    </select>
    <select
      id="search-content-id"
      name="search_content_id"
      class="form-select me-2 search-type"
      @change="setType()"
    >
      <option value="0" selected>관광지 유형</option>
      <option v-for="contentType in contentTypeList" :value="contentType.contentTypeId">
        {{ contentType.contentTypeName }}
      </option>
    </select>
    <input
      id="search-keyword"
      class="form-control me-2"
      type="search"
      placeholder="검색어"
      aria-label="검색어"
      v-model="title"
    />
    <input
      type="button"
      id="btn-search"
      @click="attrlist()"
      class="btn btn-outline-success"
      value="검색"
    />
  </div>
  <!-- kakao map start -->
  <!-- <div id="map" class="row col-12 my-3 rounded ms-1" style="height: 500px"></div> -->
  <!-- kakao map end -->
  <div class="row col-12 mt-2 ms-1 card-table"></div>
  <VKakaoMapSearch :attractions="attrList" style="width: 80%" />

  <table class="table" style="width: 50%; margin: auto">
    <thead>
      <tr>
        <th scope="col" style="width: 5%">#</th>
        <th scope="col" style="width: 40%">이름</th>
        <th scope="col" style="width: 30%">주소</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(attraction, index) in attrList" :key="index">
        <td>{{ index + 1 }}</td>
        <td style="width: 40%">{{ attraction.title }}</td>
        <td style="width: 30%">{{ attraction.addr1 }}</td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped></style>

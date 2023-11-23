<script setup>
import HotPlaceZone from "@/components/HotPlaceZone.vue";
import BoardTable from "@/components/BoardTable.vue";
import { ref, onMounted } from "vue";
import api from "axios";
import { useRouter } from "vue-router";
import { searchStore } from "@/stores/planListStore";
import { VueTyper } from 'vue-typer'

const sstore = searchStore();
const router = useRouter();
const hotTags = ref({});
const word = ref("");
const searchRef = ref(null);

const getHotTags = async () => {
  await api
    .get(`${import.meta.env.VITE_VUE_API_URL}/plan/hottag`)
    .then(({ data }) => {
      hotTags.value = data;
      console.log("hotTags: " + hotTags);
    })
    .catch((e) => {
      console.log(e);
    });
};

const search = () => {
  sstore.word = word;
  sstore.isSearch = true;
  router.push({ name: "planlist" });
};

const searchInput = () => {
  searchRef.value.focus();
};

const searchTag = (t) => {
  console.log(t);
  sstore.word = t;
  sstore.isSearch = true;
  router.push({ name: "planlist" });
};

onMounted(() => {
  getHotTags();
  sstore.isMy = false;
});
</script>

<template>
  <!DOCTYPE html>
  <html lang="en" data-bs-theme="auto">

  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
    <meta name="generator" content="Hugo 0.118.2" />
    <title>Carousel Template · Bootstrap v5.3</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/carousel/" />

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3" />

    <!-- <link href="/src/assets/bootstrap.min.css" rel="stylesheet" /> -->

    <link href="/src/assets/carousel.css" rel="stylesheet" />
  </head>

  <body>
    <main class="mt-4" style="display: flex; flex-direction: column; align-items: center; justify-content: center">
      <div id="carouselExampleCaptions" class="carousel slide mb-2" data-bs-ride="carousel" style="width: fit-content">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
            aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
            aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
            aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="../assets/image/fall.png" alt="..." style="height: 100%" />
            <!-- <div class="carousel-caption d-none d-md-block">
                        <h5>단풍으로 물든 에버랜드</h5>
                        <p>놀이기구 뿐 아니라 경치도 예쁜 에버랜드!</p>
                      </div> -->
            <div class="container">
              <div class="carousel-caption text-start">
                <h2 class="featurette-heading fw-normal lh-1">
                  자연을 즐기는
                  <br />
                  산청 힐링 여행 코스
                </h2>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <img src="../assets/image/city.png" alt="..." style="height: 100%" />

            <div class="container">
              <div class="carousel-caption">
                <!-- carousel-caption text-end -->
                <h1>반짝이는 야경명소</h1>
                <p>밤이 더 아름다운 대한민국!</p>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <img src="../assets/image/snow.png" alt="..." style="height: 100%" />
            <div class="container">
              <div class="carousel-caption">
                <h1>눈으로 뒤덮인 대한민국</h1>
              </div>
            </div>
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
          data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
          data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>

      <!-- 검색창 -->
<!-- 
      <vue-typer class="mt-5" :text='["지금 어디로 떠나고 싶으신가요?","지금 어디로 떠나고 싶으신가요?"]' :repeat='Infinity' :shuffle='true' initial-action='typing'
        :pre-type-delay='2000' :type-delay='70' :pre-erase-delay='2000' :erase-delay='70' erase-style='backspace'
        :erase-on-complete='false' caret-animation='blink'></vue-typer> -->





      <div class="mt-3 py-2">
        <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
        <div class="search-mode mb-2" @click="searchInput">
          <input type="text" class="form-control" placeholder="검색어를 입력하세요." ref="searchRef" @keyup.enter="search(word)"
            v-model="word" />

          <div class="icon">
            <div class="search">
              <span class="material-symbols-outlined">search</span>
            </div>
          </div>
        </div>

        <div class="hot-tag py-2 row" style="display: flex">
          <div class="col-md-2" style="float: left; font-size: 20px">
            <div class="col-md-12">
              <b class="w-btn" style="margin: 0px;">인기태그 : </b>
            </div>
          </div>
          <div class="col-md-2" style="float: left; font-size: 20px; color: #0000cd; width: 140px"
            v-for="(tag, index) in hotTags" :key="index">
            <div class="col-md-12">
              <button type="button" class="btn w-btn w-btn-tag" @click="searchTag(tag.tagName)" style="width: 130px;"> #
                {{
                  tag.tagName }}</button>
            </div>
            <br />
            <br />
          </div>
        </div>
        <hr />
      </div>

      <div class="container marketing">
        <div class="row">
          <hot-place-zone></hot-place-zone>
        </div>
      </div>
      <footer class="container">
        <p class="float-end"><a href="#">Back to top</a></p>
        <p>
          &copy; 2017–2023 Company, Inc. &middot;
          <a href="#">Privacy</a> &middot; <a href="#">Terms</a>
        </p>
      </footer>
    </main>
  </body>

  </html>
</template>

<style scoped>
.material-symbols-outlined {
  font-variation-settings: "FILL" 0, "wght" 400, "GRAD" 0, "opsz" 24;
  font-size: 27px;
}

.search-mode {
  position: relative;
  width: 950px;
  height: 70px;
  margin: 20px auto;
}

.hot-tag {
  position: relative;
  width: 1000px;
  margin: 5px auto;
  justify-content: flex-start;
  padding-right: 40px;
  padding-left: 40px;
}

input {
  /* border: 1px solid #f1f1f1; */
  border-radius: 50px;
  width: 100%;
  height: 100%;
  border: 2px solid;
  font-size: 21px;
  padding-left: 9%;
  color: grey;
}

.icon {
  display: flex;
  justify-content: space-between;
  position: absolute;
  top: 23px;
  right: 50px;
  left: 40px;
}

.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}

@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
}

.b-example-divider {
  width: 100%;
  height: 3rem;
  background-color: rgba(0, 0, 0, 0.1);
  border: solid rgba(0, 0, 0, 0.15);
  border-width: 1px 0;
  box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1), inset 0 0.125em 0.5em rgba(0, 0, 0, 0.15);
}

.b-example-vr {
  flex-shrink: 0;
  width: 1.5rem;
  height: 100vh;
}

.bi {
  vertical-align: -0.125em;
  fill: currentColor;
}

.nav-scroller {
  position: relative;
  z-index: 2;
  height: 2.75rem;
  overflow-y: hidden;
}

.nav-scroller .nav {
  display: flex;
  flex-wrap: nowrap;
  padding-bottom: 1rem;
  margin-top: -1px;
  overflow-x: auto;
  text-align: center;
  white-space: nowrap;
  -webkit-overflow-scrolling: touch;
}

.btn-bd-primary {
  --bd-violet-bg: #712cf9;
  --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

  --bs-btn-font-weight: 600;
  --bs-btn-color: var(--bs-white);
  --bs-btn-bg: var(--bd-violet-bg);
  --bs-btn-border-color: var(--bd-violet-bg);
  --bs-btn-hover-color: var(--bs-white);
  --bs-btn-hover-bg: #6528e0;
  --bs-btn-hover-border-color: #6528e0;
  --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
  --bs-btn-active-color: var(--bs-btn-hover-color);
  --bs-btn-active-bg: #5a23c8;
  --bs-btn-active-border-color: #5a23c8;
}

.bd-mode-toggle {
  z-index: 1500;
}

.bd-mode-toggle .dropdown-menu .active .bi {
  display: block !important;
}

.w-btn-tag {
  background-color: white;
  border-width: 2px;
  border-color: #f4bd19;
  color: #f4bd19;
}

.w-btn {
  position: relative;
  display: inline-block;
  padding: 7px 22px;
  margin: 7px;
  border-radius: 25px;
  /* box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2); */
  text-decoration: none;
  font-weight: 600;
  /* transition: 0.25s; */
}
</style>

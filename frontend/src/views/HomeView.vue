<script setup>
import HotPlaceZone from "@/components/HotPlaceZone.vue";
import { ref, onMounted } from "vue";
import api from "axios";
import { useRouter } from "vue-router";
import { searchStore } from "@/stores/planListStore";

const arr = ref([
  "지금 어디로 떠나고 싶으신가요?",
  "어디로 갈까요?",
  "언제 떠날까요?",
  "어디로 떠날까요?",
]);
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

      <link href="/src/assets/carousel.css" rel="stylesheet" />
    </head>

    <body>
      <main
        style="display: flex; flex-direction: column; align-items: center; justify-content: center"
      >
        <div
          id="carouselExampleCaptions"
          class="carousel slide mb-2"
          data-bs-ride="carousel"
          style="width: fit-content"
        >
          <div class="carousel-indicators">
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to="0"
              class="active"
              aria-current="true"
              aria-label="Slide 1"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to="1"
              aria-label="Slide 2"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to="2"
              aria-label="Slide 3"
            ></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img src="../assets/image/fall.png" alt="..." style="height: 100%" />
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
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="next"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>

        <h1 class="mt-5">
          <VueWriter :array="arr" class="is-typed cursor" :caret="underscore" />
        </h1>

        <div class="mt-3 py-2">
          <link
            href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined"
            rel="stylesheet"
          />
          <div class="search-mode mb-2" @click="searchInput">
            <input
              type="text"
              class="form-control"
              placeholder="검색어를 입력하세요."
              ref="searchRef"
              @keyup.enter="search(word)"
              v-model="word"
            />

            <div class="icon">
              <div class="search">
                <span class="material-symbols-outlined">search</span>
              </div>
            </div>
          </div>

          <div class="hot-tag py-2 row" style="display: flex">
            <div class="col-md-2" style="float: left; font-size: 20px">
              <div class="col-md-12">
                <b class="w-btn" style="margin: 0px">인기태그 : </b>
              </div>
            </div>
            <div
              class="col-md-2"
              style="float: left; font-size: 20px; color: #0000cd; width: 140px"
              v-for="(tag, index) in hotTags"
              :key="index"
            >
              <div class="col-md-12">
                <button
                  type="button"
                  class="btn w-btn w-btn-tag"
                  @click="searchTag(tag.tagName)"
                  style="width: 130px"
                >
                  # {{ tag.tagName }}
                </button>
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

@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
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
  text-decoration: none;
  font-weight: 600;
}

.is-typed {
  font-family: "Monaco";
}
.is-typed span.typed {
  colour: black;
}
.is-typed span.cursor {
  display: inline-block;
  width: 3px;
  background-color: black;
  animation: blink 1s infinite;
}
.is-typed span.underscore {
  display: inline-flex;
  width: 10px;
  height: 1px;
  align-items: flex-end;
  background-color: black;
  animation: blink 1s infinite;
}
.is-typed span.cursor.typing {
  animation: none;
}
@keyframes blink {
  49% {
    background-color: black;
  }
  50% {
    background-color: transparent;
  }
  99% {
    background-color: transparent;
  }
}
</style>

<script setup>
import HotPlaceZone from "@/components/HotPlaceZone.vue";
import BoardTable from "@/components/BoardTable.vue";
import { ref, onMounted } from "vue";
import api from "axios";
import { useRouter } from "vue-router";
import { searchStore } from "@/stores/planListStore";

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
      <main class="mt-4">
        <div id="carouselExampleCaptions" class="carousel slide mb-2" data-bs-ride="carousel">
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
              <div class="carousel-caption d-none d-md-block">
                <h5>단풍으로 물든 에버랜드</h5>
                <p>놀이기구 뿐 아니라 경치도 예쁜 에버랜드!</p>
              </div>
            </div>
            <div class="carousel-item">
              <img src="../assets/image/city.png" alt="..." style="height: 100%" />
              <div class="carousel-caption d-none d-md-block">
                <h5>밤이 더 아름다운 대한민국!</h5>
                <p>반짝이는 야경명소</p>
              </div>
            </div>
            <div class="carousel-item">
              <img src="../assets/image/snow.png" alt="..." style="height: 100%" />
              <div class="carousel-caption d-none d-md-block">
                <h5>눈 내린 경복궁</h5>
                <p></p>
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

        <!-- 검색창 -->
        <div class="mt-5 py-2">
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
              <b>인기 태그 : </b>
            </div>
            <div
              class="col-md-3"
              style="float: left; font-size: 20px; color: #0000cd; width: 120px"
              v-for="(tag, index) in hotTags"
              :key="index"
            >
              <p style="width: 100px" @click="searchTag(tag.tagName)"># {{ tag.tagName }}</p>
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

          <hr class="featurette-divider" />

          <div class="row featurette">
            <div class="col-md-7">
              <h2 class="featurette-heading fw-normal lh-1">
                First featurette heading.
                <span class="text-body-secondary">It’ll blow your mind.</span>
              </h2>
              <p class="lead">
                Some great placeholder content for the first featurette here. Imagine some exciting
                prose here.
              </p>
            </div>
            <div class="col-md-5">
              <svg
                class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto"
                width="500"
                height="500"
                xmlns="http://www.w3.org/2000/svg"
                role="img"
                aria-label="Placeholder: 500x500"
                preserveAspectRatio="xMidYMid slice"
                focusable="false"
              >
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="var(--bs-secondary-bg)" />
                <text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text>
              </svg>
            </div>
          </div>

          <hr class="featurette-divider" />

          <div class="row featurette">
            <div class="col-md-7 order-md-2">
              <h2 class="featurette-heading fw-normal lh-1">
                Oh yeah, it’s that good.
                <span class="text-body-secondary">See for yourself.</span>
              </h2>
              <p class="lead">
                Another featurette? Of course. More placeholder content here to give you an idea of
                how this layout would work with some actual real-world content in place.
              </p>
            </div>
            <div class="col-md-5 order-md-1">
              <svg
                class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto"
                width="500"
                height="500"
                xmlns="http://www.w3.org/2000/svg"
                role="img"
                aria-label="Placeholder: 500x500"
                preserveAspectRatio="xMidYMid slice"
                focusable="false"
              >
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="var(--bs-secondary-bg)" />
                <text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text>
              </svg>
            </div>
          </div>

          <hr class="featurette-divider" />

          <div class="row featurette">
            <div class="col-md-7">
              <h2 class="featurette-heading fw-normal lh-1">
                And lastly, this one.
                <span class="text-body-secondary">Checkmate.</span>
              </h2>
              <p class="lead">
                And yes, this is the last block of representative placeholder content. Again, not
                really intended to be actually read, simply here to give you a better view of what
                this would look like with some actual content. Your content.
              </p>
            </div>
            <div class="col-md-5">
              <svg
                class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto"
                width="500"
                height="500"
                xmlns="http://www.w3.org/2000/svg"
                role="img"
                aria-label="Placeholder: 500x500"
                preserveAspectRatio="xMidYMid slice"
                focusable="false"
              >
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="var(--bs-secondary-bg)" />
                <text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text>
              </svg>
            </div>
          </div>

          <hr class="featurette-divider" />
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
</style>

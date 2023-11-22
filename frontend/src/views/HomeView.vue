<script setup></script>

<template>
  <!DOCTYPE html>
  <html lang="en" data-bs-theme="auto">
    <head>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <meta name="description" content="" />
      <meta
        name="author"
        content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
      />
      <meta name="generator" content="Hugo 0.118.2" />
      <title>Carousel Template · Bootstrap v5.3</title>

      <link
        rel="canonical"
        href="https://getbootstrap.com/docs/5.3/examples/carousel/"
      />

      <link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/@docsearch/css@3"
      />

      <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet" />

      <!-- Custom styles for this template -->
      <link href="carousel.css" rel="stylesheet" />
    </head>
    <body>
      <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
        <symbol id="check2" viewBox="0 0 16 16">
          <path
            d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"
          />
        </symbol>
        <symbol id="circle-half" viewBox="0 0 16 16">
          <path d="M8 15A7 7 0 1 0 8 1v14zm0 1A8 8 0 1 1 8 0a8 8 0 0 1 0 16z" />
        </symbol>
        <symbol id="moon-stars-fill" viewBox="0 0 16 16">
          <path
            d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z"
          />
          <path
            d="M10.794 3.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387a1.734 1.734 0 0 0-1.097 1.097l-.387 1.162a.217.217 0 0 1-.412 0l-.387-1.162A1.734 1.734 0 0 0 9.31 6.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387a1.734 1.734 0 0 0 1.097-1.097l.387-1.162zM13.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.156 1.156 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.156 1.156 0 0 0-.732-.732l-.774-.258a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732L13.863.1z"
          />
        </symbol>
        <symbol id="sun-fill" viewBox="0 0 16 16">
          <path
            d="M8 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z"
          />
        </symbol>
      </svg>

      <div
        class="dropdown position-fixed bottom-0 end-0 mb-3 me-3 bd-mode-toggle"
      >
        <button
          class="btn btn-bd-primary py-2 dropdown-toggle d-flex align-items-center"
          id="bd-theme"
          type="button"
          aria-expanded="false"
          data-bs-toggle="dropdown"
          aria-label="Toggle theme (auto)"
        >
          <svg class="bi my-1 theme-icon-active" width="1em" height="1em">
            <use href="#circle-half"></use>
          </svg>
          <span class="visually-hidden" id="bd-theme-text">Toggle theme</span>
        </button>
        <ul
          class="dropdown-menu dropdown-menu-end shadow"
          aria-labelledby="bd-theme-text"
        >
          <li>
            <button
              type="button"
              class="dropdown-item d-flex align-items-center"
              data-bs-theme-value="light"
              aria-pressed="false"
            >
              <svg
                class="bi me-2 opacity-50 theme-icon"
                width="1em"
                height="1em"
              >
                <use href="#sun-fill"></use>
              </svg>
              Light
              <svg class="bi ms-auto d-none" width="1em" height="1em">
                <use href="#check2"></use>
              </svg>
            </button>
          </li>
          <li>
            <button
              type="button"
              class="dropdown-item d-flex align-items-center"
              data-bs-theme-value="dark"
              aria-pressed="false"
            >
              <svg
                class="bi me-2 opacity-50 theme-icon"
                width="1em"
                height="1em"
              >
                <use href="#moon-stars-fill"></use>
              </svg>
              Dark
              <svg class="bi ms-auto d-none" width="1em" height="1em">
                <use href="#check2"></use>
              </svg>
            </button>
          </li>
          <li>
            <button
              type="button"
              class="dropdown-item d-flex align-items-center active"
              data-bs-theme-value="auto"
              aria-pressed="true"
            >
              <svg
                class="bi me-2 opacity-50 theme-icon"
                width="1em"
                height="1em"
              >
                <use href="#circle-half"></use>
              </svg>
              Auto
              <svg class="bi ms-auto d-none" width="1em" height="1em">
                <use href="#check2"></use>
              </svg>
            </button>
          </li>
        </ul>
      </div>

      <header data-bs-theme="dark">
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
          <div class="container-fluid">
            <a class="navbar-brand" href="#">Carousel</a>
            <button
              class="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarCollapse"
              aria-controls="navbarCollapse"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
              <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="#"
                    >Home</a
                  >
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                </li>
              </ul>
              <form class="d-flex" role="search">
                <input
                  class="form-control me-2"
                  type="search"
                  placeholder="Search"
                  aria-label="Search"
                />
                <button class="btn btn-outline-success" type="submit">
                  Search
                </button>
              </form>
            </div>
          </div>
        </nav>
      </header>

      <main>
        <div
          id="myCarousel"
          class="carousel slide mb-6"
          data-bs-ride="carousel"
        >
          <div class="carousel-indicators">
            <button
              type="button"
              data-bs-target="#myCarousel"
              data-bs-slide-to="0"
              class="active"
              aria-current="true"
              aria-label="Slide 1"
            ></button>
            <button
              type="button"
              data-bs-target="#myCarousel"
              data-bs-slide-to="1"
              aria-label="Slide 2"
            ></button>
            <button
              type="button"
              data-bs-target="#myCarousel"
              data-bs-slide-to="2"
              aria-label="Slide 3"
            ></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <svg
                class="bd-placeholder-img"
                width="100%"
                height="100%"
                xmlns="http://www.w3.org/2000/svg"
                aria-hidden="true"
                preserveAspectRatio="xMidYMid slice"
                focusable="false"
              >
                <rect
                  width="100%"
                  height="100%"
                  fill="var(--bs-secondary-color)"
                />
              </svg>
              <div class="container">
                <div class="carousel-caption text-start">
                  <h1>Example headline.</h1>
                  <p class="opacity-75">
                    Some representative placeholder content for the first slide
                    of the carousel.
                  </p>
                  <p>
                    <a class="btn btn-lg btn-primary" href="#">Sign up today</a>
                  </p>
                </div>
              </div>
            </div>
            <div class="carousel-item">
              <svg
                class="bd-placeholder-img"
                width="100%"
                height="100%"
                xmlns="http://www.w3.org/2000/svg"
                aria-hidden="true"
                preserveAspectRatio="xMidYMid slice"
                focusable="false"
              >
                <rect
                  width="100%"
                  height="100%"
                  fill="var(--bs-secondary-color)"
                />
              </svg>
              <div class="container">
                <div class="carousel-caption">
                  <h1>Another example headline.</h1>
                  <p>
                    Some representative placeholder content for the second slide
                    of the carousel.
                  </p>
                  <p>
                    <a class="btn btn-lg btn-primary" href="#">Learn more</a>
                  </p>
                </div>
              </div>
            </div>
            <div class="carousel-item">
              <svg
                class="bd-placeholder-img"
                width="100%"
                height="100%"
                xmlns="http://www.w3.org/2000/svg"
                aria-hidden="true"
                preserveAspectRatio="xMidYMid slice"
                focusable="false"
              >
                <rect
                  width="100%"
                  height="100%"
                  fill="var(--bs-secondary-color)"
                />
              </svg>
              <div class="container">
                <div class="carousel-caption text-end">
                  <h1>One more for good measure.</h1>
                  <p>
                    Some representative placeholder content for the third slide
                    of this carousel.
                  </p>
                  <p>
                    <a class="btn btn-lg btn-primary" href="#"
                      >Browse gallery</a
                    >
                  </p>
                </div>
              </div>
            </div>
          </div>
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#myCarousel"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#myCarousel"
            data-bs-slide="next"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>

        <!-- Marketing messaging and featurettes
  ================================================== -->
        <!-- Wrap the rest of the page in another container to center all the content. -->

        <div class="container marketing">
          <!-- Three columns of text below the carousel -->
          <div class="row">
            <div class="col-lg-4">
              <svg
                class="bd-placeholder-img rounded-circle"
                width="140"
                height="140"
                xmlns="http://www.w3.org/2000/svg"
                role="img"
                aria-label="Placeholder"
                preserveAspectRatio="xMidYMid slice"
                focusable="false"
              >
                <title>Placeholder</title>
                <rect
                  width="100%"
                  height="100%"
                  fill="var(--bs-secondary-color)"
                />
              </svg>
              <h2 class="fw-normal">Heading</h2>
              <p>
                Some representative placeholder content for the three columns of
                text below the carousel. This is the first column.
              </p>
              <p>
                <a class="btn btn-secondary" href="#">View details &raquo;</a>
              </p>
            </div>
            <!-- /.col-lg-4 -->
            <div class="col-lg-4">
              <svg
                class="bd-placeholder-img rounded-circle"
                width="140"
                height="140"
                xmlns="http://www.w3.org/2000/svg"
                role="img"
                aria-label="Placeholder"
                preserveAspectRatio="xMidYMid slice"
                focusable="false"
              >
                <title>Placeholder</title>
                <rect
                  width="100%"
                  height="100%"
                  fill="var(--bs-secondary-color)"
                />
              </svg>
              <h2 class="fw-normal">Heading</h2>
              <p>
                Another exciting bit of representative placeholder content. This
                time, we've moved on to the second column.
              </p>
              <p>
                <a class="btn btn-secondary" href="#">View details &raquo;</a>
              </p>
            </div>
            <!-- /.col-lg-4 -->
            <div class="col-lg-4">
              <svg
                class="bd-placeholder-img rounded-circle"
                width="140"
                height="140"
                xmlns="http://www.w3.org/2000/svg"
                role="img"
                aria-label="Placeholder"
                preserveAspectRatio="xMidYMid slice"
                focusable="false"
              >
                <title>Placeholder</title>
                <rect
                  width="100%"
                  height="100%"
                  fill="var(--bs-secondary-color)"
                />
              </svg>
              <h2 class="fw-normal">Heading</h2>
              <p>
                And lastly this, the third column of representative placeholder
                content.
              </p>
              <p>
                <a class="btn btn-secondary" href="#">View details &raquo;</a>
              </p>
            </div>
            <!-- /.col-lg-4 -->
          </div>
          <!-- /.row -->

          <!-- START THE FEATURETTES -->

          <hr class="featurette-divider" />

          <div class="row featurette">
            <div class="col-md-7">
              <h2 class="featurette-heading fw-normal lh-1">
                First featurette heading.
                <span class="text-body-secondary">It’ll blow your mind.</span>
              </h2>
              <p class="lead">
                Some great placeholder content for the first featurette here.
                Imagine some exciting prose here.
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
                <rect
                  width="100%"
                  height="100%"
                  fill="var(--bs-secondary-bg)"
                />
                <text
                  x="50%"
                  y="50%"
                  fill="var(--bs-secondary-color)"
                  dy=".3em"
                >
                  500x500
                </text>
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
                Another featurette? Of course. More placeholder content here to
                give you an idea of how this layout would work with some actual
                real-world content in place.
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
                <rect
                  width="100%"
                  height="100%"
                  fill="var(--bs-secondary-bg)"
                />
                <text
                  x="50%"
                  y="50%"
                  fill="var(--bs-secondary-color)"
                  dy=".3em"
                >
                  500x500
                </text>
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
                And yes, this is the last block of representative placeholder
                content. Again, not really intended to be actually read, simply
                here to give you a better view of what this would look like with
                some actual content. Your content.
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
                <rect
                  width="100%"
                  height="100%"
                  fill="var(--bs-secondary-bg)"
                />
                <text
                  x="50%"
                  y="50%"
                  fill="var(--bs-secondary-color)"
                  dy=".3em"
                >
                  500x500
                </text>
              </svg>
            </div>
          </div>

          <hr class="featurette-divider" />

          <!-- /END THE FEATURETTES -->
        </div>
        <!-- /.container -->

        <!-- FOOTER -->
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
  box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1),
    inset 0 0.125em 0.5em rgba(0, 0, 0, 0.15);
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

<!-- <script setup>
import BoardTable from "@/components/BoardTable.vue";
import HotPlaceZone from "@/components/HotPlaceZone.vue";
import { ref, onMounted } from "vue";
import api from "axios";
import { useRouter } from "vue-router";
import { searchStore } from "@/stores/planListStore";
const sstore = searchStore();
const router = useRouter();
const hotTags = ref({});
const word = ref("");
const searchRef = ref(null);

const search = () => {
  sstore.word = word;
  sstore.isSearch = true;
  router.push({ name: "planlist" });
};

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

  <div class="hot-tag mb-4 row" style="display: flex">
    <div class="col-md-2" style="float: left; font-size: 16px">
      <b>인기 태그 :</b>
    </div>
    <div
      class="col-md-2"
      style="float: left; font-size: 16px; color: #0000cd"
      v-for="(tag, index) in hotTags"
      :key="index"
    >
      <p @click="searchTag(tag.tagName)"># {{ tag.tagName }}</p>
    </div>
  </div>


  <div class="mb-4 text-white rounded bg-dark pics">
    <div
      id="carouselExampleCaptions"
      class="carousel slide carousel-fade"
      data-bs-ride="carousel"
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
        <div class="carousel-item active" data-bs-interval="1000">
          <img
            src="https://img.freepik.com/free-photo/female-tourists-on-hand-have-a-happy-travel-map_1150-7411.jpg?w=1380&t=st=1700116885~exp=1700117485~hmac=87071a92a3e2b4aa412e6e9d753ea9a6c0ce9d4629879ac640e88326f3cb8852"
            class="d-block w-100"
            alt="..."
          />
          <div class="carousel-caption d-none d-md-block">
            <h5>First slide label</h5>
            <p>Some representative placeholder content for the first slide.</p>
          </div>
        </div>
        <div class="carousel-item" data-bs-interval="1000">
          <img
            src="https://a.travel-assets.com/findyours-php/viewfinder/images/res70/477000/477580-Osaka.jpg"
            class="d-block w-100"
            alt="..."
          />
          <div class="carousel-caption d-none d-md-block">
            <h5>Second slide label</h5>
            <p>Some representative placeholder content for the second slide.</p>
          </div>
        </div>
        <div class="carousel-item" data-bs-interval="1000">
          <img
            src="https://images.france.fr/zeaejvyq9bhj/2iAa84874gFOOKxspo7ssu/aa855e94895b7f1192c949bf8ef34fa5/kr_france_fr____________________-018.png?w=1120&h=490&q=70&fm=webp&fit=fill"
            class="d-block w-100"
            alt="..."
          />
          <div class="carousel-caption d-none d-md-block">
            <h5>Third slide label</h5>
            <p>Some representative placeholder content for the third slide.</p>
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
  </div>

  <div class="row g-5">
    <div class="col-md-12">
      <h3 class="pb-4 mb-4 fst-italic border-bottom">HOT Placeses TOP3</h3>
    </div>
  </div>
  <div class="row g-5">
    <div class="col-md-12">
      <hot-place-zone></hot-place-zone>
    </div>
  </div>
  <div class="row g-5">
    <div class="col-md-8">
      <h3 class="pb-4 mb-4 fst-italic border-bottom">Gallery</h3>
      <div class="row g-5">
        <div class="col-md-12">
          <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <div class="col">
              <div class="card shadow-sm">
                <img
                  src="https://mdbootstrap.com/img/Others/documentation/1.webp"
                  class="img-fluid mx-auto d-block"
                  alt="Responsive image"
                />
                <div class="card-body">
                  <p class="card-text">
                    This is a wider card with supporting text below as a natural
                    lead-in to additional content. This content is a little bit
                    longer.
                  </p>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div class="btn-group">
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        View
                      </button>
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        Edit
                      </button>
                    </div>
                    <small class="text-muted">9 mins</small>
                  </div>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="card shadow-sm">
                <img
                  src="https://mdbootstrap.com/img/Others/documentation/1.webp"
                  class="img-fluid mx-auto d-block"
                  alt="Responsive image"
                />
                <div class="card-body">
                  <p class="card-text">
                    This is a wider card with supporting text below as a natural
                    lead-in to additional content. This content is a little bit
                    longer.
                  </p>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div class="btn-group">
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        View
                      </button>
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        Edit
                      </button>
                    </div>
                    <small class="text-muted">9 mins</small>
                  </div>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="card shadow-sm">
                <img
                  src="https://mdbootstrap.com/img/Others/documentation/1.webp"
                  class="img-fluid mx-auto d-block"
                  alt="Responsive image"
                />
                <div class="card-body">
                  <p class="card-text">
                    This is a wider card with supporting text below as a natural
                    lead-in to additional content. This content is a little bit
                    longer.
                  </p>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div class="btn-group">
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        View
                      </button>
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        Edit
                      </button>
                    </div>
                    <small class="text-muted">9 mins</small>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <div class="col">
              <div class="card shadow-sm">
                <img
                  src="https://mdbootstrap.com/img/Others/documentation/1.webp"
                  class="img-fluid mx-auto d-block"
                  alt="Responsive image"
                />
                <div class="card-body">
                  <p class="card-text">
                    This is a wider card with supporting text below as a natural
                    lead-in to additional content. This content is a little bit
                    longer.
                  </p>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div class="btn-group">
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        View
                      </button>
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        Edit
                      </button>
                    </div>
                    <small class="text-muted">9 mins</small>
                  </div>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="card shadow-sm">
                <img
                  src="https://mdbootstrap.com/img/Others/documentation/1.webp"
                  class="img-fluid mx-auto d-block"
                  alt="Responsive image"
                />
                <div class="card-body">
                  <p class="card-text">
                    This is a wider card with supporting text below as a natural
                    lead-in to additional content. This content is a little bit
                    longer.
                  </p>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div class="btn-group">
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        View
                      </button>
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        Edit
                      </button>
                    </div>
                    <small class="text-muted">9 mins</small>
                  </div>
                </div>
              </div>
            </div>

            <div class="col">
              <div class="card shadow-sm">
                <img
                  src="https://mdbootstrap.com/img/Others/documentation/1.webp"
                  class="img-fluid mx-auto d-block"
                  alt="Responsive image"
                />

                <div class="card-body">
                  <p class="card-text">
                    This is a wider card with supporting text below as a natural
                    lead-in to additional content. This content is a little bit
                    longer.
                  </p>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <div class="btn-group">
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        View
                      </button>
                      <button
                        type="button"
                        class="btn btn-sm btn-outline-secondary"
                      >
                        Edit
                      </button>
                    </div>
                    <small class="text-muted">9 mins</small>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <article class="blog-post">
        <h2 class="blog-post-title">Sample blog post</h2>
        <p class="blog-post-meta">January 1, 2021 by <a href="#">Mark</a></p>

        <p>
          This blog post shows a few different types of content that’s supported
          and styled with Bootstrap. Basic typography, lists, tables, images,
          code, and more are all supported as expected.
        </p>
        <hr />
        <p>
          This is some additional paragraph placeholder content. It has been
          written to fill the available space and show how a longer snippet of
          text affects the surrounding content. We'll repeat it often to keep
          the demonstration flowing, so be on the lookout for this exact same
          string of text.
        </p>
        <h2>Blockquotes</h2>
        <p>This is an example blockquote in action:</p>
        <blockquote class="blockquote">
          <p>Quoted text goes here.</p>
        </blockquote>
        <p>
          This is some additional paragraph placeholder content. It has been
          written to fill the available space and show how a longer snippet of
          text affects the surrounding content. We'll repeat it often to keep
          the demonstration flowing, so be on the lookout for this exact same
          string of text.
        </p>
        <h3>Example lists</h3>
        <p>
          This is some additional paragraph placeholder content. It's a slightly
          shorter version of the other highly repetitive body text used
          throughout. This is an example unordered list:
        </p>
        <ul>
          <li>First list item</li>
          <li>Second list item with a longer description</li>
          <li>Third list item to close it out</li>
        </ul>
        <p>And this is an ordered list:</p>
        <ol>
          <li>First list item</li>
          <li>Second list item with a longer description</li>
          <li>Third list item to close it out</li>
        </ol>
        <p>And this is a definiton list:</p>
        <dl>
          <dt>HyperText Markup Language (HTML)</dt>
          <dd>
            The language used to describe and define the content of a Web page
          </dd>
          <dt>Cascading Style Sheets (CSS)</dt>
          <dd>Used to describe the appearance of Web content</dd>
          <dt>JavaScript (JS)</dt>
          <dd>
            The programming language used to build advanced Web sites and
            applications
          </dd>
        </dl>
        <h2>Inline HTML elements</h2>
        <p>
          HTML defines a long list of available inline tags, a complete list of
          which can be found on the
          <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element"
            >Mozilla Developer Network</a
          >.
        </p>
        <ul>
          <li>
            <strong>To bold text</strong>, use
            <code class="language-plaintext highlighter-rouge"
              >&lt;strong&gt;</code
            >.
          </li>
          <li>
            <em>To italicize text</em>, use
            <code class="language-plaintext highlighter-rouge">&lt;em&gt;</code
            >.
          </li>
          <li>
            Abbreviations, like
            <abbr title="HyperText Markup Langage">HTML</abbr> should use
            <code class="language-plaintext highlighter-rouge"
              >&lt;abbr&gt;</code
            >, with an optional
            <code class="language-plaintext highlighter-rouge">title</code>
            attribute for the full phrase.
          </li>
          <li>
            Citations, like <cite>— Mark Otto</cite>, should use
            <code class="language-plaintext highlighter-rouge"
              >&lt;cite&gt;</code
            >.
          </li>
          <li>
            <del>Deleted</del> text should use
            <code class="language-plaintext highlighter-rouge"
              >&lt;del&gt;</code
            >
            and <ins>inserted</ins> text should use
            <code class="language-plaintext highlighter-rouge">&lt;ins&gt;</code
            >.
          </li>
          <li>
            Superscript <sup>text</sup> uses
            <code class="language-plaintext highlighter-rouge"
              >&lt;sup&gt;</code
            >
            and subscript <sub>text</sub> uses
            <code class="language-plaintext highlighter-rouge">&lt;sub&gt;</code
            >.
          </li>
        </ul>
        <p>
          Most of these elements are styled by browsers with few modifications
          on our part.
        </p>
        <h2>Heading</h2>
        <p>
          This is some additional paragraph placeholder content. It has been
          written to fill the available space and show how a longer snippet of
          text affects the surrounding content. We'll repeat it often to keep
          the demonstration flowing, so be on the lookout for this exact same
          string of text.
        </p>
        <h3>Sub-heading</h3>
        <p>
          This is some additional paragraph placeholder content. It has been
          written to fill the available space and show how a longer snippet of
          text affects the surrounding content. We'll repeat it often to keep
          the demonstration flowing, so be on the lookout for this exact same
          string of text.
        </p>
        <pre><code>Example code block</code></pre>
        <p>
          This is some additional paragraph placeholder content. It's a slightly
          shorter version of the other highly repetitive body text used
          throughout.
        </p>
      </article>

      <article class="blog-post">
        <h2 class="blog-post-title">Another blog post</h2>
        <p class="blog-post-meta">December 23, 2020 by <a href="#">Jacob</a></p>

        <p>
          This is some additional paragraph placeholder content. It has been
          written to fill the available space and show how a longer snippet of
          text affects the surrounding content. We'll repeat it often to keep
          the demonstration flowing, so be on the lookout for this exact same
          string of text.
        </p>
        <blockquote>
          <p>
            Longer quote goes here, maybe with some
            <strong>emphasized text</strong> in the middle of it.
          </p>
        </blockquote>
        <p>
          This is some additional paragraph placeholder content. It has been
          written to fill the available space and show how a longer snippet of
          text affects the surrounding content. We'll repeat it often to keep
          the demonstration flowing, so be on the lookout for this exact same
          string of text.
        </p>
        <board-table></board-table>
        <p>
          This is some additional paragraph placeholder content. It's a slightly
          shorter version of the other highly repetitive body text used
          throughout.
        </p>
      </article>

      <article class="blog-post">
        <h2 class="blog-post-title">New feature</h2>
        <p class="blog-post-meta">December 14, 2020 by <a href="#">Chris</a></p>

        <p>
          This is some additional paragraph placeholder content. It has been
          written to fill the available space and show how a longer snippet of
          text affects the surrounding content. We'll repeat it often to keep
          the demonstration flowing, so be on the lookout for this exact same
          string of text.
        </p>
        <ul>
          <li>First list item</li>
          <li>Second list item with a longer description</li>
          <li>Third list item to close it out</li>
        </ul>
        <p>
          This is some additional paragraph placeholder content. It's a slightly
          shorter version of the other highly repetitive body text used
          throughout.
        </p>
      </article>

      <nav class="blog-pagination" aria-label="Pagination">
        <a class="btn btn-outline-primary" href="#">Older</a>
        <a
          class="btn btn-outline-secondary disabled"
          href="#"
          tabindex="-1"
          aria-disabled="true"
          >Newer</a
        >
      </nav>
    </div>
    <div class="col-md-4">
      <div class="position-sticky" style="top: 2rem">
        <div class="p-4 mb-3 bg-light rounded">
          <h4 class="fst-italic">About</h4>
          <p class="mb-0">
            Customize this section to tell your visitors a little bit about your
            publication, writers, content, or something else entirely. Totally
            up to you.
          </p>
        </div>

        <div class="p-4">
          <h4 class="fst-italic">Archives</h4>
          <ol class="list-unstyled mb-0">
            <li><a href="#">March 2021</a></li>
            <li><a href="#">February 2021</a></li>
            <li><a href="#">January 2021</a></li>
            <li><a href="#">December 2020</a></li>
            <li><a href="#">November 2020</a></li>
            <li><a href="#">October 2020</a></li>
            <li><a href="#">September 2020</a></li>
            <li><a href="#">August 2020</a></li>
            <li><a href="#">July 2020</a></li>
            <li><a href="#">June 2020</a></li>
            <li><a href="#">May 2020</a></li>
            <li><a href="#">April 2020</a></li>
          </ol>
        </div>

        <div class="p-4">
          <h4 class="fst-italic">Elsewhere</h4>
          <ol class="list-unstyled">
            <li><a href="#">GitHub</a></li>
            <li><a href="#">Twitter</a></li>
            <li><a href="#">Facebook</a></li>
          </ol>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.material-symbols-outlined {
  font-variation-settings: "FILL" 0, "wght" 400, "GRAD" 0, "opsz" 24;
}

.search-mode {
  position: relative;
  width: 750px;
  height: 50px;
  margin: 20px auto;
}
.hot-tag {
  position: relative;
  width: 770px;
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
  border: 1.5px solid;
  font-size: 19px;
  padding-left: 9%;
}

.icon {
  display: flex;
  justify-content: space-between;
  position: absolute;
  top: 14px;
  right: 10px;
  left: 20px;
}

.search {
  color: #808080;
}

.plus-option i {
  display: inline-block;
  margin-left: 5px;
}

.plus-option i:nth-child(2) {
  color: #4f86ec;
}

/* 캐러셀 */
.carousel-inner > .carousel-item > img {
  width: 640px;
  height: 400px;
}
</style> -->

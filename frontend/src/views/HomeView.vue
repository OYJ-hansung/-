<script setup>
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
      <div class="search"><span class="material-symbols-outlined">search</span></div>
    </div>
  </div>

  <div class="hot-tag mb-4 row" style="display: flex">
    <div class="col-md-2" style="float: left; font-size: 18px"><b>인기 태그 :</b></div>
    <div
      class="col-md-2"
      style="float: left; font-size: 18px; color: #0000cd"
      v-for="(tag, index) in hotTags"
      :key="index"
    >
      <p @click="searchTag(tag.tagName)"># {{ tag.tagName }}</p>
    </div>
  </div>

  <!-- 케러셀 -->
  <div class="mb-4 text-white rounded bg-dark pics">
    <div id="carouselExampleCaptions" class="carousel slide carousel-fade" data-bs-ride="carousel">
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
                    This is a wider card with supporting text below as a natural lead-in to
                    additional content. This content is a little bit longer.
                  </p>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                      <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                      <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
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
                    This is a wider card with supporting text below as a natural lead-in to
                    additional content. This content is a little bit longer.
                  </p>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                      <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                      <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
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
                    This is a wider card with supporting text below as a natural lead-in to
                    additional content. This content is a little bit longer.
                  </p>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                      <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                      <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
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
                    This is a wider card with supporting text below as a natural lead-in to
                    additional content. This content is a little bit longer.
                  </p>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                      <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                      <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
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
                    This is a wider card with supporting text below as a natural lead-in to
                    additional content. This content is a little bit longer.
                  </p>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                      <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                      <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
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
                    This is a wider card with supporting text below as a natural lead-in to
                    additional content. This content is a little bit longer.
                  </p>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                      <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                      <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
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
          This blog post shows a few different types of content that’s supported and styled with
          Bootstrap. Basic typography, lists, tables, images, code, and more are all supported as
          expected.
        </p>
        <hr />
        <p>
          This is some additional paragraph placeholder content. It has been written to fill the
          available space and show how a longer snippet of text affects the surrounding content.
          We'll repeat it often to keep the demonstration flowing, so be on the lookout for this
          exact same string of text.
        </p>
        <h2>Blockquotes</h2>
        <p>This is an example blockquote in action:</p>
        <blockquote class="blockquote">
          <p>Quoted text goes here.</p>
        </blockquote>
        <p>
          This is some additional paragraph placeholder content. It has been written to fill the
          available space and show how a longer snippet of text affects the surrounding content.
          We'll repeat it often to keep the demonstration flowing, so be on the lookout for this
          exact same string of text.
        </p>
        <h3>Example lists</h3>
        <p>
          This is some additional paragraph placeholder content. It's a slightly shorter version of
          the other highly repetitive body text used throughout. This is an example unordered list:
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
          <dd>The language used to describe and define the content of a Web page</dd>
          <dt>Cascading Style Sheets (CSS)</dt>
          <dd>Used to describe the appearance of Web content</dd>
          <dt>JavaScript (JS)</dt>
          <dd>The programming language used to build advanced Web sites and applications</dd>
        </dl>
        <h2>Inline HTML elements</h2>
        <p>
          HTML defines a long list of available inline tags, a complete list of which can be found
          on the
          <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element"
            >Mozilla Developer Network</a
          >.
        </p>
        <ul>
          <li>
            <strong>To bold text</strong>, use
            <code class="language-plaintext highlighter-rouge">&lt;strong&gt;</code>.
          </li>
          <li>
            <em>To italicize text</em>, use
            <code class="language-plaintext highlighter-rouge">&lt;em&gt;</code>.
          </li>
          <li>
            Abbreviations, like
            <abbr title="HyperText Markup Langage">HTML</abbr> should use
            <code class="language-plaintext highlighter-rouge">&lt;abbr&gt;</code>, with an optional
            <code class="language-plaintext highlighter-rouge">title</code>
            attribute for the full phrase.
          </li>
          <li>
            Citations, like <cite>— Mark Otto</cite>, should use
            <code class="language-plaintext highlighter-rouge">&lt;cite&gt;</code>.
          </li>
          <li>
            <del>Deleted</del> text should use
            <code class="language-plaintext highlighter-rouge">&lt;del&gt;</code>
            and <ins>inserted</ins> text should use
            <code class="language-plaintext highlighter-rouge">&lt;ins&gt;</code>.
          </li>
          <li>
            Superscript <sup>text</sup> uses
            <code class="language-plaintext highlighter-rouge">&lt;sup&gt;</code>
            and subscript <sub>text</sub> uses
            <code class="language-plaintext highlighter-rouge">&lt;sub&gt;</code>.
          </li>
        </ul>
        <p>Most of these elements are styled by browsers with few modifications on our part.</p>
        <h2>Heading</h2>
        <p>
          This is some additional paragraph placeholder content. It has been written to fill the
          available space and show how a longer snippet of text affects the surrounding content.
          We'll repeat it often to keep the demonstration flowing, so be on the lookout for this
          exact same string of text.
        </p>
        <h3>Sub-heading</h3>
        <p>
          This is some additional paragraph placeholder content. It has been written to fill the
          available space and show how a longer snippet of text affects the surrounding content.
          We'll repeat it often to keep the demonstration flowing, so be on the lookout for this
          exact same string of text.
        </p>
        <pre><code>Example code block</code></pre>
        <p>
          This is some additional paragraph placeholder content. It's a slightly shorter version of
          the other highly repetitive body text used throughout.
        </p>
      </article>

      <article class="blog-post">
        <h2 class="blog-post-title">Another blog post</h2>
        <p class="blog-post-meta">December 23, 2020 by <a href="#">Jacob</a></p>

        <p>
          This is some additional paragraph placeholder content. It has been written to fill the
          available space and show how a longer snippet of text affects the surrounding content.
          We'll repeat it often to keep the demonstration flowing, so be on the lookout for this
          exact same string of text.
        </p>
        <blockquote>
          <p>
            Longer quote goes here, maybe with some
            <strong>emphasized text</strong> in the middle of it.
          </p>
        </blockquote>
        <p>
          This is some additional paragraph placeholder content. It has been written to fill the
          available space and show how a longer snippet of text affects the surrounding content.
          We'll repeat it often to keep the demonstration flowing, so be on the lookout for this
          exact same string of text.
        </p>
        <board-table></board-table>
        <p>
          This is some additional paragraph placeholder content. It's a slightly shorter version of
          the other highly repetitive body text used throughout.
        </p>
      </article>

      <article class="blog-post">
        <h2 class="blog-post-title">New feature</h2>
        <p class="blog-post-meta">December 14, 2020 by <a href="#">Chris</a></p>

        <p>
          This is some additional paragraph placeholder content. It has been written to fill the
          available space and show how a longer snippet of text affects the surrounding content.
          We'll repeat it often to keep the demonstration flowing, so be on the lookout for this
          exact same string of text.
        </p>
        <ul>
          <li>First list item</li>
          <li>Second list item with a longer description</li>
          <li>Third list item to close it out</li>
        </ul>
        <p>
          This is some additional paragraph placeholder content. It's a slightly shorter version of
          the other highly repetitive body text used throughout.
        </p>
      </article>

      <nav class="blog-pagination" aria-label="Pagination">
        <a class="btn btn-outline-primary" href="#">Older</a>
        <a class="btn btn-outline-secondary disabled" href="#" tabindex="-1" aria-disabled="true"
          >Newer</a
        >
      </nav>
    </div>
    <div class="col-md-4">
      <div class="position-sticky" style="top: 2rem">
        <div class="p-4 mb-3 bg-light rounded">
          <h4 class="fst-italic">About</h4>
          <p class="mb-0">
            Customize this section to tell your visitors a little bit about your publication,
            writers, content, or something else entirely. Totally up to you.
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
  border: 3px solid;
  font-size: 23px;
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
</style>

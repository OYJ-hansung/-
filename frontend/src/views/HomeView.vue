<script setup>
import BoardTable from "@/components/BoardTable.vue";
import AdsBody from "@/components/AdsBody.vue";
import HotPlaceZone from "@/components/HotPlaceZone.vue";
</script>

<template>
  <link
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined"
    rel="stylesheet"
  />
  <div class="search-mode">
    <input type="text" class="form-control" placeholder="검색어를 입력하세요." />
    <i class="bi bi-search"></i>

    <div class="icon">
      <div class="search"><span class="material-symbols-outlined">search</span></div>
      <div class="plus-option">
        <i class="fas fa-keyboard"></i>
        <i class="fas fa-microphone"></i>
      </div>
    </div>
  </div>

  <div class="p-4 p-md-5 mb-4 text-white rounded bg-dark">
    <ads-body></ads-body>
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
  width: 800px;
  height: 50px;
  margin: 10px auto;
}

input {
  /* border: 1px solid #f1f1f1; */
  border-radius: 50px;
  width: 100%;
  height: 100%;
  border: 2px solid;
  font-size: 25px;
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
</style>

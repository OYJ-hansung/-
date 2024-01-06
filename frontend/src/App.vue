<script setup>
import { RouterView } from "vue-router";
import HeaderNav from "./components/common/HeaderNav.vue";
import FooterNav from "./components/common/FooterNav.vue";
</script>

<template>
  <header class="blog-header" style="position: sticky; top: 0; z-index: 2">
    <header-nav></header-nav>
  </header>

  <main class="container-fluid">
    <router-view></router-view>
  </main>
  <footer class="blog-footer">
    <footer-nav></footer-nav>
  </footer>
</template>

<style scoped>
.card {
  position: relative;
}
.card-content {
  box-shadow: 0 2rem 2rem rgba(0, 0, 0, 0.5);
  transition: all 2s;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  backface-visibility: hidden;
}
.card-content img {
  width: 100%;
  height: 200px;
  object-fit: cover;
  margin-bottom: 10px;
}

.card-body {
  padding: 20px;
}

.card-body h3 {
  margin-bottom: 20px;
}

.card-back {
  transform: rotateY(180deg);
}
.card:hover .card-front {
  transform: rotateY(-180deg);
}
.card:hover .card-back {
  transform: rotateY(0);
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
</style>

<script setup>
import BoardCardList from "../components/board/BoardCardList.vue";
import { searchStore } from "@/stores/planListStore";

const sstore = searchStore();
const check = () => {
  console.log(sstore.onlyFavorite);
};
</script>
좋
<template>
  <div class="row g-5">
    <div class="col-md-12">
      <h1 class="fw-light d-flex justify-content-center mt-5">여행계획 게시판</h1>
      <board-card-list></board-card-list>
    </div>
  </div>
</template>

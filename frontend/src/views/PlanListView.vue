<script setup>
import BoardCardList from "../components/board/BoardCardList.vue";
import { ref } from "vue";
import { useRoute } from "vue-router";
import { useRouter } from "vue-router";
</script>

<template>
  <div class="row g-5">
    <div class="col-md-12">
      <h3 class="pb-4 mb-4 fst-italic border-bottom">여행계획 게시판</h3>
      <board-card-list></board-card-list>
    </div>
  </div>
</template>

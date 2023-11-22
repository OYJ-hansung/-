<script setup>
import BoardCardList from "../components/board/BoardCardList.vue";
import { searchStore } from "@/stores/planListStore";

const sstore = searchStore();
const check = () => {
  console.log(sstore.onlyFavorite);
};
</script>

<template>
  <div class="row g-5">
    <div class="col-md-12">
      <h3 class="pb-4 mb-4 fst-italic border-bottom">여행계획 게시판</h3>
      <div class="form-check form-check-inline d-flex justify-content-center m-3">
        <input
          class="form-check-input"
          type="checkbox"
          id="favoriteCheckBox"
          v-model="sstore.onlyFavorite"
          @change="check($event)"
        />
        <label class="form-check-label ms-3" for="inlineCheckbox1">좋아요한 여행계획만</label>
      </div>
      <board-card-list></board-card-list>
    </div>
  </div>
</template>

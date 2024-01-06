<script setup>
import BoardCardList from "../components/board/BoardCardList.vue";
import { useRouter } from "vue-router";
import { searchStore } from "@/stores/planListStore";
import { onMounted } from "vue";

const sstore = searchStore();
const router = useRouter();

onMounted(() => {
  sstore.isMy = true;
});
</script>

<template>
  <div class="row g-5">
    <div class="col-md-12">
      <h1 class="fw-light d-flex justify-content-center mt-5">
        나의 여행 계획
      </h1>
      <board-card-list></board-card-list>
    </div>
  </div>
</template>

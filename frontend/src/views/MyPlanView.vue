<script setup>
import BoardCardList from "../components/board/BoardCardList.vue";
import { useRouter } from "vue-router";
import { searchStore } from "@/stores/planListStore";
import { onMounted, ref } from "vue";

const sstore = searchStore();
const router = useRouter();

const addPlan = () => {
  router.push({ path: "/addplan" });
};

onMounted(() => {
  sstore.isMy = true;
});
</script>

<template>
  <div class="row g-5">
    <div class="col-md-12">
      <h3 class="pb-4 mt-4 mb-4 fst-italic border-bottom">나의여행계획게시판</h3>
      <div class="m-2 p-1 row justify-content-end">
        <button type="button" class="btn btn-primary float-right m-2 col-2" @click="addPlan()">
          <b>여행계획추가</b>
        </button>
      </div>
      <!-- <board-card-list :props="props"></board-card-list> -->
      <board-card-list></board-card-list>
    </div>
  </div>
</template>

<script setup>
import api from "axios";
import { onMounted, ref } from "vue";
import BoardCard from "../components/board/BoardCard.vue";

const hotPlanList = ref([]);

const getHotPlanList = async () => {
  await api
    .get(`${import.meta.env.VITE_VUE_API_URL}/plan/hot`)
    .then(({ data }) => {
      hotPlanList.value = data;
      console.log(hotPlanList.value);
    })
    .catch((e) => {
      console.log(e);
    });
};

onMounted(() => {
  getHotPlanList();
});
</script>

<template>
  <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <div class="col-md-12">
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
        <div v-for="(plan, index) in hotPlanList" :key="index">
          <board-card :plan="plan"></board-card>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>

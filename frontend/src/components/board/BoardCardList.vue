<script setup>
import { ref, onMounted, defineProps } from "vue";
import BoardCard from "./BoardCard.vue";
import api from "axios";
import { searchStore } from "@/stores/planListStore";
const sstore = searchStore();

const planList = ref([]);
const boardlist = async () => {
  await api
    .post(`http://localhost:8090/trip/plan`, {
      key: "",
      word: "",
    })
    .then(({ data }) => {
      console.log("검색한거니?", sstore.isSearch);
      if (sstore.isSearch) {
        
      }
      planList.value = data;
    })
    .catch((e) => {
      console.log(e);
    });
};

onMounted(() => {
  boardlist();
});
</script>

<template>
  <div class="col-md-12">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
      <div v-for="(plan, index) in planList" :key="index">
        <board-card :plan="plan"></board-card>
      </div>
    </div>
  </div>
</template>

<style scoped></style>

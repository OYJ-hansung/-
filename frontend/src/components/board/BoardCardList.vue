<script setup>
import { ref, onMounted, defineProps } from "vue";
import BoardCard from "./BoardCard.vue";
import api from "axios";
import { searchStore } from "@/stores/planListStore";
import { userStore } from "@/stores/userStore";

const sstore = searchStore();
const ustore = userStore();

const planList = ref([]);
const boardlist = async () => {
  await api
    .post(`http://localhost:8090/trip/plan`, {
      key: sstore.key,
      word: sstore.word,
      userId: ustore.userInfo.userId
    })
    .then(({ data }) => {
      console.log(data);
      if (ustore.isLogin && sstore.isMy) {
        // 내여행계획
        planList.value = data.filter((d) => d.userId === ustore.userInfo.userId);
      } else {
        planList.value = data.filter((d) => d.shared === 1);
      }
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

<script setup>
import { ref, onMounted, defineProps } from "vue";
import BoardCard from "./BoardCard.vue";
import api from "axios";
import { userStore } from "@/stores/userStore";

const ustore = userStore();
const my = defineProps("my");
console.log("my: " + my.value);

const planList = ref([]);
const boardlist = async () => {
  await api
    .post(`http://localhost:8090/trip/plan`, {
      key: "",
      word: "",
    })
    .then(({ data }) => {
      if (my.value === true) {
        // 내 정보라면
        planList.value = data.filter((plan) => plan.userId === ustore.userInfo.userId);
      } else {
        planList.value = data;
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

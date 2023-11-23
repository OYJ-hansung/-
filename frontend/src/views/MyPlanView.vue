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
const check = () => {
  console.log(sstore.onlyShared);
};

onMounted(() => {
  sstore.isMy = true;
});
</script>

<template>
  <div class="row g-5">
    <div class="col-md-12">
      <h1 class="fw-light d-flex justify-content-center mt-5">나의 여행 계획</h1>

      <!-- <div class="form-check form-check-inline d-flex justify-content-center m-3">
        <input
          class="form-check-input"
          type="checkbox"
          id="favoriteCheckBox"
          v-model="sstore.onlyShared"
          @change="check($event)"
        />
        <label class="form-check-label ms-3" for="favoriteCheckBox">공유된 여행계획만</label>
      </div> -->
      <board-card-list></board-card-list>
    </div>
  </div>
</template>

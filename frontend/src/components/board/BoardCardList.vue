<script setup>
import { ref, onMounted, watch } from "vue";
import BoardCard from "./BoardCard.vue";
import api from "axios";
import { searchStore } from "@/stores/planListStore";
import { userStore } from "@/stores/userStore";
import { useRouter } from "vue-router";

const router = useRouter();

const sstore = searchStore();
const ustore = userStore();
const planList = ref([]);
const onlyFavoriteList = ref([]);
const onlySharedList = ref([]);
const onlyFavoriteToggle = ref("");
const onlySharedToggle = ref("");

const boardlist = async () => {
  await api
    .post(`${import.meta.env.VITE_VUE_API_URL}/plan`, {
      key: sstore.key,
      word: sstore.word,
      userId: ustore.userInfo.userId,
    })
    .then(({ data }) => {
      if (ustore.isLogin && sstore.isMy) {
        // 내여행계획
        planList.value = data.filter(
          (d) => d.userId === ustore.userInfo.userId
        );
      } else {
        planList.value = data.filter((d) => d.shared === 1);
      }

      onlyFavoriteList.value = planList.value.filter((plan) => {
        return plan.isFavorite === true;
      });

      onlySharedList.value = planList.value.filter((plan) => {
        return plan.shared === 1;
      });
    })
    .catch((e) => {
      console.log(e);
    });
};

watch(
  () => sstore.onlyFavorite,
  () => {
    if (sstore.onlyFavorite) {
      onlyFavoriteToggle.value = true;
      onlySharedToggle.value = false;
    } else onlyFavoriteToggle.value = false;
  }
);

watch(
  () => sstore.onlyShared,
  () => {
    if (sstore.onlyShared) {
      onlySharedToggle.value = true;
      onlyFavoriteToggle.value = false;
    } else onlySharedToggle.value = false;
  }
);
const addPlan = () => {
  router.push({ path: "/addplan" });
};

onMounted(() => {
  boardlist();
});
</script>

<template>
  <div class="cards-1 section-gray">
    <div class="container">
      <div class="mb-4" v-if="!sstore.isMy">
        <div class="form-check form-check-inline m-3">
          <input
            class="form-check-input"
            type="checkbox"
            id="favoriteCheckBox"
            v-model="sstore.onlyFavorite"
            @change="check($event)"
          />
          <label class="form-check-label ms-3" for="inlineCheckbox1"
            >좋아요한 여행계획만</label
          >
        </div>
      </div>

      <div v-if="sstore.isMy" class="mb-4">
        <div class="form-check form-check-inline m-3">
          <input
            class="form-check-input"
            type="checkbox"
            id="favoriteCheckBox"
            v-model="sstore.onlyShared"
            @change="check($event)"
          />
          <label class="form-check-label ms-3" for="favoriteCheckBox"
            >공유된 여행계획만</label
          >
        </div>
        <div class="form-check form-check-inline" @click="addPlan()">
          <button
            class="btn btn-outline-primary d-inline-flex align-items-center"
            style="font-size: 14px"
            type="button"
          >
            <i class="fa-light fa-plus"></i>&nbsp; 여행계획추가
          </button>
        </div>
      </div>
      <div
        v-if="!onlyFavoriteToggle && !onlySharedToggle"
        class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-3"
      >
        <div v-for="(plan, index) in planList" :key="index">
          <board-card :plan="plan"></board-card>
        </div>
      </div>

      <div class="col-md-12">
        <div
          v-if="onlyFavoriteToggle"
          class="row row-cols-sm-2 row-cols-md-3 g-3"
        >
          <div v-for="(plan, index) in onlyFavoriteList" :key="index">
            <board-card :plan="plan"></board-card>
          </div>
        </div>
      </div>
      <div class="col-md-12">
        <div
          v-if="onlySharedToggle"
          class="row row-cols-sm-2 row-cols-md-3 g-3"
        >
          <div v-for="(plan, index) in onlySharedList" :key="index">
            <board-card :plan="plan"></board-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>

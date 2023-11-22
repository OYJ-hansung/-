<script setup>
import { defineProps, ref, computed } from "vue";
import api from "axios";
import { userStore } from "@/stores/userStore";

const p = defineProps(["plan"]);
const ustore = userStore();
const favoriteToggle = ref(p.plan.isFavorite);

const addFavorite = async () => {
  await api
    .post(`${import.meta.env.VITE_VUE_API_URL}/plan/addfavorite`, {
      userId: ustore.userInfo.userId,
      planId: p.plan.planId,
    })
    .then(() => {
      favoriteToggle.value = true;
    })
    .catch((e) => {
      console.log(e);
    });
};

const deleteFavorite = async () => {
  console.log(ustore.userInfo.userId);
  console.log(p.plan.planId);
  await api
    .post(`${import.meta.env.VITE_VUE_API_URL}/plan/delfavorite`, {
      userId: ustore.userInfo.userId,
      planId: p.plan.planId,
    })
    .then(() => {
      favoriteToggle.value = false;
    })
    .catch((e) => {
      console.log(e);
    });
};

const currentTotalFavorite = computed(() => {
  return p.plan.totalFavorite;
});

const clickFavorite = () => {
  if (favoriteToggle.value === true) {
    deleteFavorite();
    p.plan.totalFavorite -= 1;
  } else {
    addFavorite();
    p.plan.totalFavorite += 1;
  }
};
</script>

<template>
  <div class="col">
    <div class="card shadow-sm">
      <img :src="plan.img" class="img-fluid mx-auto d-block" alt="Responsive image" />
      <div class="card-body">
        <div class="row">
          <figure>
            <blockquote class="blockquote">
              <router-link :to="{ name: 'plandetail', params: { id: plan.planId } }">
                <p>{{ plan.planName }}</p>
              </router-link>
            </blockquote>
            <figcaption class="blockquote-footer">
              <cite title="Source Title">{{ plan.userId }}</cite>
            </figcaption>
          </figure>
          <p class="card-text">
            {{ plan.planDetail }}
          </p>

          <div class="d-flex align-items-center">
            <small class="text-primary" v-for="(tag, index) in plan.tagList" :key="index">
              #{{ tag }} &nbsp;</small
            >
          </div>
          <div>
            <span class="d-inline-block btn" @click="clickFavorite">
              <i
                v-if="favoriteToggle === true"
                class="fa-solid fa-heart fa-2xl"
                style="color: #ff0000"
              ></i>
              <i v-else class="fa-regular fa-heart fa-2xl" style="color: #ff0000"></i>
            </span>
            <span class="d-inline-block float-right">{{ currentTotalFavorite }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>

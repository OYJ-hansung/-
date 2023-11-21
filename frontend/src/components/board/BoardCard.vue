<script setup>
import { defineProps, ref } from "vue";
import api from "axios";
import { userStore } from "@/stores/userStore";

const p = defineProps(["plan"]);
const ustore = userStore();
const favoriteToggle = ref(p.plan.isFavorite);

console.log("p: " + JSON.stringify(p.plan.isFavorite));

const addFavorite = async () => {
  console.log("좋아요합니다!");
  await api
    .post(`http://localhost:8090/trip/plan/addfavorite`, {
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
  console.log("좋아요 취소합니다!");
  console.log(ustore.userInfo.userId);
  console.log(p.plan.planId);
  await api
    .post(`http://localhost:8090/trip/plan/delfavorite`, {
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

const clickFavorite = () => {
  console.log("favoriteToggle.value : ", favoriteToggle.value);
  if (favoriteToggle.value === true) {
    console.log("좋아요 취소합니다! 를 들어왔어요!");
    deleteFavorite();
  } else {
    console.log("좋아요합니다! 를 들어왔어요");
    addFavorite();
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
          <div class="col-sm-10 m-1" @click="clickFavorite">
            <i
              v-if="favoriteToggle === true"
              class="fa-solid fa-heart fa-2xl"
              style="color: #ff0000"
            ></i>
            <i v-else class="fa-regular fa-heart fa-2xl" style="color: #ff0000"></i>
          </div>
          <!-- <input type="button" @click="$emit('updateBalance', child)" value="용돈 더 주세요" /> -->
        </div>
      </div>
    </div>
  </div>
  <!-- </router-link> -->
</template>

<style scoped></style>

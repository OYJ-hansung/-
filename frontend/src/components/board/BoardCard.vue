<script setup>
import { defineProps } from "vue";
defineProps(["plan"]);
const addFavorite = async () => {
  await api
    .post(`http://localhost:8090/trip/plan/favorite`, {})
    .then(() => {})
    .catch((e) => {
      console.log(e);
    });
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
          <div class="col-sm-10 m-1" @click="addFavorite">
            <i class="fa-regular fa-heart fa-2xl" style="color: #ff0000"></i>
            <!-- <i class="fa-solid fa-heart fa-2xl" style="color: #ff0000"></i> -->
          </div>
          <!-- <input type="button" @click="$emit('updateBalance', child)" value="용돈 더 주세요" /> -->
        </div>
      </div>
    </div>
  </div>
  <!-- </router-link> -->
</template>

<style scoped></style>

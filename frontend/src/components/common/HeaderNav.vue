<script setup>
import { RouterLink } from "vue-router";
import { userStore } from "@/stores/userStore";
const ustore = userStore();
console.log("ustore userInfo - 네비게이션바: " + ustore.isLogin);
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="/"> EnjoyTrips </a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <a class="navbar-brand" href="#"> EnjoyTrips </a>
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav m-1">
          <li class="nav-item">
            <router-link to="/" class="nav-link active" aria-current="page"
              >지역별 여행지</router-link
            >
          </li>
          <li class="nav-item">
            <a href="/planlist" class="nav-link active" aria-current="page">여행계획목록</a>
          </li>
          <li v-show="ustore.userInfo === null" class="nav-item">
            <a href="/login" class="nav-link active" aria-current="page">로그인/회원가입</a>
          </li>
          <li v-show="ustore.userInfo !== null" class="nav-item">
            <a href="/login" class="nav-link active" aria-current="page">
              <b>{{ ustore.userInfo.userId }} 님 환영합니다.</b></a
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>

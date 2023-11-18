<script setup>
import { RouterLink } from "vue-router";
import { userStore } from "@/stores/userStore";
const ustore = userStore();
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
          <li v-if="ustore.userInfo !== null" class="nav-item">
            <div class="dropdown">
              <a
                href="#"
                class="nav-link active dropdown-toggle"
                aria-current="page"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <b>{{ ustore.userInfo.userId }} 님 환영합니다.</b></a
              >
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/myplan">나의여행계획</a></li>
                <li><a class="dropdown-item" href="/userInfo">회원정보</a></li>
              </ul>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>

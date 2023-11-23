<script setup>
import { RouterLink } from "vue-router";
import { searchStore } from "@/stores/planListStore";
import { userStore } from "@/stores/userStore";
import { onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const sstore = searchStore();
const ustore = userStore();
const logout = async () => {
  await ustore.userLogout(ustore.userInfo.userId);
  router.push({ path: "/login" });
};

onMounted(() => {
  sstore.isMy = false;
  sstore.isSearch = false;
  sstore.onlyFavorite = false;
  sstore.onlyShared = false;
  sstore.word = "";
});
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
        <ul class="navbar-nav">
          <li class="nav-item">
            <a href="/attrlist" class="nav-link active" aria-current="page">지역별 여행지</a>
          </li>
          <li class="nav-item">
            <a href="/planlist" class="nav-link active" aria-current="page">여행계획목록</a>
          </li>
          <li class="nav-item" v-if="ustore.userInfo !== null">
            <a class="nav-link active" href="/myplan">나의여행계획</a>
          </li>

          <li v-show="ustore.userInfo === null" class="nav-item">
            <a href="/login" class="nav-link active" aria-current="page">로그인/회원가입</a>
          </li>
          <li v-if="ustore.userInfo !== null" class="nav-item mx-2">
            <div class="dropdown">
              <button
                class="btn dropdown-toggle"
                type="button"
                id="dropdownMenuButton"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <b>{{ ustore.userInfo.userId }} 님 환영합니다.</b>
              </button>

              <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <li>
                  <a class="dropdown-item my-2" href="/userInfo"
                    ><i class="fa-solid fa-user ms-3"></i> &nbsp; 회원정보</a
                  >
                </li>
                <li @click="logout">
                  <a class="dropdown-item my-2 ms-3" href="#"
                    ><i class="fa-solid fa-arrow-right-from-bracket"></i>&nbsp; 로그아웃</a
                  >
                </li>
              </ul>




              
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped>


</style>

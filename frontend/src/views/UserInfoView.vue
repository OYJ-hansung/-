<script setup>
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { userStore } from "@/stores/userStore";
import api from "axios";

const router = useRouter();
const ustore = userStore();
const isChangePass = ref(false);

const userInfo = ref({
  userId: ustore.userInfo.userId,
  userName: ustore.userInfo.userName,
  userPassword: ustore.userInfo.userPassword,
  emailId: ustore.userInfo.emailId,
  emailDomain: ustore.userInfo.emailDomain
});

const modify = async () => {
  await api
    .post(`${import.meta.env.VITE_VUE_API_URL}/members/`, {
      userId: userInfo.value.userId,
      userName: userInfo.value.userName,
      userPassword: userInfo.value.userPassword,
      emailId: userInfo.value.emailId,
      emailDomain: userInfo.value.emailDomain,
    })
    .then(() => {
      router.push({ name: "login" });
    })
    .catch((e) => {
      console.log(e);
    });
};

const changePassword = ()=>{
  isChangePass.value = true
}
</script>

<template>
  <body>
    <div class="container d-flex justify-content-center">
      <main>
        <div class="py-5 text-center">
          <h2>회원정보</h2>
        </div>

        <div class="row">
          <div class="col-md-7 col-lg-12">
            <form >
              <div class="row g-3">
                <div class="col-sm-12 mb-3">
                  <label for="firstName" class="form-label">아이디</label>
                  <input type="text" class="form-control" id="id" placeholder="" v-model="userInfo.userId" disabled />
                </div>
              </div>
              <div class="row g-3">
                <div class="col-sm-12 mb-3">
                  <label for="firstName" class="form-label">이름</label>
                  <input type="text" class="form-control" id="name" placeholder="" v-model="userInfo.userName" disabled />
                </div>
              </div>
              <div class="row g-3">
                <div class="col-sm-12 mb-3">
                  <label for="firstName" class="form-label">비밀번호</label>
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="******" disabled>
                    <button class="btn btn-primary" @click="changePassword()">변경하기</button>
                  </div>
                </div>
              </div>

              <div v-if="isChangePass" class="row g-3">
                <div class="col-sm-12 mb-3">
                  <label for="firstName" class="form-label">새로운 비밀번호</label>
                  <input type="text" class="form-control" id="name" placeholder="" v-model="userInfo.userName" disabled />
                </div>
              </div>

              <div v-if="isChangePass" class="row g-3">
                <div class="col-sm-12 mb-3">
                  <label for="firstName" class="form-label">비밀번호 확인</label>
                  <input type="text" class="form-control" id="name" placeholder="" v-model="userInfo.userName" disabled />
                </div>
              </div>


              <div class="row g-3">
                <div class="col-sm-12">
                  <label for="firstName" class="form-label">이메일</label>
                  <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="아이디" aria-label="emailId"
                      v-model="userInfo.emailId" />
                    <span class="input-group-text">@</span>
                    <input type="text" class="form-control" placeholder="도메인" aria-label="emailDomain"
                      v-model="userInfo.emailDomain" />
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
        <button class="w-100 btn btn-primary btn-lg" @click="register">저장</button>
      </main>
    </div>
  </body>
</template>

<style scoped>
.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}

@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
}

.b-example-divider {
  width: 100%;
  height: 3rem;
  background-color: rgba(0, 0, 0, 0.1);
  border: solid rgba(0, 0, 0, 0.15);
  border-width: 1px 0;
  box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1), inset 0 0.125em 0.5em rgba(0, 0, 0, 0.15);
}

.b-example-vr {
  flex-shrink: 0;
  width: 1.5rem;
  height: 100vh;
}

.bi {
  vertical-align: -0.125em;
  fill: currentColor;
}

.nav-scroller {
  position: relative;
  z-index: 2;
  height: 2.75rem;
  overflow-y: hidden;
}

.nav-scroller .nav {
  display: flex;
  flex-wrap: nowrap;
  padding-bottom: 1rem;
  margin-top: -1px;
  overflow-x: auto;
  text-align: center;
  white-space: nowrap;
  -webkit-overflow-scrolling: touch;
}

.btn-bd-primary {
  --bd-violet-bg: #712cf9;
  --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

  --bs-btn-font-weight: 600;
  --bs-btn-color: var(--bs-white);
  --bs-btn-bg: var(--bd-violet-bg);
  --bs-btn-border-color: var(--bd-violet-bg);
  --bs-btn-hover-color: var(--bs-white);
  --bs-btn-hover-bg: #6528e0;
  --bs-btn-hover-border-color: #6528e0;
  --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
  --bs-btn-active-color: var(--bs-btn-hover-color);
  --bs-btn-active-bg: #5a23c8;
  --bs-btn-active-border-color: #5a23c8;
}

.bd-mode-toggle {
  z-index: 1500;
}

.bd-mode-toggle .dropdown-menu .active .bi {
  display: block !important;
}
</style>

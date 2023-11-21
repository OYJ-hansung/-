<script setup>
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import api from "axios";

const router = useRouter();
const idVerification = computed(() => {
  return userInfo.value.userId.length > 0 ? false : true;
});
const nameVerification = computed(() => {
  return userInfo.value.userName.length > 0 ? false : true;
});
const passwordVerification = computed(() => {
  return userInfo.value.userPassword.length > 0 ? false : true;
});
const userInfo = ref({
  userId: "",
  userName: "",
  userPassword: "",
  emailId: "",
  emailDomain: "",
});

const register = async () => {
  if (
    userInfo.value.userId != "" &&
    userInfo.value.userPassword != "" &&
    userInfo.value.userName != "" &&
    userInfo.value.emailId != "" &&
    userInfo.value.emailDomain != ""
  ) {
    await api
      .post(`${import.meta.env.VITE_VUE_API_URL}/members/`, {
        userId: userInfo.value.userId,
        userName: userInfo.value.userName,
        userPassword: userInfo.value.userPassword,
        emailId: userInfo.value.emailId,
        emailDomain: userInfo.value.emailDomain,
      })
      .then(() => {
        router.push({ path: "/login" });
      })
      .catch((e) => {
        console.log(e);
      });
  } else {
    alert("오류");
  }
};
</script>

<template>
  <body>
    <div class="container d-flex justify-content-center">
      <main>
        <div class="py-5 text-center">
          <h2>회원가입</h2>
        </div>
        <div class="row">
          <div class="col-md-7 col-lg-12">
            <div class="row g-3">
              <div class="col-sm-12 mb-3">
                <label for="firstName" class="form-label">아이디</label>
                <input
                  type="text"
                  class="form-control"
                  id="id"
                  placeholder=""
                  v-model="userInfo.userId"
                />
                <p v-show="idVerification" class="error-message mb-2 mt-2">아이디는 필수입니다.</p>
              </div>
            </div>
            <div class="row g-3">
              <div class="col-sm-12 mb-3">
                <label for="firstName" class="form-label">이름</label>
                <input
                  type="text"
                  class="form-control"
                  id="name"
                  placeholder=""
                  v-model="userInfo.userName"
                />
                <p v-show="nameVerification" class="error-message mb-2 mt-2">이름은 필수입니다.</p>
              </div>
            </div>
            <div class="row g-3">
              <div class="col-sm-12 mb-3">
                <label for="firstName" class="form-label">비밀번호</label>
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  placeholder=""
                  v-model="userInfo.userPassword"
                />
                <p v-show="passwordVerification" class="error-message mb-2 mt-2">
                  비밀번호는 필수입니다.
                </p>
              </div>
            </div>
            <div class="row g-3">
              <div class="col-sm-12">
                <label for="firstName" class="form-label">이메일</label>
                <div class="input-group mb-3">
                  <input
                    type="text"
                    class="form-control"
                    placeholder="아이디"
                    aria-label="emailId"
                    v-model="userInfo.emailId"
                  />
                  <span class="input-group-text">@</span>
                  <input
                    type="text"
                    class="form-control"
                    placeholder="도메인"
                    aria-label="emailDomain"
                    v-model="userInfo.emailDomain"
                  />
                </div>
              </div>
            </div>
            <button class="w-100 btn btn-primary btn-lg" @click="register">회원가입</button>
          </div>
        </div>
      </main>
    </div>
  </body>
</template>

<style scoped>
.error-message {
  color: rgba(231, 78, 78, 0.829);
  font-size: 14px;
}
</style>

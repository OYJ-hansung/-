<script setup>
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import api from "axios";

const allSatisfied = computed(() => {
  if (
    userInfo.value.userId != "" &&
    userInfo.value.userPassword != "" &&
    userInfo.value.userName != ""
  )
    return true;
  else return false;
});
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
                <input type="text" class="form-control" id="id" v-model="userInfo.userId" />
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
            <div class="col-12">
              <div class="d-grid">
                <input
                  v-if="!allSatisfied"
                  class="btn btn-outline-dark align-items-center p-2"
                  type="button"
                  data-bs-toggle="modal"
                  data-bs-target="#registerModal"
                  value="회원가입"
                  disabled
                />
                <input
                  v-if="allSatisfied"
                  class="btn btn-outline-primary align-items-center p-2"
                  type="button"
                  data-bs-toggle="modal"
                  data-bs-target="#registerModal"
                  value="회원가입"
                />
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>

    <div
      class="modal fade"
      id="registerModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content rounded-0">
          <div class="modal-body p-4 px-5">
            <div class="main-content text-center">
              <form action="#">
                <h2 class="my-5">회원가입이 완료되었습니다!</h2>

                <!-- @click="register()" -->
                <div class="col-12">
                  <div class="d-grid">
                    <input
                      class="btn btn-outline-dark align-items-center p-2 mx-4"
                      type="button"
                      @click="register()"
                      data-bs-dismiss="modal"
                      value="확인"
                    />
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<style scoped>
.error-message {
  color: rgba(231, 78, 78, 0.829);
  font-size: 14px;
}
</style>

<script setup>
import { computed, ref, reactive } from "vue";
import { useRouter } from "vue-router";
import { userStore } from "@/stores/userStore";
import api from "axios";

const router = useRouter();
const ustore = userStore();
const isChangePass = ref(false);
const rePass = ref("");
const passwordVerifcationString = ref("");
const user = reactive({
  userId: "",
  userPassword: "",
});
const passwordVerifcation = computed(() => {
  if (isChangePass.value === false) return true;
  if (userInfo.value.userPassword === "") {
    // 비밀번호가 비어있는 경우
    passwordVerifcationString.value = "비밀번호는 필수입니다.";
    return false;
  }
  if (rePass.value != userInfo.value.userPassword) {
    // 비밀번호 2개가 일치하지 않는 경우
    passwordVerifcationString.value = "비밀번호가 일치하지 않습니다.";
    return false;
  }
  return true;
});

const userInfo = ref({
  userId: ustore.userInfo.userId,
  userName: ustore.userInfo.userName,
  userPassword: ustore.userInfo.userPassword,
  emailId: ustore.userInfo.emailId,
  emailDomain: ustore.userInfo.emailDomain,
});

const deleteAccount = async () => {
  // 회원삭제
  await api
    .delete(
      `${import.meta.env.VITE_VUE_API_URL}/members/${ustore.userInfo.userId}`
    )
    .then(async () => {
      await ustore.userLogout(ustore.userInfo.userId);
      router.push({ path: "/" });
    })
    .catch((e) => {
      console.log(e);
    });
};

async function signIn() {
  await ustore.userConfirm(user);
  let token = sessionStorage.getItem("access-token");
  if (ustore.isLogin) {
    await ustore.getUserInfo(token);
    if (ustore.userInfo.isAdmin) {
      toast.success(ustore.userInfo.userId + "관리자 님 환영합니다!", {
        position: toast.POSITION.TOP_RIGHT,
        autoClose: 1000,
      });
    }
    if (click.rememeberId) {
      // 아이디 저장 필요함
      await storeIDByCookie(user.userId);
      await getIDByCookie();
    }
  } else {
    error.message = "아이디 또는 비밀번호가 잘못되었습니다.";
  }
}

const modify = async () => {
  if (
    (isChangePass.value && passwordVerifcation.value) ||
    !isChangePass.value
  ) {
    // 비밀번호 변경했고 조건을 만족했다면, 비밀번호를 변경하지 않았다면
    await api
      .put(`${import.meta.env.VITE_VUE_API_URL}/members/`, {
        userId: userInfo.value.userId,
        userPassword: userInfo.value.userPassword,
        emailId: userInfo.value.emailId,
        emailDomain: userInfo.value.emailDomain,
      })
      .then(() => {
        user.userId = userInfo.value.userId;
        user.userPassword = userInfo.value.userPassword;
        signIn();
        router.push({ path: "/userinfo" });
      })
      .catch((e) => {
        console.log(e);
      });
  } else {
    alert(passwordVerifcationString.value);
  }
};

const changePassword = () => {
  isChangePass.value = true;
  userInfo.value.userPassword = "";
};
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
            <form>
              <div class="row g-3">
                <div class="col-sm-12 mb-3">
                  <label for="firstName" class="form-label">아이디</label>
                  <input
                    type="text"
                    class="form-control"
                    id="id"
                    placeholder=""
                    v-model="userInfo.userId"
                    disabled
                  />
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
                    disabled
                  />
                </div>
              </div>
              <div v-if="!isChangePass" class="row g-3">
                <div class="col-sm-12 mb-3">
                  <label for="firstName" class="form-label">비밀번호</label>
                  <div class="input-group">
                    <input
                      type="text"
                      class="form-control"
                      placeholder="******"
                      disabled
                    />

                    <input
                      class="btn btn-outline-dark align-items-center p-2 mx-1"
                      type="button"
                      @click="changePassword()"
                      value="변경하기"
                    />
                  </div>
                </div>
              </div>

              <div v-if="isChangePass" class="row g-3">
                <div class="col-sm-12 mb-3">
                  <label for="firstName" class="form-label"
                    >새로운 비밀번호</label
                  >
                  <input
                    type="password"
                    class="form-control"
                    id="name"
                    placeholder=""
                    v-model="userInfo.userPassword"
                  />
                  <p
                    v-show="
                      !passwordVerifcation &&
                      passwordVerifcationString === '비밀번호는 필수입니다.'
                    "
                    class="error-message mb-2 mt-2"
                  >
                    {{ passwordVerifcationString }}
                  </p>
                </div>
              </div>

              <div v-if="isChangePass" class="row g-3">
                <div class="col-sm-12 mb-3">
                  <label for="firstName" class="form-label"
                    >비밀번호 확인</label
                  >
                  <input
                    type="password"
                    class="form-control"
                    id="name"
                    placeholder=""
                    v-model="rePass"
                  />
                  <p
                    v-show="
                      !passwordVerifcation &&
                      passwordVerifcationString ===
                        '비밀번호가 일치하지 않습니다.' &&
                      rePass.length != 0
                    "
                    class="error-message mb-2 mt-2"
                  >
                    {{ passwordVerifcationString }}
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
                  <div class="row">
                    <div class="col-6">
                      <div class="d-grid">
                        <input
                          v-if="!passwordVerifcation"
                          class="btn btn-dark align-items-center p-2"
                          type="button"
                          @click="modify()"
                          value="저장"
                          disabled
                        />
                        <input
                          v-if="passwordVerifcation"
                          class="btn btn-outline-primary align-items-center p-2"
                          type="button"
                          data-bs-toggle="modal"
                          data-bs-target="#confirmChangeModal"
                          value="저장"
                        />
                      </div>
                    </div>

                    <div class="col-6">
                      <div class="d-grid">
                        <input
                          class="btn btn-outline-danger align-items-center p-2"
                          type="button"
                          data-bs-toggle="modal"
                          data-bs-target="#deleteModal"
                          value="회원탈퇴"
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </main>
    </div>

    <div
      class="modal fade"
      id="confirmChangeModal"
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
                <h3 class="my-5">회원정보수정이 완료되었습니다!</h3>
                <div class="col-12">
                  <div class="d-grid">
                    <input
                      class="btn btn-outline-dark align-items-center p-2 mx-4"
                      type="button"
                      @click="modify()"
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

    <div
      class="modal fade"
      id="deleteModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content rounded-0">
          <div class="modal-body p-4 px-5">
            <div class="main-content text-center">
              <a
                href="#"
                class="close-btn"
                data-dismiss="modal"
                aria-label="Close"
              >
                <span aria-hidden="true"
                  ><span class="icon-close2"></span
                ></span>
              </a>

              <div class="warp-icon mb-4">
                <span class="icon-lock2"></span>
              </div>
              <form action="#">
                <h4 class="mb-4">정말 탈퇴하시겠습니까?</h4>

                <div class="row mt-4">
                  <div class="d-grid col">
                    <input
                      class="btn btn-outline-danger align-items-center p-2 mx-1"
                      type="button"
                      @click="deleteAccount()"
                      data-bs-dismiss="modal"
                      value="확인"
                    />
                  </div>
                  <div class="d-grid col">
                    <input
                      class="btn btn-outline-dark align-items-center p-2 mx-1"
                      type="button"
                      data-bs-dismiss="modal"
                      value="취소"
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

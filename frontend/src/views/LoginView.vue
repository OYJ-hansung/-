<script setup>
import { reactive, ref } from "vue";
import { userStore } from "@/stores/userStore";
import { useRouter } from "vue-router";
import { toast } from "vue3-toastify";
import api from "axios";
import "vue3-toastify/dist/index.css";
const router = useRouter();
const ustore = userStore();
const findUserId = ref("");

const click = reactive({
  rememeberId: false,
});

const user = reactive({
  userId: "",
  userPassword: "",
});

const error = reactive({
  message: "",
});

const findPassword = async () => {
  await api
    .get(`${import.meta.env.VITE_VUE_API_URL}/members/login/${findUserId.value}`)
    .then(({ data }) => {
      alert("등록된 이메일로 비밀번호를 발송하였습니다.");
      alert(data);
    })
    .catch((e) => {
      console.log(e);
    });
};

const register = () => {
  router.push({ name: "register" });
};

function updateData(data, key) {
  user[key] = data;
}
//쿠키에서 저장된 아이디가 있으면 가져오는 함수
async function getIDByCookie() {
  const cookies = document.cookie;
  const cookieArray = cookies.split("; ");
  for (let i = 0; i < cookieArray.length; i++) {
    const cookie = cookieArray[i].split("=");
    const key = cookie[0];
    const value = cookie[1];

    if (key === "userId") {
      user.userId = value;
    }
  }
}

//아이디를 쿠키에 저장하는 함수
async function storeIDByCookie(userId) {
  const cookies = document.cookie;
  const cookieArray = cookies.split("; ");
  document.cookie = `userId=${userId}`;
}

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
    } else {
      router.push({ path: "/" });
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
</script>
<template>
  <section class="bg-light p-3 p-md-4 p-xl-5">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-12 col-xxl-11">
          <div class="card border-light-subtle shadow-sm">
            <div class="row g-0">
              <div class="col-12 col-md-6">
                <img
                  class="img-fluid rounded-start w-100 h-100 object-fit-cover"
                  loading="lazy"
                  src="https://images.unsplash.com/photo-1500835556837-99ac94a94552?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  alt=""
                />
              </div>
              <div class="col-12 col-md-6 d-flex align-items-center justify-content-center">
                <div class="col-12 col-lg-11 col-xl-10">
                  <div class="card-body p-3 p-md-4 p-xl-5">
                    <div class="row">
                      <div class="col-12">
                        <div class="mb-5">
                          <div class="text-center mb-4">
                            <a href="#!"> </a>
                          </div>
                          <h1 class="fw-light d-flex justify-content-center mt-5">로그인</h1>
                        </div>
                      </div>
                    </div>
                    <form action="#!">
                      <div class="row gy-3 overflow-hidden">
                        <div class="col-12">
                          <div class="form-floating mb-3">
                            <input
                              type="text"
                              class="form-control"
                              id="floatingInput"
                              placeholder="Id"
                              v-model="user.userId"
                            />
                            <label for="email" class="form-label">아이디</label>
                          </div>
                        </div>
                        <div class="col-12">
                          <div class="form-floating mb-3">
                            <input
                              type="password"
                              class="form-control"
                              id="floatingPassword"
                              placeholder="Password"
                              v-model="user.userPassword"
                              required
                            />

                            <label for="password" class="form-label">비밀번호</label>
                          </div>
                        </div>

                        <div class="col-12">
                          <div class="d-grid">
                            <input
                              class="btn btn-outline-dark align-items-center p-2"
                              type="button"
                              @click="signIn()"
                              value="로그인"
                            />
                          </div>
                        </div>
                      </div>
                    </form>
                    <div class="row">
                      <div class="col-12">
                        <div
                          class="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-center mt-4"
                        >
                          <label class="link-secondary text-decoration-none" @click="register()"
                            >회원가입</label
                          >

                          <label
                            class="link-secondary text-decoration-none"
                            data-bs-toggle="modal"
                            data-bs-target="#findPasswordModal"
                          >
                            비밀번호 찾기
                          </label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div
      class="modal fade"
      id="findPasswordModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content rounded-0">
          <div class="modal-body p-4 px-5">
            <div class="main-content text-center">
              <a href="#" class="close-btn" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true"><span class="icon-close2"></span></span>
              </a>

              <div class="warp-icon mb-4">
                <span class="icon-lock2"></span>
              </div>
              <form action="#">
                <h1 class="mb-4">비밀번호 찾기</h1>
                <p class="mb-3">비밀번호를 찾을 아이디를 입력해주세요.</p>
                <div class="form-group mb-4">
                  <input
                    type="text"
                    class="form-control text-center"
                    placeholder=""
                    v-model="findUserId"
                  />
                </div>

                <div class="row mt-4">
                  <div class="d-grid col">
                    <input
                      class="btn btn-outline-dark align-items-center p-2 mx-1"
                      type="button"
                      @click="findPassword()"
                      data-bs-dismiss="modal"
                      value="확인"
                    />
                  </div>
                  <div class="d-grid col">
                    <input
                      class="btn btn-outline-danger align-items-center p-2 mx-1"
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
  </section>
</template>

<style>
.form-signin {
  max-width: 700px;
  padding: 1rem;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
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

.outer-div {
  width: 400px;
  height: 60px;
}

.inner-div {
  width: 200px;
  height: 60px;
  display: inline-block;
}
</style>

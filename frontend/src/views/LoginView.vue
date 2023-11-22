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
  console.log(click);
  user[key] = data;
  console.log(user);
}
//쿠키에서 저장된 아이디가 있으면 가져오는 함수
async function getIDByCookie() {
  const cookies = document.cookie;
  const cookieArray = cookies.split("; ");
  console.log(cookieArray);
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
  console.log("로그인 정보: " + user.userPassword);
  await ustore.userConfirm(user);
  let token = sessionStorage.getItem("access-token");
  console.log("1. confirm() token >> " + token);
  if (ustore.isLogin) {
    await ustore.getUserInfo(token);
    console.log("4. confirm() userInfo :: ", ustore.userInfo);
    if (ustore.userInfo.isAdmin) {
      toast.success(ustore.userInfo.userId + "관리자 님 환영합니다!", {
        position: toast.POSITION.TOP_RIGHT,
        autoClose: 1000,
      });
    } else {
      // toast.success(ustore.userInfo.userId + "님 환영합니다!", {
      //   position: toast.POSITION.TOP_RIGHT,
      //   autoClose: 1000,
      // });
      router.push({ path: "/" });
    }
    if (click.rememeberId) {
      // 아이디 저장 필요함
      await storeIDByCookie(user.userId);
      await getIDByCookie();
    }
    console.log("ustore userInfo: " + ustore.isLogin);
    // router.push({ name: "home" }); // 메인 페이지로 이동
  } else {
    error.message = "아이디 또는 비밀번호가 잘못되었습니다.";
  }
}
</script>
<template>
  <div class="dropdown position-fixed bottom-0 end-0 mb-3 me-3 bd-mode-toggle">
    <ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="bd-theme-text">
      <li>
        <button
          type="button"
          class="dropdown-item d-flex align-items-center"
          data-bs-theme-value="light"
          aria-pressed="false"
        >
          <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em">
            <use href="#sun-fill"></use>
          </svg>
          Light
          <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
        </button>
      </li>
      <li>
        <button
          type="button"
          class="dropdown-item d-flex align-items-center"
          data-bs-theme-value="dark"
          aria-pressed="false"
        >
          <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em">
            <use href="#moon-stars-fill"></use>
          </svg>
          Dark
          <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
        </button>
      </li>
      <li>
        <button
          type="button"
          class="dropdown-item d-flex align-items-center active"
          data-bs-theme-value="auto"
          aria-pressed="true"
        >
          <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em">
            <use href="#circle-half"></use>
          </svg>
          Auto
          <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
        </button>
      </li>
    </ul>
  </div>

  <form class="form-signin w-100 m-auto" @submit.prevent>
    <h1 class="h3 mb-3 fw-normal">로그인</h1>

    <div class="form-floating">
      <input
        type="text"
        class="form-control"
        id="floatingInput"
        placeholder="Id"
        v-model="user.userId"
      />
      <label for="floatingInput">아이디</label>
    </div>
    <div class="form-floating">
      <input
        type="password"
        class="form-control"
        id="floatingPassword"
        placeholder="Password"
        v-model="user.userPassword"
      />
      <label for="floatingPassword">비밀번호</label>
    </div>

    <div class="row g-3 outer-div">
      <div class="inner-div">
        <label
          class="form-check-label text-decoration-underline"
          for="flexCheckDefault"
          data-bs-toggle="modal"
          data-bs-target="#findPasswordModal"
        >
          비밀번호 찾기</label
        >&nbsp; | &nbsp;

        <label
          class="form-check-label text-decoration-underline"
          for="flexCheckDefault"
          @click="register()"
        >
          회원가입</label
        >
      </div>
    </div>

    <div class="row g-3">
      <div class="col-sm-12">
        <button class="btn btn-primary w-100 py-2" type="submit" @click="signIn()">로그인</button>
      </div>
    </div>
  </form>
  <div
    class="modal fade"
    id="findPasswordModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">비밀번호 찾기</h1>

          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="modal-body">
          <div class="form-floating">
            <input
              type="text"
              class="form-control"
              id="floatingInput"
              placeholder="Id"
              v-model="findUserId"
            />
            <label for="floatingInput">아이디</label>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니요</button>
          <button
            type="button"
            class="btn btn-primary"
            @click="findPassword()"
            data-bs-dismiss="modal"
          >
            네
          </button>
        </div>
      </div>
    </div>
  </div>
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
  /* background: #3c40c6; */
}
.inner-div {
  /* margin-left: 50%; */
  width: 200px;
  height: 60px;
  /* background: #ff5e57; */
  display: inline-block;
}
</style>

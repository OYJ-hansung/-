import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import BoardDetailView from "../views/BoardDetailView.vue";
import PlanListView from "../views/PlanListView.vue";
import PlanDetailView from "../views/PlanDetailView.vue";
import PlanAddView from "../views/PlanAddView.vue";
import LoginView from "../views/LoginView.vue";
import RegisterView from "../views/RegisterView.vue";
import MyPlanView from "../views/MyPlanView.vue";
import UserInfoView from "../views/UserInfoView.vue";
import { toast } from "vue3-toastify";
import { userStore } from "@/stores/userStore";
import "vue3-toastify/dist/index.css";

const onlyAuthUser = async (to, from, next) => {
  const store = userStore(); // 안에서 pinia를 부른다. Auth만 사용하고 나머지는 다 버린다.
  const checkUserInfo = store.checkUserInfo;
  const checkToken = store.checkToken;

  let token = sessionStorage.getItem("accestoken");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크");
    await store.getUserInfo(token);
  }
  if (!checkToken || checkUserInfo === null) {
    store.isLogin = false;

    // 비동기는 해놨는데.. 뭔가 좀 허졉한것 같아서 주석처리해둠 - 영주
    // function a() {
    //   return new Promise((resolve) => {
    //     toast.error("로그인이 필요합니다.", {
    //       position: toast.POSITION.TOP_CENTER,
    //       theme: "colored",
    //       autoClose: 1000,
    //     });
    //     setTimeout(() => {
    //       resolve();
    //     }, 1500);
    //   });
    // }

    // function test() {
    //   a().then(() => {
    //     router.push({ name: "login" });
    //   });
    // }
    // test();

    alert("로그인이 필요합니다.");
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/about",
      name: "about",
      component: () => import("../views/AboutView.vue"),
    },
    {
      path: "/boarddetail/:no",
      name: "boarddetail",
      component: BoardDetailView,
    },
    // 여기부터 관통프로젝트
    {
      path: "/planlist",
      beforeEnter: onlyAuthUser,
      name: "planlist",
      component: PlanListView,
    },
    {
      path: "/plandetail/:id",
      beforeEnter: onlyAuthUser,
      name: "plandetail",
      component: PlanDetailView,
    },
    {
      path: "/addplan",
      name: "addplan",
      component: PlanAddView,
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView,
    },
    {
      path: "/myplan",
      name: "myplan",
      component: MyPlanView,
    },
    {
      path: "/userinfo",
      name: "userinfo",
      component: UserInfoView,
    },
  ],
});

export default router;

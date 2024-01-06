import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import PlanListView from "../views/PlanListView.vue";
import PlanDetailView from "../views/PlanDetailView.vue";
import PlanAddView from "../views/PlanAddView.vue";
import PlanEditView from "../views/PlanEditView.vue";
import LoginView from "../views/LoginView.vue";
import RegisterView from "../views/RegisterView.vue";
import MyPlanView from "../views/MyPlanView.vue";
import UserInfoView from "../views/UserInfoView.vue";
import AttrListView from "../views/AttrListView.vue";
import { userStore } from "@/stores/userStore";
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
      path: "/editplan/:id",
      name: "editplan",
      component: PlanEditView,
    },
    {
      path: "/userinfo",
      name: "userinfo",
      component: UserInfoView,
    },
    {
      path: "/attrlist",
      name: "attrlist",
      component: AttrListView,
    },
  ],
});

export default router;

<script setup>
import { useRoute } from "vue-router";
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import api from "axios";
import PlanTimeLine from "../components/board/PlanTimeLine.vue";
import VKakaoMapDetail from "@/components/common/VKakaoMapDetail.vue";
import { searchStore } from "@/stores/planListStore";
import { userStore } from "@/stores/userStore";

const sstore = searchStore();
const ustore = userStore();
const router = useRouter();
const route = useRoute();

const planId = ref(route.params.id);
const plan = ref({});
const shared = ref("");
const attractions = ref([]);
const isMyPlan = ref();

const getDetail = async () => {
  console.log("planId: ", planId.value);
  await api
    .get(`${import.meta.env.VITE_VUE_API_URL}/plan/${planId.value}`)
    .then(({ data }) => {
      console.log("data: ", data);
      plan.value = data;
      attractions.value = plan.value.attrInfoList;
      shared.value = plan.value.shared;
      isMyPlan.value = plan.value.userId == ustore.userInfo.userId;
      reload.value = !reload.value;
    })
    .catch((e) => {
      console.log(e);
    });
};

const editPlan = () => {
  router.push({ name: "editplan", params: { id: plan.planId } });
};

const deletePlan = async () => {
  await api
    .delete(`${import.meta.env.VITE_VUE_API_URL}/plan/${planId.value}`)
    .then(() => {
      router.push({ path: "/planlist", params: { planId: planId } });
    })
    .catch((e) => {
      console.log(e);
    });
};

const shareMyPlan = async () => {
  await api
    .put(`${import.meta.env.VITE_VUE_API_URL}/plan/${planId.value}`, {
      planId: planId.value,
    })
    .then(({ data }) => {
      shared.value = data;
      console.log("shared 값 ");
      console.log(shared.value);
    })
    .catch((e) => {
      console.log(e);
    });
};
onMounted(() => {
  getDetail();
});
</script>
<template>
  <div class="row g-5">
    <div class="col-md-12">
      <h3 class="pb-4 mb-4 border-bottom">내 마음대로 여행코스!!!</h3>
      <div class="row g-5">
        <div class="col-md-12">
          <div class="card shadow-sm">
            <div class="card-body">
              <div v-if="isMyPlan">
                <button class="btn btn-success rounded-pill m-1" style="float: right" type="button"
                  @click="shareMyPlan()">
                  <div v-if="shared == 0">공유하기</div>
                  <div v-if="shared == 1">공유 취소하기</div>
                </button>
              </div>

              <div class="row g-5">
                <div class="col-md-4">
                  <img :src="plan.img" class="mt-3 img-fluid mx-auto d-block" alt="Responsive image" />
                </div>
                <div class="col-md-8">
                  <h2 class="card-title mx-auto d-block mt-1 mb-2">
                    {{ plan.planName }}
                  </h2>

                  <h6 class="card-subtitle mx-auto d-block mb-3">[{{ plan.userId }}]님</h6>

                  <span class="bold-text">- 여행 시작 날짜</span>
                  {{ new Date(plan.startDate).toLocaleDateString() }}
                  <br />
                  <span class="bold-text">- 여행 마지막 날짜</span>
                  {{ new Date(plan.endDate).toLocaleDateString() }}

                  <h4 class="box-title mt-5">[ 세부 내용 ]</h4>
                  <p class="card-text mb-5">
                    {{ plan.planDetail }}
                  </p>

                  <h4>[ 태그 ]</h4>
                  <div class="mb-4 row" style="float: left; justify-content: space-between; display: flex"
                    v-for="(tag, index) in plan.tagList" :key="index">
                    <div class="col-md-12">
                      <button type="button" class="btn w-btn w-btn-tag"># {{ tag.tagName }}</button>
                    </div>
                  </div>
                </div>
              </div>

              <div class="row g-3">
                <div class="col-md-8">
                  <h5>지도</h5>
                  <VKakaoMapDetail :attractions="attractions" />
                </div>

                <div class="col-md-4">
                  <h5>타임라인</h5>
                  <plan-time-line :attractions="attractions" isDetail="true" />
                </div>
              </div>



              <div class="row mt-5 justify-content-end" v-if="isMyPlan">
                <div class="col-3">
                  <div class="d-grid">
                    <input class="btn btn-outline-primary align-items-center p-2 mx-5" type="button" @click="editPlan()"
                      value="수정" />
                  </div>
                </div>

                <div class="col-3">
                  <div class="d-grid">
                    <input class="btn btn-outline-danger align-items-center p-2 mx-5 mb-3" type="button"
                      data-bs-toggle="modal" data-bs-target="#deleteModal" value="삭제" />
                  </div>
                </div>
              </div>









              <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
                aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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
                          <h4 class="mb-4">정말 삭제하시겠습니까?</h4>

                          <div class="row mt-4">
                            <div class="d-grid col">
                              <input class="btn btn-outline-danger align-items-center p-2 mx-1" type="button"
                                @click="deletePlan()" data-bs-dismiss="modal" value="확인" />
                            </div>
                            <div class="d-grid col">
                              <input class="btn btn-outline-dark align-items-center p-2 mx-1" type="button"
                                data-bs-dismiss="modal" value="취소" />
                            </div>
                          </div>
                        </form>
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
</template>

<style scoped>
body {
  margin-top: 20px;
  color: #bcd0f7;
  background: #1a233a;
}

.bold-text {
  font-weight: bold;
}

.w-btn {
  position: relative;
  display: inline-block;
  padding: 7px 22px;
  margin: 7px;
  border-radius: 25px;
  /* box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2); */
  text-decoration: none;
  font-weight: 600;
  /* transition: 0.25s; */
}

.w-btn-aqua {
  background-color: white;
  border-color: rgb(67, 67, 232);
  color: rgb(67, 67, 232);
}

.w-btn-red {
  background-color: white;
  border-color: crimson;
  color: crimson;
}

.w-btn-tag {
  background-color: white;
  border-width: 2px;
  border-color: #f4bd19;
  color: #f4bd19;
}

.timeline {
  position: relative;
  /* background: #272e48; */
  /* -webkit-border-radius: 4px; */
  /* -moz-border-radius: 4px; */
  border-radius: 4px;
  /* padding: 1rem; */
  /* margin: 0 auto 1rem auto; */
  overflow: hidden;
}

.timeline-row:after {
  content: "";
  position: absolute;
  top: 0;
  right: 90%;
  margin-right: -2px;
  border-left: 2px dashed #4b546f;
  height: 100%;
  display: block;
}

.timeline-row {
  /* padding-left: 50%; */
  position: relative;
  /* margin-bottom: 30px; */
}
</style>

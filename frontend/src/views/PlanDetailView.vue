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
const reload = ref(false);
const isMyPlan = ref();

const getDetail = async () => {
  await api
    .get(`${import.meta.env.VITE_VUE_API_URL}/plan/${planId.value}`)
    .then(({ data }) => {
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
  reload.value = !reload.value;
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
                <button class="w-btn w-btn-blue" type="button" @click="shareMyPlan()">
                  <div v-if="shared == 0">공유하기</div>
                  <div v-if="shared == 1">공유 취소하기</div>
                </button>
              </div>

              <div class="row g-5">
                <div class="col-md-4">
                  <img
                    :src="plan.img"
                    class="mt-3 img-fluid mx-auto d-block"
                    alt="Responsive image"
                  />
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
                  <div
                    class="mb-4 row"
                    style="float: left; justify-content: space-between; display: flex"
                    v-for="(tag, index) in plan.tagList"
                    :key="index"
                  >
                    <div class="col-md-12">
                      <button type="button" class="btn btn-primary rounded-pill m-1">
                        # {{ tag.tagName }}
                      </button>
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

              <div class="m-2 p-1 row justify-content-end">
                <div v-if="isMyPlan">
                  <button
                    href="#"
                    @click="editPlan()"
                    type="button"
                    class="btn btn-primary float-right m-2 col-1"
                  >
                    수정
                  </button>

                  <button
                    type="button"
                    class="btn btn-danger float-right m-2 col-1"
                    data-bs-toggle="modal"
                    data-bs-target="#deleteModal"
                  >
                    삭제
                  </button>
                </div>
              </div>

              <!-- Delete Modal -->
              <div
                class="modal fade"
                id="deleteModal"
                tabindex="-1"
                aria-labelledby="exampleModalLabel"
                aria-hidden="true"
              >
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h1 class="modal-title fs-5" id="exampleModalLabel">삭제하기</h1>
                      <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                      ></button>
                    </div>
                    <div class="modal-body">정말 삭제하시겠습니까?</div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        아니요
                      </button>
                      <button
                        type="button"
                        class="btn btn-primary"
                        @click="deletePlan()"
                        data-bs-dismiss="modal"
                      >
                        네
                      </button>
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
  border: none;
  display: inline-block;
  padding: 15px 30px;
  border-radius: 15px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
}
.w-btn-blue {
  background-color: #6aafe6;
  color: #d4dfe6;
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

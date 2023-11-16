<script setup>
import { useRoute } from "vue-router";
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import api from "axios";
import PlanTimeLine from "../components/board/PlanTimeLine.vue";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import TestView from "./TestView.vue";

const router = useRouter();
const route = useRoute();

const planId = ref(route.params.id);
const plan = ref({});
const attractions = ref([]);
const planNameEdit = ref("");
const planDetailEdit = ref(""); // 변경된 데이터
const startDateEdit = ref(""); // 변경된 데이터
const endDateEdit = ref(""); // 변경된 데이터

// 수정
const isEdit = ref(false);
const editPlan = () => {
  if (!isEdit.value) {
    // 수정
    isEdit.value = true;
    planNameEdit.value = plan.value.planName;
    planDetailEdit.value = plan.value.planDetail;
    startDateEdit.value = plan.value.startDate;
    endDateEdit.value = plan.value.endDate;
  } else {
    // 완료
    isEdit.value = false;
    editDetail();
  }
};

const editDetail = async () => {
  await api
    .put(`http://localhost:8090/trip/plan`, {
      planId: planId.value,
      planName: planNameEdit.value,
      startDate: startDateEdit.value,
      endDate: endDateEdit.value,
      planDetail: planDetailEdit.value,
    })
    .then(() => {
      getDetail();
    })
    .catch((e) => {
      console.log(e);
    });
};

const getDetail = async () => {
  await api
    .get(`http://localhost:8090/trip/plan/${planId.value}`)
    .then(({ data }) => {
      plan.value = data;
      console.log("넘어올때");
      attractions.value = plan.value.attrInfoList;
      console.log(attractions.value);
    })
    .catch((e) => {
      console.log(e);
    });
};

const deletePlan = async () => {
  await api
    .delete(`http://localhost:8090/trip/plan/${planId.value}`)
    .then(() => {
      router.push({ path: "/planlist", params: { planId: planId } });
    })
    .catch((e) => {
      console.log(e);
    });
};

// onMounted(async () => {
//   getDetail();
// });
getDetail();
</script>
<template>
  <div class="row g-5">
    <div class="col-md-12">
      <h3 class="pb-4 mb-4 fst-italic border-bottom">내 마음대로 여행코스!!!</h3>
      <div class="row g-5">
        <div class="col-md-12">
          <div class="card shadow-sm">
            <div class="card-body">
              <div class="row g-5">
                <div class="col-md-4">
                  <img
                    :src="plan.img"
                    class="mt-3 img-fluid mx-auto d-block"
                    alt="Responsive image"
                  />
                </div>
                <div class="col-md-8">
                  <h2 v-if="!isEdit" class="card-title mx-auto d-block mt-1 mb-2">
                    {{ plan.planName }}
                  </h2>
                  <input
                    v-if="isEdit"
                    type="text"
                    class="form-control"
                    id="planName"
                    :placeholder="planNameEdit"
                    v-model="planNameEdit"
                  />

                  <h6 class="card-subtitle mx-auto d-block mb-3">[{{ plan.userId }}]님</h6>
                  <figcaption v-if="!isEdit" class="blockquote-footer mt-3">
                    시작일: {{ plan.startDate }}
                  </figcaption>
                  <div>
                    <input
                      v-if="isEdit"
                      type="text"
                      class="form-control"
                      id="startDate"
                      :placeholder="startDateEdit"
                      v-model="startDateEdit"
                    />
                  </div>
                  <figcaption v-if="!isEdit" class="blockquote-footer">
                    마지막일: {{ plan.endDate }}
                  </figcaption>
                  <input
                    v-else
                    type="text"
                    class="form-control"
                    id="endDate"
                    :placeholder="endDateEdit"
                    v-model="endDateEdit"
                  />
                  <h4 class="box-title mt-5">[ 세부 내용 ]</h4>
                  <p v-if="!isEdit" class="card-text mb-5">
                    {{ plan.planDetail }}
                  </p>
                  <textarea
                    v-else
                    class="form-control mb-5"
                    id="planDetail"
                    rows="3"
                    v-model="planDetailEdit"
                  >
                                      {{ plan.planDetail }}
                                    </textarea
                  >
                </div>
              </div>

              <div class="timeline">
                <div
                  class="timeline-row"
                  v-for="(attrInfo, index) in plan.attrInfoList"
                  :key="index"
                >
                  <plan-time-line :attrInfo="attrInfo" />
                </div>
              </div>

              <VKakaoMap :attractions="attractions" />

              <div class="m-2 p-1 row justify-content-end">
                <button
                  v-if="!isEdit"
                  @click="editPlan()"
                  type="button"
                  class="btn btn-primary float-right m-2 col-1"
                >
                  수정
                </button>
                <button
                  v-else
                  type="button"
                  class="btn btn-primary float-right m-2 col-1"
                  data-bs-toggle="modal"
                  data-bs-target="#editModal"
                >
                  완료
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

              <!-- Edit Modal -->
              <div
                class="modal fade"
                id="editModal"
                tabindex="-1"
                aria-labelledby="exampleModalLabel"
                aria-hidden="true"
              >
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h1 class="modal-title fs-5" id="exampleModalLabel">수정하기</h1>
                      <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                      ></button>
                    </div>
                    <div class="modal-body">정말 수정하시겠습니까?</div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        아니요
                      </button>
                      <button
                        type="button"
                        class="btn btn-primary"
                        @click="editPlan()"
                        data-bs-dismiss="modal"
                      >
                        네
                      </button>
                    </div>
                  </div>
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

.timeline {
  position: relative;
  /* background: #272e48; */
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
  padding: 5rem;
  margin: 0 auto 1rem auto;
  overflow: hidden;
}

.timeline:after {
  content: "";
  position: absolute;
  top: 0;
  left: 50%;
  margin-left: -2px;
  border-right: 2px dashed #4b546f;
  height: 100%;
  display: block;
}

.timeline-row {
  padding-left: 50%;
  position: relative;
  margin-bottom: 30px;
}

.timeline-row .timeline-time {
  position: absolute;
  right: 50%;
  top: 15px;
  text-align: right;
  margin-right: 20px;
  color: #272e48;
  font-size: 1.5rem;
}

.timeline-row .timeline-time small {
  display: block;
  font-size: 0.8rem;
}

.timeline-row .timeline-content {
  border: solid #272e48;
  position: relative;
  padding: 20px 30px;
  background: #ffffff;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  text-align: center;
}

.timeline-row .timeline-content:after {
  content: "";
  position: absolute;
  top: 20px;
  height: 16px;
  width: 16px;
  background: #1a233a;
}

.timeline-row .timeline-content:before {
  content: "";
  position: absolute;
  top: 20px;
  right: -49px;
  width: 20px;
  height: 20px;
  -webkit-border-radius: 100px;
  -moz-border-radius: 100px;
  border-radius: 100px;
  z-index: 10;
  background: #272e48;
  border: 2px dashed #4b546f;
}

.timeline-row .timeline-content h4 {
  margin: 0 0 20px 0;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  line-height: 150%;
}

.timeline-row .timeline-content p {
  margin-bottom: 30px;
  line-height: 150%;
}

.timeline-row .timeline-content i {
  font-size: 1.2rem;
  line-height: 100%;
  padding: 15px;
  -webkit-border-radius: 100px;
  -moz-border-radius: 100px;
  border-radius: 100px;
  background: #272e48;
  margin-bottom: 10px;
  display: inline-block;
}

.timeline-row .timeline-content .thumbs {
  margin-bottom: 20px;
  display: flex;
}

.timeline-row .timeline-content .thumbs img {
  margin: 5px;
  /* max-width: px; */
}

.timeline-row .timeline-content .badge {
  color: #ffffff;
  background: linear-gradient(120deg, #00b5fd 0%, #0047b1 100%);
}

.timeline-row:nth-child(even) .timeline-content {
  margin-left: 40px;
  text-align: left;
}

.timeline-row:nth-child(even) .timeline-content:after {
  left: -8px;
  right: initial;
  border-bottom: 0;
  border-left: 0;
  transform: rotate(-135deg);
}

.timeline-row:nth-child(even) .timeline-content:before {
  left: -52px;
  right: initial;
}

.timeline-row:nth-child(odd) {
  padding-left: 0;
  padding-right: 50%;
}

.timeline-row:nth-child(odd) .timeline-time {
  right: auto;
  left: 50%;
  text-align: left;
  margin-right: 0;
  margin-left: 20px;
}

.timeline-row:nth-child(odd) .timeline-content {
  margin-right: 40px;
}

.timeline-row:nth-child(odd) .timeline-content:after {
  right: -8px;
  border-left: 0;
  border-bottom: 0;
  transform: rotate(45deg);
}

@media (max-width: 992px) {
  .timeline {
    padding: 15px;
  }

  .timeline:after {
    border: 0;
  }

  .timeline .timeline-row:nth-child(odd) {
    padding: 0;
  }

  .timeline .timeline-row:nth-child(odd) .timeline-time {
    position: relative;
    top: 0;
    left: 0;
    margin: 0 0 10px 0;
  }

  .timeline .timeline-row:nth-child(odd) .timeline-content {
    margin: 0;
  }

  .timeline .timeline-row:nth-child(odd) .timeline-content:before {
    display: none;
  }

  .timeline .timeline-row:nth-child(odd) .timeline-content:after {
    display: none;
  }

  .timeline .timeline-row:nth-child(even) {
    padding: 0;
  }

  .timeline .timeline-row:nth-child(even) .timeline-time {
    position: relative;
    top: 0;
    left: 0;
    margin: 0 0 10px 0;
    text-align: left;
  }

  .timeline .timeline-row:nth-child(even) .timeline-content {
    margin: 0;
  }

  .timeline .timeline-row:nth-child(even) .timeline-content:before {
    display: none;
  }

  .timeline .timeline-row:nth-child(even) .timeline-content:after {
    display: none;
  }
}
</style>

<script setup>
import { useRoute } from "vue-router";
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import api from "axios";
import PlanTimeLine from "../components/board/PlanTimeLine.vue";
import VKakaoMapEdit from "@/components/common/VKakaoMapEdit.vue";
import { searchStore } from "@/stores/planListStore";
import { userStore } from "@/stores/userStore";

const sstore = searchStore();
const ustore = userStore();
const router = useRouter();
const route = useRoute();
const searchWord = ref("");
const searchResult = ref([]);
const markPlace = ref({});
const planId = ref(route.params.id);
const plan = ref({});
const planName = ref("");
const planDetail = ref("");
const startDate = ref("");
const endDate = ref("");
const attrInfoList = ref([]);
const tagList = ref([]);
const tagContent = ref("");
const tagSearchResult = ref([]);
const sameTag = ref(false);

// 날짜
const inputDate = ref({
  start: null,
  end: null,
});

const getFormatDate = (date) => {
  const YYYY = String(date.getFullYear());
  const MM = String(
    date.getMonth() + 1 >= 10
      ? date.getMonth() + 1
      : "0" + (date.getMonth() + 1)
  );
  const dd = String(
    date.getDate() >= 10 ? date.getDate() : "0" + date.getDate()
  );
  return YYYY + "-" + MM + "-" + dd;
};

const addPlace = (place) => {
  attrInfoList.value.push(place);
  searchResult.value = [];
  console.log(plan.value.attrInfo);
};

const markPlaceOnMap = (place) => {
  markPlace.value = place;
};

const deletePlace = function (index) {
  attrInfoList.value.splice(index, 1);
};

const searchAttraction = async () => {
  await api
    .post(
      `${import.meta.env.VITE_VUE_API_URL}/attraction/search`,
      searchWord.value,
      {
        headers: { "Content-Type": "application/text" },
      }
    )
    .then(({ data }) => {
      searchResult.value = data;
      console.log(searchResult.value);
    })
    .catch((e) => {
      console.log(e);
    });
};
const hasSearchResult = computed(() => {
  return searchResult.value.length > 0 ? true : false;
});
const deleteTag = (tag) => {
  var planFilter = [];
  planFilter = tagList.value.filter((t) => t != tag);
  tagList.value = planFilter;
  console.log("태그 삭제됨");
  console.log(tagList.value);
};

const addTag = () => {
  tagList.value.push({ tagId: -1, tagName: tagContent.value });
  tagContent.value = "";
  tagSearchResult.value = "";
  console.log("addTag 호출");
  console.log(tagList.value);
};

const addTagLike = (tag) => {
  tagList.value.push({ tagId: tag.tagId, tagName: tag.tagName });
  tagContent.value = "";
  tagSearchResult.value = "";
  console.log("addTagLike 호출");
  console.log(tagList.value);
};

const onTagInput = (event) => {
  tagContent.value = event.target.value;
  searchTag();
};

async function searchTag() {
  await api
    .get(`${import.meta.env.VITE_VUE_API_URL}/plan/tag/${tagContent.value}`)
    .then(({ data }) => {
      sameTag.value = false;
      tagSearchResult.value = data;
      tagSearchResult.value.forEach((tag) => {
        if (tag.tagName === tagContent.value) {
          console.log("완전 같은 태그");
          sameTag.value = true;
        }
      });
    })
    .catch((e) => {
      console.log(e);
      tagSearchResult.value = "";
    });
}

// 수정
const editPlan = async () => {
  console.log("전달된 태그 리스트");
  console.log(tagList.value);

  await api
    .put(`${import.meta.env.VITE_VUE_API_URL}/plan`, {
      planId: planId.value,
      planName: planName.value,
      startDate: getFormatDate(inputDate.value.start),
      endDate: getFormatDate(inputDate.value.end),
      planDetail: planDetail.value,
      attrInfoList: attrInfoList.value,
      tagList: tagList.value,
    })
    .then(() => {
      router.push({ name: "plandetail", params: { id: plan.planId } });
    })
    .catch((e) => {
      console.log(e);
    });
};

const initInfo = async () => {
  await api
    .get(`${import.meta.env.VITE_VUE_API_URL}/plan/${planId.value}`)
    .then(({ data }) => {
      plan.value = data;
      planName.value = plan.value.planName;
      inputDate.value.start = new Date(plan.value.startDate);
      inputDate.value.end = new Date(plan.value.endDate);
      planDetail.value = plan.value.planDetail;
      tagList.value = plan.value.tagList;
      attrInfoList.value = plan.value.attrInfoList;
      console.log("시작일");
      console.log(inputDate.value.start);
      console.log("마지막일");
      console.log(inputDate.value.end);
    })
    .catch((e) => {
      console.log(e);
    });
};

onMounted(() => {
  initInfo();
});
</script>
<template>
  <div class="row g-5">
    <div class="col-md-12">
      <h3 class="pb-4 mb-4 fst-italic border-bottom">여행계획 수정하기</h3>
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
                  <h5>여행 이름</h5>
                  <input
                    type="text"
                    class="form-control mb-2"
                    id="planName"
                    :placeholder="planName"
                    v-model="planName"
                  />
                  <!-- <h5>시작일</h5>
                  <div>
                    <input
                      type="text"
                      class="form-control mb-2"
                      id="startDate"
                      :placeholder="startDate"
                      v-model="startDate"
                    />
                  </div>
                  <h5>마지막일</h5>
                  <input
                    type="text"
                    class="form-control mb-2"
                    id="endDate"
                    :placeholder="endDate"
                    v-model="endDate"
                  /> -->

                  <div class="input-group mb-3">
                    <VDatePicker
                      v-model.range="inputDate"
                      mode="date"
                      style="width: 50%"
                    />
                  </div>

                  <h5>세부내용</h5>
                  <textarea
                    class="form-control mb-2"
                    id="planDetail"
                    rows="3"
                    v-model="planDetail"
                  ></textarea>
                  <!-- <h4>[ 태그 ]</h4>
                  <div
                    class="mb-4 row"
                    style="
                      float: left;
                      justify-content: space-between;
                      display: flex;
                    "
                    v-for="(tag, index) in tagList"
                    :key="index"
                  >
                    <div class="col-md-12">
                      <button
                        type="button"
                        class="btn btn-primary rounded-pill m-1"
                      >
                        # {{ tag.tagName }}
                      </button>
                    </div>
                  </div> -->
                  <h5>태그</h5>
                  <div class="justify-content-center mb-3">
                    <!-- <div class="overflow-scroll"> -->
                    <input
                      type="text"
                      class="form-control"
                      placeholder="태그를 검색하세요."
                      aria-label="태그를 검색하세요."
                      aria-describedby="button-addon2"
                      @input="onTagInput($event)"
                    />

                    <div>
                      <ul
                        v-if="
                          (tagSearchResult.length === 0 &&
                            tagContent.length != 0) ||
                          (!sameTag && tagContent.length != 0)
                        "
                        class="list-group"
                      >
                        <li class="list-group-item" @click="addTag()">
                          직접 태그 추가하기
                        </li>
                      </ul>

                      <ul
                        class="list-group"
                        v-for="(tag, index) in tagSearchResult"
                        :key="index"
                      >
                        <li class="list-group-item" @click="addTagLike(tag)">
                          {{ tag.tagName }}
                        </li>
                      </ul>
                    </div>
                  </div>

                  <div
                    class="mb-4 row"
                    style="
                      float: left;
                      justify-content: space-between;
                      display: flex;
                    "
                    v-for="(tag, index) in tagList"
                    :key="index"
                  >
                    <div class="col-md-12">
                      <button
                        type="button"
                        class="btn btn-primary rounded-pill m-1"
                        @click="deleteTag(tag)"
                      >
                        {{ tag.tagName }} <span class="badge">X</span>
                      </button>
                    </div>
                  </div>
                </div>
              </div>

              <div class="row g-3">
                <div class="col-md-3">
                  <!--  여행지 검색 -->
                  <h5>여행지 검색</h5>
                  <!-- class="m-2 mb-3 row justify-content-center" -->
                  <div class="input-group justify-content-center">
                    <input
                      type="text"
                      class="form-control"
                      placeholder="장소를 검색하세요."
                      aria-label="장소를 검색하세요."
                      aria-describedby="button-addon2"
                      v-model="searchWord"
                    />
                    <button
                      class="btn btn-outline-secondary"
                      type="button"
                      id="button-addon2"
                      @click="searchAttraction()"
                    >
                      검색
                    </button>
                  </div>

                  <div class="justify-content-center">
                    <div
                      id="searchResult"
                      v-show="hasSearchResult"
                      class="overflow-y-scroll h-100 bg-body-tertiary p-2 rounded-2"
                      style="max-height: 800px"
                    >
                      <ul
                        class="list-group"
                        v-for="(place, index) in searchResult"
                        :key="index"
                      >
                        <a
                          @click="markPlaceOnMap(place)"
                          class="list-group-item list-group-item-action"
                          aria-current="true"
                        >
                          <div class="row g-2">
                            <!-- <div class="col-md-8"> -->
                            <div>
                              <div class="time-title">{{ place.title }}</div>
                              <div>{{ place.addr1 }}</div>
                            </div>
                            <!-- </div> -->
                            <!-- <div class="col-md-4 align-items-center"> -->
                            <div @click="addPlace(place)" aria-current="true">
                              <div class="align-middle blue">
                                여행계획에 추가
                              </div>
                            </div>
                            <!-- </div> -->
                          </div>
                        </a>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="col-md-6">
                  <h5>지도</h5>
                  <VKakaoMapEdit
                    :markPlace="markPlace"
                    :addedPlaces="attrInfoList"
                    style="width: 100%"
                  />
                </div>

                <div class="col-md-3">
                  <h5>타임라인</h5>
                  <plan-time-line
                    @delete-place="deletePlace"
                    :attractions="attrInfoList"
                    isDetail="false"
                  />
                </div>
              </div>

              <div class="m-2 p-1 row justify-content-end">
                <button
                  type="button"
                  class="btn btn-primary float-right m-2 col-1"
                  data-bs-toggle="modal"
                  data-bs-target="#editModal"
                >
                  완료
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
                      <h1 class="modal-title fs-5" id="exampleModalLabel">
                        수정하기
                      </h1>
                      <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                      ></button>
                    </div>
                    <div class="modal-body">정말 수정하시겠습니까?</div>
                    <div class="modal-footer">
                      <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                      >
                        아니요
                      </button>
                      <a
                        href="#"
                        type="button"
                        class="btn btn-primary"
                        @click="editPlan()"
                        data-bs-dismiss="modal"
                      >
                        네
                      </a>
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
.blue {
  color: blue;
  text-decoration: underline;
}
.w-btn {
  position: relative;
  border: none;
  display: inline-block;
  padding: 15px 30px;
  border-radius: 15px;
  font-family: "paybooc-Light", sans-serif;
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

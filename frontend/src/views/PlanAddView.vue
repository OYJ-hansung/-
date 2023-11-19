<script setup>
import { useRoute } from "vue-router";
import { ref, computed, reactive } from "vue";
import { ko } from "date-fns/locale";
import { useRouter } from "vue-router";
import api from "axios";
import VKakaoMapAdd from "@/components/common/VKakaoMapAdd.vue";

// import Datepicker from 'vue3-datepicker';
import { userStore } from "@/stores/userStore";
const ustore = userStore();

const markPlace = ref({});
const addedPlaces = ref([]);
const router = useRouter();
const searchWord = ref("");
const searchResult = ref([]);
const tagContent = ref("");
const tagSearchResult = ref([]);
const reload = ref(false);

// 날짜
const inputDate = ref({
  start: new Date(),
  end: new Date(),
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

const plan = ref({
  userId: ustore.userInfo.userId,
  planName: "",
  startDate: "",
  endDate: "",
  planDetail: "",
  attrInfo: [],
  tagList: [],
  img: "https://img.freepik.com/free-photo/airplane_74190-464.jpg?w=1380&t=st=1699807779~exp=1699808379~hmac=aa5cc0c5c8e05a2a1437b84eec67fc7e174e450c93e37d6996ca134b2a9a4184",
});

const deleteTag = (tag) => {
  var planFilter = [];
  planFilter = plan.value.tagList.filter((t) => t != tag);
  plan.value.tagList = planFilter;
};

const searchTag = async () => {
  await api
    .get(`http://localhost:8090/trip/plan/tag/${tagContent.value}`)
    .then(({ data }) => {
      console.log(getFormatDate(inputDate.value.start));

      tagSearchResult.value = data;
    })
    .catch((e) => {
      console.log(e);
      tagSearchResult.value = "";
    });
};

const addPlace = (place) => {
  plan.value.attrInfo.push(place);
  searchResult.value = [];
  console.log(plan.value.attrInfo);

  //지도에 전달할 배열에 장소 추가
  addedPlaces.value.push(place);
};

const markPlaceOnMap = (place) => {
  markPlace.value = place;
};

const reloadMap = () => {
  reload.value = true;
};

const hasAttr = computed(() => {
  return plan.value.attrInfo.length > 0 ? true : false;
});

const hasSearchResult = computed(() => {
  return searchResult.value.length > 0 ? true : false;
});

const addTag = () => {
  plan.value.tagList.push({ tagName: tagContent.value });
  tagContent.value = "";
  tagSearchResult.value = "";
};

const addPlan = async () => {
  await api
    .post(`http://localhost:8090/trip/plan/new`, {
      userId: "JohnOh",
      planName: plan.value.planName,
      startDate: getFormatDate(inputDate.value.start),
      endDate: getFormatDate(inputDate.value.end),
      planDetail: plan.value.planDetail,
      tagList: plan.value.tagList,
      img: "https://img.freepik.com/free-photo/airplane_74190-464.jpg?w=1380&t=st=1699807779~exp=1699808379~hmac=aa5cc0c5c8e05a2a1437b84eec67fc7e174e450c93e37d6996ca134b2a9a4184",
    })
    .then(() => {
      router.push({ path: "/planlist" });
    })
    .catch((e) => {
      console.log(e);
    });
};

const searchAttraction = async () => {
  await api
    .post(`http://localhost:8090/trip/attraction/search`, searchWord.value, {
      headers: { "Content-Type": "application/text" },
    })
    .then(({ data }) => {
      searchResult.value = data;
      console.log(searchResult.value);
    })
    .catch((e) => {
      console.log(e);
    });
};
</script>

<template>
  <div class="row g-5">
    <div class="col-md-12">
      <h3 class="pb-4 mb-4 fst-italic border-bottom">
        내 마음대로 여행코스!!!
      </h3>
      <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
          <button
            class="nav-link active"
            id="nav-home-tab"
            data-bs-toggle="tab"
            data-bs-target="#nav-home"
            type="button"
            role="tab"
            aria-controls="nav-home"
            aria-selected="true"
          >
            여행 계획 설명
          </button>
          <button
            class="nav-link"
            id="nav-profile-tab"
            data-bs-toggle="tab"
            data-bs-target="#nav-profile"
            type="button"
            role="tab"
            aria-controls="nav-profile"
            aria-selected="false"
            @click="reloadMap"
          >
            여행 계획 추가
          </button>
        </div>
      </nav>

      <div class="tab-content" id="nav-tabContent">
        <div
          class="tab-pane fade show active"
          id="nav-home"
          role="tabpanel"
          aria-labelledby="nav-home-tab"
        >
          <br />
          <div class="card shadow-sm">
            <div class="card-body">
              <h4 class="box-title">[ 제목 ]</h4>
              <input
                type="text"
                class="form-control mb-5 input-lg"
                id="planName"
                placeholder="제목을 입력하세요."
                v-model="plan.planName"
              />

              <h4 class="box-title">[ 날짜 ]</h4>
              <div class="input-group mb-3">
                <VDatePicker
                  v-model.range="inputDate"
                  mode="date"
                  style="width: 50%"
                />
              </div>

              <h4 class="box-title mt-5 mb-0">[ 세부 내용 ]</h4>
              <textarea
                class="form-control mb-5"
                id="planDetail"
                rows="3"
                v-model="plan.planDetail"
                placeholder="세부 내용을 입력하세요."
              ></textarea>
              <h5 class="box-title mt-3">[ 태그 ]</h5>
              <div class="justify-content-center mb-3">
                <!-- <div class="overflow-scroll"> -->
                <input
                  type="text"
                  class="form-control"
                  placeholder="태그를 검색하세요."
                  aria-label="태그를 검색하세요."
                  aria-describedby="button-addon2"
                  v-model="tagContent"
                  @keyup="searchTag()"
                />

                <div>
                  <ul
                    class="list-group"
                    v-for="(tag, index) in tagSearchResult"
                    :key="index"
                  >
                    <li class="list-group-item" @click="addTag()">
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
                v-for="(tag, index) in plan.tagList"
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
        </div>

        <!-- 두 번째 탭 -->
        <div
          class="tab-pane fade"
          id="nav-profile"
          role="tabpanel"
          aria-labelledby="nav-profile-tab"
        >
          <br />
          <div class="card shadow-sm">
            <div class="card-body">
              <div class="row g-3">
                <div class="col-md-3">
                  <!--  여행지 검색 -->
                  <div>
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
                        style="max-height: 670px"
                      >
                        <div
                          data-bs-spy="scroll"
                          data-bs-target="searchResult"
                          data-bs-root-margin="0px 0px -40%"
                          data-bs-smooth-scroll="true"
                          class="bg-body-tertiary rounded-2"
                          tabindex="0"
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
                                <div class>
                                  <div>{{ place.title }}</div>
                                  <div>{{ place.addr1 }}</div>
                                </div>
                                <!-- </div> -->
                                <!-- <div class="col-md-4 align-items-center"> -->
                                <a
                                  href="#"
                                  @click="addPlace(place)"
                                  aria-current="true"
                                >
                                  <div class="align-middle">
                                    여행계획에 추가
                                  </div>
                                </a>
                                <!-- </div> -->
                              </div>
                            </a>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-9">
                  <VKakaoMapAdd
                    :markPlace="markPlace"
                    :addedPlaces="addedPlaces"
                    :reload="reload"
                  />
                </div>
              </div>
              <div>ddddddddd</div>
            </div>
          </div>
        </div>
        <!-- 여기까지가 탭 별 화면 -->
        <!-- 타임라인 -->
        <div class="col-md-12">
          <div v-show="hasAttr" class="timeline">
            <div
              class="timeline-row"
              v-for="(attr, index) in plan.attrInfo"
              :key="index"
            >
              <div class="timeline-time">7:45PM<small>Dec 21</small></div>
              <div class="timeline-content">
                <i class="icon-attachment"></i>
                <h4>{{ attr.title }}</h4>
                <p>내용</p>
                <!-- 사진 -->
                <div class="thumbs">
                  <img
                    class="img-fluid rounded"
                    :src="attr.firstImage"
                    alt="Maxwell Admin"
                  />
                </div>
              </div>
            </div>
          </div>
          <div class="m-2 p-1 row justify-content-end">
            <button
              type="button"
              class="btn btn-primary float-right m-2 col-1"
              data-bs-toggle="modal"
              data-bs-target="#addModal"
            >
              완료
            </button>
          </div>
        </div>

        <div
          class="modal fade"
          id="addModal"
          tabindex="-1"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">
                  추가하기
                </h1>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">정말 추가하시겠습니까?</div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  아니요
                </button>
                <button
                  type="button"
                  class="btn btn-primary"
                  @click="addPlan()"
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
</template>

<style scoped>
body {
  margin-top: 20px;
  color: #bcd0f7;
  background: #1a233a;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
}

* {
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
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

.btn-circle.btn-xl {
  width: 50px;
  height: 50px;
  padding: 8px 12px;
  border-radius: 35px;
  font-size: 24px;
}

.btn-circle {
  width: 9px;
  height: 9px;
  /* padding: 4px 0px; */
  border-radius: 15px;
  text-align: center;
  font-size: 12px;
}

div.date {
  display: inline-flex;
}
</style>

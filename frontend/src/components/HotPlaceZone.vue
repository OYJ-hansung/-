<script setup>
import api from "axios";
import { onMounted, ref } from "vue";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();

const hotPlanList = ref([]);

const getHotPlanList = async () => {
  await api
    .get(`${import.meta.env.VITE_VUE_API_URL}/plan/hot`)
    .then(({ data }) => {
      hotPlanList.value = data;
      console.log(hotPlanList.value);
    })
    .catch((e) => {
      console.log(e);
    });
};

const moveToDetail = (planId) => {
  router.push({ name: "plandetail", params: { id: planId } });
};
onMounted(() => {
  getHotPlanList();
});
</script>

<template>
  <div>
    <span># 인기 여행코스</span>
    <img src="../assets/image/cuute.png" alt="" style="width: 160px" />
  </div>

  <div class="hot-course">
    <div class="hot">
      <div v-for="(plan, index) in hotPlanList" :key="index" class="col-lg-5 boxx">
        <div class="content">
          <!-- 이미지 -->
          <div class="round-image">
            <img :src="plan.img" alt="Your Image" />
          </div>

          <div class="round-wrap">
            <h3 class="fw-normal">
              {{ plan.planName }}
            </h3>
            <p class="detail">
              {{ plan.planDetail }}
            </p>
            <div class="btn btn-secondary" @click="moveToDetail(plan.planId)">자세히 보기</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.hot-course {
  background-color: aliceblue;
}

h2 {
  padding: 2%;
}

.hot {
  display: flex;
  flex-wrap: wrap; /* 필요에 따라 요소가 한 줄에 맞춰 표시되도록 설정 */
  justify-content: center; /* 가로 방향 가운데 정렬 */
  background-color: aliceblue;
  margin-bottom: 7%;
}

.col-lg-5 {
  flex: 0 0 40%; /* 필요에 따라 요소의 크기를 조절하세요 */
  margin: 10px; /* 필요에 따라 여백을 조절하세요 */
}

.content {
  text-align: center; /* 텍스트를 가운데 정렬 */
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 400px;
}
.round-wrap {
  background-color: aliceblue;
  position: absolute;
  bottom: 0;
}

.round-image {
  width: 300px;
  height: 300px;
  border-radius: 50%;
  overflow: hidden;
  margin-bottom: -90px;
  margin: auto;
}

.round-image img {
  width: 120%;
  /* height: auto; */
}

.detail {
  max-width: 300px;
}
</style>

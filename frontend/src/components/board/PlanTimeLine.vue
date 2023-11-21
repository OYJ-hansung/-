<script setup>
import { defineProps } from "vue";
const props = defineProps({
  attractions: Array,
  isDetail: String,
});
const emit = defineEmits(["deletePlace"]);

const deletePlace = function (index) {
  emit("deletePlace", index);
};
</script>

<template>
  <div
    id="timescroll"
    class="overflow-y-scroll h-100 rounded-2 timeline"
    style="max-height: 800px"
  >
    <div class="timeline-row" v-for="(attr, index) in attractions" :key="index">
      <div class="timeline-content">
        <div class="time-title">{{ attr.title }}</div>
        <!-- 사진 -->
        <div class="thumbs">
          <img
            class="img-fluid rounded"
            :src="attr.firstImage"
            alt="Maxwell Admin"
          />
        </div>
        <div v-if="isDetail == 'false'" @click="deletePlace(index)">삭제</div>
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

.time-title {
  font-weight: bold;
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

.timeline-row .timeline-time {
  position: absolute;
  right: 60%;
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
  left: 20%;
  width: 70%;
  padding: 10px 20px;
  margin-top: 20px;
  margin-bottom: 20px;
  background: #ffffff;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
  display: flex;
  /* align-items: center; */
  /* justify-content: center; */
  flex-direction: column;
  /* text-align: center; */
}

.timeline-row .timeline-content:before {
  content: "";
  position: absolute;
  top: 20px;
  right: 112%;
  width: 20px;
  height: 20px;
  -webkit-border-radius: 100px;
  -moz-border-radius: 100px;
  border-radius: 100px;
  z-index: 10;
  background: #272e48;
  border: 2px dashed #4b546f;
}

.timeline-row .timeline-content h5 {
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
  /* margin-bottom: 20px; */
  display: flex;
}

.timeline-row .timeline-content .thumbs img {
  margin-top: 5px;
  /* margin: 5px; */
  /* max-width: px; */
  /* width: 90%; */
  display: flex;
  align-content: center;
}

.timeline-row .timeline-content .badge {
  color: #ffffff;
  background: linear-gradient(120deg, #00b5fd 0%, #0047b1 100%);
}

.timeline-row:nth-child() {
  padding-left: 0;
  padding-right: 50%;
}

.timeline-row:nth-child() .timeline-time {
  right: auto;
  left: 50%;
  text-align: left;
  margin-right: 0;
  margin-left: 20px;
}

.timeline-row:nth-child() .timeline-content {
  margin-right: 40px;
}

.timeline-row:nth-child() .timeline-content:after {
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

  .timeline .timeline-row:nth-child() {
    padding: 0;
  }

  .timeline .timeline-row:nth-child() .timeline-time {
    position: relative;
    top: 0;
    left: 0;
    margin: 0 0 10px 0;
  }

  .timeline .timeline-row:nth-child() .timeline-content {
    margin: 0;
  }

  .timeline .timeline-row:nth-child() .timeline-content:before {
    display: none;
  }

  .timeline .timeline-row:nth-child() .timeline-content:after {
    display: none;
  }
}
</style>

<template>
  <div class="centerLine">
    <img src="@/assets/test.gif" />
    <br />
  </div>

  <div class="centerLine">
    <div id="progress-container">
      <div id="progress-bar"></div>
    </div>
  </div>
  <br />
  <div class="centerLine">
    <button @click="goHome">입장</button>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

let i = 0;

const goHome = function () {
  if (i == 0) {
    i = 1;
    const perFont = document.getElementById("progress-container");
    const elem = document.getElementById("progress-bar");
    let width = 0;
    const id = setInterval(frame, 20);

    function frame() {
      if (width >= 100) {
        clearInterval(id);
        i = 0;

        router.push({
          path: "/home",
        });
      } else {
        width++;
        elem.style.width = width + "%";
        // elem.innerHTML = width + "%";
      }
    }
  }
};
</script>

<style scoped>
.centerLine {
  display: flex;
  justify-content: center;
}

#progress-container {
  width: 50%;
  background-color: #dddddd;
  text-align: center;
}

#progress-bar {
  width: 0;
  height: 30px;
  line-height: 30px;
  background-color: #8c9cec;
  /* text-align: center; */
}
</style>

<template>
  <div class="sideSpace">
    <div class="chart">
      <h2>당신의 사이트 랭킹은?</h2>
      <canvas id="myChart1"></canvas>
    </div>
  </div>

  <div class="sideSpace">
    <div class="chart">
      <h2>3대 운동 능력 수행치</h2>
      <canvas id="myChart2"></canvas>
    </div>
  </div>
</template>

<script>
import Chart from "chart.js/auto";

export default {
  name: "test1",
  props: {
    msg: String,
  },
  mounted() {
    const ctx = document.getElementById("myChart1");
    const ctx2 = document.getElementById("myChart2");

    new Chart(ctx, {
      type: "line",
      data: {
        labels: ["1", "2", "3", "4", "5", "6", "7"],
        datasets: [
          {
            label: "My First Dataset",
            data: [65, 59, 80, 81, 56, 55, 40],
            fill: false,
            borderColor: "rgb(75, 192, 192)",
            tension: 0.1,
          },
        ],
      },
    });

    new Chart(ctx2, {
      type: "radar",
      data: {
        labels: ["벤치프레스", "데드리프트", "스쿼트"],
        datasets: [
          {
            label: "3대 운동 측정치",
            data: [80, 140, 130],
            fill: true,
            backgroundColor: "rgba(255, 99, 132, 0.2)",
            borderColor: "rgb(255, 99, 132)",
            pointBackgroundColor: "rgb(255, 99, 132)",
            pointBorderColor: "#fff",
            pointHoverBackgroundColor: "#fff",
            pointHoverBorderColor: "rgb(255, 99, 132)",
          },
          // {
          //   label: "사이트 활동 수치",
          //   data: [28, 48, 40],
          //   fill: true,
          //   backgroundColor: "rgba(54, 162, 235, 0.2)",
          //   borderColor: "rgb(54, 162, 235)",
          //   pointBackgroundColor: "rgb(54, 162, 235)",
          //   pointBorderColor: "#fff",
          //   pointHoverBackgroundColor: "#fff",
          //   pointHoverBorderColor: "rgb(54, 162, 235)",
          // },
        ],
      },
      options: {
        elements: {
          line: {
            borderWidth: 3,
          },
        },
      },
    });
  },
};
</script>

<style scoped>
.chart {
  width: 400px;
  height: 400px;
}

.sideSpace {
  display: flex;
}
</style>

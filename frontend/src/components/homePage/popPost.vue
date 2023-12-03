<template>
  <div class="container">
    <h4>인기 게시물</h4>
    <hr />
    <table class="table table-hover text-center">
      <tr>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
      </tr>
      <tr v-for="board in currentPageBoardList" :key="board.boardNo">
        <td>
          <RouterLink :to="`/board/${board.boardNo}`">{{
            board.boardTitle
          }}</RouterLink>
        </td>
        <td>{{ board.userNickname }}</td>
        <td>{{ board.boardViewCount }}</td>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { onMounted, computed, ref } from "vue";
import BoardSearchInput from "../board/BoardSearchInput.vue";
const store = useBoardStore();
onMounted(() => {
  store.getBoardList();
});

const perPage = 5;

const currentPage = ref(1);

const pageCount = computed(() => {
  return Math.ceil(store.boardList.length / perPage);
});
const clickPage = function (page) {
  currentPage.value = page;
};

const currentPageBoardList = computed(() => {
  return store.boardList.slice(
    (currentPage.value - 1) * perPage,
    currentPage.value * perPage
  );
});

const logCheck = ref(sessionStorage.getItem("access-token"));
</script>

<style scoped>
a {
  text-decoration: none;
}

button {
  background-color: rgb(255, 153, 0);
  background-color: hsla(24, 240, 120);
}

button:hover {
  background-color: red;
  background-color: hsla(24, 240, 120);
}

.rGo {
  text-align: end;
}

.rGo a {
  color: white;
}
</style>

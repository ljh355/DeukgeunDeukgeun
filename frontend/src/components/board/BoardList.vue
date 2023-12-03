<template>
  <div class="container">
    <div class="text-center">
      <h2 class="my-h2 my-underline">게시판</h2>
    </div>
    <h4>게시글 목록</h4>
    <hr />
    <BoardSearchInput />
    <table class="table table-hover text-center">
      <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>등록일</th>
      </tr>
      <tr v-for="board in currentPageBoardList" :key="board.boardNo">
        <td>{{ board.boardNo }}</td>
        <td>
          <RouterLink :to="`/board/${board.boardNo}`">{{
            board.boardTitle
          }}</RouterLink>
        </td>
        <td>{{ board.userNickname }}</td>
        <td>{{ board.boardViewCount }}</td>
        <td>{{ board.boardRegDate }}</td>
      </tr>
    </table>
    <nav aria-label="Page navigation">
      <ul class="pagination d-flex justify-content-center">
        <li class="page-item">
          <a
            class="page-link"
            :class="{ disabled: currentPage <= 1 }"
            href="#"
            @click.prevent="currentPage--"
            >&lt;</a
          >
        </li>
        <li
          :class="{ active: currentPage === page }"
          v-for="page in pageCount"
          :key="page"
        >
          <a
            class="page-link"
            href="#"
            @click.prevent="clickPage(page)"
            style="background-color: rgb(255, 153, 0)"
            >{{ page }}</a
          >
        </li>
        <li class="page-item">
          <a
            class="page-link"
            :class="{ disabled: currentPage >= pageCount }"
            href="#"
            @click.prevent="currentPage++"
            >&gt;</a
          >
        </li>
      </ul>
    </nav>
    <div class="rGo" v-show="logCheck !== null">
      <button class="btn btn-primary">
        <RouterLink :to="{ name: 'boardCreate' }">BoardCreate</RouterLink>
      </button>
    </div>
  </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { onMounted, computed, ref } from "vue";
import BoardSearchInput from "./BoardSearchInput.vue";
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

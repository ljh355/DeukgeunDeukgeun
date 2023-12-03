<template>
  <div>
    <div>
      <h3>작성한 글</h3>
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
              :class="{ disabled: boardCurrentPage <= 1 }"
              href="#"
              @click.prevent="boardCurrentPage--"
              >&lt;</a
            >
          </li>
          <li
            :class="{ active: boardCurrentPage === page }"
            v-for="page in boardPageCount"
            :key="page"
          >
            <a
              class="page-link"
              href="#"
              @click.prevent="boardClickPage(page)"
              >{{ page }}</a
            >
          </li>
          <li class="page-item">
            <a
              class="page-link"
              :class="{ disabled: boardCurrentPage >= boardPageCount }"
              href="#"
              @click.prevent="boardCurrentPage++"
              >&gt;</a
            >
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { onMounted, computed, ref } from "vue";
const boardStore = useBoardStore();

onMounted(() => {
  boardStore.getMyBoardList();
});

const boardPerPage = 5;

const boardCurrentPage = ref(1);

const boardPageCount = computed(() => {
  return Math.ceil(boardStore.boardList.length / boardPerPage);
});

const boardClickPage = function (page) {
  boardCurrentPage.value = page;
};

const currentPageBoardList = computed(() => {
  return boardStore.boardList.slice(
    (boardCurrentPage.value - 1) * boardPerPage,
    boardCurrentPage.value * boardPerPage
  );
});
</script>

<style scoped></style>

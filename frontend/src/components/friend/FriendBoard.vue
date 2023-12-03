<template>
  <hr />
  <div>
    <div>
      <h2>작성한 글</h2>
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
  <hr />
  <FriendComment />
</template>

<script setup>
import { useFriendStore } from "@/stores/friend";
import { onMounted, computed, ref } from "vue";
import FriendComment from "@/components/friend/FriendComment.vue";
const friendStore = useFriendStore();

onMounted(() => {
  friendStore.getFriendBoardList();
});

const boardPerPage = 5;

const boardCurrentPage = ref(1);

const boardPageCount = computed(() => {
  return Math.ceil(friendStore.boardList.length / boardPerPage);
});

const boardClickPage = function (page) {
  boardCurrentPage.value = page;
};

const currentPageBoardList = computed(() => {
  return friendStore.boardList.slice(
    (boardCurrentPage.value - 1) * boardPerPage,
    boardCurrentPage.value * boardPerPage
  );
});
</script>

<style scoped></style>

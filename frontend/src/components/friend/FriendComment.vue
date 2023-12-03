<template>
  <div>
    <div>
      <h2>작성한 댓글</h2>
      <table class="table table-hover text-center">
        <tr>
          <th>글번호</th>
          <th>내용</th>
          <th>작성자</th>
          <th>등록일</th>
        </tr>
        <tr v-for="comment in currentPageCommentList" :key="comment.commentNo">
          <td>{{ comment.commentNo }}</td>
          <td>
            <RouterLink :to="`/board/${comment.boardNo}`">{{
              comment.commentContent
            }}</RouterLink>
          </td>
          <td>{{ comment.userNickname }}</td>
          <td>{{ comment.commentRegDate }}</td>
        </tr>
      </table>
      <nav aria-label="Page navigation">
        <ul class="pagination d-flex justify-content-center">
          <li class="page-item">
            <a
              class="page-link"
              :class="{ disabled: commentCurrentPage <= 1 }"
              href="#"
              @click.prevent="commentCurrentPage--"
              >&lt;</a
            >
          </li>
          <li
            :class="{ active: commentCurrentPage === page }"
            v-for="page in commentPageCount"
            :key="page"
          >
            <a
              class="page-link"
              href="#"
              @click.prevent="commentClickPage(page)"
              >{{ page }}</a
            >
          </li>
          <li class="page-item">
            <a
              class="page-link"
              :class="{ disabled: commentCurrentPage >= commentPageCount }"
              href="#"
              @click.prevent="commentCurrentPage++"
              >&gt;</a
            >
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script setup>
import { useFriendStore } from "@/stores/friend";
import { onMounted, computed, ref } from "vue";
const friendStore = useFriendStore();

onMounted(() => {
  friendStore.getFriendCommentList();
});

const commentPerPage = 5;

const commentCurrentPage = ref(1);

const commentPageCount = computed(() => {
  return Math.ceil(friendStore.commentList.length / commentPerPage);
});
const commentClickPage = function (page) {
  commentCurrentPage.value = page;
};

const currentPageCommentList = computed(() => {
  return friendStore.commentList.slice(
    (commentCurrentPage.value - 1) * commentPerPage,
    commentCurrentPage.value * commentPerPage
  );
});
</script>

<style scoped></style>

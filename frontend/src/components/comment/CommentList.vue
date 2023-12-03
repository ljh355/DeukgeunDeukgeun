<template>
  <div>
    <div class="comment">
      <div>
        <CommentCreate :boardNo="boardNo" />
      </div>
      <div>
        <table>
          <tr>
            <th>번호</th>
            <th>닉네임</th>
            <th>내용</th>
            <th>등록일</th>
            <th>좋아용</th>
            <th></th>
          </tr>
          <tr v-for="comment in store.commentList" :key="comment.commentNo">
            <td>{{ comment.commentNo }}</td>
            <td>{{ comment.userNickname }}</td>
            <td>{{ comment.commentContent }}</td>
            <td>{{ comment.commentRegDate }}</td>
            <td>{{ comment.commentLikeCount }}</td>
            <td>
              <button
                class="mx-3 btn btn-outline-danger"
                @click="deleteComment(comment)"
              >
                삭제
              </button>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useCommentStore } from "@/stores/comment";
import CommentCreate from "./CommentCreate.vue";
const store = useCommentStore();
const props = defineProps(["SearchCondition"]);
const SearchCondition = props.SearchCondition;
const boardNo = SearchCondition.word;

const ui = ref(sessionStorage.getItem("login-userid"));
const unn = ref(sessionStorage.getItem("login-usernickname"));

const deleteComment = function (comment) {
  if (comment.userId === ui.value) {
    store.deleteComment(comment.commentNo);
  } else {
    alert("권한이 없습니다");
  }
};

onMounted(() => {
  store.getCommentList(SearchCondition);
});
</script>

<style coped></style>

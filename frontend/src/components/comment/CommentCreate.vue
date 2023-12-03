<template>
  <div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="userId"
        placeholder="작성자"
        readonly
        v-model="unn"
      />
      <label for="userId">userId</label>
    </div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="commentContent"
        placeholder="내용"
        v-model="comment.commentContent"
      />
      <label for="commentContent">commentContent</label>
    </div>
    <div class="d-flex justify-content-end">
      <button class="btn btn-outline-primary" @click="createComment(comment)">
        등록
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useCommentStore } from "@/stores/comment";
import { useUserStore } from "@/stores/user";
const props = defineProps(["boardNo"]);
const store = useCommentStore();
const userStore = useUserStore();

const ui = ref(sessionStorage.getItem("login-userid"));
const unn = ref(sessionStorage.getItem("login-usernickname"));

const comment = ref({
  boardNo: props.boardNo,
  userId: "",
  commentContent: "",
});

const createComment = function (comment) {
  console.log(userStore.loginUseruserId);
  console.log(comment.userId);
  console.log(userStore.loginUser.userName);
  comment.userId = ui.value;

  if (ui.value !== null) {
    store.createComment(comment);
  } else {
    alert("권한이 없습니다!");
  }
};
</script>

<style scoped></style>

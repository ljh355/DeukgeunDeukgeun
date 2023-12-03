<template>
  <div>
    <h4>게시글 작성</h4>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="boardTitle"
        placeholder="제목"
        v-model="board.boardTitle"
      />
      <label for="boardTitle">boardTitle</label>
    </div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="userNickname"
        placeholder="작성자"
        readonly
        v-model="unn"
      />
      <label for="userNickname">userNickname</label>
    </div>
    <div class="form-floating mb-3">
      <form class="form-control">
        <select name="boardClassification" v-model="board.boardClassification">
          <option value="정보">정보게시판</option>
          <option value="자유">자유게시판</option>
          <option value="핫딜">핫딜게시판</option>
          <option value="질문">질문게시판</option>
        </select>
      </form>
      <label for="userNickname">게시판 선택</label>
    </div>
    <div class="form-floating mb-3">
      <textarea
        class="form-control"
        id="boardContent"
        placeholder="내용"
        style="height: 200px"
        v-model="board.boardContent"
      ></textarea>
      <label for="boardContent">boardContent</label>
    </div>
    <div class="d-flex justify-content-end">
      <button class="btn btn-outline-primary" @click="createBoard">등록</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useBoardStore } from "@/stores/board";
import { useUserStore } from "@/stores/user";

const board = ref({
  userId: "",
  userNickname: "",
  boardTitle: "",
  boardContent: "",
  boardClassification: "",
});

const store = useBoardStore();
const userStore = useUserStore();

const ui = ref(sessionStorage.getItem("login-userid"));
const unn = ref(sessionStorage.getItem("login-usernickname"));

const test = function () {
  // console.log(board.value);
  // console.log(userStore.loginUseruserId);
  // console.log(userStore.searchOneUser(userStore.loginUseruserId));
  // board.userId = userStore.loginUser.userId;
  // console.log(board.userId);
  // console.log(unn.value);
};

// onMounted(() => {
//   userStore.searchOneUser(userStore.loginUseruserId);
// });

const createBoard = function () {
  board.value.userId = ui.value;
  board.value.userNickname = unn.value;
  console.log(board.value);
  store.createBoard(board.value);
};
</script>

<style scoped></style>

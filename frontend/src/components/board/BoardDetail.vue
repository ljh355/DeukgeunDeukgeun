<template>
  <div>
    <h4>게시글 상세</h4>
    <hr />
    <div class="d-flex justify-content-center">
      <div class="card" style="width: 30rem">
        <div class="card-body">
          <div class="mb-3 d-flex justify-content-between align-items-center">
            <h4 class="card-title">
              asd
              {{ store.board.boardTitle }}
              <span class="badge bg-danger">{{
                store.board.boardViewCount
              }}</span>
            </h4>
            <div class="d-flex justify-content-end">
              <h6 class="card-subtitle mx-3 text-body-secondary">
                {{ store.board.userNickname }}
              </h6>
              <h6 class="card-subtitle text-body-secondary">
                {{ store.board.boardRegDate }}
              </h6>
            </div>
          </div>
          <p class="card-text">
            {{ store.board.boardContent }}
          </p>
          <div class="d-flex justify-content-center">
            <button class="mx-3 btn btn-outline-success" @click="moveUpdate">
              수정
            </button>
            <button class="mx-3 btn btn-outline-danger" @click="deleteBoard">
              삭제
            </button>
            <button class="mx-3 btn btn-outline-danger" @click="favoriteBoard">
              좋아용
            </button>
            {{ store.board.boardFavCount }}
          </div>
        </div>
      </div>
    </div>
    <CommentList :SearchCondition="SearchCondition" />
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import { useBoardStore } from "@/stores/board";
import { useFavoriteStore } from "@/stores/favorite";
import axios from "axios";
import CommentList from "../comment/CommentList.vue";
const route = useRoute();
const router = useRouter();
const store = useBoardStore();
const favoriteStore = useFavoriteStore();

const ui = ref(sessionStorage.getItem("login-userid"));
const unn = ref(sessionStorage.getItem("login-usernickname"));

onMounted(() => {
  store.getBoard(route.params.boardNo);
  console.log(route.params.boardNo); // 이거 언디파인
});

const SearchCondition = ref({
  key: "boardNo",
  word: route.params.boardNo,
});

// ▼ 좋아요관련 추가된 부분 ▼
const favoriteBoard = function () {
  const favorite = ref({
    boardNo: route.params.boardNo,
    userId: ui,
  });
  favoriteStore.clickFavorite(favorite.value);
};
// ▲ 좋아요관련 추가된 부분 ▲

const moveUpdate = function () {
  if (store.board.userNickname === unn.value) {
    router.push({ name: "boardUpdate" });
  } else {
    alert("권한이 없습니다");
  }
};

const deleteBoard = function () {
  if (store.board.userNickname === unn.value) {
    axios
      .delete(`http://localhost:8080/api/board/${route.params.boardNo}`)
      .then(() => {
        router.push({ name: "boardList" });
      })
      .catch(() => {});
  } else {
    alert("권한이 없습니다");
  }
};
</script>

<style scoped></style>

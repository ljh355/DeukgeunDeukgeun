<template>
  <div class="text-center" @keydown.enter="searchBoardList">
    <div class="row align-items-center">
      <div class="col-2">
        <select class="form-select" v-model="searchInfo.key">
          <option value="none">전체</option>
          <option value="boardTitle">제목</option>
          <option value="userNickname">작성자</option>
          <option value="boardContent">내용</option>
        </select>
      </div>
      <div class="col-6">
        <input type="text" class="form-control" v-model="searchInfo.word" />
      </div>
      <div class="col-2">
        <div class="form-check form-check-inline">
          <input
            v-model="searchInfo.orderBy"
            class="form-check-input"
            type="radio"
            id="boardFavCount"
            value="boardFavCount"
          />
          <label class="form-check-label" for="boardFavCount"
            ><i class="bi bi-heart-fill">추천수</i></label
          >
        </div>
        <div class="form-check form-check-inline">
          <input
            v-model="searchInfo.orderBy"
            class="form-check-input"
            type="radio"
            id="boardViewCount"
            value="boardViewCount"
          />
          <label class="form-check-label" for="boardViewCount"
            ><i class="bi bi-eye-fill">조회수</i></label
          >
        </div>
      </div>
      <div class="col-1">
        <div @click="sortChange">
          <span v-if="searchInfo.orderByDir === 'asc'">
            <i class="bi bi-arrow-up-right-square-fill">낮은순</i>
          </span>
          <span v-else
            ><i class="bi bi-arrow-down-right-square-fill">높은순</i></span
          >
        </div>
      </div>
      <div class="col-1">
        <button class="btn btn-outline-warning" @click="searchBoardList">
          검색
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useBoardStore } from "@/stores/board";

const store = useBoardStore();
const searchInfo = ref({
  key: "none",
  word: "",
  orderBy: "none",
  orderByDir: "asc",
});
const searchBoardList = function () {
  store.searchBoardList(searchInfo.value);
};

const sortChange = function () {
  if (searchInfo.value.orderByDir === "asc")
    searchInfo.value.orderByDir = "desc";
  else searchInfo.value.orderByDir = "asc";
};
</script>

<style scoped></style>

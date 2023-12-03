import { ref } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_BOARD_API = `http://localhost:8080/api/comment`;

export const useCommentStore = defineStore("comment", () => {
  const commentList = ref([]);
  const getCommentList = function (SearchCondition) {
    axios
      .get(REST_BOARD_API, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        params: SearchCondition,
      })
      .then((response) => {
        commentList.value = response.data;
      });
  };

  const createComment = function (comment) {
    console.log(comment);
    axios({
      url: REST_BOARD_API,
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: comment,
    })
      .then(() => {
        router.go(0);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const deleteComment = function (commentNo) {
    axios({
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
      url: `${REST_BOARD_API}/${commentNo}`,
      method: "DELETE",
    })
      .then(() => {
        router.go(0);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const getMyCommentList = function () {
    const token = sessionStorage.getItem("access-token").split(".");
    let userId = token[1];
    userId = atob(userId);
    userId = JSON.parse(userId);
    userId = userId["id"];
    axios
      .get(`${REST_BOARD_API}/list/${userId}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        params: {
          userId: userId,
        },
      })
      .then((response) => {
        commentList.value = response.data;
      });
  };

  return {
    commentList,
    getCommentList,
    createComment,
    deleteComment,
    getMyCommentList,
  };
});

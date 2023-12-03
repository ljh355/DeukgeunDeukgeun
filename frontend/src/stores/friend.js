import { ref, computed } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_BOARD_API = `http://localhost:8080/api/friend`;

export const useFriendStore = defineStore("friend", () => {
  const friendList = ref({});
  const getFriendList = function (SearchCondition) {
    console.log(SearchCondition);
    axios({
      url: `${REST_BOARD_API}`,
      method: "GET",
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
      params: {
        key: SearchCondition.key,
        word: SearchCondition.word,
      },
    }).then((response) => {
      console.log(response);
      friendList.value = response.data;
    });
  };

  const createFriend = function (friendReqNo) {
    axios({
      url: `${REST_BOARD_API}/${friendReqNo}`,
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "access-token": sessionStorage.getItem("access-token"),
      },
      params: {
        friendReqNo: friendReqNo,
      },
    })
      .then(() => {
        router.go(0);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const deleteFriend = function (friendNo) {
    axios({
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
      url: `${REST_BOARD_API}/${friendNo}`,
      method: "DELETE",
    })
      .then(() => {
        router.go(0);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const boardList = ref([]);
  const getFriendBoardList = function () {
    const token = sessionStorage.getItem("access-token").split(".");
    let userId = token[1];
    userId = atob(userId);
    userId = JSON.parse(userId);
    userId = userId["id"];
    axios
      .get(`${REST_BOARD_API}/board/${userId}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        params: {
          userId: userId,
        },
      })
      .then((response) => {
        boardList.value = response.data;
      });
  };

  const commentList = ref([]);
  const getFriendCommentList = function () {
    const token = sessionStorage.getItem("access-token").split(".");
    let userId = token[1];
    userId = atob(userId);
    userId = JSON.parse(userId);
    userId = userId["id"];
    axios
      .get(`${REST_BOARD_API}/comment/${userId}`, {
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
    friendList,
    boardList,
    commentList,
    getFriendList,
    createFriend,
    deleteFriend,
    getFriendBoardList,
    getFriendCommentList,
  };
});

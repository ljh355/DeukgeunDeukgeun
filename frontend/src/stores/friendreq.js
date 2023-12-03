import { ref, computed } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_BOARD_API = `http://localhost:8080/api/friendreq`;

export const useFriendReqStore = defineStore("friendreq", () => {
  const friendReqFromList = ref([]);
  const getFriendReqFromList = function (SearchCondition) {
    console.log(SearchCondition);
    axios
      .get(REST_BOARD_API, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        params: SearchCondition,
      })
      .then((response) => {
        friendReqFromList.value = response.data;
      });
  };

  const friendReqToList = ref([]);
  const getFriendReqToList = function (SearchCondition) {
    console.log(SearchCondition);
    axios
      .get(REST_BOARD_API, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        params: SearchCondition,
      })
      .then((response) => {
        friendReqToList.value = response.data;
      });
  };

  const createFriendReq = function (friendRequest) {
    console.log(friendRequest);
    axios({
      url: `${REST_BOARD_API}`,
      method: "POST",
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
      params: friendRequest,
    })
      .then((res) => {
        console.log(res);
        if (res.data == 1) {
          alert("친구신청 되었습니다.");
          router.go(0);
        } else if (res.data == -1) {
          alert("ID를 입력하세요");
        } else if (res.data == -2) {
          alert("친구신청한 ID가 존재하지 않습니다.");
        } else if (res.data == -3) {
          alert("자기 자신에게 친구요청을 보낼 수 없습니다.");
        } else if (res.data == -4) {
          alert("이미 친구요청 진행중입니다.");
        } else if (res.data == -5) {
          alert("이미 친구관계입니다.");
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const deleteFriendReq = function (frienReqNo) {
    axios({
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
      url: `${REST_BOARD_API}/${frienReqNo}`,
      method: "DELETE",
    })
      .then(() => {
        router.go(0);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return {
    friendReqFromList,
    friendReqToList,
    getFriendReqFromList,
    getFriendReqToList,
    createFriendReq,
    deleteFriendReq,
  };
});

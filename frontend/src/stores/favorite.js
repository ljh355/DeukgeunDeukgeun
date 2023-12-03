import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_BOARD_API = `http://localhost:8080/api/fav`;

export const useFavoriteStore = defineStore("favorite", () => {
  const clickFavorite = function (favorite) {
    console.log(favorite);
    axios({
      url: REST_BOARD_API,
      method: "POST",
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
        "Content-Type": "application/json",
      },
      data: favorite,
    })
      .then((response) => {
        console.log(response);
        if (response.data == -1) {
          alert("로그인이 필요합니다");
        } else if (response.data == 1) {
          alert("추천했습니다");
        } else if (response.data == 2) {
          alert("추천 취소했습니다");
        } else if (response.data == 3) {
          alert("자신이 쓴 글은 추천할 수 없습니다");
          return;
        }
        router.go(0);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return { clickFavorite };
});

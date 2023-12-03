import { ref, computed } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_BOARD_API = `http://localhost:8080/api`;
const REST_SIGNUP_API = `http://localhost:8080/api/signup`;

export const useUserStore = defineStore("user", () => {
  const loginUseruserId = ref("");

  const userList = ref([]);

  const loginUser = ref({});

  const searchOneUser = async function (userId) {
    await axios.get(`${REST_BOARD_API}/user/${userId}`).then((response) => {
      loginUser.value = response.data;
    });
  };

  const userSearch = function (SearchCondition) {
    axios
      .get(`${REST_BOARD_API}/users`, { SearchCondition })
      .then((response) => {
        userList.value = response.data;
      });
    // .then((
    //   router.push({name:""})
    // ));
  };

  const userLogin = function (userId, userPassword) {
    axios
      .post(`${REST_BOARD_API}/login`, { userId, userPassword })
      .then((response) => {
        console.log(response);

        sessionStorage.setItem("login-userid", response.data["login-userid"]);
        sessionStorage.setItem(
          "login-usernickname",
          response.data["login-usernickname"]
        );

        sessionStorage.setItem("access-token", response.data["access-token"]);
        const token = response.data["access-token"].split(".");
        let userId = token[1];

        userId = atob(userId);
        console.log(userId);
        userId = JSON.parse(userId);
        console.log(userId["id"]);
        console.log("test");
        loginUseruserId.value = userId["id"];

        router.push({ path: "/home" });
      })
      .catch(() => {
        alert("로그인실패");
        sessionStorage.removeItem("access-token");
        sessionStorage.removeItem("login-userid");
        sessionStorage.removeItem("login-usernickname");
      });
  };

  const userLogout = function () {
    alert("로그아웃 성공!");

    sessionStorage.removeItem("access-token");
    sessionStorage.removeItem("login-userid");
    sessionStorage.removeItem("login-usernickname");
    router.push({ path: "/home" });
    // router.push({ name: "boardList" });
  };

  const signUp = function (formData) {
    console.log(formData);
    console.log(formData.value);
    axios({
      url: REST_SIGNUP_API,
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: formData,
    })
      .then(() => {
        alert("회원가입 성공");
        router.push({ path: "/home" });
      })
      .catch((err) => {
        alert("회원가입 실패");
        // console.log(err);
      });
  };

  const nowLoginUser = ref({});
  const searchLoginUser = function () {
    const token = sessionStorage.getItem("access-token").split(".");
    let userId = token[1];
    userId = atob(userId);
    userId = JSON.parse(userId);
    userId = userId["id"];
    axios
      .get(`${REST_BOARD_API}/user/${userId}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        params: {
          userId: userId,
        },
      })
      .then((response) => {
        nowLoginUser.value = response.data;
      });
  };

  const updateUser = function (formData) {
    searchLoginUser();
    console.log(formData);
    axios
      .put(`${REST_BOARD_API}/update`, {
        params: {
          userPassword: formData.userPassword,
          userNickname: formData.userNickname,
          userEmail: formData.userEmail,
          userPhoneNumber: formData.userPhoneNumber,
        },
      })
      .then(() => {
        router.push({ name: "home" });
      });
  };

  return {
    userList,
    userSearch,
    loginUser,
    searchOneUser,
    userLogin,
    loginUseruserId,
    userLogout,
    signUp,
    nowLoginUser,
    searchLoginUser,
    updateUser,
  };
});

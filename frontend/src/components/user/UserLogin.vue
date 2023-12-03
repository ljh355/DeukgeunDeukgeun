<template>
  <div class="logo"><RouterLink to="/home"><img src="@/assets/logo.png" /></RouterLink></div>
  <div>
    <div class="loginBox">
      <div class="loginTitle">
        <h4>Login</h4>
      </div>
      <div>
        <i class="bi bi-person"></i>
        <input
          type="text"
          placeholder="아이디"
          v-model.trim="userId"
          @keydown.enter="login"
        />
      </div>

      <div>
        <i class="bi bi-lock"></i>
        <input
          type="password"
          placeholder="비밀번호"
          v-model.trim="userPassword"
          @keydown.enter="login"
        />
      </div>
      <div class="loginButton">
        <button @click="login" class="btn btn-primary">로그인</button>
      </div>
    </div>
  </div>
  <div>
    아이디 찾기 | 비밀번호 찾기 |
    <RouterLink to="/signUp">회원가입</RouterLink>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { RouterLink } from "vue-router";

const store = useUserStore();

const userId = ref("");
const userPassword = ref("");

const login = function () {
  store.userLogin(userId.value, userPassword.value);
};

const logout = function () {
  store.userLogout();
};
</script>

<style scoped>
* {
  display: flex;
  justify-content: center;
  text-align: center;
}

a {
  text-decoration: none;
  color: black;
}

.logo {
  margin-top: 100px;
  display: flex;
  justify-content: center;
}

.loginBox {
  /* margin-top: 50px; */
  border: 1px solid gray;
  padding: 10px;
  flex-direction: column;
  width: 470px;
}

input,
button {
  width: 400px;
}

.loginTitle {
  margin-top: 10px;
  margin-bottom: 20px;
}

.loginButton {
  margin-top: 20px;
  margin-bottom: 10px;
}

button {
  background-color: rgb(255, 153, 0);
  background-color: hsla(24, 240, 120);
}

button:hover {
  background-color: red;
  background-color: hsla(24, 240, 120);
}
</style>

<template>
  <h2>회원정보</h2>
  <hr />

  <div id="sideSpace">
    <div>
      <div>ID : {{ userStore.loginUser.userId }}</div>
      <div>닉네임 : {{ userStore.loginUser.userNickname }}</div>
      <div>경험치 : {{ userStore.loginUser.userExp }}</div>
      <div>이름 : {{ userStore.loginUser.userName }}</div>
      <div>성별 : {{ userStore.loginUser.userGender }}</div>
      <div>이메일 : {{ userStore.loginUser.userEmail }}</div>
      <div>전화번호 : {{ userStore.loginUser.userPhoneNumber }}</div>
    </div>
    <div>
      <div><MyPageInfoAboutBoard style="width: 500px" /></div>
      <hr />
      <div><MyPageInfoAboutComment style="width: 500px" /></div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { onMounted, computed, ref } from "vue";
import MyPageInfoAboutComment from "./MyPageInfoAboutComment.vue";
import MyPageInfoAboutBoard from "./MyPageInfoAboutBoard.vue";
const userStore = useUserStore();

const token = sessionStorage.getItem("access-token").split(".");
let userId = token[1];
userId = atob(userId);
userId = JSON.parse(userId);
userId = userId["id"];
userStore.searchOneUser(userId);
</script>

<style scoped>
#sideSpace {
  display: flex;
  justify-content: space-between;
}
</style>

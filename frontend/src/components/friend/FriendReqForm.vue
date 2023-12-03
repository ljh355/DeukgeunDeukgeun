<template>
  <h4>친구 신청하기</h4>
  <div class="frBox">
    <div class="col-6">
      <input
        type="text"
        class="form-control"
        v-model="friendRequest.friendId"
      />
    </div>
    <button class="btn btn-outline-danger" @click="createFriendReq">
      보내기
    </button>
  </div>
  <hr />
</template>

<script setup>
import { useFriendReqStore } from "@/stores/friendreq";
import { onMounted, computed, ref } from "vue";
const friendReqStore = useFriendReqStore();
const loginUserId = sessionStorage.getItem("login-userid");

const friendRequest = ref({
  userId: loginUserId,
  friendId: "",
});

const createFriendReq = function () {
  friendReqStore.createFriendReq(friendRequest.value);
};
</script>

<style scoped>
.frBox {
  display: flex;
}
</style>

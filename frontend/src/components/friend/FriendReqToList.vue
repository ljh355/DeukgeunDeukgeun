<template>
  <div>
    <h2>보낸 친구요청</h2>
    <table class="table table-hover text-center">
      <tr>
        <th>유저ID</th>
        <th>닉네임</th>
        <th></th>
      </tr>
      <tr
        v-for="friend in friendReqStore.friendReqToList"
        :key="friend.friendId"
      >
        <td>{{ friend.friendId }}</td>
        <td>{{ friend.friendNickname }}</td>
        <button
          class="mx-3 btn btn-outline-danger"
          @click="deleteFriendReq(friend.friendReqNo)"
        >
          취소
        </button>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { useFriendReqStore } from "@/stores/friendreq";
import { onMounted, computed, ref } from "vue";
const friendReqStore = useFriendReqStore();

onMounted(() => {
  const loginUserId = sessionStorage.getItem("login-userid");
  const SearchCondition = ref({
    key: "userId",
    word: loginUserId,
  });
  friendReqStore.getFriendReqToList(SearchCondition.value);
});

const deleteFriendReq = function (friendReqNo) {
  friendReqStore.deleteFriendReq(friendReqNo);
  alert("친구요청을 취소하였습니다.");
};


</script>

<style scoped></style>

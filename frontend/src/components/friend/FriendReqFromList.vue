<template>
  <div>
    <h2>받은 친구요청</h2>
    <table class="table table-hover text-center">
      <tr>
        <th>유저ID</th>
        <th>닉네임</th>
        <th></th>
        <th></th>
      </tr>
      <tr
        v-for="friend in friendReqStore.friendReqFromList"
        :key="friend.userId"
      >
        <td>{{ friend.userId }}</td>
        <td>{{ friend.userNickname }}</td>
        <button
          class="mx-3 btn btn-outline-danger"
          @click="createFriend(friend.friendReqNo)"
        >
          수락
        </button>
        <button
          class="mx-3 btn btn-outline-danger"
          @click="deleteFriendReq(friend.friendReqNo)"
        >
          거절
        </button>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { useFriendReqStore } from "@/stores/friendreq";
import { useFriendStore } from "@/stores/friend";
import { onMounted, computed, ref } from "vue";
const friendReqStore = useFriendReqStore();
const friendStore = useFriendStore();

onMounted(() => {
  const loginUserId = sessionStorage.getItem("login-userid");
  const SearchCondition = ref({
    key: "friendId",
    word: loginUserId,
  });
  friendReqStore.getFriendReqFromList(SearchCondition.value);
});

const deleteFriendReq = function (friendReqNo) {
  friendReqStore.deleteFriendReq(friendReqNo);
  alert("친구요청을 거부하였습니다.");
};

const createFriend = function (friendReqNo) {
  alert("친구요청을 수락하였습니다.");
  friendStore.createFriend(friendReqNo);
};
</script>

<style scoped></style>

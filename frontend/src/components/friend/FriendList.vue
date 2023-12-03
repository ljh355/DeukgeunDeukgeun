<template>
  <div>
    <h3>친구목록</h3>
    <table class="table table-hover text-center">
      <tr>
        <th></th>
        <th>유저ID</th>
        <th>닉네임</th>
        <th></th>
      </tr>
      <tr v-for="friend in friendStore.friendList" :key="friend.friendId">
        <td>{{ friend.userId }}</td>
        <td>{{ friend.friendId }}</td>
        <td>{{ friend.friendNickname }}</td>
        <button
          class="mx-3 btn btn-outline-danger"
          @click="deleteFriend(friend.friendNo)"
        >
          친구삭제
        </button>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { useFriendStore } from "@/stores/friend";
import { onMounted, computed, ref } from "vue";
const friendStore = useFriendStore();

onMounted(() => {
  const loginUserId = sessionStorage.getItem("login-userid");
  const SearchCondition = ref({
    key: "userId",
    word: loginUserId,
  });
  friendStore.getFriendList(SearchCondition.value);
});

const deleteFriend = function (friendNo) {
  friendStore.deleteFriend(friendNo);
  alert("친구를 삭제했습니다.");
};
</script>

<style scoped></style>

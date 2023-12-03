<template>
  <h2>친구정보</h2>
  <hr />
  <div id="sideSpace">
    <div>
      <div><FriendReqForm /></div>
    </div>
    <div>
      <div>
        <div><FriendReqFromList /></div>
      </div>
    </div>
    <div>
      <div><FriendReqToList /></div>
    </div>
  </div>

  <div id="sideSpace">
    <FriendList />
    <div style="width: 500px"><FriendBoard /></div>
  </div>
</template>

<script setup>
import { RouterView } from "vue-router";
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
import TheFooterNav from "@/components/common/TheFooterNav.vue";
import FriendReqFromList from "@/components/friend/FriendReqFromList.vue";
import FriendReqToList from "@/components/friend/FriendReqToList.vue";
import FriendList from "@/components/friend/FriendList.vue";
import FriendReqForm from "@/components/friend/FriendReqForm.vue";
import FriendBoard from "@/components/friend/FriendBoard.vue";
import FriendComment from "@/components/friend/FriendComment.vue";
</script>

<style scoped>
#sideSpace {
  display: flex;
  justify-content: space-between;
}
</style>

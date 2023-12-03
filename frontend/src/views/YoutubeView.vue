<template>
  <TheHeaderNav />
  <div id="sideSpace">
    <h2 class="text-center">
      <i class="bi bi-youtube" style="color: red"></i> YoutubeView
    </h2>
    <YoutubeVideoSearch />
    <hr />
    <YoutubeVideoList />
  </div>
  <TheFooterNav />
</template>

<script setup>
import YoutubeVideoSearch from "@/components/youtube/YoutubeVideoSearch.vue";
import YoutubeVideoList from "@/components/youtube/YoutubeVideoList.vue";
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
import TheFooterNav from "@/components/common/TheFooterNav.vue";
</script>

<style scoped>
#sideSpace {
  margin-left: 20%;
  margin-right: 20%;
}
</style>

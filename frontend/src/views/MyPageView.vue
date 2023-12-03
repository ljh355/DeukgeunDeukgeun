<template>
  <TheHeaderNav />
  <div id="sideSpace">
    <RouterView />
  </div>
  <TheFooterNav />
</template>

<script setup>
import { RouterView } from "vue-router";
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
import TheFooterNav from "@/components/common/TheFooterNav.vue";
</script>

<style scoped>
#sideSpace {
  margin-left: 20%;
  margin-right: 20%;
}
</style>

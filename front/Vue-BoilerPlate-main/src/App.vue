<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>

<template>
  <div id="app">
    <router-view class="header" name="header" />
    <div class="contents">
      <router-view class="body" name="body" />
    </div>
    <router-view class="footer" name="footer" />
  </div>
</template>

<style lang="scss">
#app {
  color: rgba(0, 0, 0, 0.85);
  display: flex;
  width: 100%;
  height: 100%;
  flex-direction: column;
  font-family: 'Poppins';
  font-style: normal;
  margin-top: 70px;
}

.contents {
  display: flex;
  flex-direction: column;
}
</style>

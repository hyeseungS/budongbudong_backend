<script setup>
import { ref } from 'vue'
import { RouterLink } from 'vue-router'

const menuKey = ref([window.location.pathname])
</script>

<template>
  <a-config-provider
    :theme="{
      token: {
        colorPrimary: '#378d8b'
      }
    }"
  >
    <a-layout id="components-layout">
      <a-layout-header id="header">
        <div class="logo">
          <!--a태그-->
          <RouterLink to="/">부동부동</RouterLink>
          <img src="@/assets/logo.png" style="width: 35px; ,height: 35px" />
        </div>

        <div class="nav-right">
          <a-button class="signup-button"> 회원가입 </a-button>
          <a-button class="login-button"> 로그인 </a-button>
        </div>

        <a-menu
          v-model:selectedKeys="menuKey"
          class="menu"
          theme="light"
          mode="horizontal"
          :style="{ lineHeight: '64px', float: 'right', borderBottom: 'none' }"
        >
          <a-menu-item key="/"> <RouterLink to="/"> 공지사항 </RouterLink></a-menu-item>
          <a-menu-item key="/"> <RouterLink to="/"> 1 : 1 문의 </RouterLink> </a-menu-item>
          <a-menu-item key="/"> <RouterLink to="/"> 매물 검색 </RouterLink></a-menu-item>
          <a-menu-item key="/"> <RouterLink to="/"> 비교분석 </RouterLink> </a-menu-item>
          <a-menu-item key="/"> <RouterLink to="/"> 관심 매물 </RouterLink> </a-menu-item>
        </a-menu>
      </a-layout-header>
    </a-layout>
  </a-config-provider>
</template>

<style lang="scss" scoped>
#components-layout {
  position: fixed;
  top: 0;
  left: 0;
  height: 65px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 1;
}
#header {
  position: fixed;
  width: 100%;
  overflow: hidden;
  background: #ffffff;

  a {
    font-weight: 600;
    font-size: 24px;
    line-height: 48px;
    padding: 3px;
    color: #092d37;
  }
  .logo {
    display: flex;
    align-items: center;
    float: left;
    height: 64px;
  }
}

#components-layout .menu {
  a {
    font-weight: 600;
    font-size: 12px;
    color: #c4c4c4;
  }
}

#components-layout .nav-right {
  float: right;
  padding-left: 20px;

  button {
    border-radius: 10px;
  }
}

.signup-button {
  background: #ffffff;
  border: 1px solid #092d37;
  color: #092d37;
  font-weight: 600;
}
.login-button {
  margin-left: 10px;
  background: #378d8b;
  color: #ffffff;
}
</style>

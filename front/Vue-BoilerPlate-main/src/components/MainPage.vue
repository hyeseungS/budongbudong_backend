<script setup>
import { ref } from 'vue'
import { Button } from 'ant-design-vue'

defineProps({})
</script>

<template>
  <div class="main">
    <div class="card">
      <div class="card-title">
        <span class="main-title">당신의 미래 집</span
        ><span class="sub-title">을<br />비교 분석해보세요</span>
      </div>

      <img class="card-img" alt="MainPage" src="@/assets/main.png" />
    </div>

    <div class="search-box">
      <div id="box-content">
        <div id="box-title">
          <span class="trial">시/도</span>
          <div class="trial-sub">
            <span>시/군/구</span>
            <span>읍/면/동</span>
          </div>
        </div>
        <div class="box-input">
          <div class="box-select">
            <div class="div-8">서울특별시</div>
            <button></button>
          </div>
          <div class="box-select">
            <div class="div-8">송파구</div>
            <button></button>
          </div>
          <div class="box-select">
            <div class="div-8">잠실동</div>
            <button></button>
          </div>
        </div>
      </div>

      <button id="box-button">Search</button>
    </div>
    <div id="news">
      <span>오늘의 부동산 뉴스</span>
      <div class="fake"></div>
    </div>
    <div id="news">
      <span>오늘의 Top 매물</span>
      <div class="fake"></div>
    </div>
    <div id="news">
      <span>OOO님의 OO동 Top 관심매물</span>
      <div class="fake"></div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
* {
  color: #437a77;
  font:
    600 28px Poppins,
    sans-serif;
}
.card > img {
  margin-left: auto;
  width: auto;
}
.card {
  background: #e9f9f9;
  border-radius: 20px;
  display: flex;
  overflow: hidden;
  width: 100%;
  height: 400px;
  .card-title {
    margin: 100px 0 0 100px;
    font-weight: 600;
    font-size: 32px;
    line-height: 48px;

    color: #437a77;
    .sub-title {
      color: #092d37;
    }
  }
}
.main {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: auto;
  min-height: 100%;
  box-sizing: border-box;
  padding: 0 50px 50px;

  #news {
    margin: 100px 0 0 0;
    display: flex;
    flex-direction: column;
    width: 100%;
    gap: 20px;
  }
  // fake
  .fake {
    width: 100%;
    height: 200px;
    background-color: #e9f9f9;
    border-radius: 20px;
    box-shadow: 0px 4px 4px 0px rgba(1, 115, 153, 0.2);
  }
}

.search-box {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  margin: -60px auto 0;
  width: 70%;
  border-radius: 20px;
  box-shadow: 0px 4px 4px 0px rgba(1, 115, 153, 0.2);
  background-color: #fff;
  padding: 40px 39px 40px 81px;
  @media (max-width: 991px) {
    max-width: 100%;
    flex-wrap: wrap;
    justify-content: center;
  }
  #box-button {
    color: #fff;
    align-self: stretch;
    border-radius: 10px;
    background-color: #378d8b;
    width: 100%;
    max-width: 180px;
    float: right;
    padding: 20px 40px 20px 40px;
    border: none;
    font:
      400 24px Poppins,
      sans-serif;

    @media (max-width: 991px) {
      margin-top: 40px;
      padding: 0 20px;
    }
  }
}

#box-content {
  display: flex;
  flex-direction: column;
  margin: auto 0;
  width: 100%;
  max-width: 650px;

  .box-input {
    align-self: stretch;
    display: flex;
    margin-top: 14px;
    align-items: flex-start;
    justify-content: space-between;
    gap: 19px;
    @media (max-width: 991px) {
      max-width: 100%;
      flex-wrap: wrap;
      justify-content: center;
    }
  }
  .box-select {
    color: #437a77;
    font:
      600 28px Poppins,
      sans-serif;

    display: flex;
    justify-content: space-between;
    gap: 20px;

    button {
      background-image: url('https://cdn.builder.io/api/v1/image/assets/TEMP/6df585f9-3117-4bc7-a5fe-34607cfc3150?');
      background-color: transparent;
      background-repeat: no-repeat;
      background-position: center;
      border: none;
      cursor: pointer;
      padding-left: 16px;
    }
  }

  .img {
    aspect-ratio: 1.25;
    object-fit: contain;
    object-position: center;
    width: 20px;
    fill: #dadee4;
    overflow: hidden;
    align-self: center;
    max-width: 100%;
    margin: auto 0;
  }

  #box-title {
    align-self: stretch;
    display: flex;
    width: 100%;
    align-items: flex-start;
    justify-content: space-between;
    gap: 50px;

    .trial-sub {
      align-self: stretch;
      display: flex;
      width: 227px;
      max-width: 100%;
      align-items: flex-start;
      justify-content: space-between;
      gap: 10px;
    }
    span {
      color: #c4c4c4;
      align-self: stretch;
      font:
        400 14px Poppins,
        sans-serif;
    }
  }
}

@media (max-width: 850px) {
  .card-img {
    display: none;
  }

  .card .card-title {
    span {
      font-size: 1.5rem;
    }
    margin: auto;
  }
}
</style>

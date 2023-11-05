<template>
  <div class="footer">
    <div class="footer-logo">
      <h1>부동부동</h1>
      <span> developers 이가영 | 신혜승</span>
    </div>

    <div class="footer-description">
      <router-link to="/"> 회사소개 </router-link>
      <router-link to="/"> 이용약관 </router-link>
      <router-link to="/"> 개인정보처리방침 </router-link>
      <router-link to="/"> 1:1 문의 </router-link>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.footer {
  display: flex;
  justify-content: space-around;
  box-sizing: border-box;

  height: 150px;
  padding: 20px 10px 30px;

  background: linear-gradient(
    219.84deg,
    rgba(35, 135, 192, 0.3) 4.14%,
    rgba(1, 115, 153, 0.2) 44.22%
  );
  box-shadow: 0px 10px 15px rgba(151, 151, 151, 0.12);

  * {
    color: #092d37;
    text-decoration: none;
  }
  div {
    display: flex;
  }
}
.footer-logo {
  display: flex;
  flex-direction: column;
  text-align: left;
  margin-right: 20px;
  h1 {
    margin: 0 0 20px 0;
    font-weight: 700;
    font-size: 24px;
    line-height: 36px;
  }
  span {
    font-weight: 600;
    font-size: 16px;
    line-height: 24px;
  }
}
.footer-description {
  font-weight: 500;
  font-size: 18px;
  line-height: 27px;
  a {
    padding: 7px;
  }
}

@media (max-width: 350px) {
  .footer {
    display: none;
  }
}
</style>

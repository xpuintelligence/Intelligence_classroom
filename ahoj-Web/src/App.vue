<template>
  <transition>
    <div id="app" v-if="flag">
      <img src="./assets/logo.png" style="width: 100px; heigh: 100px;" alt="logo.png">
      <h1>智慧教室</h1>
      <router-link to="Login">
        <button class="btn btn-primary">登录</button>
      </router-link>
      <router-link to="Signin">
        <button class="btn btn-primary" disabled>注册</button>
      </router-link>
      <router-view/>
    </div>
  </transition>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      flag: false
    };
  },
  mounted() {
    this.flag = !this.flag;
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.v-enter,
.v-leave-to {
  opacity: 0;
  /* transform: translateY(-1000px); */
}

.v-enter-active,
.v-leave-active {
  transition: all 1s ease;
}
</style>

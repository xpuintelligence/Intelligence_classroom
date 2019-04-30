<template>
  <div>
    <img alt="logo.png" src="../assets/logo.png" style="width: 200px">
    <br>
    <router-link to="Login">
      <button class="btn btn-primary">登录</button>
    </router-link>

    <router-link to="Register">
      <button class="btn btn-primary">注册</button>
    </router-link>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: "Index",
    components: {},
    data() {
      return {
        btn_login: false,
        btn_register: false,
      }
    },
    methods: {},
  }
</script>

<style scoped>

</style>

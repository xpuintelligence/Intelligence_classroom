<template>
  <div class="components-Index">

    <img alt="logo.png" src="../assets/logo.png" style="width: 200px">
    <br />

    <div class="container">
      <router-link to="Login">
        <button type="button" class="btn btn-outline-primary">登录</button>
      </router-link>

      <router-link to="/">
        <button type="button" class="btn btn-outline-primary" disabled>注册</button>
      </router-link>

    </div>

  </div>
</template>

<script>
  export default {
    name: "Index",
    components: {},
    data() {
      return {}
    },
    methods: {},
  }
</script>

<style scoped>

</style>

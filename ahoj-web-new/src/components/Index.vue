<template>
  <div>

    <img alt="logo.png" src="../assets/logo.png" style="width: 200px">
    <br>

    <div class="container">
      <router-link to="Login">
        <button type="button" class="btn btn-primary">登录</button>
      </router-link>

      <router-link to="/">
        <button type="button" class="btn btn-primary" disabled>注册</button>
      </router-link>

      <!--
      <router-link to="StudentRegister">
        <button type="button" class="btn btn-primary">学生注册</button>
      </router-link>

      <router-link to="TeacherRegister">
        <button type="button" class="btn btn-primary">教师注册</button>
      </router-link>
      -->

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

<template>
  <div>
    <div style="text-align: center;">
      <transition enter-active-class="flip">
        <img v-show="flag" alt="logo.png" src="../../assets/logo.png" style="width: 500px" class="animated">
      </transition>


      <h1>智慧教室管理系统</h1>
    </div>
  </div>
</template>

<script>

  export default {
    name: "Welcome",
    data() {
      return {
        flag: false, // 页面 动画控制
      }
    },
    components: {},
    mounted() {
      this.flag = true;
    },
  }
</script>

<style scoped>

</style>

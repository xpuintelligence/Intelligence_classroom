<template>
  <div>
    <transition enter-active-class="fadeInRight">
      <div v-show="flag" class="animated">
        <h1>尚未开发注册!</h1>
      </div>
    </transition>
  </div>
</template>

<script>
export default {
  name: "Regist",
  data() {
    return {
      flag: false
    };
  },
  mounted() {
    this.flag = true;
  }
};
</script>

<style scope>
</style>
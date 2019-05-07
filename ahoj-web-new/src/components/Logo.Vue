<template>
  <div>
    <transition enter-active-class="flip">
      <img
        v-if="flag"
        alt="logo.png"
        src="../assets/logo.png"
        style="width: 200px"
        class="animated"
        onclick="window.open('#')"
      >
    </transition>
    <br>
  </div>
</template>

<script>
export default {
  name: "Logo",
  data() {
    return {
      flag: false
    };
  },
  mounted() {
    this.flag = true;
  },
};
</script>

<style scope>
</style>
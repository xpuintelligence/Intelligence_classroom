<template>
  <div>
    <transition enter-active-class="rubberBand">
      <img
        v-show="flag"
        alt="logo.png"
        src="../assets/logo.png"
        style="width: 200px"
        class="animated"
      >
    </transition>
    <br>
  </div>
</template>

<script>
export default {
  name: 'Logo',
  data() {
    return {
      flag: false,
    }
  },
  mounted() {
    this.flag = true;
  },
};
</script>

<style scope>
</style>
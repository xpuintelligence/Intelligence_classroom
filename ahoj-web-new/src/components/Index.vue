<template>
  <div class="components-Index">
    <transition enter-active-class="jackInTheBox">
      <div v-show="flag" class="container animated">
        <router-link to="Login">
          <button type="button" class="btn btn-outline-primary">登录</button>
        </router-link>

        <router-link to="/">
          <button type="button" class="btn btn-outline-primary" disabled>注册</button>
        </router-link>
      </div>
    </transition>
  </div>
</template>

<script>
export default {
  name: "Index",
  components: {},
  data() {
    return {
      flag: false
    };
  },
  methods: {},
  mounted() {
    this.flag = true;
  }
};
</script>

<style scoped>
</style>

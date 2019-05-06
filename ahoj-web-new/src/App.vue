<template>
  <div id="app">
    <Logo></Logo>
    <router-view/>
  </div>
</template>

<script>
import Logo from "@/components/Logo";

export default {
  name: "App",
  components: {
    Logo,
  },
  data() {
    return {};
  },
  mounted() {}
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
</style>

<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  import Login from "@/components/Login";

  export default {
    name: 'App',
    data: function () {
      return {}
    },
    components: {
      Login,
    },
    mounted() {
    }
  }
</script>

<style>
</style>

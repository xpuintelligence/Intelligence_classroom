<template>
  <div id="app" :v-if="flag">
    <!--<router-view/>-->
    <transition>
      <router-view></router-view>
    </transition>
    <!--<Index></Index>-->
  </div>
</template>

<script>
  import Index from '@/components/Index'

  export default {
    name: 'App',
    components: {
      Index,
    },
    data: function () {
      return {
        flag: false,
      }
    },
    mounted: function () {
      this.flag = !this.flag;
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }


  .v-enter,
  .v-leave-to {
    /*opacity: 0;*/
    transform: translateX(1000px);
  }

  .v-enter-active,
  .v-leave-active {
    transition: all 1s ease;
  }
</style>

<template>
  <div id="app">

    <router-view/>

    <!--<div v-for="(item, index) in audios" :key="index">-->
      <!--<VueAudio :theUrl="item.url" :theControlList="item.controlList"/>-->
    <!--</div>-->

  </div>
</template>

<script>

  import Login from "@/components/Login";
  import VueAudio from './components/VueAudio'

  export default {
    name: 'App',
    data: function () {
      return {
        flag: true,
        // audios: [
        //   {
        //     url: 'http://106.12.202.93/aaa.mp3',
        //     controlList: 'onlyOnePlaying'
        //   },
        //   {
        //     url: 'http://106.12.202.93/aaa.mp3',
        //     controlList: 'noDownload noMuted onlyOnePlaying'
        //   }, {
        //     url: 'http://106.12.202.93/aaa.mp3',
        //     controlList: 'noDownload noVolume noMuted onlyOnePlaying'
        //   }, {
        //     url: 'http://106.12.202.93/aaa.mp3',
        //     controlList: 'noDownload noSpeed onlyOnePlaying'
        //   }
        // ]
      }
    },
    components: {
      Login,
      VueAudio,
    },
  }
</script>

<style>
</style>

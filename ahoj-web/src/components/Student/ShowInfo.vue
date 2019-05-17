<template>
  <div v-loading="loading" element-loading-text="拼命加载中"
       element-loading-spinner="el-icon-loading">

    <Overview></Overview>

    <br>
    <state-of-class></state-of-class>

    <br>
    <charts></charts>

  </div>
</template>

<script>

  import XChart from '@/components/charts/test'
  import Mallki from "@/components/MyComponents/Mallki";
  import Overview from "@/components/Student/ShowInfo/Overview";
  import StateOfClass from "@/components/Student/ShowInfo/StateOfClass";
  import Charts from "@/components/Student/ShowInfo/Charts";

  export default {
    name: "ShowInfo",
    data() {
      return {
        userData: { // 用户信息
        },
        todayCourse: { // 今天的课程信息
        },
        loading: true,
        isWeekend: false,
      }
    },
    computed: {},
    beforeCreate: function () {
    },
    methods: {},
    mounted: function () {
      setTimeout(() => {
        this.loading = false;
      }, 1000);
    },
    components: {
      Charts,
      StateOfClass,
      Overview,
      Mallki,
      XChart
    }
  }
</script>

<style scoped>
  body {
    margin: 0;
  }
</style>

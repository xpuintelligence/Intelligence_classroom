<template>
  <div>
    本月
  </div>
</template>

<script>
  export default {
    name: "GetThisMonthCourse",
    components: {},
    data() {
      return {}
    },
    async mounted() {
      // 获取用户数据
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // 获取学期的上课信息
      await this.$http.post('wisdom_web/studetnAttendance/allCourseAttThisMonth', {}).then(res => {
        console.log('this month :');
        console.log(res);
        console.log('--------');
      }).catch(err => {
        console.log("--------err-------");
        console.log(err);
      });
    },
    methods: {},
  }
</script>

<style scoped>

</style>

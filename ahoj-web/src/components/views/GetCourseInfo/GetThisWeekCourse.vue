<template>
  <div>
    本周
  </div>
</template>

<script>
  export default {
    name: "GetThisWeekCourse",
    components: {},
    data() {
      return {}
    },
    async mounted() {
      // 获取用户数据
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // 获取学期的上课信息
      await this.$http.post('wisdom_web/studetnAttendance/allCourseAttThisWeek', {}).then(res => {
        console.log('this week :');
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

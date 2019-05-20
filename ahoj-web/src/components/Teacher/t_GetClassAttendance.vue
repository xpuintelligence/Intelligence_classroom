<template>
  <div>
    <h1>
      考勤查询
    </h1>
  </div>
</template>

<script>
  export default {
    name: "t_GetClassAttendance",
    data() {
      return {

      }
    },
    async mounted() {
      // 获取学期的上课信息
      await this.$http.post('wisdom_web/teacherAttendance/getAttOfClassAtASpellTime', {
        start: '2019-05-05 00:00:00',
        end: '2019-05-19 00:00:00',
        status: 6
      }).then(res => {
        console.log(res);
      }).catch(err => {
        console.log("--------err-------");
        console.log(err);
      });
    }
  }
</script>

<style scoped>

</style>

<template>
  <div>
    <el-table :data="thisMonthData" stripe style="width: 100%">
      <el-table-column prop="time" label="日期" width="180"></el-table-column>

      <el-table-column prop="name" label="课程名" width="180"></el-table-column>

      <el-table-column prop="classroom_id" label="教室"></el-table-column>

      <el-table-column prop="teacher_name" label="老师"></el-table-column>

      <el-table-column prop="goal" label="成绩"></el-table-column>

      <el-table-column>
        <el-button type="text" @click="hasQuestion"><i class="el-icon-question"></i></el-button>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  export default {
    name: "GetThisMonthCourse",
    components: {},
    data() {
      return {
        thisMonthData: [],
      }
    },
    async mounted() {
      // 获取用户数据
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // 获取学期的上课信息
      await this.$http.post('wisdom_web/studetnAttendance/allCourseAttThisMonth', {}).then(res => {
        this.thisMonthData = res.body.data;
      }).catch(err => {
        console.log("--------err-------");
        console.log(err);
      });
    },
    methods: {
      hasQuestion() {
        alert('有问题，不会百度啊？');
      },
    },
  }
</script>

<style scoped>

</style>

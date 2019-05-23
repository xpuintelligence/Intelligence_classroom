<template>
  <div>
    <el-table :data="lastWeekData" stripe style="width: 100%">
      <el-table-column prop="time" label="日期" width="180"></el-table-column>

      <el-table-column prop="name" label="课程名" width="180"></el-table-column>

      <el-table-column prop="classroom_id" label="教室"></el-table-column>

      <el-table-column prop="head_up_rate" label="班级平均抬头率"></el-table-column>

      <el-table-column>
        <el-button type="text" @click="hasQuestion"><i class="el-icon-question"></i></el-button>
      </el-table-column>

      <el-table-column>
        <el-button type="text" @click="moreInfo">详情</el-button>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "GetLastWeekCourse",
    components: {},
    data() {
      return {
        lastWeekData: [],
      }
    },
    async mounted() {
      // 获取用户数据
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // 获取学期的上课信息
      await this.$http.post('wisdom_web/studetnAttendance/allCourseAttLastWeek', {}).then(res => {
        this.lastWeekData = res.body.data;
      }).catch(err => {
        console.log("--------err-------");
        console.log(err);
      });

      for (let i = 0; i < this.lastWeekData.length; i++) {
        this.lastWeekData[i].time = new Date(this.lastWeekData[i].time).toLocaleDateString()
          + ' '
          + new Date(this.lastWeekData[i].time).toLocaleTimeString();
      }
    },
    methods: {
      hasQuestion() {
        this.$prompt('问题描述', '意见反馈', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({value}) => {
          this.$message({
            type: 'success',
            message: '我们已收到您的反馈意见：' + value + '，会尽快处理。'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },

      moreInfo() {
        this.$message({
          type: 'info',
          message: '敬请期待...'
        })
      }
    },
  }
</script>

<style scoped>

</style>

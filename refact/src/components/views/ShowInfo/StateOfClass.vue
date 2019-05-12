<template>
  <el-card class="box-card" v-if="isWeekend === false">
    <div slot="header" class="clearfix">
      <span>今日上课状态</span>
    </div>
    <div class="text item">
      <el-row :gutter="12">
        <el-col :span="12">
          <el-card>
            <label>总成绩</label>
            <el-divider direction="vertical"></el-divider>
            <!--<el-progress type="circle" :percentage="todayCourse.attendanceTotalScore" :width="200" status="text">-->
            <!--{{todayCourse.attendanceTotalScore}}分-->
            <!--</el-progress>-->
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card>
            <label>抬头率</label>
            <el-divider direction="vertical"></el-divider>
            <!--<el-progress type="circle" :percentage="todayCourse.headUpScore" :width="200"></el-progress>-->
          </el-card>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
  export default {
    name: "StateOfClass",
    data() {
      return {
        isWeekend: false,
        todayCourse: {}
      }
    },
    mounted() {

      // 如果是周六、周日就不显示这一栏
      if (new Date().getDay() === 6 || new Date().getDay() === 0) {
        this.isWeekend = true;
        return;
      }

      // 获取今天的上课状态信息
      this.$http.post('wisdom_web/studentCourseInfo/today', {}).then(res => {
        this.todayCourse = res.data.data;
        // console.log(res.data.data)
      }).catch(err => {
        console.log("--------err-------");
        console.log(err);
      });
    }
  }
</script>

<style scoped>

</style>

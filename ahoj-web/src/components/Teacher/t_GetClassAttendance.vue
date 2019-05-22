<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="上周">
        <GetLastWeekCourse></GetLastWeekCourse>
      </el-tab-pane>

      <el-tab-pane label="本周">
        <GetThisMonthCourse></GetThisMonthCourse>
      </el-tab-pane>

      <el-tab-pane label="上月">
        <GetLastMonthCourse></GetLastMonthCourse>
      </el-tab-pane>

      <el-tab-pane label="本月">
        <GetThisMonthCourse></GetThisMonthCourse>
      </el-tab-pane>

      <el-tab-pane label="某段时间">
        <GetSomeDayCourse></GetSomeDayCourse>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import GetLastMonthCourse from "@/components/Teacher/GetClassAttendance/GetLastMonthCourse";
  import GetLastWeekCourse from "@/components/Teacher/GetClassAttendance/GetLastWeekCourse";
  import GetSomeDayCourse from "@/components/Teacher/GetClassAttendance/GetSomeDayCourse";
  import GetThisMonthCourse from "@/components/Teacher/GetClassAttendance/GetThisMonthCourse";
  import GetThisWeekCourse from "@/components/Teacher/GetClassAttendance/GetThisWeekCourse";

  export default {
    name: "t_GetClassAttendance",
    components: {GetLastWeekCourse, GetLastMonthCourse, GetThisMonthCourse, GetSomeDayCourse},
    data() {
      return {}
    },
    async mounted() {

    }
  }
</script>

<style scoped>

</style>

<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="上周">
        <GetLastWeekCourse></GetLastWeekCourse>
      </el-tab-pane>

      <el-tab-pane label="本周">
        <GetThisWeekCourse></GetThisWeekCourse>
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
  import GetThisWeekCourse from "@/components/Student/GetCourseInfo/GetThisWeekCourse";
  import GetLastWeekCourse from "@/components/Student/GetCourseInfo/GetLastWeekCourse";
  import GetLastMonthCourse from "@/components/Student/GetCourseInfo/GetLastMonthCourse";
  import GetThisMonthCourse from "@/components/Student/GetCourseInfo/GetThisMonthCourse";
  import GetSomeDayCourse from "@/components/Student/GetCourseInfo/GetSomeDayCourse";

  export default {
    name: "GetCourseInfo",
    components: {GetSomeDayCourse, GetThisMonthCourse, GetLastMonthCourse, GetLastWeekCourse, GetThisWeekCourse},
    data() {
      return {}
    },
    methods: {},
    async mounted() {
    },
  }
</script>

<style scoped>

</style>

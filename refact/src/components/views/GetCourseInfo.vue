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
    </el-tabs>

    <hr>

    <h2>考勤信息查询</h2>

    <div class="block">
      <el-date-picker
        v-model="time"
        type="datetimerange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期">
      </el-date-picker>
    </div>

    <Table></Table>

  </div>
</template>

<script>
  import GetThisWeekCourse from "@/components/views/GetCourseInfo/GetThisWeekCourse";
  import GetLastWeekCourse from "@/components/views/GetCourseInfo/GetLastWeekCourse";
  import GetLastMonthCourse from "@/components/views/GetCourseInfo/GetLastMonthCourse";
  import GetThisMonthCourse from "@/components/views/GetCourseInfo/GetThisMonthCourse";
  import Table from "@/components/views/GetCourseInfo/Table";

  export default {
    name: "GetCourseInfo",
    components: {Table, GetThisMonthCourse, GetLastMonthCourse, GetLastWeekCourse, GetThisWeekCourse},
    data() {
      return {
        time: [new Date(), new Date()],
      }
    },
    methods: {},
    async mounted() {
    },
  }
</script>

<style scoped>

</style>

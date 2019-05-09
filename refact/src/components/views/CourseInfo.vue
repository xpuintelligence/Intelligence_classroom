<template>
  <div>

    <el-progress type="circle" :percentage="0"></el-progress>
    <el-progress type="circle" :percentage="25"></el-progress>
    <el-progress type="circle" :percentage="80" color="#8e71c7"></el-progress>
    <el-progress type="circle" :percentage="100" status="success"></el-progress>
    <el-progress type="circle" :percentage="50" status="exception"></el-progress>
    <el-progress type="circle" :percentage="100" status="text">Done</el-progress>

    <hr>

    <el-progress :text-inside="true" :stroke-width="18" :percentage="0"></el-progress>
    <el-progress :text-inside="true" :stroke-width="18" :percentage="70"></el-progress>
    <el-progress :text-inside="true" :stroke-width="18" :percentage="80" color="rgba(142, 113, 199, 0.7)"></el-progress>
    <el-progress :text-inside="true" :stroke-width="18" :percentage="100" status="success"></el-progress>
    <el-progress :text-inside="true" :stroke-width="18" :percentage="50" status="exception"></el-progress>

    <hr>

    <el-progress :percentage="0"></el-progress>
    <el-progress :percentage="70"></el-progress>
    <el-progress :percentage="80" color="#8e71c7"></el-progress>
    <el-progress :percentage="100" status="success"></el-progress>
    <el-progress :percentage="50" status="exception"></el-progress>

    <hr>

  </div>
</template>

<script>
  export default {
    name: "CourseInfo",
    data() {
      return {
        p: true
      }
    },
    async mounted() {
      try {
        let res = await this.$http.post(
          "wisdom_web/studentCourseInfo/thisMonth",
          {},
          {}
        );
        if (res.data.msg === "true") {
          console.log(res.data.data);
        } else {
          this.$message.error(res.data.msg);
        }
      } catch (e) {
        this.$message.error("抱歉，服务器出了点问题");
      }
    },
    methods: {},
  }
</script>

<style scoped>

</style>

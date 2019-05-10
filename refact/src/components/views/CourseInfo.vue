<template>
  <div>
    <el-progress type="circle" :percentage="0"></el-progress>
    <el-progress type="circle" :percentage="25"></el-progress>
    <el-progress type="circle" :percentage="randomNumber" color="#8e71c7"></el-progress>
    <el-progress type="circle" :percentage="100" status="success"></el-progress>
    <el-progress type="circle" :percentage="50" status="exception"></el-progress>
    <el-progress type="circle" :percentage="100" status="text">Done</el-progress>

    <hr>

    <el-progress :text-inside="true" :stroke-width="18" :percentage="0"></el-progress>
    <el-progress :text-inside="true" :stroke-width="18" :percentage="70"></el-progress>
    <el-progress :text-inside="true" :stroke-width="18" :percentage="80"
                 color="rgba(142, 113, 199, 0.7)"></el-progress>
    <el-progress :text-inside="true" :stroke-width="18" :percentage="100" status="success"></el-progress>
    <el-progress :text-inside="true" :stroke-width="18" :percentage="50" status="exception"></el-progress>

    <hr>

    <el-progress :percentage="0"></el-progress>
    <el-progress :percentage="70"></el-progress>
    <el-progress :percentage="80" color="#8e71c7"></el-progress>
    <el-progress :percentage="100" status="success"></el-progress>
    <el-progress :percentage="50" status="exception"></el-progress>

    <hr>

    {{data}}

    <hr>
    <div v-for="(val,key) in data">
      <!--{{key}} -&#45;&#45; {{val}}-->
      <p v-for="(v, key) in val">
        {{v}} === {{key}}
      </p>
    </div>
  </div>
</template>

<script>
  export default {
    name: "CourseInfo",
    data() {
      return {
        flag: false,
        data: {},
      }
    },
    computed: {
      randomNumber() {
        return parseInt(Math.random() * 100);
      }
    },
    mounted() {
      // wisdom_web/studentCourseInfo/thisWeek
      // this.$http.post('wisdom_web/studentCourseInfo/thisWeek', {
      // }).then(function (res) {
      //   console.log(res);
      // }).catch(function (err) {
      //   console.log("err");
      //   console.log(err);
      // })

      this.$http.post('wisdom_web/studentCourseInfo/thisWeek', {
        // emulateJSON: true,
        // credentials: true
      }).then(res => {
        console.log(res);
        this.data = res.data.data;
        // console.log(this.data.data)
      }).catch(err => {
        console.log("err-------");
        console.log(err);
        this.$message.error("抱歉，服务器出错");
      })
    },
    methods: {},
  }
</script>

<style scoped>

</style>

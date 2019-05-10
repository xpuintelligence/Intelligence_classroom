<template>
  <div v-loading="loading" element-loading-text="拼命加载中"
       element-loading-spinner="el-icon-loading">

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>概览</span>
        <el-button style="padding: 3px 0;float: right;" type="text">
          {{new Date()}}
        </el-button>
      </div>

      <div class="container">
        <div class="row clearfix">
          <div class="col-md-3 column">
            <el-card :body-style="{ padding: '0px' }">
              <img :src="userData.picture" style="width: 100%;"><br>
            </el-card>
          </div>

          <div class="col-md-5 column">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>基本信息</span>
              </div>
              <div class="text item" style="font-size: 12px">
                学号：{{userData.id}}<br>
                姓名：{{userData.name}}<br>
                班级：{{userData.classId}}<br>
                性别：{{userData.sex === 'male' ? '男' : '女'}}<br>
                邮箱：{{userData.email}}<br>
                手机：{{userData.phoneCode}}
              </div>
            </el-card>
          </div>

          <div class="col-md-4 column">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>今日课程</span>
              </div>
              <div class="text item" style="font-size: 12px">
                课程：{{todayCourse.courseName}}<br>
                时间：{{courseDate}}<br>
                教室：{{todayCourse.classroomId}}<br>
                教师：{{todayCourse.teacherName}}<br>
              </div>
            </el-card>
          </div>
        </div>
      </div>
    </el-card>

    <el-divider><i class="el-icon-more"></i></el-divider>

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>今日上课状态</span>
      </div>
      <div class="text item">
        <el-row :gutter="12">
          <el-col :span="12">
            <el-card shadow="always">
              <label>总成绩</label>
              <el-divider direction="vertical"></el-divider>
              <el-progress type="circle" :percentage="todayCourse.attendanceTotalScore" :width="200" status="text">
                {{todayCourse.attendanceTotalScore}}分
              </el-progress>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card shadow="hover">
              <label>抬头率</label>
              <el-divider direction="vertical"></el-divider>
              <el-progress type="circle" :percentage="todayCourse.headUpScore" :width="200"></el-progress>
            </el-card>
          </el-col>
        </el-row>

      </div>
    </el-card>

    <hr>
    <div id='myChart' style='width:300px; height:300px' disabled></div>
    <hr>

  </div>
</template>

<script>

  export default {
    name: "ShowInfo",
    data() {
      return {
        userData: { // 用户信息
        },
        todayCourse: { // 今天的课程信息
        },
        loading: true,
      }
    },
    computed: {
      courseDate() {
        let d = new Date(this.todayCourse.time);
        return d.toLocaleTimeString();
      }
    },
    methods: {
      drawLine() {
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('myChart'), 'macarons');
        // 绘制图表
        myChart.setOption({
          title: {text: '哈哈哈哈哈哈哈哈哈哈'},
          tooltip: {},
          xAxis: {
            data: ["1", "2", "3", "4", "5", "6"]
          },
          yAxis: {},
          series: [{
            name: 'name',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
          }]
        });
      },
    },
    mounted: function () {
      this.drawLine();

      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      this.$http.post('wisdom_web/studentCourseInfo/today', {}).then(res => {
        this.todayCourse = res.data.data.data[0];
        this.loading = false; // loading 关闭
        console.log(this.todayCourse);
      }).catch(err => {
        console.log("err-------");
        console.log(err);
        this.$message.error("server error!");
      });

    },
  }
</script>

<style scoped>

  body {
    margin: 0;
  }

</style>

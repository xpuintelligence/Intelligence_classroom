<template>

  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span><Mallki class-name="Overview" text="概览"></Mallki></span>
        <el-button style="padding: 3px 0;float: right;" type="text">
          <Mallki class-name="date" :text="(new Date()).toDateString()"></Mallki>
        </el-button>
      </div>

      <div class="container">
        <div class="row clearfix">

          <!-- 头像 -->
          <div class="col-md-3 column">
            <el-card :body-style="{ padding: '0px' }">
              <img :src="userData.picture" style="width: 100%;" alt="头像" />
            </el-card>
          </div>

          <div class="col-md-5 column">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>基本信息</span>
              </div>
              <div class="text item" style="font-size: 12px">
                姓名：{{userData.name}}<br>
                班级：{{userData.classId}}<br>
                性别：{{userData.sex === "male" ? '男': '女'}}<br>
                手机：{{userData.phoneCode}}<br>
                <el-button icon="el-icon-key" type="text" style="color: dodgerblue" @click="changePassword">修改密码</el-button>
              </div>
            </el-card>
          </div>

          <div class="col-md-4 column">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>今日课程</span>
              </div>
              <div class="text item" style="font-size: 12px">
                <!--{{todayCourse}}-->
                课程：{{todayCourse.courseName}}<br>
                时间：{{new Date(todayCourse.time).toLocaleTimeString()}}<br>
                地点：{{todayCourse.classroomId}}<br>
                老师：{{todayCourse.teacherName}}<br>
              </div>
            </el-card>
          </div>
        </div>
      </div>
    </el-card>
  </div>

</template>

<script>
  import Mallki from "@/components/MyComponents/Mallki";

  export default {
    name: "Overview",
    components: {Mallki},
    data() {
      return {
        userData: {},
        todayCourse: {},
      }
    },
    mounted() {
      // 获取用户数据
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // console.log(new Date().getDay());  周六是6 周日是0
      if (new Date().getDay() === 6 || new Date().getDay() === 0) { // 如果是周末就不获取信息了
        this.todayCourse = '今天没有课哦~';
        return;
      }
      // 获取今天课程信息
      this.$http.post('wisdom_web/studentCourseInfo/today', {}).then(res => {
          this.todayCourse = res.data.data.data[0];
          // console.log(res.data.data.data[0])
      }).catch(err => {
        console.log("--------err-------");
        console.log(err);
        // this.$message.error("server error!");
      });
    },
    methods: {
      changePassword() {

        // 验证邮箱。。。。
        this.$prompt('请输入邮箱', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
          inputErrorMessage: '邮箱格式不正确'
        }).then(({ value }) => {
          this.$message({
            type: 'success',
            message: '你的邮箱是: ' + value
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });

      },
    },
  }
</script>

<style scoped>

</style>

<template>

  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span><Mallki class-name="Overview" text="概览"></Mallki></span>
        <el-button style="padding: 3px 0;float: right;" type="text">
          <Mallki class-name="date" :text="(new Date()).toLocaleDateString()"></Mallki>
        </el-button>
      </div>

      <el-row :gutter="10">

        <!--头像-->
        <el-col :span="6">
          <el-card :body-style="{ padding: '0px' }">
            <img :src="userData.picture" style="width: 100%;" alt="头像"/>
          </el-card>
        </el-col>

        <el-col :span="9">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>基本信息</span>
            </div>
            <div class="text item" style="font-size: 12px">
              姓名：{{userData.name}}<br>
              班级：{{userData.className}}<br>
              性别：{{userData.sex === "male" ? '男': '女'}}<br>
              手机：{{userData.phone}}<br>
              <el-button icon="el-icon-key" type="text" style="color: dodgerblue" @click="changePassword">
                修改密码
              </el-button>
            </div>
          </el-card>
        </el-col>

        <el-col :span="9">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>今日课程</span>
            </div>
            <div class="text item" style="font-size: 12px">
              <div v-if="hasCourse === false">{{todayCourse}}<br></div>

              <div v-if="hasCourse === true">
                课程：{{todayCourse.courseName}}<br>
                时间：{{new Date(todayCourse.time).toLocaleTimeString()}}<br>
                地点：{{todayCourse.classroomId}}<br>
                老师：{{todayCourse.teacherName}}<br>
                课程资料：{{todayCourse.courseNode}}<br>
              </div>
            </div>
          </el-card>
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>我的消息</span>
            </div>
            <div style="font-size: 12px">
              {{message}}<br>
              <el-button icon="el-icon-message" type="text" style="color: dodgerblue" @click="sendMsg">
                发送消息
              </el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>

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
        hasCourse: true,
        message: '没有收到消息哦~',
      }
    },
    async mounted() {
      // 获取用户数据
      this.userData = JSON.parse(sessionStorage.getItem('userData'));
      // 获取留言消息
      let test = JSON.parse(localStorage.getItem('message'));
      if (test.data !== null) {
        this.message = test.data;
      }

      // console.log(new Date().getDay());  周六是6 周日是0
      if (new Date().getDay() === 6 || new Date().getDay() === 0) { // 如果是周末就不获取信息了
        this.todayCourse = '今天没有课哦~';
        this.hasCourse = false;
        return;
      }

      // 获取今天课程信息
      await this.$http.post('wisdom_web/studentCourseInfo/today', {}).then(res => {
        this.todayCourse = res.body.data;
      }).catch(err => {
        console.log("--------err-------");
        console.log(err);
      });

      if (this.todayCourse.length === 0) {
        this.todayCourse = '今天没有课哦~';
        this.hasCourse = false;
        return;
      }

      this.todayCourse = this.todayCourse[0];
    },
    methods: {
      changePassword() {
        // 验证邮箱。。。。
        this.$prompt('请输入邮箱', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
          inputErrorMessage: '邮箱格式不正确'
        }).then(({value}) => {
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
      sendMsg() {
        this.$msgbox('功能暂未启用', '发送信息');
      }
    },
  }
</script>

<style scoped>

  .el-col {
    border-radius: 4px;
  }

  .bg-purple-dark {
    background: #99a9bf;
  }

  .bg-purple {
    background: #d3dce6;
  }

  .bg-purple-light {
    background: #e5e9f2;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }

</style>

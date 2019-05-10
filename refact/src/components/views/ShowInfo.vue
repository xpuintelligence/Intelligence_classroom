<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>个人信息</span>
        <el-button style="float: right; padding: 3px 0" type="text">
          <el-badge :value="12" class="item">操作按钮</el-badge>
        </el-button>
      </div>

      <div class="container">
        <div class="row clearfix">
          <div class="col-md-4 column">
            <el-card :body-style="{ padding: '0px' }">
              <img :src="userData.picture" style="width: 100%;"><br>
            </el-card>
          </div>

          <div class="col-md-4 column">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>概览</span>
              </div>
              <div class="text item">
                学号：{{userData.id}}<br>
                姓名：{{userData.name}}<br>
                班级：{{userData.classId}}<br>
                性别：{{userData.sex === 'male' ? '男' : '女'}}<br>
              </div>
            </el-card>
          </div>

          <div class="col-md-4 column">
          </div>
        </div>
      </div>
    </el-card>

    {{userData}}

    <hr>

    {{todayCourse}}

    <hr>

    <label>今日上课状态</label>
    <el-divider direction="vertical"></el-divider>
    <el-progress type="circle" :percentage="20" :width="200"></el-progress>

  </div>
</template>

<script>
  export default {
    name: "test",
    data() {
      return {
        userData: { // 用户信息
        },
        todayCourse: { // 今天的课程信息
        },
      }
    },
    methods: {},
    mounted: function () {
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      this.$http.post('wisdom_web/studentCourseInfo/today', {}).then(res => {
        this.todayCourse = res.data.data.data;
        console.log(this.todayCourse);
      }).catch(err => {
        console.log("err-------");
        console.log(err);
        this.$message.error("抱歉，服务器出错");
      })
    },
  }
</script>

<style scoped>

</style>

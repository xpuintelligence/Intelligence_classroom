<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <!--<span>基本信息</span>-->
        <Mallki class-name="date" text="基本信息"></Mallki>
      </div>
      <div style="font-size: 12px">
        ID：{{userData.id}}<br>
        姓名：{{userData.name}}<br>
        学院：{{userData.college_name}}<br>
        职位：{{userData.position_name}}<br>
      </div>
    </el-card>

    <br/>
    <el-card>
      <div slot="header" class="todayCourse">
        <!--<span>基本信息</span>-->
        <Mallki class-name="date" text="今天的课程"></Mallki>
      </div>
      <div style="font-size: 12px">
        <el-table :data="todaySource" stripe style="width: 100%">
          <el-table-column prop="className" label="课程名" width="180"></el-table-column>

          <el-table-column prop="classTime" label="时间" width="180"></el-table-column>

          <el-table-column prop="classRoom" label="教室"></el-table-column>

          <el-table-column prop="classBan" label="班级"></el-table-column>

          <el-table-column prop="studentNum" label="班级人数"></el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
  import Mallki from "@/components/MyComponents/Mallki";

  export default {
    name: "t_MyInfo",
    components: {Mallki},
    data() {
      return {
        userData: {},
        todaySource: [
          {
            className: '数据结构',
            classTime: '08:10:00',
            classRoom: 'A440',
            classBan: '软件工程1701班',
            studentNum: 37,
          },
          {
            className: '计算方法',
            classTime: '10:10:00',
            classRoom: 'C1103',
            classBan: '计算机科学与技术1701班',
            studentNum: 44,
          },
          {
            className: '数据结构上机',
            classTime: '19:00:00',
            classRoom: '实验楼440',
            classBan: '软件工程1701班',
            studentNum: 37,
          },
        ]
      }
    },
    async mounted() {
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

    }
  }
</script>

<style scoped>

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
  }

</style>

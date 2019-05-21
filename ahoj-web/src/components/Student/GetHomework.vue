<template>
  <div>
    <el-card class="box-card">
      <div slot="header">
        <Mallki class="homework1" :text="homeworkObj[0].courseName"></Mallki>
      </div>
      <div class="作业内容">
        作业内容：{{homeworkObj[0].text}}<br/>
        截止日期：{{homeworkObj[0].deadLine}}
      </div>
    </el-card>

    <el-card class="box-card">
      <div slot="header">
        <Mallki class="homework1" :text="homeworkObj[2].courseName"></Mallki>
      </div>
      <div class="作业内容">
        作业内容：{{homeworkObj[2].text}}<br/>
        截止日期：{{homeworkObj[2].deadLine}}
      </div>
      <el-button icon="el-icon-sort" type="primary" @click="pushHomework">
        提交作业
      </el-button>
    </el-card>

    <el-card class="box-card">
      <div slot="header">
        <Mallki class="homework1" :text="homeworkObj[1].courseName"></Mallki>
      </div>
      <div class="作业内容">
        作业内容：{{homeworkObj[1].text}}<br/>
        截止日期：{{homeworkObj[1].deadLine}}
      </div>
    </el-card>
  </div>
</template>

<script>
  import Mallki from "@/components/MyComponents/Mallki";

  export default {
    name: "GetHomework",
    components: {Mallki},
    data() {
      return {
        homeworkObj: [
          {
            courseName: 'J2EE与中间件技术',
            deadLine: new Date().toLocaleDateString(),
            text: '预习11章内容，' + '第10章课后练习3,4,5,19,21题。',
          },
          {
            courseName: '企业管理与技术经济',
            deadLine: new Date().toLocaleDateString(),
            text: '交上一次要求写的论文《管理是什么》',
          },
          {
            courseName: 'UNIX系统程序设计',
            deadLine: new Date().toLocaleDateString(),
            text: '总结上一节课的内容，写一篇博客，提交链接给老师。',
          },
        ],

      }
    },
    methods: {
      pushHomework() {
        this.$prompt('博客链接', '提交作业', {
          confirmButtonText: '提交',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          this.$message({
            type: 'success',
            message: '已发送',
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消'
          });
        });
      }
    },
  }
</script>

<style scoped>
  .box-card {
    width: 480px;
  }
</style>

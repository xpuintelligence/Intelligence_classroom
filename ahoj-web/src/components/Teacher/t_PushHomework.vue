<template>
  <div>
    <el-card style="width: 500px">
      <div slot="header">
        <Mallki class="pushHomework" text="发布作业"></Mallki>
      </div>
      <el-divider content-position="left">课程名</el-divider>
      <el-input class="title" v-model="courseName"></el-input>

      <el-divider content-position="left">班级</el-divider>
      <el-input class="title" v-model="className"></el-input>

      <el-divider content-position="left">描述</el-divider>
      <el-input type="textarea" v-model="comment"></el-input>

      <el-divider content-position="left">截止日期</el-divider>
      <el-date-picker
        v-model="deadLine"
        type="date"
        placeholder="选择日期">
      </el-date-picker>

      <el-divider content-position="left">语音/图片/ppt/pdf/word</el-divider>
      <el-upload
        class="upload-demo"
        drag
        action="https://jsonplaceholder.typicode.com/posts/"
        multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传jpg/png/mp3/flv/ppt/pptx/doc/docx/pdf文件，且不超过50M</div>
      </el-upload>

      <el-button type="primary" style="float: right" @click="submit">提交</el-button>
    </el-card>
  </div>
</template>

<script>
  import Mallki from "@/components/MyComponents/Mallki";
  export default {
    name: "t_PushHomework",
    components: {Mallki},
    data() {
      return {
        courseName: '',
        deadLine: '',
        comment: '',
        className: '',
      }
    },
    methods: {
      submit() {
        if (this.source === '' || this.comment === '') {
          this.$message({
            message: '表达均不能为空!',
            type: 'error'
          });
          return;
        }

        this.$message({
          message: '发布作业成功',
          type: 'success'
        });
      }
    }
  }
</script>

<style scoped>

</style>

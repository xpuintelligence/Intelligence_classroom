<template>
  <div>

    <el-card style="width: 500px">
      <el-divider content-position="left">课程名</el-divider>
      <el-input class="title" v-model="source"></el-input>

      <el-divider content-position="left">详细描述</el-divider>
      <el-input type="textarea" v-model="comment"></el-input>

      <el-divider content-position="left">语音/图片</el-divider>
      <el-upload
        class="upload-demo"
        drag
        action="https://jsonplaceholder.typicode.com/posts/"
        multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传jpg/png/mp3/flv文件，且不超过5M</div>
      </el-upload>

      <el-button type="primary" style="float: right" @click="submit">提交</el-button>
    </el-card>

  </div>
</template>

<script>
  export default {
    name: "AnsweringQuestions",
    data() {
      return {
        source: '',
        comment: '',
      }
    },
    methods: {
      submit() {
        this.$message({
          message: '发送成功',
          type: 'success'
        });
      }
    }
  }
</script>

<style scoped>

</style>

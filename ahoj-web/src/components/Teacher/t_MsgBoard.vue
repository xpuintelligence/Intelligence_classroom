<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <Mallki class="question" text="同学们的提问"></Mallki>
          </div>
          <div>

            <el-card shadow="hover">
              <div slot="header">
                <el-button style="padding: 0px 0" type="text">
                  问题ID：{{parseInt(Math.random()*1000)}}
                </el-button>
              </div>
              匿名：老师，您好，想想问一下我们的考试大约在几号，题型都有哪些呢？
            </el-card>

            <br/>
            <el-card shadow="hover">
              <div slot="header">
                <el-button style="padding: 0px 0" type="text">
                  问题ID：{{parseInt(Math.random()*1000)}}
                </el-button>
              </div>
              班级：计算机科学与技术1701班<br/>
              姓名：刘景亮<br/>
              老师你好呀，麻烦上传一下PPT啦，我们预习复习。
            </el-card>

            <el-divider><i class="el-icon-more-outline"></i><i class="el-icon-more-outline"></i></el-divider>
          </div>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card style="width: 500px">
          <el-divider content-position="left">问题ID</el-divider>
          <el-input class="title" v-model="source"></el-input>

          <el-divider content-position="left">描述</el-divider>
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
      </el-col>
    </el-row>

  </div>
</template>

<script>
  import Mallki from "@/components/MyComponents/Mallki";

  export default {
    name: "t_MsgBoard",
    components: {Mallki},
    data() {
      return {
        source: '',
        comment: '',
      }
    },
    methods: {
      submit() {
        if (this.source === '' || this.comment === '') {
          this.$message({
            message: '问题ID和描述不能为空!',
            type: 'error'
          });
          return;
        }

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


<template>
  <div>
    <el-card class="box-card" v-if="flag">
      <div slot="header" class="clearfix">
        <span>个人信息</span>
        <el-button style="float: right; padding: 3px 0" type="text">
          <el-badge :value="12" class="item">操作按钮</el-badge>
        </el-button>
      </div>
      <div class="container">
        <div class="row clearfix">
          <div class="col-md-4 column">
            <img :src="userData.picture" style="width: 100%;display: block;"><br>
          </div>

          <div class="col-md-4 column">
            学号：{{userData.id}}<br>
            姓名：{{userData.name}}<br>
            班级：{{userData.classId}}<br>
          </div>

          <div class="col-md-4 column">
            <label>信息完整度</label>
            <el-divider direction="vertical"></el-divider>
            <el-progress type="circle" :percentage="80" :width="200"></el-progress>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "test",
    data() {
      return {
        userData: {
          classId: '',
          collegeId: '',
          email: '',
          id: '',
          identityId: '',
          name: '',
          password: '',
          phoneCode: '',
          picture: '',
          professionId: '',
          sex: '',
          wexinId: '',
        },
        flag: false,
      }
    },
    methods: {},
    mounted: function () {
      this.flag = true;
      this.userData = JSON.parse(localStorage.getItem('userData') || '[]');
      console.log(this.userData);
    },
  }
</script>

<style scoped>

</style>

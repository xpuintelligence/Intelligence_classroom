<template>
  <div>
    <h2>
      活动报名
    </h2>

    <hr>

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>活动名称</span>
        <el-button style="float: right; padding: 3px 0" type="text">报名</el-button>
      </div>
      <div class="text item">
        活动简介、照片
      </div>
    </el-card>

    <hr>

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>活动名称</span>
        <el-button style="float: right; padding: 3px 0" type="text">报名</el-button>
      </div>
      <div class="text item">
        活动简介、照片
      </div>
    </el-card>

    <hr>

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>活动名称</span>
        <el-button style="float: right; padding: 3px 0" type="text">报名</el-button>
      </div>
      <div class="text item">
        活动简介、照片
      </div>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "JoinActivities"
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

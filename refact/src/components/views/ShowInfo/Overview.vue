<template>

  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span><Mallki class-name="Overview" text="概览"></Mallki></span>
      <el-button style="padding: 3px 0;float: right;" type="text">
        {{new Date()}}
      </el-button>
    </div>

    <div class="container">
      <div class="row clearfix">
        <div class="col-md-3 column">
          <el-card :body-style="{ padding: '0px' }">
            <el-image src="src">
              <div slot="placeholder" class="image-slot">
                加载中<span class="dot">...</span>
              </div>
            </el-image>
          </el-card>
        </div>

        <div class="col-md-5 column">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>基本信息</span>
            </div>
            <div class="text item" style="font-size: 12px">
              个人基本信息
            </div>
          </el-card>
        </div>

        <div class="col-md-4 column">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>今日课程</span>
            </div>
            <div class="text item" style="font-size: 12px">
              今天课程信息
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </el-card>

</template>

<script>
  import Mallki from "@/components/MyComponents/Mallki";

  export default {
    name: "Overview",
    components: {Mallki}
  }
</script>

<style scoped>

</style>

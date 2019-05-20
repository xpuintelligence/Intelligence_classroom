<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <!--<span>基本信息</span>-->
        <Mallki class-name="date" text="基本信息"></Mallki>
      </div>
      <div class="text item" style="font-size: 12px">
        ID：{{userData.id}}<br>
        姓名：{{userData.name}}<br>
        学院：{{userData.college_name}}<br>
        职位：{{userData.position_name}}<br>
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
      }
    },
    mounted() {
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

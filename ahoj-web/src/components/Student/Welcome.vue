<template>
  <div>
    <div style="text-align: center;">
      <transition enter-active-class="flip">
        <img v-show="flag" alt="logo.png" src="../../assets/logo.png" style="width: 500px" class="animated">
      </transition>


      <h2>智慧教室管理系统</h2>

      <el-carousel :interval="1000" type="card" height="200px">
        <el-carousel-item>
          <!--<h3>智慧教室管理系统</h3>-->
          <img src="http://996xpu.zouchanglin.cn/image/1.jpg" alt="3.png" title="3.png"
               style="width: 300px"/>
        </el-carousel-item>
        <el-carousel-item>
          <!--<h3>智慧教室管理系统</h3>-->
          <img src="http://996xpu.zouchanglin.cn/image/2.jpg" alt="5.png" title="5.png"
               style="width: 300px"/>
        </el-carousel-item>
        <el-carousel-item>
          <!--<h3>智慧教室管理系统</h3>-->
          <img src="http://996xpu.zouchanglin.cn/image/3.jpg" alt="1.png" title="1.png"
               style="width: 300px"/>
        </el-carousel-item>
        <el-carousel-item>
          <!--<h3>智慧教室管理系统</h3>-->
          <img src="http://996xpu.zouchanglin.cn/image/4.jpg" alt="2.png" title="2.png"
               style="width: 300px"/>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>

  export default {
    name: "Welcome",
    data() {
      return {
        flag: false, // 页面 动画控制
      }
    },
    components: {},
    mounted() {
      this.flag = true;
    },
  }
</script>

<style scoped>

</style>

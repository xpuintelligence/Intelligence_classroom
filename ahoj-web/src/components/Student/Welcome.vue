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
          <img src="https://i.loli.net/2019/05/20/5ce24794e23bd35527.png" alt="3.png" title="3.png"
               style="width: 300px"/>
        </el-carousel-item>
        <el-carousel-item>
          <!--<h3>智慧教室管理系统</h3>-->
          <img src="https://i.loli.net/2019/05/20/5ce2479266ce978543.png" alt="5.png" title="5.png"
               style="width: 300px"/>
        </el-carousel-item>
        <el-carousel-item>
          <!--<h3>智慧教室管理系统</h3>-->
          <img src="https://i.loli.net/2019/05/20/5ce247943808f53039.png" alt="1.png" title="1.png"
               style="width: 300px"/>
        </el-carousel-item>
        <el-carousel-item>
          <!--<h3>智慧教室管理系统</h3>-->
          <img src="https://i.loli.net/2019/05/20/5ce247955c03b10433.png" alt="2.png" title="2.png"
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

<template>
  <div>
    <div style="text-align: center;">

      <h2>智慧教室管理系统</h2>

      <div class="main-wrap">
        <video id="myVideo" class="video-js">
          <source src="http://qiniusave.luoshaoqi.cn/show.mp4" type="video/mp4">
        </video>
      </div>


      <transition enter-active-class="flip">
        <img v-show="flag" alt="logo.png" src="../../assets/logo.png" style="width: 500px" class="animated">
      </transition>


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
      this.initVideo();
    },
    methods: {
      initVideo() {
        //初始化视频方法
        let myPlayer = this.$video(myVideo, {
          //确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
          controls: true,
          //自动播放属性,muted:静音播放
          // autoplay: "muted",
          //建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
          preload: "auto",
          //设置视频播放器的显示宽度（以像素为单位）
          width: "800px",
          //设置视频播放器的显示高度（以像素为单位）
          height: "400px"
        });
      },
    }
  }
</script>

<style scoped>

  .main-wrap {
    margin: 0 auto;
    min-width: 320px;
    max-width: 640px;
    padding-right: 800px;
  }

  .main-wrap video {
    width: 100%;
  }

</style>

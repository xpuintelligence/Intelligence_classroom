<template>
  <div>
    <el-container class="wrap">
      <el-aside class="app-side silder" :class="isCollapse ? 'app-side-collapsed' : 'app-side-expanded'">
        <div class="app-side-logo">
          <img src="@/assets/logo.png" :width="isCollapse ? '60' : '60'" height="60"/>
        </div>

        <!-- 菜单 -->
        <el-menu default-active="Welcome" class="el-menu-vertical-demo" @open="handleOpen" @select="handleSelect"
                 background-color="#324157"
                 text-color="#fff"
                 active-text-color="#ffd04b">

          <el-menu-item index="t_Welcome">
            <i class="el-icon-position"></i>
            <span slot="title">首页</span>
          </el-menu-item>

          <el-menu-item index="t_MyInfo">
            <i class="el-icon-s-custom"></i>
            <span slot="title">个人信息</span>
          </el-menu-item>

          <el-submenu index="课堂考勤">
            <template slot="title">
              <i class="el-icon-date"></i>
              <span slot="title">课堂考勤</span>
            </template>
            <el-menu-item index="t_ThisClassAttendance">当堂考勤</el-menu-item>
            <el-menu-item index="t_GetClassAttendance">考勤查询</el-menu-item>
            <el-menu-item index="t_ClassAttendanceRanking">考勤排名</el-menu-item>
          </el-submenu>

          <el-submenu index="答疑">
            <template slot="title">
              <i class="el-icon-chat-dot-round"></i>
              <span slot="title">答疑</span>
            </template>
            <el-menu-item index="t_MsgBoard">留言板</el-menu-item>
            <!--<el-menu-item index="GetCourseInfo">语音</el-menu-item>-->
          </el-submenu>

          <el-submenu index="课堂作业">
            <template slot="title">
              <i class="el-icon-edit"></i>
              <span slot="title">课堂作业</span>
            </template>
            <el-menu-item index="t_PushHomework">发布作业</el-menu-item>
            <el-menu-item index="t_HomeworkCorrecting">批改作业</el-menu-item>
            <el-menu-item index="t_HomeworkStatistics">作业统计</el-menu-item>
          </el-submenu>

          <el-menu-item index="t_PrepareLessonsBeforeClass">
            <i class="el-icon-folder-opened"></i>
            <span slot="title">课前预习</span>
          </el-menu-item>

          <el-menu-item index="t_StudentInfo">
            <i class="el-icon-search"></i>
            <span slot="title">学习情况</span>
          </el-menu-item>

          <el-submenu index="活动" disabled>
            <template slot="title">
              <i class="el-icon-basketball"></i>
              <span slot="title">课外活动</span>
            </template>
            <el-menu-item index="PushActivities">发布活动</el-menu-item>
            <el-menu-item index="JoinActivities">活动报名</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container class="container">

        <!--上边栏-->
        <el-header class="app-header">

          <!--侧栏开关-->
          <div style="width: 60px; cursor: pointer;" @click.prevent="toggleSideBar">
            <i v-show="!isCollapse" class="el-icon-open"></i>
            <i v-show="isCollapse" class="el-icon-turn-off"></i>
          </div>

          <el-button class="app-header-userinfo" type="text" style="color: black;" @click.native="logout">
            <Mallki class="loginOut" text="溜了溜了"></Mallki>
          </el-button>

        </el-header>

        <!--主要内容部分-->
        <el-main class="main">
          <transition name="fade" mode="out-in" translate="yes">
            <router-view></router-view>
          </transition>
        </el-main>

      </el-container>

    </el-container>

  </div>
</template>

<script>
  import Mallki from "@/components/MyComponents/Mallki";

  export default {
    name: "Teacher",
    data() {
      return {
        userData: {},
        isCollapse: false,
      }
    },
    methods: {
      toggleSideBar() {
        this.isCollapse = !this.isCollapse;
      },
      logout: function () {
        this.$confirm('确认退出?', '提示', {})
          .then(() => {
            sessionStorage.removeItem('userData');
            this.$router.push('/');
          })
          .catch(() => {
          });
      },
      handleOpen(key, keyPath) {
        // console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        // console.log(key, keyPath);
      },
      handleSelect(key, keyPath) {
        // console.log(key, keyPath);
        this.$router.push('/Teacher/' + key);
      },
    },
    mounted() {
      this.userData = JSON.parse(sessionStorage.getItem('userData'));
      this.$notify({
        message: '你好，' + this.userData.name,
        type: 'success'
      });
      // console.log(this.userData);
    },
    components: {Mallki},
  }
</script>

<style lang="scss">
  $hideWidth: 0px;
  $silderWidth: 230px;
  .wrap {
    width: 100%;
    min-height: 100vh;
  }

  .container {
    position: relative;
    height: 100vh;
    transition: margin 0.3s;
    margin-left: $silderWidth;
  }

  .main {
    position: absolute;
    left: 0;
    top: 50px;
    width: 100%;
    bottom: 0;
    overflow-y: auto;
  }

  .silder {
    position: fixed;
    left: 0;
    top: 0;
    bottom: 0;
    z-index: 10;
    overflow: hidden;
    transition: width 0.3s;
    background-color: #324157;
    color: #bfcbd9;
    font-size: 14px;
    user-select: none;
    width: 180px;
    overflow-y: auto;

    & > ul {
      width: 180px;
    }
  }

  .fade-enter-active, .fade-leave-active {
    transition: opacity .3s;
  }

  .fade-enter, .fade-leave-to {
    opacity: 0;
  }

  html {
    height: 100%;
  }

  body {
    position: relative;
    top: 0;
    left: 0;
    margin: 0;
    padding: 0;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    width: 100%;
    height: 100%;
    /*overflow: hidden;*/
  }

  .app {
    $solidBorder: 0px solid #eee;
    $sideCollapsedWidth: 66px;
    $sideExpandedWidth: 230px;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    font-size: 1em;
    width: 100%;
    height: 100%;
    min-width: 900px;
    border: $solidBorder;
    display: flex;
    display: -webkit-flex;
    flex-flow: row nowrap;

    &-side {
      width: 230px;
      height: 100%;
      font-weight: 400;

      /* logo 背景颜色 */
      &-left {
        background-color: rgb(255, 255, 255);
      }

      &-logo {
        height: 60px;
        text-align: center;
      }

      /* 关闭的侧栏宽度 */
      &-collapsed {
        width: 66px !important;
      }

      /* 展开的侧边栏宽度 */
      &-expanded {
        width: 170px !important;
      }
    }

    &-header {
      width: 100%;
      height: 60px;
      display: flex;
      flex-flow: row nowrap;
      justify-content: flex-start;
      align-items: center;
      border-bottom: $solidBorder;
      background-color: #fff;
      box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.12), 0 0 3px 0 rgba(0, 0, 0, 0.04);
      position: relative;
      filter:alpha(Opacity=60);-moz-opacity:0.6;opacity: 1.0;

      &-userinfo {
        position: absolute;
        right: 0;
        margin-right: 25px;
        display: flex;
        flex-flow: row nowrap;
        height: 60px;
        justify-content: flex-start;
        align-items: center;
      }
    }

    &-body {
      font-size: 1em;
      width: 100%;
      height: 100%;
      padding: 20px;
      overflow-y: scroll;
    }

    &-footer {
      width: 100%;
      height: 60px;
    }
  }

  .inline-block {
    display: inline-block;
  }
</style>

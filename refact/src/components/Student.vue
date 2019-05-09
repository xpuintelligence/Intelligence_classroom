<template>
  <div>
    <el-container>
      <el-aside class="app-side app-side-left" :class="isCollapse ? 'app-side-collapsed' : 'app-side-expanded'">
        <div class="app-side-logo">
          <img src="@/assets/logo.png" :width="isCollapse ? '60' : '60'" height="60"/>
        </div>

        <div>
          <!-- 我是样例菜单 -->
          <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @select="handleSelect"
                   :collapse="isCollapse">

            <!--ShowInfo-->
            <el-menu-item index="ShowInfo">
              <i class="el-icon-user"></i>
              <span slot="title">个人信息</span>
            </el-menu-item>

            <el-submenu index="考勤信息">
              <template slot="title">
                <i class="el-icon-date"></i>
                <span slot="title">考勤信息</span>
              </template>
              <el-menu-item index="CourseInfo">概览</el-menu-item>
              <el-menu-item index="GetCourseInfo">考勤查询</el-menu-item>
            </el-submenu>

            <el-submenu index="课堂作业">
              <template slot="title">
                <i class="el-icon-edit"></i>
                <span slot="title">课堂作业</span>
              </template>
              <el-menu-item index="GetHomework">查看作业</el-menu-item>
              <el-menu-item index="PushHomework">提交作业</el-menu-item>
            </el-submenu>

            <el-submenu index="课堂内容">
              <template slot="title">
                <i class="el-icon-chat-dot-round"></i>
                <span slot="title">课堂内容</span>
              </template>
              <el-menu-item index="ClassPreview">课前预习</el-menu-item>
              <el-menu-item index="AnsweringQuestions">课后答疑</el-menu-item>
            </el-submenu>

            <el-menu-item index="Evaluation">
              <i class="el-icon-star-off"></i>
              <span slot="title">课程评价</span>
            </el-menu-item>

            <el-submenu index="活动">
              <template slot="title">
                <i class="el-icon-basketball"></i>
                <span slot="title">课外活动</span>
              </template>
              <el-menu-item index="PushActivities">发布活动</el-menu-item>
              <el-menu-item index="JoinActivities">活动报名</el-menu-item>
            </el-submenu>
          </el-menu>
        </div>
      </el-aside>

      <el-container>
        <el-header class="app-header">
          <div style="width: 60px; cursor: pointer;" @click.prevent="toggleSideBar">
            <i v-show="!isCollapse" class="el-icon-d-arrow-left"></i>
            <i v-show="isCollapse" class="el-icon-d-arrow-right"></i>
          </div>

          <div class="app-header-userinfo">
            <el-dropdown trigger="hover" :hide-on-click="false">
              <span class="el-dropdown-link">{{ this.userData.name }}<i
                class="el-icon-arrow-down el-icon--right"></i></span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item divided @click.native="logout">退出登录
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-header>

        <el-main class="app-body">
          <transition name="fade" mode="out-in" translate="yes">
            <router-view></router-view>
          </transition>
        </el-main>

      </el-container>
    </el-container>

  </div>
</template>

<script>
  export default {
    name: "Student",
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
            localStorage.removeItem('userData');
            this.$router.push('/');
          })
          .catch(() => {
          });
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
        this.$router.push('/Student/' + key);
      },
    },
    mounted() {
      // this.userData = JSON.parse(localStorage.getItem('userData') || '[]');
      // console.log(this.userData);
      this.userData = JSON.parse(sessionStorage.getItem('userData'));
      this.$message.success('你好，' + this.userData.name);
      console.log(this.userData);
    },
    components: {},
  }
</script>

<style lang="scss">

  .fade-enter-active, .fade-leave-active {
    transition: opacity .25s;
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

      &-left {
        background-color: rgb(255, 255, 255);
      }

      &-logo {
        height: 60px;
        text-align: center;
      }

      &-collapsed {
        width: 66px !important;
      }

      &-expanded {
        width: 230px !important;
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

<template>
  <div>
    <div>
      <transition enter-active-class="flip">
        <img v-if="flag" alt="logo.png" src="../assets/logo.png" style="width: 200px" class="animated"
             onclick="window.open('#')">
      </transition>
      <br>
    </div>
    <transition enter-active-class="fadeInRight">
      <div v-show="flag" class="components-Login animated">
        <div class="container" v-on:submit.prevent="onSubmit">
          <form class="form-signin" action.pravite="#">

            <transition enter-active-class="bounceIn">
              <el-alert center v-if="connFail" :title="msg" :type="alert_color" :closable="false"></el-alert>
            </transition>
            <p></p>

            <el-input placeholder="用户名" v-model="input_username" clearable v-focus></el-input>

            <p></p>

            <el-input placeholder="密码" v-model="input_password" show-password></el-input>

            <el-checkbox @change="changeWho">我是教师</el-checkbox>

            <el-button type="primary btn-block" @click="onSubmit">{{who}}登录</el-button>
            <p></p>
            <el-button type="primary btn-block" @click="onSubmit" disabled>忘记密码</el-button>

          </form>
        </div>
      </div>
    </transition>

  </div>

</template>

<script>

  export default {
    name: "Login",
    data() {
      return {
        flag: false, // 页面动画控制
        who: "学生",
        input_username: "",
        input_password: "",
        input_who: 1,
        connFail: false,
        msg: "未知错误!",
        alert_color: "info form-control",
        alert_color_arr: [
          "error form-control", // 错误
          "success form-control" // msg === true
        ]
      };
    },
    mounted() {
      this.flag = true;
    },
    methods: {
      changeWho() {
        if (this.who === "学生") {
          this.who = "教师";
          this.input_who = 2;
        } else {
          this.who = "学生";
          this.input_who = 1;
        }
      },
      onSubmit: async function () {
        this.connFail = true;
        try {
          let res = await this.$http.post(
            "wisdom_web/login/all",
            {
              account: this.input_username,
              password: this.input_password,
              status: this.input_who
            },
            {}
            // { emulateJSON: true }
          );
          if (res.data.msg === "true") {
            // this.alert_color = this.alert_color_arr[1];
            // this.msg = "你好," + res.data.data.name + ",正在跳转...";
            if (res.data.status === 1) {
              this.$router.push('Student');
            } else if (res.data.status === 2) {
              this.$router.push('Teacher');
            } else {
              this.alert_color = this.alert_color_arr[0];
              this.msg = "功能尚未开放，敬请期待。";
            }

          } else {
            this.alert_color = this.alert_color_arr[0];
            this.msg = res.data.msg;
          }
        } catch (e) {
          // console.log(e);
          this.msg = "抱歉,出了点问题!";
        }
      } // onSubmit()
    },
    directives: {
      // 自定义私有指令
      focus: {
        inserted: el => {
          el.focus();
        }
      }
    }
  };
</script>

<style scope>
  .form-signin {
    width: 100%;
    max-width: 330px;
    padding: 15px;
    margin: 0 auto;
  }

  .form-signin .checkbox {
    font-weight: 400;
  }

  .form-signin .form-control {
    position: relative;
    box-sizing: border-box;
    height: auto;
    padding: 10px;
    font-size: 16px;
  }

  .form-signin .form-control:focus {
    z-index: 2;
  }

  .form-signin input[type="username"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }

  .form-signin input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }
</style>

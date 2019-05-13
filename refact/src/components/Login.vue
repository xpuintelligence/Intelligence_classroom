<template>
  <div>

    <div class="img-logo">
      <transition enter-active-class="flip">
        <img v-if="flag" alt="logo.png" src="../assets/logo.png" style="width: 200px" class="animated"
             onclick="window.open('#')">
      </transition>
      <br>
    </div>

    <transition enter-active-class="fadeInUp">
      <div v-show="flag" class="components-Login animated">
        <div class="container" v-on:submit.prevent="onSubmit">
          <form class="form-signin" action.pravite="#">

            <p></p>

            <el-input placeholder="用户名" v-model="input_username" @keyup.native.enter="onSubmit" clearable
                      v-focus></el-input>

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
        flag: false, // 页面 动画控制
        who: "学生",
        input_username: "",
        input_password: "",
        input_who: 1,
        connFail: false,
        msg: "未知错误!",
      };
    },
    mounted() {
      this.flag = true;
    },
    methods: {
      changeWho() { // 选择
        if (this.who === "学生") {
          this.who = "教师";
          this.input_who = 2;
        } else {
          this.who = "学生";
          this.input_who = 1;
        }
      },
      onSubmit: async function () { // 提交
        // 账号密码不能为空
        if (this.input_username === "" || this.input_password === "") {
          this.$message.error("用户名或密码不能为空");
          return false;
        }

        try {
          let res = await this.$http.post("wisdom_web/login/all", {
              account: this.input_username,
              password: this.input_password,
              status: this.input_who
            },
            {
              emulateJSON: true,
              withCredentials: true,
              credentials: true
            }
          );

          if (res.data.msg === "true") {
            // console.log("=== res ===");
            // console.log(res);
            // console.log("=== res.data ===");
            // console.log(res.data);
            sessionStorage.setItem('userData', JSON.stringify(res.data.data));
            if (res.data.status === 1) {
              this.$router.push('Student');
            } else if (res.data.status === 2) {
              this.$router.push('Teacher');
            } else {
              this.$message.warning("功能尚未开放");
            }
          } else {
            this.$message.error(res.data.msg);
          }
        } catch (e) {
          this.$message.warning("抱歉，出了点问题");
        }
      } // onSubmit()
    },
    directives: {
      // 自定义私有指令
      focus: { // 打开后自动锁定
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


  .img-logo {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>

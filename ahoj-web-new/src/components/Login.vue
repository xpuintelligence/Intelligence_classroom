<template>
  <div class="components-Login">
    <!-- <Index></Index> -->
    <img alt="logo.png" src="../assets/logo.png" style="width: 200px">
    <br>

    <div class="container" v-on:submit.prevent="onSubmit">
      <form class="form-signin" action.pravite="#">
        <!-- <transition name="alert" translate mode="in-out">
          <div v-if="connFail" :class="alert_color" class>{{msg}}</div>
        </transition> -->
        <transition enter-active-class="bounceIn">
          <div v-if="connFail" :class="alert_color" class="animated">{{msg}}</div>
        </transition>
        <p></p>

        <label for="inputUsername" class="sr-only">username</label>
        <input
          type="username"
          id="inputUsername"
          v-model="input_username"
          class="form-control"
          placeholder="用户名"
          required
          v-focus
        >
        <p></p>
        <label for="inputPassword" class="sr-only">Password</label>
        <input
          type="password"
          id="inputPassword"
          v-model="input_password"
          class="form-control"
          placeholder="密码"
          required
        >

        <div class="checkbox">
          <label>
            <input type="checkbox" value="isTeacher" @change="changeWho">我是教师
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">{{who}}登录</button>
        <button class="btn btn-lg btn-primary btn-block" type="button" disabled>忘记密码</button>
      </form>
    </div>
  </div>
</template>

<script>
import Index from "@/components/Index";

export default {
  name: "Login",
  components: { Index },
  data() {
    return {
      who: "学生",
      input_username: "",
      input_password: "",
      input_who: 1,
      connFail: false,
      msg: "未知错误!",
      alert_color: "alert-danger form-control",
      alert_color_arr: [
        "alert-danger form-control", // 错误
        "alert-success form-control" // msg === true
      ]
    };
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
    onSubmit: async function() {
      this.connFail = true;
      // this.$http.post(
      //   "http://jsonplaceholder.typicode.com/posts",
      //   // "http://47.103.14.73/wisdom_web/login/all",
      //   {
      //     account: this.input_username,
      //     password: this.input_password,
      //     status: this.input_who
      //   },
      //   {
      //     emulateJSON: true
      //   }
      // ).then((data) => {
      //   console.log(data);
      // });
      // this.$jsonp('http://47.103.14.73'+'/wisdom_web/login/all',{
      //   account: this.input_username,
      //   password: this.input_password,
      //   status: this.input_who
      // }).then(json=>{
      //   console.log(json);
      // }).catch(err=>{
      //   this.connFail = true;
      // })
      try {
        let res = await this.$http.post(
          "wisdom_web/login/all",
          {
            account: this.input_username,
            password: this.input_password,
            status: this.input_who
          },{}
          // { emulateJSON: true }
        );
        // console.log(res.data);
        if (res.data.msg === "true") {
          this.alert_color = this.alert_color_arr[1];
          this.msg = "你好 , " + res.data.data.name;
        } else {
          this.alert_color = this.alert_color_arr[0];
          this.msg = res.data.msg;
        }
      } catch (e) {
        // console.log(e);
        this.msg = "抱歉 , 出了点问题!";
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

.alert-enter-active {
  transition: all 1s ease;
}

.alert-leave-active {
  transition: all 2s cubic-bezier(1, 0.5, 0.8, 1);
}

.alert-enter,
.alert-leave-to {
  transform: translateY(-10px);
  opacity: 0;
}
</style>

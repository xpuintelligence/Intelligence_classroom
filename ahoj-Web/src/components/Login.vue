<template>
  <transition>
    <div v-if="flag">
      <div class="container">
        <form class="form-signin">
          <label for="inputUsername" class="sr-only">username</label>
          <input
            type="username"
            id="inputUsername"
            class="form-control"
            placeholder="用户名"
            required
            autofocus
          >
          <p></p>
          <label for="inputPassword" class="sr-only">Password</label>
          <input
            type="password"
            id="inputPassword"
            class="form-control"
            placeholder="密码"
            required
          >
          <div class="checkbox">
            <label>
              <input type="checkbox" value="isTeacher" @change="changeWho">我是教师
            </label>
          </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">以{{who}}的身份登录</button>
        </form>
      </div>
      <!-- /container -->
    </div>
  </transition>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      flag: false,
      who: "学生"
    };
  },
  mounted() {
    this.flag = !this.flag;
  },
  methods: {
    changeWho: function() {
      if (this.who == "学生") {
        this.who = "教师";
      } else {
        this.who = "学生";
      }
    },
  },

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
.form-signin input[type="email"] {
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
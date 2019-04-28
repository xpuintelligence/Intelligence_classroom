<template>
  <transition>
    <div v-if="flag">
      <div class="container">
        <form class="form-signin">
          <label for="inputUsername" class="sr-only">username</label>
          <input
            type="email"
            id="inputUsername"
            class="form-control"
            placeholder="用户名"
            required
            autofocus
          >
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
    }
  }
};
</script>

<style scope>
.v-enter,
.v-leave-to {
  opacity: 0;
}

.v-enter-active,
.v-leave-active {
  transition: all 1s ease;
}
</style>
<template>
  <div>
    <Index></Index>

    <div class="container" v-on:submit.prevent="onSubmit">
      <form class="form-signin" action.pravite="https://www.baidu.com">
        <label for="inputUsername" class="sr-only">username</label>
        <input type="username" id="inputUsername" v-model="input_username" class="form-control" placeholder="用户名"
               required v-focus>
        <p></p>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" v-model="input_password" class="form-control" placeholder="密码"
               required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="isTeacher" @change="changeWho">我是教师
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">{{who}}登录</button>
      </form>
    </div>

  </div>
</template>

<script>
  import Index from "@/components/Index";

  export default {
    name: "Login",
    components: {Index},
    data() {
      return {
        who: "学生",
        input_username: "",
        input_password: "",
      };
    },
    methods: {
      changeWho() {
        if (this.who === "学生") {
          this.who = "教师";
        } else {
          this.who = "学生";
        }
      },
      onSubmit: function () {
        console.log("login...");
        console.log(this.input_username);
        console.log(this.input_password);
        console.log(this.who);
        this.$http.post('http://api.komavideo.com/news/list',).then(function (result) {
          console.log(result);
        });
      },
    },
    directives: { // 自定义私有指令
      'focus': {
        inserted: el => {
          el.focus()
        },
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

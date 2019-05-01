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

      <transition name="alert" translate mode="in-out">=
          <div v-if="connFail" class="alert-danger form-control">
            sorry, the web server error!
          </div>=
      </transition>
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
        input_who: 1,
        connFail: false,
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
      onSubmit: async function () {
        console.log("login...");
        console.log(this.input_username);
        console.log(this.input_password);
        console.log(this.input_who);
        // this.$http.post('http://api.komavideo.com/news/list',).then(function (result) {
        //   console.log(result);
        // });
        this.$http.post(
          "http://jsonplaceholder.typicode.com/posts",
          // "http://47.103.14.73/wisdom_web/login/all",
          {
            account: this.input_username,
            password: this.input_password,
            status: this.input_who
          },
          {
            emulateJSON: true
          }
        ).then((data) => {
          console.log(data);
        });

        try {
          let data = await this.$http.post("http://47.103.14.73/wisdom_web/login/all", {
            account: this.input_username,
            password: this.input_password,
            status: this.input_who
          }, {emulateJSON: true});
          console.log(data);
        } catch (e) {
          this.connFail = true;
        }
      },  // onSubmit()
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


  .alert-enter-active {
    transition: all .3s ease;
  }

  .alert-leave-active {
    transition: all .5s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }

  .alert-enter, .alert-leave-to
    /* .slide-fade-leave-active for below version 2.1.8 */
  {
    transform: translateY(-10px);
    opacity: 0;
  }
</style>

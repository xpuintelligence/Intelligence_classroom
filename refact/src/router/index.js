import Vue from 'vue'
import Router from 'vue-router'
import VueResource from 'vue-resource'

import Login from "@/components/Login"
import Student from "@/components/Student"
import Teacher from "@/components/Teacher"

import ShowInfo from "@/components/views/ShowInfo" // 个人信息界面
import Welcome from "@/components/views/Welcome"  // 登录后的欢迎界面
import CourseInfo from "@/components/views/CourseInfo" // 课堂考勤页面
import Homework from "@/components/views/Homework" // 课堂作业

Vue.use(Router);
Vue.use(VueResource);

Vue.http.options.root = 'http://47.103.14.73/';
Vue.http.options.emulateJSON = true;


export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
    },
    {
      path: '/Student',
      name: 'Student',
      component: Student,
      redirect: '/Student/Welcome',
      children: [
        {
          path: 'ShowInfo',
          name: 'ShowInfo',
          component: ShowInfo,
        },
        {
          path: 'Welcome',
          name: 'Welcome',
          component: Welcome,
        },
        {
          path: 'CourseInfo',
          name: 'CourseInfo',
          component: CourseInfo,
        },
        {
          path: 'Homework',
          name: 'Homework',
          component: Homework,
        },
      ],
    },
    {
      path: '/Teacher',
      name: 'Teacher',
      component: Teacher,
    },
  ],
})

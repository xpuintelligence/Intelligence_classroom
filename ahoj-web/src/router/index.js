import Vue from 'vue'
import Router from 'vue-router'

import VueResource from 'vue-resource'

Vue.use(VueResource);
// Vue.http.options.root = 'http://101.132.78.78:8080/'; http://47.103.14.73:8080
Vue.http.options.root = 'http://47.103.14.73:8080';
Vue.http.interceptors.push(function (request, next) {//拦截器
// 跨域携带cookie
  request.credentials = true;
  next()
});
Vue.http.options.xhr = {withCredentials: true};
Vue.http.options.emulateJSON = true;

import Login from "@/components/Login"
import Student from "@/components/Student"
import Teacher from "@/components/Teacher"

import ShowInfo from "@/components/views/ShowInfo" // 个人信息界面
import Welcome from "@/components/views/Welcome"  // 登录后的欢迎界面

import CourseInfo from "@/components/views/CourseInfo" // 课堂考勤页面
import GetCourseInfo from "@/components/views/GetCourseInfo" // 学生考勤信息查询

import Homework from "@/components/views/Homework" // 课堂作业
import GetHomework from "@/components/views/GetHomework" // 查看作业
import PushHomework from "@/components/views/PushHomework" // 提交作业

import ClassPreview from "@/components/views/ClassPreview" // 课前预习
import AnsweringQuestions from "@/components/views/AnsweringQuestions" // 课后答疑

import Evaluation from "@/components/views/Evaluation" // 课程评价

import PushActivities from "@/components/views/PushActivities" // 发布活动
import JoinActivities from "@/components/views/JoinActivities" // 活动报名

// import NotFound from "@/components/404" // 404 page
import testChart from "@/components/testChart"

Vue.use(Router);


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
        {
          path: 'GetCourseInfo',
          name: 'GetCourseInfo',
          component: GetCourseInfo,
        },
        {
          path: 'GetHomework',
          name: 'GetHomework',
          component: GetHomework,
        },
        {
          path: 'PushHomework',
          name: 'PushHomework',
          component: PushHomework,
        },
        {
          path: 'ClassPreview',
          name: 'ClassPreview',
          component: ClassPreview,
        },
        {
          path: 'AnsweringQuestions',
          name: 'AnsweringQuestions',
          component: AnsweringQuestions,
        },
        {
          path: 'Evaluation',
          name: 'Evaluation',
          component: Evaluation,
        },
        {
          path: 'PushActivities',
          name: 'PushActivities',
          component: PushActivities,
        },
        {
          path: 'JoinActivities',
          name: 'JoinActivities',
          component: JoinActivities,
        },
      ],
    },
    {
      path: '/Teacher',
      name: 'Teacher',
      component: Teacher,
    },
    { // test~~~~~~~~~~~~
      path: '/testChart',
      name: 'testChart',
      component: testChart,
    },
  ],
})

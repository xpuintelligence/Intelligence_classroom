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

/******************/
/*      学生       */
/******************/
import Welcome from "@/components/Student/Welcome"    // 登录后的欢迎界面
import ShowInfo from "@/components/Student/ShowInfo"  // 个人信息界面

import CourseInfo from "@/components/Student/CourseInfo"       // 课堂考勤页面
import GetCourseInfo from "@/components/Student/GetCourseInfo" // 学生考勤信息查询

import Homework from "@/components/Student/Homework"          // 课堂作业
import GetHomework from "@/components/Student/GetHomework"    // 查看作业
import PushHomework from "@/components/Student/PushHomework"  // 提交作业

import ClassPreview from "@/components/Student/ClassPreview"              // 课前预习
import AnsweringQuestions from "@/components/Student/AnsweringQuestions"  // 课后答疑

import Evaluation from "@/components/Student/Evaluation" // 课程评价

import PushActivities from "@/components/Student/PushActivities" // 发布活动
import JoinActivities from "@/components/Student/JoinActivities" // 活动报名

import testChart from "@/components/testChart"

/******************/
/*      教师       */
/******************/
import t_welcome from "@/components/Teacher/t_Welcome"

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
          path: 'Welcome',
          name: 'Welcome',
          component: Welcome,
        },
        {
          path: 'ShowInfo',
          name: 'ShowInfo',
          component: ShowInfo,
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
      redirect: '/Teacher/t_Welcome',
      children: [
        {
          path: 't_welcome',
          name: 't_welcome',
          component: t_welcome,
        },
      ]
    },
    { // test~~~~~~~~~~~~
      path: '/testChart',
      name: 'testChart',
      component: testChart,
    },
  ],
})

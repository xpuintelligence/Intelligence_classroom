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
import t_welcome from "@/components/Teacher/t_Welcome" // 欢迎界面

import t_Activities from "@/components/Teacher/t_Activities" // 发布、组织活动
import t_AnsweringQuestions from "@/components/Teacher/t_AnsweringQuestions" // 答疑解惑
import t_CheckInfo from "@/components/Teacher/t_CheckInfo" // 查看学生信息
import t_ClassAttendance from "@/components/Teacher/t_ClassAttendance" // 课堂考勤
import t_ClassContent from "@/components/Teacher/t_ClassContent" // 课堂内容
import t_MyInfo from "@/components/Teacher/t_MyInfo" // 教师个人信息
import t_SendMsg from "@/components/Teacher/t_SendMsg" // 教师个人信息

import t_ThisClassAttendance from "@/components/Teacher/t_ThisClassAttendance" // 当堂考勤
import t_GetClassAttendance from "@/components/Teacher/t_GetClassAttendance" // 考勤查询
import t_ClassAttendanceRanking from "@/components/Teacher/t_ClassAttendanceRanking" // 班级考勤对比
import t_MsgBoard from "@/components/Teacher/t_MsgBoard" // 答疑留言板
import t_PushHomework from "@/components/Teacher/t_PushHomework" // 布置作业
import t_HomeworkCorrecting from "@/components/Teacher/t_HomeworkCorrecting" // 批改作业
import t_HomeworkStatistics from "@/components/Teacher/t_HomeworkStatistics" // 作业统计
import t_PrepareLessonsBeforeClass from "@/components/Teacher/t_PrepareLessonsBeforeClass" // 发布预习内容
import t_StudentInfo from "@/components/Teacher/t_StudentInfo" // 查看学生学习情况


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
        {
          path: 't_StudentInfo',
          name: 't_StudentInfo',
          component: t_StudentInfo
        },
        {
          path: 't_PrepareLessonsBeforeClass',
          name: 't_PrepareLessonsBeforeClass',
          component: t_PrepareLessonsBeforeClass
        },
        {
          path: 't_HomeworkStatistics',
          name: 't_HomeworkStatistics',
          component: t_HomeworkStatistics
        },
        {
          path: 't_ThisClassAttendance',
          name: 't_ThisClassAttendance',
          component: t_ThisClassAttendance,
        },
        {
          path: 't_GetClassAttendance',
          name: 't_GetClassAttendance',
          component: t_GetClassAttendance,
        },
        {
          path: 't_ClassAttendanceRanking',
          name: 't_ClassAttendanceRanking',
          component: t_ClassAttendanceRanking,
        },
        {
          path: 't_MsgBoard',
          name: 't_MsgBoard',
          component: t_MsgBoard,
        },
        {
          path: 't_PushHomework',
          name: 't_PushHomework',
          component: t_PushHomework
        },
        {
          path: 't_HomeworkCorrecting',
          name: 't_HomeworkCorrecting',
          component: t_HomeworkCorrecting
        },
        {
          path: 't_Activities',
          name: 't_Activities',
          component: t_Activities,
        },
        {
          path: 't_AnsweringQuestions',
          name: 't_AnsweringQuestions',
          component: t_AnsweringQuestions,
        },
        {
          path: 't_CheckInfo',
          name: 't_CheckInfo',
          component: t_CheckInfo,
        },
        {
          path: 't_ClassAttendance',
          name: 't_ClassAttendance',
          component: t_ClassAttendance,
        },
        {
          path: 't_ClassContent',
          name: 't_ClassContent',
          component: t_ClassContent,
        },
        {
          path: 't_MyInfo',
          name: 't_MyInfo',
          component: t_MyInfo,
        },
        {
          path: 't_SendMsg',
          name: 't_SendMsg',
          component: t_SendMsg,
        }
      ]
    },
    { // test~~~~~~~~~~~~
      path: '/testChart',
      name: 'testChart',
      component: testChart,
    },
  ],
})

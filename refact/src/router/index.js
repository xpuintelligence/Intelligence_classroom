import Vue from 'vue'
import Router from 'vue-router'
import VueResource from 'vue-resource'

import Login from "@/components/Login"
import Student from "@/components/Student"
import Teacher from "@/components/Teacher"
import Sidebar from "@/components/Sidebar"

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
    },
    {
      path: '/Teacher',
      name: 'Teacher',
      component: Teacher,
    },
    {
      path: '/Sidebar',
      name: 'Sidebar',
      component: Sidebar,
    }
  ],
})

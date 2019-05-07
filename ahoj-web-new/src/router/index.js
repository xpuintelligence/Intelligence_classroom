import Vue from 'vue'
import Router from 'vue-router'
import resource from 'vue-resource'

import Index from '@/components/Index'
import Login from '@/components/Login'
import Logo from '@/components/Logo'
import Regist from '@/components/Regist'


Vue.use(Router);
Vue.use(resource);

Vue.http.options.root = 'http://47.103.14.73/';
Vue.http.options.emulateJSON = true;

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    {
      path: '/Logo',
      name: 'Logo',
      component: Logo
    }, 
    {
      path: '/Regist',
      name: 'Regist',
      component: Regist
    },
  ]
})

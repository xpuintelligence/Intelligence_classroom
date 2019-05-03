import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Login from '@/components/Login'
import Regist from '@/components/Regist'

Vue.use(Router)

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
        path: '/Regist',
        name: 'Regist',
        component: Regist
    }
  ]
})

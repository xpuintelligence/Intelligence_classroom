import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Login from '@/components/Login'
import resource from 'vue-resource/dist/vue-resource.esm'
import VueJsonp from 'vue-jsonp'


Vue.use(Router);
Vue.use(resource);
Vue.use(VueJsonp);

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
  ]
})

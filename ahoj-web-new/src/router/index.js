import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Login from '@/components/Login'
import resource from 'vue-resource'
// import VueJsonp from 'vue-jsonp'


Vue.use(Router);
Vue.use(resource);
// Vue.use(VueJsonp);

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
  ]
})

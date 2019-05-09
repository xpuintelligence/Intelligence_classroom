// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'bootstrap/dist/css/bootstrap.css'
import './assets/animate.css'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';


Vue.use(ElementUI);

Vue.config.productionTip = false;


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});


router.beforeEach((to, from, next) => {
  if(to.path === '/'){
    localStorage.removeItem('userData');
  }
  let user = localStorage.getItem('userData');
  if(!user && to.path !== '/'){
    next({
      path: '/'
    })
  }else{
    next();
  }
});

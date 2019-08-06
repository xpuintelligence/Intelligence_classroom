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

Vue.config.productionTip = true;

import Video from 'video.js'
import 'video.js/dist/video-js.css'

Vue.prototype.$video = Video;

// 音频
// import AudioVisual from 'vue-audio-visual'
// Vue.use(AudioVisual);


// 图表 ECharts
// import echarts from 'echarts'
// import 'echarts/theme/macarons.js'
// Vue.prototype.$echarts = echarts;

// HighCharts
import HighCharts from 'highcharts';

// import axios from 'axios'
// Vue.prototype.$http = axios;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
//
// let axiosInstance = axios.create({
//   baseURL: 'http://47.103.14.73/',
//   timeout: 1000,
//   headers: {'application/x-wwww-form-urlencoded': 'foobar'}
// });

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>',
});


router.beforeEach((to, from, next) => {
  if (to.path === '/') {
    localStorage.removeItem('userData');
  }
  let user = sessionStorage.getItem('userData');
  if (!user && to.path !== '/') {
    next({
      path: '/'
    })
  } else {
    next();
  }
});

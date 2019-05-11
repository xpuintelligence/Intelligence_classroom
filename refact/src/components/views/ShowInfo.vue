<template>
  <div v-loading="loading" element-loading-text="拼命加载中"
       element-loading-spinner="el-icon-loading">

    <Overview></Overview>

    <el-divider><i class="el-icon-more"></i></el-divider>

    <state-of-class></state-of-class>

    <hr>
    <!--<div id='myChart' style='width:300px; height:300px'></div>-->
    <hr>
    <div>
      <charts></charts>
      <!--<x-chart id="highcharts" class="high" :option="option"></x-chart>-->
      <!--<x-chart id="high" class="high" :option="option1"></x-chart>-->
    </div>
    <hr>

  </div>
</template>

<script>

  // let myvue = {};
  import XChart from '@/components/charts/test'
  import Mallki from "@/components/MyComponents/Mallki";
  import Overview from "@/components/views/ShowInfo/Overview";
  import StateOfClass from "@/components/views/ShowInfo/StateOfClass";
  import Charts from "@/components/views/ShowInfo/Charts";

  export default {
    name: "ShowInfo",
    data() {
      return {
        userData: { // 用户信息
        },
        todayCourse: { // 今天的课程信息
        },
        loading: true,
        isWeekend: false,

        // option: {},
        // data: [{
        //   name: '安装，实施人员',
        //   data: [43934, 52503, 10, 69658, 97031, 119931, 137133, 154175]
        // }, {
        //   name: '工人',
        //   data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
        // }, {
        //   name: '销售',
        //   data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
        // }, {
        //   name: '项目开发',
        //   data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
        // }, {
        //   name: '其他',
        //   data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
        // }],
        // other: {
        //   title: {
        //     //大标题
        //     text: ''
        //   },
        //   subtitle: {
        //     //小标题
        //     text: ''
        //   },
        //   yAxis: {
        //     title: {
        //       text: ''
        //     }
        //   },
        //   legend: {
        //     layout: 'vertical',
        //     align: 'right',
        //     verticalAlign: 'middle'
        //   },
        //   plotOptions: {
        //     series: {
        //       label: {
        //         connectorAllowed: false
        //       },
        //       pointStart: 2010
        //     }
        //   },
        //   series: '',
        //   responsive: {
        //     rules: [{
        //       condition: {
        //         maxWidth: 500
        //       },
        //       chartOptions: {
        //         legend: {
        //           layout: 'horizontal',
        //           align: 'center',
        //           verticalAlign: 'bottom'
        //         }
        //       }
        //     }]
        //   }
        // },
        option1: {
          chart: {
            type: 'column'
          },
          title: {
            text: '月平均降雨量'
          },
          subtitle: {
            text: '数据来源: WorldClimate.com'
          },
          xAxis: {
            categories: [
              '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'
            ],
            crosshair: true
          },
          yAxis: {
            min: 0,
            title: {
              text: '降雨量 (mm)'
            }
          },
          tooltip: {
            // head + 每个 point + footer 拼接成完整的 table
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
              '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
          },
          plotOptions: {
            column: {
              borderWidth: 0
            }
          },
          series: [{
            name: '东京',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 500, 194.1, 95.6, 54.4]
          }]
        },
      }
    },
    computed: {
      courseDate() {
        let d = new Date(this.todayCourse.time);
        return d.toLocaleTimeString();
      }
    },
    beforeCreate: function () {
      // myvue = this;
    },
    methods: {
      // drawLine() {
      //   // 基于准备好的dom，初始化echarts实例
      //   let myChart = this.$echarts.init(document.getElementById('myChart'), 'macarons');
      //   // 绘制图表
      //   myChart.setOption({
      //     title: {text: '哈哈哈哈哈哈哈哈哈哈'},
      //     tooltip: {},
      //     xAxis: {
      //       data: ["1", "2", "3", "4", "5", "6"]
      //     },
      //     yAxis: {},
      //     series: [{
      //       name: 'name',
      //       type: 'bar',
      //       data: [5, 20, 36, 10, 10, 20]
      //     }]
      //   });
      // },
    },
    mounted: function () {
      this.loading = false;

      // myvue.other.title.text = '2010 ~ 2016 年太阳能行业就业人员发展情况';
      // myvue.other.subtitle.text = '数据来源：thesolarfoundation.com';
      // myvue.other.series = myvue.data;     //数据
      // myvue.other.yAxis.title.text = '就业人数';   //数据
      // myvue.option = myvue.other;

      // this.drawLine();

      // this.userData = JSON.parse(sessionStorage.getItem('userData'));
      //
      // this.$http.post('wisdom_web/studentCourseInfo/today', {}).then(res => {
      //   // console.log(res.data.data.data.length);
      //   if (res.data.data.data.length === 0) {
      //     console.log('今天周末木有课哟，好好享受周末吧~');
      //     this.loading = false;
      //   }
      //   this.todayCourse = res.data.data.data[0];
      //   this.loading = false; // loading 关闭
      //   console.log(this.todayCourse);
      // }).catch(err => {
      //   console.log("err-------");
      //   console.log(err);
      //   this.$message.error("server error!");
      // });
    },
    components: {
      Charts,
      StateOfClass,
      Overview,
      Mallki,
      XChart
    }
  }
</script>

<style scoped>
  body {
    margin: 0;
  }
</style>

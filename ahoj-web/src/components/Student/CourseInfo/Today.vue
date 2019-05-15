<template>
  <div v-if="hasData">
    <el-card>
      <div slot="header" class="cell">
        <Mallki class-name="date" :text="chartTittle"></Mallki>
        <el-button style="padding: 3px 0;float: right;" type="text">
          <Mallki class-name="date" :text="todayTime"></Mallki>
        </el-button>
      </div>

      <div class="text item">
        <el-col :span="12">
          <el-card>
            <x-chart class="todayState" id="todayState" :option="yibiaoCharts"></x-chart>
          </el-card>
        </el-col>

        <el-col :span="12">
          <el-card>
            <x-chart id="TodayChart" class="TodayChart" :option="todayChart"></x-chart>
          </el-card>
        </el-col>
      </div>
    </el-card>
    <br>
  </div>
</template>

<script>
  import XChart from '@/components/charts/test'
  import Mallki from "@/components/MyComponents/Mallki";

  export default {
    name: "Today",
    data() {
      return {
        userData: {},
        hasData: false,
        todayData: {},
        attendTotalScore: [], // 出勤总分数
        headUpScore: [],  // 抬头率分数
        todayTime: '',  // 今天的时间

        chartTittle: '',
        todayChart: {
          navigation: {
            buttonOptions: {
              text: '导出',
              enabled: true,
            }
          },
          credits: {
            enabled: false, // 右下角的 highcharts 标识去掉
          },
          chart: {
            polar: true,
            type: 'line'
          },
          title: {
            text: '',
          },
          pane: {
            size: '80%'
          },
          xAxis: {
            categories: ['到勤分数', '抬头总分', '迟到分数', '请假默认分数'],
            tickmarkPlacement: 'on',
            lineWidth: 0
          },
          yAxis: {
            gridLineInterpolation: 'polygon',
            lineWidth: 0,
          },
          tooltip: {
            shared: true,
            pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y:,.0f}</b><br/>'
          },
          legend: {
            align: 'right',
            verticalAlign: 'top',
            y: 70,
            layout: 'vertical'
          },
          series: [
            {
              name: 'score',
              data: [],
              pointPlacement: 'on'
            },
          ]
        },  // 本周数据的spline图

        yibiaoCharts: {
          navigation: {
            buttonOptions: {
              text: '导出',
              enabled: true,
            }
          },
          chart: {
            type: 'solidgauge',
            marginTop: 50
          },
          colors: ['#F62366', '#9DFF02', '#0CCDD6'],
          title: {
            text: '状态仪表盘',
          },
          credits: {
            enabled: false, // 右下角的 highcharts 标识去掉
          },
          tooltip: {
            borderWidth: 0,
            backgroundColor: 'none',
            shadow: false,
            style: {
              fontSize: '16px',
              color: 'silver',
            },
            pointFormat: '{series.name}<br><span style="font-size:2em; color: {point.color}; font-weight: bold">{point.y}分</span>',
            positioner: function (labelWidth) {
              return {
                x: 200 - labelWidth / 2,
                y: 180
              };
            }
          },
          pane: {
            startAngle: 0,
            endAngle: 360,
            background: [
              // { // Track for Move
              //   outerRadius: '112%',
              //   innerRadius: '88%',
              //   //Highcharts.Color(Highcharts.getOptions().colors[1]).setOpacity(0.3).get(),
              //   borderWidth: 0
              // },
              // { // Track for Exercise
              //   outerRadius: '87%',
              //   innerRadius: '63%',
              //   // backgroundColor: Highcharts.Color(Highcharts.getOptions().colors[1]).setOpacity(0.3).get(),
              //   borderWidth: 0
              // },
              // { // Track for Stand
              //   outerRadius: '62%',
              //   innerRadius: '38%',
              //   // backgroundColor: Highcharts.Color(Highcharts.getOptions().colors[2]).setOpacity(0.3).get(),
              //   borderWidth: 0
              // }
            ]
          },
          yAxis: {
            min: 0,
            max: 100,
            lineWidth: 0,
            tickPositions: []
          },
          plotOptions: {
            solidgauge: {
              borderWidth: '34px',
              dataLabels: {
                enabled: false
              },
              linecap: 'round',
              stickyTracking: false
            }
          },

          series: [
            {
              name: '考勤总分',
              borderColor: '#0CCDD6',
              data: [
                {
                  color: '#0CCDD6',
                  radius: '75%',
                  innerRadius: '75%',
                  y: 0,
                }
              ]
            },
            {
              name: '专注度',
              borderColor: '#9DFF02',
              data: [
                {
                  color: '#9DFF02',
                  radius: '50%',
                  innerRadius: '50%',
                  y: 0,
                }
              ]
            },
          ]
        }, // 活动仪表图
      }
    },
    async mounted() {
      // 从本地获取用户信息
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // 获取本周上课数据
      await this.$http.post('wisdom_web/studentCourseInfo/today', {}).then(res => {
        this.todayData = res.body.data;
      }).catch(err => {
        console.log("---err---");
        console.log(err);
        this.$message.error("抱歉，服务器出错");
      });

      if (this.todayData.length === 0) { // 今天没有课程信息
        this.hasData = false; // 本块不显示
      } else {
        this.hasData = true;

        this.todayData = this.todayData[0];
        // 处理获取到的数据
        this.todayTime = new Date(this.todayData.time).toLocaleDateString();

        this.chartTittle = this.userData.name + '-今天上课状态';

        this.todayChart.series[0].data.push(this.todayData.attendScore);
        this.todayChart.series[0].data.push(this.todayData.headUpScore);
        this.todayChart.series[0].data.push(-this.todayData.lateAttendScore);
        this.todayChart.series[0].data.push(this.todayData.leaveScore);

        this.yibiaoCharts.series[0].data[0].y = this.todayData.attendanceTotalScore;
        this.yibiaoCharts.series[1].data[0].y = this.todayData.headUpScore;
      }

      // 处理本月上课数据 生成表格
      // this.chartTittle = this.userData.name + '-今天上课状态';
      //
      // 拼接成本月的时间段
      // this.thisMonthTime =
      //   new Date(this.thisMonthData[0].time).toLocaleDateString()
      //   + " - " +
      //   new Date(this.thisMonthData[this.thisMonthData.length - 1].time).toLocaleDateString();
      //
      // this.todayChart.subtitle.text = '智慧云提供计算服务';  // 表格副标题
      //
      // // this.thisMonthChart.yAxis.min = -50; // y轴最小
      //
      // this.todayChart.series[0].name = '考勤总分';
      // this.todayChart.series[1].name = '专注度';
      // this.todayChart.series[2].name = '迟到扣分';
      //
      // for (let i = 0; i < this.thisMonthData.length; i++) {
      //   this.todayChart.xAxis.categories.push(
      //     this.thisMonthData[0].courseName
      //     + "(" +
      //     new Date(this.thisMonthData[i].time).toLocaleDateString()
      //     + ")"
      //   );
      //   // this.thisMonthChart.series[0].data.push(this.thisMonthData[i].attendanceTotalScore);  // 考勤总分
      //   // this.thisMonthChart.series[1].data.push(this.thisMonthData[i].headUpScore);  // 专注度，抬头分
      //   // this.thisMonthChart.series[2].data.push(-this.thisMonthData[i].lateAttendScore);  // 迟到扣的分数
      //   this.todayChart.series[0].data.push(Math.random()*100);  // 考勤总分
      //   this.todayChart.series[1].data.push(Math.random()*100);  // 专注度，抬头分
      //   this.todayChart.series[2].data.push(-Math.random()*100);  // 迟到扣的分数
      // } // for
    },
    components: {Mallki, XChart}
  }
</script>

<style scoped>

</style>

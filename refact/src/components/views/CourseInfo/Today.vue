<template>
  <div>
    <el-card>
      <div slot="header" class="cell">
        <Mallki class-name="date" :text="chartTittle"></Mallki>
        <el-button style="padding: 3px 0;float: right;" type="text">
          <Mallki class-name="date" :text="todayTime"></Mallki>
        </el-button>
      </div>

      <div class="text item">
        <el-row :span="16">
          <x-chart id="TodayChart" class="TodayChart" :option="todayChart"></x-chart>
        </el-row>

        <el-row :span="8">
          <el-card>
            <h1>
              哈啊啊啊啊啊
            </h1>
          </el-card>
        </el-row>
      </div>
    </el-card>
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
            categories: ['到勤分数',  '抬头总分', '迟到分数', '请假默认分数'],
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
      }
    },
    async mounted() {
      // 从本地获取用户信息
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // 获取本周上课数据
      await this.$http.post('wisdom_web/studentCourseInfo/today', {}).then(res => {
        this.todayData = res.data.data.data[0];
      }).catch(err => {
        console.log("---err---");
        console.log(err);
        this.$message.error("抱歉，服务器出错");
      });

      // 处理获取到的数据
      this.todayTime = new Date(this.todayData.time).toLocaleDateString();

      this.chartTittle = this.userData.name + '-今天上课状态';

      this.todayChart.series[0].data.push(this.todayData.attendScore);
      // this.todayChart.series[0].data.push(this.todayData.attendanceTotalScore);
      this.todayChart.series[0].data.push(this.todayData.headUpScore);
      this.todayChart.series[0].data.push(-this.todayData.lateAttendScore);
      this.todayChart.series[0].data.push(this.todayData.leaveScore);

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

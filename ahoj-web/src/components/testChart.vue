<template>
  <div>
    <x-chart id="thisMonthChart" class="thisMonthChart" :option="thisMonthChart"></x-chart>
  </div>
</template>

<script>
  import XChart from '@/components/charts/test'
  import Mallki from "@/components/MyComponents/Mallki";

  export default {
    name: "testChart",
    data() {
      return {
        userData: {},
        thisMonthData: {},
        attendTotalScore: [], // 出勤总分数
        headUpScore: [],  // 抬头率分数
        thisMonthTime: '',  // 本月时间段

        chartTittle: '',
        thisMonthChart: {
          navigation: {
            buttonOptions: {
              text: '导出',
              enabled: true,
            }
          },
          exporting: {
            // url: 'http://127.0.0.1:3000'
          },
          credits: {
            enabled: false, // 右下角的 highcharts 标识去掉
          },
          chart: {
            type: 'spline',  // 图表类型,
            borderColor: 'DeepSkyBlue',
            borderRadius: 20,
            borderWidth: 2,
          },
          title: {
            text: '',
            useHTML: true
          },
          subtitle: {
            text: ''
          },
          xAxis: {
            categories: [],
            crosshair: true
          },
          yAxis: {
            // min: 0,
            title: {
              text: '分数'
            }
          },
          tooltip: {
            // head + 每个 point + footer 拼接成完整的 table
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
              '<td style="padding:0"><b>{point.y:.1f}分</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
          },
          plotOptions: {
            column: {
              borderWidth: 0
            }
          },
          series: [
            {
              name: '',
              data: [],
              color: 'LightPink'
            },
            {
              name: '',
              data: []
            },
            {
              name: '',
              data: [],
              color: 'LightCoral'
            },
          ]
        },  // 本周数据的spline图
      }
    },
    async mounted() {
      // 从本地获取用户信息
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // 获取本周上课数据
      await this.$http.post('wisdom_web/studentCourseInfo/thisMonth', {}).then(res => {
        this.thisMonthData = res.data.data;
      }).catch(err => {
        console.log("---err---");
        console.log(err);
        this.$message.error("抱歉，服务器出错");
      });

      // 处理本月上课数据 生成表格
      this.chartTittle = this.userData.name + '-本周上课状态';

      // 拼接成本月的时间段
      this.thisMonthTime =
        new Date(this.thisMonthData[0].time).toLocaleDateString()
        + " - " +
        new Date(this.thisMonthData[this.thisMonthData.length - 1].time).toLocaleDateString();

      this.thisMonthChart.subtitle.text = '智慧云提供计算服务';  // 表格副标题

      // this.thisMonthChart.yAxis.min = -50; // y轴最小

      this.thisMonthChart.series[0].name = '考勤总分';
      this.thisMonthChart.series[1].name = '专注度';
      this.thisMonthChart.series[2].name = '迟到扣分';

      for (let i = 0; i < this.thisMonthData.length; i++) {
        this.thisMonthChart.xAxis.categories.push(
          this.thisMonthData[0].courseName
          + "(" +
          new Date(this.thisMonthData[i].time).toLocaleDateString()
          + ")"
        );

        // this.thisMonthChart.series[0].data.push(this.thisMonthData[i].attendanceTotalScore);  // 考勤总分
        // this.thisMonthChart.series[1].data.push(this.thisMonthData[i].headUpScore);  // 专注度，抬头分
        // this.thisMonthChart.series[2].data.push(-this.thisMonthData[i].lateAttendScore);  // 迟到扣的分数

        // 随机生成一些测试数据
        this.thisMonthChart.series[0].data.push(Math.random() * 100);  // 考勤总分
        this.thisMonthChart.series[1].data.push(Math.random() * 100);  // 专注度，抬头分
        this.thisMonthChart.series[2].data.push(-Math.random() * 100);  // 迟到扣的分数
      } // for
    },
    components: {Mallki, XChart}
  }
</script>

<style scoped>

</style>

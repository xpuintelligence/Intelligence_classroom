<template>
  <div>
    <el-card>
      <div slot="header" class="cell">
        <Mallki class-name="date" :text="chartTittle"></Mallki>
        <el-button style="padding: 3px 0;float: right;" type="text">
          <Mallki class-name="date" :text="thisWeekTime"></Mallki>
        </el-button>
      </div>

      <div class="text item">
        <el-row :span="16">
          <x-chart id="high" class="high" :option="thisWeekChart"></x-chart>
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
    name: "ThisWeek",
    data() {
      return {
        userData: {},
        thisWeekData: {},
        attendTotalScore: [], // 出勤总分数
        headUpScore: [],  // 抬头率分数
        thisWeekTime: '',

        chartTittle: '',
        thisWeekChart: {
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
            type: 'line',  // 图表类型
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
      await this.$http.post('wisdom_web/studentCourseInfo/thisWeek', {}).then(res => {
        this.thisWeekData = res.data.data;
      }).catch(err => {
        console.log("---err---");
        console.log(err);
        this.$message.error("抱歉，服务器出错");
      });

      // 处理本周上课数据 生成表格
      this.chartTittle = this.userData.name + '-本周上课状态';

      this.thisWeekTime =
        new Date(this.thisWeekData[0].time).toLocaleDateString()
        + " - " +
        new Date(this.thisWeekData[this.thisWeekData.length - 1].time).toLocaleDateString();

      this.thisWeekChart.subtitle.text = '智慧云提供计算服务';

      this.thisWeekChart.series[0].name = '考勤总分';
      this.thisWeekChart.series[1].name = '专注度';
      this.thisWeekChart.series[2].name = '迟到扣分';

      for (let i = 0; i < this.thisWeekData.length; i++) {
        this.thisWeekChart.xAxis.categories.push(
          this.thisWeekData[0].courseName
          + "(" +
          new Date(this.thisWeekData[i].time).toLocaleDateString()
          + ")"
        );
        // this.thisWeekChart.series[0].data.push(this.thisWeekData[i].attendanceTotalScore);  // 考勤总分
        // this.thisWeekChart.series[1].data.push(this.thisWeekData[i].headUpScore);  // 专注度，抬头分
        // this.thisWeekChart.series[2].data.push(-this.thisWeekData[i].lateAttendScore);  // 迟到扣的分数
        this.thisWeekChart.series[0].data.push(Math.random()%100);  // 考勤总分
        this.thisWeekChart.series[1].data.push(Math.random()%100);  // 专注度，抬头分
        this.thisWeekChart.series[2].data.push(-Math.random()%100);  // 迟到扣的分数

      } // for

    },
    components: {Mallki, XChart}
  }
</script>

<style scoped>

</style>

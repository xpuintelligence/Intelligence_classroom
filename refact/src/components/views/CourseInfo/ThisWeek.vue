<template>
  <div>
    <el-card>
      <div slot="header" class="cell">
        <Mallki class-name="date" :text="chartTittle"></Mallki>
        <el-button style="padding: 3px 0;float: right;" type="text">
          <Mallki class-name="date" :text="new Date().toDateString()"></Mallki>
        </el-button>
      </div>

      <div class="text item">
        <el-col :span="16">
          <el-card body-style="padding: 0px;">
            <x-chart id="high" class="high" :option="thisWeekChart"></x-chart>
          </el-card>
        </el-col>

        <el-col :span="8">
          <el-card>

          </el-card>
        </el-col>
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
            type: 'spline'  // 图表类型
          },
          title: {
            text: '',
            useHTML: true
          },
          subtitle: {
            text: '数据来源: 智慧教室云服务'
          },
          xAxis: {
            // categories: [
            //   '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'
            // ],
            categories: this.attendTotalScore,
            crosshair: true
          },
          yAxis: {
            min: 0,
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
              name: '专注度',
              // data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 500, 194.1, 95.6, 54.4]
              data: []
            },
            {
              name: '考勤',
              data: [],
              color: 'LightPink'
            }
          ]
        },  // 本周数据的spline图
      }
    },
    mounted() {
      // 从本地获取用户信息
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // 获取本周上课数据
      this.$http.post('wisdom_web/studentCourseInfo/thisWeek', {}).then(res => {
        console.log(res);
        this.thisWeekData = res.data.data;
        for (let i = 0; i < res.data.data.length; i++) {
          this.thisWeekChart.series[0].data.push(res.body.data[i].headUpScore);  // 专注度
          this.thisWeekChart.series[1].data.push(res.body.data[i].attendScore);  // 考勤
        }
        // this.thisWeekChart.title.text = '智慧教室-本周上课状态-' + this.userData.name;
        this.chartTittle = this.userData.name + '-本周上课状态';
      }).catch(err => {
        console.log("err-------");
        console.log(err);
        this.$message.error("抱歉，服务器出错");
      })
    },
    components: {Mallki, XChart}
  }
</script>

<style scoped>

</style>

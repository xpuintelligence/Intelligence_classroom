<template>
  <div>
    <el-card>
      <x-chart id="high" class="high" :option="thisSemesterCharts"></x-chart>
    </el-card>
  </div>
</template>

<script>
  import XChart from '@/components/charts/test'

  export default {
    name: "Charts",
    data: function () {
      return {
        userData: {},
        thisSemesterTotalInfo: {},
        attendTotalScore: [], // 出勤总分数
        headUpScore: [],  // 抬头率分数

        thisSemesterCharts: {
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
            type: 'line'  // 图表类型
          },
          title: {
            text: ''
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
        },
      }
    },
    components:
      {
        XChart
      }
    ,
    mounted() {
      // 获取用户数据
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // 获取学期的上课信息
      this.$http.post('wisdom_web/studentCourseInfo/thisSemester', {}).then(res => {
        this.thisSemesterTotalInfo = res.data.data;
        for (let i = 0; i < 66; i++) {
          // this.attendTotalScore.push(res.body.data[i].attendScore);
          // this.headUpScore.push(res.body.data[i].headUpScore);

          this.thisSemesterCharts.series[0].data.push(res.body.data[i].headUpScore);  // 专注度
          this.thisSemesterCharts.series[1].data.push(res.body.data[i].attendScore);  // 考勤
        }
        this.thisSemesterCharts.title.text = '智慧教室-学期总成绩-' + this.userData.name;
        // console.log("attendTotalScore");
        // console.log(this.attendTotalScore);
        // console.log("headUpScore");
        // console.log(this.headUpScore);
      }).catch(err => {
        console.log("--------err-------");
        console.log(err);
      });
    }
  }
</script>

<style scoped>

</style>

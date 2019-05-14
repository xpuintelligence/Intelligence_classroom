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
          exporting: {
            // url: 'http://127.0.0.1:3000'
          },
          credits: {
            enabled: false, // 右下角的 highcharts 标识去掉
          },
          chart: {
            type: 'heatmap',  // 图表类型,
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
              name: 'Sales per employee',
              borderWidth: 1,
              data: [[0, 0, 10], [0, 1, 19], [0, 2, 8], [0, 3, 24], [0, 4, 67], [1, 0, 92], [1, 1, 58], [1, 2, 78], [1, 3, 117], [1, 4, 48], [2, 0, 35], [2, 1, 15], [2, 2, 123], [2, 3, 64], [2, 4, 52], [3, 0, 72], [3, 1, 132], [3, 2, 114], [3, 3, 19], [3, 4, 16], [4, 0, 38], [4, 1, 5], [4, 2, 8], [4, 3, 117], [4, 4, 115], [5, 0, 88], [5, 1, 32], [5, 2, 12], [5, 3, 6], [5, 4, 120], [6, 0, 13], [6, 1, 44], [6, 2, 88], [6, 3, 98], [6, 4, 96], [7, 0, 31], [7, 1, 1], [7, 2, 82], [7, 3, 32], [7, 4, 30], [8, 0, 85], [8, 1, 97], [8, 2, 123], [8, 3, 64], [8, 4, 84], [9, 0, 47], [9, 1, 114], [9, 2, 31], [9, 3, 48], [9, 4, 91]],
              dataLabels: {
                enabled: true,
                color: '#000000'
              }
            },
          ],
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

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
          <el-card v-if="chartsRanderOk === true">
            <x-chart id="high" class="high" :option="thisWeekChart"></x-chart>
          </el-card>
        </el-row>

        <br>

        <el-row :span="8">
          <el-card v-if="chartsRanderOk === true">
            <x-chart id="thisWeekHeatMapChart" class="thisWeekHeatMapChart" :option="thisWeekHeatMapChart"></x-chart>
          </el-card>
        </el-row>
      </div>
    </el-card>

    <br>
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
        chartsRanderOk: false,

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
            // borderColor: 'DeepSkyBlue',
            // borderRadius: 20,
            // borderWidth: 1,
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

        thisWeekHeatMapChart: {
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
            type: 'heatmap',
            marginTop: 40,
            marginBottom: 80,
            plotBorderWidth: 1,
          },
          title: {
            text: ' '
            // subtitle: '智慧云提供计算服务',
          },
          xAxis: {
            // categories: ['Alexander', 'Marie', 'Maximilian', 'Sophia', 'Lukas', 'Maria', 'Leon', 'Anna', 'Tim', 'Laura']
            categories: [],
          },
          yAxis: {
            categories: ['0', '周一', '周二', '周三', '周四', '周五'],
            title: null
          },
          colorAxis: {
            dataClasses: [{
              from: 0,
              to: 10,
              color: '#c6e48b',
              name: '0 - 9'
            }, {
              from: 10,
              to: 29,
              color: '#7bc96f',
              name: '10 - 29'
            }, {
              from: 30,
              to: 79,
              color: '#239a3b',
              name: '30 - 79'
            }, {
              from: 80,
              color: '#196127',
              name: '80 - 100'
            }]
          },
          legend: {
            // align: 'right',
            // layout: 'vertical',
            // margin: 0,
            // verticalAlign: 'top',
            // y: 25,
            // symbolHeight: 280
          },
          tooltip: {
            headerFormat: '',
            pointFormat: '',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
          },
          series: [
            {
              animation: true,
              borderWidth: 0,
              data: [],
              dataLabels: {
                enabled: true,
                color: '#000000'
              }
            },
          ]
        },  // 本周数据热力图
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
          this.thisWeekData[i].courseName
          + "(" +
          new Date(this.thisWeekData[i].time).toLocaleDateString()
          + ")"
        );

        // this.thisWeekChart.series[0].data.push(this.thisWeekData[i].attendanceTotalScore);  // 考勤总分
        // this.thisWeekChart.series[1].data.push(this.thisWeekData[i].headUpScore);  // 专注度，抬头分
        // this.thisWeekChart.series[2].data.push(-this.thisWeekData[i].lateAttendScore);  // 迟到扣的分数
        this.thisWeekChart.series[0].data.push(parseInt(Math.random() * 100));  // 考勤总分
        this.thisWeekChart.series[1].data.push(parseInt(Math.random() * 100));  // 专注度，抬头分
        this.thisWeekChart.series[2].data.push(-parseInt(Math.random() * 100));  // 迟到扣的分数

        // let row = [i, new Date(this.thisWeekData[i].time).getDay(), this.thisWeekData[i].attendanceTotalScore]; // row[i]  i=[0,4]    // row[]
        let row = [i, new Date(this.thisWeekData[i].time).getDay(), parseInt(Math.random() * 100)]; // row[i]  i=[0,4]    // row[]
        this.thisWeekHeatMapChart.series[0].data.push(row);

        // 提示
        this.thisWeekHeatMapChart.tooltip.pointFormat = this.thisWeekData[i].courseName
          + '<br>'
          + new Date(this.thisWeekData[i].time).toLocaleDateString()
          + ' '
          + this.thisWeekHeatMapChart.yAxis.categories[new Date(this.thisWeekData[i].time).getDay()]
          + '<br>考勤总分' + this.thisWeekData[i].attendanceTotalScore + '分'
        ;

      } // for

      this.chartsRanderOk = true;
    },
    components: {Mallki, XChart}
  }
</script>

<style scoped>

</style>

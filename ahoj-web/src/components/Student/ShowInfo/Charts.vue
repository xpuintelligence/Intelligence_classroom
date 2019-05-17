<template>
  <div>
    <el-card v-if="chartsRanderOk === true">
      <x-chart id="thisSemesterCharts" class="thisSemesterCharts" :option="thisSemesterCharts"></x-chart>
    </el-card>


    <el-card v-if="chartsRanderOk === true">
      <x-chart id="packedbubbleCharts" class="packedbubbleCharts" :option="packedbubbleCharts"></x-chart>
    </el-card>


    <el-card v-if="chartsRanderOk === true">
      <x-chart id="thisSemester3dPointChart" class="thisSemester3dPointChart"
               :option="thisSemester3dPointChart"></x-chart>
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
        chartsRanderOk: false,

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
            type: 'line',  // 图表类型
            renderTo: 'thisSemesterCharts',
          },
          title: {
            text: '',
            useHTML: true
          },
          subtitle: {
            text: '智慧云提供计算服务'
          },
          xAxis: {
            categories: [],
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
              name: '考勤总分',
              data: [],
              color: 'LightPink'
            }
          ]
        },  // 直线图

        packedbubbleCharts: { // 气泡图
          chart: {
            type: 'packedbubble',
            renderTo: 'packedbubbleCharts',
            height: '50%' ///这里修改了注意
          },
          title: {
            text: '状态分布'
          },
          tooltip: {
            useHTML: true,
            pointFormat: '<b>{point.name}:</b> {point.y} 分'
          },
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
          plotOptions: {
            packedbubble: {
              minSize: '30%',
              maxSize: '120%',
              zMin: 0,
              zMax: 1000,
              layoutAlgorithm: {
                gravitationalConstant: 0.05,
                splitSeries: true,
                seriesInteraction: false,
                dragBetweenSeries: true,
                parentNodeLimit: true
              },
              dataLabels: {
                enabled: true,
                format: '{point.name}',
                filter: {
                  property: 'y',
                  operator: '>',
                  value: 250
                },
                style: {
                  color: 'black',
                  textOutline: 'none',
                  fontWeight: 'normal'
                }
              }
            }
          },
          series:
            [
              {
                name: '',
                data: [],
                color: '#FFB6C1',
              },
              {
                name: '',
                data: [],
                color: '#87CEFA',
              },
              {
                name: '',
                data: [],
                color: '#7FFFAA',
              },
            ]
        },  // 泡泡图

        thisSemester3dPointChart: {
          chart: {
            renderTo: 'thisSemester3dPointChart',
            margin: 100,
            type: 'scatter',
            options3d: {
              enabled: true,
              alpha: 10,
              beta: 30,
              depth: 250,
              viewDistance: 5,
              frame: {
                bottom: {size: 1, color: 'rgba(0,0,0,0.02)'},
                back: {size: 1, color: 'rgba(0,0,0,0.04)'},
                side: {size: 1, color: 'rgba(0,0,0,0.06)'}
              }
            }
          },
          navigation: {
            buttonOptions: {
              text: '导出',
              enabled: true,
            }
          },
          credits: {
            enabled: false, // 右下角的 highcharts 标识去掉
          },
          title: {
            text: '状态分布'
          },
          subtitle: {
            text: '数据来源：智慧云'
          },
          plotOptions: {
            scatter: {
              width: 10,
              height: 10,
              depth: 10
            }
          },
          yAxis: {
            min: 0,
            max: 10,
            title: null
          },
          xAxis: {
            min: 0,
            max: 10,
            gridLineWidth: 1
          },
          zAxis: {
            min: 0,
            max: 10
          },
          legend: {
            enabled: false
          },
          series: [{
            name: '状态分布',
            colorByPoint: true,
            data: [[1, 6, 5], [8, 7, 9], [1, 3, 4], [4, 6, 8], [5, 7, 7], [6, 9, 6], [7, 0, 5], [2, 3, 3], [3, 9, 8], [3, 6, 5], [4, 9, 4], [2, 3, 3], [6, 9, 9], [0, 7, 0], [7, 7, 9], [7, 2, 9], [0, 6, 2], [4, 6, 7], [3, 7, 7], [0, 1, 7], [2, 8, 6], [2, 3, 7], [6, 4, 8], [3, 5, 9], [7, 9, 5], [3, 1, 7], [4, 4, 2], [3, 6, 2], [3, 1, 6], [6, 8, 5], [6, 6, 7], [4, 1, 1], [7, 2, 7], [7, 7, 0], [8, 8, 9], [9, 4, 1], [8, 3, 4], [9, 8, 9], [3, 5, 3], [0, 2, 4], [6, 0, 2], [2, 1, 3], [5, 8, 9], [2, 1, 1], [9, 7, 6], [3, 0, 2], [9, 9, 0], [3, 4, 8], [2, 6, 1], [8, 9, 2], [7, 6, 5], [6, 3, 1], [9, 3, 1], [8, 9, 3], [9, 1, 0], [3, 8, 7], [8, 0, 0], [4, 9, 7], [8, 6, 2], [4, 3, 0], [2, 3, 5], [9, 1, 4], [1, 1, 4], [6, 0, 2], [6, 1, 6], [3, 8, 8], [8, 8, 7], [5, 5, 0], [3, 9, 6], [5, 4, 3], [6, 8, 3], [0, 1, 5], [6, 7, 3], [8, 3, 2], [3, 8, 3], [2, 1, 6], [4, 6, 7], [8, 9, 9], [5, 4, 2], [6, 1, 3], [6, 9, 5], [4, 8, 2], [9, 7, 4], [5, 4, 2], [9, 6, 1], [2, 7, 3], [4, 5, 4], [6, 8, 1], [3, 4, 0], [2, 2, 6], [5, 1, 2], [9, 9, 7], [6, 9, 9], [8, 4, 3], [4, 1, 7], [6, 2, 5], [0, 4, 9], [3, 5, 9], [6, 9, 1], [1, 9, 2]]
          }]
        },  // 3d散点图
      }
    },
    components: {
      XChart
    },
    async mounted() {
      // 获取用户数据
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // 获取学期的上课信息
      await this.$http.post('wisdom_web/studentCourseInfo/thisSemester', {}).then(res => {
        this.thisSemesterTotalInfo = res.data.data;
      }).catch(err => {
        console.log("--------err-------");
        console.log(err);
      });

      // 处理数据 拼接成表格数据
      for (let i = 0; i < this.thisSemesterTotalInfo.length; i++) {
        this.thisSemesterCharts.xAxis.categories.push(this.thisSemesterTotalInfo[i].courseName
          + '(' +
          new Date(this.thisSemesterTotalInfo[i].time).toLocaleDateString() + ')'
        );
        // Y轴
        this.thisSemesterCharts.series[0].data.push(this.thisSemesterTotalInfo[i].headUpScore);  // 专注度
        this.thisSemesterCharts.series[1].data.push(this.thisSemesterTotalInfo[i].attendanceTotalScore);  // 考勤总分

        this.packedbubbleCharts.series[0].name = '请假';
        this.packedbubbleCharts.series[0].data.push({
          name: new Date(this.thisSemesterTotalInfo[i].time).toLocaleDateString(),
          value: this.thisSemesterTotalInfo[i].leaveScore
        });

        this.packedbubbleCharts.series[1].name = '迟到';
        this.packedbubbleCharts.series[1].data.push({
          name: new Date(this.thisSemesterTotalInfo[i].time).toLocaleDateString(),
          value: -this.thisSemesterTotalInfo[i].lateAttendScore
        });

        this.packedbubbleCharts.series[2].name = '抬头';
        this.packedbubbleCharts.series[2].data.push({
          name: new Date(this.thisSemesterTotalInfo[i].time).toLocaleDateString(),
          value: this.thisSemesterTotalInfo[i].headUpScore
        });
      }
      this.thisSemesterCharts.title.text = '智慧教室-学期总成绩-' + this.userData.name;

      this.chartsRanderOk = true; // 表格处理完毕，显示进行渲染
    },
  }
</script>

<style scoped>

</style>

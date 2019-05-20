<template>
  <div>
    <el-card>
      <div slot="header" class="cell">
        <Mallki class-name="date" :text="chartTittle"></Mallki>
        <el-button style="padding: 3px 0;float: right;" type="text">
          <Mallki class-name="date" :text="thisSemesterTime"></Mallki>
        </el-button>
      </div>

      <div class="text item">
        <el-card v-if="chartsRanderOk === true">
          <x-chart id="thisSemesterChart" class="thisSemesterChart" :option="thisSemesterChart"></x-chart>
        </el-card>

        <br>

        <el-card v-if="chartsRanderOk === true">
          <x-chart id="thisSemesterHeatMapChart" class="thisSemesterHeatMapChart"
                   :option="thisSemesterHeatMapChart"></x-chart>
        </el-card>

        <el-card v-if="chartsRanderOk === true">
          <vuejs-heatmap selector="ThisSemester" :entries="tmp_entries" :tooltip-unit="tooltipHeatmap" :locale="tmp_locale"
                         :color-range="['#ebedf0', '#196127']" :max="100"></vuejs-heatmap>
        </el-card>
      </div>
    </el-card>

    <br>
  </div>
</template>

<script>
  import XChart from '@/components/charts/test'
  import Mallki from "@/components/MyComponents/Mallki";
  // import VuejsHeatmap from 'vuejs-heatmap'
  import VuejsHeatmap from "vuejs-heatmap/src/VuejsHeatmap";

  function dateFormat(dateStr, pattern = '') {
    // 根据给定的时间字符串，得到特定的时间
    var dt = new Date(dateStr)

    //   yyyy-mm-dd
    var y = dt.getFullYear()
    var m = (dt.getMonth() + 1).toString().padStart(2, '0')
    var d = dt.getDate().toString().padStart(2, '0')

    if (pattern.toLowerCase() === 'yyyy-mm-dd') {
      return `${y}-${m}-${d}`
    } else {
      var hh = dt.getHours().toString().padStart(2, '0')
      var mm = dt.getMinutes().toString().padStart(2, '0')
      var ss = dt.getSeconds().toString().padStart(2, '0')

      return `${y}-${m}-${d}`
    }
  }

  export default {
    name: "ThisSemester",
    data() {
      return {
        userData: {},
        thisSemesterData: {},
        attendTotalScore: [], // 出勤总分数
        headUpScore: [],  // 抬头率分数
        thisSemesterTime: '',  // 本月时间段
        chartsRanderOk: false,

        chartTittle: '',
        thisSemesterChart: {
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
            // borderColor: 'DeepSkyBlue',
            // borderRadius: 20,
            // borderWidth: 2,
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

        thisSemesterHeatMapChart: {
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
              to: 60,
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
            }
          ]
        },  // 本学期数据热力图

        tmp_entries: [
          {
            "counting": 100,
            "created_at": "2019-04-21"
          },
          {
            "counting": 60,
            "created_at": "2019-04-22"
          }
        ],

        tooltipHeatmap: '',

        tmp_locale: {
          months: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
          days: ['S', 'M', 'T', 'W', 'T', 'F', 'S'],
          No: 'No',
          on: 'on',
          Less: 'Less',
          More: 'More'
        },
      }
    },
    async mounted() {
      // 从本地获取用户信息
      this.userData = JSON.parse(sessionStorage.getItem('userData'));

      // 获取本周上课数据
      await this.$http.post('wisdom_web/studentCourseInfo/thisSemester', {}).then(res => {
        this.thisSemesterData = res.data.data;
      }).catch(err => {
        console.log("---err---");
        console.log(err);
        this.$message.error("抱歉，服务器出错");
      });

      // 处理本月上课数据 生成表格
      this.chartTittle = this.userData.name + '-本学期上课状态';

      // 拼接成本月的时间段
      this.thisSemesterTime =
        new Date(this.thisSemesterData[0].time).toLocaleDateString()
        + " - " +
        new Date(this.thisSemesterData[this.thisSemesterData.length - 1].time).toLocaleDateString();

      this.thisSemesterChart.subtitle.text = '智慧云提供计算服务';  // 表格副标题

      // this.thisMonthChart.yAxis.min = -50; // y轴最小

      this.thisSemesterChart.series[0].name = '考勤总分';
      this.thisSemesterChart.series[1].name = '专注度';
      this.thisSemesterChart.series[2].name = '迟到扣分';

      for (let i = 0; i < this.thisSemesterData.length; i++) {
        this.thisSemesterChart.xAxis.categories.push(
          this.thisSemesterData[i].courseName
          + "(" +
          new Date(this.thisSemesterData[i].time).toLocaleDateString()
          + ")"
        );
        this.thisSemesterChart.series[0].data.push(this.thisSemesterData[i].attendanceTotalScore);  // 考勤总分
        this.thisSemesterChart.series[1].data.push(this.thisSemesterData[i].headUpScore);  // 专注度，抬头分
        this.thisSemesterChart.series[2].data.push(-this.thisSemesterData[i].lateAttendScore);  // 迟到扣的分数

        let row = [i, new Date(this.thisSemesterData[i].time).getDay(), this.thisSemesterData[i].attendanceTotalScore]; // row[i]  i=[0,4]    // row[]
        this.thisSemesterHeatMapChart.series[0].data.push(row);

        // 提示
        this.thisSemesterHeatMapChart.tooltip.pointFormat = this.thisSemesterData[i].courseName
          + '<br>'
          + new Date(this.thisSemesterData[i].time).toLocaleDateString()
          + ' '
          + this.thisSemesterHeatMapChart.yAxis.categories[new Date(this.thisSemesterData[i].time).getDay()]
          + '<br>考勤总分' + this.thisSemesterData[i].attendanceTotalScore + '分'
        ;

        let t = {
          // "counting": this.thisSemesterData[i].attendanceTotalScore,
          "counting": parseInt(Math.random()*100),
          "created_at": dateFormat(new Date(this.thisSemesterData[i].time), 'yyyy-dd-mm')
        };
        this.tooltipHeatmap = this.thisSemesterData[i].courseName + '<br>';
        this.tmp_entries.push(t);

      } // for

      this.chartsRanderOk = true;
    },
    components: {VuejsHeatmap, Mallki, XChart},
  }
</script>

<style scoped>

</style>

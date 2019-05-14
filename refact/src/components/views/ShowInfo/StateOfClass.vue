<template>
  <el-card class="box-card" v-if="isWeekend === false">
    <div slot="header" class="clearfix">
      <span>
        <Mallki class-name="title" text="今日上课状态"></Mallki>
      </span>
      <el-button style="padding: 3px 0;float: right;" type="text">
        <Mallki class-name="date" :text="todayDate"></Mallki>
      </el-button>
    </div>
    <div class="text item">
      <el-row :gutter="12">
        <el-col :span="10">
          <x-chart class="todayState" id="todayState" :option="yibiaoCharts"></x-chart>
        </el-col>

        <el-col :span="14">
          <el-badge is-dot class="item">
            <span>评价</span>
            <el-rate v-model="classStar.value" disabled text-color="#ff9900" score-template="{value}"
                     show-text></el-rate>
          </el-badge>

          <div class="text item" style="font-size: 12px">
            <el-collapse v-model="teacherWord.whichIsActive" accordion>

              <el-collapse-item title="语音消息" name="1">
                <VueAudio :theUrl="teacherWord.url"></VueAudio>
              </el-collapse-item>

              <el-collapse-item title="文本消息" name="2">
                <el-alert
                  title="啊啊啊啊啊啊啊哈"
                  type="success"
                  description="这是一句绕口令：黑灰化肥会挥发发灰黑化肥挥发；灰黑化肥会挥发发黑灰化肥发挥。 黑灰化肥会挥发发灰黑化肥黑灰挥发化为灰……"
                  :closable="false">
                </el-alert>
              </el-collapse-item>
            </el-collapse>
          </div>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
  import Mallki from "@/components/MyComponents/Mallki";
  import XChart from '@/components/charts/test'
  import VueAudio from "@/components/VueAudio";

  export default {
    name: "StateOfClass",
    components: {VueAudio, Mallki, XChart},
    data() {
      return {
        teacherWord: {
          url: 'http://106.12.202.93/aaa.mp3',
          whichIsActive: '1',
        },

        classStar: {
          value: 3,
          iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'],
        },

        isWeekend: false,
        todayCourse: {},
        todayDate: '',

        yibiaoCharts: {
          chart: {
            type: 'solidgauge',
            marginTop: 50
          },
          colors: ['#F62366', '#9DFF02', '#0CCDD6'],
          title: {
            text: ' ',
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
              color: 'silver'
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

      // 如果是周六、周日就不显示这一栏
      if (new Date().getDay() === 6 || new Date().getDay() === 0) {
        this.isWeekend = true;
        return;
      }

      // 获取今天的上课状态信息
      await this.$http.post('wisdom_web/studentCourseInfo/today', {}).then(res => {
        this.todayCourse = res.body.data.data[0];
        // console.log(this.todayCourse);
      }).catch(err => {
        console.log("--------err-------");
        console.log(err);
      });

      this.todayDate = new Date().toLocaleDateString();

      this.yibiaoCharts.series[0].data[0].y = this.todayCourse.attendanceTotalScore;
      this.yibiaoCharts.series[1].data[0].y = this.todayCourse.headUpScore;
    }
  }
</script>

<style scoped>

</style>

<template>
  <div>
    <div :id="id" :option="option" class="x-bar" v-loading="loading" element-loading-text="拼命加载中"
         element-loading-spinner="el-icon-loading"></div>
  </div>
</template>

<script>
  import HighCharts from 'highcharts'
  import HighChartsExport from 'highcharts/modules/exporting'
  import Map from 'highcharts/modules/map'
  import HeapMap from 'highcharts/modules/heatmap'
  import HighChartsMore from 'highcharts/highcharts-more'

  import SolidGauge from 'highcharts/modules/solid-gauge'
  import ThreeCharts from 'highcharts/highcharts-3d'

  Map(HighCharts);  // map
  HighChartsExport(HighCharts); // 导出
  HeapMap(HighCharts);  // 热力图
  HighChartsMore(HighCharts);
  SolidGauge(HighCharts);
  ThreeCharts(HighCharts); // 3d

  HighCharts.setOptions({
    lang: {
      printChart: "打印图表",
      downloadJPEG: "下载JPEG 图片",
      downloadPDF: "下载PDF文档",
      downloadPNG: "下载PNG图片",
      downloadSVG: "下载SVG矢量图"
    }
  });

  export default {
    name: "test.vue",
    data() {
      return {
        loading: true,
        showChart: false,
      }
    },
    // 验证类型
    props: {
      id: {
        type: String
      },
      option: {
        type: Object
      }
    },
    watch: {
      mc() {
        // HighCharts.chart(this.id, this.option);
      }
    },
    async mounted() {
      // console.log("======");
      // console.log(this.option);

      // await HighCharts.chart(this.id, this.option);
      await setTimeout(() => {
        HighCharts.chart(this.id, this.option);
      }, 2000);
    },
  }
</script>

<style scoped>

</style>

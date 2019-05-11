<template>
  <div>
    <div :id="id" :option="option" class="x-bar" v-loading="loading" element-loading-text="拼命加载中"
         element-loading-spinner="el-icon-loading"></div>
  </div>
</template>

<script>
  import HighCharts from 'highcharts'
  import HighChartsExport from 'highcharts/modules/exporting'
  HighChartsExport(HighCharts);

  HighCharts.setOptions({
    lang: {
      printChart:"打印图表",
      downloadJPEG: "下载JPEG 图片" ,
      downloadPDF: "下载PDF文档"  ,
      downloadPNG: "下载PNG图片"  ,
      downloadSVG: "下载SVG矢量图"
    }
  });

  export default {
    name: "test.vue",
    data() {
      return {
        loading: true,
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
      option() {
        HighCharts.chart(this.id, this.option);
      }
    },
    mounted() {
      console.log("======");
      console.log(this.option);

      setTimeout(() => {
        HighCharts.chart(this.id, this.option);
        this.loading = this.option.series[0].data.length === 0 || this.option.series[1].data.length === 0;
      }, 500);
      // HighCharts.chart(this.id, this.option);
      // this.loading = false;
    },
  }
</script>

<style scoped>

</style>

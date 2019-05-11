<template>
  <div>
    <div :id="id" :option="option" class="x-bar" v-loading="loading" element-loading-text="拼命加载中"
         element-loading-spinner="el-icon-loading"></div>
  </div>
</template>

<script>
  import HighCharts from 'highcharts'

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
        this.loading = false;
      }, 500);
      // HighCharts.chart(this.id, this.option)
    },
  }
</script>

<style scoped>

</style>

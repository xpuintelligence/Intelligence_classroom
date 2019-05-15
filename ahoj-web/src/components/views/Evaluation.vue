<template>
  <div>
    <h2>
      课程评价
    </h2>

    <hr>

    <div class="block">
      <el-rate v-model="value1"></el-rate>
    </div>
    <div class="block">
      <el-rate
        v-model="value2"
        :colors="colors">
      </el-rate>
    </div>

    <hr>

    <el-rate
      v-model="value"
      show-text>
    </el-rate>

  </div>
</template>

<script>
  export default {
    name: "Evaluation",
    data() {
      return {
        value: 3,
        value1: 2,
        value2: 3,
        colors: ['#99A9BF', '#F7BA2A', '#FF9900'],  // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
      }
    }
  }
</script>

<style scoped>

</style>

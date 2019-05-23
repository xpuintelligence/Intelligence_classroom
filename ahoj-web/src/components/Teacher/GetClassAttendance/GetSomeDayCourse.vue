<template>
  <div>
    <div class="block">
      <el-date-picker
        v-model="time"
        type="datetimerange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        @change="changeTime">
      </el-date-picker>
    </div>

    <el-card>
      <el-table :data="getCourseData" stripe style="width: 100%">
        <el-table-column prop="time" label="日期" width="180"></el-table-column>

        <el-table-column prop="name" label="课程名" width="180"></el-table-column>

        <el-table-column prop="classroom_id" label="教室"></el-table-column>

        <el-table-column prop="head_up_rate" label="班级平均抬头率"></el-table-column>

        <el-table-column>
          <el-button type="text" @click="hasQuestion"><i class="el-icon-question"></i></el-button>
        </el-table-column>

        <el-table-column>
          <el-button type="text" @click="moreInfo">详情</el-button>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "GetSomeDayCourse",
    data() {
      return {
        userData: {},
        time: [new Date(2019, 4, 15, 10, 10), new Date(2019, 4, 15, 10, 10)],
        getCourseData: [],
      }
    },
    async mounted() {
      // 获取用户数据
      this.userData = JSON.parse(sessionStorage.getItem('userData'));
    },
    methods: {
      hasQuestion() {
        this.$prompt('问题描述', '意见反馈', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({value}) => {
          this.$message({
            type: 'success',
            message: '我们已收到您的反馈意见：' + value + '，会尽快处理。'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },

      moreInfo() {
        this.$message({
          type: 'info',
          message: '敬请期待...'
        })
      },

      async changeTime() {
        this.time[0] = this.time[0].toLocaleDateString().replace(/\//g, '-') + ' ' + this.time[0].toTimeString().split(' ')[0];
        this.time[1] = this.time[1].toLocaleDateString().replace(/\//g, '-') + ' ' + this.time[1].toTimeString().split(' ')[0];
        // console.log(this.time);
        await this.$http.post('wisdom_web/studetnAttendance/allCourseAttASpellTime', {
          start: this.time[0],
          end: this.time[1],
        }).then(res => {
          this.getCourseData = res.body.data;
          console.log(res);
        }).catch(err => {
          console.log("--------err in GetSomeDayCourse.vue-------");
          console.log(err);
        });

        // 处理时间戳
        for (let i = 0; i < this.getCourseData.length; i++) {
          this.getCourseData[i].time = new Date(this.getCourseData[i].time).toLocaleDateString()
            + ' '
            + new Date(this.getCourseData[i].time).toLocaleTimeString();
        }
      },

    },
  }
</script>

<style scoped>

</style>

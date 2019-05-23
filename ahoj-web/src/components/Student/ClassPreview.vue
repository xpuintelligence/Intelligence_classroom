<template>
  <div>
    <el-card>
      <div slot="header">
        <Mallki class="MySql_pdf" text="MySQL课件"></Mallki>
      </div>
      <div>
        <el-table :data="pdfUrl[0].MySQL" stripe style="width: 100%">
          <el-table-column prop="text" label="课件名"></el-table-column>

          <el-table-column prop="url" label="地址"></el-table-column>

          <el-table-column>
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>

    <br/>
    <el-card>
      <div slot="header">
        <Mallki class="MySql_pdf" text="MySQL课件"></Mallki>
      </div>
      <div>
        <el-table :data="pdfUrl[1].C" stripe style="width: 100%">
          <el-table-column prop="text" label="课件名"></el-table-column>

          <el-table-column prop="url" label="地址"></el-table-column>

          <el-table-column>
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
  // pdf_C_2
  import Mallki from "@/components/MyComponents/Mallki";

  export default {
    name: "ClassPreview",
    components: {Mallki},
    data() {
      return {
        pdfUrl: [
          {
            MySQL: [
              {
                text: 'pdf_MySQL_1',
                url: 'http://996xpu.zouchanglin.cn/pdf/pdf_MySQL_1.pdf'
              },
              {
                text: 'pdf_MySQL_2',
                url: 'http://996xpu.zouchanglin.cn/pdf/pdf_MySQL_2.pdf'
              },
              {
                text: 'pdf_MySQL_3',
                url: 'http://996xpu.zouchanglin.cn/pdf/pdf_MySQL_3.pdf'
              },
              {
                text: 'pdf_MySQL_4',
                url: 'http://996xpu.zouchanglin.cn/pdf/pdf_MySQL_4.pdf'
              },
              {
                text: 'pdf_MySQL_5',
                url: 'http://996xpu.zouchanglin.cn/pdf/pdf_MySQL_5.pdf'
              },
            ],
          },
          {
            C: [
              {
                text: 'pdf_C_2',
                url: 'http://996xpu.zouchanglin.cn/pdf/pdf_C_2.pdf'
              },
              {
                text: 'pdf_C_3',
                url: 'http://996xpu.zouchanglin.cn/pdf/pdf_C_3.pdf'
              },
              {
                text: 'pdf_C_4',
                url: 'http://996xpu.zouchanglin.cn/pdf/pdf_C_4.pdf'
              },
            ],
          },
        ],
      }
    },
    mounted() {

    },
    methods: {
      handleClick(row) {
        window.open(row.url, '_blank').location;
      }
    },
  }
</script>

<style scoped>

</style>

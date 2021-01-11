<template>
  <div class="dashboard-editor-container">

    <panel-group  :data-list="datalist"/>

    <el-row>
      <line-chart :data-list="datalist" @handleSetLineChartData="handleSetLineChartData"/>
    </el-row>

    <el-dialog :title="title" :visible.sync="open" width="40%" append-to-body>
      <el-form :model="form" :rules="rules" ref="form" label-position="right" label-width="80px">
        <el-form-item label="简历状态">
          <el-select v-model="form.updateStatic" placeholder="请选择简历状态">
            <el-option label="跟进中" :value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="跟进情况" prop="memoDetail">
            <el-input v-model="form.memoDetail" type="textarea" placeholder="请输入内容" style="width: 80%;"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="open = false">取 消</el-button>
      </div>
    </el-dialog>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="12" :lg="8" v-for="data,i in datalist.listMarEntry" :key="i">
        <div class="chart-wrapper">
          <raddar-chart :followstatus="followstatus"  :data="data" @getList="getList"/>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'


import { getlist } from "@/api/index.js"
import{genzongbut}from'@/api/resume/mytrckresume.js'
export default {
  name: 'Index',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {
      followstatus:[],
      datalist:{
        entryrobnnum:0,
        EnterInfosize:0,
        bingdinnum:0,
        resumeadopt:0,
        interviewadopt:0,
        entryPeople:0,
      },
      open:false,
      title:"",
      form:{},
      rules:{
        memoDetail: [
         { required: true, message: "跟踪情况不能为空", trigger: "blur" }
      ],
      },
    }
  },
  created() {
    this.getDicts("mar_demandresumefollow_status").then(response => {
         this.followstatus = response.data;
    });
    this.getList()
  },
  methods: {
    getList(){
      getlist().then(res=>{
          this.datalist = res.data
      })
    },
    handleSetLineChartData(type) {
      this.form = {}
      this.open = true
      this.form.contactCustomercode = type.resumeId
      this.form.updateStatic = 2
      this.title = "跟踪简历"
    },
    submitForm(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          genzongbut(this.form).then(res=>{
            this.msgSuccess("操作成功");
            this.open = false;
            this.getList();
          })
        }
      });
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    border-radius: 10px;
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1035px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>

<template>
  <div class="dashboard-editor-container">

    <panel-group  :data-list="datalist" />

    <el-row>
      <jianzhi v-if="datalist.numb===3"  :data-list="datalist"  @handleSetLineChartData="handleSetLineChartData"></jianzhi>
      <line-chart v-else :data-list="datalist"  @handleSetLineChartData="handleSetLineChartData"/>
    </el-row>


    <mytrack :open="open" :title="title" :form="form" @getlist="getList"></mytrack>

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
import jianzhi from './dashboard/jianzhi'
import { getlist,getbusinessData,getlists,partjob } from "@/api/index.js"
import{genzongbut}from'@/api/resume/mytrckresume.js'
import mytrack from "./components/resume/mytrack.vue"
import {permission} from "@/utils/ruoyi.js"
export default {
  name: 'Index',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart,
    mytrack,
    jianzhi
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
        litentry: 0,
        litinfo: 0,
        litmap: 0,
        litmarD: 0,
        litview: 0,
        lastmap:0,
        lastview:0,
        lastinfo:0,
        lastlitentry:0,
        listMarEntry:[],
        followStatus:1,
        firstEnter:0,
        firstRob:0,
        firstMarbing:0,
        firstresumeadopt:0,
        firstinterviewadopt:0,
        firstentryPeople:0,
        litout:0,
        lastlitout:0,
      },

      open:{
        opens:false
      },
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
      let hasPermissions = permission()
      if(hasPermissions == 1){
        this.datalist.numb = 1
        getbusinessData().then(res=>{
            this.datalist = res.data
            this.datalist.numb = hasPermissions
        })
      }else if(hasPermissions == 2){
        this.datalist.numb = 2
        getlist().then(res=>{
            this.datalist = res.data
            this.datalist.numb = hasPermissions
        });
      }else if(hasPermissions == 3){
        this.datalist.numb = 3
   
        partjob().then(res=>{
            this.datalist = res.data
            this.datalist.numb = hasPermissions
        });
      }else{
        this.datalist.numb = 2
        getlists().then(res=>{
          this.datalist = res.data
          this.datalist.numb = 2
        });
      }
    },
    handleSetLineChartData(type) {
      this.form = {}
      this.open.opens = true
      this.form.contactCustomercode = type.resumeId
      this.form.updateStatic = 2
      this.title = "跟踪简历"
    },
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    border-radius: 2px;
    background: #fff;
    padding: 16px 20px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1035px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>

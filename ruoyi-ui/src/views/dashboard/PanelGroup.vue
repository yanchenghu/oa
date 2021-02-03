<template>
  <div>
    <el-row :gutter="35" class="panel-group" v-if="dataList.numb==2">
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(0)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              录入
            </div>
            <count-to  :start-val="0" :end-val="dataList.EnterInfosize" :duration="2600" class="card-panel-num4" />
            <count-to  :start-val="0" :end-val="dataList.firstEnter" :duration="2600" class="card-panel-num" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-luru">
            <svg-icon icon-class="luru" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(1)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              抢占
            </div>
            <count-to :start-val="0" :end-val="dataList.entryrobnnum" :duration="2600" class="card-panel-num5" />
            <count-to :start-val="0" :end-val="dataList.firstRob" :duration="2600" class="card-panel-num" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>

          </div>
          <div class="card-panel-icon-wrapper icon-qaingzhan">
            <svg-icon icon-class="genzong" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(2)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              绑定
            </div>
            <count-to :start-val="0" :end-val="dataList.bingdinnum" :duration="3000" class="card-panel-num6" />
            <count-to :start-val="0" :end-val="dataList.firstMarbing" :duration="3000" class="card-panel-num" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-bangd">
            <svg-icon icon-class="bangding" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(3)">

          <div class="card-panel-description">
            <div class="card-panel-text">
              简历通过
            </div>
            <count-to :start-val="0" :end-val="dataList.resumeadopt" :duration="3200" class="card-panel-num1 " />
            <count-to :start-val="0" :end-val="dataList.firstresumeadopt" :duration="3200" class="card-panel-num " />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-jianli">
            <svg-icon icon-class="jianli" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(4)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              面试通过
            </div>
            <count-to :start-val="0" :end-val="dataList.interviewadopt" :duration="3600" class="card-panel-num2" />
            <count-to :start-val="0" :end-val="dataList.firstinterviewadopt" :duration="3600" class="card-panel-num" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-mianshi">
            <svg-icon icon-class="mianshi" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(5)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              入项
            </div>
            <count-to :start-val="0" :end-val="dataList.entryPeople" :duration="3600" class="card-panel-num3" />
            <count-to :start-val="0" :end-val="dataList.firstentryPeople" :duration="3600" class="card-panel-num" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-ruxiang">
            <svg-icon icon-class="ruxiang" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="35" class="panel-group" v-if="dataList.numb==1">
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(0)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              录入需求
            </div>
            <count-to  :start-val="0" :end-val="dataList.litmarD" :duration="2600" class="card-panel-num4" />

            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-luru">
            <svg-icon icon-class="luru" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(1)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              简历绑定
            </div>
            <count-to :start-val="0" :end-val="dataList.litmap" :duration="3000" class="card-panel-num6" />
            <count-to  :start-val="0" :end-val="dataList.lastmap " :duration="2600" class="card-panel-num" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-bangd">
            <svg-icon icon-class="bangding" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(3)">

          <div class="card-panel-description">
            <div class="card-panel-text">
              简历通过
            </div>
            <count-to :start-val="0" :end-val="dataList.litinfo" :duration="3200" class="card-panel-num1 " />
            <count-to  :start-val="0" :end-val="dataList.lastinfo" :duration="2600" class="card-panel-num" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-jianli">
            <svg-icon icon-class="jianli" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(5)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              面试通过
            </div>
            <count-to :start-val="0" :end-val="dataList.litview" :duration="3600" class="card-panel-num2" />
            <count-to  :start-val="0" :end-val="dataList.lastinfo" :duration="2600" class="card-panel-num" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-mianshi">
            <svg-icon icon-class="mianshi" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(7)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              人员入项
            </div>
            <count-to :start-val="0" :end-val="dataList.litentry" :duration="3600" class="card-panel-num3" />
            <count-to  :start-val="0" :end-val="dataList.lastlitentry" :duration="2600" class="card-panel-num" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-ruxiang">
            <svg-icon icon-class="ruxiang" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(8)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              人员出项
            </div>
            <count-to :start-val="0" :end-val="dataList.litout " :duration="3600" class="card-panel-num3" />
            <count-to  :start-val="0" :end-val="dataList.lastlitout" :duration="2600" class="card-panel-num" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-ruxiang">
            <svg-icon icon-class="ruxiang" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
    </el-row>
    <el-dialog v-if="title=='人员出项'" :title="title" :visible.sync="dialogTableVisible"  width="500px">
      <el-table :data="gridData" v-loading="loading">
        <el-table-column property="customer_name" label="姓名" ></el-table-column>
        <el-table-column property="outof_projecttime" label="离项时间" ></el-table-column>
        <el-table-column property="quit_proreason" label="离项原因" :formatter="professionIdopFormat"></el-table-column>
      </el-table>
    </el-dialog>
    <el-dialog v-else :title="title" :visible.sync="dialogTableVisible"  width="500px">
      <el-table :data="gridData" v-if="dataList.numb==2" v-loading="loading">
        <el-table-column property="customerName" label="姓名" ></el-table-column>
        <el-table-column property="customerTel" label="电话"  ></el-table-column>
        <el-table-column v-if="title=='录入'||title=='抢占'" property="addTime" label="录入时间"></el-table-column>
        <el-table-column v-else  property="trackingtime" label="操作时间"></el-table-column>
      </el-table>
      <el-table :data="gridData" v-if="dataList.numb==1" v-loading="loading">
        <el-table-column v-if="title!=='录入需求'" property="customerName" label="姓名" width="80"></el-table-column>
        <el-table-column  property="projectName" label="公司名称" ></el-table-column>
        <el-table-column v-if="title=='录入需求'" property="addTime" label="添加时间" ></el-table-column>
        <el-table-column v-else property="trackingtime" label="操作时间" ></el-table-column>
      </el-table>
    </el-dialog>

  </div>

</template>

<script>
import CountTo from 'vue-count-to'
import { getmsg, getbusmsg} from "@/api/index.js"

export default {
  data(){
    return{
      loading:false,
      gridData:{},
      dialogTableVisible:false,
      title:"",
      customerleve:[],
    }
  },
  created() {
    this.getDicts("outof_project_cause").then(response => {
      this.customerleve = response.data;
    });
  },
  props:{
    dataList:{
      type:Object,
      required: true
    }
  },
  components: {
    CountTo
  },
  methods: {
    professionIdopFormat(row, column) {
      return this.selectDictLabel(this.customerleve, row.quit_proreason);},
      
    handleSetLineChartData(type) {
      // this.$emit('handleSetLineChartData', type)
      let form = new FormData()
      form.append("followStatus",type)
      this.loading=true
      getmsg(form).then(res=>{
        this.gridData = res.data
        this.dialogTableVisible = true
        this.loading = false
      })
      if(type==0){
        this.title = "录入"
      }else if(type == 1){
        this.title = "抢占"
      }else if(type == 2){
        this.title = "绑定"
      }else if(type == 3){
        this.title = "简历通过"
      }else if(type == 4){
        this.title = "面试通过"
      }else if(type == 5){
        this.title = "入项"
      }
    },
    handleSetLineChartDatas(type) {
      let form = new FormData()
      form.append("followStatus",type)
      this.loading=true
      getbusmsg(form).then(res=>{
        this.gridData = res.data
        this.dialogTableVisible = true
        this.loading=false
      })
      if(type==0){
        this.title = "录入需求"
      }else if(type == 1){
        this.title = "简历绑定"
      }else if(type == 3){
        this.title = "简历通过"
      }else if(type == 5){
        this.title = "面试通过"
      }else if(type == 7){
        this.title = "人员入项"
      }else if(type == 8){
        this.title = "人员出项"
      }
    },

  }
}
</script>

<style lang="scss" scoped>

.panel-group {
  margin-top: 18px;
  .card-panel-col {
    margin-bottom: 32px ;
    padding: 0 16px !important;
  }
  .card-panel {
    border-radius: 2px;
    height: 110px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon-luru {
        background: #0081FF;
      }
      .icon-qaingzhan{
       background: #FF9F43;
      }
      .icon-bangd{
        background:#28C76F;
      }

      .icon-jianli{
        background: #33B5E5;
      }
      .icon-ruxiang {
        background: #EA5455;
      }
     .icon-mianshi {
       background: #AA66CC;
     }
    }

    .icon-luru {
      color: #0081FF;
      background-color: rgba(51,181,229,0.1) ;
    }
    .icon-qaingzhan{
     color: #FF9F43;
     background-color: rgba(170,102,204,0.1) ;
    }
    .icon-bangd{
      color:#28C76F;
      background-color: rgba(40,199,111,0.1) ;
    }
    .icon-jianli{
      color: #33B5E5;
      background-color: rgba(51,181,229,0.1) ;
    }
    .icon-mianshi {
      color: #AA66CC;
      background-color:rgba(170,102,204,0.1) ;
    }

    .icon-ruxiang {
      color: #EA5455;
      background-color: rgba(234,84,85,0.1) ;
    }


    .card-panel-icon-wrapper {
      float: right;
      margin: 35px 16px 0 0;
      padding:6px 6px;
      transition: all 0.38s ease-out;
      border-radius: 10px;
    }

    .card-panel-icon {
      float: right;
      font-size: 28px;
    }

    .card-panel-description {
      float: left;
      font-weight: bold;
      margin:20px 0 26px 16px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 14px;
        margin-bottom: 12px;
      }
      .card-panel-num{
        margin-left: 20px;
        font-size: 20px;
        font-weight: 800;
        color: #c0c4cc;
      }

      .card-panel-num1 {
        font-size: 20px;
        font-weight: 800;
        color: #33B5E5;
      }
      .card-panel-num2 {
        font-size: 20px;
         color: #AA66CC;
        font-weight: 800;
      }
      .card-panel-num3 {
        color: #EA5455;
        font-size: 20px;
        font-weight: 800;
      }
      .card-panel-num4 {
        color: #0081FF;
        font-size: 20px;
        font-weight: 800;
      }
      .card-panel-num5 {
        font-size: 20px;
        color: #FF9F43;
        font-weight: 800;
      }
      .card-panel-num6 {
        font-size: 20px;
         color:#28C76F;
        font-weight: 800;
      }
    }
  }
}
@media only screen and (min-width: 1200px){
  .el-col-lg-4-8 {
  		width: 20%;
  	}
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;
    border-radius: 2px !important;
    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>

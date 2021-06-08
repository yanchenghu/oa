<template>
  <div class="manage">
      <div class="manage-top">
        <el-row :gutter="20">
          <el-col :span="19">
            <div style="float: right;">
              <el-select v-model="queryParem.recentYears" placeholder="请选择" size="small" @change="getList">
                  <el-option
                    v-for="time,i in timedata"
                    :key="i"
                    :label="time.lable"
                    :value="time.value">
                  </el-option>
                </el-select>
                <el-select v-model="queryParem.salesQuota" placeholder="请选择" size="small" @change="getList">
                    <el-option
                      label="按实际回款计算"
                      :value="1">
                    </el-option>
                    <el-option
                      label="按销售额计算"
                      :value="2">
                    </el-option>
                  </el-select>
            </div>
            <business style="margin-top: 25px;" :chart-data="chartData"></business>
          </el-col>
          <el-col :span="5">
            <div>
              <p><b>数据总况</b></p>
              <div>
                <div class="card-panel">
                  <div class="card-panel-icon-wrapper icon-mianshi">
                    <svg-icon icon-class="shouru" class-name="card-panel-icon" />
                  </div>
                  <div class="card-panel-description">
                    <div class="card-panel-text">
                      收入
                    </div>
                    <count-to :start-val="0" :end-val="zongData.totalincome" :duration="3600" class="card-panel-num2" />
                    <div>
                      <span class="tongbi" v-if="zongData.totalincome-zongData.lastyearTotalincome>0">
                        同比增长
                        <span class=" tongbi-up">{{Math.abs(((zongData.totalincome-zongData.lastyearTotalincome)/zongData.lastyearTotalincome*100).toFixed(2))}}% <svg-icon icon-class="sanup" class-name="card-panel-ico" /></span>
                      </span>
                      <span class="tongbi" v-else>
                        同比下降
                        <span class="tongbi-down">{{Math.abs(((zongData.totalincome-zongData.lastyearTotalincome)/zongData.lastyearTotalincome*100).toFixed(2))}}% <svg-icon icon-class="sandow" class-name="card-panel-ico" /></span>
                      </span>
                    </div>
                  </div>
                </div>
                <div class="card-panel">
                  <div class="card-panel-icon-wrapper icon-zhichu">
                    <svg-icon icon-class="zhichu" class-name="card-panel-icon" />
                  </div>
                  <div class="card-panel-description">
                    <div class="card-panel-text">
                      支出
                    </div>
                    <count-to :start-val="0" :end-val="zongData.totalexpenditure" :duration="3600" class="card-panel-num2" />
                    <div>
                      <span class="tongbi" v-if="zongData.totalexpenditure-zongData.lastyearTotalexpenditure>0">
                          同比增长
                          <span class=" tongbi-up">{{Math.abs(((zongData.totalexpenditure-zongData.lastyearTotalexpenditure)/zongData.lastyearTotalexpenditure*100).toFixed(2))}}% <svg-icon icon-class="sanup" class-name="card-panel-ico" /></span>
                        </span>
                        <span class="tongbi" v-else>
                          同比下降
                          <span class="tongbi-down">{{Math.abs(((zongData.totalexpenditure-zongData.lastyearTotalexpenditure)/zongData.lastyearTotalexpenditure*100).toFixed(2))}}% <svg-icon icon-class="sandow" class-name="card-panel-ico" /></span>
                        </span>
                      </div>
                    </div>
                </div>
                <div class="card-panel">
                  <div class="card-panel-icon-wrapper icon-lirun">
                    <svg-icon icon-class="yingli" class-name="card-panel-icon" />
                  </div>
                  <div class="card-panel-description">
                    <div class="card-panel-text">
                      利润
                    </div>
                    <count-to :start-val="0" :end-val="zongData.totalProfit" :duration="3600" class="card-panel-num2" />
                    <div>
                      <span class="tongbi" v-if="zongData.totalProfit-zongData.lastyeartotalProfit>0">
                          同比增长
                          <span class="tongbi-up">{{((zongData.totalProfit-zongData.lastyeartotalProfit)/zongData.lastyeartotalProfit*100).toFixed(2)}}% <svg-icon icon-class="sanup" class-name="card-panel-ico" /></span>
                        </span>
                        <span class="tongbi" v-else>
                          同比下降
                          <span class="tongbi-down">{{Math.abs(((zongData.totalProfit-zongData.lastyeartotalProfit)/zongData.lastyeartotalProfit*100).toFixed(2))}}% <svg-icon icon-class="sandow" class-name="card-panel-ico" /></span>
                        </span>
                      </div>
                    </div>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
      <el-row v-if="biaoshi!==1" :gutter="20">
            <el-col :xs="24" :sm="12" :lg="8">
              <div class="dom-dow">
                <xiangqing :chart-data="chartData.lirunData" :tad-data="onProfitData" :mone="expectedData" :title="'利润数据'"></xiangqing>
              </div>
            </el-col>
              <el-col :xs="24" :sm="12" :lg="8">
                <div class="dom-dow">
                  <xiangqing :chart-data="chartData.actualData" :tad-data="onIncomeData" :mone="expectedData" :title="'收入数据'"></xiangqing>
                </div>
            </el-col>
            <el-col :xs="24" :sm="12" :lg="8">
              <div class="dom-dow">
                <xiangqing :chart-data="chartData.shouruData" :tad-data="onExpenditureData" :mone="expectedData" :title="'支出数据'"></xiangqing>
              </div>
            </el-col>
     </el-row>
  </div>
</template>

<script>
  import {listmanagement} from "@/api/analysis/management.js"
  import business from "../../components/management/business"
  import xiangqing from "../../components/management/xiangqing"
  import CountTo from 'vue-count-to'
  export default {
    name:"Management",
    props: ["biaoshi"],
    data(){
      return{
        queryParem:{
          recentYears:"",
          salesQuota:1,
        },
        chartData:{
          expectedData:[],
          actualData:[],
          shouruData:[],
          lirunData:[],
        },
        onProfitData:[],
        onIncomeData:[],
        onExpenditureData:[],
        zongData:{},
        expectedData:[],
        timedata:[],
      }
    },
    components:{
      business,
      CountTo,
      xiangqing
    },
    created() {
      this.time()
      this.getList()

    },
    methods:{
      time(){
        let time = new Date()
        let y,m,d
        y = time.getFullYear()
        m = time.getMonth()+1
        d = time.getDate()
        this.timedata.push({lable:"最近一年",value:y+"-"+m+"-"+d})
        this.timedata.push({lable:y-1,value:y-1+"-12-30"})
        this.timedata.push({lable:y-2,value:y-2+"-12-30"})
        this.queryParem.recentYears = y+"-"+m+"-"+d
      },
      getList(){
        this.chartData={
          expectedData:[],
          actualData:[],
          shouruData:[],
          lirunData:[],
        },
        this.onProfitData=[],
        this.onIncomeData=[],
        this.onExpenditureData=[],
        this.zongData={},
        this.expectedData=[],
        listmanagement(this.queryParem).then(res=>{
          this.zongData = res.data.totle
          res.data.liMan.forEach(item=>{
            this.expectedData.push(item.customMonth.split("-")[1])
            this.chartData.expectedData.push(item.customMonth)
            this.chartData.actualData.push(item.income.toFixed(2));
            this.chartData.shouruData.push(item.expenditure.toFixed(2));
            this.chartData.lirunData.push(item.profit.toFixed(2));
          })
          res.data.lastyearMan.forEach(item=>{
            this.onIncomeData.push(item.onIncomeData.toFixed(2));
            this.onProfitData.push(item.onProfitData.toFixed(2));
            this.onExpenditureData.push(item.onExpenditureData.toFixed(2));
          })
        })
      },
    },

  }
</script>

<style scoped>
  .manage-top{
    background-color: #fff;
    padding: 20px;
    height: 470px;
  }
  .manage{
    margin: 15px 10px 0 10px;
  }
  .card-panel-icon-wrapper {
    float: left;
    margin: 30px 0  0 16px;
    padding:8px 8px;
    border-radius: 10px;
    color: #fff;
  }
  .card-panel {
    margin-bottom:20px ;
    border-radius: 2px;
    height: 110px;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background:rgb(240,249,255);
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);
    }
    .tongbi{
      color: #c0c4cc;
      font-size: 14px;
    }
    .icon-mianshi {

      background-color:#0081FF ;
    }
    .icon-zhichu{
      background-color:#33B5E5 ;
    }
    .icon-lirun{
      background-color: #ff9f43 ;
    }
    .card-panel-icon {
      font-size: 28px;
    }
    .card-panel-text {
      color: #606266;
      font-size: 16px;

    }
    .card-panel-num{
      margin-left: 20px;
      font-size: 20px;
      font-weight: 800;
      color: #c0c4cc;
    }
    .card-panel-num2 {
      font-size: 20px;
      color: #303133;
      font-weight: 700;
      line-height: 40px;
    }
    .tongbi-up{
      color: #28c76f;
    }
    .tongbi-down{
      color:#ea5455 ;
    }
    .card-panel-description {
      float: left;
      font-weight: bold;
      margin:20px 5px 20px 10px;
    }
    .dom-dow{
      background-color: #fff;
      margin-top: 20px;
      padding: 20px 0 20px 20px;
    }
</style>

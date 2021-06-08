<template>
  <div class="manage">
    <div class="manage-top">
      <div  style="float: left;font-size: 18px;color: #303133;">收入与支出</div>
      <div style="float: right;margin-bottom: 20px;">
        <el-select v-model="queryParem.recentYears" placeholder="请选择" size="small" @change="getList">
            <el-option
              v-for="time,i in timedata"
              :key="i"
              :label="time.lable"
              :value="time.value">
            </el-option>
          </el-select>
      </div>
      <div style="clear: both;"></div>
      <div style="display: flex;justify-content: space-between;">
        <div class="card-panel">
          <div class="card-panel-icon-wrapper icon-mianshi">
            <svg-icon icon-class="shouru" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              收入
            </div>
            <count-to :start-val="0" :end-val="zongData.incomeTotal" :duration="3600" class="card-panel-num2" />
          </div>
        </div>
        <div class="card-panel zongzhichu">
          <div class="card-panel-icon-wrapper icon-zhichu">
            <svg-icon icon-class="zhichu" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              总支出
            </div>
            <count-to :start-val="0" :end-val="zongData.expenditureTotal" :duration="3600" class="card-panel-num2" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              外派工资支出
            </div>
            <count-to :start-val="0" :end-val="zongData.expatriateSalaryTotal" :duration="3600" class="card-panel-num2" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              内部工资支出
            </div>
            <count-to :start-val="0" :end-val="zongData.insideSalaryTotal" :duration="3600" class="card-panel-num2" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              其他支出
            </div>
            <count-to :start-val="0" :end-val="zongData.otherExpensesTotal" :duration="3600" class="card-panel-num2" />
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
            <count-to :start-val="0" :end-val="zongData.profitTotal" :duration="3600" class="card-panel-num2" />
            </div>
        </div>
      </div>
      <receipts-bur :chart-data="chartData"></receipts-bur>
    </div>
    <div v-if="biaoshi!==1" style="background-color: #fff;margin-top:20px;padding: 30px;">
      <span  style="font-size: 18px;color: #303133;font-weight: 400;">数据详情</span>
      <p></p>
      <el-table v-loading="loading" :data="zongdatas" border>

        <el-table-column label="日期"  prop="customMonth"/>
        <el-table-column label="总收入"  prop="income">
          <template slot-scope="scope">
            <span>{{scope.row.income.toFixed(2)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="总支出" >
          <template slot-scope="scope">
            <span>{{(scope.row.expatriateSalary + scope.row.insideSalary + scope.row.otherExpenses).toFixed(2)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="外派工资支出"  prop="expatriateSalary">
        <template slot-scope="scope">
            <span>{{scope.row.expatriateSalary.toFixed(2)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="内部工资支出"  prop="insideSalary"/>
        <el-table-column label="其他支出"  prop="otherExpenses"/>
        <el-table-column label="利润"  prop="profit">
          <template slot-scope="scope">
            <span>{{scope.row.profit.toFixed(2)}}</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import {listincomeexpenditure} from "@/api/analysis/incomeexpenditure.js"
 import receiptsBur from "../../components/incomeexpenditure/receiptsBur.vue"
 import CountTo from 'vue-count-to'
  export default {
    name:"Incomeexpenditure",
    props:["biaoshi"],
    data(){
      return{
        queryParem:{
          recentYears:"",
        },
        chartData:{
          expectedData:[],
          income:[],
          expatriateSalary:[],
          insideSalary:[],
          otherExpenses:[],
          profit:[],
        },
        loading:true,
        zongData:{},
        timedata:[],
        zongdatas:[],
      }
    },
    created() {
      this.time()
      this.getList()
    },
    components:{
      receiptsBur,
      CountTo
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
            income:[],
            expatriateSalary:[],
            insideSalary:[],
            otherExpenses:[],
            profit:[],
          },
          this.zongData={},
          this.loading = true
          listincomeexpenditure(this.queryParem).then(res=>{
            this.zongdatas = res.data.liMan
            this.zongData = res.data.maTotle
            this.loading = false
            res.data.liMan.forEach(item=>{
              this.chartData.expectedData.push(item.customMonth)
              this.chartData.income.push(item.income.toFixed(2));
              this.chartData.expatriateSalary.push(item.expatriateSalary.toFixed(2));
              this.chartData.insideSalary.push(item.insideSalary.toFixed(2));
              this.chartData.otherExpenses.push(item.otherExpenses.toFixed(2));
              this.chartData.profit.push(item.profit.toFixed(2));
            })
          })
        },
     },
  }
</script>

<style scoped>
  .manage{
    margin: 15px 10px 0 10px;
  }
  .manage-top{
    background-color: #fff;
    padding: 20px;
    /* height: 470px; */
  }
  .card-panel-icon-wrapper {
    float: left;
    margin: 20px 0  0 16px;
    padding:8px 8px;
    border-radius: 10px;
    color: #fff;
  }
  .card-panel {
    margin-bottom:20px ;
    border-radius: 2px;
    height: 80px;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;

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
      margin:20px 20px 20px 10px;
    }
    .zongzhichu{
      border-left:1px solid #dcdfe6;
      border-right:1px solid #dcdfe6;
      padding: 0 70px;
    }
</style>

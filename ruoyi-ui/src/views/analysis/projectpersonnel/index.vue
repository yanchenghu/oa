<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" >
      <el-form-item prop="currentDate">
        <el-date-picker
          type="date"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
          size="small"
          :clearable="false"
          v-model="queryParams.currentDate"
          placeholder="选择月"
          :picker-options="pickerOptions3"
          @change="getList"
          >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="small" @click="getList">查询</el-button>
      </el-form-item>

     <!-- <el-form-item class="asd">
       <el-button type="primary" v-hasPermi="['analysis:returnedmoney:export']" plain  size="small" @click="handleExport">导出</el-button>
     </el-form-item> -->
    </el-form>
    <el-table border  v-loading="loading" :data="servicechargeList" show-summary :summary-method="getSummaries">
      <el-table-column
        label="序号"
        type="index"
        width="50">
      </el-table-column>
      <el-table-column label="客户名称"  prop="corpName"/>
      <el-table-column :label="currentDate+'月'"  prop="thismonthNum" >
      <template slot-scope="scope">
          <el-button
            size="medium"
            type="text"
            @click="handlesele(scope.row,currentDate)"
          >{{scope.row.thismonthNum}}</el-button>
        </template>
      </el-table-column>
      <el-table-column :label="nMonthlastDate+'月'"  prop="lastmonthNum">
        <template slot-scope="scope">
          <el-button
            size="medium"
            type="text"
            @click="handlesele(scope.row,nMonthlastDate)"
          >{{scope.row.lastmonthNum}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="环比"  prop="accumulatedArrears">
        <template slot-scope="scope">
          <span :style="{color:scope.row.thismonthNum - scope.row.lastmonthNum>=0?'#0000FF':'red'}">
            {{scope.row.thismonthNum - scope.row.lastmonthNum}}
          </span>
        </template>
      </el-table-column>
      <el-table-column :label="lastYearDate+'月'"  prop="lastyearmonthNum">
        <template slot-scope="scope">
          <el-button
            size="medium"
            type="text"
            @click="handlesele(scope.row,lastYearDate)"
          >{{scope.row.lastyearmonthNum}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="同比"  prop="accumulatedArrears">
        <template slot-scope="scope">
          <span :style="{color:scope.row.thismonthNum - scope.row.lastyearmonthNum>=0?'#0000FF':'red'}">
            {{scope.row.thismonthNum - scope.row.lastyearmonthNum}}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="回款周期"  prop="collectionCycle" :formatter="companySituationFormat"/>
    </el-table>

    <el-dialog  :title="title" :visible.sync="open" width="550px" append-to-body>
      <el-table v-loading="loading2" :data="tabadata">
        <el-table-column
          label="序列"
          type="index"
          width="50">
        </el-table-column>
        <el-table-column label="姓名"  prop="customerName" width="60"/>
        <el-table-column label="当月支出"  prop="samemonthExpenditure"/>
        <el-table-column label="当月回款"  prop="samemonthCollection"/>
        <el-table-column label="当月盈利"  >
          <template slot-scope="scope">
            <span>{{scope.row.samemonthCollection-scope.row.samemonthExpenditure}}</span>
          </template>
        </el-table-column>
        <el-table-column label="时间"  prop="wagesTime" width="120"/>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
  import { listReturnedmoney,getaccumulatedlist} from "@/api/analysis/projectpersonnel";
  export default {
    name:"Projectpersonnel",
    data(){
      return{
        pickerOptions3:{
          disabledDate:(time) => {
              return time.getTime() >  Date.now()
          }
        },
        queryParams: {
          currentDate:null,
        },
        servicechargeList:[],
        hikuanlist:[],
        currentDate:"",
        lastYearDate:"",
        nMonthlastDate:"",
        tabadata:[],
        companyperiod:[],
        loading:true,
        loading2:true,
        open:false,
        title:"",
      }
    },
    created() {
      this.queryParams.currentDate = this.parseTime(new Date(),"{y}-{m}-{d}")
      this.getDicts("mar_company_period").then(response => {
        this.companyperiod = response.data;
      });
      this.getList()

    },
    methods:{
      getList(){
        this.loading = true;
        listReturnedmoney(this.queryParams).then(res => {
          this.servicechargeList = res.data.list;
          this.currentDate = res.data.currentDate
          this.lastYearDate = res.data.lastYearDate
          this.nMonthlastDate = res.data.nMonthlastDate
          this.loading = false;
        });
      },
      companySituationFormat(row, column) {
        return this.selectDictLabel(this.companyperiod, row.collectionCycle);
      },
      handlesele(row,tit){
        this.open = true
        this.title = tit + "月"
        // this.loading2 = true
        // getaccumulatedlist().then(res=>{

        // })
      },
      getSummaries(param) {
        const { columns, data } = param
        const len = columns.length
        const sums = []
        columns.forEach((column, index) => {
        //如果是第一列，则最后一行展示为“总计”两个字
          if (index === 0) {
            sums[index] = '总计'
            //如果是最后一列，索引为列数-1，则显示计算总和
          } else if (index === len - 1) {
            sums[index] = ''
          } else if (index === 4) {
            sums[index] = sums[2]-sums[3]
          } else if (index === 6) {
            sums[index] = sums[2]-sums[5]
          } else {
            const values = data.map(item => Number(item[column.property]))
            if (!values.every(value => isNaN(value))) {
              sums[index] = values.reduce((prev, curr) => {
                const value = Number(curr)
                if (!isNaN(value)) {
                  return prev + curr
                } else {
                  return prev
                }
              }, 0)
            }
          }
        })
        return sums
      },
    }
  }
</script>

<style>
</style>

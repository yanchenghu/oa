<template>
  <div class="app-containe">
    <el-row style="display: flex;">
      <!-- 需求表格 -->
      <el-col style="width: 190px; text-align: center; background: #fff;padding: 10px;margin-right: 10px;">
        <el-input prefix-icon="el-icon-search" v-model="queryParams.corpName" size="medium" placeholder="搜索需求" @input="input">
        </el-input>
        <el-radio-group v-model="fromdata.corpCode" size="medium" style="margin-top: 10px;" @change="changes" v-loading="loading2">
           <div  v-for="nood in tabledata" style="width: 100% ;height: 40px;">
              <el-tooltip class="item" effect="dark" :content="nood.corp_name" v-if="nood.corp_name.length>=8" placement="top">
                    <el-radio-button style="display: inline-block; width: 100%;"  :label="nood.corp_code">{{`${nood.corp_name.slice(0,8)}...`}}</el-radio-button>
              </el-tooltip>
              <el-radio-button style="display: inline-block; width: 100%;" v-else :label="nood.corp_code">{{nood.corp_name}}</el-radio-button>
           </div>
        </el-radio-group>
        <el-pagination
          layout="prev, next"
          v-show="total2>0"
          :total="total2"
          :current-page.sync="queryParams.pageNum"
          :page-size.sync="queryParams.pageSize"
          @current-change="getxuqiulist"
          >
        </el-pagination>
    </el-col>
    <el-col style="background-color: #fff;padding: 10px;width:calc( 100% - 200px);">
       <el-form style="padding-bottom: 25px;" ref="queryForm" :model="fromdata" label-width="80px" :inline="true"> 
         <el-form-item  prop="customerName">
           <el-input
             v-model="fromdata.customerName"
             placeholder="请输入姓名"
             clearable
             size="small"
             @keyup.enter.native="change"
           />
         </el-form-item>
         <el-form-item>
           <el-button type="primary" icon="el-icon-search" size="small" @click="change">查询</el-button>
         </el-form-item>
         <el-form-item>
           <el-form-item  prop="startTime">
             <el-date-picker
               v-model="value1"
               type="daterange"
               size="small"
               format="yyyy 年 MM 月 dd 日"
               value-format="yyyy-MM-dd"
               range-separator="至"
               start-placeholder="开始日期"
               end-placeholder="结束日期"
               :picker-options="pickerOptions3"
               @change="change">
             </el-date-picker>
           </el-form-item>
           </el-date-picker>
         </el-form-item>
         <el-form-item class="asd">
           <el-button  type="primary" size="small" @click="resetQuery">重置</el-button>
           <el-button type="primary" v-hasPermi="['analysis:returnedmoney:export']" plain  size="small" @click="handleExport">导出</el-button>
         </el-form-item>
       </el-form>
       <!-- 数据表格 -->
       <el-table   :data="msgtaba" v-loading="loading" show-summary :summary-method="getSummaries" :span-method="arraySpanMethod">
         <el-table-column prop="customerName" label="姓名" >
         </el-table-column>
         <el-table-column prop="customerTel" label="电话" width="150" >
           <template slot-scope="scope">
             <span>{{scope.row.customerTel}}</span>
           </template>
         </el-table-column>
         <el-table-column prop="syqstartTime" label="入职日期"  width="100">
         </el-table-column>
         <el-table-column prop="outofProjecttime" label="出项日期" width="150"/>
         <el-table-column prop="salary" label="工资" />
         <el-table-column prop="servicePay" label="服务费" width="160"/>
         <el-table-column prop="lastcollecMoth" label="最近回款月份" width="100"/>
         <el-table-column prop="cumulativeSalary" label="累计工资" />
         <el-table-column prop="cumulativeServicepay" label="累计服务费" />
         <el-table-column label="累计利润">
           <template slot-scope="scope">
             <span>{{(scope.row.cumulativeServicepay - scope.row.cumulativeSalary).toFixed(2)}}</span>
           </template>
        </el-table-column>
         <el-table-column label="操作"  class-name="small-padding fixed-width" width="100">
           <template slot-scope="scope">
             <el-button
               size="medium"
               type="text"
               @click="handlesee(scope.row)"
             ><svg-icon icon-class="eye-open" class="icons"/>查看明细</el-button>
           </template>
         </el-table-column>
       </el-table>
    </el-col>
 <!-- 筛选列表 -->
    </el-row>
       <el-dialog :title="title" :visible.sync="open" width="550px">
       <el-table  :data="customerleve">
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
             <span>{{(scope.row.samemonthCollection-scope.row.samemonthExpenditure).toFixed(2)}}</span>
           </template>
         </el-table-column>
         <el-table-column label="时间"  prop="wagesTime" width="120"/>
       </el-table>
      </el-dialog>
  </div>
</template>
<script>
  import {
    listexpatriatewages,
    getexpatriatewages,
    exportexpatriatewages,
    listFollow,
  } from "@/api/analysis/expatriatewages";
  import {debounce} from "@/utils/ruoyi.js"
  export default {
    name: "Expatriatewages",
    data() {
      return {
        pickerOptions3:{
          disabledDate:(time) => {
              return time.getTime() >  Date.now()
          }
        },
        loading2:true,
        reg:/^(\d{3})\d{4}(\d{4})$/,
        // 表单内容
        fromdata: {
          startTime:null,
          endTime:null,
          customerName:null,
          corpCode:null,
        },
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          corpName:null,
        },
        // 表格数据
        msgtaba: [],
        value1:null,
        // 需求数组
        tabledata: [],
        // 表单参数
        form: {},
        // 遮罩层
        loading: true,
        src:"",
        total2:0,
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 简历表格数据
        expatriatewagesList: [],
        // 弹出层标题
        title: "",
        customerleve:[],
        // 是否显示弹出层
        open: false,
        // 表单校验
        rules: {
          customerName: [{
            required: true,
            message: "简历姓名不能为空",
            trigger: "blur"
          }],
          customerTel: [{
            required: true,
            message: "联系电话不能为空",
            trigger: "blur"
          }],
        },
      };
    },
    created() {
      this.getxuqiulist()
      this.getLit()
    },
    methods: {
      input:debounce(function(){this.inpu()},500),
      inpu(){
        this.getxuqiulist();
      },
      getxuqiulist(){
        this.loading2 = true
        listFollow(this.queryParams).then(res=>{
          this.tabledata = res.rows
          this.loading2 = false
          this.total2 = res.total
        })
      },
      // 筛选更多
      getList(){
          if(this.value1==null){
            this.value1 = null
            this.fromdata.startTime = null
            this.fromdata.endTime =null
          }else{
            this.fromdata.startTime = this.value1[0]
            this.fromdata.endTime = this.value1[1]
          }
          this.loading = true;
          listexpatriatewages(this.fromdata).then(response => {
            this.msgtaba = response.rows;
            this.loading = false;
          })
      },
      arraySpanMethod({ row, column, rowIndex, columnIndex }) {

          },
      getSummaries(param){
        const { columns, data } = param
        const sums = []
        let values = []
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = '总合计'
            return
            //如果是最后一列，索引为列数-1，则显示计算总和
          }
          if(column.property === "customerTel"){
            values = data.map(item => Number(item["cumulativeServicepay"]))
            sums[index] = ' 累计回款: '+values.reduce((prev, curr) => {
              const value = Number(curr)
              if (!isNaN(value)) {
                return prev + curr
              } else {
                return prev
              }
            }, 0).toFixed(2)
          }else if(column.property === "outofProjecttime"){
            values = data.map(item => Number(item["cumulativeSalary"]))
            sums[index] = ' 累计支出: '+values.reduce((prev, curr) => {
              const value = Number(curr)
              if (!isNaN(value)) {
                return prev + curr
              } else {
                return prev
              }
            }, 0).toFixed(2)
          }else if(column.property === "servicePay"){
            values = data.map(item => Number(item.cumulativeServicepay-item.cumulativeSalary))
            sums[index] = ' 累计盈利: '+values.reduce((prev, curr) => {
              const value = Number(curr)
              if (!isNaN(value)) {
                return prev + curr
              } else {
                return prev
              }
            }, 0).toFixed(2)
          }
        })
        return sums
      },
      change(value){
        this.getList()
      },
      changes(value){
        this.getList()
      },
     resetQuery() {
       this.fromdata={
          startTime:null,
          endTime:null,
          customerName:null,
          corpCode:null,
        },
        this.value1 = null
       this.getList()
     },
     // 查看
     handlesee(row){
       getexpatriatewages({customerCode:row.customerCode,corpCode:row.corpCode}).then(res=>{
          this.title = "累计盈利明细列表"
          this.open = true
          this.customerleve = res.rows
       })
     },
     /** 导出按钮操作 */
     handleExport() {
       const fromdata = this.fromdata;
       this.$confirm('是否确认导出所有服务费核算请求书数据项?', "警告", {
           confirmButtonText: "确定",
           cancelButtonText: "取消",
           type: "warning"
         }).then(function() {
           return exportexpatriatewages(fromdata);
         }).then(response => {
           this.download(response.msg);
         })
     }
    }
  };
</script>
<style scoped>
  >>>.el-radio-button__inner {
      width: 100%;
  }
  .app-containe{
    padding: 10px;
  }
</style>

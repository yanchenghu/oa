<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" >
      <el-form-item prop="corpName">
        <el-input
          v-model="queryParams.corpName"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="small" @click="handleQuery">查询</el-button>
      </el-form-item>
      <el-form-item prop="latestcollectionMonth">
        <el-date-picker
          type="month"
          style="width:160px"
          format="yyyy 年 MM 月 "
          value-format="yyyy-MM-dd"
          size="small"
          v-model="queryParams.latestcollectionMonth"
          placeholder="选择月"
          :picker-options="pickerOptions3"
          @change="handleQuery"
          >
        </el-date-picker>
      </el-form-item>
     <el-form-item class="asd">
       <el-button type="primary" v-hasPermi="['analysis:returnedmoney:export']" plain  size="small" @click="handleExport">导出</el-button>
     </el-form-item>
    </el-form>
    <el-table  v-loading="loading" :data="servicechargeList" @selection-change="handleSelectionChange" show-summary :summary-method="getSummaries">
      <el-table-column
        label="序号"
        type="index"
        width="50">
      </el-table-column>
      <el-table-column label="客户名称"  prop="corpName"/>
      <el-table-column label="累计回款"  prop="accumulatedCollection" >
      <template slot-scope="scope">
          <el-button
            size="medium"
            type="text"
            @click="handlesele(scope.row,1)"
          >{{scope.row.accumulatedCollection.toFixed(2)}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="累计支出"  prop="accumulatedExpenditure">
        <template slot-scope="scope">
          <el-button
            size="medium"
            type="text"
            @click="handlesele(scope.row,2)"
          >{{scope.row.accumulatedExpenditure.toFixed(2)}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="累计盈利"  prop="accumulatedProfit">
        <template slot-scope="scope">
          <el-button
            size="medium"
            type="text"
            @click="handlesele(scope.row,3)"
          >{{scope.row.accumulatedProfit}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="累计欠款"  prop="accumulatedArrears" />
      <el-table-column label="最近回款月份"  prop="latestcollectionMonth">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.latestcollectionMonth,"{y}-{m}-{d}")}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作"  class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="medium"
            type="text"
            @click="handlesele(scope.row)"
          ><svg-icon icon-class="eye-open" class="icons"/>查看明细</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog  :title="title" :visible.sync="open" width="550px" append-to-body>
      <el-form :model="queryParams" ref="queryForm" :inline="true">
        <el-form-item prop="customerName">
          <el-input
            v-model="searchmsg"
            placeholder="请输入姓名"
            clearable
            size="small"
            @keyup.enter.native="handleQuery2"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="small" @click="handleQuery2">查询</el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loading2" :data="customerleve" v-if="title== '累计回款列表'">
        <el-table-column
          label="序列"
          type="index"
          width="50">
        </el-table-column>
        <el-table-column label="姓名"  prop="customerName"/>
        <el-table-column label="回款工资总额"  prop="collectionTotal"/>
        <el-table-column label="开始时间"  prop="startTime"/>
        <el-table-column label="结束时间"  prop="endTime"/>
      </el-table>

      <el-table v-loading="loading2" :data="customerleve" v-else-if="title== '累计支出列表'">
        <el-table-column
          label="序列"
          type="index"
          width="50">
        </el-table-column>
        <el-table-column label="姓名"  prop="customerName"/>
        <el-table-column label="支出工资总额"  prop="expenditureTotal"/>
        <el-table-column label="开始时间"  prop="startTime"/>
        <el-table-column label="结束时间"  prop="endTime"/>
      </el-table>

      <el-table v-loading="loading2" :data="customerleve" v-else-if="title== '累计盈利列表'">
        <el-table-column
          label="序列"
          type="index"
          width="50">
        </el-table-column>
        <el-table-column label="姓名"  prop="customerName"/>
        <el-table-column label="累计支出"  prop="expenditureTotal"/>
        <el-table-column label="累计回款"  prop="collectionTotal"/>
        <el-table-column label="累计盈利"  >
          <template slot-scope="scope">
            <span>{{scope.row.collectionTotal - scope.row.expenditureTotal}}</span>
          </template>
        </el-table-column>
      </el-table>

      <el-table v-loading="loading2" :data="customerleve" v-else-if="title== '累计盈利明细列表'">
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
import { listReturnedmoney, getReturnedmoney,getExpenditurelist, delReturnedmoney,getaccumulatedlist ,getsamemonthlist,  exportReturnedmoney } from "@/api/analysis/returnedmoney";
export default {
  name: "Returnedmoney",
  data() {
    return {
      pickerOptions3:{
        disabledDate:(time) => {
            return time.getTime() >  Date.now()
        }
      },
      wenjian:false,
      src:"",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      singles:true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 服务费核算请求书表格数据
      servicechargeList: [],
      // 付款状态
      customerleve:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open3:false,
      loading2:false,
      // 查询参数
      queryParams: {
        corpName:null,
        latestcollectionMonth:null,
      },
      searchmsg:"",
      dialogImageUrl:"",
      dialogVisible:false,
      open2:false,
      // 表单参数
      form: {},
      wen:true,
      partylist:[],
      timer:null,
      // 表单校验
      rules: {
        months: [{
          required: true,
          message: "工资日期不能为空",
          trigger: ["blur","change" ]
        }, ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getSummaries(param) {
      const { columns, data } = param
      const len = columns.length
      const sums = []
      columns.forEach((column, index) => {
      //如果是第一列，则最后一行展示为“总计”两个字
        if (index === 0) {
          sums[index] = '总计'
          //如果是最后一列，索引为列数-1，则显示计算总和
        } else if (index === len - 2) {
          sums[index] = ''
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
            sums[index] = sums[index].toFixed(2)
          }
        }
      })
      return sums
    },
    handlesele(row,ind){
      let data = {
        corpCode:row.corpCode
      }
      this.searchmsg=""
      if(ind==1){
        this.title = "累计回款列表"
        getReturnedmoney(data).then(res=>{
          this.open = true
          this.customerleve = res.rows
          this.partylist = this.customerleve
        })
      }else if(ind==2){
        this.title = "累计支出列表"
        getExpenditurelist(data).then(res=>{
          this.open = true
          this.customerleve = res.rows
          this.partylist = this.customerleve
        })
      }else if(ind==3){
        this.title = "累计盈利列表"
        getaccumulatedlist(data).then(res=>{
          this.open = true
          this.customerleve = res.rows
          this.partylist = this.customerleve
        })
      }else{
        this.title = "累计盈利明细列表"
        getsamemonthlist(data).then(res=>{
          this.open = true
          this.customerleve = res.rows
          this.partylist = this.customerleve
        })
      }

    },
    /** 查询服务费核算请求书列表 */
    getList() {
      this.loading = true;
      listReturnedmoney(this.queryParams).then(response => {
        this.servicechargeList = response.rows;
        this.servicechargeList.forEach(item=>{item.accumulatedProfit = (item.accumulatedCollection - item.accumulatedExpenditure).toFixed(2)})
        this.total = response.total;
        this.loading = false;
      });
    },
    // 表单重置
    reset() {
      this.wen = true
      this.form = {
        id: null,
        customerName: null,
        customerTel: null,
        customerCode:null,
        overAttence:null,
        realAttence:null,
        projectSalary:null,
        buzhuSalary:null,
        baoxianSalary:null,
        realSalary:null,
        months:null,
      };
      this.resetForm("form");
      if(this.$refs.file!==undefined){
        this.$refs.file.clearFiles()
      }
    },
    handleQuery2(){
        let that = this
        this.loading2=true
        if(this.searchmsg==""){
        setTimeout(function(){
          that.customerleve=that.partylist
          that.loading2=false
        },1000)
        }else{
          clearInterval(this.timer)
          this.timer=setTimeout(function(){
            var data= that.partylist.filter(item=>{
              return item.customerName == that.searchmsg
             })
            that.customerleve = data
            that.loading2=false
          },1000)
        }
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.singles = selection.length!==1
      this.single = selection
      this.multiple = !selection.length
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除服务费核算请求书编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delReturnedmoney(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有服务费核算请求书数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportReturnedmoney(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
<style>
  .el-table .warning-row {
      color: red;
  }
</style>

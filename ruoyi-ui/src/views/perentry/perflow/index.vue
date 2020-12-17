<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true"  label-width="70px" class="form">
        <el-form-item label="开始时间">
          <el-date-picker size="small"
            v-model="queryParams.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            :clearable="false">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker size="small"
            v-model="queryParams.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            :clearable="false">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-select  v-model="queryParams.settledCycle"  size="small" @change="handleQuery">
            <el-option
                :key="1"
                label="全部人员"
                :value="null"
              />
            <el-option
                :key="2" label="不包含离职人员" :value="2"
              />
            </el-select>
        </el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery" style="margin:3px 10px 0 -10px">查询</el-button>
    </el-form>
    <div style="font-size: 14px;">
      <span>人员入项列表: </span><span style="color: orangered;">共计 : {{ru.totalItem.entryNum}}人,总成本 : {{ru.totalItem.entrySalary}}元,总服务费 : {{ru.totalItem.entryServicePay}}元,总利润 : {{ru.totalItem.entryServicePay - ru.totalItem.entrySalary}}元,总利润率 : {{((ru.totalItem.entryServicePay - ru.totalItem.entrySalary)/ru.totalItem.entrySalary*100).toFixed(2)}}%;</span>
    </div>
    <div  style="color: orangered;font-size: 14px; margin-bottom: 10px;">
     <span>当前在项总人数: {{ru.nowItem.nowNum}}人,当月净成本 : {{ru.nowItem.sumSalary}}元,当月净服务费 : {{ru.nowItem.sumServicePay}}元,当月净利润 : {{ru.nowItem.sumServicePay-ru.nowItem.sumSalary}}元,净利润率 : {{((ru.nowItem.sumServicePay-ru.nowItem.sumSalary)/ru.nowItem.sumSalary*100).toFixed(2)}}%;</span>

    </div>
    <el-table
      :data="datalist"
      style="width: 100%"
      border
      :span-method="objectSpanMethod"
       v-loading="loading"
      :row-class-name="tableRowClassName"
      >
      <el-table-column type="index"></el-table-column>
      <el-table-column label="小组" prop="deptName">

      </el-table-column>
      <el-table-column label="小组利润" :key="1">
        <template slot-scope="scope">
           <span>{{scope.row.zongshu}} </span>
         </template>
      </el-table-column>
      <el-table-column label="小组利润率" :key="2" prop="lvrun">
          <template slot-scope="scope">
             <span>{{scope.row.lvrun.toFixed(2)}}%</span>
           </template>
      </el-table-column>
      <el-table-column label="招聘人员" prop="nickName" :key="3">

      </el-table-column>
      <el-table-column label="HR利润" :key="4" prop="hrzongshu">

      </el-table-column>
      <el-table-column label="备注"  prop="joinStatus" >
          <template slot-scope="scope">
             <span>{{scope.row.joinStatus==2?'社招':'二次入项'}}</span>
          </template>
      </el-table-column>
      <el-table-column label="姓名"  prop="customerName" :key="5">

      </el-table-column>
      <el-table-column label="利润" prop="profit" :key="6">

      </el-table-column>
      <el-table-column label="利润率" prop="profitMargin">
        <template slot-scope="scope">
           <span>{{scope.row.profitMargin}}%</span>
         </template>
      </el-table-column>
      <el-table-column label="工资" prop="salary">

      </el-table-column>
      <el-table-column label="服务费" prop="servicePay" :key="7"/>
      <el-table-column label="入职公司" prop="corpName" :key="13"/>
      <el-table-column label="入职时间" prop="syqstartTime":key="8">

      </el-table-column>
      <el-table-column label="转正时间" prop="syqEndtime" :key="9">

      </el-table-column>
      <el-table-column label="社保"  :key="10">
          <template slot-scope="scope">
            <span>{{scope.row.socSecopt==1?'交':"未交"}}</span>
          </template>
      </el-table-column>
      <el-table-column label="联系方式" prop="customerTel" :key="11">

      </el-table-column>
      <el-table-column label="入项地点" prop="entryAddress" :key="12">

      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <div style="font-size: 14px;margin-top: 80px;">
      <span>人员出项列表: </span><span style="color: orangered;">共计 : {{ru.digression.digrePeopleNum}}人, 总成本 : {{ru.digression.digreCost}}元, 总服务费 : {{ru.digression.digreService}}元, 总利润 : {{ru.digression.digreprofit}}元, 总利润率 : {{(ru.digression.digreprofit/ru.digression.digreCost*100).toFixed(2)}}%;</span>
    </div>
    <el-table :data="dataoutl"
      style="width: 100%;"
      border
      v-loading="loading2">
      <el-table-column label="姓名" prop="customer_name"/>
      <el-table-column label="电话" prop="customer_tel"/>
      <el-table-column label="入职公司" prop="corp_name"/>
      <el-table-column label="工资" prop="salary"/>
      <el-table-column label="服务费" prop="service_pay"/>
      <el-table-column label="利润" prop="profit"/>
      <el-table-column label="利润率" >
        <template slot-scope="scope">
          <span>{{(scope.row.profit/scope.row.salary*100).toFixed(2)}}%</span>
        </template>
      </el-table-column>
      <el-table-column label="出项日期" prop="outof_projecttime"/>
      <el-table-column label="借用物品" prop="borrow_sth">
        <template slot-scope="scope">
          <span>{{scope.row.borrow_sth?scope.row.borrow_sth:"已还"}}</span>
        </template>
      </el-table-column>
      <el-table-column label="招聘人员" prop="nick_name"/>
      <el-table-column label="离项原因" prop="quit_proreason" :formatter="customerFormat"/>
    </el-table>
    <pagination
      v-show="total2>0"
      :total="total2"
      :page.sync="queryParam.pageNum"
      :limit.sync="queryParam.pageSize"
      @pagination="getoutList"
    />
  </div>

</template>

<script>
  import { getlist ,getendlist,getoutlist} from "@/api/perentry/perflow.js";

  export default {
    name: "Yxdemand",
    data() {
      return{
        total:0,
        total2:0,
        loading:true,
        loading2:true,
        datalist:[],
        queryParams:{
          pageNum: 1,
          pageSize: 10,
          startTime:"",
          endTime:"",
          settledCycle:null,
        },
        queryParam:{
          pageNum: 1,
          pageSize: 10,
          startTime:"",
          endTime:"",
        },
        dataoutl:[],
        zongprofit:{},
        NormKindArr:[],
        NormKindIndex:null,
        normSubKindArr:[],
        normSubKindIndex:null,
        arr:[],
        indexList:[],
        customerleve:[],
        ru:{},
      }
    },
    created() {
      // 离项原因
      this.getDicts("outof_project_cause").then(response => {
        this.customerleve = response.data;
      });
      this.time()
      this.getList()
      this.getendList()
      this.getoutList()
    },
    methods:{
      time(){
        var nowDate = new Date();
        var cloneNowDate = new Date();
        var fullYear = nowDate.getFullYear();
        var month = nowDate.getMonth() + 1; // getMonth 方法返回 0-11，代表1-12月
        var endOfMonth = new Date(fullYear, month, 0).getDate(); // 获取本月最后一天
        function getFullDate(targetDate) {
                var D, y, m, d;
                if (targetDate) {
                    D = new Date(targetDate);
                    y = D.getFullYear();
                    m = D.getMonth() + 1;
                    d = D.getDate();
                } else {
                    y = fullYear;
                    m = month;
                    d = date;
                }
                m = m > 9 ? m : '0' + m;
                d = d > 9 ? d : '0' + d;
                return y + '-' + m + '-' + d;
            };
        this.queryParams.endTime = getFullDate(cloneNowDate.setDate(endOfMonth));//当月最后一天
        this.queryParams.startTime = getFullDate(cloneNowDate.setDate(1));//当月第一天
      },
      getendList(){
        getendlist(this.queryParams).then(res=>{
          this.ru = res
        })
      },
      customerFormat(row, column) {
        return this.selectDictLabel(this.customerleve, row.quit_proreason);
      },

      getoutList(){
        this.loading2=true
        let _this = this
        _this.queryParam.startTime = _this.queryParams.startTime
         _this.queryParam.endTime=_this.queryParams.endTime,
        getoutlist(this.queryParam).then(res=>{
          this.dataoutl = res.rows
          this.total2 = res.total
          this.loading2 = false
        })
      },
      getList(){
        this.loading=true
        getlist(this.queryParams).then(res=>{
          this.datalist = res.rows
          this.total = res.total
          this.loading = false
          this.getNormKind(this.datalist)
        })
      },

      handleQuery(){
        this.getList()
        this.getoutList()
      },
      indexMethod(){
        return
      },
      getNormKind (data) {
        this.NormKindArr = []
        this.NormKindIndex = null
        this.normSubKindArr = []
        this.normSubKindIndex  = null
        let zong = 0
        let hrzong = 0
        let zonggongzi = 0
        for (let i = 0; i < data.length; i++) {
          if (i === 0) {
            this.NormKindArr.push(1)
            this.NormKindIndex = 0
            this.normSubKindArr.push(1)
            this.normSubKindIndex = 0
            zong+=data[i].profit
            zonggongzi+=data[i].salary
          } else {
            if (data[i].deptName === data[i - 1].deptName) {
              this.NormKindArr[this.NormKindIndex] += 1
              this.NormKindArr.push(0)
              zong+=data[i].profit
              zonggongzi+=data[i].salary
              data[this.NormKindIndex].zongshu = zong
              data[this.NormKindIndex].lvrun = zong/zonggongzi*100
            } else {
              data[0].zongshu = zong
              data[0].lvrun = zong/zonggongzi*100
              zong=0
              zonggongzi = 0
              zong+=data[i].profit
              zonggongzi+=data[i].salary
              this.NormKindArr.push(1)
              this.NormKindIndex = i

            }
            if (data[i].nickName === data[i - 1].nickName) {
              this.normSubKindArr[this.normSubKindIndex] += 1
              this.normSubKindArr.push(0)
              hrzong+=data[i].profit
              data[this.normSubKindIndex].hrzongshu = hrzong
            } else {
              data[i-1].hrzongshu = hrzong
              hrzong=0
              hrzong+=data[i].profit
              this.normSubKindArr.push(1)
              this.normSubKindIndex = i
            }
          }
        }
      },
      objectSpanMethod({ row, column, rowIndex, columnIndex }){
        if(columnIndex === 1 || columnIndex === 2 || columnIndex === 3){
          const _row = this.NormKindArr[rowIndex]
          return {
            rowspan: _row,
            colspan: 1
          }

        }
        if(columnIndex === 4 || columnIndex === 5 ){
          const _row2 = this.normSubKindArr[rowIndex]
          return {
            rowspan: _row2,
            colspan: 1
          }
        }
      },
      findexIndex(){
        return this.NormKindArr.findIndex((v,index)=>v>0 && this.indexList.indexOf(index)== -1)
        },
      zong(scope){
          let q = this.findexIndex()
          if(q== -1){
          }else{
            this.indexList.push(q)
            this.arr.push({key: q, value:this.NormKindArr[q]})
            this.zong()
          }
      },
      zongs(scope){
        const reg = /^\d+.?\d{0,2}$/
        return reg.test(scope)
      },

       tableRowClassName({row, rowIndex}) {
         
          if (row.joinStatus == 4) {
            return 'warning-row';
          }
          return '';
      }


    }
  }
</script>

<style >
  .el-table .warning-row {
      color: red;
    }
  .app-container{
    /* background-color: rgb(240, 242, 245); */
  }
</style>

<template>
    <div class="app-container">
      <span style="font-weight: 400; font-size: 25px;">待面试列表</span>
      <p></p>
      <p style="float: right;font-size: 14px;"><span class="biaozhu h"><span class="dian a" ></span>未通过</span> <span class="biaozhu"><span class="dian b" ></span>待面试</span> <span class="biaozhu l"><span class="dian c" ></span>已通过</span></p>
      <el-table v-loading="loading" :data="dataList" :row-class-name="tableRowClassName">
        <el-table-column label="序号" type="index" width="50"/>
        <el-table-column label="姓名"  prop="customer_name">
          <template slot-scope="scope">
            <el-button type="text" size="medium" @click="tiaozhuan(scope.row)">{{scope.row.customer_name}}</el-button>
          </template>
        </el-table-column>
        <el-table-column label="公司名称"  prop="corp_name"/>
        <el-table-column label="项目名称"  prop="project_name"/>
        <el-table-column label="面试开始时间"  prop="begin_time"/>
        <el-table-column label="面试结束时间"  prop="end_time"/>
        <el-table-column label="确认面试时间"  prop="interview_time"/>
        <el-table-column label="人事"  prop="nick_name"/>
        <el-table-column label="未通过原因"  prop="follow_detail"/>
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>
</template>

<script>
  import {
   listdatas
  } from "@/api/demand/follow";
  export default{
    name:"waitingentry",
    data(){
      return{
        dataList:[],
        total:0,
        loading:false,
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
      }
    },
    created() {
      this.getList()
    },
    methods:{
      getList(){
        this.loading = true
        listdatas(this.queryParams).then(res=>{
          this.dataList = res.rows
          this.total = res.total
          this.loading = false
        })
      },
      tableRowClassName({row, rowIndex}) {
            if (row.follow_status === 6) {
              return 'warning-row';
            } else if (row.follow_status === 5||row.follow_status>6) {
              return 'success-row';
            }
            return '';
      },
      tiaozhuan(row){
        this.$router.push({path:"/follow/particulars",query:{ident:8,name:row.customer_name,row:row.demand_id}})
      },
    }
  }
</script>

<style scoped>
  >>>.warning-row{
    color: red;
  }
  >>>.success-row{
    color: green;
  }
  .biaozhu{
    margin-right:10px ;
  }
  .h{
    color: red;
  }
  .l{
    color: green;
  }
  .dian{
    display: inline-block;
    width: 10px;
    height: 10px;
    margin-right: 5px;
    border-radius: 50%;
  }
  .a{
    background-color: red;
  }
  .b{
    background-color: #606266;
  }
  .c{
    background-color: green;
  }
  /* style="background-color: red;" */
</style>

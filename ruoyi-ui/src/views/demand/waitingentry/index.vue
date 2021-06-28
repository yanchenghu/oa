<template>
    <div class="app-container">
      <span style="font-weight: 400; font-size: 25px;">待入项列表</span>
      <p></p>
      <p style="float: right;font-size: 14px;"><span class="biaozhu h"><span class="dian a" ></span>未入项</span> <span class="biaozhu"><span class="dian b" ></span>待入项</span> <span class="biaozhu l"><span class="dian c" ></span>已入项</span></p>
      <el-table v-loading="loading" :data="dataList" :row-class-name="tableRowClassName">
        <el-table-column label="序号" type="index" width="50"/>
        <el-table-column label="姓名"  prop="customer_name"/>
        <el-table-column label="公司名称"  prop="corp_name"/>
        <el-table-column label="项目名称"  prop="project_name"/>
        <el-table-column label="面试时间"  prop="interview_time"/>
        <el-table-column label="人事"  prop="nick_name"/>
        <el-table-column label="商务"  prop="bus_name"/>
        <el-table-column label="预计入项"  prop="stay_time"/>
        <el-table-column label="入项时间"  prop="syqstart_time"/>
        <el-table-column label="未入项原因"  prop="follow_detail"/>
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
   listdata
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
        listdata(this.queryParams).then(res=>{
          this.dataList = res.rows
          this.total = res.total
          this.loading = false
        })
      },
      tableRowClassName({row, rowIndex}) {
            if (row.follow_status === 8) {
              return 'warning-row';
            } else if (row.follow_status === 7) {
              return 'success-row';
            }
            return '';
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

<template>
  <div  style="height: 300px; ">
    <div class="div-a">
      <router-link v-hasPermi="['demand:follow:list']" style="color: #0081FF;" :to="{path:'/follow/particulars',query:{row:data.demandId,ident:8}}">{{data.projectName}}</router-link>
      <router-link v-hasPermi="['demand:binding:list']" style="color: #0081FF;font-size: 14px;" :to="{path:'/follow/particulars',query:{row:data.demandId,ident:2}}">{{data.projectName}}</router-link>
      <el-button type="primary" size="mini" style="position: absolute;right: 10px;top: 10px;" @click="handleUpdate(data)">添加</el-button></div>
    <div style="overflow: auto; height: 250px;">
      <div v-for="basic in data.basicInfo" class="ul">
        <div><el-avatar style="background-color: #0081FF;"> {{basic.customerName}} </el-avatar></div>
        <div class="li-i">{{basic.customerName}}</div>
         <div v-if="basic.followStatus==4||basic.followStatus==6||basic.followStatus==8" class="li-y">{{followstatusFormat(basic)}}</div>
        <div v-else :class="basic.followStatus==7?'li-u':'li-o'">{{followstatusFormat(basic)}}</div>
      </div>
    </div>

    <index :bangding="bangding" @getList="getList"></index>
  </div>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
 import {
    delFollow,
    addFollow,
  } from "@/api/demand/binding";
  import index from "../components/particulars/index"
const animationDuration = 3000

export default {
  props: {
    data: {
      type: Object,
    },
    followstatus:{
      type: Array,
    }
  },
  components:{
    index
  },
  data() {
    return {
      chart: null,
      bangding:{
        open2:false,
        loading2:false,
        templist2:[],
        forms: [],
        id:"",
      },
    }
  },
  mounted() {

  },
  methods: {
      // 简历状态字典翻译
      followstatusFormat(row, column) {
        return this.selectDictLabel(this.followstatus, row.followStatus-1);
      },
      // 绑定
      handleUpdate(row){
        this.bangding.templist2=[]
        let form = new FormData()
        form.append("demandId",row.demandId)
        addFollow(form).then(res=>{
          this.bangding.forms=res.data
          this.bangding.templist2=res.data
          this.bangding.id=row.demandId
          this.bangding.open2 = true;
        })
      },
      getList(){
        this.$emit("getList")
      },
  }
}
</script>
<style scoped>
  .div-a{
    font-size: 14px;
    line-height: 50px;
    border-bottom:1px solid #dcdfe6 ;
    position: relative;
  }
  .ul{
    width: 60px;
    text-align: center;
    margin: 15px 25px 0  15px;
    float: left;
  }
  .li-i{
    font-size: 14px;
    color: #0081FF;
  }
  .li-u{
    font-size: 12px;
    color: #67C23A;
    line-height: 20px;
  }
  .li-o{
    line-height: 20px;
    font-size: 12px;
    color: #909399;
  }
  .li-y{
    font-size: 12px;
    color: #F56C6C;
    line-height: 20px;
  }
</style>

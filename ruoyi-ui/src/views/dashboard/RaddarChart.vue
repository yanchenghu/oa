<template>
  <div  style="height: 300px; ">
    <div class="div-a"><span>{{data.projectName}}</span><el-button type="primary" size="mini" style="position: absolute;right: 10px;top: 5px;" @click="handleUpdate(data)">添加</el-button></div>
    <div style="overflow: auto; height: 250px;">
      <div v-for="basic in data.basicInfo" class="ul">
        <div><el-avatar style="background-color: #0081FF;"> {{basic.customerName}} </el-avatar></div>
        <div class="li-i">{{basic.customerName}}</div>
         <div v-if="basic.followStatus==4||basic.followStatus==6||basic.followStatus==8" class="li-y">{{followstatusFormat(basic)}}</div>
        <div v-else :class="basic.followStatus==7?'li-u':'li-o'">{{followstatusFormat(basic)}}</div>
      </div>
    </div>
    <el-dialog :title="title" :visible.sync="open" width="40%" append-to-body>
      <el-input v-model="searchmsg" placeholder="请输入" clearable size="small" @keyup.enter.native="search" style="width: 170px;"/>
      <el-button type="cyan" icon="el-icon-search" size="mini" @click="search">查询</el-button>
      <p></p>
      <el-alert
          title="最多可绑定3条简历"
          type="warning"
          :closable="false">
      </el-alert>
      <p></p>
      <el-checkbox-group v-model="corpnamelist" v-loading="loading2">
           <el-checkbox v-for="city in templist" :label="city.customer_code" :key="city.customer_code" style="width:155px;">{{city.customer_name}}-{{city.customer_tel}}</el-checkbox>
      </el-checkbox-group>
      <div v-show="templist.length==0 && form.length!==0" style="text-align: center;
      ">暂无该信息</div>
      <div v-show="templist.length==0 && form.length==0" style="text-align: center; ">暂无简历信息,先去抢占几份去吧</div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确定</el-button>
        <el-button @click="open=false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
 import {
    delFollow,
    addFollow,

  } from "@/api/demand/binding";
const animationDuration = 3000

export default {
  props: {
    data: {
      type: Object,
    },
  },
  data() {
    return {
      chart: null,
      followstatus:[],
      title:"",
      open:false,
      searchmsg:"",
      templist:[],
      corpnamelist:[],
      form:{},
      loading2:false
    }
  },
  mounted() {
    this.getDicts("mar_demandresumefollow_status").then(response => {
         this.followstatus = response.data;
    });
  },
  watch:{
      "corpnamelist.length":{
          handler(newValue, oldValue) {
            if (newValue>3) {
                this.msgError("最多能绑定3条简历")
                this.corpnamelist.pop()
                console.log(this.corpnamelist)
            }
          }
      }
  },
  methods: {
      // 简历状态字典翻译
      followstatusFormat(row, column) {
        return this.selectDictLabel(this.followstatus, row.followStatus-1);
      },
     handleUpdate(row){
       addFollow().then(res=>{
         this.corpnamelist=[]
         this.form=res.data
         this.templist=res.data
         this.form.id=row.demandId
         this.open = true;
         this.title = "绑定";
       })
     },
     // 搜索简历
     search(){
       let that = this
       this.loading2=true
       if(this.searchmsg==""){
       setTimeout(function(){
         that.templist=that.form
         that.loading2=false
       },1000)
       }else{
         clearInterval(this.timer)
         this.timer=setTimeout(function(){
           var data= that.form.filter(item=>{
             return item.customer_name == that.searchmsg || item.customer_tel==that.searchmsg
            })
           that.templist = data
           that.loading2=false
         },1000)
       }
     },
     // 确定按钮
     submitForm(){
       if(this.corpnamelist.length>3){
           this.msgError("最多绑定3条简历")
       }else{
         let zm={
           list:this.corpnamelist,
           demandId:this.form.id
         }
         zm = JSON.stringify(zm)
         let form= new FormData()
         form.append("zm",zm)
         delFollow(form).then(res=>{
           this.msgSuccess("绑定成功")
           this.open=false
         })
       }
       },
  }
}
</script>
<style scoped>
  .div-a{
    padding:0 16px 0;
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

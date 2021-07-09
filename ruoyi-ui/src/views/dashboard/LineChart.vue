<template>
  <el-row :gutter="32">
    <el-col :xs="24" :md="12" :lg="8" class="col">
      <div style="background: #fff;height: 100%;border-radius: 2px;">
        <div class="div-a aa" >
          <span>
            排行榜
          </span>
          <el-tabs v-model="biaoqian" >
              <el-tab-pane label="入项" name="first">
               </el-tab-pane>
              <el-tab-pane label="面试通过" name="second"></el-tab-pane>
           </el-tabs>
        </div>
        <el-table v-if="biaoqian=='first'" :data="dataList.RankingEntry" class="customer-no-border-table">
          <el-table-column
            label="排名"
            align="center"
            width="70"
            >
            <template slot-scope="scope">
              <span v-if="scope.$index==0" class="paim"><svg-icon icon-class="first" class-name="paim"/></span>
              <span v-else-if="scope.$index==1" class="paim"><svg-icon icon-class="second" class-name="paim"/></span>
              <span v-else-if="scope.$index==2" class="paim"><svg-icon icon-class="thirdly" class-name="paim"/></span>
              <span v-else>{{scope.$index+1}}</span>
            </template>
            </el-table-column>
            <el-table-column prop="nick_name" label="姓名" width="120">
                <template slot-scope="scope">
                  <img v-if="scope.$index==0" class="img-circle" src="../../assets/image/frist.png" alt="">
                  <img v-if="scope.$index==1" class="img-circle" src="../../assets/image/second.png" alt="">
                  <img v-if="scope.$index==2" class="img-circle" src="../../assets/image/thirdly.png" alt="">
                  <el-avatar class="avatar" size="medium" v-if="scope.row.avatar" :src="avart+scope.row.avatar"></el-avatar>
                  <el-avatar class="avatar" size="medium" v-else>
                    <img v-if="scope.row.sex==1" :src="index1" alt="">
                    <img v-else :src="index2" alt="">
                  </el-avatar>
                  <span class="table-span">{{scope.row.nick_name}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="peonum" label="入项人数"  align="right">
              <template slot-scope="scope">
                <span style="margin-right: 20px;">{{scope.row.peonum}}</span>
              </template>
            </el-table-column>
        </el-table>

        <el-table v-else :data="dataList.InterviewEntry" class="customer-no-border-table">
          <el-table-column
            label="排名"
            align="center"
            width="70"
            >
            <template slot-scope="scope">
              <span v-if="scope.$index==0" class="paim"><svg-icon icon-class="first" class-name="paim"/></span>
              <span v-else-if="scope.$index==1" class="paim"><svg-icon icon-class="second" class-name="paim"/></span>
              <span v-else-if="scope.$index==2" class="paim"><svg-icon icon-class="thirdly" class-name="paim"/></span>
              <span v-else>{{scope.$index+1}}</span>
            </template>
            </el-table-column>
            <el-table-column prop="nick_name" label="姓名" width="120">
                <template slot-scope="scope">
                  <img v-if="scope.$index==0" class="img-circle" src="../../assets/image/frist.png" alt="">
                  <img v-if="scope.$index==1" class="img-circle" src="../../assets/image/second.png" alt="">
                  <img v-if="scope.$index==2" class="img-circle" src="../../assets/image/thirdly.png" alt="">
                  <el-avatar class="avatar" size="medium" v-if="scope.row.avatar" :src="avart+scope.row.avatar"></el-avatar>
                  <el-avatar class="avatar" size="medium" v-else>
                    <img v-if="scope.row.sex==1" :src="index1" alt="">
                    <img v-else :src="index2" alt="">
                  </el-avatar>
                  <span class="table-span">{{scope.row.nick_name}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="peonum" label="面试通过"  align="right">
              <template slot-scope="scope">
                <span style="margin-right: 20px;">{{scope.row.peonum}}</span>
              </template>
            </el-table-column>
        </el-table>


      </div>
    </el-col>
    <el-col :xs="24" :md="12" :lg="16" class="col col-ri" v-if="dataList.numb==2">
      <div style="height: 100%;background: #fff; width: 101%;border-radius: 2px;">
        <div class="div-a "><span>
            我的需求
          </span></div>
        <ul class="cont-ul">
          <div style="height: 600px; overflow: auto; width: 100%; margin-left: -35px;">
            <el-col :xs="24" :sm="24" :lg="12" v-for="list,index in dataList.list" :key="index">
                <li style="margin-top: 5px;">
                  <el-avatar style="background-color: #0081FF;"> {{list.projectName.slice(2,4)}} </el-avatar>
                  <div class="ul-div">
                    <span :style="{color:list.importantLevel==0?'red':''}">{{list.projectName.slice(0,10)}}</span>
                    <p style="color: #999999;font-size: 12px;"> {{list.addTime}}</p>
                  </div>
                  <div style="position: absolute;right: 10px; height: 20px;">
                      <el-button style="width:130px ;" type="warning" @click="tiaozhuan2(list)">目标{{list.targetNumber?list.targetNumber:0}}/绑定{{list.ifLook?list.ifLook:0}}</el-button>
                  </div>
                </li>
            </el-col>
          </div>
        </ul>
      </div>
    </el-col>
    <el-col :xs="24" :md="12" :lg="16" class="col col-ri" v-if="dataList.numb==1">
      <div style="height: 100%;background: #fff; width: 100%;border-radius: 2px;">
        <div class="div-a aa" >
          <span>录入的需求</span>
        </div>
        <ul class="cont-ul" >
          <div style="height: 600px; overflow: auto; width: 100%; margin-left: -35px;" >
            <el-col :xs="24" :sm="24" :lg="12" v-for="list,index in dataList.marDWorLit" :key="index">
              <li style="margin-top: 5px;">
                <el-avatar style="background-color: #0081FF;"> {{list.projectName.slice(2,4)}} </el-avatar>
                <div class="ul-div">
                  <span :style="{color:list.importantLevel==0?'red':''}">{{list.address}}</span>
                  <p><span style="color: #999999;font-size: 12px;">{{list.projectName.slice(0,10)}}</span> {{list.addTime}}</p>
                </div>
                <div style=" position: absolute;right: 10px; height: 20px;">
                    <el-button style="width:130px ;" type="warning" @click="tiaozhuan(list)">目标{{list.targetNumber?list.targetNumber:0}}/绑定{{list.demandNumber?list.demandNumber:0}}</el-button>
                </div>
              </li>
            </el-col>
          </div>
        </ul>
      </div>
    </el-col>
    <el-col :xs="24" :md="12" :lg="16" class="col col-ri" v-if="dataList.numb==5">
      <div style="height: 100%; background: #fff; width: 100%;border-radius: 2px;">
        <div class="div-a aa" >
          <span>快捷入口</span>
        </div>
        <div style="display: flex;padding: 20px 20px;">
          <el-input
            size="small"
            placeholder="请输入姓名或电话"
            prefix-icon="el-icon-search"
            @keyup.enter.native="find"
            v-model="finddata.customerName">
          </el-input>
          <el-button size="small" type="primary" @click="find">搜人才</el-button>
        </div>
        <el-row :gutter="20" style="padding: 0 20px;">
          <el-col :xs="24" :lg="12" class="col-col">
            <el-card shadow="hover" class="col-item red" @click.native="tiao(0)">
              <div>
                <div>今日重点</div>
                <count-to  :start-val="0" :end-val="dataList.keyNeeds" :duration="2600" class="card-panel-num4"/>
              </div>
               <img class="card-panel-icon" src="../../assets/image/重点监管标签_fill.png" alt="">
            </el-card>
          </el-col>
          <el-col :xs="24" :lg="12" class="col-col">
             <el-card shadow="hover" class="col-item" @click.native="tiao(1)">
               <div>
                 <div>常规需求</div>
                 <count-to  :start-val="0" :end-val="dataList.nonKeyNeeds" :duration="2600" class="card-panel-num4"/>
               </div>
               <img class="card-panel-icon" src="../../assets/image/文档.png" alt="">
             </el-card>
          </el-col>
          <el-col :xs="24" :lg="12" class="col-col" >
             <el-card shadow="hover" class="col-item" @click.native="mianshi">
               <div >
                 <div>我的面试</div>
                 <div v-if="dataList.bindpassnum!==0">{{dataList.bindpassnum}}</div>
                 <div v-else>今天还没安排面试哦~</div>
               </div>
                <img class="card-panel-icon" src="../../assets/image/面试.png" alt="">
            </el-card>
          </el-col>
          <el-col :xs="24" :lg="12" class="col-col">
             <el-card @click.native="binding" shadow="hover" class="col-item">
               <div>
                 <div >我绑定的简历</div>
                  <count-to  :start-val="0" :end-val="dataList.bindnum" :duration="2600" class="card-panel-num4"/>
               </div>
               <img class="card-panel-icon" src="../../assets/image/offer.png" alt="">

             </el-card>
          </el-col>
        </el-row>
      </div>
    </el-col>
    <el-dialog :visible.sync="open"  title="简历查询" append-to-body>
      <div>
        <el-table :data="tableData" border style="width: 100%" v-loading="loading">
          </el-table-column>
          <el-table-column  label="姓名"width="70" >
            <template slot-scope="scope">
                <span v-if="checkPermi(['resume:record:allquery'])">{{scope.row.customer_name}}</span>
                <span v-else>{{scope.row.customer_name.substring(0,1)+ new Array(scope.row.customer_name.length).join('*')}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="customer_tel" label="电话">
            <template slot-scope="scope">
              <span v-if="checkPermi(['resume:record:allquery'])">{{scope.row.customer_tel}}</span>
              <span v-else>{{scope.row.customer_tel.replace(reg,"$1****$2")}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="customer_birth" label="出生日期">
          </el-table-column>
          <el-table-column prop="profession_id" label="技术方向" :formatter="professionIdSituationFormat" width="60">
          </el-table-column>
          <el-table-column label="录入简历时间"  prop="add_time" >
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.add_time, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="更新简历时间"  prop="add_time" >
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.update_time, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="add_name" label="占有人" width="70"  fixed="right">
          </el-table-column>
          <el-table-column label="操作"  width="90" fixed="right">
            <template slot-scope="scope">
              <el-button v-hasPermi="['resume:record:query']" @click="handsee(scope.row.customer_code)" type="text" size="small">查看</el-button>
              <el-button v-if="!scope.row.add_name" @click="handleClick(scope.row.customer_code)" type="text" size="small">抢占</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="finddata.pageNum"
        :limit.sync="finddata.pageSize"
        @pagination="select"
              />
    </el-dialog>
  </el-row>
</template>

<script>
import {
    listRecord,
    addRecord
  } from "@/api/resume/record/customerinfo";
  import CountTo from 'vue-count-to'
export default {
  name:"LineChart",
  props: {
    dataList:{
      type:Object,
    }
  },
  components: {
    CountTo
  },
  data() {
    return {
      finddata: {
        pageNum: 1,
        pageSize: 10,
        customerName: "",
        customerTel: "",
      },
      total:0,
      tableData:[],
      professionIdoptions:[],
      loading:false,
      open:false,
      reg: /^(\d{3})\d{4}(\d{4})$/,
      biaoqian:"first",
      biaoqian1:"time",
      name:"",
      name2:"",
      chart: null,
      avart:process.env.VUE_APP_BASE_API,
      avart1:[
        require('../../assets/avat/avta2.png'),
        require('../../assets/avat/头像 4.png'),
        require('../../assets/avat/头像 8.png'),
        require('../../assets/avat/头像 12.png'),
        require('../../assets/avat/头像 10.png'),
        require('../../assets/avat/头像 6.png'),
      ],
      avart2:[
        require('../../assets/avat/avat1.png'),
        require('../../assets/avat/头像 3.png'),
        require('../../assets/avat/头像 5.png'),
        require('../../assets/avat/头像 9.png'),
        require('../../assets/avat/头像 7.png'),
      ],
    }
  },
  created() {
      this.getDicts("per_customerinfo_professionid").then(response => {
        this.professionIdoptions = response.data;
      });
  },
  mounted() {
    this.name = this.index1
    this.name2 = this.index2
  },
  computed:{
    index1:{
       cache:false,
       get:function(){
           let index = Math.floor((Math.random()*this.avart1.length))
            return this.avart1[index]
          }
    },
    index2:{
      cache:false,
      get:function(){
        let index = Math.floor((Math.random()*this.avart2.length))
        return this.avart2[index]
      }
    },
  },
  methods: {
    professionIdSituationFormat(row,column){
      return this.selectDictLabel(this.professionIdoptions, row.profession_id);
    },
    select(){
        this.loading=true
        listRecord(this.finddata).then(res =>{
        this.total = res.total;
        this.loading=false
        this.tableData=res.rows
        });
    },
    // 查询
    find() {
      // this.select()ss
      // this.open = true;
      if(this.finddata.customerName==""){
        this.msgError("请输入姓名或电话")
      }else{
        this.open = true;
        this.select()
      }
    },
    handsee(value){
       let customerCode = value
       this.$router.push({path:"/record/particulars",query:{customerCode:customerCode}});
       this.open = false;
    },
    handleClick(val) {
      var formData = new FormData()
      formData.append("customerCode",val)
      addRecord(formData).then(res=>{
        if(res.code===200){
          this.msgSuccess("抢占成功"),
          this.select()
        }
      })
    },
    handleSetLineChartData(type){
      this.$emit("handleSetLineChartData",type)
    },
    tiaozhuan(list){
      this.$router.push({path:'/follow/particulars',query:{row:list.demandId,ident:8}})
    },
    tiaozhuan2(list){
      this.$router.push({path:'/follow/particulars',query:{row:list.demandId,ident:2}})
    },
    tiao(row){
      this.$router.push({name:'Binding',params:{row:row}})
    },
    mianshi(){
      this.$router.push({path:'/demand/forinter'})
    },
    binding(){
      console.log("12321")
      this.$router.push({path:'/binbing'})
    },
  }
}
</script>
<style scoped>
  >>>.el-input--medium .el-input__inner{
    height: 50px;
  }
  .paim{
    font-size: 24px;
  }
  .img-circle{
    position: absolute;
    top: 1px;
    left: 9px;
    width: 38px;
    height: 51px;
  }
  >>>.el-table--medium td{
    padding:15px 0 0 0;
  }
  .customer-no-border-table >>>th{
      border:none;
    }
  >>>.customer-no-border-table td,.customer-no-border-table th.is-leaf {
      border:none;
    }
  >>>.el-tabs__nav-wrap::after{
    display: none;
  }
  >>>.el-tabs__header{
    margin: 0;
  }
  >>>.el-tabs__active-bar{
    bottom: 8px;
  }
  >>>.customer-no-border-table::before{
        width: 0;
      }
  >>>.customer-no-border-table .is-right{
    padding-right: 10px;
  }
  .col{
    padding: 16px 16px 0;
    margin-bottom:32px;
    height: 700px;
  }
  .col-ri{
    float: right;
  }
  .div-a{
    padding:0 20px 0;
    line-height: 50px;
    border-bottom:1px solid #dcdfe6 ;
  }
  .div-a span{
    color: #606266;
    font-style: italic;
    font-size: 18px;
    font-weight: 800;
  }
  .border{
    border-right: 1px red solid;
  }
  .table-span{
    position: absolute;
    top: 18px;
    margin-left: 15px;
  }
  >>>.el-tabs__header{
    background-color: #fff;
    padding: 0;
  }
  .card-panel-icon{
    width: 54px;
    height: 54px;
    margin-top: 15px;
  }
  .aa{
    display: flex;
    justify-content: space-between;
  }
  .cont-ul{
    list-style: none;
    display:flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  .cont-ul li{
    display: flex;
    margin-top: 10px;
    position: relative;
  }
  .ul-div{
    width: 55%;
    margin-top: 5px;
    margin-left: 10px;
  }
  .ul-div span{
    font-size: 14px;
    font-weight: 400;
    color: #606266;
  }
  .ul-div p{
    margin-top: 4px;
    font-size: 12px;
    color: #c0c4cc;
  }
  .col-col{
    height: 150px;
    margin-top: 20px;
  }
  .col-item{
    background-color: #fff;
    height: 100%;
    padding-top: 15px;
    border-radius: 8px;
  }
  >>>.el-card__body{
    display: flex;
    justify-content: space-between;
  }
  .col-item div:nth-child(1){
    font-size: 18px;
  }
  .col-item div:nth-child(2){
    margin-top: 25px;
    font-size: 16px;
    font-weight: 500;
    letter-spacing: -1px;
  }
  .card-panel-num4{
    margin-top: 25px;
    display: inline-block;
    font-weight: 700;
    font-size: 30px;
  }
  .red{
    color: red;
  }
</style>

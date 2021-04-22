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
            跟踪人员
          </span></div>
        <ul class="cont-ul">
          <div style="height: 600px; overflow: auto; width: 100%; margin-left: -35px;">
            <el-col :xs="24" :sm="24" :lg="12" v-for="list,index in dataList.ListperRob" :key="index">
                <li>
                  <router-link :to="{path:'/record/particulars',query:{customerCode:list.resumeId}}"><el-avatar style="background-color: #0081FF;"  class="avatar" size="medium"> {{list.customerName}}</el-avatar></router-link>

                  <div class="ul-div">
                    <span>{{list.customerName}} </span>
                    <span> {{list.customerTel}}</span>
                    <p>{{list.addTime}} - {{list.editTime}}</p>
                  </div>
                  <div style=" position: absolute;right: 10px;">
                    <el-button type="warning" @click="handleSetLineChartData(list)" >跟踪</el-button>
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
          <el-tabs v-model="biaoqian1">
              <el-tab-pane label="发布时间" name="time">
               </el-tab-pane>
              <el-tab-pane label="绑定最多" name="moer"></el-tab-pane>
          </el-tabs>
        </div>
        <ul class="cont-ul" >
          <div style="height: 600px; overflow: auto; width: 100%; margin-left: -35px;" >
            <el-col :xs="24" :sm="24" :lg="12" v-for="list,index in biaoqian1=='time'?dataList.marDWorLit:dataList.marThreeLit" :key="index" >
              <li style="margin-top: 5px;">
                <el-avatar style="background-color: #0081FF;"> {{list.projectName.slice(2,4)}} </el-avatar>
                <div class="ul-div">
                  <span >{{list.address}}</span>
                  <p><span style="color: #999999;font-size: 12px;">{{list.projectName.slice(0,10)}}</span> {{list.addTime}}</p>
                </div>
                <div style=" position: absolute;right: 20px;">
                  <el-button type="warning" @click="tiaozhuan(list)">查看</el-button>
                </div>
              </li>
            </el-col>
          </div>
        </ul>
      </div>
    </el-col>
  </el-row>
</template>

<script>

export default {
  props: {
    dataList:{
      type:Object,
    }
  },
  data() {
    return {
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
    handleSetLineChartData(type){
      this.$emit("handleSetLineChartData",type)
    },
    tiaozhuan(list){
      this.$router.push({path:'/follow/particulars',query:{row:list.demandId,ident:8}})
    },
  }
}
</script>
<style scoped>
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
</style>

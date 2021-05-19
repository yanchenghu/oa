<template>
  <div class="dashboard-editor-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-select v-hasPermi="['statistc:homepage:boss']" filterable  v-model="userName"  placeholder="请选择员工" size="medium"  @change="getList">
        <el-option
           v-for="dict in userlist"
           :key="dict.userName"
           :label="dict.nickName"
           :value="dict.userName"
         />
      </el-select>
      <personnel-operating ref="child" ></personnel-operating>
    </el-row>
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
        <el-table v-if="biaoqian=='first'" :data="datalist.RankingEntry" class="customer-no-border-table">
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
                  <img v-if="scope.$index==0" class="img-circle" src="../../../assets/image/frist.png" alt="">
                  <img v-if="scope.$index==1" class="img-circle" src="../../../assets/image/second.png" alt="">
                  <img v-if="scope.$index==2" class="img-circle" src="../../../assets/image/thirdly.png" alt="">
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

        <el-table v-else :data="datalist.InterviewEntry" class="customer-no-border-table">
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
                  <img v-if="scope.$index==0" class="img-circle" src="../../../assets/image/frist.png" alt="">
                  <img v-if="scope.$index==1" class="img-circle" src="../../../assets/image/second.png" alt="">
                  <img v-if="scope.$index==2" class="img-circle" src="../../../assets/image/thirdly.png" alt="">
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
    <!-- <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <yxqushitu></yxqushitu>
    </el-row> -->
    <el-row>
      <el-col>

      </el-col>
    </el-row>
  </div>
</template>

<script>
import personnelOperating from '../../components/work/personnelOperating';
import yxqushitu from '../../components/work/yxqushitu'
import tubiao from '../../components/work/tubiao'
import {yuangonglist,getbusinessData} from "@/api/analysis/personnelanalysis.js"

export default {
  name: 'Index',
  components: {
    personnelOperating,
    yxqushitu,
    tubiao
  },
  data() {
    return {
      userlist:[],
      userName:null,
      biaoqian:"first",
      datalist:[],
      avart1:[
        require('../../../assets/avat/avta2.png'),
        require('../../../assets/avat/头像 4.png'),
        require('../../../assets/avat/头像 8.png'),
        require('../../../assets/avat/头像 12.png'),
        require('../../../assets/avat/头像 10.png'),
        require('../../../assets/avat/头像 6.png'),
      ],
      avart2:[
        require('../../../assets/avat/avat1.png'),
        require('../../../assets/avat/头像 3.png'),
        require('../../../assets/avat/头像 5.png'),
        require('../../../assets/avat/头像 9.png'),
        require('../../../assets/avat/头像 7.png'),
      ],
    }
  },
  created() {
    this.getuserlist()
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
    getuserlist(){
      getbusinessData().then(res=>{
          this.datalist = res.data
      })
      yuangonglist().then(res=>{
        this.userlist = res.data
        this.userName = res.data[0].userName
        this.getList()
      })
    },
    getList(){
      this.$refs.child.getData(this.userName)
    }
  }
}
</script>

<style  scoped>
.dashboard-editor-container {
  padding: 32px;
  
  position: relative;
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
</style>

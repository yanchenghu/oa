<template>
  <el-row>
    <el-col class="col col-ri">
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
                      <el-button style="width:130px ;" type="warning" @click="tiaozhuan2(list)">目标{{list.targetNumber?list.targetNumber:0}}/绑定{{list.demandNumber?list.demandNumber:0}}</el-button>
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
    }
  },
  created() {

  },
  methods: {
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

  .col{
    /* padding: 16px 16px 0; */
    padding-right: 16px;
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
    margin-top: 10px;
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

<template>
  <el-row>
    <el-col class="col col-ri">
      <div style="height: 100%;background: #fff; width: 101%;border-radius: 2px;">
        <div class="div-a "><span>
            跟踪人员
          </span></div>
        <ul class="cont-ul">
          <div style="height: 600px; overflow: auto; width: 100%; margin-left: -35px;">
            <el-col :xs="24" :sm="12" :lg="8" v-for="list,index in dataList.ListperRob" :key="index">
                <li>
                  <router-link :to="{path:'/record/particulars',query:{customerCode:list.resumeId}}"><el-avatar style="background-color: #0081FF;"> {{list.customerName}} </el-avatar></router-link>
                  <div class="ul-div">
                    <span>{{list.customerName}} </span>
                    <span> {{list.customerTel}}</span>
                    <p>{{list.addTime}} - {{list.editTime}}</p>
                  </div>
                  <div style=" position: absolute;right: 20px;">
                    <el-button type="warning" @click="handleSetLineChartData(list)" >跟踪</el-button>
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
    handleSetLineChartData(type){
      this.$emit("handleSetLineChartData",type)
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

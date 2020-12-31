<template>
  <el-row :gutter="32">
    <el-col :xs="24" :md="12" :lg="8" class="col">
      <div style="background: #fff;height: 100%;border-radius: 10px;">
        <div class="div-a">本月入项成绩排行榜</div>
        <el-table :data="dataList.RankingEntry">
          <el-table-column
            label="排名"
            type="index"
            align="center"
            />
            <el-table-column prop="nick_name" label="姓名" >
                <template slot-scope="scope">
                  <el-avatar size="medium" v-if="scope.row.avatar" :src="avart+scope.row.avatar"></el-avatar>
                  <el-avatar size="medium" v-else>
                    <img v-if="scope.row.sex==1" src="../../assets/avat/avta2.png" alt="">
                    <img v-else src="../../assets/avat/avat1.png" alt="">
                  </el-avatar>
                  <span class="table-span">{{scope.row.nick_name}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="peonum" label="入项人数" align="center"/>
        </el-table>
      </div>
    </el-col>
    <el-col :xs="24" :md="12" :lg="16" class="col col-ri">
      <div style="height: 100%;background: #fff; width: 100%;border-radius: 10px;">
        <div class="div-a aa">我跟踪的人员</div>
        <ul class="cont-ul">
          <div style="height: 600px; overflow: auto; width: 100%;">
            <el-col :xs="24" :sm="24" :lg="12" v-for="list,index in dataList.ListperRob" :key="index">
                <li>
                  <el-avatar style="background-color: #0081FF;"> {{list.customerName}} </el-avatar>
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
      chart: null,
      avart:process.env.VUE_APP_BASE_API,
    }
  },
  created() {

  },

  methods: {
    handleSetLineChartData(type){
      this.$emit("handleSetLineChartData",type)
    }

  }
}
</script>
<style scoped>
  .col{

    padding: 16px 16px 0;
    margin-bottom:32px;
    height: 750px;
  }
  .col-ri{
    float: right;
  }
  .div-a{

    padding:0 16px 0;
    line-height: 50px;
    border-bottom:1px solid #dcdfe6 ;
  }
  .aa{
    padding-left: 40px;
  }
  .table-span{
    position: absolute;
    top: 18px;
    margin-left: 15px;
  }
  .cont-ul{
    list-style: none;
    display:flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  .cont-ul li{
    display: flex;
    margin-right: 16px;
    margin-top: 10px;
    position: relative;
  }
  .ul-div{
    width: 55%;
    margin-top: 4px;
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

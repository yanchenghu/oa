<template>
  <div class="manage">
      <div class="manage-top">
        <el-row :gutter="20">
          <el-col :span="19">
            <div style="float: right;">
              <el-select v-model="queryParem.recentYears" placeholder="请选择" size="small" @change="getList">
                  <el-option
                    v-for="time,i in timedata"
                    :key="i"
                    :label="time.lable"
                    :value="time.value">
                  </el-option>
                </el-select>
            </div>
            <person-bottom style="margin-top: 25px;" :chart-data="chartData"></person-bottom>
          </el-col>
          <el-col :span="5">
            <div>
              <p><b>数据总况</b></p>
              <div>
                <div class="card-panel">
                  <div class="card-panel-icon-wrapper icon-mianshi">
                    <svg-icon icon-class="ruxiang" class-name="card-panel-icon" />
                  </div>
                  <div class="card-panel-description">
                    <div class="card-panel-text">
                      入项
                    </div>
                    <count-to :start-val="0" :end-val="zongData.entryPeopleTotle" :duration="3600" class="card-panel-num2" />
                    <div>
                      <span class="tongbi" v-if="zongData.entryPeopleTotle-zongData.lastyearentryPeopleTotle>0">
                        同比增长
                        <span class=" tongbi-up">{{((zongData.entryPeopleTotle-zongData.lastyearentryPeopleTotle)/zongData.lastyearentryPeopleTotle*100).toFixed(2)}}% <svg-icon icon-class="sanup" class-name="card-panel-ico" /></span>
                      </span>
                      <span class="tongbi" v-else>
                        同比下降
                        <span class="tongbi-down">{{Math.abs(((zongData.entryPeopleTotle-zongData.lastyearentryPeopleTotle)/zongData.lastyearentryPeopleTotle*100).toFixed(2))}}% <svg-icon icon-class="sandow" class-name="card-panel-ico" /></span>
                      </span>
                    </div>
                  </div>
                </div>
                <div class="card-panel">
                  <div class="card-panel-icon-wrapper icon-zhichu">
                    <svg-icon icon-class="out" class-name="card-panel-icon" />
                  </div>
                  <div class="card-panel-description">
                    <div class="card-panel-text">
                      出项
                    </div>
                    <count-to :start-val="0" :end-val="zongData.outPeopleTotle" :duration="3600" class="card-panel-num2" />
                    <div>
                      <span class="tongbi" v-if="zongData.outPeopleTotle-zongData.lastyearoutPeopleTotle>0">
                          同比增长
                          <span class=" tongbi-up">{{((zongData.outPeopleTotle-zongData.lastyearoutPeopleTotle)/zongData.lastyearoutPeopleTotle*100).toFixed(2)}}% <svg-icon icon-class="sanup" class-name="card-panel-ico" /></span>
                        </span>
                        <span class="tongbi" v-else>
                          同比下降
                          <span class="tongbi-down">{{Math.abs(((zongData.outPeopleTotle-zongData.lastyearoutPeopleTotle)/zongData.lastyearoutPeopleTotle*100).toFixed(2))}}% <svg-icon icon-class="sandow" class-name="card-panel-ico" /></span>
                        </span>
                      </div>
                    </div>
                </div>
                <div class="card-panel">
                  <div class="card-panel-icon-wrapper icon-renyuan">
                    <svg-icon icon-class="renyuan" class-name="card-panel-icon" />
                  </div>
                  <div class="card-panel-description">
                    <div class="card-panel-text">
                      人员增长(出-入)
                    </div>
                    <count-to :start-val="0" :end-val="zongData.gradationTotle" :duration="3600" class="card-panel-num2" />
                    <div>
                      <span class="tongbi" v-if="zongData.gradationTotle-zongData.lastyeargradationTotle>0">
                          同比增长
                          <span class="tongbi-up">{{Math.abs(((zongData.gradationTotle-zongData.lastyeargradationTotle)/zongData.lastyeargradationTotle*100).toFixed(2))}}% <svg-icon icon-class="sanup" class-name="card-panel-ico" /></span>
                        </span>
                        <span class="tongbi" v-else>
                          同比下降
                          <span class="tongbi-down">{{Math.abs(((zongData.gradationTotle-zongData.lastyeargradationTotle)/zongData.lastyeargradationTotle*100).toFixed(2))}}% <svg-icon icon-class="sandow" class-name="card-panel-ico" /></span>
                        </span>
                      </div>
                    </div>
                </div>
                <div class="card-panel">
                  <div class="card-panel-icon-wrapper icon-lirun">
                    <svg-icon icon-class="yingli" class-name="card-panel-icon" />
                  </div>
                  <div class="card-panel-description">
                    <div class="card-panel-text">
                      利润增减额
                    </div>
                    <count-to :start-val="0" :end-val="zongData.gradationProfitTotle" :duration="3600" class="card-panel-num2" />
                    <div>
                      <span class="tongbi" v-if="zongData.gradationProfitTotle - zongData.lastyeargradationProfitTotle>0">
                          同比增长
                         <span class="tongbi-up">{{Math.abs(((zongData.gradationProfitTotle-zongData.lastyeargradationProfitTotle)/zongData.lastyeargradationProfitTotle*100).toFixed(2))}}% <svg-icon icon-class="sanup" class-name="card-panel-ico" /></span>
                        </span>
                        <span class="tongbi" v-else>
                          同比下降
                          <span class="tongbi-down">{{Math.abs(((zongData.gradationProfitTotle-zongData.lastyeargradationProfitTotle)/zongData.lastyeargradationProfitTotle*100).toFixed(2))}}% <svg-icon icon-class="sandow" class-name="card-panel-ico" /></span>
                        </span>
                      </div>
                    </div>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
      <el-row :gutter="20">
            <el-col  :sm="24" :lg="12">
              <div class="dom-dow">
                <person-top :chart-data="chartData.entryPeople" :tad-data="entryPeople" :mone="expectedData" :title="'入项人员及利润'" :legend='["今年入项","去年入项","今年利润","去年利润"]' ></person-top>
              </div>
            </el-col>
              <el-col  :sm="24" :lg="12">
                <div class="dom-dow">
                  <person-top :chart-data="chartData.outPeople" :tad-data="outPeople" :mone="expectedData" :title="'出项人员及利润'" :legend='["今年出项","去年出项","今年利润","去年利润"]'></person-top>
                </div>
            </el-col>
            <el-col  :sm="24" :lg="12">
              <div class="dom-dow">
                <person-top :chart-data="chartData.gradation" :tad-data="gradation" :mone="expectedData" :title="'人员增减对比'" :legend='["今年人员","今年人员","去年人员"]'></person-top>
              </div>
            </el-col>
            <el-col  :sm="24" :lg="12">
              <div class="dom-dow">
                <person-top :chart-data="chartData.gradationProfit" :tad-data="gradationProfit" :mone="expectedData" :title="'利润增减对比'" :legend='["今年增减利润","去年增减利润","去年增减利润"]'></person-top>
              </div>
            </el-col>
          </el-row>
  </div>
</template>

<script>
  import {listmanagement} from "@/api/analysis/personnelmobility.js"
  import personBottom from "../../components/personnelmobility/personBottom"
  import personTop from "../../components/personnelmobility/personTop.vue"
  import CountTo from 'vue-count-to'
  export default {
    name:"management",
    data(){
      return{
        queryParem:{
          recentYears:"",
          salesQuota:1,
        },
        chartData:{
          customMonth:[],
          entryPeople:[],
          outPeople:[],
          gradation:[],
          gradationProfit:[],
        },
        entryPeople:{
          lastyearEntryPeople:[],
          entryPeopleProfit:[],
          lastyearEntryPeopleProfit:[]
        },
        outPeople:{
          lastyearEntryPeople:[],
          entryPeopleProfit:[],
          lastyearEntryPeopleProfit:[],
        },
        gradation:{
          entryPeopleProfit:[],
        },
        gradationProfit:{
          entryPeopleProfit:[],
        },
        zongData:{},
        expectedData:[],
        timedata:[],
      }
    },
    components:{
      personBottom,
      CountTo,
      personTop
    },
    created() {
      this.time()
      this.getList()
    },
    methods:{
      time(){
        let time = new Date()
        let y,m,d
        y = time.getFullYear()
        m = time.getMonth()+1
        d = time.getDate()
        this.timedata.push({lable:"最近一年",value:y+"-"+m+"-"+d})
        this.timedata.push({lable:y-1,value:y-1+"-12-30"})
        this.timedata.push({lable:y-2,value:y-2+"-12-30"})
        this.queryParem.recentYears = y+"-"+m+"-"+d
      },
      getList(){
        this.restert()
        listmanagement(this.queryParem).then(res=>{
          this.zongData = res.data.totle
          res.data.liMan.forEach(item=>{
            this.expectedData.push(item.customMonth.split("-")[1])
            this.chartData.customMonth.push(item.customMonth)
            this.chartData.entryPeople.push(item.entryPeople);
            this.chartData.outPeople.push(item.outPeople);
            this.chartData.gradation.push(item.gradation);
            this.chartData.gradationProfit.push(item.gradationProfit);
            //
            this.entryPeople.entryPeopleProfit.push(item.entryPeopleProfit)
            this.outPeople.entryPeopleProfit.push(item.outPeopleProfit)
          })
          res.data.lastyearliMan.forEach(item=>{
            this.entryPeople.lastyearEntryPeople.push(item.lastyearEntryPeople)
            this.entryPeople.lastyearEntryPeopleProfit.push(item.lastyearEntryPeopleProfit)
            this.outPeople.lastyearEntryPeople.push(item.lastyearOutPeople)
            this.outPeople.lastyearEntryPeopleProfit.push(item.lastyearOutPeopleProfit)
            this.gradation.entryPeopleProfit.push(item.lastyearGradation)
            this.gradationProfit.entryPeopleProfit.push(item.lastyearProfit)
          })
        })
      },
      restert(){
        this.chartData={
          customMonth:[],
          entryPeople:[],
          outPeople:[],
          gradation:[],
          gradationProfit:[],
        }
        this.entryPeople={
          lastyearEntryPeople:[],
          entryPeopleProfit:[],
          lastyearEntryPeopleProfit:[]
        }
        this.outPeople={
          lastyearEntryPeople:[],
          entryPeopleProfit:[],
          lastyearEntryPeopleProfit:[],
        }
        this.gradation={
          entryPeopleProfit:[],
        }
        this.gradationProfit={
          entryPeopleProfit:[],
        }
        this.zongData={}
        this.expectedData=[]
      }
    },
  }
</script>

<style scoped>
  .manage-top{
    background-color: #fff;
    padding: 20px;
    height: 470px;
  }
  .manage{
    margin: 15px 10px 0 10px;
  }
  .card-panel-icon-wrapper {
    float: left;
    margin: 35px 0  0 14px;
    padding:6px 6px;
    border-radius: 10px;
    color: #fff;
  }
  .card-panel {
    border-radius: 2px;
    height: 90px;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    /* background:rgb(240,249,255); */
    border-color: rgba(0, 0, 0, .05);
    }
    .tongbi{
      color: #c0c4cc;
      font-size: 14px;
    }
    .icon-mianshi {
      background-color:#0081FF ;
    }
    .icon-zhichu{
      background-color:#33B5E5 ;
    }
    .icon-lirun{
      background-color: #ff9f43 ;
    }
    .icon-renyuan{
      background-color: #28C76F ;
    }
    .card-panel-icon {
      font-size: 23px;
    }
    .card-panel-text {
      color: #606266;
      font-size: 16px;
    }
    .card-panel-num{
      margin-left: 20px;
      font-size: 16px;
      font-weight: 800;
      color: #c0c4cc;
    }
    .card-panel-num2 {
      font-size: 16px;
      color: #303133;
      font-weight: 700;
      line-height: 30px;
    }
    .tongbi-up{
      color: #28c76f;
    }
    .tongbi-down{
      color:#ea5455 ;
    }
    .card-panel-description {
      float: left;
      font-weight: bold;
      margin:20px 5px 20px 10px;
    }
    .dom-dow{
      background-color: #fff;
      margin-top: 20px;
      padding: 20px 0 20px 20px;
    }
</style>

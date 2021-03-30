<template>
   <div class="dashboard-editor-container">
      <div style="margin-bottom: 35px;">
        <el-radio-group style="margin-right: 20px;margin-bottom: 2px;" v-model="radio2" size="medium" @change="getList">
          <el-radio-button label="chengyuan" >成员对比</el-radio-button>
          <el-radio-button label="meiri" >每日详细数据</el-radio-button>
        </el-radio-group>
        <el-date-picker
          style="margin-right: 20px;margin-top: 10px;margin-bottom: 10px; "
          v-model="value1"
          type="daterange"
          size="medium"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
          range-separator="至"
          :clearable="false"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions3"
          @change="getList">
        </el-date-picker>
        &nbsp;
        <el-select v-if="this.radio2=='meiri'"  filterable  v-model="queryParams.userName"  placeholder="请选择员工" size="medium"  @change="getList">
       <el-option
            v-for="dict in userlist"
            :key="dict.userName"
            :label="dict.nickName"
            :value="dict.userName"
          />
        </el-select>
      </div>
      <el-row :gutter="15">
        <el-col :xs="24" :sm="12" >
            <div class="chart-wrapper">
              <statistical-work :title="list[0]" :chart-data="InputList"></statistical-work>
            </div>
        </el-col>
        <el-col :xs="24" :sm="12"  >
            <div class="chart-wrapper">
              <statistical-work :title="list[1]" :chart-data="trackList"></statistical-work>
            </div>
        </el-col>
        <el-col :xs="24" :sm="12" >
            <div class="chart-wrapper">
              <statistical-work :title="list[2]" :chart-data="bindingList"></statistical-work>
            </div>
        </el-col>
        <el-col :xs="24" :sm="12" >
            <div class="chart-wrapper">
              <statistical-work :title="list[3]" :chart-data="resumePassedList"></statistical-work>
            </div>
        </el-col>
        <el-col :xs="24" :sm="12" >
            <div class="chart-wrapper">
              <statistical-work :title="list[4]" :chart-data="interviewPassedList"></statistical-work>
            </div>
        </el-col>
        <el-col :xs="24" :sm="12" >
            <div class="chart-wrapper">
              <statistical-work :title="list[5]" :chart-data="entryPersonnelList"></statistical-work>
            </div>
        </el-col>
      </el-row>
      <div class="chart-wrapper">
        <termpro-fit :chartData="ruxianglist" ></termpro-fit>
      </div>
      <div class="chart-wrapper">
        <el-table v-if="radio2=='chengyuan'" border :data="customerleve" key="1">
          <el-table-column label="姓名"  prop="nickName" />
          <el-table-column label="录入"  prop="inputNum"/>
          <el-table-column label="跟踪"  prop="trackNum"/>
          <el-table-column label="绑定"  prop="bindingNum"/>
          <el-table-column label="简历通过"  prop="resumePassedNum"/>
          <el-table-column label="绑定成活率">
            <template slot-scope="scope">
                <span v-if="scope.row.bindingNum!==0">{{(scope.row.resumePassedNum/scope.row.bindingNum).toFixed(2)*100}}%</span>
                <span v-else>0</span>
            </template>
          </el-table-column>
          <el-table-column label="面试通过"  prop="interviewPassedNum"/>
          <el-table-column label="面试成活率">
            <template slot-scope="scope">
                <span v-if="scope.row.resumePassedNum!==0">{{(scope.row.interviewPassedNum/scope.row.resumePassedNum).toFixed(2)*100}}%</span>
                <span v-else>0</span>
            </template>
          </el-table-column>
          <el-table-column label="入项"  prop="entryPersonnelNum"/>
          <el-table-column label="利润"  prop="samemonthCollection">
            <template slot-scope="scope">
                  <span>{{(scope.row.serviceNum-scope.row.costNum).toFixed(2)}}</span>
              </template>
          </el-table-column>
        </el-table>

        <el-table v-else  :data="customerleve" key="2" border>
          <el-table-column label="日期"  prop="addTime">
            <template slot-scope="scope">
              <span>{{scope.row.addTime.split('T')[0]}}</span>
            </template>
          </el-table-column>
          <el-table-column label="录入"  prop="inputNum"/>
          <el-table-column label="跟踪"  prop="trackNum"/>
          <el-table-column label="绑定需求" prop="bindingNum" />
          <el-table-column label="简历通过"  prop="resumePassedNum" />
          <el-table-column label="面试通过"  prop="interviewPassedNum" />
          <el-table-column label="入项"  prop="entryPersonnelNum" />
        </el-table>

      </div>
   </div>
</template>

<script>
  import statisticalWork from '../../components/personnelanalysis/statisticalWork.vue'
  import termproFit from '../../components/personnelanalysis/termproFit.vue'
  import {listmingxi,listdata ,yuangonglist} from "@/api/analysis/personnelanalysis.js"
  export default {
    name: 'personnelanalysis',
    components: {
      statisticalWork,
      termproFit
    },
    data() {
      return {
        pickerMinDate: "",
        pickerOptions3: {
            onPick: ({ maxDate, minDate }) => {
              this.pickerMinDate = minDate.getTime()
              if (maxDate) {
                this.pickerMinDate = ''
              }
            },
            disabledDate: time => {
                      if (this.pickerMinDate !== "") {
                        const day30 = 365 * 24 * 3600 * 1000;
                        let maxTime = this.pickerMinDate + day30;
                        let minTime = this.pickerMinDate - day30;
                        if (maxTime > new Date()) {
                          maxTime = new Date();
                        }
                        return time.getTime() > maxTime||time.getTime() < minTime;
                      }
                      return time.getTime() > Date.now();
                    }
         },
        customerleve:[],
        userlist:[],
        radio2:"chengyuan",
        queryParams:{
          startTime:null,
          endTime:null,
          userName:null,
        },
        list:["录入","跟踪","绑定","简历通过","面试通过","入项"],
        value1:null,
        chartDatas:[],
        InputList:{
          expectedData:[],
          actualData:[],
        },
        bindingList:{
          expectedData:[],
          actualData:[],
        },
        entryPersonnelList:{
          expectedData:[],
          actualData:[],
        },
        interviewPassedList:{
          expectedData:[],
          actualData:[],
        },
        resumePassedList:{
          expectedData:[],
          actualData:[],
        },
        trackList:{
          expectedData:[],
          actualData:[],
        },
        ruxianglist:{
          expectedData:[],
          actualData:[],
        },

      }
    },
    created() {
      this.time()
      this.getuserlist()
      this.getList()
    },
    methods: {
      resers(){
        this.InputList={
          expectedData:[],
          actualData:[],
        },
        this.bindingList={
          expectedData:[],
          actualData:[],
        },
        this.entryPersonnelList={
          expectedData:[],
          actualData:[],
        },
        this.interviewPassedList={
          expectedData:[],
          actualData:[],
        },
        this.resumePassedList={
          expectedData:[],
          actualData:[],
        },
        this.trackList={
          expectedData:[],
          actualData:[],
        },
        this.ruxianglist={
          expectedData:[],
          actualData:[],
        }
      },
        time(){
          var nowDate = new Date().getTime();
          var sever = nowDate - 7*24*60*60*1000
          function getFullDate(targetDate){
            var D, y, m, d;
            if(targetDate){
              D = new Date(targetDate);
            }else{
              D = new Date();
            }
            y = D.getFullYear();
            m = D.getMonth() + 1;
            d = D.getDate();
            m = m > 9 ? m : '0' + m;
            d = d > 9 ? d : '0' + d;
            return y + '-' + m + '-' + d;
          }
          this.value1 = [getFullDate(sever),getFullDate()]
        },
        getList(){
          this.queryParams.startTime = this.value1[0]
          this.queryParams.endTime = this.value1[1]
          if(this.radio2=="chengyuan"){
            listmingxi(this.queryParams).then(res=>{
              this.resers()
              this.chartDatas = res.data
              this.customerleve = res.data.dataDetailsList
              this.chartDatas.InputList.forEach(item=>{
                this.InputList.expectedData.push(item.opertName)
                this.InputList.actualData.push(item.inputdataNum)
              })
              this.chartDatas.bindingList.forEach(item=>{
                this.bindingList.expectedData.push(item.nickName)
                this.bindingList.actualData.push(item.bindingNum)
              })
              this.chartDatas.entryPersonnelList.forEach(item=>{
                this.entryPersonnelList.expectedData.push(item.nickName)
                this.entryPersonnelList.actualData.push(item.entryPersonneNum)
              })
              this.chartDatas.interviewPassedList.forEach(item=>{
                this.interviewPassedList.expectedData.push(item.nickName)
                this.interviewPassedList.actualData.push(item.interviewPassedNum)
              })
              this.chartDatas.resumePassedList.forEach(item=>{
                this.resumePassedList.expectedData.push(item.nickName)
                this.resumePassedList.actualData.push(item.resumePassedNum)
              })
              this.chartDatas.trackList.forEach(item=>{
                this.trackList.expectedData.push(item.nickName)
                this.trackList.actualData.push(item.trackNum)
              })

              this.chartDatas.inputProfitList.forEach((item,index)=>{
                this.ruxianglist.expectedData.push(item.nickName)
                let obj = []
                obj.push(item.serviceNum-item.costNum)
                obj.push(index)
                obj.push(item.peopleNum)
                obj.push(item.serviceNum)
                this.ruxianglist.actualData.push(obj)
              })

            })
          }else{
            listdata(this.queryParams).then(res=>{
              this.resers()
              this.chartDatas = res.data
              this.customerleve = res.data.dataDetailsList
              this.chartDatas.InputList.forEach(item=>{
                this.InputList.expectedData.push(item.addTime)
                this.InputList.actualData.push(item.inputdataNum)
              })
              this.chartDatas.bindingList.forEach(item=>{
                this.bindingList.expectedData.push(item.addTime)
                this.bindingList.actualData.push(item.bindingNum)
              })
              this.chartDatas.entryPersonnelList.forEach(item=>{
                this.entryPersonnelList.expectedData.push(item.addTime)
                this.entryPersonnelList.actualData.push(item.entryPersonneNum)
              })
              this.chartDatas.interviewPassedList.forEach(item=>{
                this.interviewPassedList.expectedData.push(item.addTime)
                this.interviewPassedList.actualData.push(item.interviewPassedNum)
              })
              this.chartDatas.resumePassedList.forEach(item=>{
                this.resumePassedList.expectedData.push(item.addTime)
                this.resumePassedList.actualData.push(item.resumePassedNum)
              })
              this.chartDatas.trackList.forEach(item=>{
                this.trackList.expectedData.push(item.addTime)
                this.trackList.actualData.push(item.trackNum)
              })

              this.chartDatas.inputProfitList.forEach((item,index)=>{
                this.ruxianglist.expectedData.push(item.nickName)
                let obj = []
                obj.push(item.serviceNum-item.costNum)
                obj.push(index)
                obj.push(item.peopleNum)
                obj.push(item.serviceNum)
                this.ruxianglist.actualData.push(obj)
              })
            })
          }
        },
        getuserlist(){
          yuangonglist().then(res=>{
            this.userlist = res.data
            this.queryParams.userName = this.userlist[0].userName
          })
        },
    }
  }
</script>

<style scoped>
  .dashboard-editor-container {
    margin: 35px 15px 0 15px;
    position: relative;
  }
  .chart-wrapper{
    border-radius: 2px;
    background: #fff;
    padding: 16px 20px 0;
    margin-bottom: 32px;
  }
</style>

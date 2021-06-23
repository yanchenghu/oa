<template>
   <div class="dashboard-editor-container">
      <div style="margin-bottom: 35px;">
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
      </div>
      <el-row :gutter="15">
        <el-col :xs="24" :sm="12" >
            <div class="chart-wrapper">
              <statistical-work :title="list[0]" :chart-data="InputList"></statistical-work>
            </div>
        </el-col>
        <el-col :xs="24" :sm="12"  >
            <div class="chart-wrapper">
              <statistical-work :title="list[1]" :chart-data="bindingList"></statistical-work>
            </div>
        </el-col>
        <el-col :xs="24" :sm="12" >
            <div class="chart-wrapper">
              <statistical-work :title="list[2]" :chart-data="resumePassed"></statistical-work>
            </div>
        </el-col>
        <el-col :xs="24" :sm="12" >
            <div class="chart-wrapper">
              <statistical-work :title="list[3]" :chart-data="entryPersonnelList"></statistical-work>
            </div>
        </el-col>
        <el-col :xs="24" :sm="12" >
            <div class="chart-wrapper">
              <statistical-work :title="list[4]" :chart-data="interviewPassedList"></statistical-work>
            </div>
        </el-col>
        <el-col :xs="24" :sm="12" >
            <div class="chart-wrapper">
              <statistical-work :title="list[5]" :chart-data="outPersonnelNum"></statistical-work>
            </div>
        </el-col>
      </el-row>

     <div class="chart-wrapper">
        <el-table border :data="chartDatas" show-summary :summary-method="getSummaries">
          <el-table-column label="姓名"  prop="nickName" />
          <el-table-column label="录入的需求"  prop="inputNum"/>
          <el-table-column label="绑定的简历"  prop="bindingNum"/>
          <el-table-column label="简历通过"  prop="resumePassed"/>
          <el-table-column label="面试通过"  prop="interviewPassedNum"/>
          <el-table-column label="入项"  prop="entryPersonnelNum"/>
          <el-table-column label="出现"  prop="outPersonnelNum"/>
        </el-table>
      </div>
   </div>
</template>

<script>
  import statisticalWork from '../../components/personnelanalysis/statisticalWork.vue'
  import {listbusinessllist} from "@/api/analysis/personnelanalysis.js"
  export default {
    name: 'Businessdata',
    components: {
      statisticalWork,
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
        list:["录入的需求","绑定简历","简历通过", "面试通过","入项","出项"],
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
        resumePassed:{
          expectedData:[],
          actualData:[],
        },
        outPersonnelNum:{
          expectedData:[],
          actualData:[],
        }
      }
    },
    created() {
      this.time()
      this.getList()
    },
    methods: {
      getSummaries(param) {
        const { columns, data } = param
        const len = columns.length
        const sums = []
        let values = []
        columns.forEach((column, index) => {
        //如果是第一列，则最后一行展示为“总计”两个字
          if (index === 0) {
            sums[index] = '总计'
            //如果是最后一列，索引为列数-1，则显示计算总和
          }else {
            values = data.map(item => Number(item[column.property]))
          }
          if (!values.every(value => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr)
              if (!isNaN(value)) {
                return prev + curr
              } else {
                return prev
              }
            }, 0)
          }
        })
        return sums
      },
      resers(){
        this.InputList={
          expectedData:[],
          actualData:[],
        }
        this.bindingList={
          expectedData:[],
          actualData:[],
        }
        this.entryPersonnelList={
          expectedData:[],
          actualData:[],
        }
        this.interviewPassedList={
          expectedData:[],
          actualData:[],
        }
        this.resumePassed={
          expectedData:[],
          actualData:[],
        }
        this.outPersonnelNum={
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
          this.queryParams.startTime = this.value1[0]+" "+'00:00:00'
          this.queryParams.endTime = this.value1[1]+" "+'23:59:59'
          listbusinessllist(this.queryParams).then(res=>{
            this.resers()
            this.chartDatas = res.data
            this.customerleve = res.data.dataDetailsList
            this.chartDatas.forEach(item=>{
              this.InputList.expectedData.push(item.nickName)
              this.InputList.actualData.push(item.inputNum)
              this.bindingList.expectedData.push(item.nickName)
              this.bindingList.actualData.push(item.bindingNum)
              this.entryPersonnelList.expectedData.push(item.nickName)
              this.entryPersonnelList.actualData.push(item.interviewPassedNum)
              this.interviewPassedList.expectedData.push(item.nickName)
              this.interviewPassedList.actualData.push(item.entryPersonnelNum)
              this.resumePassed.expectedData.push(item.nickName)
              this.resumePassed.actualData.push(item.resumePassed)
              this.outPersonnelNum.expectedData.push(item.nickName)
              this.outPersonnelNum.actualData.push(item.outPersonnelNum)
            })
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

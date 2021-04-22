<template>
  <div v-hasPermi="['work:statistic:workresultlist']" :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'
import { getdata } from "@/api/index"
require('echarts/theme/macarons') // echarts theme
import resize from '../../dashboard/mixins/resize.js'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '200px'
    },
    autoResize: {
      type: Boolean,
      default: true
    },

  },
  data() {
    return {
      chart: null,
      newVisitis: [ ]
    }
  },
  watch: {
    newVisitis: {
      deep: true,
      handler(val) {
        this.setOptions(val)
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    getData(username){
      let form = new FormData()
      form.append("usercode",username)
      getdata(form).then(res=>{
          this.newVisitis = res.data
      })
    },
    initChart() {
      this.chart = echarts.init(this.$el,)
      this.setOptions(this.newVisitis)
    },
    setOptions(expectedData) {

      this.newVisitis.map(item=>{

      })
      function crtTimeF(val) {
              if (val != null) {
                var date = new Date(val);
                let a= date.getSeconds()<10?"0"+date.getSeconds():date.getSeconds()
                return date.getHours() + ':' + date.getMinutes() + ":" + a;
              }
            }
      function crtTimeFtt(val) {
      	if (val != null) {
      		var date = new Date(val);
      		return (date.getMonth() + 1) + '月' + date.getDate() + "日";
      	}
      }
      var jintian = "",
      	qiantian = "",
      	zuotian = "";
        var tian = 1000 * 60 * 60 * 24;
        var currDate = new Date(new Date().toLocaleDateString()).getTime();
        var currdate = new Date(currDate);
        var ddate2 = currdate.getDay();
       var day9 = new Date(new Date(new Date().toLocaleDateString()).getTime() + 8 * 60 * 60 * 1000);
       var day22 = new Date(new Date(new Date().toLocaleDateString()).getTime() + 19 * 60 * 60 * 1000);
      if (ddate2 == 1) { //星期1
      	qiantian = crtTimeFtt(new Date(currDate - tian * 4)); //星期4
      	zuotian = crtTimeFtt(new Date(currDate - tian * 3)); //星期5
      	jintian = crtTimeFtt(new Date(currDate));
      } else if (ddate2 == 2) { //星期2
      	qiantian = crtTimeFtt(new Date(currDate - tian * 4)); //星期5
      	zuotian = crtTimeFtt(new Date(currDate - tian)); //星期1
      	jintian = crtTimeFtt(new Date(currDate));
      } else {
      	qiantian = crtTimeFtt(new Date(currDate - tian * 2));
      	zuotian = crtTimeFtt(new Date(currDate - tian));
      	jintian = crtTimeFtt(new Date(currDate));
      }

      let list = expectedData
      var type1 = [],type2 = [],type3 = [],type4=[],type5=[];
      for(var i in list){
          var type = 0;
          var ddate = list[i].dateTime;
          var cha = ddate-currDate;
          if(ddate2==1){//星期1或星期2
              if(cha>0){//当天
                  type=1;
                  jintian = crtTimeFtt(ddate);
              }else if(cha<0-tian*3){//前天
                  ddate = ddate+tian*4;
                  type=3;
              }else{//昨天
                  ddate = ddate+tian*3;
                  type=2
              }
          }else if(ddate2==2){//星期2
              if(cha>0){//当天
                  type=1;
                  jintian = crtTimeFtt(ddate);
              }else if(cha<0-tian*3){//前天
                  ddate = ddate+tian*4;
                  type=3;
              }else{//昨天
                  ddate = ddate+tian;
                  type=2
              }
          }else{
      		if(cha>0){//当天
                  type=1;
                  jintian = crtTimeFtt(ddate);
              }else if(cha<0-tian){//前天
                  ddate = ddate+tian*2;
                  type=3;
              }else{//昨天
                  ddate = ddate+tian;
                  type=2
              }
      	}
          // ddate = ddate+tian/3;
          if(list[i].types==1){
              var obj =[];
              obj.push(ddate)
              obj.push(type)
              obj.push(list[i].remarks);
              type1.push(obj);
      	}else if(list[i].types==2){
              var obj =[];
              obj.push(ddate)
              obj.push(type)
              obj.push(list[i].remarks);
              type2.push(obj);
      	}else if(list[i].types==3){
              var obj =[];
              obj.push(ddate)
              obj.push(type)
              obj.push(list[i].remarks);
              type3.push(obj);
          }else if(list[i].types==4){
              var obj =[];
              obj.push(ddate)
              obj.push(type)
              obj.push(list[i].remarks);
              type4.push(obj);
          }else if(list[i].types==5){
              var obj =[];
              obj.push(ddate)
              obj.push(type)
              obj.push(list[i].remarks);
              type5.push(obj);
          }
      }
      var series = [];
      series.push({name: '录入简历',
          color: '#0081FF',
          data:type1,
          type:'scatter',
          symbolSize:10,
          symbol:"rect",
             tooltip: {
                formatter: function (params, ticket, callback) {
                         var res =crtTimeF(params.value[0])  + "<br/>" + params.value[2]
                         // setTimeout(function () {
                         //     // 仅为了模拟异步回调
                         //     callback(ticket, res);
                         // }, 200)
                         return res;
                     }
               }
          })
      series.push({name: '跟踪',
      type:'scatter',
      symbolSize:10,
      symbol:"circle",
          color:'rgb(0,0,0)',data:type2,
          tooltip: {
             formatter: function (params, ticket, callback) {
                      var res =crtTimeF(params.value[0])  + "<br/>" + params.value[2]
                      return res;
                  }
            }
          })
      series.push({name: '抢占简历',
      type:'scatter',
      symbolSize:10,
      symbol:"diamond",
          color: '#FF9F43',data:type3,
          tooltip: {
              formatter: function (params, ticket, callback) {
                       var res =crtTimeF(params.value[0])  + "<br/>" + params.value[2]
                       return res;
                   }
          }})
      series.push({name: '绑定简历',
      type:'scatter',
      symbolSize:10,
      symbol:"triangle",
          color: '#28C76F',data:type4,tooltip: {
              formatter: function (params, ticket, callback) {
                       var res =crtTimeF(params.value[0])  + "<br/>" + params.value[2]
                       return res;
                   }
          }})
      series.push({name: '入项',
      type:'scatter',
      symbolSize:10,
      z:3,
      symbol:"diamond",
          color: '#EA5455',data:type5,tooltip: {
             formatter: function (params, ticket, callback) {
                      var res =crtTimeF(params.value[0])  + "<br/>" + params.value[2]
                      return res;
                  }
          }})

      this.chart.setOption({
        title:{
          text:"人事3天操作图",
          padding:10,
          left: 'center'
        },
        xAxis: [{
          splitLine:{
             show:false
          },
          type: 'time',
          min:  day9,
          max:  day22,
          splitNumber: 11,
          axisLabel: {
              formatter: function(value) {
                  var date = new Date(value);
                  var h = date.getHours();
                  var m = date.getMinutes();
                  h = h < 10 ? '0' + h : h;
                  m = m === 0 ? m + '0' : m;
                  var time = h + ':' + m;
                  return time;
              }
          }
            }],
        grid: {
          left: 70,
          bottom: 20,
          top: 40,
          containLabel: true,
          height:"70%"
        },
        legend:{
          type:"plain",
          data:["录入简历","跟踪","抢占简历","绑定简历","入项"],
          bottom: 0,
        },
        tooltip:{
          trigger:"item"
        },
        yAxis: {
          axisLine:{
            show:false,
          },
          axisTick:{
            show:false,
          },
          axisLine:{
            show:false,
          },
          splitNumber: 3,
        	max: 3,
          min:0,
          // type:"time",
          axisLabel: {
              formatter: function(value) {
              	if (value == 1) {
              		return jintian;
              	} else if (value == 2) {
              		return zuotian;
              	} else if (value == 3) {
              		return qiantian;
              	} else if (value == 0) {
              		return 0;
              	}
              }
          },
        },
        series:series
      })
    }
  }
}
</script>

<template>
  <div v-hasPermi="['analysis:personneldata:personnellist']" :class="className" :style="{height:height,width:width}" />
</template>

<script>
  import * as echarts from 'echarts'
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
        default: '400px'
      },
      mone:{
        type: Array,
      },
      title:{
          type: String,
      },
      tadData:{
        type: Object,
        required: true
      },
      chartData: {
        type: Array,
      },
      legend:{
        type: Array,
      }
    },
    data() {
      return {
        chart: null
      }
    },
    watch: {
      tadData: {
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
      initChart() {
        this.chart = echarts.init(this.$el, )
        this.setOptions(this.tadData)
      },
      setOptions({lastyearEntryPeople,entryPeopleProfit,lastyearEntryPeopleProfit}={}) {

        var data1 = this.legend
        var series = [
                {
                    name: data1[0],
                    type: 'bar',
                    itemStyle:{
                      color:"#0081ff",
                      borderType:'dotted',
                      width:"10px",
                    },
                    barMaxWidth:10,
                    data: this.chartData.map(item=>{
                        return{
                            value:item,
                            itemStyle: {
                                  borderRadius: item > 0 ? [15, 15, 0, 0] : [0, 0, 15, 15], // 动态设置柱状图圆角
                              }
                        }
                    }) ,
                },
                {
                    name: data1[1],
                    type: 'bar',
                    itemStyle:{
                      color:"#99CCFF",
                      borderType:'dotted',
                      borderRadius: [15, 15, 0, 0,]
                    },
                    barMaxWidth:10,
                    data:  lastyearEntryPeople.map(item=>{
                        return{
                            value:item,
                            itemStyle: {
                                  borderRadius: item > 0 ? [15, 15, 0, 0] : [0, 0, 15, 15], // 动态设置柱状图圆角
                              }
                        }
                    }),
                },
                {
                    name: data1[2],
                    type: 'line',
                    smooth: true,
                    yAxisIndex: 1,
                    symbolSize: 10,
                    showSymbol:false,
                    itemStyle:{
                        color:'#FF9F43',
                    },
                    lineStyle:{
                        width:4,
                        color:"#FF9F43"
                    },
                    data:  entryPeopleProfit,
                },
                {
                    name: data1[3],
                    type: 'line',
                    smooth: true,
                    yAxisIndex:1,
                    symbolSize: 10,
                    showSymbol:false,
                    itemStyle:{
                        color:'#EA5455',
                    },
                    lineStyle:{
                        width:4,
                        color:"#EA5455"
                    },
                    data: lastyearEntryPeopleProfit,
                }
            ]
         if(data1.length>3){
            series = series
         }else{
             series = series.map((item,i)=>{
                 if(i==0||i==1){
                     return item
                 }
             })
         }
        this.chart.setOption({
                      title: {
                          text: this.title
                      },
                       grid: {
                          left: '5%',
                          right: '7%',
                          bottom: '10%',
                          containLabel: true
                        },
                        tooltip: {
                                trigger: 'axis',
                           },
                          legend: {
                              data: data1,
                              bottom:0,
                          },
                          xAxis: [
                              {
                                  type: 'category',
                                  data:  this.mone,
                                  axisPointer: {
                                      type: 'none'
                                  },
                                  axisTick: {
                                       show:false,
                                    },

                              }
                          ],
                          yAxis: [
                              {
                                  type: 'value',
                              },
                              {
                                  type: 'value',
                                  splitLine: {
                                     show: false,
                                  },
                              }
                          ],
                          dataZoom: [
                                  {
                                      type: 'inside'
                                  }
                              ],
                          series: series
        })
      },
    }
  }
</script>

<style>
</style>

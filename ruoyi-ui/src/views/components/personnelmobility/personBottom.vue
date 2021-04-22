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
      title:{
          type: String,
      },
      chartData: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        chart: null
      }
    },
    watch: {
      chartData: {
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
        this.setOptions(this.chartData)
      },
      setOptions({customMonth, entryPeople,outPeople,gradation,gradationProfit} = {}) {
        this.chart.setOption({
                  grid: {
                    left: '7%',
                    right: '7%',
                    bottom: '15%',
                    // containLabel: true
                  },
                  title:{
                      text:"人员增减及利润"
                  },
                  tooltip: {
                          trigger: 'axis',

                      },
                    legend: {
                        data: ['入项', '出项','人员增减','利润增减额'],
                        bottom:0,
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data:  customMonth,
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
                            name: '人数',
                        },
                        {
                            type: 'value',
                            name: '利润',
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
                    series: [
                        {
                            name: '入项',
                            type: 'bar',
                            itemStyle:{
                              color:"#0081ff",
                              borderType:'dotted',
                              borderRadius: [15, 15, 0, 0,]
                            },
                            barMaxWidth:16,
                            data:  entryPeople,
                        },
                        {
                            name: '出项',
                            type: 'bar',
                            itemStyle:{
                              color:"#99CCFF",
                              borderType:'dotted',
                              borderRadius: [15, 15, 0, 0,]
                            },
                            barMaxWidth:16,
                            data:  outPeople,
                        },
                        {
                            name: '人员增减',
                            type: 'bar',
                            barMaxWidth:16,
                            itemStyle:{
                              color:"#28C76F",
                              borderType:'dotted',
                            },
                            data: gradation.map(item=>{
                                return{
                                    value:item,
                                    itemStyle: {
                                          borderRadius: item > 0 ? [15, 15, 0, 0] : [0, 0, 15, 15], // 动态设置柱状图圆角
                                      }
                                }
                            }) ,
                        },
                        {
                            name: '利润增减额',
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
                            data: gradationProfit,
                        }
                    ]
        })
      },
    }
  }
</script>

<style>
</style>

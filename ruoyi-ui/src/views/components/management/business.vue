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
      setOptions({ expectedData, actualData,shouruData,lirunData } = {}) {

        this.chart.setOption({
          grid: {
                left: '7%',
                right: '7%',
                bottom: '15%',
                // containLabel: true
              },
              title:{
                  text:"经营情况"
              },
              tooltip: {
                      trigger: 'axis',
                      axisPointer: {
                          type: 'none',
                          crossStyle: {
                              color: '#999'
                          }
                      }
                  },
                  toolbox: {
                      // feature: {
                      //     dataView: {show: true, readOnly: false},
                      //     magicType: {show: true, type: ['line', 'bar']},
                      //     restore: {show: true},
                      //     saveAsImage: {show: true}
                      // }
                  },
                  legend: {
                      data: ['收入', '支出', '利润'],
                      bottom:0,
                  },
                  xAxis: [
                      {
                          type: 'category',
                          data: expectedData,
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
                          name: '收入/支出',

                          // interval: 500000,

                          // axisLabel: {
                          //     formatter: '{value}'
                          // }
                      },
                      {
                          type: 'value',
                          name: '利润',
                          splitLine: {
                             show: false,
                          },
                          // interval:1000000,
                          // min:-4000000,

                          // axisLabel: {
                          //     formatter: '{value} W'
                          // }
                      }
                  ],
                  series: [
                      {
                          name: '收入',
                          type: 'bar',
                          itemStyle:{
                            color:"#0081ff",
                            borderType:'dotted',
                            borderRadius: [100, 100, 0, 0]
                          },
                          data: actualData,
                      },
                      {
                          name: '支出',
                          type: 'bar',
                          itemStyle:{
                            color:"#99CCFF",
                            borderType:'dotted',
                            borderRadius: [100, 100, 0, 0]
                          },
                          data: shouruData,
                      },
                      {
                          name: '利润',
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
                          data: lirunData,
                      }
                  ]
        })
      },
    }
  }
</script>

<style>
</style>

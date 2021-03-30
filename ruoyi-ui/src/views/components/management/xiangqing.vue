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
        type: Array,
      },
      chartData: {
        type: Array,
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
      setOptions(data) {
        this.chart.setOption({
              title: {
                  text: this.title
              },
              tooltip: {
                  trigger: 'axis'
              },
              legend: {
                  data: [ '今年', '去年'],
                  top:"bottom",
              },
              grid: {
                  left: '3%',
                  right: '9%',
                  bottom: '8%',
                  containLabel: true
              },
              dataZoom: [
                      {
                          type: 'inside'
                      }
                  ],
              xAxis: {
                  type: 'category',
                  boundaryGap: false,
                  axisPointer: {
                    show: true,
                    type: "line",
                    lineStyle: {
                    type: "solid",
                    width: 4,
                    color: "rgba(220, 223, 230, 1)"
                        }
                  },
                  axisTick: {
                       show:false,
                    },
                  data: this.mone,
              },
              yAxis: {
                  type: 'value',

              },
              series: [
                  {
                      name: '今年',
                      type: 'line',
                      areaStyle:{
                          color:"rgb(229,242,254)",
                           origin:"start",
                       },
                      smooth: true,
                      showSymbol:false,
                      lineStyle:{
                            width:4,
                            color:"#0081ff"
                      },
                      itemStyle:{
                        normal:{
                            color:'#0081ff',//拐点颜色
                        },
                        emphasis: {
                              color: '#fff',//hover拐点颜色定义
                              borderWidth:6//拐点边框大小
                          }
                      },
                      data: data
                  },
                  {
                      name: '去年',
                      type: 'line',
                      smooth: true,
                      showSymbol:false,
                      lineStyle:{
                            width:4,
                            color:"#FF9F43"
                      },
                      itemStyle:{
                        normal:{
                            color:'#FF9F43',//拐点颜色
                        },
                        emphasis: {
                              color: '#fff',//hover拐点颜色定义
                              borderWidth:6//拐点边框大小
                          }
                      },
                      data: this.tadData
                  }
              ]
        })
      },
    }
  }
</script>

<style>
</style>

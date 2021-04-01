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
      setOptions({ expectedData, income,expatriateSalary,insideSalary,otherExpenses,profit } = {}) {
        this.chart.setOption({
         tooltip: {
                 trigger: 'axis',
                 axisPointer: {
                     type: 'none'
                 }
             },
             legend: {
                 data: ['收入', '外派工资', '内部工资', '其他支出', '盈利', ],
                 top:"bottom"
             },
             grid: {
                 left: '3%',
                 right: '4%',
                 bottom: '7%',
                 top:"5%",
                 containLabel: true
             },
             xAxis: [
                 {
                     type: 'category',
                     axisTick: {
                          show:false,
                       },
                     data: expectedData
                 }
             ],
             yAxis: [
                 {
                     type: 'value'
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
             series: [
                 {
                     name: '收入',
                     type: 'bar',
                     color:"#0081FF",
                     emphasis: {
                         focus: 'series'
                     },
                     barMaxWidth:16,
                     itemStyle:{
                           borderRadius: [15, 15, 0, 0,]
                         },
                     data: income
                 },
                 {
                     name: '内部工资',
                     type: 'bar',
                     stack: '广告',
                     color:"#28C76F",
                     emphasis: {
                         focus: 'series'
                     },
                     barMaxWidth:16,
                     data: insideSalary
                 },
                 {
                     name: '其他支出',
                     type: 'bar',
                     stack: '广告',
                     color:"#33b5e5",
                     emphasis: {
                         focus: 'series'
                     },
                     barMaxWidth:16,
                     data: otherExpenses
                 },
                 {
                     name: '外派工资',
                     type: 'bar',
                     stack: '广告',
                     color:"#99ccFF",
                     itemStyle:{
                           borderRadius: [15, 15, 0, 0,]
                         },
                     emphasis: {
                         focus: 'series'
                     },
                     barMaxWidth:16,
                     data:  expatriateSalary
                 },
                 {
                     name: '盈利',
                     type: 'line',
                     yAxisIndex:1,
                     color:'#FF9F43',
                     showSymbol:false,
                     symbolSize: 10,
                     smooth: true,
                     lineStyle:{
                         width:4,
                         color:"#FF9F43"
                     },
                     data: profit
                 },

             ]
        })
      },
    }
  }
</script>

<style>
</style>

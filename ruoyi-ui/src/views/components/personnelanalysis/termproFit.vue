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
      setOptions({ expectedData, actualData } = {}) {
        var zoomSize = 6;

        this.chart.setOption({
              tooltip: {
                      trigger: 'axis',
                      axisPointer: {
                          type: 'none'
                      },
                      formatter: function (params, ticket, callback) {
                        var res = `<div><div>${params[0].name}</div> <span class='params ruxiang'></span><span>共入项: ${params[0].value[2]}人</span><br/><span class='params chengben'></span><span>总成本: ${params[0].value[3]}</span><br/><span class='params lirun'></span><span>总利润: ${params[0].value[0]}</span><br/></div>`
                         return res;
                           },
                  },
              grid: {
                  left: '3%',
                  right: '4%',
                  bottom: '3%',
                  containLabel: true
              },
              title:{
                  text:"入项利润"
              },
              xAxis: [
                  {
                      type: 'value'
                  }
              ],
              dataZoom: [
                      {
                          type: 'inside',
                          orient :"vertical",
                      }
                  ],
              yAxis: [
                  {
                  data:expectedData,
                  type: 'category',
                  axisLine: {show: false},
                  axisTick: {show: false},
                  }
              ],
              series: [
                  {
                      name: '总利润',
                      type: 'bar',
                      barWidth: '20px',
                      itemStyle:{
                        color:"#0081ff",
                        borderType:'dotted',
                        borderRadius: [0, 100, 100, 0]
                      },
                      data: actualData,
                      showBackground: true,
                      backgroundStyle: {
                          color: '#ebeef5'
                      },
                  }
              ]
        })
      },
    }
  }
</script>

<style scoped>
 >>>.params{
    display: inline-block;
    width: 15px;
    height: 15px;
    margin-right: 10px;
    margin-top: 5px;
    border-radius: 50%;
  }
  >>>.ruxiang{
    background-color: #EA5455;
  }
  >>>.chengben{
    background-color: #DCDFE6;
  }
  >>>.lirun{
    background-color: #0081FF;
  }
</style>

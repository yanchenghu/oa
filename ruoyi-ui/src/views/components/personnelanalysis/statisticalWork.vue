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
        default: '300px'
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
          grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
              },
              title:{
                  text:this.title
              },
              xAxis: [
                  {
                      type: 'category',
                      data: expectedData,
                      silent:true,
                      axisTick: {
                          show:false,
                      },
                      axisLabel:{
                        rotate:50
                      },
                  }
              ],
              yAxis: [
                  {
                      type: 'value'
                  }
              ],
              dataZoom: [
                      {
                          type: 'inside'
                      }
                  ],
              series: [
                  {
                      name: '录入',
                      type: 'bar',
                      barWidth: '20px',
                      label:{
                          show:true,
                          position: 'top'
                      },
                      itemStyle:{
                          color:"#0081ff",
                          borderType:'dotted',
                          borderRadius: [100, 100, 0, 0]
                      },
                      data: actualData,
                  }
              ]
        })
      },
    }
  }
</script>

<style>
</style>

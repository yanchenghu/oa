<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :xs="12" :sm="12" :lg="{span: '4-8'}" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
        <div class="card-panel-description">
          <div class="card-panel-text">
            录入抢占
          </div>
          <count-to :start-val="0" :end-val="dataList.entryrobnnum" :duration="2600" class="card-panel-num" />
          <div>
            <el-button type="text">查看详情</el-button>
          </div>

        </div>
        <div class="card-panel-icon-wrapper icon-people">
          <svg-icon icon-class="peoples" class-name="card-panel-icon" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="{span: '4-8'}" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('messages')">
        <div class="card-panel-description">
          <div class="card-panel-text">
            绑定
          </div>
          <count-to :start-val="0" :end-val="dataList.bingdinnum" :duration="3000" class="card-panel-num" />
        </div>
        <div class="card-panel-icon-wrapper icon-message">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="{span: '4-8'}" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('purchases')">

        <div class="card-panel-description">
          <div class="card-panel-text">
            简历通过
          </div>
          <count-to :start-val="0" :end-val="dataList.resumeadopt" :duration="3200" class="card-panel-num" />
        </div>
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="{span: '4-8'}" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('shoppings')">
        <div class="card-panel-description">
          <div class="card-panel-text">
            面试通过
          </div>
          <count-to :start-val="0" :end-val="dataList.interviewadopt" :duration="3600" class="card-panel-num" />
        </div>
        <div class="card-panel-icon-wrapper icon-shopping">
          <svg-icon icon-class="shopping" class-name="card-panel-icon" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="{span: '4-8'}" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('shoppings')">
        <div class="card-panel-description">
          <div class="card-panel-text">
            入项
          </div>
          <count-to :start-val="0" :end-val="dataList.entryPeople" :duration="3600" class="card-panel-num" />
        </div>
        <div class="card-panel-icon-wrapper icon-shopping">
          <svg-icon icon-class="shopping" class-name="card-panel-icon" />
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import CountTo from 'vue-count-to'

export default {
  props:{
    dataList:{
      type:Object,
      required: true
    }
  },
  components: {
    CountTo
  },
  methods: {
    handleSetLineChartData(type) {
      // this.$emit('handleSetLineChartData', type)
    }
  }
}
</script>

<style lang="scss" scoped>

.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }


  .card-panel {
    height: 118px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon-people {
        background: #36a3f7;
      }

      .icon-message {
        background: #40c9c6;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }
    }

    .icon-people {
      color: #36a3f7;
    }
    .icon-message {
      color: #40c9c6;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: right;
      margin: 16px 16px 0 0;
      padding:17px 12px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: right;
      font-size: 48px;
    }

    .card-panel-description {
      float: left;
      font-weight: bold;
      margin:26px 15px 26px 15px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}
@media only screen and (min-width: 1200px){
  .el-col-lg-4-8 {
  		width: 20%;
  	}
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>

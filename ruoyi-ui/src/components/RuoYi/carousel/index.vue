<template>
  <div>
      <el-carousel :interval="4000" arrow="nerver">
            <el-carousel-item v-for="item,i in swiperList" :key="i">
               <h3 class="medium">恭喜 {{item.nick_name}} 成功入项 {{item.corp_name}}  {{item.project_name}} 项目</h3>
             </el-carousel-item>
      </el-carousel>
  </div>
</template>

<script>
  import { getlunbo} from "@/api/login"
export default {
  name: 'RuoYiDoc',
  data() {
    return {
      animate: false,
      swiperList:[ ]
    }
  },
  created () {
      this.getlun()
      // 页面显示
      // setInterval(this.showSwiper, 3000);
    },
  methods: {
    getlun(){
      getlunbo().then(res=>{
          this.swiperList = res.data
      })
    },
  }
}
</script>
<style lang="scss" scoped>

  .el-carousel{
    width: 300px;
    float: left;
    margin-left: 50%;
    position:absolute;
    left:-100px;
    .el-carousel__item{
      text-align: center;
    }
  }

  .el-carousel__item h3 {
      color: red;
      font-size: 15px;
      opacity: 0.75;
      line-height: 80px;
      margin: 0;
    }
</style>

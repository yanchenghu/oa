<template>
  <div class="dashboard-editor-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-select v-hasPermi="['statistc:homepage:boss']" filterable  v-model="userName"  placeholder="请选择员工" size="medium"  @change="getList">
        <el-option
           v-for="dict in userlist"
           :key="dict.userName"
           :label="dict.nickName"
           :value="dict.userName"
         />
      </el-select>
      <personnel-operating ref="child" ></personnel-operating>
    </el-row>
    <!-- <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <yxqushitu></yxqushitu>
    </el-row> -->
    <el-row>
      <el-col>

      </el-col>
    </el-row>
  </div>
</template>

<script>
import personnelOperating from '../../components/work/personnelOperating';
import yxqushitu from '../../components/work/yxqushitu'
import tubiao from '../../components/work/tubiao'
import {yuangonglist} from "@/api/analysis/personnelanalysis.js"
export default {
  name: 'Index',
  components: {
    personnelOperating,
    yxqushitu,
    tubiao
  },
  data() {
    return {
      userlist:[],
      userName:null,
    }
  },
  created() {
    this.getuserlist()
  },
  methods: {
    getuserlist(){
      yuangonglist().then(res=>{
        this.userlist = res.data
        this.userName = res.data[0].userName
        this.getList()
      })
    },
    getList(){
      this.$refs.child.getData(this.userName)
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  // background-color: rgb(240, 242, 245);
  position: relative;
}


</style>

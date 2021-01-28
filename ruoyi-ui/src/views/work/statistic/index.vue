<template>
  <div class="dashboard-editor-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <personnel-operating></personnel-operating>
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
export default {
  name: 'Index',
  components: {
    personnelOperating,
    yxqushitu,
    tubiao
  },
  data() {
    return {

    }
  },
  created() {

  },
  methods: {

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

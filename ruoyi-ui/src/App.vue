<template>
  <div id="app">
      <router-view />
  </div>
</template>

<script>
export default  {
  name:  'App'
}
</script>
<style>
 .app-container{
    /* min-height: 850px; */
    border-radius: 2px;
    background-color: #fff;
    margin: 15px 10px 0 10px;
  }
  .el-icon-circle-check{
    display: none;
  }
  .el-icon-close{
    display: inline-block !important;
  }
  .el-table th.gutter{
      display: table-cell!important;
    }
  .icons{
    font-size: 14px !important;
  }
  @media (min-width: 1750px) {
    .asd{
      float: right;
      margin-right: 0 !important;
    }
  }
</style>

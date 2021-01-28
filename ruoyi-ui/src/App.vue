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
    margin: 10px 10px 0 10px;
  }
  .el-icon-circle-check{
    display: none;
  }
  .el-icon-close{
    font-size: 18px;
    display: inline !important;
  }
  .el-table th.gutter{
      display: table-cell!important;
    }
  .icons{
    font-size: 14px !important;
  }  
</style>

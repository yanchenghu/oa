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
</style>

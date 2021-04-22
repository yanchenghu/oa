<template>
  <div>
    <management :biaoshi="1"></management>
    <personnelmobility :biaoshi="1"></personnelmobility>
    <incomeexpenditure :biaoshi="1"></incomeexpenditure>
  </div>
</template>

<script>
  import management from "../../analysis/management/index.vue"
  import personnelmobility from "../../analysis/personnelmobility/index.vue"
  import incomeexpenditure from "../../analysis/incomeexpenditure/index.vue"
  export default {
    name:"bossHome",
    data(){
      return{
      }
    },
    components:{
      management,
      personnelmobility,
      incomeexpenditure
    },
  }
</script>

<style>
</style>

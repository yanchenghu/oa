<template>
  <div class="app-container">
      <div>{{form.projectName}}</div>
  </div>
</template>

<script>
  export default {
    name:'particulars',
    data(){
      return{
        form:{},
      }
    },
    created() {
      this.form=this.$route.query.row
    },
  }
</script>

<style>
</style>

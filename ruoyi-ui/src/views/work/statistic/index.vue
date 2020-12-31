<template>
  <div class="dashboard-editor-container">
     <personnel-operating></personnel-operating>
  </div>
</template>

<script>
import personnelOperating from '../../components/work/personnelOperating'

export default {
  name: 'Index',
  components: {
    personnelOperating,
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

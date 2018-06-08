<!--发现-->
<template>
  <section>
    <mt-navbar fixed v-model="selectedBar" style="padding-bottom: 3px;">
      <mt-tab-item id="1">选项一</mt-tab-item>
      <mt-tab-item id="2">选项二</mt-tab-item>
      <mt-tab-item id="3">选项三</mt-tab-item>
    </mt-navbar>
    <mt-tab-container swipeable v-model="selectedBar">
      <mt-tab-container-item id="1">
        <mt-cell v-for="n in 20" :title="'内容 ' + n"/>
      </mt-tab-container-item>
      <mt-tab-container-item id="2">
        <mt-cell v-for="n in 4" :title="'测试 ' + n"/>
      </mt-tab-container-item>
      <mt-tab-container-item id="3">
        <mt-cell v-for="n in 6" :title="'选项 ' + n"/>
      </mt-tab-container-item>
    </mt-tab-container>
  </section>
</template>

<script>
  export default {
    data() {
      return {
        selectedBar: '1'
      };
    }
  }
</script>

<style scoped>
</style>

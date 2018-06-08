<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'app'
  }
</script>

<style>
  body {
    margin: 0px;
    padding: 0px;
    font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
    font-size: 14px;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  #app {
  / / font-family: 'Avenir', Helvetica, Arial, sans-serif;
  / / -webkit-font-smoothing: antialiased;
  / / -moz-osx-font-smoothing: grayscale;
    text-align: center;
  / / color: #2c3e50;
  / / margin-top: 60 px;
  }

  .horizontal-divider {
    width: 100%;
    height: 1px;
    background: #C0C0C0;
  }

  .vertical-divider {
    display: inline;
    float: left;
    width: 1px;
    height: 100%;
    background: #C0C0C0;
  }
</style>

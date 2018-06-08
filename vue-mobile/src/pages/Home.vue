<!--首页-->
<template>
  <section class="home-bg">
    <router-view class="container"></router-view>
    <mt-tabbar fixed v-model="selectedTab">
      <mt-tab-item id="main">
        <img slot="icon" :src="require(selectedTab=='main'?'../assets/main-active.png':'../assets/main-inactive.png')">
        <span :class="selectedTab=='main'?'activeTabBar':'inactiveTabBar'">首页</span>
      </mt-tab-item>
      <mt-tab-item id="discovery">
        <img slot="icon"
             :src="require(selectedTab=='discovery'?'../assets/discovery-active.png':'../assets/discovery-inactive.png')">
        <span :class="selectedTab=='discovery'?'activeTabBar':'inactiveTabBar'">发现</span>
      </mt-tab-item>
      <mt-tab-item id="mine">
        <img slot="icon" :src="require(selectedTab=='mine'?'../assets/mine-active.png':'../assets/mine-inactive.png')">
        <span :class="selectedTab=='mine'?'activeTabBar':'inactiveTabBar'">我的</span>
      </mt-tab-item>
    </mt-tabbar>
  </section>
</template>

<script>
  export default {
    data() {
      return {
        selectedTab: 'main'
      };
    },
    watch: {
      selectedTab(val) {
        this.$router.push({name: val});
      }
    },
    mounted() {
      // 通过url跳进来，需要确定选中哪个tab
      this.selectedTab = this.$route.name;
    }
  }
</script>

<style lang="scss" scoped>
  @import '../styles/vars';

  .home-bg {
    position: absolute;
    width: 100%;
    height: 100%;
    //background-image: url('../assets/main-bg.jpg');
    background-size: contain;
  }

  .container {
    padding-bottom: 55px;
  }

  .activeTabBar {
    color: $color-primary;
  }

  .inactiveTabBar {
    color: $color-normal;
  }

</style>

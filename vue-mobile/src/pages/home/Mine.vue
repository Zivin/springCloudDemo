<!--我的-->
<template>
  <section>
    <mt-header fixed title="username" class="mine-header-background" :style="{opacity:this.headerAlpha}">
    </mt-header>
    <mt-header fixed class="mine-header">
      <router-link to="/main" slot="left">
        <mt-button icon="back"></mt-button>
      </router-link>
      <mt-button icon="more" slot="right"></mt-button>
    </mt-header>
    <div class="mine-top">
      <img class="mine-user-img" src="../../assets/1.jpg"/>
      <div class="mine-user-name">dff如dfgfggfgg果f</div>
    </div>
    <mt-cell v-for="n in 14" :title="'测试 ' + n"/>
  </section>
</template>

<script>
  export default {
    data() {
      return {
        headerAlpha: 0
      };
    },
    methods: {
      handleScroll() {
        let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
        if (scrollTop > 160) {
          scrollTop = 160;
        }
        // 获取标题栏透明度
        this.headerAlpha = scrollTop / 160;
      }
    },
    mounted() {
      window.addEventListener('scroll', this.handleScroll);
    },
    destroyed() {
      window.removeEventListener('scroll', this.handleScroll);
    }
  }
</script>

<style lang="scss" scoped>
  @import '../../styles/vars';

  .mine-header-background {
    background: $color-primary;
  }

  .mine-header {
    background: transparent;
  }

  .mine-top {
    display: flex;
    align-items: center;
    background-image: url('../../assets/mine-bg.jpg');
    background-size: cover;
    height: 200px;
    .mine-user-img {
      flex: none;
      margin-left: 30px;
      width: 100px;
      height: 100px;
      border-radius: 50px;
    }
    .mine-user-name {
      margin-left: 30px;
      margin-right: 30px;
      flex: auto;
      height: 80px;
      font-size: 20px;
      font-family: 楷体;
      color: $color-main;
    }
  }

</style>

<!--主页-->
<template>
  <section>
    <mt-header fixed class="main-header-background" :style="{opacity:this.headerAlpha}">
    </mt-header>
    <mt-header fixed title="亦九" class="main-header">
      <router-link to="/main" slot="left">
        <mt-button icon="back"></mt-button>
      </router-link>
      <mt-button icon="more" slot="right"></mt-button>
    </mt-header>
    <mt-swipe style=" height: 200px;">
      <mt-swipe-item><img class="main-swipe-item" src="../../assets/1.jpg"></mt-swipe-item>
      <mt-swipe-item><img class="main-swipe-item" src="../../assets/2.jpg"></mt-swipe-item>
      <mt-swipe-item><img class="main-swipe-item" src="../../assets/3.jpg"></mt-swipe-item>
    </mt-swipe>
    <div>
      <horizontal-divider style="margin-top: 10px;"></horizontal-divider>
      <div style="height: 150px;display: flex;">
        <div class="main-shopping">
          <semitransparent-text-box :text="'购物'"></semitransparent-text-box>
        </div>
        <vertical-divider></vertical-divider>
        <div style="flex: 3 3 auto;display: flex;flex-direction: column;">
          <div class="main-coupon">
            <semitransparent-text-box :text="'优惠券'"></semitransparent-text-box>
          </div>
          <horizontal-divider></horizontal-divider>
          <div class="main-express">
            <semitransparent-text-box :text="'查快递'"></semitransparent-text-box>
          </div>
        </div>
      </div>
      <horizontal-divider></horizontal-divider>
    </div>
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

  .main-header-background {
    background: $color-primary;
  }

  .main-header {
    background: transparent;
  }

  .main-swipe-item {
    height: auto;
    width: 100%;
  }

  .main-shopping {
    flex: 2 2 auto;
    background-image: url('../../assets/main-shopping.png');
    background-size: cover;
  }

  .main-coupon {
    flex: auto;
    background-image: url('../../assets/main-coupon.png');
    background-size: cover;
  }

  .main-express {
    flex: auto;
    background-image: url('../../assets/main-express.png');
    background-size: cover;
  }
</style>

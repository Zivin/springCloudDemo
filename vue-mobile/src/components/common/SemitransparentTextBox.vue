<!--半透明文本框-->
<template>
  <section style="position:relative;">
    <div class="text-box-background" :style="this.randomStyle.bg">
    </div>
    <div type="text" class="text-box-text" :style="this.randomStyle.text">{{this.text}}</div>
  </section>
</template>

<script>
  export default {
    data() {
      return {}
    },
    props: [
      'text'
    ],
    computed: {
      boxWidth: function () {
        return (40 * this.text.length) + 'px';
      },
      styleList: function () {
        return [
          {
            bg: {background: '#473C8B', width: this.boxWidth},
            text: {color: '#FFF', width: this.boxWidth}
          },
          {
            bg: {background: '#8B1C62', width: this.boxWidth},
            text: {color: '#FFF', width: this.boxWidth}
          },
          {
            bg: {background: '#CD00CD', width: this.boxWidth},
            text: {color: '#FFF', width: this.boxWidth}
          },
          {
            bg: {background: '#00008B', width: this.boxWidth},
            text: {color: '#FFF', width: this.boxWidth}
          },
          {
            bg: {background: '#0000CD', width: this.boxWidth},
            text: {color: '#FFF', width: this.boxWidth}
          },
          {
            bg: {background: '#8B0000', width: this.boxWidth},
            text: {color: '#FFF', width: this.boxWidth}
          },
          {
            bg: {background: '#90EE90', width: this.boxWidth},
            text: {color: '#FFF', width: this.boxWidth}
          }
        ]
      },
      randomStyle: function () {
        return this.styleList[parseInt(Math.random() * this.styleList.length)];
      }
    }
  }
</script>

<style scoped>
  .text-box-background {
    position: absolute;
    height: 40px;
    border-top-left-radius: 20px;
    border-bottom-right-radius: 20px;
    opacity: 0.5;
  }

  .text-box-text {
    position: absolute;
    height: 40px;
    line-height: 40px;
    text-align: center;
    font-size: 20px;
    font-family: 黑体;
  }
</style>

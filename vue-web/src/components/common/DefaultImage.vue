<!--带默认图片的图片框-->
<template>
  <img :src="this.image">
</template>

<script>
  export default {
    data() {
      return {}
    },
    props: [
      'src'
    ],
    computed: {
      image: function () {
        return this.src == '' ? require('assets/user.png') : this.src;
      }
    }
  }
</script>

<style scoped>
</style>

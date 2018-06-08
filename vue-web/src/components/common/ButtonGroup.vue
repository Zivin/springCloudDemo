<!--按钮组控件-->
<template>
  <div>
    <el-button-group style="margin-bottom: 15px;">
      <el-button v-for="(button, index) in buttonProps.buttonList" :key="button" type="primary" plain
                 v-if="$util.hasPermission(buttonProps.menuCode, button)" @click="handleClick(button)">
        {{$util.hasPermission(buttonProps.menuCode, button)}}
      </el-button>
    </el-button-group>
  </div>
</template>

<script>
  export default {
    data() {
      return {}
    },
    props: [
      'buttonProps'
    ],
    methods: {
      handleClick(button) {
        this.$emit(button);
      }
    }
  }
</script>

<style scoped>
</style>

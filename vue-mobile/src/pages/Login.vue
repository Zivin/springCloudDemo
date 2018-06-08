<!--登录-->
<template>

</template>

<script>
  import {LOGIN, GET_VERIFICATION_CODE} from 'api/modules/login'
  import {setToken} from 'api/auth'
  import md5 from 'js-md5'
  import cookies from 'js-cookie'

  const cookieName = 'accountInfo';

  export default {
    data() {
      return {};
    },
    methods: {},
    mounted() {
    }
  }
</script>

<style lang="scss" scoped>

</style>

<!--管理员模板-->
<template>
  <el-card :body-style="{ padding: '0px' }" class="card">
    <img :src="this.item.avatar" class="image" @click="get()">
    <div style="padding: 14px;">
      <el-checkbox v-model="item.checked">
        <!--超级管理员显示图标-->
        <i class="el-icon-star-on" v-if="this.item.adminType === 0"></i>
        <span>{{ this.item.account }}</span>
      </el-checkbox>
      <div class="primary">{{ this.item.userName }}</div>
    </div>
  </el-card>
</template>

<script>
  import {ADMIN_GET} from 'api/modules/account'

  export default {
    data() {
      return {};
    },
    props: [
      'item'
    ],
    computed: {},
    methods: {
      get() {
        if (this.$util.hasPermission('Admin', 'get')) {
          this.$emit('react', 'get', this.item);
        }
      }
    }
  }
</script>

<style scoped>
  .card {
    width: 200px;
    height: 280px;
  }

  .image {
    width: 200px;
    height: 200px;
    display: block;
  }

  .primary {
    margin-top: 13px;
    font-size: 13px;
    color: #999;
  }
</style>

<!--带分页的项目控件-->
<template>
  <el-col>
    <el-row style="display: inline;" v-loading="loading">
      <item-page-template class="item-temp" v-for="(item, index) in this.list" :item="item"
                          :key="index" @react="react"></item-page-template>
    </el-row>
    <el-row>
      <el-pagination layout="sizes, prev, pager, next, jumper, ->, total" :page-sizes="[10, 20, 50, 100]"
                     :page-size="pageSize" :current-page="pageNum" :total="total"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange">
      </el-pagination>
    </el-row>
  </el-col>
</template>

<script>
  import Vue from 'vue'

  export default {
    data() {
      return {
        list: [],
        pageSize: 20,
        pageNum: 1,
        total: 0,
        loading: false
      };
    },
    props: [
      'pageProps'
    ],
    computed: {},
    methods: {
      query() {
        this.loading = true;
        this.list = [];
        this.total = 0;
        this.$http.post(this.pageProps.url, {
          param: this.pageProps.param,
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }, response => {
          if (response.code == 0) {
            this.list = response.data.list;
            this.total = response.data.total;
          }
          this.loading = false;
        }, error => {
          this.loading = false;
        });
      },
      handleSizeChange(pageSize) {
        this.pageSize = pageSize;
        this.pageNum = 1;
        this.query();
      },
      handleCurrentChange(pageNum) {
        this.pageNum = pageNum;
        this.query();
      },
      react(action, item) {
        this.$emit(action, item);
      },
      // 给父组件调用，获取选中的id
      getSelectedIds() {
        let selectedIds = [];
        this.list.forEach((item, index) => {
          if (item.checked) {
            selectedIds.push(item.id);
          }
        });
        return selectedIds;
      }
    },
    mounted() {
      Vue.component("ItemPageTemplate", this.pageProps.itemTemplate);
      this.query();
    },
    components: {}
  }
</script>

<style scoped>
  .item-temp {
    margin: 10px;
    float: left;
  }
</style>

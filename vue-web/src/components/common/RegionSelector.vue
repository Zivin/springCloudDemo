<!--区域选择控件-->
<template>
  <section>
    <el-input v-model="region" placeholder="选择区域" suffix-icon="el-icon-more" readonly @focus="show"></el-input>
    <el-dialog title="区域选择" :visible.sync="dialogFormVisible" custom-class="region-select">
      <el-input v-model="param.regionName" placeholder="区域名称">
        <el-button slot="append" type="primary" plain @click="query" icon="el-icon-search" style="width: 100px;">搜索
        </el-button>
      </el-input>
      <pagination-page ref="regionSelectorPage" :pageProps="this.pageProps"></pagination-page>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit">确定</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
  import {REGION_QUERY} from 'api/modules/basic'

  export default {
    data() {
      return {
        region: '',
        param: {regionName: ''},
        list: [],
        pageSize: 20,
        pageNum: 1,
        total: 0,
        loading: false,
        dialogFormVisible: false
      }
    },
    computed: {
      pageProps: function () {
        return {
          selectName: 'regionName',
          url: REGION_QUERY,
          param: this.param,
          columnList: [
            {prop: 'regionName', label: '区域名称'},
            {prop: 'regionCode', label: '区域编码', width: 150},
            {
              prop: 'regionLevel', label: '区域等级', width: 100, type: 'tag',
              tagMap: {
                0: {label: '国'},
                1: {label: '省'},
                2: {label: '市'},
                3: {label: '县/区'},
                4: {label: '乡/镇'},
                5: {label: '村'}
              }
            },
            {
              prop: 'status', label: '区域状态', width: 100, type: 'tag',
              tagMap: {
                0: {label: '启用', tag: 'success'},
                1: {label: '禁用', tag: 'danger'}
              }
            }
          ]
        }
      }
    },
    methods: {
      show() {
        this.dialogFormVisible = true;
      },
      query() {
        this.$refs.regionSelectorPage.query();
      },
      onSubmit() {
        this.dialogFormVisible = false;
        this.region = this.$refs.regionSelectorPage.getSelectedName();
        this.$emit('input', this.$refs.regionSelectorPage.getSelectedId());
      },
    }
  }
</script>

<style>
  .region-select {
    width: 800px;
  / / height: 700 px;
  }
</style>

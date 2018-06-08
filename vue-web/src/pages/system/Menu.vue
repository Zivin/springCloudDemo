<!--菜单管理-->
<template>
  <section>
    <button-group :buttonProps="this.buttonProps" @query="this.query" @reset="this.reset"></button-group>
    <el-form ref="menuParam" :inline="true" :model="param" label-width="100px">
      <el-form-item prop="menuName" label="菜单名称">
        <el-input v-model="param.menuName" placeholder="菜单名称" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="menuCode" label="菜单编码">
        <el-input v-model="param.menuCode" placeholder="菜单编码" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="status" label="菜单状态">
        <el-select v-model="param.status" placeholder="菜单状态" class="search-param-style">
          <el-option label="全部" value=""></el-option>
          <el-option label="启用" value="0"></el-option>
          <el-option label="禁用" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="remark" label="备注">
        <el-input v-model="param.remark" placeholder="备注" class="search-param-style"></el-input>
      </el-form-item>
    </el-form>
    <pagination-page ref="menuPage" :pageProps="this.pageProps" @get="this.get"></pagination-page>
    <menu-edit ref="menuEdit"></menu-edit>
  </section>
</template>

<script>
  import {MENU_QUERY} from 'api/modules/system'

  export default {
    data() {
      return {
        menuCode: 'Menu',
        param: {
          menuName: '',
          menuCode: '',
          status: '',
          remark: ''
        }
      };
    },
    computed: {
      pageProps: function () {
        return {
          menuCode: this.menuCode,
          url: MENU_QUERY,
          param: this.param,
          columnList: [
            {prop: 'menuName', label: '菜单名称', width: 150},
            {prop: 'menuCode', label: '菜单编码', width: 150},
            {prop: 'icon', label: '菜单图标', width: 100, type: 'icon'},
            {
              prop: 'status', label: '菜单状态', width: 100, type: 'tag',
              tagMap: {
                0: {label: '启用', tag: 'success'},
                1: {label: '禁用', tag: 'danger'}
              }
            },
            {prop: 'sort', label: '菜单排序', width: 100, align: 'center'},
            {prop: 'remark', label: '备注', minWidth: 200}
          ],
          operationList: ['get']
        };
      },
      buttonProps: function () {
        return {
          menuCode: this.menuCode,
          buttonList: ['query', 'reset']
        }
      }
    },
    methods: {
      query() {
        this.$refs.menuPage.query();
      },
      reset() {
        this.$refs.menuParam.resetFields();
      },
      get(row, index) {
        this.$refs.menuEdit.show(row.id);
      }
    },
    mounted() {
    },
    components: {
      MenuEdit: resolve => require(['components/system/menu/MenuEdit'], resolve)
    }
  }
</script>

<style scoped>
</style>

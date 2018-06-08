<!--模块管理-->
<template>
  <section>
    <button-group :buttonProps="this.buttonProps" @query="this.query" @reset="this.reset"></button-group>
    <el-form ref="moduleParam" :inline="true" :model="param" label-width="100px">
      <el-form-item prop="moduleName" label="模块名称">
        <el-input v-model="param.moduleName" placeholder="模块名称" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="moduleCode" label="模块编码">
        <el-input v-model="param.moduleCode" placeholder="模块编码" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="status" label="模块状态">
        <el-select v-model="param.status" placeholder="模块状态" class="search-param-style">
          <el-option label="全部" value=""></el-option>
          <el-option label="启用" value="0"></el-option>
          <el-option label="禁用" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="remark" label="备注">
        <el-input v-model="param.remark" placeholder="备注" class="search-param-style"></el-input>
      </el-form-item>
    </el-form>
    <pagination-page ref="modulePage" :pageProps="this.pageProps" @get="this.get"></pagination-page>
    <module-edit ref="moduleEdit"></module-edit>
  </section>
</template>

<script>
  import {MODULE_QUERY} from 'api/modules/system'

  export default {
    data() {
      return {
        menuCode: 'Permission',
        param: {
          moduleName: '',
          moduleCode: '',
          status: '',
          remark: ''
        }
      };
    },
    computed: {
      pageProps: function () {
        return {
          menuCode: this.menuCode,
          url: MODULE_QUERY,
          param: this.param,
          columnList: [
            {prop: 'moduleName', label: '模块名称', width: 150},
            {prop: 'moduleCode', label: '模块编码', width: 150},
            {prop: 'icon', label: '模块图标', width: 100, type: 'icon'},
            {
              prop: 'status', label: '模块状态', width: 100, type: 'tag',
              tagMap: {
                0: {label: '启用', tag: 'success'},
                1: {label: '禁用', tag: 'danger'}
              }
            },
            {prop: 'sort', label: '菜单排序', width: 100, align: 'center'},
            {
              prop: 'system', label: '所属系统', width: 100, type: 'tag',
              tagMap: {
                0: {label: '集成系统', tag: 'primary'},
                1: {label: '用户中心', tag: 'success'},
                2: {label: '消息中心', tag: 'warning'}
              }
            },
            {prop: 'remark', label: '备注', minWidth: 200}
          ],
          operationList: ['get']
        }
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
        this.$refs.modulePage.query();
      },
      reset() {
        this.$refs.moduleParam.resetFields();
      },
      get(row, index) {
        this.$refs.moduleEdit.show(row.id);
      }
    },
    mounted() {
    },
    components: {
      ModuleEdit: resolve => require(['components/system/module/ModuleEdit'], resolve)
    }
  }
</script>

<style scoped>
</style>

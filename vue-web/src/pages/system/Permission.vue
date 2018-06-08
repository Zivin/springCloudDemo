<!--权限管理-->
<template>
  <section>
    <button-group :buttonProps="this.buttonProps" @query="this.query" @reset="this.reset"></button-group>
    <el-form ref="permissionParam" :inline="true" :model="param" label-width="100px">
      <el-form-item prop="permissionName" label="权限名称">
        <el-input v-model="param.permissionName" placeholder="权限名称" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="permissionCode" label="权限编码">
        <el-input v-model="param.permissionCode" placeholder="权限编码" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="status" label="权限状态">
        <el-select v-model="param.status" placeholder="权限状态" class="search-param-style">
          <el-option label="全部" value=""></el-option>
          <el-option label="启用" value="0"></el-option>
          <el-option label="禁用" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="remark" label="备注">
        <el-input v-model="param.remark" placeholder="备注" class="search-param-style"></el-input>
      </el-form-item>
    </el-form>
    <pagination-page ref="permissionPage" :pageProps="this.pageProps" @get="this.get"></pagination-page>
    <permission-edit ref="permissionEdit"></permission-edit>
  </section>
</template>

<script>
  import {PERMISSION_QUERY} from 'api/modules/system'

  export default {
    data() {
      return {
        menuCode: 'Permission',
        param: {
          permissionName: '',
          permissionCode: '',
          status: '',
          remark: ''
        }
      };
    },
    computed: {
      pageProps: function () {
        return {
          menuCode: this.menuCode,
          url: PERMISSION_QUERY,
          param: this.param,
          columnList: [
            {prop: 'permissionName', label: '权限名称', width: 150},
            {prop: 'permissionCode', label: '权限编码', width: 150},
            {
              prop: 'status', label: '权限状态', width: 100, type: 'tag',
              tagMap: {
                0: {label: '启用', tag: 'success'},
                1: {label: '禁用', tag: 'danger'}
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
        this.$refs.permissionPage.query();
      },
      reset() {
        this.$refs.permissionParam.resetFields();
      },
      get(row, index) {
        this.$refs.permissionEdit.show(row.id);
      }
    },
    mounted() {
    },
    components: {
      PermissionEdit: resolve => require(['components/system/permission/PermissionEdit'], resolve)
    }
  }
</script>

<style scoped>
</style>

<!--角色管理-->
<template>
  <section>
    <button-group :buttonProps="this.buttonProps" @query="this.query" @add="this.add"
                  @delete="this.delete" @reset="this.reset"></button-group>
    <el-form ref="roleParam" :inline="true" :model="param" label-width="100px">
      <el-form-item prop="roleName" label="角色名称">
        <el-input v-model="param.roleName" placeholder="角色名称" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="roleCode" label="角色编码">
        <el-input v-model="param.roleCode" placeholder="角色编码" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="status" label="角色状态">
        <el-select v-model="param.status" placeholder="角色状态" class="search-param-style">
          <el-option label="全部" value=""></el-option>
          <el-option label="启用" value="0"></el-option>
          <el-option label="禁用" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="remark" label="备注">
        <el-input v-model="param.remark" placeholder="备注" class="search-param-style"></el-input>
      </el-form-item>
    </el-form>
    <pagination-page ref="rolePage" :pageProps="this.pageProps" @get="this.get"></pagination-page>
    <role-add ref="roleAdd"></role-add>
    <role-edit ref="roleEdit"></role-edit>
  </section>
</template>

<script>
  import {ROLE_QUERY, ROLE_DELETE} from 'api/modules/system'

  export default {
    data() {
      return {
        menuCode: 'Role',
        param: {
          roleName: '',
          roleCode: '',
          status: '',
          remark: ''
        }
      };
    },
    computed: {
      pageProps: function () {
        return {
          multiSelect: true,
          menuCode: this.menuCode,
          url: ROLE_QUERY,
          param: this.param,
          columnList: [
            {prop: 'roleName', label: '角色名称', width: 150},
            {prop: 'roleCode', label: '角色编码', width: 150},
            {
              prop: 'status', label: '角色状态', width: 100, type: 'tag',
              tagMap: {
                0: {label: '启用', tag: 'success'},
                1: {label: '禁用', tag: 'danger'}
              }
            },
            {prop: 'remark', label: '备注', minWidth: 200}
          ],
          operationList: ['get']
        };
      },
      buttonProps: function () {
        return {
          menuCode: this.menuCode,
          buttonList: ['query', 'add', 'delete', 'reset']
        }
      }
    },
    methods: {
      query() {
        this.$refs.rolePage.query();
      },
      add() {
        this.$refs.roleAdd.show();
      },
      delete() {
        var ids = this.$refs.rolePage.getSelectedIds();
        if (!ids || ids.length === 0) {
          this.$message.warning("请选择需要删除的角色！");
          return;
        }
        this.$confirm('确定要删除该角色吗？', '确认删除', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.post(ROLE_DELETE, {
            ids: ids
          }, response => {
            if (response.code == 0) {
              this.$message.success("删除成功！");
              this.query();
            }
          }, error => {
          });
        });
      },
      reset() {
        this.$refs.roleParam.resetFields();
      },
      get(row, index) {
        this.$refs.roleEdit.show(row.id);
      }
    },
    mounted() {
    },
    components: {
      RoleAdd: resolve => require(['components/system/role/RoleAdd'], resolve),
      RoleEdit: resolve => require(['components/system/role/RoleEdit'], resolve)
    }
  }
</script>

<style scoped>
</style>

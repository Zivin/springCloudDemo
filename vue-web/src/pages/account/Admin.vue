<!--管理员管理-->
<template>
  <section>
    <button-group :buttonProps="this.buttonProps" @query="this.query" @add="this.add"
                  @delete="this.delete" @reset="this.reset"></button-group>
    <el-form ref="adminParam" :inline="true" :model="param" label-width="100px">
      <el-form-item prop="account" label="账号">
        <el-input v-model="param.account" placeholder="账号" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="userName" label="用户名">
        <el-input v-model="param.userName" placeholder="用户名" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="realName" label="真实姓名">
        <el-input v-model="param.realName" placeholder="真实姓名" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="phone" label="手机号码">
        <el-input v-model="param.phone" placeholder="手机号码" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="email" label="邮箱">
        <el-input v-model="param.email" placeholder="邮箱" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="gender" label="性别">
        <el-select v-model="param.gender" placeholder="性别" class="search-param-style">
          <el-option label="全部" value=""></el-option>
          <el-option label="未知" value="0"></el-option>
          <el-option label="男性" value="1"></el-option>
          <el-option label="女性" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="adminType" label="管理员类型">
        <el-select v-model="param.adminType" placeholder="管理员类型" class="search-param-style">
          <el-option label="全部" value=""></el-option>
          <el-option label="超级管理员" value="0"></el-option>
          <el-option label="普通管理员" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="status" label="管理员状态">
        <el-select v-model="param.status" placeholder="管理员状态" class="search-param-style">
          <el-option label="全部" value=""></el-option>
          <el-option label="启用" value="0"></el-option>
          <el-option label="禁用" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="remark" label="备注">
        <el-input v-model="param.remark" placeholder="备注" class="search-param-style"></el-input>
      </el-form-item>
    </el-form>
    <item-page ref="adminPage" :pageProps="this.pageProps" @get="this.get"></item-page>
    <admin-add ref="adminAdd"></admin-add>
    <admin-edit ref="adminEdit"></admin-edit>
  </section>
</template>

<script>
  import {ADMIN_QUERY, ADMIN_DELETE} from 'api/modules/account'

  export default {
    data() {
      return {
        menuCode: 'Admin',
        param: {
          account: '',
          userName: '',
          realName: '',
          phone: '',
          email: '',
          gender: '',
          adminType: '',
          status: '',
          remark: ''
        }
      }
    },
    computed: {
      pageProps: function () {
        return {
          menuCode: this.menuCode,
          url: ADMIN_QUERY,
          param: this.param,
          itemTemplate: resolve => require(['components/account/admin/AdminTemplate'], resolve)
        }
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
        this.$refs.adminPage.query();
      },
      add() {
        this.$refs.adminAdd.show();
      },
      delete() {
        var ids = this.$refs.adminPage.getSelectedIds();
        if (!ids || ids.length === 0) {
          this.$message.warning("请选择需要删除的管理员！");
          return;
        }
        this.$confirm('确定要删除该管理员吗？', '确认删除', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.post(ADMIN_DELETE, {
            ids: this.$refs.adminPage.getSelectedIds()
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
        this.$refs.adminParam.resetFields();
      },
      get(row) {
        this.$refs.adminEdit.show(row.id);
      }
    },
    mounted() {
    },
    components: {
      AdminAdd: resolve => require(['components/account/admin/AdminAdd'], resolve),
      AdminEdit: resolve => require(['components/account/admin/AdminEdit'], resolve)
    }
  }
</script>

<style scoped>
</style>

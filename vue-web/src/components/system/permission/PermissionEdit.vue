<!--权限管理 编辑-->
<template>
  <el-dialog title="编辑权限" :visible.sync="dialogFormVisible" custom-class="permission-edit">
    <el-form ref="permissionEditFrom" :model="form" label-width="100px" style="width: 300px;">
      <el-form-item label="权限名称" prop="permissionName" :rules="this.$validator.validate('required|max:4')">
        <el-input v-model="form.permissionName"></el-input>
      </el-form-item>
      <el-form-item label="权限编码" prop="permissionCode">
        <el-input disabled v-model="form.permissionCode"></el-input>
      </el-form-item>
      <el-form-item label="权限状态" prop="status">
        <el-switch active-text="启用" inactive-text="禁用" :active-value="0" :inactive-value="1"
                   v-model="form.status"></el-switch>
      </el-form-item>
      <el-form-item label="备注" prop="remark" :rules="this.$validator.validate('max:20')">
        <el-input type="textarea" v-model="form.remark"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取消</el-button>
      <el-button v-if="this.$util.hasPermission('Permission', 'edit')" type="primary" @click="onSubmit">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {PERMISSION_GET, PERMISSION_EDIT} from 'api/modules/system'

  export default {
    data() {
      return {
        form: {
          id: "",
          permissionName: "",
          permissionCode: "",
          status: 1,
          remark: ""
        },
        dialogFormVisible: false,
      };
    },
    methods: {
      show(id) {
        // 判断ref为空，防止第一次进来页面还没初始化完成，调用报错
        if (this.$refs.permissionEditFrom != null) {
          // 清除上次弹框填入的信息
          this.$refs.permissionEditFrom.resetFields();
        }
        this.dialogFormVisible = true;

        // 获取权限信息
        this.$http.get(PERMISSION_GET, {id: id}, response => {
          if (response.code == 0) {
            this.form = response.data;
          }
        }, error => {
        });
      },
      onSubmit() {
        this.$refs.permissionEditFrom.validate((valid) => {
          if (!valid) {
            this.$message.error("修改失败，请正确填写信息！");
            return false;
          }
          this.$confirm('确定要修改该权限吗？', '确认修改', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.post(PERMISSION_EDIT, this.form, response => {
              if (response.code == 0) {
                this.$message.success("修改成功！");
                this.dialogFormVisible = false;
                this.$parent.query();
              }
            }, error => {
            });
          })
        });
      }
    }
  }
</script>

<style>
  .permission-edit {
    width: 350px;
  }
</style>

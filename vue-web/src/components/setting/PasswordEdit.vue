<!--修改密码-->
<template>
  <el-dialog title="修改密码" :visible.sync="dialogFormVisible" custom-class="password-editor">
    <el-form ref="passwordEditFrom" :model="form" label-width="100px" style="width: 300px;">
      <el-form-item label="旧密码" prop="oldPassword" :rules="this.$validator.validate('password')">
        <el-input type="password" v-model="form.oldPassword"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword" :rules="this.$validator.validate('password')">
        <el-input type="password" v-model="form.newPassword"></el-input>
      </el-form-item>
      <el-form-item label="新密码确认" prop="newPasswordRepeat" :rules="this.$validator.validate('password')">
        <el-input type="password" v-model="form.newPasswordRepeat"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取消</el-button>
      <el-button type="primary" @click="onSubmit">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {mapState} from 'vuex'
  import {ADMIN_UPDATE_PASSWORD} from 'api/modules/login'
  import md5 from 'js-md5'

  export default {
    data() {
      return {
        form: {
          oldPassword: "",
          newPassword: "",
          newPasswordRepeat: ""
        },
        dialogFormVisible: false,
      };
    },
    computed: {
      ...mapState({
        userId: state => state.user.userId
      })
    },
    methods: {
      show() {
        // 判断ref为空，防止第一次进来页面还没初始化完成，调用报错
        if (this.$refs.passwordEditFrom != null) {
          // 清除上次弹框填入的信息
          this.$refs.passwordEditFrom.resetFields();
        }
        this.dialogFormVisible = true;
      },
      onSubmit() {
        this.$refs.passwordEditFrom.validate((valid) => {
          if (!valid) {
            this.$message.error("修改失败，请正确填写信息！");
            return false;
          }
          if (this.form.newPassword != this.form.newPasswordRepeat) {
            this.$message.error("两次新密码填写不一致，请确认后修改！");
            return false;
          }
          this.$http.post(ADMIN_UPDATE_PASSWORD, {
            userId: this.userId,
            oldPassword: md5(this.form.oldPassword),
            newPassword: md5(this.form.newPassword)
          }, response => {
            if (response.code == 0) {
              this.$message.success("修改成功！");
              this.dialogFormVisible = false;
            }
          }, error => {
          });
        });
      }
    }
  }
</script>

<style>
  .password-editor {
    width: 350px;
  }
</style>

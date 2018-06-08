<!--管理员管理 新增-->
<template>
  <el-dialog title="新增管理员" :visible.sync="dialogFormVisible" custom-class="admin-add">
    <el-row>
      <el-col :span="12">
        <el-form ref="adminAddFrom1" :model="form" label-width="100px" style="width: 300px;">
          <el-form-item label="头像" prop="avatar">
            <image-uploader :initUrl="form.avatar" @success="handleAvatarSuccess"></image-uploader>
          </el-form-item>
          <el-form-item label="账号" prop="account" :rules="this.$validator.validate('required|max:32')">
            <el-input v-model="form.account"></el-input>
          </el-form-item>
          <el-form-item label="用户名" prop="userName" :rules="this.$validator.validate('required|max:32')">
            <el-input v-model="form.userName"></el-input>
          </el-form-item>
          <el-form-item label="管理员状态" prop="status">
            <el-switch active-text="启用" inactive-text="禁用" :active-value="0" :inactive-value="1"
                       v-model="form.status"></el-switch>
          </el-form-item>
          <el-form-item label="所属角色" prop="roleId">
            <el-select v-model="form.roleId" placeholder="所属角色" clearable filterable>
              <el-option v-for="(item, index) in roleList" :key="item.id" :label="item.roleName"
                         :value="item.id"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="12">
        <el-form ref="adminAddFrom2" :model="form" label-width="100px" style="width: 300px;">
          <el-form-item label="真实姓名" prop="realName" :rules="this.$validator.validate('required|max:50')">
            <el-input v-model="form.realName"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="phone" :rules="this.$validator.validate('phone')">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email" :rules="this.$validator.validate('email')">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="gender">
            <el-select v-model="form.gender" placeholder="性别">
              <el-option label="未知" :value="0"></el-option>
              <el-option label="男性" :value="1"></el-option>
              <el-option label="女性" :value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="管理员类型" prop="adminType">
            <el-select v-model="form.adminType" placeholder="管理员类型">
              <el-option label="超级管理员" :value="0"></el-option>
              <el-option label="普通管理员" :value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注" prop="remark" :rules="this.$validator.validate('max:20')">
            <el-input type="textarea" v-model="form.remark" :rows="6"></el-input>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取消</el-button>
      <el-button v-if="this.$util.hasPermission('Admin', 'add')" type="primary" @click="onSubmit">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {ADMIN_ADD} from 'api/modules/account'
  import {ROLE_QUERY} from 'api/modules/system'

  export default {
    data() {
      return {
        form: {
          avatar: "",
          account: "",
          userName: "",
          status: 1,
          roleId: "",
          realName: "",
          phone: "",
          email: "",
          gender: 0,
          adminType: 1,
          remark: ""
        },
        roleList: [],
        dialogFormVisible: false,
      };
    },
    methods: {
      show() {
        // 判断ref为空，防止第一次进来页面还没初始化完成，调用报错
        if (this.$refs.adminAddFrom1 != null) {
          // 清除上次弹框填入的信息
          this.$refs.adminAddFrom1.resetFields();
        }
        if (this.$refs.adminAddFrom2 != null) {
          // 清除上次弹框填入的信息
          this.$refs.adminAddFrom2.resetFields();
        }
        this.dialogFormVisible = true;

        // 获取角色列表
        this.$http.post(ROLE_QUERY, {}, response => {
          if (response.code == 0) {
            this.roleList = response.data.list;
          }
        }, error => {
        });
      },
      handleAvatarSuccess(imageUrl) {
        this.form.avatar = imageUrl;
      },
      onSubmit() {
        this.$refs.adminAddFrom1.validate((valid) => {
          if (!valid) {
            this.$message.error("新增失败，请正确填写信息！");
            return false;
          }
          this.$refs.adminAddFrom2.validate((valid) => {
            if (!valid) {
              this.$message.error("新增失败，请正确填写信息！");
              return false;
            }
            this.$confirm('确定要新增该管理员吗？', '确认新增', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.$http.post(ADMIN_ADD, this.form, response => {
                if (response.code == 0) {
                  this.$message.success("新增成功！");
                  this.dialogFormVisible = false;
                  this.$parent.query();
                }
              }, error => {
              });
            })
          });
        });
      }
    },
    components: {
      ImageUploader: resolve => require(['components/common/ImageUploader'], resolve)
    }
  }
</script>

<style>
  .admin-add {
    width: 700px;
  }

  .avatar {
    width: 200px;
    height: 200px;
    display: block;
  }
</style>

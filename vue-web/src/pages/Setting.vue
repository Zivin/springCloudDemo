<!--个人设置-->
<template>
  <section>
    <el-row style="width: 700px;">
      <el-col :span="12">
        <el-form ref="settingForm" :model="form" label-width="100px" style="width: 300px;">
          <el-form-item label="头像" prop="avatar">
            <image-uploader :initUrl="this.form.avatar" @success="handleAvatarSuccess"></image-uploader>
          </el-form-item>
          <el-form-item label="账号" prop="account">
            <el-input disabled v-model="form.account"></el-input>
          </el-form-item>
          <el-form-item label="用户名" prop="userName">
            <el-input v-model="form.userName"></el-input>
          </el-form-item>
          <el-form-item label="真实姓名" prop="realName">
            <el-input v-model="form.realName"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="gender">
            <el-select v-model="form.gender">
              <el-option label="未知" :value="0"></el-option>
              <el-option label="男性" :value="1"></el-option>
              <el-option label="女性" :value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="联系电话" prop="phone" :rules="this.$validator.validate('phone')">
            <el-input type="tel" v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="电子邮箱" prop="email" :rules="this.$validator.validate('email')">
            <el-input type="email" v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="管理员类型" prop="adminType">
            <el-select disabled v-model="form.adminType">
              <el-option label="超级管理员" :value="0"></el-option>
              <el-option label="普通管理员" :value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="上次登录时间" prop="lastLoginTime">
            <el-date-picker type="datetime" disabled v-model="form.lastLoginTime" style="width: 100%;">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 45%" @click="$refs.passwordEdit.show()">修改密码</el-button>
            <el-button type="primary" style="width: 45%" @click="onSubmit">保存</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="12">
        <el-tag>{{this.form.roleName}}</el-tag>
        <el-tree style="overflow: auto; height: 730px" :data="permissionList" :props="props"></el-tree>
      </el-col>
    </el-row>
    <password-edit ref="passwordEdit"></password-edit>
  </section>
</template>

<script>
  import {mapActions} from 'vuex'
  import {ADMIN_GET_ADMIN, PERMISSION_GET_PERMISSION, ADMIN_UPDATE_ADMIN} from 'api/modules/login'
  import * as types from 'store/types'

  export default {
    data() {
      return {
        form: {
          id: '',
          account: '',
          userName: '',
          gender: 0,
          avatar: '',
          realName: '',
          phone: '',
          email: '',
          adminType: 1,
          lastLoginTime: '',
          roleName: '用户角色'
        },
        permissionList: [],
        props:
          {
            label: 'name',
            children: 'children'
          }
      }
    },
    computed: {
      adminType: function () {
        switch (this.form.adminType) {
          case 0:
            return '超级管理员';
          case 1:
            return '普通管理员';
          default:
            return '未知类型';
        }
      }
    },
    methods: {
      ...mapActions({
        buildUser: types.BUILD_USER
      }),
      handleAvatarSuccess(imageUrl) {
        this.form.avatar = imageUrl;
      },
      onSubmit() {
        this.$refs.settingForm.validate((valid) => {
          if (!valid) {
            this.$message.error("保存失败，请正确填写信息！");
            return false;
          }
          this.$http.post(ADMIN_UPDATE_ADMIN, this.form, response => {
            if (response.code == 0) {
              this.$message.success("保存成功！");
              this.$http.get(ADMIN_GET_ADMIN, {}, response => {
                if (response.code == 0) {
                  this.buildUser(response.data);
                  this.form = response.data;
                }
              }, error => {
              });
            }
          }, error => {
          });
        });
      }
    },
    mounted() {
      this.$http.get(ADMIN_GET_ADMIN, {}, response => {
        if (response.code == 0) {
          this.buildUser(response.data);
          this.form = response.data;
        }
      }, error => {
      });
      this.$http.get(PERMISSION_GET_PERMISSION, {}, response => {
        if (response.code == 0) {
          let list = [];
          list.push({name: '集成系统', children: response.data.menuList[types.INTEGRATE_SYSTEM]});
          list.push({name: '用户中心', children: response.data.menuList[types.USER_CENTER]});
          list.push({name: '信息中心', children: response.data.menuList[types.MESSAGE_CENTER]});
          this.permissionList = list;
        }
      }, error => {
      });
    },
    components: {
      PasswordEdit: resolve => require(['components/setting/PasswordEdit'], resolve),
      ImageUploader: resolve => require(['components/common/ImageUploader'], resolve)
    }
  }
</script>

<style scoped>

</style>

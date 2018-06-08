<!--角色管理 编辑-->
<template>
  <el-dialog title="编辑角色" :visible.sync="dialogFormVisible" custom-class="role-edit">
    <el-row>
      <el-col :span="12">
        <el-form ref="roleEditFrom" :model="form" label-width="100px" style="width: 300px;">
          <el-form-item label="角色名称" prop="roleName" :rules="this.$validator.validate('required|max:8')">
            <el-input v-model="form.roleName"></el-input>
          </el-form-item>
          <el-form-item label="角色编码" prop="roleCode">
            <el-input disabled v-model="form.roleCode"></el-input>
          </el-form-item>
          <el-form-item label="角色状态" prop="status">
            <el-switch active-text="启用" inactive-text="禁用" :active-value="0" :inactive-value="1"
                       v-model="form.status"></el-switch>
          </el-form-item>
          <el-form-item label="备注" prop="remark" :rules="this.$validator.validate('max:20')">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="12">
        <el-tag>关联权限</el-tag>
        <el-tree ref="roleEditTree" style="overflow: auto; height: 200px" :data="rolePermissionList" show-checkbox
                 node-key="id" :props="props">
        </el-tree>
      </el-col>
    </el-row>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取消</el-button>
      <el-button v-if="this.$util.hasPermission('Role', 'edit')" type="primary" @click="onSubmit">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {ROLE_GET, ROLE_EDIT} from 'api/modules/system'

  export default {
    data() {
      return {
        form: {
          id: "",
          roleName: "",
          roleCode: "",
          status: 1,
          remark: ""
        },
        dialogFormVisible: false,
        rolePermissionList: [],
        props:
          {
            label: 'name',
            children: 'children'
          }
      };
    },
    methods: {
      show(id) {
        // 判断ref为空，防止第一次进来页面还没初始化完成，调用报错
        if (this.$refs.roleEditFrom != null) {
          // 清除上次弹框填入的信息
          this.$refs.roleEditFrom.resetFields();
        }
        this.rolePermissionList = [];
        this.dialogFormVisible = true;

        // 获取角色信息
        this.$http.get(ROLE_GET, {id: id}, response => {
          if (response.code == 0) {
            this.form = response.data;
            // 获取角色权限列表
            let role = {};
            role.children = [];
            let defaultKeyList = [];
            response.data.roleMenuPermissionList.forEach((rmp, index) => {
              let permission = {};
              permission.id = rmp.id;
              permission.name = rmp.permissionName;
              if (rmp.status === 0) {
                defaultKeyList.push(rmp.id);
              }

              let menu;
              if (role.children.length > 0 && role.children[role.children.length - 1].id === rmp.menuId) {
                menu = role.children[role.children.length - 1];
              } else {
                menu = {};
                menu.id = rmp.menuId;
                menu.name = rmp.menuName;
                menu.children = [];

                role.id = rmp.roleId;
                role.name = rmp.roleName;
                role.children.push(menu);
              }
              menu.children.push(permission);
            });
            this.rolePermissionList.push(role);
            setTimeout(() => {
              this.$refs.roleEditTree.setCheckedKeys(defaultKeyList);
            }, 0);
          }
        }, error => {
        });
      },
      onSubmit() {
        this.$refs.roleEditFrom.validate((valid) => {
          if (!valid) {
            this.$message.error("修改失败，请正确填写信息！");
            return false;
          }
          this.$confirm('确定要修改该角色吗？', '确认修改', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.post(ROLE_EDIT, {
              id: this.form.id,
              roleName: this.form.roleName,
              roleCode: this.form.roleCode,
              status: this.form.status,
              remark: this.form.remark,
              roleMenuPermissionList: this.$refs.roleEditTree.getCheckedNodes(true)
            }, response => {
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
  .role-edit {
    width: 750px;
  }
</style>

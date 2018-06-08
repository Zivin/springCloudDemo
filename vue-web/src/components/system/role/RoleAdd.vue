<!--角色管理 新增-->
<template>
  <el-dialog title="新增角色" :visible.sync="dialogFormVisible" custom-class="role-add">
    <el-row>
      <el-col :span="12">
        <el-form ref="roleAddFrom" :model="form" label-width="100px" style="width: 300px;">
          <el-form-item label="角色名称" prop="roleName" :rules="this.$validator.validate('required|max:8')">
            <el-input v-model="form.roleName"></el-input>
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
        <el-tree ref="roleAddTree" style="overflow: auto; height: 300px" :data="menuPermissionList" show-checkbox
                 node-key="id" :props="props">
        </el-tree>
      </el-col>
    </el-row>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取消</el-button>
      <el-button v-if="this.$util.hasPermission('Role', 'add')" type="primary" @click="onSubmit">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {MENU_QUERY_PERMISSION, ROLE_ADD} from 'api/modules/system'

  export default {
    data() {
      return {
        form: {
          id: "",
          roleName: "",
          status: 1,
          remark: ""
        },
        dialogFormVisible: false,
        menuPermissionList: [],
        props:
          {
            label: 'name',
            children: 'children'
          }
      };
    },
    methods: {
      show() {
        // 判断ref为空，防止第一次进来页面还没初始化完成，调用报错
        if (this.$refs.roleAddFrom != null) {
          // 清除上次弹框填入的信息
          this.$refs.roleAddFrom.resetFields();
        }
        this.menuPermissionList = [];
        this.dialogFormVisible = true;

        // 获取权限列表信息
        this.$http.get(MENU_QUERY_PERMISSION, {}, response => {
          if (response.code == 0) {
            // 获取角色权限列表
            response.data.forEach((mp, index) => {
              let permission = {};
              permission.id = mp.id;
              permission.name = mp.permissionName;

              let menu;
              if (this.menuPermissionList.length > 0 && this.menuPermissionList[this.menuPermissionList.length - 1].id === mp.menuId) {
                menu = this.menuPermissionList[this.menuPermissionList.length - 1];
              } else {
                menu = {};
                menu.id = mp.menuId;
                menu.name = mp.menuName;
                menu.children = [];

                this.menuPermissionList.push(menu);
              }
              menu.children.push(permission);
            });
          }
        }, error => {
        });
      },
      onSubmit() {
        this.$refs.roleAddFrom.validate((valid) => {
          if (!valid) {
            this.$message.error("新增失败，请正确填写信息！");
            return false;
          }
          this.$confirm('确定要新增该角色吗？', '确认新增', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.post(ROLE_ADD, {
              id: this.form.id,
              roleName: this.form.roleName,
              status: this.form.status,
              remark: this.form.remark,
              roleMenuPermissionList: this.$refs.roleAddTree.getCheckedNodes(true)
            }, response => {
              if (response.code == 0) {
                this.$message.success("新增成功！");
                this.dialogFormVisible = false;
                this.$parent.query();
              }
            }, error => {
            });
          });
        });
      }
    }
  }
</script>

<style>
  .role-add {
    width: 750px;
  }
</style>

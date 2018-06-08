<!--菜单管理 编辑-->
<template>
  <el-dialog title="编辑菜单" :visible.sync="dialogFormVisible" custom-class="menu-edit">
    <el-row>
      <el-col :span="12">
        <el-form ref="menuEditFrom" :model="form" label-width="100px" style="width: 300px;">
          <el-form-item label="菜单名称" prop="menuName" :rules="this.$validator.validate('required|max:6')">
            <el-input v-model="form.menuName"></el-input>
          </el-form-item>
          <el-form-item label="菜单编码" prop="menuCode">
            <el-input disabled v-model="form.menuCode"></el-input>
          </el-form-item>
          <el-form-item label="菜单图标" prop="icon">
            <i :class="form.icon" v-model="form.icon"></i>
            <el-button type="primary" size="mini" style="float: right;" @click="">修改</el-button>
          </el-form-item>
          <el-form-item label="菜单状态" prop="status">
            <el-switch active-text="启用" inactive-text="禁用" :active-value="0" :inactive-value="1"
                       v-model="form.status"></el-switch>
          </el-form-item>
          <el-form-item label="菜单排序 " prop="sort">
            <el-tooltip content="为保证排序结果，请确保同一模块下的菜单排序不重复" placement="bottom">
              <el-input v-model="form.sort"></el-input>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="备注" prop="remark" :rules="this.$validator.validate('max:20')">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="12">
        <el-tag>关联权限</el-tag>
        <el-tree ref="menuEditTree" style="overflow: auto; height: 320px" :data="menuPermissionList" show-checkbox
                 node-key="id" :props="props">
        </el-tree>
      </el-col>
    </el-row>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取消</el-button>
      <el-button v-if="this.$util.hasPermission('Menu', 'edit')" type="primary" @click="onSubmit">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {MENU_GET, MENU_EDIT} from 'api/modules/system'

  export default {
    data() {
      return {
        form: {
          id: "",
          menuName: "",
          menuCode: "",
          icon: "",
          status: 1,
          sort: 0,
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
      show(id) {
        // 判断ref为空，防止第一次进来页面还没初始化完成，调用报错
        if (this.$refs.menuEditFrom != null) {
          // 清除上次弹框填入的信息
          this.$refs.menuEditFrom.resetFields();
        }
        this.menuPermissionList = [];
        this.dialogFormVisible = true;

        // 获取菜单信息
        this.$http.get(MENU_GET, {id: id}, response => {
          if (response.code == 0) {
            this.form = response.data;
            // 获取菜单权限列表
            let menu = {};
            menu.children = [];
            let defaultKeyList = [];
            response.data.menuPermissionList.forEach((mp, index) => {
              let permission = {};
              permission.id = mp.id;
              permission.name = mp.permissionName;
              if (mp.status === 0) {
                defaultKeyList.push(mp.id);
              }
              menu.id = mp.menuId;
              menu.name = mp.menuName;
              menu.children.push(permission);
            });
            this.menuPermissionList.push(menu);
            setTimeout(() => {
              this.$refs.menuEditTree.setCheckedKeys(defaultKeyList);
            }, 0);
          }
        }, error => {
        });
      },
      onSubmit() {
        this.$refs.menuEditFrom.validate((valid) => {
          if (!valid) {
            this.$message.error("修改失败，请正确填写信息！");
            return false;
          }
          this.$confirm('确定要修改该菜单吗？', '确认修改', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.form.menuPermissionList = this.$refs.menuEditTree.getCheckedNodes(true);
            this.$http.post(MENU_EDIT, this.form, response => {
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
  .menu-edit {
    width: 750px;
  }
</style>

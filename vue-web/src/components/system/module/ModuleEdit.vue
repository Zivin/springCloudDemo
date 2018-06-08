<!--模块管理 编辑-->
<template>
  <el-dialog title="编辑模块" :visible.sync="dialogFormVisible" custom-class="module-edit">
    <el-form ref="moduleEditFrom" :model="form" label-width="100px" style="width: 300px;">
      <el-form-item label="模块名称" prop="moduleName" :rules="this.$validator.validate('required|max:6')">
        <el-input v-model="form.moduleName"></el-input>
      </el-form-item>
      <el-form-item label="模块编码" prop="moduleCode">
        <el-input disabled v-model="form.moduleCode"></el-input>
      </el-form-item>
      <el-form-item label="菜单图标" prop="icon">
        <i :class="form.icon" v-model="form.icon"></i>
        <el-button type="primary" size="mini" style="float: right;" @click="">修改</el-button>
      </el-form-item>
      <el-form-item label="模块状态" prop="status">
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
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取消</el-button>
      <el-button v-if="this.$util.hasPermission('Module', 'edit')" type="primary" @click="onSubmit">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {MODULE_GET, MODULE_EDIT} from 'api/modules/system'

  export default {
    data() {
      return {
        form: {
          id: "",
          moduleName: "",
          moduleCode: "",
          status: 1,
          remark: ""
        },
        dialogFormVisible: false,
      };
    },
    methods: {
      show(id) {
        // 判断ref为空，防止第一次进来页面还没初始化完成，调用报错
        if (this.$refs.moduleEditFrom != null) {
          // 清除上次弹框填入的信息
          this.$refs.moduleEditFrom.resetFields();
        }
        this.dialogFormVisible = true;

        // 获取模块信息
        this.$http.get(MODULE_GET, {id: id}, response => {
          if (response.code == 0) {
            this.form = response.data;
          }
        }, error => {
        });
      },
      onSubmit() {
        this.$refs.moduleEditFrom.validate((valid) => {
          if (!valid) {
            this.$message.error("修改失败，请正确填写信息！");
            return false;
          }
          this.$confirm('确定要修改该模块吗？', '确认修改', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.post(MODULE_EDIT, this.form, response => {
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
  .module-edit {
    width: 350px;
  }
</style>

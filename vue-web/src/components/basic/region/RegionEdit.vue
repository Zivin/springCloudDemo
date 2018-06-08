<!--区域管理 编辑-->
<template>
  <el-dialog title="编辑区域" :visible.sync="dialogFormVisible" custom-class="region-edit">
    <el-form ref="regionEditFrom" :model="form" label-width="100px" style="width: 300px;">
      <el-form-item label="区域名称" prop="regionName" :rules="this.$validator.validate('required|max:4')">
        <el-input v-model="form.regionName"></el-input>
      </el-form-item>
      <el-form-item label="区域编码" prop="regionCode">
        <el-input disabled v-model="form.regionCode"></el-input>
      </el-form-item>
      <el-form-item label="区域状态" prop="status">
        <el-switch active-text="启用" inactive-text="禁用" :active-value="0" :inactive-value="1"
                   v-model="form.status"></el-switch>
      </el-form-item>
      <el-form-item label="父级区域名称" prop="parentRegionName">
        <el-input disabled v-model="form.parentRegionName"></el-input>
      </el-form-item>
      <el-form-item label="父级区域编码" prop="parentRegionCode">
        <el-input disabled v-model="form.parentRegionCode"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="remark" :rules="this.$validator.validate('max:20')">
        <el-input type="textarea" v-model="form.remark"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取消</el-button>
      <el-button v-if="this.$util.hasPermission('Region', 'edit')" type="primary" @click="onSubmit">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {REGION_GET, REGION_EDIT} from 'api/modules/basic'

  export default {
    data() {
      return {
        form: {
          id: "",
          regionName: "",
          regionCode: "",
          regionLevel: 1,
          status: 1,
          parentRegionName: "",
          parentRegionCode: "",
          remark: ""
        },
        dialogFormVisible: false,
      };
    },
    methods: {
      show(id) {
        // 判断ref为空，防止第一次进来页面还没初始化完成，调用报错
        if (this.$refs.regionEditFrom != null) {
          // 清除上次弹框填入的信息
          this.$refs.regionEditFrom.resetFields();
        }
        this.dialogFormVisible = true;

        // 获取区域信息
        this.$http.get(REGION_GET, {id: id}, response => {
          if (response.code == 0) {
            this.form = response.data;
          }
        }, error => {
        });
      },
      onSubmit() {
        this.$refs.regionEditFrom.validate((valid) => {
          if (!valid) {
            this.$message.error("修改失败，请正确填写信息！");
            return false;
          }
          this.$confirm('确定要修改该区域吗？', '确认修改', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.post(REGION_EDIT, this.form, response => {
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
  .region-edit {
    width: 350px;
  }
</style>

<!--区域管理 新增-->
<template>
  <el-dialog title="新增区域" :visible.sync="dialogFormVisible" custom-class="region-add">
    <el-form ref="regionAddFrom" :model="form" label-width="100px" style="width: 300px;">
      <el-form-item label="区域名称" prop="regionName" :rules="this.$validator.validate('required|max:4')">
        <el-input v-model="form.regionName"></el-input>
      </el-form-item>
      <el-form-item label="区域编码" prop="regionCode" :rules="this.$validator.validate('required|number|length:12')">
        <el-input v-model="form.regionCode"></el-input>
      </el-form-item>
      <el-form-item label="区域状态" prop="status">
        <el-switch active-text="启用" inactive-text="禁用" :active-value="0" :inactive-value="1"
                   v-model="form.status"></el-switch>
      </el-form-item>
      <el-form-item label="备注" prop="remark" :rules="this.$validator.validate('max:20')">
        <el-input type="textarea" v-model="form.remark"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取消</el-button>
      <el-button v-if="this.$util.hasPermission('Region', 'add')" type="primary" @click="onSubmit">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {REGION_GET, REGION_ADD} from 'api/modules/basic'

  export default {
    data() {
      return {
        form: {
          id: "",
          regionName: "",
          regionCode: "",
          status: 1,
          remark: ""
        },
        dialogFormVisible: false,
      };
    },
    methods: {
      show(id) {
        // 判断ref为空，防止第一次进来页面还没初始化完成，调用报错
        if (this.$refs.regionAddFrom != null) {
          // 清除上次弹框填入的信息
          this.$refs.regionAddFrom.resetFields();
        }
        this.dialogFormVisible = true;
      },
      onSubmit() {
        this.$refs.regionAddFrom.validate((valid) => {
          if (!valid) {
            this.$message.error("新增失败，请正确填写信息！");
            return false;
          }
          this.$confirm('确定要新增该区域吗？', '确认新增', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.post(REGION_ADD, this.form, response => {
              if (response.code == 0) {
                this.$message.success("新增成功！");
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
  .region-add {
    width: 350px;
  }
</style>

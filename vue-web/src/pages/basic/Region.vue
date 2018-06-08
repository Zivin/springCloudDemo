<!--区域管理-->
<template>
  <section>
    <button-group :buttonProps="this.buttonProps" @query="this.query" @add="this.add"
                  @delete="this.delete" @refresh="this.refresh" @reset="this.reset"></button-group>
    <el-form ref="regionParam" :inline="true" :model="param" label-width="100px">
      <el-form-item prop="regionName" label="区域名称">
        <el-input v-model="param.regionName" placeholder="区域名称" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="regionCode" label="区域编码">
        <el-input v-model="param.regionCode" placeholder="区域编码" class="search-param-style"></el-input>
      </el-form-item>
      <el-form-item prop="regionLevel" label="区域等级">
        <el-select v-model="param.regionLevel" placeholder="区域等级" class="search-param-style">
          <el-option label="全部" value=""></el-option>
          <el-option label="国" value="0"></el-option>
          <el-option label="省" value="1"></el-option>
          <el-option label="市" value="2"></el-option>
          <el-option label="县/区" value="3"></el-option>
          <el-option label="乡/镇" value="4"></el-option>
          <el-option label="村" value="5"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="status" label="区域状态">
        <el-select v-model="param.status" placeholder="区域状态" class="search-param-style">
          <el-option label="全部" value=""></el-option>
          <el-option label="启用" value="0"></el-option>
          <el-option label="禁用" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="parentId" label="父级区域">
        <region-selector v-model="param.parentId" class="search-param-style"></region-selector>
      </el-form-item>
      <el-form-item prop="remark" label="备注">
        <el-input v-model="param.remark" placeholder="备注" class="search-param-style"></el-input>
      </el-form-item>
    </el-form>
    <pagination-page ref="regionPage" :pageProps="this.pageProps" @get="this.get"></pagination-page>
    <region-add ref="regionAdd"></region-add>
    <region-edit ref="regionEdit"></region-edit>
  </section>
</template>

<script>
  import {REGION_QUERY, REGION_DELETE, REGION_REFRESH} from 'api/modules/basic'

  export default {
    data() {
      return {
        menuCode: 'Region',
        param: {
          regionName: '',
          regionCode: '',
          regionLevel: '',
          status: '',
          parentId: '',
          remark: ''
        }
      };
    },
    computed: {
      pageProps: function () {
        return {
          multiSelect: true,
          menuCode: this.menuCode,
          url: REGION_QUERY,
          param: this.param,
          columnList: [
            {prop: 'regionName', label: '区域名称', width: 150},
            {prop: 'regionCode', label: '区域编码', width: 150},
            {
              prop: 'regionLevel', label: '区域等级', width: 100, type: 'tag',
              tagMap: {
                0: {label: '国'},
                1: {label: '省'},
                2: {label: '市'},
                3: {label: '县/区'},
                4: {label: '乡/镇'},
                5: {label: '村'}
              }
            },
            {
              prop: 'status', label: '区域状态', width: 100, type: 'tag',
              tagMap: {
                0: {label: '启用', tag: 'success'},
                1: {label: '禁用', tag: 'danger'}
              }
            },
            {prop: 'parentRegionName', label: '父级区域名称', width: 150},
            {prop: 'parentRegionCode', label: '父级区域编码', width: 150},
            {prop: 'remark', label: '备注', minWidth: 200}
          ],
          operationList: ['get']
        }
      },
      buttonProps: function () {
        return {
          menuCode: this.menuCode,
          buttonList: ['query', 'add', 'delete', 'refresh', 'reset']
        }
      }
    },
    methods: {
      query() {
        this.$refs.regionPage.query();
      },
      add() {
        this.$refs.regionAdd.show();
      },
      delete() {
        var ids = this.$refs.regionPage.getSelectedIds();
        if (!ids || ids.length === 0) {
          this.$message.warning("请选择需要删除的区域！");
          return;
        }
        this.$confirm('确定要删除该区域吗？', '确认删除', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.post(REGION_DELETE, {
            ids: this.$refs.regionPage.getSelectedIds()
          }, response => {
            if (response.code == 0) {
              this.$message.success("删除成功！");
              this.query();
            }
          }, error => {
          });
        });
      },
      refresh() {
        this.$confirm('系统将会重新从第三方拉取区域数据，确定要刷新全部区域数据吗？', '确认刷新', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.get(REGION_REFRESH, {}, response => {
            if (response.code == 0) {
              this.$message.success("刷新请求提交成功，请稍后查看数据！");
            }
          }, error => {
          });
        });
      },
      reset() {
        this.$refs.regionParam.resetFields();
      },
      get(row, index) {
        this.$refs.regionEdit.show(row.id);
      }
    }
    ,
    mounted() {
    }
    ,
    components: {
      RegionAdd: resolve => require(['components/basic/region/RegionAdd'], resolve),
      RegionEdit: resolve => require(['components/basic/region/RegionEdit'], resolve)
    }
  }
</script>

<style scoped>

</style>

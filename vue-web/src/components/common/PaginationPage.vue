<!--带分页的列表控件-->
<template>
  <div>
    <el-table :data="list" :row-key="id" border stripe
              v-loading="loading" highlight-current-row @selection-change="handleSelectionChange"
              @current-change="handleCurrentSelectionChange">
      <el-table-column v-if="pageProps.multiSelect" type="selection" width="35">
      </el-table-column>
      <el-table-column type="index" label="序号" align="center" width="50" fixed="left">
      </el-table-column>
      <el-table-column v-for="(column, index) in pageProps.columnList" :key="column.prop" :fixed="column.fixed"
                       :align="column.type?'center':column.align" :prop="column.prop"
                       :label="column.label" :width="column.width" :min-width="column.minWidth"
                       :formatter="column.formatter" show-overflow-tooltip header-align="center">
        <template slot-scope="scope">
          <div v-if="column.type === 'tag' && column.tagMap">
            <el-tag v-if="column.tagMap"
                    :type='column.tagMap[scope.row[column.prop]]?column.tagMap[scope.row[column.prop]].tag:"danger"'>
              {{column.tagMap[scope.row[column.prop]]?column.tagMap[scope.row[column.prop]].label:"未知"}}
            </el-tag>
          </div>
          <div v-else-if="column.type === 'icon'" :class="scope.row[column.prop]"></div>
          <div v-else-if="column.formatter">{{column.formatter(scope.row, scope.column, scope.row[column.prop])}}</div>
          <div v-else>{{scope.row[column.prop]}}</div>
        </template>
      </el-table-column>
      <el-table-column v-if="operationList.length > 0" label="操作" :width="operationList.length * 100" align="center"
                       fixed="right">
        <template slot-scope="scope">
          <el-button v-for="operation in operationList" :key="operation.action" type="primary" size="small"
                     @click="handleButtonClick(operation.action, scope.row, scope.$index)">
            {{operation.label}}
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination layout="sizes, prev, pager, next, jumper, ->, total" :page-sizes="[10, 20, 50, 100]"
                   :page-size="pageSize" :current-page="pageNum" :total="total"
                   @size-change="handleSizeChange" @current-change="handleCurrentChange">
    </el-pagination>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        list: [],
        pageSize: 20,
        pageNum: 1,
        total: 0,
        loading: false,
        // 多选时使用
        selectedIds: [],
        selectedNames: [],
        // 单选时使用
        selectedId: '',
        selectedName: ''
      };
    },
    props: [
      'pageProps'
    ],
    computed: {
      operationList: function () {
        let list = [];
        // 判断是否拥有权限
        if (this.pageProps.operationList) {
          this.pageProps.operationList.forEach((item, index) => {
            let permissionName = this.$util.hasPermission(this.pageProps.menuCode, item);
            // 存在权限
            if (permissionName) {
              list.push({label: permissionName, action: item});
            }
          });
        }
        return list;
      }
    },
    methods: {
      query() {
        this.loading = true;
        this.$http.post(this.pageProps.url, {
          param: this.pageProps.param,
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }, response => {
          if (response.code == 0) {
            this.list = response.data.list;
            this.total = response.data.total;
          }
          this.loading = false;
        }, error => {
          this.loading = false;
        });
      },
      handleSizeChange(pageSize) {
        this.pageSize = pageSize;
        this.pageNum = 1;
        this.query();
      },
      handleCurrentChange(pageNum) {
        this.pageNum = pageNum;
        this.query();
      },
      handleButtonClick(action, row, index) {
        this.$emit(action, row, index);
      },
      handleSelectionChange(selection) {
        this.selectedIds = [];
        this.selectedNames = [];
        selection.forEach((item, index) => {
          this.selectedIds.push(item.id);
          this.selectedNames.push(item[this.pageProps.selectName]);
        });
      },
      handleCurrentSelectionChange(currentRow, oldCurrentRow) {
        if (currentRow) {
          this.selectedId = currentRow.id;
          this.selectedName = currentRow[this.pageProps.selectName];
        } else {
          this.selectedId = '';
          this.selectedName = '';
        }
      },
      // 给父组件调用，获取选中的id
      getSelectedIds() {
        return this.selectedIds;
      },
      // 给父组件调用，获取选中的字段
      getSelectedNames() {
        return this.selectedNames;
      },
      // 给父组件调用，获取选中的id
      getSelectedId() {
        return this.selectedId;
      },
      // 给父组件调用，获取选中的字段
      getSelectedName() {
        return this.selectedName;
      }
    },
    mounted() {
      this.query();
    }
  }
</script>

<style scoped>
</style>

<template>
  <div>
    <el-col :span="8" style="border: 1px solid #ebeef5">
      <el-row>
        <el-button type="text" size="mini" @click="queryList({ funcId: 0, children: 'A' })">根结点列表</el-button>
        <el-button type="text" size="mini" @click="doAdd(0)">添加根结点</el-button>
      </el-row>
      <el-row>
        <el-tree default-expand-all :data="treeList" :props="{ children: 'children', label: 'name' }">
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}</span>
            <span>
              <el-button type="text" size="mini" @click.stop="queryList(data)" :disabled="data.leaf==1">结点列表</el-button>
              <el-button type="text" size="mini" @click.stop="doAdd(data.funcId)">添加下级</el-button>
              <el-button type="text" size="mini" @click.stop="doEdit(data)">编辑</el-button>
              <el-button type="text" size="mini" @click.stop="doDelete(data)" :disabled="data.leaf==0">删除</el-button>
            </span>
          </span>
        </el-tree>
      </el-row>
    </el-col>
    <el-col :span="16" style="padding-left: 20px">
      <v-table :data="dataList">
        <el-table-column prop="name" label="名称" width="100"></el-table-column>
        <el-table-column prop="path" label="路径"></el-table-column>
        <el-table-column prop="icon" label="图标"></el-table-column>
        <el-table-column prop="orders" label="排序" width="60"></el-table-column>
        <el-table-column prop="component" label="组件路径"></el-table-column>
      </v-table>
    </el-col>
    <FuncDialog ref="dialog" :queryTree="queryTree"> </FuncDialog>
  </div>
</template>
<script>
import FuncDialog from "./FuncDialog.vue";
import { treeMix } from "@/common/tree";
export default {
  mixins: [treeMix],
  components: { FuncDialog },
  methods: {
    /*功能菜单-树*/
    queryTree() {
      this.rq.post("/func/tree").then((res) => {
        if (res.code == 200) this.treeList = res.data;
        else this.$message.error(res.msg);
      });
    },
    /*功能菜单-列表*/
    queryList(data) {
      this.loading = true;
      if (data.children) {
        let param = { parentId: data.funcId };
        this.rq.post("/func/list", param).then((res) => {
          if (res.code == 200) this.dataList = res.data; else this.$message.error(res.msg);
        });
      }
      this.loading = false;
    },
    /*功能菜单-删除*/
    doDelete(row) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定", cancelButtonText: "取消", type: "warning" }).then(() => {
        this.rq.post("/func/delete", row).then((res) => {
          if (res.code == 200) {
            this.$message.success("删除成功"); this.queryTree();
          } else this.$message.error(res.msg);
        });
      });
    },
  },
};
</script>
<style scoped lang="scss">
.custom-tree-node { flex: 1; display: flex; align-items: center; justify-content: space-between; font-size: 14px; padding-right: 20px; }
::v-deep .el-tree { overflow-y: auto; height: 800px; }
</style>
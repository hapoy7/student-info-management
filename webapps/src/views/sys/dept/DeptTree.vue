/*组织机构管理,作者:高振中,日期:2020-11-15 21:33:20*/
<template>
  <div>
    <el-col :span="8" style="border: 1px solid #ebeef5">
      <el-row>
        <el-button type="text" size="mini" @click="queryList({ id: 0, children: 'A' })" >根结点列表</el-button>
        <el-button type="text" size="mini" @click="doAdd(0)">添加根结点</el-button>
      </el-row>
      <el-row>
        <el-tree default-expand-all :data="treeList" :props="{ children: 'children', label: 'name' }" :style="treeHeight">
          <span class="tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}</span>
            <span>
              <el-button type="text" size="mini" @click.stop="queryList(data)" :disabled="data.leaf==1">结点列表</el-button>
              <el-button type="text" size="mini" @click.stop="doAdd(data.id)">添加下级</el-button>
              <el-button type="text" size="mini" @click.stop="doEdit(data)">编辑</el-button>
              <el-button type="text" size="mini" @click.stop="doDelete(data)" :disabled="data.leaf==0">删除</el-button>
            </span>
          </span>
        </el-tree>
      </el-row>
    </el-col>
    <el-col :span="16" style="padding-left: 20px">
      <v-table :data="dataList" >
        <el-table-column prop="name" label="机构名称"></el-table-column>
        <el-table-column prop="type" label="机构类型" :formatter="deptTypeFormat"></el-table-column>
        <el-table-column prop="code" label="机构编码"></el-table-column>
        <el-table-column prop="status" label="状态" :formatter="statusFormat"></el-table-column>
        <el-table-column prop="orderNum" label="排序编号"></el-table-column>
        <el-table-column prop="remark" label="备注"></el-table-column>
      </v-table>
    </el-col>
    <DeptDialog ref="dialog" :queryTree="queryTree"></DeptDialog>
  </div>
</template>
<script>
import DeptDialog from "./DeptDialog.vue";
import { treeMix } from "@/common/tree";
import { statusFormat, deptTypeFormat} from "@/common/dicts";
export default {
  mixins: [treeMix],
  components: { DeptDialog },
  computed: {
    treeHeight() { return "overflow-y: auto; height: " + (document.documentElement.clientHeight - 184) + "px;"; },
  },
  methods: {
    deptTypeFormat,statusFormat,
    /**组织机构-树查询*/
    queryTree() {
      this.rq.post("/dept/tree").then((res) => {
        if (res.code == 200) this.treeList = res.data;
      });
    },
    /**组织机构-列表*/
    queryList(data) {
      this.loading = true;
      if (data.children) {
        this.rq.post("/dept/list", { parentId: data.id }).then((res) => {
          if (res.code == 200) this.dataList = res.data;
          else this.$message.error(res.msg);
        });
      } else this.dataList = [];
      this.loading = false;
    },
    /**组织机构-树删除*/
    doDelete(row) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定", cancelButtonText: "取消", type: "warning" }).then(() => {
        this.rq.post("/dept/delete", row).then((res) => {
          if (res.code == 200) {
            this.$message.success("删除成功");
            this.queryTree();
          } else this.$message.error(res.msg);
        });
      });
    },
  },
};
</script>
<style scoped lang="scss">
.tree-node { flex: 1; display: flex; align-items: center; justify-content: space-between; font-size: 14px; padding-right: 8px; }
</style>
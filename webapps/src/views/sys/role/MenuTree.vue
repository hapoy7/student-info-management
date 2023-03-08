/*功能菜单树,作者:高振中,日期:2021-02-23 15:04:24*/
<template>
  <el-dialog title="功能权限" :visible.sync="show" width="500px">
    <el-tree default-expand-all :data="menus" ref="tree" :props="{ children: 'children', label: 'name' }" show-checkbox
      node-key="funcId"> <span slot-scope="{ node }">{{ node.label }}</span> </el-tree>
    <div style="text-align: right">
      <el-button @click="show = false" size="small">取消</el-button>
      <el-button type="primary" @click="save()" size="small">确定</el-button>
    </div>
  </el-dialog>
</template>
<script>
export default {
  data() {
    return {
      menus: [],
      show: false,
      nodes: null,
      roleId: null,
    };
  },
  methods: {
    /*功能菜单树-显示*/
    showFuncs(roleId) {
      this.roleId = roleId;
      this.show = true;
      this.queryTree(roleId);
    },
    /*功能菜单-保存授权*/
    save() {
      this.nodes = [];
      this.$refs.tree.getCheckedNodes(false, true).map((i) => this.nodes.push({ roleId: this.roleId, funcId: i.funcId }));
      this.rq.post("/roleFunc/save?roleId=" + this.roleId, this.nodes).then((res) => {
        if (res.code == 200) {
          this.show = false;
          this.$message.success("授权成功");
        } else this.$message.error(res.msg);
      });
    },
    /*功能菜单树-请求数据*/
    queryTree(roleId) {
      this.rq.post("/roleFunc/tree?roleId=" + roleId).then((res) => {
        if (res.code == 200) {
          this.menus = res.data.funcs;
          this.$refs.tree.setCheckedKeys(res.data.ids);
        } else this.$message.error(res.msg);
      });
    },
  },
};
</script>
<style scoped lang="scss">
::v-deep .el-tree { overflow-y: auto; height: 800px; }
</style>
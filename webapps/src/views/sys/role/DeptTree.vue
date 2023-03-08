/*组织机构树,作者:高振中,日期:2021-02-23 15:04:24*/
<template>
  <el-dialog title="数据权限" :visible.sync="show" width="300px">
    <el-tree default-expand-all :data="depts" ref="tree" :props="{ children: 'children', label: 'name' }" show-checkbox node-key="id">
      <span slot-scope="{ node }">{{ node.label }}</span>
    </el-tree>
    <div style="text-align: right">
      <el-button @click="show = false" size="small">取消</el-button> 
      <el-button type="primary" @click="save()" size="small">确定</el-button>
    </div>
  </el-dialog>
</template>
<script>
export default {
  data() {
    return { depts: [], show: false, nodes: null, roleId: null };
  },
  methods: {
    /*组织机构树-显示*/
    showDepts(roleId) {
      this.roleId = roleId;
      this.show = true;
      this.queryTree(roleId);
    },
    /*组织机构-保存授权*/
    save() {
      this.nodes = [];
      this.$refs.tree.getCheckedKeys().map((i) => this.nodes.push({ roleId: this.roleId, deptId: i }));
      this.rq.post("/roleDept/save?roleId=" + this.roleId, this.nodes).then((res) => {
        if (res.code == 200) {
          this.show = false;
          this.$message.success("授权成功");
        } else this.$message.error(res.msg);
      });
    },
    /*组织机构树-请求数据*/
    queryTree(roleId) {
      this.rq.post("/roleDept/tree?roleId=" + roleId).then((res) => {
        if (res.code == 200) {
          this.depts = res.data.depts;
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
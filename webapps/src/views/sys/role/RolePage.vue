/*角色分页列表,作者:高振中,日期:2021-02-20 11:34:58*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="角色名称">
        <el-input placeholder="请输入角色名称" size="mini" v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="备注">
        <el-input placeholder="请输入备注" size="mini" v-model="form.remark"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" @click="loadData">查询</el-button>
        <el-button size="mini" @click="doReset">重置</el-button>
        <el-button size="mini" @click="doAdd" type="primary">添加</el-button>
        <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0">删除</el-button>
        <el-button size="mini" @click="doExport" style="margin-right: 8px">导出</el-button>
        <v-upload :doImport="doImport" label="导入"></v-upload>
      </el-form-item>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows) => selectChange(rows, 'roleId')">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="name" label="角色名称"></el-table-column>
      <el-table-column prop="type" label="类型" :formatter="roleTypeFormat"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>
      <el-table-column prop="orderNum" label="排序"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" :formatter="dateFormat" width="140"></el-table-column>
      <el-table-column prop="updateTime" label="修改时间" :formatter="dateFormat" width="140"></el-table-column>
      <el-table-column label="操作" width="300">
        <template slot-scope="props">
          <div>
            <el-button type="text" size="mini" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" size="mini" @click="doDelete([props.row.roleId])">删除</el-button>
            <el-button type="text" size="mini" @click="linkUser(props.row.roleId)">关联用户</el-button>
            <el-button type="text" size="mini" @click="linkFunc(props.row.roleId)">功能权限</el-button>
            <el-button type="text" size="mini" @click="linkDept(props.row.roleId)">数据权限</el-button>
          </div>
        </template>
      </el-table-column>
    </v-table>
    <v-page :page="page" :total="total" :size="size" :pageChange="pageChange" :sizeChange="sizeChange"></v-page>
    <RoleDialog ref="dialog" :loadData="loadData"></RoleDialog>
    <UserRef ref="userRef"></UserRef>
    <MenuTree ref="menuTree"></MenuTree>
    <DeptTree ref="deptTree"></DeptTree>
  </div>
</template>
<script>
import RoleDialog from "./RoleDialog";
import MenuTree from "./MenuTree";
import DeptTree from "./DeptTree";
import UserRef from "./UserRef";
import { pageMix } from "@/common/page";
import { roleTypeFormat } from "@/common/dicts";
export default {
  components: { RoleDialog, UserRef, MenuTree, DeptTree },
  mixins: [pageMix],
  created() {
    this.loadData();
  },
  methods: {
    roleTypeFormat,
    /*角色-导出*/
    doExport() {
      const param = { ...this.form };
      this.rq.post("/role/export", param, { responseType: "blob" }).then((res) => {
        this.downloadFile(res, "角色.xlsx");
      });
    },
    /*角色-导入*/
    doImport(obj) {
      this.rq.post("/role/import", obj.file).then((res) => {
        if (res.code == 200) this.loadData();
        else this.$message.error(res.msg);
      });
    },
    /*角色-关联用户*/
    linkUser(roleId) {
      this.$refs["userRef"].showUsers(roleId);
    },
    /*角色-授权功能*/
    linkFunc(roleId) {
      this.$refs["menuTree"].showFuncs(roleId);
    },
    /*角色-授权部门*/
    linkDept(roleId) {
      this.$refs["deptTree"].showDepts(roleId);
    },
    /*角色-查询参数*/
    initForm() {
      return { name: null, /*角色名称*/ remark: null /*备注*/ };
    },
    /*角色-列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/role/page", param).then((res) => {
        if (res.code == 200) {
          this.dataList = res.data.dataList;
          this.total = res.data.rowCount;
        } else this.$message.error(res.msg);
      });
      this.loading = false;
    },
    /*角色-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定", cancelButtonText: "取消", type: "warning" }).then(() => {
        this.rq.post("/role/delete", ids).then((res) => {
          if (res.code == 200) {
            this.$message.success("删除成功");
            this.loadData();
          } else this.$message.error(res.msg);
        });
      });
    },
  },
};
</script>
<style scoped lang="scss">

</style>

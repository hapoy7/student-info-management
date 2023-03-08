/*表引用关系分页列表,作者:高振中,日期:2022-10-12 14:42:25*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="主表表名"><el-input placeholder="请输入主表表名" size="mini" v-model="form.mainTable"></el-input></el-form-item>
      <el-form-item label="主表描述"><el-input placeholder="请输入主表描述" size="mini" v-model="form.mainInfo"></el-input></el-form-item>
      <el-form-item label="子表表名"><el-input placeholder="请输入子表表名" size="mini" v-model="form.subTable"></el-input></el-form-item>
      <el-form-item label="子表描述"><el-input placeholder="请输入子表描述" size="mini" v-model="form.subInfo"></el-input></el-form-item>
      <el-form-item>
        <el-button size="mini" @click="loadData">查询</el-button>
        <el-button size="mini" @click="doReset">重置</el-button>
        <el-button size="mini" @click="doAdd" type="primary">添加</el-button>
        <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0">删除</el-button>
        <el-button size="mini" @click="()=>allShow=!allShow" type="text">{{allShow?"▲":"▼"}}</el-button>
      </el-form-item>
      <div v-if="allShow">
        <el-form-item label="外键名称"><el-input placeholder="请输入外键名称" size="mini" v-model="form.refId"></el-input></el-form-item>
        <el-form-item label="删除标记"><el-input placeholder="请输入删除标记" size="mini" v-model="form.delFlag"></el-input></el-form-item>
        <el-form-item label="创建时间"><el-input placeholder="请输入创建时间" size="mini" v-model="form.createTime"></el-input></el-form-item>
        <el-form-item label="创建人主键"><el-input placeholder="请输入创建人主键" size="mini" v-model="form.createBy"></el-input></el-form-item>
        <el-form-item label="修改时间"><el-input placeholder="请输入修改时间" size="mini" v-model="form.updateTime"></el-input></el-form-item>
        <el-form-item label="修改人主键"><el-input placeholder="请输入修改人主键" size="mini" v-model="form.updateBy"></el-input></el-form-item>
        <el-form-item label="删除标记"><el-input placeholder="请输入删除标记" size="mini" v-model="form.dr"></el-input></el-form-item>
      </div>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows)=>selectChange(rows,'id')">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="mainTable" label="主表表名"></el-table-column>
      <el-table-column prop="mainInfo" label="主表描述"></el-table-column>
      <el-table-column prop="subTable" label="子表表名"></el-table-column>
      <el-table-column prop="subInfo" label="子表描述"></el-table-column>
      <el-table-column prop="refId" label="外键名称"></el-table-column>
      <el-table-column prop="dr" label="删除标记"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column prop="createBy" label="创建人主键"></el-table-column>
      <el-table-column prop="updateTime" label="修改时间"></el-table-column>
      <el-table-column prop="updateBy" label="修改人主键"></el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="props">
          <div>
            <el-button type="text" size="mini" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" size="mini" @click="doDelete([props.row.id])">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </v-table>
    <v-page :page="page" :total="total" :size="size" :pageChange="pageChange" :sizeChange="sizeChange"></v-page>
    <TableRefDialog ref="dialog" :loadData="loadData"></TableRefDialog>
  </div>
</template>
<script>
import TableRefDialog from './TableRefDialog.vue';
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  components: { TableRefDialog },
  data() { return {} },
  //computed: {}, mounted(){},
  created() { this.loadData(); },
  methods: {
    /**表引用关系-查询参数*/
    initForm() {
      return {
        mainTable: null,/*主表表名*/
        mainInfo: null,/*主表描述*/
        subTable: null,/*子表表名*/
        subInfo: null,/*子表描述*/
        refId: null,/*外键名称*/
        delFlag: null,/*删除标记*/
      };
    },
    /**表引用关系-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/tableRef/page", param).then((res) => {
        if (res.code == 200) {
          this.dataList = res.data.dataList;
          this.total = res.data.rowCount;
          this.loading = false;
        } else
          this.$message.error(res.msg);
      });
    },
     /**表引用关系-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定",cancelButtonText: "取消",type: "warning" }).then(() => {
        this.rq.post("/tableRef/delete", ids).then((res) => {
          if (res.code == 200) { this.$message.success("删除成功"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
  },    
};
</script>
<style scoped lang="scss"></style>

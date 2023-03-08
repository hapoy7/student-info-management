/*系统参数分页列表,作者:高振中,日期:2021-04-11 00:29:26*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="组名称"><el-input placeholder="请输入组名称" size="mini" v-model="form.groupName"></el-input></el-form-item>
      <el-form-item label="参数名称"><el-input placeholder="请输入参数名称" size="mini" v-model="form.paramName"></el-input></el-form-item>
      <el-form-item label="参数键"><el-input placeholder="请输入参数键" size="mini" v-model="form.paramKey"></el-input></el-form-item>
      <el-form-item label="参数值"><el-input placeholder="请输入参数值" size="mini" v-model="form.paramValue"></el-input></el-form-item>
      <el-form-item>
        <el-button size="mini" @click="loadData">查询</el-button>
        <el-button size="mini" @click="doReset">重置</el-button>
        <el-button size="mini" @click="doAdd" type="primary">添加</el-button>
        <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0">删除</el-button>
      </el-form-item>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows) => selectChange(rows,'id')">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="groupName" label="组名称"></el-table-column>
      <el-table-column prop="paramName" label="参数名称"></el-table-column>
      <el-table-column prop="paramKey" label="参数键"></el-table-column>
      <el-table-column prop="paramValue" label="参数值"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" :formatter="dateFormat"></el-table-column>
      <el-table-column prop="updateTime" label="修改时间" :formatter="dateFormat"></el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="props">
          <div>
            <el-button type="text" size="mini" @click="doEdit(props.row)">编辑</el-button><el-button type="text" size="mini" @click="doDelete([props.row.id])">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </v-table>
    <v-page :page="page" :total="total" :size="size" :pageChange="pageChange" :sizeChange="sizeChange"></v-page>
    <ParameterDialog ref="dialog" :loadData="loadData"></ParameterDialog>
  </div>
</template>
<script>
import ParameterDialog from "./ParameterDialog.vue";
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  components: { ParameterDialog },
  data() {
    return {};
  },
  //computed: {}, mounted(){},
  created() {
    this.loadData();
  },
  methods: {
    /**系统参数-查询参数*/
    initForm() {
      return { groupName: null, /*组名称*/ paramName: null, /*参数名称*/ paramKey: null, /*参数键*/ paramValue: null /*参数值*/ };
    },
    /**系统参数-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/parameter/page", param).then((res) => {
        if (res.code == 200) {
          this.dataList = res.data.dataList;
          this.total = res.data.rowCount;
          this.loading = false;
        } else this.$message.error(res.msg);
      });
    },
    /**系统参数-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定", cancelButtonText: "取消", type: "warning" }).then(() => {
        this.rq.post("/parameter/delete", ids).then((res) => {
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
<style scoped lang="scss"></style>

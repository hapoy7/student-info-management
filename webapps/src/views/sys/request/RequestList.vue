/*请求时长统计不分页列表,作者:高振中,日期:2021-02-20 14:02:55*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="请求路径"> <el-input placeholder="请输入请求路径" size="mini" v-model="form.path"></el-input> </el-form-item>
      <el-form-item label="类型"> <v-select v-model="form.type" placeholder="请选择请求状态" :dictKey="'requestType'" :addBlank="true"/> </el-form-item>
      <el-form-item>
        <el-button size="mini" @click="loadData()" type="primary" >查询</el-button>
        <el-button size="mini" @click="doReset()">重置</el-button>
        <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0">删除</el-button>
      </el-form-item>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows) => selectChange(rows,'id')">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="appName" label="应用名"></el-table-column>
      <el-table-column prop="path" label="请求路径" sortable></el-table-column>
      <el-table-column prop="sumCount" label="请求次数" sortable></el-table-column>
      <el-table-column prop="sumMilliSecond" label="总毫秒" sortable></el-table-column>
      <el-table-column prop="avgMilliSecond" label="平均毫秒" sortable></el-table-column>
      <el-table-column prop="maxMilliSecond" label="最大毫秒" sortable></el-table-column>
      <el-table-column prop="minMilliSecond" label="最小毫秒" sortable></el-table-column>
      <el-table-column prop="type" label="请求类型" sortable :formatter="requestTypeFormat"></el-table-column>
      <el-table-column label="操作" width="100">
        <template slot-scope="props"><el-button type="text" size="mini" @click="doDelete([props.row.path])">删除</el-button></template>
      </el-table-column>
    </v-table>
  </div>
</template>
<script>
import { pageMix } from "@/common/page";
import { requestTypeFormat } from "@/common/dicts";
export default {
  mixins: [pageMix],
  created() {
    this.loadData();
  },
  methods: {
    requestTypeFormat,
    /*选中记录*/
    selectChange(rows) {
      this.selectIds = rows.map((item) => item.path);
    },
    /*请求统计-查询参数*/
    initForm() {
      return { path: null, /*应用名*/ type: null /*1后台2前端*/ };
    },
    /*请求统计-列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form };
      this.rq.post("/request/list", param).then((res) => {
        if (res.code == 200) {
          this.dataList = res.data;
          this.loading = false;
        } else this.$message.error(res.msg);
      });
    },
    /*请求统计-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定", cancelButtonText: "取消", type: "warning" }).then(() => {
        this.rq.post("/request/delete", ids).then((res) => {
          if (res.code == 200) {
            this.$message.success(res.msg);
            this.loadData();
          } else this.$message.success(res.msg);
        });
      });
    },
  },
};
</script>
<style scoped lang="scss"></style>

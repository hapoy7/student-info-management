/*系统日志分页列表,作者:高振中,日期:2021-03-12 12:57:38*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="日志标题"><el-input placeholder="请输入日志标题" size="mini" v-model="form.title"></el-input></el-form-item>
      <el-form-item label="用户主键"><el-input placeholder="请输入用户主键" size="mini" v-model="form.userId"></el-input></el-form-item>
      <el-form-item label="用户名"><el-input placeholder="请输入用户名" size="mini" v-model="form.userName"></el-input></el-form-item>
      <el-form-item label="IP地址"><el-input placeholder="请输入IP地址" size="mini" v-model="form.ip"></el-input></el-form-item>
      <el-form-item>
        <el-button @click="loadData()" size="mini" type="primary" >查询</el-button> <el-button @click="doReset()" size="mini">重置</el-button>
        <el-button v-if="selectIds.length > 0" @click="doDelete(selectIds)" size="mini">删除</el-button>
        <el-button size="mini" @click="()=>allShow=!allShow" type="text">{{allShow?"▲":"▼"}}</el-button>
      </el-form-item>
      <div v-if="allShow">
        <el-form-item label="java类.方法"><el-input placeholder="请输入java类.方法" size="mini" v-model="form.method"></el-input></el-form-item>
        <el-form-item label="请求路径"><el-input placeholder="请输入请求路径" size="mini" v-model="form.url"></el-input> </el-form-item>
        <el-form-item label="请求类型"><el-input placeholder="请输入请求类型" size="mini" v-model="form.requestType"></el-input></el-form-item>
      </div>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows) => selectChange(rows, 'id')">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column type="expand">
        <template slot-scope="props">
          <span style="color: blue">请求参数</span> {{ props.row.params }}
          <hr />
          <span style="color: blue">反回结果</span> {{ props.row.result }}
        </template>
      </el-table-column>
      <el-table-column prop="title" label="日志标题" ></el-table-column>
      <el-table-column prop="userId" label="用户主键"></el-table-column>
      <el-table-column prop="userName" label="用户名"></el-table-column>
      <el-table-column prop="ip" label="IP地址"></el-table-column>
      <el-table-column prop="method" label="java类.方法" width="280px"></el-table-column>
      <el-table-column prop="url" label="请求路径"></el-table-column>
      <el-table-column prop="requestType" label="请求类型"></el-table-column>
      <el-table-column prop="operateTime" label="请求时间" :formatter="dateFormat"></el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="props"> <el-button type="text" size="mini" @click="doDelete([props.row.id])">删除</el-button> </template>
      </el-table-column>
    </v-table>
    <v-page :page="page" :total="total" :size="size" :pageChange="pageChange" :sizeChange="sizeChange"></v-page>
  </div>
</template>
<script>
import { pageMix } from "@/common/page";
import { dateFormat } from "@/common/dicts";
export default {
  mixins: [pageMix],
  data() {
    return {};
  },
  created() {
    this.loadData();
  },
  methods: {
    dateFormat,
    /*日志-查询参数*/
    initForm() {
      return { title: null, /*日志标题*/ userId: null, /*用户主键*/ userName: null, /*用户名*/ ip: null, /*IP地址*/ method: null, /*java类.方法*/ url: null, /*请求路径*/ requestType: null, /*请求类型*/ params: null /*请求参数*/ };
    },
    /*日志-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/log/page", param).then((res) => {
        if (res.code == 200) {
          this.dataList = res.data.dataList;
          this.total = res.data.rowCount;
          this.loading = false;
        } else this.$message.error(res.msg);
      });
    },
    /*日志-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定", cancelButtonText: "取消", type: "warning" }).then(() => {
        this.rq.post("/log/delete", ids).then((res) => {
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

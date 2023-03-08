/*字典管理,作者:高振中,日期:2021-02-19 17:18:03*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="字典名称" v-show="condition.name.show"> <el-input placeholder="请输入字典名称" size="mini" v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="字典编码" v-show="condition.code.show"> <el-input placeholder="请输入字典编码" size="mini" v-model="form.code"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" @click="loadData" type="primary" >查询</el-button>
        <el-button size="mini" @click="doReset">重置</el-button>
        <el-button size="mini" @click="doAdd" type="primary">添加</el-button>
        <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0">删除</el-button>
        <el-button size="mini" @click="doRefresh">刷新缓存</el-button>
        <v-columns :condition="condition" :column="column"></v-columns>
      </el-form-item>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows) => selectChange(rows, 'id')">
      <el-table-column type="selection" width="40" v-if="column.choice.show"></el-table-column> 
      <el-table-column prop="name" label="字典名称" v-if="column.name.show"></el-table-column>
      <el-table-column prop="code" label="字典编码" v-if="column.code.show"></el-table-column>
      <el-table-column prop="remark" label="备注" v-if="column.remark.show"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" :formatter="dateFormat" v-if="column.createTime.show"></el-table-column>
      <el-table-column prop="updateTime" label="修改时间" :formatter="dateFormat" v-if="column.updateTime.show"></el-table-column>
      <el-table-column label="操作" width="150" v-if="column.operate.show">
        <template slot-scope="props">
          <div>
            <el-button type="text" size="mini" @click="queryItemList(props.row)">字典项</el-button>
            <el-button type="text" size="mini" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" size="mini" @click="doDelete([props.row.id])">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </v-table>
    <v-page :page="page" :total="total" :size="size" :pageChange="pageChange" :sizeChange="sizeChange"></v-page>
    <DictDialog ref="dialog" :loadData="loadData"></DictDialog>
    <DictItemList ref="itemList"></DictItemList>

  </div>
</template>
<script>
import DictDialog from "./DictDialog";
import DictItemList from "../dictitem/DictItemList";
import { pageMix } from "@/common/page";
import Vue from "vue";
import { DICTS } from "@/common/Constant";
export default {
  mixins: [pageMix],
  components: { DictDialog, DictItemList },
  created() {
    this.loadData();
  },
  data() {
    return {
      column: {
        choice: { show: true, text: "选择列" },
        name: { show: true, text: "字典名称" },
        code: { show: true, text: "字典编码" },
        remark: { show: true, text: "备注" },
        createTime: { show: true, text: "创建时间" },
        updateTime: { show: true, text: "修改时间" },
        operate: { show: true, text: "操作列" },
      },
      condition: {
        name: { show: true, text: "字典名称" },
        code: { show: true, text: "字典编码" },
      },
    };
  },
  methods: {
    /**刷新localStore字典数据*/
    doRefresh(page) {
      console.log("page=", page);
      this.rq.post("/dictItem/all").then((res) => {
        if (res.code == 200) {
          Vue.ls.set(DICTS, res.data);
        } else this.$message.error(res.msg);
      });
    },
    /**字典项-列表*/
    queryItemList(row) {
      this.$refs["itemList"].showItemList(row.id);
    },
    /**字典-查询参数*/
    initForm() {
      return { name: null, /*字典名称*/ code: null, /*字典编码*/ remark: null /*备注*/ };
    },
    /**字典-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/dict/page", param).then((res) => {
        if (res.code == 200) {
          this.dataList = res.data.dataList;
          this.total = res.data.rowCount;
          this.loading = false;
        } else this.$message.error(res.msg);
      });
    },
    /**字典-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定", cancelButtonText: "取消", type: "warning" }).then(() => {
        this.rq.post("/dict/delete", ids).then((res) => {
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

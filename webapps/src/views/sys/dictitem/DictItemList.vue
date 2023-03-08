/*字典项管理,作者:高振中,日期:2021-02-19 18:15:11*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false"
    width="1200px">
    <el-form :inline="true">
      <el-form-item label="字典项文本"> <el-input placeholder="请输入字典项文本" size="mini" v-model="form.text"></el-input>
      </el-form-item>
      <el-form-item label="字典项值"> <el-input placeholder="请输入字典项值" size="mini" v-model="form.value"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" @click="loadData()">查询</el-button>
        <el-button size="mini" @click="doReset()">重置</el-button>
        <el-button size="mini" @click="doAdd(form.dictId)" type="primary">添加</el-button>
        <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0">删除</el-button>
      </el-form-item>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows) => selectChange(rows, 'id')">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="text" label="字典项文本"></el-table-column>
      <el-table-column prop="value" label="字典项值" width="70"></el-table-column>
      <el-table-column prop="orderNum" label="排序编号" width="70"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="150" :formatter="dateFormat"></el-table-column>
      <el-table-column prop="createBy" label="创建人" width="70"></el-table-column>
      <el-table-column prop="updateTime" label="修改时间" width="150" :formatter="dateFormat"></el-table-column>
      <el-table-column prop="updateBy" label="修改人" width="70"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="props">
          <div>
            <el-button type="text" size="mini" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" size="mini" @click="doDelete([props.row.id])">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </v-table>
    <DictItemDialog ref="dialog" :loadData="loadData"></DictItemDialog>
  </el-dialog>
</template>
<script>
import DictItemDialog from "./DictItemDialog.vue";
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  components: { DictItemDialog },
  data() {
    return { title: null, show: false };
  },
  methods: {
    /*字典项列表*/
    showItemList(id) {
      this.title = "字典项列表";
      this.show = true;
      this.form.dictId = id;
      this.loadData();
    },
    /*字典项-新增*/
    doAdd(dictId) {
      this.$refs["dialog"].addDialog(dictId);
    },
    /*字典项列表-重置*/
    doReset() {
      this.form = { ...this.initForm(), dictId: this.form.dictId };
      this.loadData();
    },
    /*字典项-查询参数*/
    initForm() {
      return { dictId: null, /*字典主键*/ text: null, /*字典项文本*/ value: null /*字典项值*/ };
    },
    /*字典项-列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form };
      this.rq.post("/dictItem/list", param).then((res) => {
        if (res.code == 200) {
          this.dataList = res.data; /*数据列表*/
          this.loading = false;
        } else this.$message.error(res.msg);
      });
    },
    /*字典项-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定", cancelButtonText: "取消", type: "warning" }).then(() => {
        this.rq.post("/dictItem/delete", ids).then((res) => {
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
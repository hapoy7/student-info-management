/*用户管理,作者:高振中,日期:2020-12-20 22:36:10*/
<template>
  <el-dialog title="用户选择" :visible.sync="show" width="1200px" :modal="false">
    <el-form :inline="true">
      <el-form-item label="机构"> <el-input placeholder="请输入机构" size="mini" v-model="form.deptId"></el-input>
      </el-form-item>
      <el-form-item label="姓名"> <el-input placeholder="请输入姓名" size="mini" v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <v-select v-model="form.gender" placeholder="请选择性别" :dictKey="'gender'" :addBlank="true" />
      </el-form-item>
      <el-form-item label="状态">
        <v-select v-model="form.status" placeholder="请选择状态" :dictKey="'status'" :addBlank="true" />
      </el-form-item>
      <el-form-item>
        <el-button size="mini" @click="queryPage">查询</el-button>
      </el-form-item>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows) =>this.selectRow = rows">
    <el-table-column type="selection" width="40" v-if="type == 1"></el-table-column>
    <el-table-column width="50px" label="选择"  v-if="type == 2">
      <template slot-scope="scope">
        <el-radio-group v-model="radioVal" :key="radioKey">
          <el-radio :label="scope.row.userId" v-model="radio" @change.native.prevent="changeRadio(scope.row)">{{""}}</el-radio>
        </el-radio-group>
      </template>
      </el-table-column>
      <el-table-column prop="deptName" label="机构名称"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="loginName" label="登录名"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
      <el-table-column prop="status" label="状态" :formatter="statusFormat" width="60"></el-table-column>
      <el-table-column prop="number" label="编号"></el-table-column>
      <el-table-column prop="cardNo" label="卡号"></el-table-column>
      <el-table-column prop="type" label="人员类型" :formatter="pesonTypeFormat" width="80"></el-table-column>
      <el-table-column prop="startTime" label="开始时间" width="140"></el-table-column>
      <el-table-column prop="endTime" label="结束时间" width="140"></el-table-column>
    </v-table>
    <div style="text-align: right">
      <el-button size="mini" @click="show = false">取消</el-button>
      <el-button size="mini" @click="getSelectValue()" type="primary">确定</el-button>
    </div>
    <v-page :page="page" :total="total" :size="size" :pageChange="pageChange" :sizeChange="sizeChange"></v-page>
  </el-dialog>
</template>
<script>
import { genderFormat, statusFormat, pesonTypeFormat } from "@/common/dicts";
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  props: ["setValue"],
  data() {
    return { dataList: [], show: false, selectRow: null, radio: "", type: null,  radioKey: false, radioVal:''};
  },
  name: "v-user-page",
  methods: {
    statusFormat, pesonTypeFormat, genderFormat,
    /*用户-查询参数*/
    initForm() {
      return { deptId: null, /*机构ID*/ name: null, /*姓名*/ gender: null, /*性别*/ status: null /*状态*/ };
    },
    showUserPage(type) {
      this.show = true;
      this.from = this.initForm();
      this.type = type;
      this.queryPage();
    },
    queryPage() {
      this.radioVal = null;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/user/page", param).then((res) => {
        if (res.code == 200) {
          this.dataList = res.data.dataList;
          this.total = res.data.rowCount
        } else this.$message.error(res.msg);
      });
    },
    getSelectValue() {
      this.setValue(this.selectRow);
      this.show = false;
    },
     /*用户-请求列表*/
     loadData() {
      this.queryPage();
    },
    changeRadio(row) {
      this.selectRow = row ;
    },
  },
};
</script>
<style scoped lang="scss">

</style>
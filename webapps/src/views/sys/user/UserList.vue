/*用户管理,作者:高振中,日期:2020-12-20 22:36:10*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="姓名"> <el-input placeholder="请输入姓名" size="mini" v-model="form.name"></el-input> </el-form-item>
      <el-form-item label="性别"> <v-select v-model="form.gender" placeholder="请选择性别" :dictKey="'gender'" :addBlank="true"/> </el-form-item>
      <el-form-item label="状态"> <v-select v-model="form.gender" placeholder="请选择状态" :dictKey="'status'" :addBlank="true"/> </el-form-item>
      <el-form-item>
        <el-button size="mini" @click="loadData">查询</el-button>
        <el-button size="mini" @click="doReset">重置</el-button>
        <el-button size="mini" @click="doAdd" type="primary">添加</el-button>
        <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0">删除</el-button>
        <el-button size="mini" @click="()=>allShow=!allShow" type="text">{{allShow?"▲":"▼"}}</el-button>
      </el-form-item>
      <div v-if="allShow">
        <el-form-item label="登录名"> <el-input placeholder="请输入登录名" size="mini" v-model="form.loginName"></el-input> </el-form-item>
        <el-form-item label="电话"> <el-input placeholder="请输入电话" size="mini" v-model="form.phone"></el-input> </el-form-item> 
      </div>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows) => selectChange(rows,'userId')">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="name" label="姓名"  ></el-table-column>
      <el-table-column prop="loginName" label="登录名" ></el-table-column>
      <el-table-column prop="phone" label="电话" ></el-table-column>
      <el-table-column prop="status" label="状态" :formatter="statusFormat" width="60"></el-table-column>
      <el-table-column prop="number" label="编号" ></el-table-column>
      <el-table-column prop="cardNo" label="卡号" ></el-table-column>
      <el-table-column prop="groupName" label="分组" ></el-table-column>
      <el-table-column prop="type" label="人员类型" :formatter="pesonTypeFormat" width="80"></el-table-column>
      <el-table-column prop="startTime" label="开始时间" width="140"></el-table-column>
      <el-table-column prop="endTime" label="结束时间" width="140"></el-table-column>
      <el-table-column label="头像" width="50">
        <template slot-scope="props">
          <el-popover trigger="hover"> <img :src="props.row.avatarUrl" height="300px" />
            <el-button type="text" slot="reference" size="mini">头像</el-button>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="props">
          <div>
            <el-button type="text" size="mini" @click="fingerPage(props.row)">指纹</el-button>
            <el-button type="text" size="mini" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" size="mini" @click="doDelete([props.row.userId])">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </v-table>
    <v-page :page="page" :total="total" :size="size" :pageChange="pageChange" :sizeChange="sizeChange"></v-page>
    <UserDialog ref="dialog" :loadData="loadData"></UserDialog>
  </div>
</template>
<script>
import UserDialog from "./UserDialog.vue";
import { pageMix } from "@/common/page";
import { genderFormat, statusFormat,pesonTypeFormat } from "@/common/dicts";
export default {
  mixins: [pageMix],
  components: { UserDialog,  },
  created() {
    this.loadData();
  },
  methods: {
    statusFormat,pesonTypeFormat,genderFormat,
    /*用户-查询参数*/
    initForm() {
      return { deptId: null, /*机构ID*/ name: null, /*姓名*/ password: null, /*密码*/ loginName: null, /*登录名*/ avatar: null, /*头像*/ phone: null, /*电话*/ gender: null, /*性别*/ status: null /*状态*/ };
    },
    /*用户-请求列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/user/page", param).then((res) => {
        this.dataList = res.data.dataList;
        this.total = res.data.rowCount;
        this.loading = false;
      });
    },
    /*用户-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定", cancelButtonText: "取消", type: "warning" }).then(() => {
        this.rq.post("/user/delete", ids).then((res) => {
          if (res.code == 200) {
            this.$message.success("删除成功");
            this.loadData();
          } else this.$message.error(res.msg);
        });
      });
    },
     /**指纹-列表*/
     fingerPage(userId) {
      this.$refs["fingerPage"].showFingerPage(userId);
    },
  },
};
</script>
<style scoped lang="scss"></style>

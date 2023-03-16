/*老师分页列表,作者:张可可,日期:2023-03-08 23:42:07*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="老师姓名" v-show="condition.name.show"><el-input placeholder="请输入老师姓名" size="mini" v-model="form.name"></el-input></el-form-item>
      <el-form-item label="性别" v-show="condition.sex.show"> <v-select v-model="form.sex" placeholder="请选择性别" :dictKey="'gender'" :addBlank="true"/> </el-form-item>
      <el-form-item label="年龄" v-show="condition.age.show"><el-input placeholder="请输入年龄" size="mini" v-model="form.age"></el-input></el-form-item>
      <el-form-item>
        <el-button size="mini" @click="loadData">查询</el-button>
        <el-button size="mini" @click="doReset">重置</el-button>
        <el-button size="mini" @click="doAdd" type="primary">添加</el-button>
        <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0">删除</el-button>
        <el-button size="mini" @click="()=>allShow=!allShow" type="text">{{allShow?"▲":"▼"}}</el-button>
        <v-columns :condition="condition" :column="column" component="TeacherPage"></v-columns>
      </el-form-item>
      <div v-if="allShow">
      <el-form-item label="头衔" v-show="condition.title.show"><el-input placeholder="请输入头衔" size="mini" v-model="form.title"></el-input></el-form-item>
      <el-form-item label="邮箱" v-show="condition.email.show"><el-input placeholder="请输入邮箱" size="mini" v-model="form.email"></el-input></el-form-item>
      <el-form-item label="头像地址" v-show="condition.photoUrl.show"><el-input placeholder="请输入头像地址" size="mini" v-model="form.photoUrl"></el-input></el-form-item>
      <el-form-item label="手机" v-show="condition.phone.show"><el-input placeholder="请输入手机" size="mini" v-model="form.phone"></el-input></el-form-item>
      </div>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows)=>selectChange(rows,'id')">
      <el-table-column type="selection" width="40" v-if="column.choice.show" ></el-table-column>
      <el-table-column prop="name" label="老师姓名" v-if="column.name.show" ></el-table-column>
      <el-table-column prop="sex" label="性别" v-if="column.sex.show" :formatter="genderFormat"></el-table-column>
      <el-table-column prop="age" label="年龄" v-if="column.age.show" ></el-table-column>
      <el-table-column prop="title" label="头衔" v-if="column.title.show" ></el-table-column>
      <el-table-column prop="email" label="邮箱" v-if="column.email.show" ></el-table-column>
      <el-table-column prop="photoUrl" label="头像地址" v-if="column.photoUrl.show" ></el-table-column>
      <el-table-column prop="phone" label="手机" v-if="column.phone.show" ></el-table-column>
      <el-table-column label="操作" width="150" v-if="column.operate.show">
        <template slot-scope="props">
          <div>
            <el-button type="text" size="mini" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" size="mini" @click="doDelete([props.row.id])">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </v-table>
    <v-page :page="page" :total="total" :size="size" :pageChange="pageChange" :sizeChange="sizeChange"></v-page>
    <TeacherDialog ref="dialog" :loadData="loadData"></TeacherDialog>
  </div>
</template>
<script>
import TeacherDialog from './TeacherDialog';
import { pageMix } from "@/common/page";
import { genderFormat } from '@/common/dicts';
export default {
  mixins: [pageMix],
  components: { TeacherDialog },
  data() { 
    return {
      column: { 
        choice: { show: true, text: "选择列" }, 
        detail: { show: true, text: "明细列" }, 
        name: {show: true, text: "老师姓名" },
        sex: {show: true, text: "性别" },
        age: {show: true, text: "年龄" },
        title: {show: true, text: "头衔" },
        email: {show: true, text: "邮箱" },
        photoUrl: {show: true, text: "头像地址" },
        phone: {show: true, text: "手机" },
        operate: { show: true, text: "操作列" }, 
      },
      condition: { 
        name: {show: true, text: "老师姓名" },
        sex: {show: true, text: "性别" },
        age: {show: true, text: "年龄" },
        title: {show: true, text: "头衔" },
        email: {show: true, text: "邮箱" },
        photoUrl: {show: true, text: "头像地址" },
        phone: {show: true, text: "手机" },
      },
    }; 
  },
  //computed: {}, mounted(){},
  created() { this.loadData(); },
  methods: {
    genderFormat,
    /**老师-查询参数*/
    initForm() {
      return {
        userId: null,/*用户id*/
        name: null,/*老师姓名*/
        sex: null,/*性别*/
        age: null,/*年龄*/
        title: null,/*头衔*/
        email: null,/*邮箱*/
        photoUrl: null,/*头像地址*/
        phone: null,/*手机*/
      };
    },
    /**老师-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/teacher/page", param).then(res => {
        if (res.code == 200) { this.dataList = res.data.dataList; this.total = res.data.rowCount; this.loading = false; } else this.$message.error(res.msg);
      });
    },
     /**老师-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定",cancelButtonText: "取消",type: "warning" }).then(() => {
        this.rq.post("/teacher/delete", ids).then(res => {
          if (res.code == 200) { this.$message.success("删除成功"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
  },    
};
</script>
<style scoped lang="scss">

</style>

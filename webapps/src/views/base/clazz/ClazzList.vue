/*班级不分页列表,作者:张可可,日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <h3>班级-列表</h3>
    <hr />
    <el-form :inline="true">
      <el-form-item label="班级名称" v-show="condition.name.show" ><el-input placeholder="请输入班级名称" size="mini" v-model="form.name"></el-input></el-form-item>
      <el-form-item label="专业id" v-show="condition.majorId.show" ><el-input placeholder="请输入专业id" size="mini" v-model="form.majorId"></el-input></el-form-item>
      <el-form-item label="学院id" v-show="condition.instituteId.show" ><el-input placeholder="请输入学院id" size="mini" v-model="form.instituteId"></el-input></el-form-item>
      </div>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows)=>selectChange(rows,'id')">
      <el-table-column type="selection" width="40" v-if="column.choice.show" ></el-table-column>
      <el-table-column type="expand" v-if="column.detail.show" width="35">
        <template slot-scope="props">
          <label class="label">班级名称</label>{{props.row.name}}
          <hr class="hr">
          <label class="label">专业id</label>{{props.row.majorId}}
          <hr class="hr">
          <label class="label">学院id</label>{{props.row.instituteId}}
          <hr class="hr">
        </template>
      </el-table-column>
      <el-table-column prop="name" label="班级名称" v-if="column.name.show" ></el-table-column>
      <el-table-column prop="majorId" label="专业id" v-if="column.majorId.show" ></el-table-column>
      <el-table-column prop="instituteId" label="学院id" v-if="column.instituteId.show" ></el-table-column>
      <el-table-column label="操作" width="150" v-if="column.operate.show">
        <template slot-scope="props">
          <div><el-button type="text" size="mini" @click="doEdit(props.row)">编辑</el-button><el-button type="text" size="mini" @click="doDelete([props.row.id])">删除</el-button></div>
        </template>
      </el-table-column>
    </v-table>
    <ClazzDialog ref="dialog" :loadData="loadData"></ClazzDialog>
  </el-dialog>
</template>
<script>
import ClazzDialog from './ClazzDialog.vue';
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  components: { ClazzDialog }, 
  data() { 
    return {
      column: { 
        choice: { show: true, text: "选择列" }, 
        detail: { show: true, text: "明细列" }, 
        name: {show: true, text: "班级名称" },
        majorId: {show: true, text: "专业id" },
        instituteId: {show: true, text: "学院id" },
        operate: { show: true, text: "操作列" }, 
      },
      condition: { 
        name: {show: true, text: "班级名称" },
        majorId: {show: true, text: "专业id" },
        instituteId: {show: true, text: "学院id" },
      },
    }; 
  },
  //computed: {}, mounted(){},
  created() { this.loadData(); },
  methods: {
    /**班级-查询参数*/
    initForm() {
      return {
        name: null,/*班级名称*/
        majorId: null,/*专业id*/
        instituteId: null,/*学院id*/
      };
    },
    /**班级-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form};
      this.rq.post("/clazz/list", param).then(res => {
        if (res.code == 200) this.dataList = res.data; else this.$message.error(res.msg);
      });
      this.loading = false;
    },
     /**班级-删除*/    
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定",cancelButtonText: "取消",type: "warning" }).then(() => {
        this.rq.post("/clazz/delete", ids).then(res => {
          if (res.code == 200) { 
            this.$message.success("删除成功"); this.loadData(); 
          } else this.$message.error(res.msg);
        });
      });
    },
  },
};
</script>
<style scoped lang="scss">

</style>

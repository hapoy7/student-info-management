/*专业分页列表,作者:张可可,日期:2023-03-08 23:42:07*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="专业名称" v-show="condition.name.show"><el-input placeholder="请输入专业名称" size="mini" v-model="form.name"></el-input></el-form-item>
      <el-form-item label="专业编号" v-show="condition.majorNumber.show"><el-input placeholder="请输入专业编号" size="mini" v-model="form.majorNumber"></el-input></el-form-item>
      <el-form-item label="学院id" v-show="condition.instituteId.show"><el-input placeholder="请输入学院id" size="mini" v-model="form.instituteId"></el-input></el-form-item>
      </div>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows)=>selectChange(rows,'id')">
      <el-table-column type="selection" width="40" v-if="column.choice.show" ></el-table-column>
      <el-table-column type="expand" v-if="column.detail.show" width="35">
        <template slot-scope="props">
          <label class="label">专业名称</label>{{props.row.name}}
          <hr class="hr">
          <label class="label">专业编号</label>{{props.row.majorNumber}}
          <hr class="hr">
          <label class="label">学院id</label>{{props.row.instituteId}}
          <hr class="hr">
        </template>
      </el-table-column>
      <el-table-column prop="name" label="专业名称" v-if="column.name.show" ></el-table-column>
      <el-table-column prop="majorNumber" label="专业编号" v-if="column.majorNumber.show" ></el-table-column>
      <el-table-column prop="instituteId" label="学院id" v-if="column.instituteId.show" ></el-table-column>
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
    <MajorDialog ref="dialog" :loadData="loadData"></MajorDialog>
  </div>
</template>
<script>
import MajorDialog from './MajorDialog';
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  components: { MajorDialog },
  data() { 
    return {
      column: { 
        choice: { show: true, text: "选择列" }, 
        detail: { show: true, text: "明细列" }, 
        name: {show: true, text: "专业名称" },
        majorNumber: {show: true, text: "专业编号" },
        instituteId: {show: true, text: "学院id" },
        operate: { show: true, text: "操作列" }, 
      },
      condition: { 
        name: {show: true, text: "专业名称" },
        majorNumber: {show: true, text: "专业编号" },
        instituteId: {show: true, text: "学院id" },
      },
    }; 
  },
  //computed: {}, mounted(){},
  created() { this.loadData(); },
  methods: {
    /**专业-查询参数*/
    initForm() {
      return {
        name: null,/*专业名称*/
        majorNumber: null,/*专业编号*/
        instituteId: null,/*学院id*/
      };
    },
    /**专业-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/major/page", param).then(res => {
        if (res.code == 200) { this.dataList = res.data.dataList; this.total = res.data.rowCount; this.loading = false; } else this.$message.error(res.msg);
      });
    },
     /**专业-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定",cancelButtonText: "取消",type: "warning" }).then(() => {
        this.rq.post("/major/delete", ids).then(res => {
          if (res.code == 200) { this.$message.success("删除成功"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
  },    
};
</script>
<style scoped lang="scss">

</style>

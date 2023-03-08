/*老师课程关系不分页列表,作者:张可可,日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <h3>老师课程关系-列表</h3>
    <hr />
    <el-form :inline="true">
      <el-form-item label="课程id" v-show="condition.courseId.show" ><el-input placeholder="请输入课程id" size="mini" v-model="form.courseId"></el-input></el-form-item>
      <el-form-item label="老师id" v-show="condition.teacherId.show" ><el-input placeholder="请输入老师id" size="mini" v-model="form.teacherId"></el-input></el-form-item>
      </div>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows)=>selectChange(rows,'id')">
      <el-table-column type="selection" width="40" v-if="column.choice.show" ></el-table-column>
      <el-table-column type="expand" v-if="column.detail.show" width="35">
        <template slot-scope="props">
          <label class="label">课程id</label>{{props.row.courseId}}
          <hr class="hr">
          <label class="label">老师id</label>{{props.row.teacherId}}
          <hr class="hr">
        </template>
      </el-table-column>
      <el-table-column prop="courseId" label="课程id" v-if="column.courseId.show" ></el-table-column>
      <el-table-column prop="teacherId" label="老师id" v-if="column.teacherId.show" ></el-table-column>
      <el-table-column label="操作" width="150" v-if="column.operate.show">
        <template slot-scope="props">
          <div><el-button type="text" size="mini" @click="doEdit(props.row)">编辑</el-button><el-button type="text" size="mini" @click="doDelete([props.row.id])">删除</el-button></div>
        </template>
      </el-table-column>
    </v-table>
    <CourseTeacherDialog ref="dialog" :loadData="loadData"></CourseTeacherDialog>
  </el-dialog>
</template>
<script>
import CourseTeacherDialog from './CourseTeacherDialog.vue';
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  components: { CourseTeacherDialog }, 
  data() { 
    return {
      column: { 
        choice: { show: true, text: "选择列" }, 
        detail: { show: true, text: "明细列" }, 
        courseId: {show: true, text: "课程id" },
        teacherId: {show: true, text: "老师id" },
        operate: { show: true, text: "操作列" }, 
      },
      condition: { 
        courseId: {show: true, text: "课程id" },
        teacherId: {show: true, text: "老师id" },
      },
    }; 
  },
  //computed: {}, mounted(){},
  created() { this.loadData(); },
  methods: {
    /**老师课程关系-查询参数*/
    initForm() {
      return {
        courseId: null,/*课程id*/
        teacherId: null,/*老师id*/
      };
    },
    /**老师课程关系-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form};
      this.rq.post("/courseteacher/list", param).then(res => {
        if (res.code == 200) this.dataList = res.data; else this.$message.error(res.msg);
      });
      this.loading = false;
    },
     /**老师课程关系-删除*/    
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定",cancelButtonText: "取消",type: "warning" }).then(() => {
        this.rq.post("/courseteacher/delete", ids).then(res => {
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

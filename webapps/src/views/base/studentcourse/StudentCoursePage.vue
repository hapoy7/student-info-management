/*学生课程关系分页列表,作者:张可可,日期:2023-03-08 23:42:07*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="学生id" v-show="condition.studentId.show"><el-input placeholder="请输入学生id" size="mini" v-model="form.studentId"></el-input></el-form-item>
      <el-form-item label="课程id" v-show="condition.courseId.show"><el-input placeholder="请输入课程id" size="mini" v-model="form.courseId"></el-input></el-form-item>
      </div>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows)=>selectChange(rows,'id')">
      <el-table-column type="selection" width="40" v-if="column.choice.show" ></el-table-column>
      <el-table-column type="expand" v-if="column.detail.show" width="35">
        <template slot-scope="props">
          <label class="label">学生id</label>{{props.row.studentId}}
          <hr class="hr">
          <label class="label">课程id</label>{{props.row.courseId}}
          <hr class="hr">
        </template>
      </el-table-column>
      <el-table-column prop="studentId" label="学生id" v-if="column.studentId.show" ></el-table-column>
      <el-table-column prop="courseId" label="课程id" v-if="column.courseId.show" ></el-table-column>
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
    <StudentCourseDialog ref="dialog" :loadData="loadData"></StudentCourseDialog>
  </div>
</template>
<script>
import StudentCourseDialog from './StudentCourseDialog';
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  components: { StudentCourseDialog },
  data() { 
    return {
      column: { 
        choice: { show: true, text: "选择列" }, 
        detail: { show: true, text: "明细列" }, 
        studentId: {show: true, text: "学生id" },
        courseId: {show: true, text: "课程id" },
        operate: { show: true, text: "操作列" }, 
      },
      condition: { 
        studentId: {show: true, text: "学生id" },
        courseId: {show: true, text: "课程id" },
      },
    }; 
  },
  //computed: {}, mounted(){},
  created() { this.loadData(); },
  methods: {
    /**学生课程关系-查询参数*/
    initForm() {
      return {
        studentId: null,/*学生id*/
        courseId: null,/*课程id*/
      };
    },
    /**学生课程关系-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/studentcourse/page", param).then(res => {
        if (res.code == 200) { this.dataList = res.data.dataList; this.total = res.data.rowCount; this.loading = false; } else this.$message.error(res.msg);
      });
    },
     /**学生课程关系-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定",cancelButtonText: "取消",type: "warning" }).then(() => {
        this.rq.post("/studentcourse/delete", ids).then(res => {
          if (res.code == 200) { this.$message.success("删除成功"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
  },    
};
</script>
<style scoped lang="scss">

</style>

/*考试安排分页列表,作者:张可可,日期:2023-03-08 23:42:07*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="课程名称" v-show="condition.examCourse.show"><el-input placeholder="请输入课程名称" size="mini" v-model="form.examCourse"></el-input></el-form-item>
      <el-form-item label="考试日期" v-show="condition.examDate.show">
        <el-date-picker v-model="form.examDate" format="yyyy-MM-dd" value-format="yyyy-MM-ddTHH:mm:ss" type="datetime" placeholder="请选择考试日期" size="mini" />
      </el-form-item>
      <el-form-item label="考试类型" v-show="condition.examType.show"> <v-select v-model="form.examType" placeholder="请选择考试类型" :dictKey="'examType'" :addBlank="true"/> </el-form-item>
      <el-form-item label="专业" v-show="condition.major.show"><el-input placeholder="请输入专业" size="mini" v-model="form.major"></el-input></el-form-item>
      <el-form-item>
        <el-button size="mini" @click="loadData">查询</el-button>
        <el-button size="mini" @click="doReset">重置</el-button>
        <el-button size="mini" @click="doAdd" type="primary">添加</el-button>
        <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0">删除</el-button>
        <v-columns :condition="condition" :column="column" component="ExamPage"></v-columns>
      </el-form-item>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows)=>selectChange(rows,'id')">
      <el-table-column type="selection" width="40" v-if="column.choice.show" ></el-table-column>
      <el-table-column prop="examDesc" label="考试介绍" v-if="column.examDesc.show" ></el-table-column>
      <el-table-column prop="examCourse" label="课程名称" v-if="column.examCourse.show" ></el-table-column>
      <el-table-column prop="examDate" label="考试日期" v-if="column.examDate.show" ></el-table-column>
      <el-table-column prop="totalTime" label="考试时长" v-if="column.totalTime.show" ></el-table-column>
      <el-table-column prop="major" label="专业" v-if="column.major.show" ></el-table-column>
      <el-table-column prop="institute" label="学院" v-if="column.institute.show" ></el-table-column>
      <el-table-column prop="totalScore" label="总分" v-if="column.totalScore.show" ></el-table-column>
      <el-table-column prop="examType" label="考试类型" v-if="column.examType.show" :formatter="examTypeFormat"></el-table-column>
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
    <ExamDialog ref="dialog" :loadData="loadData"></ExamDialog>
  </div>
</template>
<script>
import ExamDialog from './ExamDialog';
import { pageMix } from "@/common/page";
import { examTypeFormat } from '@/common/dicts';
export default {
  mixins: [pageMix],
  components: { ExamDialog },
  data() { 
    return {
      column: { 
        choice: { show: true, text: "选择列" }, 
        detail: { show: true, text: "明细列" }, 
        examDesc: {show: true, text: "考试介绍" },
        examCourse: {show: true, text: "课程名称" },
        examDate: {show: true, text: "考试日期" },
        totalTime: {show: true, text: "考试时长" },
        major: {show: true, text: "专业" },
        institute: {show: true, text: "学院" },
        totalScore: {show: true, text: "总分" },
        examType: {show: true, text: "考试类型" },
        operate: { show: true, text: "操作列" }, 
      },
      condition: { 
        examDesc: {show: true, text: "考试介绍" },
        examCourse: {show: true, text: "课程名称" },
        examDate: {show: true, text: "考试日期" },
        totalTime: {show: true, text: "考试时长" },
        major: {show: true, text: "专业" },
        totalScore: {show: true, text: "总分" },
        examType: {show: true, text: "考试类型" },
      },
    }; 
  },
  //computed: {}, mounted(){},
  created() { this.loadData(); },
  methods: {
    examTypeFormat,
    /**考试安排-查询参数*/
    initForm() {
      return {
        examDesc: null,/*考试介绍*/
        courseId: null,/*课程id*/
        examCourse: null,/*课程名称*/
        examDate: null,/*考试日期*/
        totalTime: null,/*考试时长*/
        major: null,/*专业*/
        institute: null,/*学院*/
        totalScore: null,/*总分*/
        examType: null,/*考试类型*/
      };
    },
    /**考试安排-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/exam/page", param).then(res => {
        if (res.code == 200) { this.dataList = res.data.dataList; this.total = res.data.rowCount; this.loading = false; } else this.$message.error(res.msg);
      });
    },
     /**考试安排-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定",cancelButtonText: "取消",type: "warning" }).then(() => {
        this.rq.post("/exam/delete", ids).then(res => {
          if (res.code == 200) { this.$message.success("删除成功"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
  },    
};
</script>
<style scoped lang="scss">

</style>

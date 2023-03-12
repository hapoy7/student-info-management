/*考试记录表分页列表,作者:张可可,日期:2023-03-08 23:42:07*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="考试安排ID" v-show="condition.examId.show"><el-input placeholder="请输入考试安排ID" size="mini" v-model="form.examId"></el-input></el-form-item>
      <el-form-item label="学生id" v-show="condition.studentId.show"><el-input placeholder="请输入学生id" size="mini" v-model="form.studentId"></el-input></el-form-item>
      <el-form-item label="参加考试的时间" v-show="condition.exemDate.show"><el-input placeholder="请输入参加考试的时间" size="mini" v-model="form.exemDate"></el-input></el-form-item>
      <el-form-item label="完成考试所用的时间,单位分钟" v-show="condition.exemTimeCost.show"><el-input placeholder="请输入完成考试所用的时间,单位分钟" size="mini" v-model="form.exemTimeCost"></el-input></el-form-item>
      <el-form-item>
        <el-button size="mini" @click="loadData">查询</el-button>
        <el-button size="mini" @click="doReset">重置</el-button>
        <el-button size="mini" @click="doAdd" type="primary">添加</el-button>
        <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0">删除</el-button>
        <el-button size="mini" @click="()=>allShow=!allShow" type="text">{{allShow?"▲":"▼"}}</el-button>
        <v-columns :condition="condition" :column="column" component="ExamRecordPage"></v-columns>
      </el-form-item>
      <div v-if="allShow">
      <el-form-item label="参与考试的实际得分" v-show="condition.exemScore.show"><el-input placeholder="请输入参与考试的实际得分" size="mini" v-model="form.exemScore"></el-input></el-form-item>
      <el-form-item label="考试结果的等级" v-show="condition.exemResultLevel.show"><el-input placeholder="请输入考试结果的等级" size="mini" v-model="form.exemResultLevel"></el-input></el-form-item>
      </div>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows)=>selectChange(rows,'id')">
      <el-table-column type="selection" width="40" v-if="column.choice.show" ></el-table-column>
      <el-table-column prop="examId" label="考试安排ID" v-if="column.examId.show" ></el-table-column>
      <el-table-column prop="studentId" label="学生id" v-if="column.studentId.show" ></el-table-column>
      <el-table-column prop="exemDate" label="参加考试的时间" v-if="column.exemDate.show" ></el-table-column>
      <el-table-column prop="exemTimeCost" label="完成考试所用的时间,单位分钟" v-if="column.exemTimeCost.show" ></el-table-column>
      <el-table-column prop="exemScore" label="参与考试的实际得分" v-if="column.exemScore.show" ></el-table-column>
      <el-table-column prop="exemResultLevel" label="考试结果的等级" v-if="column.exemResultLevel.show" ></el-table-column>
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
    <ExamRecordDialog ref="dialog" :loadData="loadData"></ExamRecordDialog>
  </div>
</template>
<script>
import ExamRecordDialog from './ExamRecordDialog';
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  components: { ExamRecordDialog },
  data() { 
    return {
      column: { 
        choice: { show: true, text: "选择列" }, 
        detail: { show: true, text: "明细列" }, 
        examId: {show: true, text: "考试安排ID" },
        studentId: {show: true, text: "学生id" },
        exemDate: {show: true, text: "参加考试的时间" },
        exemTimeCost: {show: true, text: "完成考试所用的时间,单位分钟" },
        exemScore: {show: true, text: "参与考试的实际得分" },
        exemResultLevel: {show: true, text: "考试结果的等级" },
        operate: { show: true, text: "操作列" }, 
      },
      condition: { 
        examId: {show: true, text: "考试安排ID" },
        studentId: {show: true, text: "学生id" },
        exemDate: {show: true, text: "参加考试的时间" },
        exemTimeCost: {show: true, text: "完成考试所用的时间,单位分钟" },
        exemScore: {show: true, text: "参与考试的实际得分" },
        exemResultLevel: {show: true, text: "考试结果的等级" },
      },
    }; 
  },
  //computed: {}, mounted(){},
  created() { this.loadData(); },
  methods: {
    /**考试记录表-查询参数*/
    initForm() {
      return {
        examId: null,/*考试安排ID*/
        studentId: null,/*学生id*/
        exemDate: null,/*参加考试的时间*/
        exemTimeCost: null,/*完成考试所用的时间,单位分钟*/
        exemScore: null,/*参与考试的实际得分*/
        exemResultLevel: null,/*考试结果的等级*/
      };
    },
    /**考试记录表-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/examrecord/page", param).then(res => {
        if (res.code == 200) { this.dataList = res.data.dataList; this.total = res.data.rowCount; this.loading = false; } else this.$message.error(res.msg);
      });
    },
     /**考试记录表-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定",cancelButtonText: "取消",type: "warning" }).then(() => {
        this.rq.post("/examrecord/delete", ids).then(res => {
          if (res.code == 200) { this.$message.success("删除成功"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
  },    
};
</script>
<style scoped lang="scss">

</style>

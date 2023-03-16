/*课程分页列表,作者:张可可,日期:2023-03-08 23:42:07*/
<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="课程名称" v-show="condition.name.show"><el-input placeholder="请输入课程名称" size="mini" v-model="form.name"></el-input></el-form-item>
      <el-form-item label="课程类型" v-show="condition.courseType.show"> <v-select v-model="form.courseType" placeholder="请选择课程类型" :dictKey="'courseType'" :addBlank="true"/> </el-form-item>
      <el-form-item label="学分" v-show="condition.credit.show"><el-input placeholder="请输入学分" size="mini" v-model="form.credit"></el-input></el-form-item>
      <el-form-item label='签到开始时间' v-show="condition.signStime.show">
        <el-date-picker v-model="form.signStime" format="yyyy-MM-dd" value-format="yyyy-MM-dd" type="date" placeholder="请选择签到开始时间" size="mini" />
      </el-form-item>
      <el-form-item>
        <el-button size="mini" @click="loadData">查询</el-button>
        <el-button size="mini" @click="doReset">重置</el-button>
        <el-button size="mini" @click="doAdd" type="primary">添加</el-button>
        <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0">删除</el-button>
        <el-button size="mini" @click="()=>allShow=!allShow" type="text">{{allShow?"▲":"▼"}}</el-button>
        <v-columns :condition="condition" :column="column" component="CoursePage"></v-columns>
      </el-form-item>
      <div v-if="allShow">
      <el-form-item label="签到结束时间" v-show="condition.signEtime.show">
        <el-date-picker v-model="form.signEtime" format="yyyy-MM-dd" value-format="yyyy-MM-dd" type="date" placeholder="请选择签到结束时间" size="mini" />
      </el-form-item>
      <el-form-item label="考试开始时间" v-show="condition.examStime.show">
        <el-date-picker v-model="form.examStime" format="yyyy-MM-dd" value-format="yyyy-MM-dd" type="date" placeholder="请选择考试开始时间" size="mini" />
      </el-form-item>
      <el-form-item label="考试结束时间" v-show="condition.examEtime.show">
        <el-date-picker v-model="form.examEtime" format="yyyy-MM-dd" value-format="yyyy-MM-dd" type="date" placeholder="请选择考试结束时间" size="mini" />
      </el-form-item>
      </div>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows)=>selectChange(rows,'id')">
      <el-table-column type="selection" width="40" v-if="column.choice.show" ></el-table-column>
      <el-table-column prop="name" label="课程名称" v-if="column.name.show" ></el-table-column>
      <el-table-column prop="courseType" label="课程类型" v-if="column.courseType.show" :formatter="courseTypeFormat"></el-table-column>
      <el-table-column prop="credit" label="学分" v-if="column.credit.show" ></el-table-column>
      <el-table-column prop="signStime" label="签到开始时间" v-if="column.signStime.show" ></el-table-column>
      <el-table-column prop="signEtime" label="签到结束时间" v-if="column.signEtime.show" ></el-table-column>
      <el-table-column prop="examStime" label="考试开始时间" v-if="column.examStime.show" ></el-table-column>
      <el-table-column prop="examEtime" label="考试结束时间" v-if="column.examEtime.show" ></el-table-column>
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
    <CourseDialog ref="dialog" :loadData="loadData"></CourseDialog>
  </div>
</template>
<script>
import CourseDialog from './CourseDialog';
import { pageMix } from "@/common/page";
import { courseTypeFormat } from "@/common/dicts";
export default {
  mixins: [pageMix],
  components: { CourseDialog },
  data() { 
    return {
      column: { 
        choice: { show: true, text: "选择列" }, 
        detail: { show: true, text: "明细列" }, 
        name: {show: true, text: "课程名称" },
        courseType: {show: true, text: "课程类型" },
        credit: {show: true, text: "学分" },
        signStime: {show: true, text: "签到开始时间" },
        signEtime: {show: true, text: "签到结束时间" },
        examStime: {show: true, text: "考试开始时间" },
        examEtime: {show: true, text: "考试结束时间" },
        operate: { show: true, text: "操作列" }, 
      },
      condition: { 
        name: {show: true, text: "课程名称" },
        courseType: {show: true, text: "课程类型" },
        credit: {show: true, text: "学分" },
        signStime: {show: true, text: "签到开始时间" },
        signEtime: {show: true, text: "签到结束时间" },
        examStime: {show: true, text: "考试开始时间" },
        examEtime: {show: true, text: "考试结束时间" },
      },
    }; 
  },
  //computed: {}, mounted(){},
  created() { this.loadData(); },
  methods: {
    courseTypeFormat,
    /**课程-查询参数*/
    initForm() {
      return {
        name: null,/*课程名称*/
        courseType: null,/*课程类型*/
        credit: null,/*学分*/
        signStime: null,/*签到开始时间*/
        signEtime: null,/*签到结束时间*/
        examStime: null,/*考试开始时间*/
        examEtime: null,/*考试结束时间*/
      };
    },
    /**课程-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("/course/page", param).then(res => {
        if (res.code == 200) { this.dataList = res.data.dataList; this.total = res.data.rowCount; this.loading = false; } else this.$message.error(res.msg);
      });
    },
     /**课程-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定",cancelButtonText: "取消",type: "warning" }).then(() => {
        this.rq.post("/course/delete", ids).then(res => {
          if (res.code == 200) { this.$message.success("删除成功"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
  },    
};
</script>
<style scoped lang="scss">

</style>

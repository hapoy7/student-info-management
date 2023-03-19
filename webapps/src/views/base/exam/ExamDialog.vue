/*考试安排新增与修改,  作者:张可可,  日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='考试介绍' prop='examDesc'><el-input placeholder='请输入考试介绍' v-model='form.examDesc' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='课程名称' prop='examCourse'>
            <el-select size="mini" :value="courseValue"  @input="change($event)" v-model='form.examCourse'>
              <el-option label="--请选择--" value=""></el-option>
              <el-option v-for="item in courseList" :key="item.id" :label="item.name" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="考试日期" prop='examDate'>
            <el-date-picker v-model="form.examDate" format="yyyy-MM-dd" value-format="yyyy-MM-ddTHH:mm:ss" type="datetime" placeholder="请选择考试日期" size="mini" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='考试时长' prop='totalTime'><el-input placeholder='请输入考试时长' v-model='form.totalTime' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='专业' prop='major'>
            <el-select size="mini" :value="majorValue"  @input="change3($event)" v-model='form.major'>
              <el-option label="--请选择--" value=""></el-option>
              <el-option v-for="item in majorList" :key="item.id" :label="item.name" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='学院' prop='institute'>
            <el-select size="mini" :value="instituteValue"  @input="change2($event)" v-model='form.institute'>
              <el-option label="--请选择--" value=""></el-option>
              <el-option v-for="item in instituteList" :key="item.id" :label="item.name" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='总分' prop='totalScore'><el-input placeholder='请输入总分' v-model='form.totalScore' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="考试类型"  prop='examType'> <v-select v-model="form.examType" placeholder="请选择考试类型" :dictKey="'examType'" size="mini"/> </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div style="text-align: right"><el-button @click="show = false" size="mini" >取消</el-button><el-button type="primary" @click="save()" size="mini">确定</el-button></div>
  </el-dialog>
</template>
<script>
export default {
  props: ["loadData"],
  components: {},
  data() {
    return {
      title: '',
      form: this.initForm(),
      dialogMode: "save",
      show: false,
      rules: {
        examDesc :[{required: true, message: '请输入考试介绍', trigger: 'blur'}],
        courseId :[{required: true, message: '请输入课程id', trigger: 'blur'}],
        examCourse :[{required: true, message: '请输入课程名称', trigger: 'blur'}],
        examDate :[{required: true, message: '请输入考试日期', trigger: 'blur'}],
        totalTime :[{required: true, message: '请输入考试时长', trigger: 'blur'}],
        major :[{required: true, message: '请输入专业', trigger: 'blur'}],
        institute :[{required: true, message: '请输入学院', trigger: 'blur'}],
        totalScore :[{required: true, message: '请输入总分', trigger: 'blur'}],
        examType :[{required: true, message: '请输入考试类型', trigger: 'blur'}],
      },
      courseList: null,
      courseValue: null,
      instituteList: null,
      instituteValue: null,
      majorValue: null,
      majorList: null,
    }
  },
  methods: {
    /*考试安排-保存*/
    save() {
      this.$refs['form'].validate((valid) => {
        if (!valid) return;
        this.rq.post("/exam/" + this.dialogMode, this.form).then(res => {
          if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
    /*考试安排-初始数据*/     
    initForm() {
      return {
        id : null,/*id*/
        examDesc : null,/*考试介绍*/
        courseId : null,/*课程id*/
        examCourse : null,/*课程名称*/
        examDate : null,/*考试日期*/
        totalTime : null,/*考试时长*/
        major : null,/*专业*/
        institute : null,/*学院*/
        totalScore : null,/*总分*/
        examType : null,/*考试类型*/
      }
    },
    /*考试安排-新增*/
    addDialog() { this.title = "新增考试安排"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; this.queryInstituteList();},
    /*考试安排-修改*/
    editDialog(row) { this.title = "修改考试安排"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
    /* 查询学院相关信息 */
    change: function (val) {
      this.courseValue = val;
    },
    queryInstituteList () {
      const param = { dr: 0, page: 1, size: 9999 };
      this.rq.post("/course/page", param).then(res => {
        if (res.code == 200) { 
          this.courseList = res.data.dataList;
        } else this.$message.error(res.msg);
      });
      this.rq.post("/institute/page", param).then(res => {
        if (res.code == 200) { 
          this.instituteList = res.data.dataList;
        } else this.$message.error(res.msg);
      });
      this.rq.post("/major/page", param).then(res => {
        if (res.code == 200) { 
          this.majorList = res.data.dataList;
        } else this.$message.error(res.msg);
      });
    },
     /* 查询学院相关信息 */
     change2: function (val) {
      this.instituteValue = val;
    },
    /* 查询专业相关信息 */
     change3: function (val) {
      this.majorValue = val;
    },
  },
}
</script>
<style scoped lang="scss"></style>

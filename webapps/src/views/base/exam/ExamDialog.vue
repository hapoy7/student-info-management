/*考试安排新增与修改,  作者:张可可,  日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='考试介绍' prop='examDesc'><el-input placeholder='请输入考试介绍' v-model='form.examDesc' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='课程id' prop='courseId'><el-input placeholder='请输入课程id' v-model='form.courseId' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='课程名称' prop='examCourse'><el-input placeholder='请输入课程名称' v-model='form.examCourse' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='考试日期' prop='examDate'><el-input placeholder='请输入考试日期' v-model='form.examDate' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='考试时长' prop='totalTime'><el-input placeholder='请输入考试时长' v-model='form.totalTime' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='专业' prop='major'><el-input placeholder='请输入专业' v-model='form.major' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='学院' prop='institute'><el-input placeholder='请输入学院' v-model='form.institute' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='总分' prop='totalScore'><el-input placeholder='请输入总分' v-model='form.totalScore' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='考试类型' prop='examType'><el-input placeholder='请输入考试类型' v-model='form.examType' size="mini" /></el-form-item>
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
      }
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
    addDialog() { this.title = "新增考试安排"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; },
    /*考试安排-修改*/
    editDialog(row) { this.title = "修改考试安排"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
  },
}
</script>
<style scoped lang="scss"></style>

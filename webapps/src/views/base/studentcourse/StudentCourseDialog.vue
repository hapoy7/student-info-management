/*学生课程关系新增与修改,  作者:张可可,  日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='学生id' prop='studentId'><el-input placeholder='请输入学生id' v-model='form.studentId' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='课程id' prop='courseId'><el-input placeholder='请输入课程id' v-model='form.courseId' size="mini" /></el-form-item>
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
        studentId :[{required: true, message: '请输入学生id', trigger: 'blur'}],
        courseId :[{required: true, message: '请输入课程id', trigger: 'blur'}],
      }
    }
  },
  methods: {
    /*学生课程关系-保存*/
    save() {
      this.$refs['form'].validate((valid) => {
        if (!valid) return;
        this.rq.post("/studentcourse/" + this.dialogMode, this.form).then(res => {
          if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
    /*学生课程关系-初始数据*/     
    initForm() {
      return {
        id : null,/*主键id*/
        studentId : null,/*学生id*/
        courseId : null,/*课程id*/
      }
    },
    /*学生课程关系-新增*/
    addDialog() { this.title = "新增学生课程关系"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; },
    /*学生课程关系-修改*/
    editDialog(row) { this.title = "修改学生课程关系"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
  },
}
</script>
<style scoped lang="scss"></style>

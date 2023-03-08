/*考试记录表新增与修改,  作者:张可可,  日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='考试安排ID' prop='examId'><el-input placeholder='请输入考试安排ID' v-model='form.examId' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='学生id' prop='studentId'><el-input placeholder='请输入学生id' v-model='form.studentId' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='参加考试的时间' prop='exemDate'><el-input placeholder='请输入参加考试的时间' v-model='form.exemDate' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='完成考试所用的时间,单位分钟' prop='exemTimeCost'><el-input placeholder='请输入完成考试所用的时间,单位分钟' v-model='form.exemTimeCost' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='参与考试的实际得分' prop='exemScore'><el-input placeholder='请输入参与考试的实际得分' v-model='form.exemScore' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='考试结果的等级' prop='exemResultLevel'><el-input placeholder='请输入考试结果的等级' v-model='form.exemResultLevel' size="mini" /></el-form-item>
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
        examId :[{required: true, message: '请输入考试安排ID', trigger: 'blur'}],
        studentId :[{required: true, message: '请输入学生id', trigger: 'blur'}],
        exemDate :[{required: true, message: '请输入参加考试的时间', trigger: 'blur'}],
        exemTimeCost :[{required: true, message: '请输入完成考试所用的时间,单位分钟', trigger: 'blur'}],
        exemScore :[{required: true, message: '请输入参与考试的实际得分', trigger: 'blur'}],
        exemResultLevel :[{required: true, message: '请输入考试结果的等级', trigger: 'blur'}],
      }
    }
  },
  methods: {
    /*考试记录表-保存*/
    save() {
      this.$refs['form'].validate((valid) => {
        if (!valid) return;
        this.rq.post("/examrecord/" + this.dialogMode, this.form).then(res => {
          if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
    /*考试记录表-初始数据*/     
    initForm() {
      return {
        id : null,/*考试记录表的主键*/
        examId : null,/*考试安排ID*/
        studentId : null,/*学生id*/
        exemDate : null,/*参加考试的时间*/
        exemTimeCost : null,/*完成考试所用的时间,单位分钟*/
        exemScore : null,/*参与考试的实际得分*/
        exemResultLevel : null,/*考试结果的等级*/
      }
    },
    /*考试记录表-新增*/
    addDialog() { this.title = "新增考试记录表"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; },
    /*考试记录表-修改*/
    editDialog(row) { this.title = "修改考试记录表"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
  },
}
</script>
<style scoped lang="scss"></style>

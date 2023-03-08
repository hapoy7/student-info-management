/*课程新增与修改,  作者:张可可,  日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='课程名称' prop='name'><el-input placeholder='请输入课程名称' v-model='form.name' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='课程类型' prop='courseType'><el-input placeholder='请输入课程类型' v-model='form.courseType' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='学分' prop='credit'><el-input placeholder='请输入学分' v-model='form.credit' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='签到开始时间' prop='signStime'><el-input placeholder='请输入签到开始时间' v-model='form.signStime' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='签到结束时间' prop='signEtime'><el-input placeholder='请输入签到结束时间' v-model='form.signEtime' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='考试开始时间' prop='examStime'><el-input placeholder='请输入考试开始时间' v-model='form.examStime' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='考试结束时间' prop='examEtime'><el-input placeholder='请输入考试结束时间' v-model='form.examEtime' size="mini" /></el-form-item>
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
        name :[{required: true, message: '请输入课程名称', trigger: 'blur'}],
        courseType :[{required: true, message: '请输入课程类型', trigger: 'blur'}],
        credit :[{required: true, message: '请输入学分', trigger: 'blur'}],
        signStime :[{required: true, message: '请输入签到开始时间', trigger: 'blur'}],
        signEtime :[{required: true, message: '请输入签到结束时间', trigger: 'blur'}],
        examStime :[{required: true, message: '请输入考试开始时间', trigger: 'blur'}],
        examEtime :[{required: true, message: '请输入考试结束时间', trigger: 'blur'}],
      }
    }
  },
  methods: {
    /*课程-保存*/
    save() {
      this.$refs['form'].validate((valid) => {
        if (!valid) return;
        this.rq.post("/course/" + this.dialogMode, this.form).then(res => {
          if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
    /*课程-初始数据*/     
    initForm() {
      return {
        id : null,/*主键id*/
        name : null,/*课程名称*/
        courseType : null,/*课程类型*/
        credit : null,/*学分*/
        signStime : null,/*签到开始时间*/
        signEtime : null,/*签到结束时间*/
        examStime : null,/*考试开始时间*/
        examEtime : null,/*考试结束时间*/
      }
    },
    /*课程-新增*/
    addDialog() { this.title = "新增课程"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; },
    /*课程-修改*/
    editDialog(row) { this.title = "修改课程"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
  },
}
</script>
<style scoped lang="scss"></style>

/*学生新增与修改,  作者:张可可,  日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='用户id' prop='userId'><el-input placeholder='请输入用户id' v-model='form.userId' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='学号' prop='studentNumber'><el-input placeholder='请输入学号' v-model='form.studentNumber' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='班级id' prop='clazzId'><el-input placeholder='请输入班级id' v-model='form.clazzId' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='学生姓名' prop='name'><el-input placeholder='请输入学生姓名' v-model='form.name' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='性别' prop='sex'><el-input placeholder='请输入性别' v-model='form.sex' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='年龄' prop='age'><el-input placeholder='请输入年龄' v-model='form.age' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='联系电话' prop='phone'><el-input placeholder='请输入联系电话' v-model='form.phone' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='邮箱' prop='email'><el-input placeholder='请输入邮箱' v-model='form.email' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='头像地址' prop='photoUrl'><el-input placeholder='请输入头像地址' v-model='form.photoUrl' size="mini" /></el-form-item>
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
        userId :[{required: true, message: '请输入用户id', trigger: 'blur'}],
        studentNumber :[{required: true, message: '请输入学号', trigger: 'blur'}],
        clazzId :[{required: true, message: '请输入班级id', trigger: 'blur'}],
        name :[{required: true, message: '请输入学生姓名', trigger: 'blur'}],
        sex :[{required: true, message: '请输入性别', trigger: 'blur'}],
        age :[{required: true, message: '请输入年龄', trigger: 'blur'}],
        phone :[{required: true, message: '请输入联系电话', trigger: 'blur'}],
        email :[{required: true, message: '请输入邮箱', trigger: 'blur'}],
        photoUrl :[{required: true, message: '请输入头像地址', trigger: 'blur'}],
      }
    }
  },
  methods: {
    /*学生-保存*/
    save() {
      this.$refs['form'].validate((valid) => {
        if (!valid) return;
        this.rq.post("/student/" + this.dialogMode, this.form).then(res => {
          if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
    /*学生-初始数据*/     
    initForm() {
      return {
        id : null,/*主键id*/
        userId : null,/*用户id*/
        studentNumber : null,/*学号*/
        clazzId : null,/*班级id*/
        name : null,/*学生姓名*/
        sex : null,/*性别*/
        age : null,/*年龄*/
        phone : null,/*联系电话*/
        email : null,/*邮箱*/
        photoUrl : null,/*头像地址*/
      }
    },
    /*学生-新增*/
    addDialog() { this.title = "新增学生"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; },
    /*学生-修改*/
    editDialog(row) { this.title = "修改学生"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
  },
}
</script>
<style scoped lang="scss"></style>

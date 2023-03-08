/*班级新增与修改,  作者:张可可,  日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='班级名称' prop='name'><el-input placeholder='请输入班级名称' v-model='form.name' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='专业id' prop='majorId'><el-input placeholder='请输入专业id' v-model='form.majorId' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='学院id' prop='instituteId'><el-input placeholder='请输入学院id' v-model='form.instituteId' size="mini" /></el-form-item>
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
        name :[{required: true, message: '请输入班级名称', trigger: 'blur'}],
        majorId :[{required: true, message: '请输入专业id', trigger: 'blur'}],
        instituteId :[{required: true, message: '请输入学院id', trigger: 'blur'}],
      }
    }
  },
  methods: {
    /*班级-保存*/
    save() {
      this.$refs['form'].validate((valid) => {
        if (!valid) return;
        this.rq.post("/clazz/" + this.dialogMode, this.form).then(res => {
          if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
    /*班级-初始数据*/     
    initForm() {
      return {
        id : null,/*主键id*/
        name : null,/*班级名称*/
        majorId : null,/*专业id*/
        instituteId : null,/*学院id*/
      }
    },
    /*班级-新增*/
    addDialog() { this.title = "新增班级"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; },
    /*班级-修改*/
    editDialog(row) { this.title = "修改班级"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
  },
}
</script>
<style scoped lang="scss"></style>

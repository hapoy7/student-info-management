/*学院新增与修改,  作者:张可可,  日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='学院名称' prop='name'><el-input placeholder='请输入学院名称' v-model='form.name' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='学院编号' prop='instituteNumber'><el-input placeholder='请输入学院编号' v-model='form.instituteNumber' size="mini" /></el-form-item>
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
        name :[{required: true, message: '请输入学院名称', trigger: 'blur'}],
        instituteNumber :[{required: true, message: '请输入学院编号', trigger: 'blur'}],
      }
    }
  },
  methods: {
    /*学院-保存*/
    save() {
      this.$refs['form'].validate((valid) => {
        if (!valid) return;
        this.rq.post("/institute/" + this.dialogMode, this.form).then(res => {
          if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
    /*学院-初始数据*/     
    initForm() {
      return {
        id : null,/*主键id*/
        name : null,/*学院名称*/
        instituteNumber : null,/*学院编号*/
      }
    },
    /*学院-新增*/
    addDialog() { this.title = "新增学院"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; },
    /*学院-修改*/
    editDialog(row) { this.title = "修改学院"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
  },
}
</script>
<style scoped lang="scss"></style>

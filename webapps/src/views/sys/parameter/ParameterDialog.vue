/*系统参数新增与修改,  作者:高振中,  日期:2021-04-11 00:29:26*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='组名称' prop='groupName'><el-input placeholder='请输入组名称' v-model='form.groupName' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='参数名称' prop='paramName'><el-input placeholder='请输入参数名称' v-model='form.paramName' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='参数键' prop='paramKey'><el-input placeholder='请输入参数键' v-model='form.paramKey' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='参数值' prop='paramValue'><el-input placeholder='请输入参数值' v-model='form.paramValue' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label='备注' prop='remark'><el-input type="textarea" placeholder='请输入备注' v-model='form.remark' size="mini" style="width:540px"/></el-form-item>
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
        paramName :[{required: true, message: '请输入参数名称', trigger: 'blur'}],
        paramKey :[{required: true, message: '请输入参数键', trigger: 'blur'}],
        paramValue :[{required: true, message: '请输入参数值', trigger: 'blur'}],
      }
    }
  },
  methods: {
    /*系统参数-保存*/
    save() {
      this.$refs['form'].validate((valid) => {
        if (!valid) return;
        this.rq.post("/parameter/" + this.dialogMode, this.form).then((res) => {
          if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
    /*系统参数-初始数据*/     
    initForm() {
      return {
        id : null,/*主键*/
        groupName : null,/*组名称*/
        paramName : null,/*参数名称*/
        paramKey : null,/*参数键*/
        paramValue : null,/*参数值*/
        remark : null,/*备注*/
      }
    },
    /*系统参数-新增*/
    addDialog() { this.title = "新增系统参数"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; },
    /*系统参数-修改*/
    editDialog(row) { this.title = "修改系统参数"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
  },
}
</script>
<style scoped lang="scss"></style>

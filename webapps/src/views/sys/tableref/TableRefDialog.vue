/*表引用关系新增与修改,  作者:高振中,  日期:2022-10-12 12:10:59*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='主表表名' prop='mainTable'><el-input placeholder='请输入主表表名' v-model='form.mainTable' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='主表描述' prop='mainInfo'><el-input placeholder='请输入主表描述' v-model='form.mainInfo' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='子表表名' prop='subTable'><el-input placeholder='请输入子表表名' v-model='form.subTable' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='子表描述' prop='subInfo'><el-input placeholder='请输入子表描述' v-model='form.subInfo' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='外键名称' prop='refId'><el-input placeholder='请输入外键名称' v-model='form.refId' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='删除标记' prop='delFlag'><el-input placeholder='请输入删除标记' v-model='form.delFlag' size="mini" /></el-form-item>
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
        mainTable :[{required: true, message: '请输入主表表名', trigger: 'blur'}],
        mainInfo :[{required: true, message: '请输入主表描述', trigger: 'blur'}],
        subTable :[{required: true, message: '请输入子表表名', trigger: 'blur'}],
        subInfo :[{required: true, message: '请输入子表描述', trigger: 'blur'}],
        refId :[{required: true, message: '请输入外键名称', trigger: 'blur'}],
      }
    }
  },
  methods: {
    /*表引用关系-保存*/
    save() {
      this.$refs['form'].validate((valid) => {
        if (!valid) return;
        this.rq.post("/tableRef/" + this.dialogMode, this.form).then((res) => {
          if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
    /*表引用关系-初始数据*/     
    initForm() {
      return {
        id : null,/*主键*/
        mainTable : null,/*主表表名*/
        mainInfo : null,/*主表描述*/
        subTable : null,/*子表表名*/
        subInfo : null,/*子表描述*/
        refId : null,/*引表外键名称*/
        delFlag : null,/*删除标记字段名*/
      }
    },
    /*表引用关系-新增*/
    addDialog() { this.title = "新增表引用关系"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; },
    /*表引用关系-修改*/
    editDialog(row) { this.title = "修改表引用关系"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
  },
}
</script>
<style scoped lang="scss"></style>

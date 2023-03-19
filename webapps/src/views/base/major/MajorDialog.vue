/*专业新增与修改,  作者:张可可,  日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='专业名称' prop='name'><el-input placeholder='请输入专业名称' v-model='form.name' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='专业编号' prop='majorNumber'><el-input placeholder='请输入专业编号' v-model='form.majorNumber' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='学院' prop='instituteId'>
            <el-select size="mini" :value="instituteValue"  @input="change2($event)" v-model='form.instituteId'>
              <el-option label="--请选择--" value=""></el-option>
              <el-option v-for="item in instituteList" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
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
        name :[{required: true, message: '请输入专业名称', trigger: 'blur'}],
        majorNumber :[{required: true, message: '请输入专业编号', trigger: 'blur'}],
        instituteId :[{required: true, message: '请输入学院id', trigger: 'blur'}],
      },
      instituteList: null,
      instituteValue: null,
    }
  },
  methods: {
    /*专业-保存*/
    save() {
      this.$refs['form'].validate((valid) => {
        if (!valid) return;
        this.rq.post("/major/" + this.dialogMode, this.form).then(res => {
          if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
    /*专业-初始数据*/     
    initForm() {
      return {
        id : null,/*主键id*/
        name : null,/*专业名称*/
        majorNumber : null,/*专业编号*/
        instituteId : null,/*学院id*/
      }
    },
    /*专业-新增*/
    addDialog() { this.title = "新增专业"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; this.queryMajorList();},
    /*专业-修改*/
    editDialog(row) { this.title = "修改专业"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
     /* 查询学院相关信息 */
     change2: function (val) {
      this.majorValue = val;
    },
    queryMajorList () {
      const param = { dr: 0, page: 1, size: 9999 };
      this.rq.post("/institute/page", param).then(res => {
        if (res.code == 200) { 
          this.instituteList = res.data.dataList;
        } else this.$message.error(res.msg);
      });
    },
  },
}
</script>
<style scoped lang="scss"></style>

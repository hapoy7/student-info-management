/*角色新增与修改,作者:高振中,日期:2021-02-20 11:34:58*/
<template>
  <el-dialog :title="title" width="500px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="角色名称" prop="name"> <el-input placeholder="请输入角色名称" v-model="form.name" size="small" /> </el-form-item>
          <el-form-item label="排序" prop="orderNum"> <el-input placeholder="请输入排序" v-model="form.orderNum" size="small" /> </el-form-item>
          <el-form-item label="类型" prop="type"> <el-input placeholder="请输入类型" v-model="form.type" size="small" /> </el-form-item>
          <el-form-item label="备注" prop="remark"> <el-input type="textarea" placeholder="请输入备注" v-model="form.remark" size="small" /> </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div style="text-align: right"><el-button @click="show = false" size="small">取消</el-button> <el-button type="primary" @click="save()" size="small">确定</el-button></div>
  </el-dialog>
</template>
<script>
export default {
  props: ["loadData"],
  data() {
    return {
      title: "", form: this.initForm(), dialogMode: "save", show: false,
      rules: {
        name: [{ required: true, message: "请输入角色名称", trigger: "blur" }],
        type: [{ required: true, message: "请输入角色类型", trigger: "blur" }],
      },
    };
  },
  methods: {
    /*角色-保存*/
    save() {
      this.$refs["form"].validate((valid) => {
        if (!valid) return;
        this.rq.post("/role/" + this.dialogMode, this.form).then((res) => { if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg); });
      });
    },
    /*角色-初始数据*/
    initForm() { return { roleId: null, /*主键*/ name: null, /*角色名称*/ remark: null, /*备注*/ orderNum: null, /*排序*/ createTime: null, /*创建时间*/ }; },
    /*角色-新增*/
    addDialog() { this.title = "新增角色"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; },
    /*角色-修改*/
    editDialog(row) { this.title = "修改角色"; this.dialogMode = "update"; this.form = { ...row }; this.show = true; },
  },
};
</script>
<style scoped lang="scss"></style>

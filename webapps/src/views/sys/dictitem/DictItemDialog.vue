/*字典项新增与修改,作者:高振中,日期:2021-02-19 17:18:03*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false" width="600px" :modal="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="字典项文本" prop="text"> <el-input placeholder="请输入字典项文本" v-model="form.text" size="mini" /> </el-form-item>
          <el-form-item label="字典项值" prop="value"> <el-input placeholder="请输入字典项值" v-model="form.value" size="mini" /> </el-form-item>
          <el-form-item label="排序编号" prop="orderNum"> <el-input placeholder="请输入排序编号" v-model="form.orderNum" size="mini" /> </el-form-item>
          <el-form-item label="备注" prop="remark"> <el-input type="textarea" placeholder="请输入备注" v-model="form.remark" size="mini" /> </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div style="text-align: right"><el-button @click="show = false" size="mini">取消</el-button> <el-button type="primary" @click="save()" size="mini">确定</el-button></div>
  </el-dialog>
</template>
<script>
export default {
  props: ["loadData"],
  components: {},
  data() {
    return {
      title: "",
      form: this.initForm(),
      dialogMode: "save",
      show: false,
      rules: { text: [{ required: true, message: "请输入字典项文本", trigger: "blur" }], value: [{ required: true, message: "请输入字典项值", trigger: "blur" }] },
    };
  },
  methods: {
    /**字典项-保存*/
    save() {
      this.$refs["form"].validate((valid) => {
        if (!valid) return;
        this.rq.post("/dictItem/" + this.dialogMode, this.form).then((res) => {
          if (res.code == 200) {
            this.show = false;
            this.$message.success(this.title + "成功!");
            this.loadData();
          } else this.$message.error(res.msg);
        });
      });
    },
    //字典项-初始数据
    initForm() {
      return { id: null, /*主键*/ dictId: null, /*字典主键*/ text: null, /*字典项文本*/ value: null, /*字典项值*/ remark: null, /*备注*/ orderNum: null /*排序编号*/ };
    },
    //字典项-新增
    addDialog(dictId) {
      this.title = "新增字典项";
      this.dialogMode = "save";
      this.form = this.initForm();
      this.form.dictId = dictId;
      this.show = true;
    },
    //字典项-修改
    editDialog(row) {
      this.title = "修改字典项";
      this.dialogMode = "update";
      this.form = { ...row };
      this.show = true;
    },
  },
};
</script>
<style scoped lang="scss"></style>

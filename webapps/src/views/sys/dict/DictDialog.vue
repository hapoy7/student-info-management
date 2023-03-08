/*字典新增与修改,作者:高振中,日期:2021-02-19 17:18:03*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false"
    width="500px">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="字典名称" prop="name">
            <el-input placeholder="请输入字典名称" v-model="form.name" size="mini" />
          </el-form-item>
          <el-form-item label="字典编码" prop="code">
            <el-input placeholder="请输入字典编码" v-model="form.code" size="mini" />
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" placeholder="请输入备注" v-model="form.remark" size="mini" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div style="text-align: right">
      <el-button @click="show = false" size="mini">取消</el-button>
      <el-button type="primary" @click="save()" size="mini">确定</el-button>
    </div>
  </el-dialog>
</template>
<script>
export default {
  props: ["loadData"],
  data() {
    return {
      title: "",
      form: this.initForm(),
      dialogMode: "save",
      show: false,
      rules: {
        name: [{ required: true, message: "请输入字典名称", trigger: "blur" }],
        code: [{ required: true, message: "请输入字典编码", trigger: "blur" }]
      }
    };
  },
  methods: {
    //字典-保存
    save() {
      this.$refs["form"].validate((valid) => {
        if (!valid) return;
        this.rq.post("/dict/" + this.dialogMode, this.form).then((res) => {
          if (res.code == 200) {
            this.show = false;
            this.$message.success(this.title + "成功!");
            this.loadData();
          } else this.$message.error(res.msg);
        });
      });
    },
    //字典-初始数据
    initForm() {
      return {
        id: null, /*主键*/
        name: null, /*字典名称*/
        code: null, /*字典编码*/
        remark: null /*备注*/
      };
    },
    //字典-新增
    addDialog() {
      this.title = "新增字典";
      this.dialogMode = "save";
      this.form = this.initForm();
      this.show = true;
    },
    //字典-修改
    editDialog(row) {
      this.title = "修改字典";
      this.dialogMode = "update";
      this.form = { ...row };
      this.show = true;
    },
  },
};
</script>
<style scoped lang="scss">

</style>

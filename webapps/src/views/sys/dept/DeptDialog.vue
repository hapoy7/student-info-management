/*组织机构新增与修改,作者:高振中,日期:2020-11-15 21:33:20*/
<template>
  <el-dialog :title="title" :visible.sync="show" width="400px">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="机构名称" prop="name"> <el-input placeholder="请输入机构名称" v-model="form.name" size="mini" /> </el-form-item>
          <el-form-item label="机构类型" prop="type">
            <el-select v-model="form.type" placeholder="请选择机构类型" size="mini"> <el-option v-for="item in deptTypes" :key="item.value" :label="item.text" :value="item.value"></el-option> </el-select>
          </el-form-item>
          <el-form-item label="机构编码" prop="code"> <el-input placeholder="请输入机构编码" v-model="form.code" size="mini" /> </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="form.status" placeholder="请选择状态" size="mini"> <el-option v-for="item in status" :key="item.value" :label="item.text" :value="item.value"></el-option> </el-select>
          </el-form-item>
          <el-form-item label="排序编号" prop="orderNum"> <el-input placeholder="请输入排序编号" v-model="form.orderNum" size="mini" /> </el-form-item>
          <el-form-item label="备注" prop="remark"> <el-input placeholder="请输入备注" type="textarea" v-model="form.remark" size="mini" /> </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div style="text-align: right"><el-button @click="show = false" size="mini">取消</el-button> <el-button type="primary" @click="save()" size="mini">确定</el-button></div>
  </el-dialog>
</template>
<script>
import { getDict } from "@/common/dicts";
export default {
  props: ["queryTree"],
  data() {
    return {
      status: getDict("status"),
      deptTypes: getDict("deptType"),
      title: "",
      form: this.initForm(),
      dialogMode: "save",
      show: false,
      rules: {
        parentId: [{ required: true, message: "请输入上级ID", trigger: "blur" }],
        name: [{ required: true, message: "请输入机构名称", trigger: "blur" }],
        type: [{ required: true, message: "请输入机构类型", trigger: "blur" }],
        code: [{ required: true, message: "请输入机构编码", trigger: "blur" }],
        status: [{ required: true, message: "请输入状态", trigger: "blur" }],
      },
    };
  },
  methods: {
    /**组织机构-保存*/
    save() {
      this.$refs["form"].validate((valid) => {
        if (!valid) return;
        this.rq.post("/dept/" + this.dialogMode, this.form).then((res) => {
          if (res.code == 200) {
            this.show = false;
            this.$message.success(this.title + "成功!");
            this.queryTree();
          } else this.$message.error(res.msg);
        });
      });
    },
    /**组织机构-初始数据*/
    initForm() {
      return { id: null, /*ID*/ parentId: null, /*上级ID*/ name: null, /*机构名称*/ type: null, /*机构类型 1单位 2部门*/ code: null, /*机构编码*/ status: 1, /*状态(1启用，0不启用)*/ orderNum: null, /*排序编号*/ leaf: 1 /*叶子*/, remark: null };
    },
    /**组织机构-新增*/
    addDialog(parentId) {
      this.title = "新增组织机构";
      this.dialogMode = "save";
      this.form = { ...this.initForm(), parentId: parentId };
      this.show = true;
    },
    /**组织机构-修改*/
    editDialog(row) {
      this.title = "修改组织机构";
      this.dialogMode = "update";
      this.form = { ...row };
      this.show = true;
    },
  },
};
</script>
<style scoped lang="scss"></style>

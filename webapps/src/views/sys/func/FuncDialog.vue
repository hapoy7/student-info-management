/*功能新增与修改,作者:高振中,日期:2020-11-15 00:25:11*/
<template>
  <el-dialog :title="title" :visible.sync="show" width="400px">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="类型" prop="name"> 
            <v-select v-model="form.type" placeholder="请选择类型" :dictKey="'funcType'" :addBlank="false"></v-select>
          </el-form-item> 
          <el-form-item label="名称" prop="name"> <el-input placeholder="请输入名称" v-model="form.name" size="mini" /> </el-form-item> 
          <el-form-item label="图标" prop="icon"> <el-input placeholder="请输入图标" v-model="form.icon" size="mini" /> </el-form-item>
          <el-form-item label="组件路径" prop="component"> <el-input placeholder="请输入组件路径" v-model="form.component" size="mini" /> </el-form-item>
          <el-form-item label="状态" prop="status">
            <v-select v-model="form.status" placeholder="请选择状态" :dictKey="'status'"></v-select>
          </el-form-item>
          <el-form-item label="排序" prop="orders"> <el-input placeholder="请输入排序" v-model="form.orders" size="mini" /> </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div style="text-align: right"><el-button @click="show = false" size="mini">取消</el-button> <el-button type="primary" @click="save()" size="mini">确定</el-button></div>
  </el-dialog>
</template>
<script>
export default {
  props: ["queryTree"],
  data() {
    return {
      title: "",
      form: this.initForm(),
      dialogMode: "save",
      show: false,
      rules: { 
        name: [{ required: true, message: "请输入名称", trigger: "blur" }], 
        path: [{ required: true, message: "请输入路径", trigger: "blur" }], 
        icon: [{ required: true, message: "请输入图标", trigger: "blur" }] },
    };
  },
  methods: {
    /*功能菜单-保存*/
    save() {
      this.$refs["form"].validate((valid) => {
        if (!valid) return;
        this.form.path = this.form.component == "" ? "" : "/" + this.form.component;
        this.rq.post("/func/" + this.dialogMode, this.form).then((res) => {
          if (res.code == 200) {
            this.show = false;
            this.$message.success(this.title + "成功!");
            this.queryTree();
          } else this.$message.error(res.msg);
        });
      });
    },
    /*功能菜单-初始数据*/
    initForm() {
      return {
        funcId: null /*主键*/,
        parentId: null /*所属上级*/,
        name: null /*名称*/,
        type: 1 /*类型(1:菜单,2:按钮)*/,
        permissionValue: null /*权限值*/,
        path: null /*路径*/,
        icon: null /*图标*/,
        status: 1 /*状态(0:禁止,1:正常)*/,
        orders: null /*排序*/,
        component: null /*文件路径*/,
        hasChild: 0 /*是否有下级*/,
        leaf: 1 /*叶子*/,
      };
    },
    /*功能菜单-新增*/
    addDialog(parentId) {
      this.title = "新增功能菜单";
      this.dialogMode = "save";
      this.form = { ...this.initForm(), parentId: parentId };
      this.show = true;
    },
    /*功能菜单-修改*/
    editDialog(row) {
      this.title = "修改功能菜单";
      this.dialogMode = "update";
      this.form = { ...row };
      this.show = true;
    },
  },
};
</script>
<style scoped lang="scss"></style>

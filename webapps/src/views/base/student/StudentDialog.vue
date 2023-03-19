/*学生新增与修改,  作者:张可可,  日期:2023-03-08 23:42:07*/
<template>
  <el-dialog :title="title" width="800px" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label='学号' prop='studentNumber'><el-input placeholder='请输入学号' v-model='form.studentNumber' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='班级' prop='clazzId'>
            <el-select size="mini" :value="clazzValue"  @input="change($event)" v-model='form.clazzId'>
              <el-option label="--请选择--" value=""></el-option>
              <el-option v-for="item in clazzList" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='学生姓名' prop='name'><el-input placeholder='请输入学生姓名' v-model='form.name' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='登录名' prop='loginName'><el-input placeholder='请输入登录名' v-model='form.loginName' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='登录密码' prop='password'><el-input placeholder='请输入登录密码' v-model='form.password' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop='sex'> <v-select v-model="form.sex" placeholder="请选择性别" :dictKey="'gender'" /> </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='年龄' prop='age'><el-input placeholder='请输入年龄' v-model='form.age' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='联系电话' prop='phone'><el-input placeholder='请输入联系电话' v-model='form.phone' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='邮箱' prop='email'><el-input placeholder='请输入邮箱' v-model='form.email' size="mini" /></el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="头像" prop="avatar">
            <el-upload class="avatar-uploader" action="#" :http-request="uploadFile" :show-file-list="false">
              <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
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
        userId :[{required: true, message: '请输入用户id', trigger: 'blur'}],
        studentNumber :[{required: true, message: '请输入学号', trigger: 'blur'}],
        clazzId :[{required: true, message: '请输入班级id', trigger: 'blur'}],
        name :[{required: true, message: '请输入学生姓名', trigger: 'blur'}],
        sex :[{required: true, message: '请输入性别', trigger: 'blur'}],
        age :[{required: true, message: '请输入年龄', trigger: 'blur'}],
        phone :[{required: true, message: '请输入联系电话', trigger: 'blur'}],
        email :[{required: true, message: '请输入邮箱', trigger: 'blur'}],
        photoUrl :[{required: true, message: '请输入头像地址', trigger: 'blur'}],
      },
      clazzList: null,
      clazzValue: null,
    }
  },
  methods: {
     /*上传图片*/
     uploadFile(obj) {
      this.rq.post("sys/saveFile", obj.file).then((res) => {
        if (res.code == 200) this.form = { ...this.form, avatarUrl: res.data.fileUrl, avatar: res.data.filePath };
        else this.$message.error(res.msg);
      });
    },
    /*学生-保存*/
    save() {
      this.$refs['form'].validate((valid) => {
        if (!valid) return;
        this.rq.post("/student/" + this.dialogMode, this.form).then(res => {
          if (res.code == 200) { this.show = false; this.$message.success(this.title + "成功!"); this.loadData(); } else this.$message.error(res.msg);
        });
      });
    },
    /*学生-初始数据*/     
    initForm() {
      return {
        id : null,/*主键id*/
        userId : null,/*用户id*/
        studentNumber : null,/*学号*/
        clazzId : null,/*班级id*/
        name : null,/*学生姓名*/
        sex : null,/*性别*/
        age : null,/*年龄*/
        phone : null,/*联系电话*/
        email : null,/*邮箱*/
        photoUrl : null,/*头像地址*/
      }
    },
    /*学生-新增*/
    addDialog() { this.title = "新增学生"; this.dialogMode = "save"; this.form = this.initForm(); this.show = true; this.queryMajorList();},
    /*学生-修改*/
    editDialog(row) { this.title = "修改学生"; this.dialogMode = "update"; this.form = {...row}; this.show = true; },
    /* 查询班级相关信息 */
    change: function (val) {
      this.clazzValue = val;
    },
    queryMajorList () {
      const param = { dr: 0, page: 1, size: 9999 };
      this.rq.post("/clazz/page", param).then(res => {
        if (res.code == 200) { 
          this.clazzList = res.data.dataList;
        } else this.$message.error(res.msg);
      });
    },
  },
}
</script>
<style scoped lang="scss"></style>

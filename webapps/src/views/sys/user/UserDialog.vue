/*用户新增与修改,作者:高振中,日期:2020-12-20 22:36:10*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false"
    width="800px">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px" :inline="true">
      <el-row>
        <el-col :span="12">
          <el-form-item label="机构" prop="deptName">
            <el-input placeholder="请选择机构" v-model="form.deptName" size="mini" @click.native="showDeptTree" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="姓名" prop="name">
            <el-input placeholder="请输入姓名" v-model="form.name" size="mini" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="密码" prop="password">
            <el-input placeholder="请输入密码" v-model="form.password" size="mini" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="登录名" prop="loginName">
            <el-input placeholder="请输入登录名" v-model="form.loginName" size="mini" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="电话" prop="phone">
            <el-input placeholder="请输入电话" v-model="form.phone" size="mini" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="gender">
            <v-select v-model="form.gender" placeholder="请选择性别" :dictKey="'gender'" :addBlank="true"></v-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='编号' prop='number'>
            <el-input placeholder='请输入编号' v-model='form.number' size="mini" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='卡号' prop='cardNo'>
            <el-input placeholder='请输入卡号' v-model='form.cardNo' size="mini" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='分组' prop='groupName'>
            <el-input placeholder='请输入分组' v-model='form.groupName' size="mini" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='指纹数量' prop='fingerprintCount'>
            <el-input placeholder='请输入指纹数量' v-model='form.fingerprintCount' size="mini" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='人脸信息' prop='faceInfo'>
            <el-input placeholder='请输入人脸信息' v-model='form.faceInfo' size="mini" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label='人员类型' prop='type'>
            <v-select v-model="form.type" placeholder="请选择人员类型" :dictKey="'personType'" :addBlank="true">
            </v-select>
          </el-form-item>
        </el-col>
        <div v-if="form.type==2">
          <el-col :span="12">
            <el-form-item label='开始时间' prop='startTime'>
              <el-date-picker v-model='form.startTime' type="datetime" format="yyyy-MM-dd HH:mm"
                value-format="yyyy-MM-dd HH:mm:ss" placeholder="请输入开始时间" size="mini"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label='结束时间' prop='endTime'>
              <el-date-picker v-model='form.endTime' type="datetime" format="yyyy-MM-dd HH:mm"
                value-format="yyyy-MM-dd HH:mm:ss" placeholder="请输入结束时间" size="mini"></el-date-picker>
            </el-form-item>
          </el-col>
        </div>
        <el-col :span="12">
          <el-form-item label="状态" prop="status">
            <v-select v-model="form.status" placeholder="请选择状态" :dictKey="'status'" :addBlank="true"></v-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="头像" prop="avatar">
            <el-upload class="avatar-uploader" action="#" :http-request="uploadFile" :show-file-list="false">
              <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" placeholder="请输入备注" v-model="form.remark" size="mini" style="width:560px" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" style="text-align: right">
      <el-button @click="show = false" size="mini">取消</el-button>
      <el-button type="primary" @click="save()" size="mini">确定</el-button>
    </div>
    <v-dept-tree ref="deptTree" :setValue="setDeptValue"></v-dept-tree>
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
        // deptName: [{ required: true, message: "请选择机构", trigger: "blur" }],
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        loginName: [{ required: true, message: "请输入登录名", trigger: "blur" }],
        // phone: [{ required: true, message: "请输入电话", trigger: "blur" }],
        number: [{ required: true, message: '请输入编号', trigger: 'blur' }],
        // cardNo: [{ required: true, message: '请输入卡号', trigger: 'blur' }],
        // groupName: [{ required: true, message: '请输入分组', trigger: 'blur' }],
        type: [{ required: true, message: '请输入人员类型1永久2临时', trigger: 'blur' }],
        // startTime: [{ required: true, message: '请输入开始时间', trigger: 'blur' }],
        // endTime: [{ required: true, message: '请输入结束时间', trigger: 'blur' }],
      },
    };
  },
  methods: {
    /*上传图片*/
    uploadFile(obj) {
      this.rq.post("sys/saveFile", obj.file).then((res) => {
        if (res.code == 200) this.form = { ...this.form, avatarUrl: res.data.fileUrl, avatar: res.data.filePath };
        else this.$message.error(res.msg);
      });
    },
    /*返回机构结点*/
    setDeptValue(node) {
      this.form = { ...this.form, deptName: node.name, deptId: node.id };
    },
    /*显示机构树*/
    showDeptTree() {
      this.$refs["deptTree"].showTree();
    },
    /*用户-保存*/
    save() {
      this.$refs["form"].validate((valid) => {
        if (!valid) return;
        this.rq.post("/user/" + this.dialogMode, this.form).then((res) => {
          if (res.code == 200) {
            this.show = false;
            this.$message.success(this.title + "成功!");
            this.loadData();
          } else this.$message.error(res.msg);
        });
      });
    },
    /*用户-初始数据*/
    initForm() {
      return {
        deptName: null, /*机构名称*/
        userId: null, /*主键*/
        deptId: null, /*机构ID*/
        name: null, /*姓名*/
        password: null, /*密码*/
        loginName: null, /*登录名*/
        avatar: null, /*头像*/
        phone: null, /*电话*/
        gender: null, /*性别*/
        status: null, /*状态*/
        number: null,/*编号*/
        cardNo: null,/*卡号*/
        groupName: null,/*分组*/
        fingerprintCount: null,/*指纹数量*/
        faceInfo: null,/*人脸信息*/
        type: null,/*人员类型1永久2临时*/
        startTime: null,/*开始时间*/
        endTime: null,/*结束时间*/
        avatarUrl: null
      };
    },
    /*用户-新增*/
    addDialog() {
      this.title = "新增用户";
      this.dialogMode = "save";
      this.form = this.initForm();
      this.show = true;
    },
    /*用户-修改*/
    editDialog(row) {
      this.title = "修改用户";
      this.dialogMode = "update";
      this.form = { ...row };
      this.show = true;
    },
  },
};
</script>
<style lang="scss" scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100x;
  line-height: 78px;
  text-align: center;
}

.avatar {
  width: 78px;
  height: 78px;
  display: block;
}
</style>  

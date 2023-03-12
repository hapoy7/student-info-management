<template>
  <div>
    <el-row class="bg">
      <el-col :span="20" class="logo" @click.native="() => $emit('changeWidth')">吉林警察学院学生信息管理系统 </el-col>
      <el-col :span="4" style="padding: 10px ; font-size: 14px; color: white;text-align: right;padding-right: 50px;">
        你好,{{ user.name }} <img src="@/assets/head.jpeg" style="width: 40px; height: 40px;" /> <el-button type="text"
          size="mini" @click="doLogOut()" style="color: white; margin-left: 8px">注消</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import Vue from "vue";
export default {
  props: ["user"],
  methods: {
    doLogOut() { Vue.ls.clear(); this.$emit("changeLogin", false); },
  },
};
</script>
<style scoped lang="scss">
.logo {
  text-align: left;
  font-size: 20px;
  color: white;
  background-image: url("../../assets/logo.png");
  background-repeat: no-repeat;
  height: 60px;
  margin: 0;
  padding-left: 70px;
  padding-top: 14px;
}

.bg {
  background: linear-gradient(to right,#3557b7,#2a4fa3);
  text-align: center;
  height: 60px;
  padding: 0;
  margin: 0;
}
</style>

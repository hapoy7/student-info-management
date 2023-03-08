/*上传组件,作者:高振中,日期:2020-12-25 01:35:25*/
<template>
  <el-upload action="#" :http-request="doImport" :show-file-list="false" style="display: inline-block"> <el-button size="mini">{{label}}</el-button></el-upload>
</template>
<script>
export default {
  name: "v-upload",
  props: ["doImport","label"],
};
</script>
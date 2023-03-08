/* 自定义表格列,作者:高振中,日期:2020-12-25 01:35:25 */
<template>
  <div style="display: inline-block;">
    <el-popover trigger="hover">
      <el-button slot="reference" size="mini">自定义条件</el-button>
      <el-checkbox v-model="condShow" :indeterminate="a" @change="(val) => conditionAll(val)" size="mini">==全部==</el-checkbox>
      <el-row v-for="(val, key) in condition" :key="key">
        <el-checkbox v-model="val.show" @change="conditions" size="mini">{{ val.text }}</el-checkbox>
      </el-row>
    </el-popover>
    <el-popover trigger="hover" with="200px">
      <el-button slot="reference" size="mini">自定义表格列</el-button>
      <el-checkbox v-model="colShow" :indeterminate="b" @change="(val) => columnAll(val)" size="mini">==全部==</el-checkbox>
      <el-row v-for="(val, key) in column" :key="key">
        <el-checkbox v-model="val.show" @change="columns" size="mini">{{ val.text }}</el-checkbox>
      </el-row>
    </el-popover>
  </div>
</template>
<script>
export default {
  name: 'v-columns',
  props: ['condition', 'column'],
  data() {
    return { condShow: true, colShow: true, a: false, b: false };
  },
  methods: {
    conditionAll(val) { for (let i in this.condition) this.condition[i].show = val; this.conditions();},
    columnAll(val) { for (let i in this.column) this.column[i].show = val;  this.columns();},
    conditions() {
      let props = Object.values(this.condition);
      let count = props.filter(i => i.show).length;
      this.condShow = props.length === count;
      this.a = count > 0 && count < props.length;
    },
    columns() {
      let props = Object.values(this.column);
      let count = props.filter(i => i.show).length;
      this.colShow = props.length === count;
      this.b = count > 0 && count < props.length;
    }
  },
};
</script>

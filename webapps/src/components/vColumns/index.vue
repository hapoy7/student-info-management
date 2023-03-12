/* 自定义表格列,作者:高振中,日期:2020-12-25 01:35:25 */
<template>
  <div style="display: inline-block;">
    <el-popover trigger="hover" placement="top">
      <el-button slot="reference" size="mini">自定义条件</el-button>
      <el-checkbox v-model="condShow" :indeterminate="condIndeterminate" @change="(val) => conditionAll(val)" size="mini">==全部==</el-checkbox>
      <el-row v-for="(val, key) in condition" :key="key">
        <el-checkbox v-model="val.show" @change="conditions" size="mini">{{ val.text }}</el-checkbox>
      </el-row>
      <el-button size="mini" @click="save()" type="primary">保存</el-button>
    </el-popover>
    <el-popover trigger="hover" placement="top" with="200px">
      <el-button slot="reference" size="mini">自定义表格列</el-button>
      <el-checkbox v-model="colShow" :indeterminate="columnIndeterminate" @change="(val) => columnAll(val)" size="mini">==全部==</el-checkbox>
      <el-row v-for="(val, key) in column" :key="key">
        <el-checkbox v-model="val.show" @change="columns" size="mini">{{ val.text }}</el-checkbox>
      </el-row>
      <el-button size="mini" @click="save()" type="primary">保存</el-button>
    </el-popover>
  </div>
</template>
<script>
export default {
  name: 'v-columns',
  props: ['condition', 'column', 'component'],
  data() { return { condShow: true, colShow: true, condIndeterminate: false, columnIndeterminate: false }; },
  mounted() { this.loadData(); },
  methods: {
    save() {
      let conds = [];
      let grids = [];
      for (let key in this.condition)
        if (this.condition[key].show == true)
          conds.push(key);

      for (let key in this.column)
        if (this.column[key].show == true)
          grids.push(key);

      this.rq.post("/fieldconf/save", { conds: conds, grids: grids, component: this.component }).then(res => {
        if (res.code == 200) {
          this.$message.success("配置保存成功!");
        } else this.$message.error(res.msg);
      });
    },
    /**从后端加载字段配置*/
    loadData() {
      this.rq.post("/fieldconf/list", { componentEq: this.component }).then(res => {
        if (res.code == 200) {
          for (let key in this.condition)
            this.condition[key].show = (res.data.conds.indexOf(key) > -1);

          for (let key in this.column)
            this.column[key].show = (res.data.grids.indexOf(key) > -1);

        } else this.$message.error(res.msg);
      });
    },
    conditionAll(val) { for (let i in this.condition) this.condition[i].show = val; this.conditions(); },
    columnAll(val) { for (let i in this.column) this.column[i].show = val; this.columns(); },
    conditions() {
      let props = Object.values(this.condition);
      let count = props.filter(i => i.show).length;
      this.condShow = props.length === count;
      this.condIndeterminate = count > 0 && count < props.length;
    },
    columns() {
      let props = Object.values(this.column);
      let count = props.filter(i => i.show).length;
      this.colShow = props.length === count;
      this.columnIndeterminate = count > 0 && count < props.length;
    }
  },
};
</script>

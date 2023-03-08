<template>
  <el-table v-bind="$attrs" v-on="$listeners" v-loading="loading?loading : false" element-loading-text="加载中..." border
    size="mini" :row-style="rowStyle" :cell-style="cellStyle" :header-row-style="headerRow"
    :header-cell-style="headerCell">
    <slot></slot>
  </el-table>
</template>
<script>
export default {
  name: "v-table",
  props: ['loading'],
  methods: {
    /*表格行高处理*/
    rowStyle(obj) { return obj.rowIndex % 2 == 0 ? { padding: 0 } : { padding: 0, background: '#FAFAFA' }; },
    cellStyle(obj) { return { padding: '0', height: '36px' }; },
    headerRow(obj) { return { height: '32px' }; },
    headerCell(obj) { return { padding: '0px', background: '#EDEDF2', height: '36px' }; }
  }
};
</script>
<style scope>

</style>

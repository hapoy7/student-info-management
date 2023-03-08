<template>
  <div>
    <el-select :placeholder="placeholder ? placeholder : ''" :size="size ? size : 'mini'" :value="value" @input="change($event)">
      <el-option label="--请选择--" value="" v-if="this.addBlank"></el-option>
      <el-option v-for="item in options" :key="item.value" :label="item.text" :value="item.value"></el-option>
    </el-select>
  </div>
</template>
<script>
import { getDict } from "@/common/dicts";
export default {
  name: "v-select",
  props: ["placeholder", "size", "value", "dictKey", "addBlank"],
  data() {
    return { options: getDict(this.dictKey) };
  },
  methods: {
    change: function (val) {
      this.$emit("input", val);
    }
  }
};
</script>
<template>
    <el-button :disabled="isDisabled" v-bind="$attrs" v-on="$listeners">{{ label }}</el-button>
</template>
<script>
import Vue from "vue";
import { BUTTONS } from "@/common/Constant";
export default {
    name: "v-button",
    props: ["buttonKey", "label"],
    computed: {
        isDisabled() {
            return Vue.ls.get(BUTTONS).filter(item => item.component == this.buttonKey) == 0;
        }
    },
};
</script>
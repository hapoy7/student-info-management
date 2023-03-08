<template>
  <el-row>
    <el-tag v-for="tab in tabs" :key="tab.meta.funcId" @close="handleClose(tab)" @click="handClick(tab)"
      :effect="tab.path == active.path ? 'dark' : 'light'" closable style="height: 32px;" >
      {{ tab.meta.name }}
    </el-tag>
  </el-row>
</template>
<script>
export default {
  props: [],
  data() {
    return {
      active: { path: '', meta: {} }, //当前激活页签
      tabs: [] //打开的页签集
    };
  },
  mounted() {
    if (this.$route.path != '/' && this.$route.meta.funcId) {
      this.active = this.$route;
      this.afterChangeRoute(this.active);
    }
    this.$router.afterEach(to => {
      if (this.active.meta.funcId != to.meta.funcId) this.afterChangeRoute(to);
    });
  },
  methods: {
    /**
     * 路由发生变化,那么添加新的tab
     */
    afterChangeRoute(tab) {
      this.active = tab;
      this.$emit("activeMenu", this.active)
      if (this.tabs.filter(e => e.path == tab.path).length == 0) // tab不存在
        this.tabs.push(this.active)
    },
    handClick(tab) {
      if (tab.meta.funcId != this.active.meta.funcId)
        this.$router.push(tab)
    },
    /*
     *关闭页签的处理逻辑
     */
    handleClose(tab) {
      if (this.tabs.length == 1 && this.$route.path == '/home') //只剩首页啥也不干
        return;

      if (this.tabs.length == 1 && this.$route.path != '/home') //只剩非首页 
        this.$router.push("/home");//开首页

      const index = this.tabs.findIndex(e => e.meta.funcId == tab.meta.funcId);
      if (tab.meta.funcId == this.active.meta.funcId && index == this.tabs.length - 1) // 关当前(最后一个)往前跳
        this.$router.push(this.tabs[index - 1]);

      if (tab.meta.funcId == this.active.meta.funcId && index != this.tabs.length - 1) // 关当前(不是最后一个)往后跳
        this.$router.push(this.tabs[index + 1])

      this.tabs.splice(index, 1);
    }
  }
};
</script>
<style scoped>

</style>

<template>
  <div id="app">
    <el-container v-if="isLogin && roleType == 1">
      <el-header>
        <Header @changeLogin="changeLogin" @changeWidth="() => width = 200 - width" :user="user"></Header>
      </el-header>
      <el-container>
        <el-aside :style="menuHeight">
          <Menu :menus="menus" :active-menu="activeMenu"></Menu>
        </el-aside>
        <el-container :style="menuHeight">
          <el-header style="height:32px;background:transparent;">
            <menu-tag @activeMenu="handleActiveMenu" />
          </el-header>
          <el-main>
            <router-view />
          </el-main>
        </el-container>
      </el-container>
      <el-footer style="height:22px;">
        <Footer></Footer>
      </el-footer>
    </el-container>
    <login v-if="!isLogin" @changeLogin="changeLogin"></login>
    <KeyList v-if="isLogin && roleType == 2" @changeLogin="changeLogin" :user="user"></KeyList>
  </div>
</template>
<script>
import Vue from "vue";
import Menu from "@/views/common/Menu";
import Header from "@/views/common/Header";
import Footer from "@/views/common/Footer";
import Login from "@/views/common/Login";
import KeyList from "@/views/base/key/KeyList.vue";
import MenuTag from "@/views/common/MenuTag";
import { USER, MENUS } from "@/common/Constant";
export default {
  components: { MenuTag, Menu, Header, Footer, Login, KeyList },
  data() {
    return { menus: [], activeMenu: {}, user: {}, isLogin: Vue.ls.get(USER) != null, roleType: 2, height: null, width: 200 };
  },
  computed: {
    menuHeight() {
      return this.resize();
    },
  },
  mounted() {
    this.changeLogin(Vue.ls.get(USER))
  },
  methods: {
    handleActiveMenu(menu) {
      this.activeMenu = menu && menu.meta ? menu : { path: '', meta: {} }
    },
    resize() {
      this.height = document.documentElement.clientHeight
      return "height:" + (this.height - 60 - 22) + "px;width:" + this.width + "px;";
    },
    changeLogin(status) {
      this.isLogin = status;
      if (status) {
        this.menus = Vue.ls.get(MENUS);
        this.user = Vue.ls.get(USER);
        this.roleType = this.user.roleType;
        window.onresize = () => this.resize();
      }
    },
  },
};
</script>
<style lang="scss">
/* 解决element-ui的table表格控件表头与内容列不对齐问题 */
.el-table th.gutter {
  display: table-cell !important;
}

.el-header {
  background-color: #667594;
  padding: 0;
}

.el-footer {
  color: white;
  background-color: #999999;
  text-align: center;
  padding: 0;
}

.el-aside {
  overflow-x :hidden;
  background-color:#1c2733 
}

.el-main {
  background-color: white;
  padding: 15px;
}

.el-container {
  margin: 0;
}

body,
html {
  padding: 0;
  margin: 0;
}

//滚动条的宽度
::-webkit-scrollbar {
  width: 10px;
  height: 10px;
}

//滚动条的滑块
::-webkit-scrollbar-thumb {
  background-color: #a1a3a9;
  border-radius: 4px;
}
</style>

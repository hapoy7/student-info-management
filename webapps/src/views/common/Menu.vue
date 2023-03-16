<template>
  <div>
    <el-menu class="el-menu-vertical-demo" background-color="#1c2733" text-color="#fff" active-text-color="#ffd04b"
      :default-openeds="openeds"
      :default-active="activeMenu.meta && activeMenu.meta.funcId ? activeMenu.meta.funcId : ''">
      <el-submenu v-for="item in menus" :index="item.funcId.toString()" :key="item.funcId" class="first_menu">
        <template slot="title">
          <i :class="item.icon"></i><span>{{ item.name }}</span>
        </template>
        <router-link v-for="menu in item.children" :key="menu.funcId" :to="menu.path">
          <el-menu-item :class="menu.icon" :index="menu.funcId.toString()">{{ menu.name }}</el-menu-item>
        </router-link>
      </el-submenu>
    </el-menu>
  </div>
</template>
<script>
import { router, defaultRoute } from "@/router/index";
import { MENUS } from "@/common/Constant";
import Vue from "vue";
export default {
  name: "Menu",
  props: ['activeMenu'],
  data() { return { 
    menus: [], openeds: ['504681454035407046', '504681454035407042', '504681454035407041', '721227411437387776','754009866309206016'] 
  } },
  mounted() {
    this.menus = Vue.ls.get(MENUS);
    // console.log(this.menus)
    let routes = [];
    this.menus.map((item) => {
      if (item.path) routes.push({ meta: { name: item.name, funcId: item.funcId }, path: item.path, component: (resolve) => require([`@/views/${item.component}.vue`], resolve) });
      if (item.children)
        item.children.map((menu) => {
          routes.push({ meta: { name: menu.name, funcId: menu.funcId }, path: menu.path, component: (resolve) => require([`@/views/${menu.component}.vue`], resolve) });
        });
    });
    routes.map(i => router.addRoute(i));
    // 添加默认路由.因为默认路由为全捕获,因此放到最后.
    router.addRoute(defaultRoute)
  },
};
</script>
<style lang="scss" scoped>
.first_menu { text-align: left; font-size: 15px; }
.el-menu-vertical-demo { width: 200px; }
li.el-submenu .el-menu-item { height: 26px; line-height: 26px; font-size: 16px; }
::v-deep div.el-submenu__title { height: 28px; line-height: 28px; font-size: 18px; }
</style>
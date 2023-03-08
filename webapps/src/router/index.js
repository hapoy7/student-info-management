import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/common/Home";
Vue.use(VueRouter);
const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes: [
    { path: "/home", component: Home, meta: { funcId: "1", name: "首页" } },
  ]
});
const defaultRoute = { path: "*", redirect: "/home", }
export {router,defaultRoute};

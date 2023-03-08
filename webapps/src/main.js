import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Vue from 'vue'
import App from './App.vue'
import {router} from './router'
import requests from './common/http.js'
import Storage from 'vue-ls';
import moment from 'moment'
import './components/index'
import 'default-passive-events';

Vue.prototype.$moment = moment
moment.locale('zh-cn') // 这里是进行了汉化 不写这句默认格式是外国的
Vue.use(Storage, { namespace: 'pro_', name: 'ls', storage: 'local', });
Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.prototype.rq = requests
new Vue({ el: "#app", router, render: h => h(App) });

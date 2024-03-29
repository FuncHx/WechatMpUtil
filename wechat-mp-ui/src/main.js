import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import "@/router/router_permission"
import "@/assets/icon/iconfont"

Vue.config.productionTip = false

Vue.use(Element, {
  size:  'medium ' // set element-ui default size
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')

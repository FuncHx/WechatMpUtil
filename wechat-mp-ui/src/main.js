import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import "@/router/router_permission"
import "@/assets/icon/iconfont"
import Avue from '@smallwei/avue'
import '@smallwei/avue/lib/index.css'

Vue.config.productionTip = false

Vue.use(Element, {
  size:  'medium ' // set element-ui default size
})

//阿里OSS配置
let ossAli = {
  region: 'oss-cn-zhangjiakou',
  endpoint: 'oss-cn-zhangjiakou.aliyuncs.com',
  stsToken: '',
  accessKeyId: 'xxxxxxxxx',
  accessKeySecret: 'xxxxxxxxxxxxxxxxxxxxxx',
  bucket: 'joolun-open',
}

Vue.use(Avue, {
  ali: ossAli
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')

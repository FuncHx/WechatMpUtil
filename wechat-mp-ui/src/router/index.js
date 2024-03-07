import Vue from 'vue'
import Router from 'vue-router'
import LayoutIndex from "@/components/layOutIndex.vue"

Vue.use(Router)

// 公共路由
export const constantRoutes = [
  {
    path: '',
    component: LayoutIndex,
    redirect: 'index',
    name: 'index',
    meta: {title: '首页', icon: 'shouye', noCache: true,},
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/index'], resolve),
        meta: { title: '首页', icon: 'dashboard', noCache: true, affix: true }
      }
    ]
  },
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    hidden: true
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },
  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },
]

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
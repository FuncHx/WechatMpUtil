<template>
  <el-menu :default-active="$route.path" mode="vertical" unique-opened>
    <template v-for="route in routes">
      <el-submenu v-if="route.children && route.children.length > 0" :index="route.path">
        <template slot="title">
          <i :class="route.meta.icon"></i>
          {{ route.meta.title }}
        </template>
        <custom-aside :routes="route.children"></custom-aside>
      </el-submenu>
      <el-menu-item v-else :index="route.path">
        <i :class="route.meta.icon"></i>
        {{ route.meta.title }}
      </el-menu-item>
    </template>
  </el-menu>
</template>

<script>
export default {
  props: {
    routes: {
      type: Array,
      default: () => [],
    },
  },
  created() {
    this.routes.forEach(res => {
      console.log(res);
    })
  },
  components: {
    CustomAside: () => import('./index.vue'), // 递归组件
  },
};
</script>
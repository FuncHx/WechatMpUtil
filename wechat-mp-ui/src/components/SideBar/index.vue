<template>
  <div class="aside" :style="'background-color:' +$store.state.settings.theme.backgroundColor+';'">
    <el-menu :background-color="$store.state.settings.theme.backgroundColor" 
    class="el-menu-vertical-demo" 
    :text-color="$store.state.settings.theme.fontColor" 
    mode="vertical" 
    :router="true"
    unique-opened>
    <template v-for="route in routes">
      <el-submenu v-if="route.children && route.children.length > 0" :index="route.path">
        <template slot="title">
          <Icon :icon="route.meta.icon"/>
          {{ route.meta.title }}
        </template>
        <custom-aside :routes="route.children"></custom-aside>
      </el-submenu>
      <el-menu-item v-else :index="route.path" :route="{name: route.name}">
        <Icon :icon="route.meta.icon"/>
        {{ route.meta.title }}
      </el-menu-item>
    </template>
  </el-menu>
  </div>
</template>

<script>
import Icon from '../util/icon.vue';
import { mapState } from 'vuex';
export default {
  props: {
    routes: {
      type: Array,
      default: () => [],
    },
  },
  components: {
    CustomAside: () => import('./index.vue'), // 递归组件
    Icon
  },
};
</script>

<style>
  .aside {
    height: 100%;
  }
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 100%;
    margin: 0 !important;
    
  }
  .el-menu-vertical-demo > .el-menu-item {
      min-width: 100px !important;
      padding: 0;
  }
</style>
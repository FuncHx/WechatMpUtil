<template>
  <el-container  class="operation-wrapper">
  <el-aside width="auto">Aside</el-aside>
  <el-container>
    <el-header><NavBar/></el-header>
    <el-main><router-view></router-view></el-main>
  </el-container>
</el-container>
</template>

<script>

import NavBar from './NavBar.vue';

export default {
    name: "LayoutIndex",
    components: {
      NavBar,
    },
    data() {
        return {

        }
    }
}
</script>

<style lang="less">
  .el-header {
    text-align: center;
    padding: 0 !important;
  }

  .operation-wrapper {
    width: 100% !important;
    .el-header {
      // height: 100px;
      padding: 0;
    }
    .el-aside {
      height: 100vh;
      overflow-y: auto;
    }
    .el-main {
      padding: 0px 16px !important;
      height: calc(100vh - 200px); 
      overflow-y: auto;
    }
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    position: relative;
    height: 100vh;
    width: 150px !important;
    .el-menu {
    // 解决侧边栏凸出来的一小块儿白边
        border-right: none;
    }
  }
  
</style>
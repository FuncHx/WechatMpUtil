<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="基本信息" name="info"><edit-info-vue/></el-tab-pane>
    <el-tab-pane label="修改密码" name="editPassword"><edit-password-vue/></el-tab-pane>
  </el-tabs>
</template>

<script>
import EditInfoVue from './editInfo.vue';
import EditPasswordVue from './editPassword.vue';
export default {
    components: {
        EditInfoVue,
        EditPasswordVue
    },
    data() {
      return {
        activeName: 'info'
      };
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
    }
}
</script>

<style>
  .el-form-item {
    margin-bottom: 5px !important;
  }
</style>
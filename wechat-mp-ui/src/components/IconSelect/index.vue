<!-- @author zhengjie -->
<template>
    <div class="icon-body">
      <el-input v-model="name" style="position: relative;" clearable placeholder="请输入图标名称" @clear="filterIcons" @input.native="filterIcons">
        <i slot="suffix" class="el-icon-search el-input__icon" />
      </el-input>
      <div class="icon-list">
        <div v-for="(item, index) in iconList" :key="index" @click="selectedIcon(item.font_class)">
            <svg class="icon" aria-hidden="true" style="width:20px;height:20px">
                <use :xlink:href="'#' + item.font_class"></use>
            </svg>
            <span>{{ item.name }}</span>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import icons from "@/assets/icon/iconfont.json"
  export default {
    name: 'IconSelect',
    data() {
      return {
        name: '',
        iconList: icons.glyphs
      }
    },
    methods: {
      filterIcons() {
        this.iconList = icons.glyphs
        if (this.name) {
          this.iconList = this.iconList.filter(item => item.includes(this.name))
        }
      },
      selectedIcon(name) {
        this.$emit('selected', name)
        document.body.click()
      },
      reset() {
        this.name = ''
        this.iconList = icons.glyphs
      }
    }
  }
  </script>
  
  <style rel="stylesheet/scss" lang="scss" scoped>
    .icon-body {
      width: 100%;
      padding: 10px;
      .icon-list {
        height: 200px;
        overflow-y: scroll;
        div {
          height: 30px;
          line-height: 30px;
          margin-bottom: -5px;
          cursor: pointer;
          width: 33%;
          float: left;
        }
        span {
          display: inline-block;
          vertical-align: -0.15em;
          fill: currentColor;
          overflow: hidden;
        }
      }
    }
  </style>
  
<template>
  <div class="navbar">
    <div class="menu-button" @click="handClickMenu()">
      <span class="iconfont" :class="{'is-active':sideBar.opened}">&#xe643;</span>
    </div>
    <Breadcrumb/>
    <div class="navbar-right">
      <el-dropdown class="avatar-container">
      <span class="el-dropdown-link">
        <!-- <el-avatar shape="square" :size="'large'" src="@/assets/images/profile.jpg"></el-avatar> -->
        <div class="avatar-wrapper">
          <img src="@/assets/images/profile.jpg" class="user-avatar">
        </div>
      </span>
      <el-dropdown-menu slot="dropdown" >
        <el-dropdown-item icon="el-icon-setting">黄金糕</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    </div>
  </div>
</template>

<script>
import  Breadcrumb  from './breadcrumb.vue'
import { mapGetters, mapState } from 'vuex'
export default {
  components: {
    Breadcrumb
  },
  computed: {
    ...mapGetters(["sideBar"]),
    ...mapState(["user"])
  },
  methods: {
    handClickMenu() {
      this.sideBar.opened = !this.sideBar.opened 
    }
  },
  created() {
    console.log(this.user);
  }
}
</script>

<style>
    .navbar {
        height: 100%;
        width: 100%;
        overflow: hidden;
        position: relative;
        background: #fff;
        box-shadow: 0 1px 4px rgba(0,21,41,.08);
        line-height: 50px !important;
        .menu-button {
          float: left;
          display: inline-block;
          vertical-align: top;
          margin: 0 20px 0 10px;
        }
        .hamburger-container {
          line-height: 46px;
          height: 100%;
          float: left;
          cursor: pointer;
          transition: background .3s;
          -webkit-tap-highlight-color:transparent;
          &:hover {
            background: rgba(0, 0, 0, .5)
          }
        }
        .breadcrumb-container {
          margin-left: 20px;
          float: left;
        }
        .el-breadcrumb{
          line-height: 50px !important;
          margin-left: 10px;
          float: left;
        }
    }
    .menu-button {
      cursor: pointer;
      .is-active {
        display: block;
        transform: rotate(180deg) !important;
        transition: width 0.3s linear, transform 0.3s linear;
      }
    }
    .navbar-right {
      float: right;
      margin-right: 40px;
      cursor: pointer;
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
    
</style>
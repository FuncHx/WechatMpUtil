<template>
  <div class="user-info">
    <el-row type="flex" >
        <el-card class="box-card" :body-style="{ padding: '20px' }">
            <div slot="header" class="clearfix">
                <span style="">个人信息</span>
            </div>
            <div class="avatar-wrapper">
                <img src="@/assets/images/profile.jpg" class="user-avatar">
            </div>
            <el-divider></el-divider>
            <div class="info-line">
                <Icon :icon="'wode1'" :size="'18px'"/>
                <span>用户名称：{{ userInfo.nickName }}</span>
            </div>
            <el-divider></el-divider>
            <div class="info-line">
                <Icon :icon="'shouji'" :size="'18px'"/>
                <span>手机号码：{{ userInfo.phonenumber }}</span>
            </div>
            <el-divider></el-divider>
            <div class="info-line">
                <Icon :icon="'xinxi'" :size="'18px'"/>
                <span>邮箱地址：{{ userInfo.email }}</span>
            </div>
            <el-divider></el-divider>
            <div class="info-line">
                <Icon :icon="'haoyourenzheng'" :size="'18px'"/>
                <span>所属角色：{{ userInfo.data.roles.join(" & ") }}</span>
            </div>
            <el-divider></el-divider>
            <div class="info-line">
                <Icon :icon="'tianjia1'" :size="'18px'"/>
                <span>创建日期：{{ userInfo.createTime !== "" ? userInfo.createTime.split("T")[0] : "" }}</span>
            </div>
        </el-card>
        <el-card class="info-card" :body-style="{ padding: '20px' }">
            <div slot="header" class="clearfix">
                <span style="">基本资料</span>
            </div>

            <Info/>

        </el-card>
    </el-row>
  </div>
</template>

<script>
import {mapState} from "vuex"
import Icon from "@/components/util/icon.vue";
import Info from "@/components/info"
export default {
    components: {
        Icon,
        Info
    },
    computed: {
        ...mapState("user", ["userInfo"])
    },
    created() {
        console.log(this.userInfo);
    }
}
</script>

<style>
    .user-info {
        padding: 20px;
    }
    .box-card {
        width: 350px;
        height: 500px;
    }
    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }
    .avatar-wrapper {
        text-align: center;
        .user-avatar {
            display: inline;
            width: 120px;
            height: 120px;
            border-radius: 120px;
        }
    }
    .el-divider--horizontal {
        margin: 8px 0 !important;
        height: 1.5px !important;
    }
    .info-line {
        padding: 10px 8px;
        .icon {
            margin-right: 6px;
        }
    }
    .info-card {
        margin-left: 20px;
        width: 800px;
        height: 350px;
    }
</style>
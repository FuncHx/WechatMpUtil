<template>
  <div class="user-info">
    <el-row type="flex" >
        <el-card class="box-card" :body-style="{ padding: '20px' }">
            <div slot="header" class="clearfix">
                <span style="">个人信息</span>
            </div>
            <div class="avatar-wrapper">
                <img :src="avatar" class="user-avatar" @click="opened = !opened">
                <el-upload
                class="upload-demo"
                :action="baseUrl + 'user/editAvatar'"
                multiple
                :headers="{Authorization: token}"
                :limit="1"
                :disabled = "uploaded"
                :on-success="uplodaSuccess"
                :show-file-list="false"
                :before-upload="beforeUpload"
                :file-list="[]">
                <el-button size="small" type="primary">点击上传</el-button>
                </el-upload>
            </div>
            <el-divider></el-divider>
            <div class="info-line">
                <Icon :icon="'wode1'" :size="'18px'"/>
                <span>用户名称</span>
                <span style="float: right;">{{ userInfo.nickName }}</span>
            </div>
            <el-divider></el-divider>
            <div class="info-line">
                <Icon :icon="'shouji'" :size="'18px'"/>
                <span>手机号码</span>
                <span style="float: right;">{{ userInfo.phonenumber }}</span>
            </div>
            <el-divider></el-divider>
            <div class="info-line">
                <Icon :icon="'xinxi'" :size="'18px'"/>
                <span>邮箱地址</span>
                <span style="float: right;">{{ userInfo.email }}</span>
            </div>
            <el-divider></el-divider>
            <div class="info-line">
                <Icon :icon="'haoyourenzheng'" :size="'18px'"/>
                <span>所属角色</span>
                <span style="float: right;">{{ roles.join(" & ") }}</span>
            </div>
            <el-divider></el-divider>
            <div class="info-line">
                <Icon :icon="'tianjia1'" :size="'18px'"/>
                <span>创建日期</span>
                <span style="float: right;">{{ userInfo.createTime !== "" ? userInfo.createTime.split("T")[0] : "" }}</span>
            </div>
            <el-divider></el-divider>

        </el-card>
        <el-card class="info-card" :body-style="{ padding: '20px' }">
            <div slot="header" class="clearfix">
                <span style="">基本资料</span>
            </div>

            <Info/>

        </el-card>
    </el-row>
    <el-image-viewer v-if="opened" :zIndex="Number(99999)" :on-close="closeImgViewer" :url-list="[avatar]" />
  </div>
</template>

<script>
import {mapState} from "vuex"
import Icon from "@/components/util/icon.vue";
import Info from "@/components/info"
import elImageViewer from 'element-ui/packages/image/src/image-viewer';
import NProgress from 'nprogress'
import request from "@/utils/request"
export default {
    components: {
        elImageViewer,
        Icon,
        Info,
    },
    data() {
        return {
            opened: false,
            uploaded: false,
            baseUrl: request.defaults.baseURL
        }
    },
    computed: {
        ...mapState("user", ["userInfo", "avatar", "token", "roles"])
    },
    methods: {
        closeImgViewer() {
            this.opened = false;
        },
        uplodaSuccess(response, file, fileList) {
            if(response.code === 200) {
                this.$store.commit("user/SET_AVATAR", response.data)
                this.$message.success(response.message)
            }else {
                this.$message.error(response.message)
            }
            NProgress.done()
        },
        beforeUpload(file) {
            NProgress.start()
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        }
    }
}
</script>

<style>
    .user-info {
        padding: 20px;
    }
    .box-card {
        width: 350px;
        height: 520px;
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
            position: relative;
            cursor: pointer;
        }
        .user-avatar:hover {
            opacity: 0.4;
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
    .upload-demo {
        position: absolute;
        top: 170px;
        left: 240px;
    }

</style>


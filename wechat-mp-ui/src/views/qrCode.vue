<template>
  <div class="magic">
    <div class="user-info">
        <img :src="headerImg">
        <div>
            <span style="font-size: 22px;font-weight: 400;">W</span>
            <br>
            <span style="font-size: 16px;color: #b7b7b7;">中国 湖南</span>
        </div>
        
    </div>
    <div class="qccode">
        <img :src="codeImg">
        <div style="text-align: center;">
            <span style="font-size: 16px;color: #b7b7b7;">扫一扫上面的二维码图案，加我为好友</span>
        </div>
    </div>
    
  </div>
</template>

<script>
import request from "@/utils/request"
export default {
    data() {
        return {
            codeImg: require("@/assets/images/test.jpg"),
            headerImg: require("@/assets/images/profile.jpg")
        }
    },
    mounted() {
        request({
            url: "/mp/getUserInfo?openId=" + this.$route.query.openId,
            method: "get"
        }).then(res => {
            this.codeImg = "data:image/gif;base64," + res.data.codeImage
            this.headerImg = res.data.headerImg
        })
    }
}
</script>

<style lang="less">
    .magic {
        margin: 0;
        padding: 0;
        height: 100vh;
        width: 100%;
        position: relative;
        .qccode {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }
        .user-info{
            position: absolute;
            left: 32%;
            top: 22%;
            transform: translate(-50%, -50%);
            >img {
                float: left;
                width: 60px;
                height: 60px;
                border-radius: 5px;
            }
            >div {
                float: left;
                margin-left: 15px;
                padding-top: 6px;
            }
        }
    }
</style>
<template>
  <div>
    <div class="banner-header"  ref="container">
        <img :src="headerImg">
        <div class="name">
            <span style="font-size: 20px;font-weight: 700;">{{ nikeName }}&nbsp;</span><span class="iconfont" style="color: #50a9f6;">&#xe8c8;</span>
            <br>
            <span style="font-size: 14px;">地区：河南 郑州</span>
        </div>
        <br>
        <hr>
    </div>
    <div class="card" style="margin-top: 0;background-color: #ffffff;height: 30px;line-height: 20px;">
        <span style="font-weight: 500;margin-left: 30px;">备注和标签</span>
        <span class="iconfont toright">&#xe775;</span>
    </div>
    <div class="tag card" style="margin-top: 10px;">
        <div>
            <span style="font-weight: 500;">个性签名</span>
            <span style="color: rgba(0, 0, 0, .5);margin-left: 10px;">相当天空人</span>
            <img :src="showImg" id="thumbnail" @click="imgClick">
            <span class="iconfont toright">&#xe775;</span>
        </div>
        <hr>
        <div>
            <span style="font-weight: 500;">朋友圈</span>
            <span class="iconfont toright">&#xe775;</span>
        </div>
        <hr>
        <div>
            <span style="font-weight: 500;">视频号</span>
            <span class="iconfont toright">&#xe775;</span>
        </div>
    </div>
    <div class="tag card" style="text-align: center;height: 60px;line-height: 60px;margin-top: 10px;color: #5e6e8b;">
        <span style="font-size: 20px;">添加通讯录</span>
    </div>
        <img
        v-if="draggShow"
        class="dragg-img" 
        @click="zoomMax" 
        :src="showImg" 
        :style="draggStyle" />
  </div>
</template>

<script>
import $ from "jquery"
import 'jquery-ui/ui/widgets/draggable.js';
import 'jquery-ui/ui/widgets/droppable.js';
import request from "@/utils/request"
export default {
    components: {},
    mounted() {
        request({
            url: "/mp/getUserInfo?openId=" + this.$route.query.openId,
            method: "get"
        }).then(res => {
            console.log(res);
            this.showImg = res.data.uploadImg
            this.headerImg = res.data.headerImg
            this.nikeName = res.data.nikeName
        })
        var lastTouchEnd = 0;
        document.documentElement.addEventListener('touchend', function (event) {
        var now = Date.now();
        if (now - lastTouchEnd <= 300) {
            event.preventDefault();
        }
        lastTouchEnd = now;
    }, false);
    },
    data() {
        return {
            showImg: require("@/assets/images/o_vvO6fcAXtcDdbYVJMLkO6Z-UeQ.jpg"),
            headerImg: require("@/assets/images/profile.jpg"),
            imgList: [require("@/assets/images/profile.jpg")],
            zoom: 1,
            draggShow: false,
            draggStyle: {
                left: 0,
                top: 0,
                transform: null,
            },
            size: 20,
            drag: false
        }
    },
    methods: {
        imgClick(e) {
            this.draggStyle.left = e.pageX - e.offsetX + 'px'
            this.draggStyle.top = e.pageY- e.offsetY + 'px'
            this.draggShow = true
        },
        zoomMax (e) {
            if (this.zoom >= 10) {
                const _this = this
                $(document).ready(function() {
                    $(".dragg-img").on("touchstart", function(event) {
                        $('html').css({'overflow': 'hidden'}); 
                    }).on("touchmove", function(event) {
                        var touch = event.originalEvent.touches[0];
                        var touchX = touch.pageX;
                        var touchY = touch.pageY;
                        _this.draggStyle.left = touchX + "px",
                        _this.draggStyle.top = touchY + "px"
                    }).on("touchend", function(event) {
                    });
                });
            }else {
                this.zoom += 4
                this.draggStyle.transform = "scale(" + this.zoom+")"
            }
        }
    }
}
</script>

<style>
.ghost {
  opacity: 0;
}
.dragg-img {
    position: absolute;
    width: 5%;
    top: 50%;
    transition: transform 0.5s ease;
    cursor: move;
}
</style>

<style>
    body {
        font-family: "Microsoft YaHei", sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f0f0f0;
    }
    .banner-header {
        background-color: #ffffff;
        padding: 10px; /* 添加一些内边距 */
        overflow: auto; /* 清除浮动 */
    }
    .name {
        margin-top: 30px;
    }
    .banner-header>img {
        margin: 30px 10px 10px 30px;
        float: left; /* 向左浮动 */
        width: 55px;
        border-radius: 5px;
    }
    .tag {
        width: 100%;
        background-color: #ffffff;
    }
    hr{
        border-color: rgba(240, 240, 240, .2);
        width: 90%;
    }
    .card{
        line-height: 70px;
    }
    .card>div>span:first-child{
        margin-left: 30px;
    }
    .toright{
        float: right;
        margin-right: 30px;
    }
    #thumbnail{
        width: 15px;
        transition: transform 0.5s ease;
    }
</style>
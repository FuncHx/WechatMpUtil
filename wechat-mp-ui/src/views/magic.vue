<template>
  <div>
    <div class="banner-header">
        <img :src="headerImg">
        <div class="name">
            <span style="font-size: 20px;font-weight: 700;">W&nbsp;</span><span class="iconfont" style="color: #50a9f6;">&#xe8c8;</span>
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
    <div class="tag card" style="text-align: center;height: 40px;line-height: 40px;margin-top: 10px;color: #50a9f6;">
        <span>添加通讯录</span>
    </div>
    <img v-if="draggShow" @mousedown="onMove($event, item)" draggable="false" @mouseenter="move($event)" class="dragg-img" @click="zoomMax" v-for="(item, index) in imgList" :key="index" :src="item" :style="draggStyle" />
  </div>
</template>

<script>
import draggable from 'vuedraggable';
export default {
    components: {draggable},
    data() {
        return {
            showImg: require("@/assets/images/profile.jpg"),
            headerImg: require("@/assets/images/profile.jpg"),
            imgList: [require("@/assets/images/profile.jpg")],
            dragOptions: {
                animation: 200,
                ghostClass: 'ghost',
            },
            zoom: 1,
            draggShow: false,
            draggStyle: {
                left: 0,
                top: 0,
                transform: null,
                width: 20 + "px",
                height: 20 + "px"
            },
            size: 20,
        }
    },
    methods: {
        onDragEnd(e) {
            console.log(e);
        },
        onMove (evt, item) { // 拖拽过程中触发
            this.startx=e.pageX; this.starty=e.pageY
            document.addEventListener('mousemove',this.mousemove)
            item.addEventListener('mouseup',this.mouseup)
        },
        mousemove(e){
            this.x=e.pageX-this.startx+this.endx
            this.y=e.pageY-this.starty+this.endy
            console.log(e);
        },
        mouseup(){
            // 解除绑定mousemove
            document.removeEventListener('mousemove',this.mousemove,false)
            this.endx=this.x
            this.endy=this.y
        },

        imgClick(e) {
            this.draggStyle.left = e.pageX - e.offsetX + 'px'
            this.draggStyle.top = e.pageY- e.offsetY + 'px'
            this.draggShow = true
        },
        zoomMax (e) {
            this.zoom += 1
            this.draggStyle.transform = "scale(" + this.zoom+")"
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
    width: 15px;
    transition: transform 0.5s ease;
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
        margin-top: 100px;
    }
    .banner-header>img {
        margin: 100px 10px 10px 30px;
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
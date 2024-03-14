<template>
  <div>
    <el-tabs :tab-position="'top'" @tab-click="handleClick" style="height: 200px;">
        <el-tab-pane name="picture">
            <span slot="label"><Icon :icon="'tupian1'" :size="'20px'"/> 
                图片</span>
                <div class="add_but">
                    <el-upload
                            :action="actionUrl"
                            :headers="headers"
                            multiple
                            :limit="1"
                            :on-success="handleUploadSuccess"
                            :file-list="fileList"
                            :before-upload="beforeImageUpload"
                            :data="uploadData">
                        <el-button size="mini" type="primary">点击上传</el-button>
                        <div slot="tip" class="el-upload__tip">
                        支持bmp/png/jpeg/jpg/gif格式，大小不超过2M
                        </div>
                    </el-upload>
                </div>
                <div class="waterfall" v-loading="tableLoading">
                <div class="waterfall-item" v-for="item in tableData" :key='item.id'>
                  <a target="_blank" :href="item.url">
                    <img class="material-img" :src="item.url">
                    <div class="item-name">{{item.name}}</div>
                  </a>
                  <el-row class="ope-row">
                    <el-button type="danger" icon="el-icon-delete" circle @click="delMaterial(item)"></el-button>
                  </el-row>
                </div>
              </div>
              <div v-if="tableData.length <=0 && !tableLoading" class="el-table__empty-block">
                <span class="el-table__empty-text">暂无数据</span>
              </div>
              <el-pagination
                      @size-change="sizeChange"
                      @current-change="currentChange"
                      :current-page.sync="page.currentPage"
                      :page-sizes="[10, 20]"
                      :page-size="page.pageSize"
                      layout="total, sizes, prev, pager, next, jumper"
                      :total="page.total"
                      class="pagination"
              >
              </el-pagination>
        </el-tab-pane>
        <el-tab-pane  name="voice">
            <span slot="label"><Icon :icon="'wode1'" :size="'20px'"/>语音</span>
            语音
        </el-tab-pane>
        <el-tab-pane  name="video">
            <span slot="label"><Icon :icon="'shipin1'" :size="'20px'"/>视频</span>
            视频</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Icon from "@/components/util/icon.vue"
export default {
    components: {Icon},
    data() {
        return {
            
        }
    },
    methods: {
        handleClick(tab) {
            console.log(tab.name);
        }
    }

}
</script>

<style>

</style>
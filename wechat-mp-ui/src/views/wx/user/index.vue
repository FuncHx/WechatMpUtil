<!--
MIT License

Copyright (c) 2020 www.joolun.com

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
-->
<template>
    <div class="app-container">
        <avue-crud ref="crud"
                   :page="page"
                   :data="tableData"
                   :table-loading="tableLoading"
                   :option="tableOption"
                   @on-load="getPage"
                   @refresh-change="refreshChange"
                   @search-change="searchChange"
                   @selection-change="selectionChange">
            <template slot="subscribe" slot-scope="scope">
                <el-tag size="mini" effect="dark"
                        :type="scope.row.subscribe == '1' ? 'success' : scope.row.subscribe == '0' ? 'danger' : 'warning'">
                    {{scope.row.$subscribe}}
                </el-tag>
            </template>
            <template slot="sex" slot-scope="scope">
                <el-tag size="mini" effect="light"
                        :type="scope.row.sex == '1' ? '' : scope.row.sex == '2' ? 'danger' : 'warning'">
                    {{scope.row.$sex}}
                </el-tag>
            </template>
            <template slot="latitude" slot-scope="scope">
                <el-link v-if="scope.row.longitude" type="primary" target="_blank"
                         :href="'https://map.qq.com/?type=marker&isopeninfowin=1&markertype=1&pointx='+scope.row.longitude+'&pointy='+scope.row.latitude+'&name='+scope.row.nickName+'&ref=joolun'">
                    <i class="el-icon-map-location"></i>
                </el-link>
            </template>
            <template slot="menuLeft">
                <el-button type="success"
                           @click="dialogTagging = true; taggingType = 'tagging'"
                           size="small"
                           icon="el-icon-document"
                           >打标签
                </el-button>
                <el-button type="warning"
                           @click="dialogTagging = true; taggingType = 'unTagging'"
                           size="small"
                           icon="el-icon-document"
                           >去除标签
                </el-button>
                <el-button type="danger"
                           size="small"
                           icon="el-icon-refresh"
                           >同步用户
                </el-button>
                <el-dialog
                        :title='taggingType == "tagging"?"请选择要打的标签":"请选择要去除的标签"'
                        :visible.sync="dialogTagging"
                        width="30%">
                    <el-checkbox-group v-model="checkedTags">
                        <el-checkbox v-for="tag in userTagsData" :label="tag.id" :key="tag.id">{{tag.name}}
                        </el-checkbox>
                    </el-checkbox-group>
                    <span slot="footer" class="dialog-footer">
                  <el-button @click="dialogTagging = false">取 消</el-button>
                  <el-button type="primary" @click="toTagging">确 定</el-button>
                </span>
                </el-dialog>
            </template>
            <template slot="menu" slot-scope="scope">
                <el-button type="text"
                           icon="el-icon-edit"
                           size="small"
                           plain
                           @click="updateRemark(scope.row,scope.index)">修改备注
                </el-button>
                <!-- <el-button type="text"
                           v-hasPermi="['wxmp:wxuser:index']"
                           icon="el-icon-chat-line-round"
                           size="small"
                           plain
                           @click="wxMsgDo(scope.row,scope.index)">消息
                </el-button> -->
            </template>
            <template slot="tagidListSearch" slot-scope="scope">
                <el-select v-model="scope.row.tagidList" placeholder="请选择">
                    <el-option
                            v-for="item in userTagsData"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </template>
        </avue-crud>
        <!-- <el-dialog title="用户消息" :visible.sync="dialogMsgVisible" width="40%">
            <WxMsg :wxUserId="wxUserId" v-if="dialogMsgVisible"></WxMsg>
        </el-dialog> -->
    </div>
</template>

<script>
    import {tableOption} from '@/const/crud/wxmp/wxuser'
    import {getPage, updateRemark} from "@/api/mp/wxuser"
    import { getTag,tagging } from '@/api/mp/wxtag'
    export default {
        name: 'wxuser',
        components: {
        },
        data() {
            return {
                tableData: [],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条
                },
                paramsSearch: {},
                tableLoading: false,
                tableOption: tableOption,
                selectionData: [],
                dialogTagging: false,
                checkedTags: [],
                userTagsData: [],
                taggingType: '',
                dialogMsgVisible: false,
                wxUserId: '',
            }
        },
        created() {
            this.listUserTags()
        },
        methods: {
            listUserTags() {
                this.tableLoading = true
                getTag().then(response => {
                    if (response.code == 200) {
                        let userTagsData = response.data
                        this.userTagsData = userTagsData
                        this.$refs.crud.DIC.tagidList = userTagsData
                    } else {
                        this.$message.error('获取用户标签出错：' + response.msg)
                    }
                    this.tableLoading = false
                })
            },
            getPage(page, params) {
                this.tableLoading = true
                if (params) {
                    this.paramsSearch = params
                    if (params.tagidList) {
                        params.tagidList = [params.tagidList]
                    }
                }else {
                    params = {}
                }
                getPage(page, params).then(response => {
                    this.tableData = response.data.records
                    this.page.total = response.data.total
                    this.page.currentPage = page.currentPage
                    this.page.pageSize = page.pageSize
                    this.tableLoading = false
                }).catch(() => {
                    this.tableLoading = false
                })
            },
            searchChange(params, done) {
                this.paramsSearch = params
                this.page.currentPage = 1
                this.getPage(this.page, this.paramsSearch)
                done()
            },
            selectionChange(list) {
                this.selectionData = list
            },
            toTagging() {
                let openIdList = []
                for (let i = 0; i < this.selectionData.length; i++) {
                    openIdList.push(this.selectionData[i].openId)
                }
                if (this.checkedTags.length <= 0) {
                    this.$message.error('请选择标签')
                    return
                }
                if (openIdList.length <= 0) {
                    this.$message.error('请选择用户')
                    return
                }
                this.tableLoading = true
                tagging({
                    taggingType: this.taggingType,
                    id: this.checkedTags,
                    openidList: openIdList
                }).then(response => {
                    this.tableLoading = false
                    this.checkedTags = []
                    this.$refs.crud.selectClear()
                    if (response.code == 200) {
                        this.getPage(this.page, this.paramsSearch)
                    } else {
                        this.$message.error('打标签出错：' + response.msg)
                    }
                }).catch(() => {
                    this.tableLoading = false
                })
                this.dialogTagging = false
            },
            updateRemark(row, index) {
                this.$prompt('请输入备注', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputPattern: /\S/,
                    inputErrorMessage: '输入不能为空'
                }).then(({value}) => {
                    this.tableLoading = true
                    row.remark = value
                    updateRemark(row).then(response => {
                        this.tableLoading = false
                        if (response.code == 200) {
                            this.tableData.splice(index, 1, Object.assign({}, row))
                            this.$message({
                                showClose: true,
                                message: '修改成功',
                                type: 'success'
                            })
                            this.getPage(this.page)
                        } else {
                            this.$message.error(response.msg)
                        }
                    }).catch(() => {
                        this.tableLoading = false
                    })
                }).catch(err => {})
            },
            /**
             * 刷新回调
             */
             refreshChange(page) {
                this.getPage(this.page, null)
            }
        }
    }
</script>

<style scoped>
</style>

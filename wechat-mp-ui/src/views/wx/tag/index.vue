<template>
    <div>
        <avue-crud ref="crud"
                :data="tableData"
                :table-loading="tableLoading"
                :option="tableOption"
                @on-load="getPage"
                @refresh-change="getPage"
                @row-update="handleUpdate"
                @row-del="handleDel"
                @row-save="handleSave">
        </avue-crud>
    </div>
</template>

<script>
import { tableOption } from '@/const/crud/wxmp/wxtag'
import { getTag, delTag, updateTag, addTag } from '@/api/mp/wxtag';
export default {
    components: {},
    data() {
        return {
            tableOption: tableOption,
            tableData: [],
            tableLoading: false,
        }
    },
    mounted() {
        this.getPage()
    },
    methods: {
        getPage() {
            getTag().then(resp => {
                this.tableData = resp.data
            })
        },
        handleDel: function(row, index) {
            var _this = this
            this.$confirm('是否确认删除', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                delTag(row.id).then(resp => {
                    if (resp.code == 200) {
                        this.$message.success(resp.message)
                        this.getPage()
                    }
                }).catch(err => {})
            }).catch(err => {
                this.$message.info("取消删除")
            })
        },
        handleUpdate(row, index, done, loading) {
            updateTag(row).then(resp => {
                if(resp.code == 200) {
                    this.$message.success(resp.message)
                    done()
                    this.getPage()
                }
            }).catch(re => {})
        },
        handleSave(row, index, done, loading){
            addTag(row).then(resp => {
                if(resp.code == 200) {
                    this.$message.success(resp.message)
                    done()
                    this.getPage()
                }
            }).catch(re => {loading()})
        }
    }

}
</script>

<style>

</style>
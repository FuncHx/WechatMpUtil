<template>
  <div>
    <!-- 添加或修改菜单对话框 -->
    <el-dialog :title="formType==='add'? '新增菜单':'修改菜单'" :visible.sync="open" width="600px" :before-close="close" append-to-body>
      <el-form ref="form" :model="form" :rules="rules"  label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="上级菜单">
              <treeselect
                v-model="form.parentId"
                :options="menuOptions"
                :normalizer="tenantIdnormalizer"
                :show-count="true"
                placeholder="选择上级菜单"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="菜单类型" prop="menuType">
              <el-radio-group v-model="form.menuType">
                <el-radio label="M">目录</el-radio>
                <el-radio label="C">菜单</el-radio>
                <el-radio label="F">按钮</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item v-if="form.menuType != 'F'" label="菜单图标">
              <el-popover
                placement="bottom-start"
                width="460"
                trigger="click"
                @show="$refs['iconSelect'].reset()"
              >
                <IconSelect ref="iconSelect" @selected="selected" />
                <el-input slot="reference" v-model="form.icon" placeholder="点击选择图标" readonly>
                  <Icon slot="prefix" :icon="form.icon" :size="'20px'" />
                </el-input>
              </el-popover>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="菜单名称" prop="menuName">
              <el-input v-model="form.menuName" placeholder="请输入菜单名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示排序" prop="orderNum">
              <el-input-number v-model="form.orderNum" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.menuType != 'F'" label="路由地址" prop="path">
              <el-input v-model="form.path" placeholder="请输入路由地址" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.menuType == 'C'">
            <el-form-item label="组件路径" prop="component">
              <el-input v-model="form.component" placeholder="请输入组件路径" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.menuType != 'M'" label="权限标识">
              <el-input v-model="form.perms" placeholder="请权限标识" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.menuType != 'F'" label="显示状态">
              <el-radio-group v-model="form.visible">
                <el-radio label="0">显示</el-radio>
                <el-radio label="1">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.menuType != 'F'" label="菜单状态">
              <el-radio-group v-model="form.status">
                <el-radio label="0">正常</el-radio>
                <el-radio label="1">禁用</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="close()">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request"
import Treeselect from "@riophae/vue-treeselect";
import IconSelect from "@/components/IconSelect"
import Icon from "@/components/util/icon.vue"
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { addMenu, updateMenu } from "@/api/menu";
export default {
    components: {Treeselect, IconSelect, Icon},
    data() {
        return {
            menuOptions: [],
            rules: {
              menuName: [
                { required: true, message: "菜单名称不能为空", trigger: "blur" }
              ],
              orderNum: [
                { required: true, message: "菜单顺序不能为空", trigger: "blur" }
              ],
              path: [
                { required: true, message: "路由地址不能为空", trigger: "blur" }
              ]
          }
        }
    },
    props: ["form", "open", "formType"],
    created() {
        this.getRouters()
    },
    methods: {
        close() {
            this.$emit("close")
        },
        getRouters() {
            request({
                url: "/menu/list",
                method: "post",
                data: {}
            }).then(res => {
                this.menuOptions.push({id: 0, menuName: "主类目", children: []})
                this.menuOptions[0].children = res.data
            })
        },
        tenantIdnormalizer(node, instanceId) {
            if (node.children && !node.children.length) {
                delete node.children
            }
            return {
                id: node.id,
                label: node.menuName,
                children: node.children
            }
        },
        submitForm () {
          this.$refs["form"].validate(valid => {
            if(valid) {
              var response = null
              if (this.form.id === null) {
                response = addMenu(this.form)
              }else {
                response = updateMenu(this.form)
              }
              response.then(res => {
                this.$message({type:res.code===200? "success": "error", message: res.message})
                if (res.code === 200) {
                  this.close()
                  this.$router.go(0)
                }
              })
              
            }
          })
        },
        selected (icon) {
            this.form.icon = icon
        }
    }
}
</script>

<style>

</style>
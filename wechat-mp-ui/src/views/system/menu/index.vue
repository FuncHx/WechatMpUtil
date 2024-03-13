<template>
  <div>
    
    <el-form :inline="true" size="mini" :model="searchForm" class="demo-form-inline">
        <el-form-item label="菜单名称">
            <el-input clearable v-model="searchForm.menuName" placeholder="菜单名称"></el-input>
        </el-form-item>
        <el-form-item label="状态">
            <el-select clearable  v-model="searchForm.status" placeholder="状态">
              <el-option label="正常" value="0"></el-option>
              <el-option label="禁用" value="1"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
            <el-button type="primary" plain @click="open = true; formType='add'">新增</el-button>
        </el-form-item>
    </el-form>

    <el-table
    :data="menus"
    style="width: 100%;margin-bottom: 20px;"
    row-key="id"
    size="medium"
    :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column
        prop="orderNum"
        label="序号"
        sortable
        width="100">
      </el-table-column>
      <el-table-column
        prop="menuName"
        label="菜单名称"
        width="150"
        sortable>
      </el-table-column>

      <el-table-column
        prop="menuType"
        label="菜单类型"
        width="100">
        <template slot-scope="scope">
            <span>{{ scope.row.menuType === "M"? "目录": scope.row.menuType==="C" ? "菜单": "按钮" }}</span>
        </template>
      </el-table-column>

      <el-table-column
        prop="perms"
        label="权限标识"
        width="180">
      </el-table-column>

      <el-table-column
        prop="icon"
        label="菜单图标"
        width="120">
        <template slot-scope="scope">
          <Icon :icon="scope.row.icon" style="margin-right: 15px;" :size="'20'"/>
        </template>
      </el-table-column>

      <el-table-column
        prop="status"
        label="菜单状态"
        width="100">
      </el-table-column>

      <el-table-column
        prop="component"
        label="组件名称">
      </el-table-column>

      <el-table-column
        prop="path"
        label="路由地址">
      </el-table-column>

      <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>

    </el-table>
    <MenuForm :open="open" :form="form" :formType="formType" @close="handleClose"/>
  </div>
</template>

<script>
import { mapState } from "vuex";
import MenuForm from "@/components/menuForm"
import Icon from "@/components/util/icon.vue"
import {deleteMenu} from "@/api/menu"
export default {
  components: {
    Icon,
    MenuForm
  },
  computed: {
    ...mapState("menu", ["menus"]),

  },
  data() {
    return {
        open: false,
        form: {
          component: "",
          icon: "",
          menuName: "",
          id: null,
          menuType: "",
          orderNum: null,
          parentId: 0,
          path: "",
          perms: "",
          remark: "",
          status: "0",
          visible: "0"
        },
        formType: 'add',
        searchForm: {
          menuName: "",
          status: ""
        }
      }
  },
  created() {
    this.onSubmit()
  },
  methods: {
    onSubmit() {
      this.$store.dispatch("menu/GetMenus", this.searchForm);
    },
    handleEdit(index, row) {
      this.form = row
      this.formType = "edit"
      this.open = true
    },
    handleDelete(index, row) {

      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteMenu(row.id).then(res => {
            this.$message({type: res.code === 200 ? "success":"error", message: res.message})
            if (res.code === 200) {
              this.$router.go(0)
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    handleClose() {
      this.open = false
      this.form = {
          component: "",
          icon: "",
          menuName: "",
          id: null,
          menuType: "",
          orderNum: null,
          parentId: 0,
          path: "",
          perms: "",
          remark: "",
          status: "0",
          visible: "0"
        }
    }
  }
}
</script>

<style>

</style>
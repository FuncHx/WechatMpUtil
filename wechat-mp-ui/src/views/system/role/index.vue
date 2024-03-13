<template>
    <div>
        
        <el-form :inline="true" size="mini" :model="searchForm" class="demo-form-inline">
        <el-form-item label="角色名称">
            <el-input clearable v-model="searchForm.roleName" placeholder="角色名称"></el-input>
        </el-form-item>
        <el-form-item label="权限标识">
            <el-input clearable v-model="searchForm.role" placeholder="权限标识"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
            <el-button type="primary" plain @click="open = true; formType='add'">新增</el-button>
        </el-form-item>
    </el-form>

      <el-table
      :data="roles"
      style="width: 100%;margin-bottom: 20px;"
      row-key="id"
      size="medium"
      default-expand-all
      >
        <el-table-column
          prop="id"
          label="序号"
          sortable>
        </el-table-column>
        <el-table-column
          prop="roleName"
          label="角色名称"
          sortable>
        </el-table-column>
  
        <el-table-column
          prop="role"
          label="权限标识">
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


      <el-dialog :title="formType==='add'? '新增角色':'修改角色'" :visible="open" width="600px" :before-close="handleClose" append-to-body>
        <el-form ref="form" :model="form" :rules="rules"  label-width="80px">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="角色名称" prop="roleName">
                        <el-input v-model="form.roleName"></el-input>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="权限标识" prop="role">
                        <el-input v-model="form.role"></el-input>
                    </el-form-item>  
                </el-col>   
                <el-col :span="24">
                    <el-form-item label="权限">
                        <el-tree
                            :data="treeSelect"
                            show-checkbox
                            default-expand-all
                            node-key="id"
                            ref="tree"
                            :props="defaultProps"
                            highlight-current
                            >
                        </el-tree>
                    </el-form-item>
                </el-col>
            </el-row>       
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="handleClose">取 消</el-button>
            <el-button type="primary" @click="commit">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { getRoles, updateRoles, addRole, deleteRole } from "@/api/role";
  export default {
    components: {
    },
    computed: {
    },
    data() {
      return {
          open: false,
          form: {
            id: null,
            role: "",
            roleName: "",
            data: {
                permission: []
            }
          },
          treeSelect: [],
          roles: [],
          formType: 'add',
          searchForm: {
            roleName: "",
            role: ""
          },
          rules: {
              roleName: [
                { required: true, message: "菜单名称不能为空", trigger: "blur" }
              ],
              role: [
                { required: true, message: "菜单顺序不能为空", trigger: "blur" }
              ]
          },
          defaultProps: {
            children: 'children',
            label: 'label'
            }
        }
    },
    created() {
      this.onSubmit()
    },
    methods: {
      setKeys(keys) {
        this.$refs.tree.setCheckedKeys(keys);
      },
      onSubmit() {
        getRoles(this.searchForm).then(res => {
            if (res.code === 200) {
                this.roles = res.data.roles
                this.treeSelect = res.data.treeSelect
            }
        })
      },
      handleEdit(index, row) {
        this.form = row
        this.formType = "edit"
        this.open = true
        this.form = row
        var keys = []
        row.data.permission.forEach(e => keys.push(e.id))
        this.setKeys(keys)
      },
      handleDelete(index, row) {
        this.$confirm('此操作将永久删除该角色, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            deleteRole(row.id).then(res => {
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
            id: null,
            role: "",
            roleName: "",
            data: {
                permission: []
            }
          }
        this.$refs.tree.setCheckedKeys([]);
      },
      commit() {
        this.form.data.permission = this.$refs.tree.getCheckedKeys()
        if (this.form.id === null) {
          addRole(this.form).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message)
              this.handleClose()
              this.onSubmit()
            }
          })
        }else {
          updateRoles(this.form).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message)
              this.handleClose()
              this.onSubmit()
            }
          })
        }
      }
    }
  }
  </script>
  
  <style>
  
  </style>
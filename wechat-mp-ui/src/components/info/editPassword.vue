<template>
    <el-form ref="form" :model="form" label-width="80px">
        <el-form-item :required="true" prop="oldPassword" label="旧密码" :rules="{
        required: true, message: '不能为空', trigger: 'blur'
        }">
            <el-input type="password" v-model="form.oldPassword" size="mini"></el-input>
        </el-form-item>

        <el-form-item :required="true" prop="newPasswordOnce" label="新密码" :rules="{
        required: true, message: '不能为空', trigger: 'blur'
        }">
            <el-input type="password"  v-model="form.newPasswordOnce" size="mini"></el-input>
        </el-form-item>

        <el-form-item :required="true" prop="newPassword" label="确认密码" :rules="{
        required: true, message: '不能为空', trigger: 'blur'
        }">
            <el-input type="password" v-model="form.newPassword" size="mini"></el-input>
        </el-form-item>
        <el-button size="mini" @click="resetForm('form')">重置</el-button>
        <el-button size="mini" type="primary" @click="submitForm('form')">保存</el-button>
    </el-form>
</template>

<script>
import {editPassword} from "@/api/user"
export default {
    data () {
        return {
            form: {
                oldPassword: "",
                newPasswordOnce: "",
                newPassword: ""
            }
        }
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                if (this.form.newPassword !== this.form.newPasswordOnce) {
                    this.$message({type: "error", message: "两次密码不一致！"})
                    return false
                }
                editPassword(this.form).then(res => {
                    if (res.code == 200) {
                        this.$message({type: "success", message: "修改成功！"})
                        this.$store.dispatch("user/LogOut")
                    }else{
                        this.$message({type: "error", message: "修改失败！请联系管理员"})
                    }
                }).catch(() => {})
            } else {
                return false;
            }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
    }
}
</script>

<style>

</style>
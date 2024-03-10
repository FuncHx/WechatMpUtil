<template>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item :required="true" prop="nickName" label="用户名称" :rules="{
      required: true, message: '名称不能为空', trigger: 'blur'
    }">
        <el-input  v-model="form.nickName" :value="userInfo.nickName" size="mini"></el-input>
    </el-form-item>
    <el-form-item label="手机号码" prop="phonenumber" :rules="{
      required: true, message: '手机号码不能为空', trigger: 'blur'
    }">
        <el-input v-model="form.phonenumber" size="mini"></el-input>
    </el-form-item>
    <el-form-item :required="true" prop="email" label="邮箱地址">
        <el-input v-model="form.email" size="mini" :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },
      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
    ]"></el-input>
    </el-form-item>
    <el-form-item :required="true" prop="sex" label="性别" :rules="{
      required: true, message: '性别不能为空', trigger: 'blur'
    }">
        <el-radio-group v-model="form.sex">
            <el-radio label="0">男</el-radio>
            <el-radio label="1">女</el-radio>
        </el-radio-group>
    </el-form-item>
    <el-button size="mini" type="primary" @click="submitForm('form')">保存</el-button>
  </el-form>
</template>

<script>
import { mapState } from 'vuex';
import { updateUserInfo } from '@/api/user';
export default {
    data () {
        return {
            form: {
                id: null,
                nickName: "",
                phonenumber: "",
                email: "",
                sex: ""
            }
        }
    },
    computed: {
        ...mapState("user", ["userInfo"])
    },
    created() {
        console.log(this.userInfo);
        this.form = JSON.parse(JSON.stringify(this.userInfo))
    },
    methods: {
        submitForm() {
            updateUserInfo(this.form).then(res => {
                if (res.code == 200){
                    this.$message({type: "success", message: "修改成功"})
                    this.$store.dispatch("user/GetUserInfo")
                    window.location.reload();
                }else{
                    this.$message({type: "error", message: res.message})
                }
            }).catch(error => {})
        },
    }
}
</script>

<style>
    
</style>
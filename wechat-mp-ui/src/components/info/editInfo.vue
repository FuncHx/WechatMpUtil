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
export default {
    data () {
        return {
            form: {
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
        this.form = JSON.parse(JSON.stringify(this.userInfo))
    },
    methods: {
        submitForm() {
            console.log(this.form);
        },
    }
}
</script>

<style>
    
</style>
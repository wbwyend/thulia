<template>
    <div style="width: 100%; height: 650px; background-color: rgba(0, 0, 0, 0);">
        <el-container style="width: 800px; height: 650px;">
            <el-form :model="password" :rules="rules" ref="modifyRef" label-width="80px"
                style="width: 100%; margin-left: 50px; margin-right: 50px; margin-top: 20px;">
                <el-row class="input" style="margin-bottom: 10px;">
                    <div style="text-align: center; font-size: 24px;">修改密码</div>
                </el-row>
                <el-row class="input">
                    <el-form-item label="原密码" prop="oldpassword">
                        <el-input placeholder="原密码" v-model="password.oldpassword" show-password></el-input>
                    </el-form-item>
                </el-row>
                <el-row class="input">
                    <el-form-item label="新密码" prop="newpassword">
                        <el-input placeholder="新密码" v-model="password.newpassword" show-password></el-input>
                    </el-form-item>
                </el-row>
                <el-row class="input">
                    <el-form-item label="确认密码" prop="secondpassword">
                        <el-input placeholder="请再次输入密码" v-model="password.secondpassword" show-password></el-input>
                    </el-form-item>
                </el-row>
                <el-row style="width: 100%; height: 40px;">
                    <div style="margin: auto; width: 100%;">
                        <el-form-item>
                            <el-button @click="modify('modifyRef')"
                                style="width: 100%; height: 40px; font-size: 18px; border-radius: 1px; background-color: rgba(0, 0, 0, 0.06); border-width: 0px;">保存</el-button>
                        </el-form-item>
                    </div>
                </el-row>
            </el-form>
        </el-container>
    </div>
</template>
<script>
export default {
    name: 'password',
    data() {
        const validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value != this.password.newpassword) {
                callback(new Error('两次密码不一致'));
            } else {
                callback();
            }
        };
        return {
            user: JSON.parse(localStorage.getItem('uInfo') || '{}'),
            password: {
                id: '',
                oldpassword: '',
                newpassword: '',
                secondpassword: ''
            },
            rules: {
                oldpassword: [
                    { required: true, message: '请输入原密码', trigger: 'blur' }
                ],
                newpassword: [
                    { required: true, message: '请输入新密码', trigger: 'blur' }
                ],
                secondpassword: [
                    { required: true, validator: validatePass, trigger: 'blur' }
                ]
            }
        }
    },
    mounted() {

    },
    methods: {
        modify() {
            this.$refs['modifyRef'].validate((valid) => {
                if (valid) {
                    this.password.id = JSON.parse(localStorage.getItem('uInfo') || '{}').uid
                    this.$request.post('/user/modify/password', this.password).then(res => {
                        if (res.code === '200') {
                            this.$message.success('密码修改成功')
                            localStorage.setItem("uInfo", JSON.stringify(res.data))
                            localStorage.setItem("token", JSON.stringify(res.data.token));
                            this.password = {}
                        } else {
                            this.$message.error(res.msg)
                        }
                    })
                }
            })
        }
    }
}
</script>
<style scoped >
.input {
    width: 100%;
    height: 40px;
}
</style>
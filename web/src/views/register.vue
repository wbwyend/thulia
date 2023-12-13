<template>
    <div>
        <el-container>
            <el-header type="flex"
                style="width: 480px; height: 140px; margin: auto; background-color: rgb(255, 255, 255);"></el-header>
            <el-main type="flex" style="width: 480px; height: 480px; margin: auto; background-color: rgb(255, 255, 255);
            border-color: rgba(0, 0, 0, 0.2); border-style: solid; border-width: 1px; border-radius: 5px;">
                <el-form :model="user" :rules="rules" ref="registerRef">
                    <el-row style="width: 100%; height: 32px">
                        <div style="text-align: center; font-size: 24px;">账号注册</div>
                    </el-row>
                    <el-row style="width: 100%; height: 40px;">
                        <div style="margin: auto; width: 100%;">
                            <el-form-item prop="username">
                                <el-input placeholder="账号" v-model="user.username">
                                    <el-button slot="prepend" icon="el-icon-user"></el-button>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-row>
                    <el-row style="width: 100%; height: 40px;">
                        <div style="margin: auto; width: 100%;">
                            <el-form-item prop="password">
                                <el-input placeholder="密码" v-model="user.password" show-password>
                                    <el-button slot="prepend" icon="el-icon-unlock"></el-button>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-row>
                    <el-row style="width: 100%; height: 40px;">
                        <div style="margin: auto; width: 100%;">
                            <el-form-item prop="password2nd">
                                <el-input placeholder="请再次输入密码" v-model="user.password2nd" show-password>
                                    <el-button slot="prepend" icon="el-icon-lock"></el-button>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-row>
                    <el-row style="width: 100%; height: 40px;">
                        <div style="margin: auto; width: 100%;">
                            <el-form-item prop="email">
                                <el-input placeholder="邮箱" v-model="user.email">
                                    <el-button slot="prepend" icon="el-icon-message"></el-button>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-row>
                    <el-row style="width: 100%; height: 40px; margin-top: 20px;">
                        <el-col :span="14">
                            <div style="margin: auto; width: 100%;">
                                <el-form-item prop="validcode">
                                    <el-input placeholder="验证码" v-model="user.validcode">
                                        <el-button slot="prepend" icon="el-icon-warning-outline"></el-button>
                                    </el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="10">
                            <div
                                style="margin-left: 40px; height: 40px; width: 120px; background-color: rgba(0, 0, 0, 0.025);">
                                <ValidCode @input="createValidCode"></ValidCode>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row style="width: 100%; height: 40px;">
                        <div style="margin: auto; width: 100%;">
                            <el-form-item>
                                <el-button @click="register('user')"
                                    style="width: 100%; height: 40px; font-size: 18px; border-radius: 1px; background-color: rgba(0, 0, 0, 0.06); border-width: 0px;">注册</el-button>
                            </el-form-item>
                        </div>
                    </el-row>

                    <el-row style="width: 100%; height: 25px;">
                        <div style="display: flex;">
                            <div style="flex: 1;">
                                <span>已有账号？请</span>
                                <a href="/login" style="text-decoration: none;">
                                    <span>登录</span>
                                </a>
                            </div>
                            <div style="flex: 1; text-align: right;">
                                <a href="/register/shop" style="text-decoration: none;">注册成为商家</a>
                            </div>
                        </div>

                    </el-row>
                </el-form>
            </el-main>
            <el-footer style="width: 480px; height: 100px; margin: auto; background-color: rgb(255, 255, 255);"></el-footer>
        </el-container>
    </div>
</template>

<script>

import ValidCode from "@/components/ValidCode.vue"

export default {
    name: 'register',
    components: {
        ValidCode
    },
    data() {
        const validateCode = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入验证码'));
            } else if (value.toLowerCase() != this.pageValidCode.toLowerCase()) {
                callback(new Error('验证码错误'));
            } else {
                callback();
            }
        };
        const validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value != this.user.password) {
                callback(new Error('两次密码不一致'));
            } else {
                callback();
            }
        };
        const validateEmail = (rule, value, callback) => {
            var emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/;
            if (value === '') {
                callback(new Error('请输入邮箱'));
            } else if (!emailReg.test(value)) {
                callback(new Error('邮箱格式错误'));
            } else {
                callback();
            }
        };
        return {
            user: {
                username: '',
                password: '',
                password2nd: '',
                email: '',
                validcode: ''
            },
            rules: {
                username: [
                    { required: true, message: '请输入账号', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                ],
                password2nd: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                email: [
                    { validator: validateEmail, trigger: 'blur' }
                ],
                validcode: [
                    { validator: validateCode, trigger: 'blur' }
                ]
            },
            pageValidCode: ''
        }
    },
    mounted() {

    },
    methods: {
        createValidCode(data) {
            this.pageValidCode = data;
        },
        register() {
            this.$refs['registerRef'].validate((valid) => {
                if (valid) {
                    this.$request.post('/register', this.user).then(res => {
                        if (res.code === '200') {
                            this.$router.push('/login')
                            this.$message.success('注册成功')
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
<template>
    <div>
        <el-container>
            <el-header type="flex" style="width: 360px; height: 140px; margin: auto; background-color: rgb(255, 255, 255);">

            </el-header>
            <el-main type="flex" style="width: 360px; height: 360px; margin: auto; background-color: rgb(255, 255, 255);
            border-color: rgba(0, 0, 0, 0.2); border-style: solid; border-width: 1px; border-radius: 5px;">
                <el-form :model="shop" :rules="rules" ref="loginRef">
                    <el-row style="width: 100%; height: 32px">
                        <div style="text-align: center; font-size: 24px;">商家登录</div>
                    </el-row>
                    <el-row style="width: 100%; height: 40px;">
                        <div style="margin: auto; width: 100%;">
                            <el-form-item prop="username">
                                <el-input placeholder="账号" v-model="shop.username">
                                    <el-button slot="prepend" icon="el-icon-user"></el-button>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-row>
                    <el-row style="width: 100%; height: 40px;">
                        <div style="margin: auto; width: 100%;">
                            <el-form-item prop="password">
                                <el-input placeholder="密码" v-model="shop.password" show-password>
                                    <el-button slot="prepend" icon="el-icon-lock"></el-button>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-row>

                    <el-row style="width: 100%; height: 40px; margin-top: 20px;">
                        <el-col :span="14">
                            <div style="margin: auto; width: 100%;">
                                <el-form-item prop="validcode">
                                    <el-input placeholder="验证码" v-model="shop.validcode">
                                        <el-button slot="prepend" icon="el-icon-warning-outline"></el-button>
                                    </el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="10">
                            <div
                                style="margin-left: 15px; height: 40px; width: 120px; background-color: rgba(0, 0, 0, 0.025);">
                                <ValidCode @input="createValidCode"></ValidCode>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row style="width: 100%; height: 40px;">
                        <div style="margin: auto; width: 100%;">
                            <el-form-item>
                                <el-button @click="login('loginRef')"
                                    style="width: 100%; height: 40px; font-size: 18px; border-radius: 1px; background-color: rgba(0, 0, 0, 0.06); border-width: 0px;">登录</el-button>
                            </el-form-item>
                        </div>
                    </el-row>

                    <el-row style="width: 100%; height: 25px;">
                        <div style="display: flex;">
                            <div style="flex: 1;">
                                <span>还没有账号？请</span>
                                <a href="/register/shop" style="text-decoration: none;">
                                    <span>注册</span>
                                </a>
                            </div>
                            <div style="flex: 1; text-align: right;">
                                <a href="/login" style="text-decoration: none;">用户登录</a>
                            </div>
                        </div>

                    </el-row>
                </el-form>
            </el-main>
            <el-footer style="width: 360px; height: 100px; margin: auto; background-color: rgb(255, 255, 255);">
            </el-footer>
        </el-container>
    </div>
</template>

<script>
 
import ValidCode from "@/components/ValidCode.vue"

export default {
    name: 'longin_shop',
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
        return {
            shop: {
                username: '',
                password: '',
                validcode: ''
            },
            rules: {
                username: [
                    { required: true, message: '请输入账号', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
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
        login() {
            this.$refs['loginRef'].validate((valid) => {
                if (valid) {
                    this.$request.post('/login/shop', this.shop).then(res => {
                        if (res.code === '200') {
                            this.$router.push('/space/shop');
                            this.$message.success('登录成功');
                            localStorage.setItem("sInfo", JSON.stringify(res.data));
                            localStorage.setItem("token", JSON.stringify(res.data.token));
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
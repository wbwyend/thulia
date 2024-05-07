<template>
    <div>
        <el-container style="width: 1024px; margin: auto;">
            <el-aside style="width: 20%; border-color: rgba(0, 0, 0, 0); border-width: 1px; border-right-style: solid ;">
                <el-container style="width: 100%; height: 50px; background-color: rgb(255, 255, 255);">
                    <div style="text-align: center; width: 100%; height: 50px; font-size: 22px; margin-top: 10px;">用户空间
                    </div>
                </el-container>
                <el-container style="width: 100%; height: 650px;">
                    <el-menu default-active="$route.path" router style="width: 100%;">
                        <el-menu-item index="/space/user/home">
                            <i class="el-icon-s-home"></i>
                            <span slot="title">首页</span>
                        </el-menu-item>
                        <el-submenu index="/space/user/my">
                            <template slot="title">
                                <i class="el-icon-user-solid"></i>
                                <span>我的信息</span>
                            </template>
                            <el-menu-item index="/space/user/information">
                                <i class="el-icon-user"></i>
                                <span slot="title">基本信息</span>
                            </el-menu-item>
                            <el-menu-item index="/space/user/password">
                                <i class="el-icon-lock"></i>
                                <span slot="title">修改密码</span>
                            </el-menu-item>
                        </el-submenu>
                        <el-submenu index="/space/user/report">
                            <template slot="title">
                                <i class="el-icon-s-management"></i>
                                <span>我的记录</span>
                            </template>
                            <el-menu-item index="/space/user/report/browse">
                                <i class="el-icon-help"></i>
                                <span>浏览记录</span>
                            </el-menu-item>
                            <el-menu-item index="/space/user/report/purchase">
                                <i class="el-icon-shopping-cart-2"></i>
                                <span>购买记录</span>
                            </el-menu-item>
                        </el-submenu>
                        <el-menu-item index="/space/user/order">
                            <i class="el-icon-s-order"></i>
                            <span slot="title">我的订单</span>
                        </el-menu-item>
                        <el-menu-item index="/space/user/setting">
                            <i class="el-icon-s-tools"></i>
                            <span slot="title">设置</span>
                        </el-menu-item>
                    </el-menu>
                </el-container>
            </el-aside>
            <el-main style="padding: 0;">
                <el-container style="width: 100%; height: 50px; background-color: rgb(255, 255, 255);">
                    <el-col :span="15"></el-col>
                    <el-col :span="3" style="background-color: rgb(255, 255, 255);">
                        <div style="text-align: center; margin-top: 12px;">
                            <el-link :underline="false" href="/cart" style="font-size: 16px;">购物车</el-link>
                        </div>
                    </el-col>
                    <el-col :span="3" style="background-color: rgb(255, 255, 255);">
                        <div style="text-align: center; margin-top: 12px;">
                            <el-link :underline="false" href="/home" style="font-size: 16px;">商城首页</el-link>
                        </div>
                    </el-col>
                    <el-col :span="3" style="background-color: rgb(255, 255, 255);">
                        <div style="text-align: center; margin-top: 12px;">
                            <el-link :underline="false" style="font-size: 16px;" @click="logout">退出登录</el-link>
                        </div>
                    </el-col>
                </el-container>
                <el-container style="width: 100%; height: 600px;">
                    <router-view></router-view>
                </el-container>
            </el-main>
        </el-container>
    </div>
</template>
<script>

export default {
    name: 'space_user',
    data() {
        return {
            user: JSON.parse(localStorage.getItem('uInfo') || '{}')
        }
    },
    mounted() {
    },
    methods: {
        logout() {
            this.$request.post('/logout', this.user.uid).then(res => {
                if (res.code == '200') {
                    localStorage.removeItem("uInfo");
                    localStorage.removeItem("token");
                    this.$router.push('/home');
                } else {
                    this.$message.error(res.msg);
                }
            });
        },
    }
}
</script>
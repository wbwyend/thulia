<template>
    <div>
        <el-container style="width: 1024px; margin: auto;">
            <el-aside style="width: 20%; border-color: rgba(0, 0, 0, 0); border-width: 1px; border-right-style: solid ;">
                <el-container style="width: 100%; height: 50px; background-color: rgb(255, 255, 255);">
                    <div style="text-align: center; width: 100%; height: 50px; font-size: 22px; margin-top: 10px;">Saler
                        Manage</div>
                </el-container>
                <el-container style="width: 100%; height: 650px;">
                    <el-menu default-active="$route.path" router style="width: 100%;">
                        <el-menu-item index="/space/saler/home">
                            <i class="el-icon-s-home"></i>
                            <span slot="title">首页</span>
                        </el-menu-item>
                        <el-submenu index="/space/saler/sale">
                            <template slot="title">
                                <i class="el-icon-menu"></i>
                                <span>商品管理</span>
                            </template>
                            <el-menu-item index="/space/saler/goods">
                                <i class="el-icon-sell"></i>
                                <span>在售商品</span>
                            </el-menu-item>
                            <el-menu-item index="/space/saler/goods/outsale">
                                <i class="el-icon-sold-out"></i>
                                <span>下架商品</span>
                            </el-menu-item>
                        </el-submenu>
                        <el-submenu index="/space/saler/report">
                            <template slot="title">
                                <i class="el-icon-s-order"></i>
                                <span>数据报表</span>
                            </template>
                            <el-menu-item index="/space/saler/report/browse">
                                <i class="el-icon-help"></i>
                                <span>浏览记录</span>
                            </el-menu-item>
                            <el-menu-item index="/space/saler/report/purchase">
                                <i class="el-icon-shopping-cart-2"></i>
                                <span>购买记录</span>
                            </el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-container>
            </el-aside>
            <el-main style="padding: 0;">
                <el-container style="width: 100%; height: 50px; background-color: rgb(255, 255, 255);">
                    <el-col :span="18"></el-col>
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
                <el-container style="width: 100%; max-height: 80vh;">
                    <router-view></router-view>
                </el-container>
            </el-main>
        </el-container>
    </div>
</template>
<script>

export default {
    name: 'space_admin',
    data() {
        return {

        }
    },
    mounted() {

    },
    methods: {
        logout() {
            localStorage.removeItem("sInfo");
            localStorage.removeItem("token");
            this.$router.push('/home');
        }
    }
}
</script>
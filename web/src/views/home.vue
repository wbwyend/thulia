<template>
    <div>
        <el-container>
            <el-header style="height: 35px; min-width: 1064px; margin: auto;">
                <el-row type="flex" :gutter="0"
                    style="border-bottom-width: 1px; border-bottom-color: rgba(0, 0, 0, 0.16); border-bottom-style: solid;">
                    <el-col :span="1" style="min-width: 60px;">
                        <div style="width: 100%; height: 35px; min-width: 60px;">
                            <el-col :span="9">
                                <i class="el-icon-goods" style="margin-left: 7px; margin-top: 10px;"></i>
                            </el-col>
                            <el-col :span="12">
                                <el-link :underline="false" href="/home"
                                    style="font-size: 14px; margin-top: 7px;">首页</el-link>
                            </el-col>
                            <el-col :span="3"></el-col>
                        </div>
                    </el-col>
                    <el-col :span="1" style="min-width: 72px;">
                        <div style="width: 100%; height: 35px;">
                            <el-col :span="9">
                                <i class="el-icon-shopping-cart-1"
                                    style="margin-left: 8px; margin-top: 10px; font-size: 16px;"></i>
                            </el-col>
                            <el-col :span="15">
                                <el-link :underline="false" href="/cart"
                                    style="font-size: 14px; margin-top: 7px;">购物车</el-link>
                            </el-col>
                        </div>
                    </el-col>
                    <el-col :span="1" style="min-width: 85px;">
                        <div style="width: 100%; height: 35px;">
                            <el-col :span="7">
                                <i class="el-icon-tickets" style="margin-left: 8px; margin-top: 10px; font-size: 16px;"></i>
                            </el-col>
                            <el-col :span="16">
                                <el-link :underline="false" href="/space/user/order"
                                    style="font-size: 14px; margin-top: 7px;">我的订单</el-link>
                            </el-col>
                        </div>
                    </el-col>
                    <el-col :span="16">
                        <div style="width: 100%; height: 35px;"></div>
                    </el-col>
                    <el-col :span="3">
                        <div v-if="!user.uid" style="width: 100%;">
                            <el-container style="width: 100%; height: 35px;">
                                <el-col :span="12">
                                    <div style="min-width: 60px; height: 35px;">
                                        <el-col :span="9">
                                            <i class="el-icon-user"
                                                style="margin-left: 8px; margin-top: 10px; font-size: 16px;"></i>
                                        </el-col>
                                        <el-col :span="12">
                                            <el-link :underline="false" @click="login"
                                                style="font-size: 14px; margin-top: 7px;">登录</el-link>
                                        </el-col>
                                        <el-col :span="3"></el-col>
                                    </div>
                                </el-col>
                                <el-col :span="12">
                                    <div style="min-width: 60px; height: 35px; background-color: rgb(255, 255, 255);">
                                        <el-col :span="9">
                                            <i class="el-icon-circle-plus-outline"
                                                style="margin-left: 8px; margin-top: 10px; font-size: 16px;"></i>
                                        </el-col>
                                        <el-col :span="12">
                                            <el-link :underline="false" href="/register"
                                                style="font-size: 14px; margin-top: 7px; margin-left: 1px;">注册</el-link>
                                        </el-col>
                                        <el-col :span="7"></el-col>
                                    </div>
                                </el-col>
                            </el-container>
                        </div>
                        <div v-else style="width: 100%;">
                            <el-container style="width: 100%;">
                                <el-col :span="12">
                                    <el-link :underline="false" href="/space/user"
                                        style="font-size: 14px; margin-top: 7px; margin-left: 0;">
                                        {{ user.username }}
                                    </el-link>
                                </el-col>
                                <el-col :span="12">
                                    <el-link :underline="false" @click="logout"
                                        style="font-size: 14px; margin-top: 7px; margin-left: 0;">
                                        退出登录
                                    </el-link>
                                </el-col>
                            </el-container>
                        </div>

                    </el-col>
                </el-row>
            </el-header>
            <el-main style="max-width: 1064px; margin: auto;">
                <el-row type="flex" :gutter="0" style="height: 100px;">
                    <el-col :span="5" style="min-width: 250px;">
                        <div class="block">
                            <img src="@/assets/sp.png">
                        </div>
                    </el-col>
                    <el-col :span="14">
                        <el-row type="flex" :gutter="0" style="height: 30px; margin: 0;"></el-row>
                        <el-row type="flex" :gutter="0" style="margin: 0;">
                            <div style="margin: auto; width: 90%; min-width: 400px;">
                                <el-input placeholder="搜索" v-model="input">
                                    <el-button slot="prepend" icon="el-icon-search" @click="handleSearch"></el-button>
                                </el-input>
                            </div>
                        </el-row>
                    </el-col>
                </el-row>
                <el-row type="flex" :gutter="0" style="height: 30px; margin-top: 20px; margin-bottom: 10px;">
                    <div style="font-size: 25px;">为你推荐</div>
                </el-row>
                <el-row v-for="index in 4" type="flex" class="display_row">
                    <div style="flex: 1;">
                        <el-link :underline="false" :href="'/details/' + displayTable[3 * (index - 1)].gid"
                            class="display_card">
                            <el-col :span="12">
                                <div class="display_card_img_div">
                                    <img :src="displayTable[3 * (index - 1)].picture" class="display_card_img"><img>
                                </div>
                            </el-col>
                            <el-col :span="12">
                                <div class="display_card_name">
                                    {{ displayTable[3 * (index - 1)].name }}
                                </div>
                                <div class="display_card_price">
                                    <em>¥</em> {{ displayTable[3 * (index - 1)].price }}
                                </div>
                            </el-col>
                        </el-link>
                    </div>
                    <div style="flex: 1;">
                        <el-link :underline="false" :href="'/details/' + displayTable[3 * (index - 1) + 1].gid"
                            class="display_card">
                            <el-col :span="12">
                                <div class="display_card_img_div">
                                    <img :src="displayTable[3 * (index - 1) + 1].picture" class="display_card_img"><img>
                                </div>
                            </el-col>
                            <el-col :span="12">
                                <div class="display_card_name">
                                    {{ displayTable[3 * (index - 1) + 1].name }}
                                </div>
                                <div class="display_card_price">
                                    <em>¥</em> {{ displayTable[3 * (index - 1) + 1].price }}
                                </div>
                            </el-col>
                        </el-link>
                    </div>
                    <div style="flex: 1;">
                        <el-link :underline="false" :href="'/details/' + displayTable[3 * (index - 1) + 2].gid"
                            class="display_card">
                            <el-col :span="12">
                                <div class="display_card_img_div">
                                    <img :src="displayTable[3 * (index - 1) + 2].picture" class="display_card_img"><img>
                                </div>
                            </el-col>
                            <el-col :span="12">
                                <div class="display_card_name">
                                    {{ displayTable[3 * (index - 1) + 2].name }}
                                </div>
                                <div class="display_card_price">
                                    <em>¥</em> {{ displayTable[3 * (index - 1) + 2].price }}
                                </div>
                            </el-col>
                        </el-link>
                    </div>
                </el-row>
                <el-row style="margin-top: 20px;">
                    <el-pagination background layout="prev, pager, next" :total="pageNumber"
                        :current-page.sync="currentPage" @current-change="handleCurrentChange">
                    </el-pagination>
                </el-row>
            </el-main>
            <el-footer
                style="height: 150px; min-width: 1064px; margin: auto; margin-top: 20px; border-top-color: rgba(0, 0, 0, 0.3); border-top-width: 1px; border-top-style: solid;">
                <div style="text-align: center; font-size: large; margin-top: 5px;">项目作者: wyer</div>
                <div style="text-align: center; font-size: large; margin-top: 5px;">开始时间: 2023-9-16</div>
            </el-footer>
        </el-container>
    </div>
</template>

<script>

export default {
    name: 'home',
    data() {
        return {
            input: '',
            user: JSON.parse(localStorage.getItem("uInfo") || '{}'),
            userId: JSON.parse(localStorage.getItem("uInfo") || '{}').uid || '0',
            dataTable: [],
            displayTable: [],
            currentPage: 1,
            pageNumber: 1
        }
    },
    mounted() {
        this.$request.get('/goods/recommend/home/' + this.userId).then(res => {
            if (res.code === '200') {
                this.dataTable = res.data;
                var len = this.dataTable.length;
                this.pageNumber = ((len - len % 12) / 12) * 10;
                this.displayTable = [];
                for (var i = 0; i < 12; i++) {
                    this.displayTable.push(this.dataTable[i]);
                }
            } else {
                this.$message.error(res.msg)
            }
        });
    },
    methods: {
        login() {
            localStorage.setItem("target", '/home');
            this.$router.push('/login');
        },
        logout() {
            this.$request.post('/logout', this.user.uid).then(res => {
                if (res.code == '200') {
                    localStorage.removeItem("uInfo");
                    localStorage.removeItem("token");
                    this.$router.go(0);
                } else {
                    this.$message.error(res.msg);
                }
            });
        },
        handleCurrentChange() {
            this.displayTable = [];
            var base = (this.currentPage - 1) * 12;
            for (var i = 0; i < 12; i++) {
                this.displayTable.push(this.dataTable[i + base]);
            }
        },
        handleSearch() {
            if (this.input == '') {
                this.$message.error("输入为空");
            } else {
                localStorage.setItem("search", this.input);
                this.$router.push('/search');
            }
        }
    }
}
</script>

<style scoped>
.display_row {
    height: 180px;
    margin-bottom: 0;
}

.display_card {
    height: 160px;
    margin: 10px;
    width: 320px;
    border-radius: 10px;
    border-width: 1px;
    border-style: solid;
}

.display_card_img_div {
    height: 140px;
    width: 140px;
    margin: 10px;
    border-radius: 10px;
}

.display_card_img {
    border-radius: 10px;
    height: 140px;
    width: 140px;
}

.display_card_name {
    height: 45px;
    width: 140px;
    margin-top: 15px;
    margin-left: 10px;
    word-wrap: break-word;
    overflow: hidden;
    font-size: 16px;
}

.display_card_price {
    height: 30px;
    width: 70px;
    margin-left: 10px;
    margin-top: 50px;
    font-size: 20px;
}
</style>


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
            <el-main style="width: 1064px; margin: auto;">
                <el-row style="height: 80px;">
                    <div style="margin: auto; width: 60%; margin-top: 30px;">
                        <el-input placeholder="搜索" v-model="input">
                            <el-button slot="prepend" icon="el-icon-search" @click="handleSearch"></el-button>
                        </el-input>
                    </div>
                </el-row>
                <el-row type="flex" :gutter="0" style="height: 30px; margin-top: 20px; margin-bottom: 10px;">
                    <div style="font-size: 25px;">搜索结果</div>
                </el-row>
                <template v-if="pageSize == 6">
                    <el-row v-for="index in 6" style="width: 1000px; height: 120px; margin-left: 10px; margin-bottom: 0;">
                        <el-link :underline="false" :href="'/details/' + displayTable[index - 1].gid"
                            style="display: block; width: 100%; height: 100px; border-radius: 10px; border-width: 1px; border-style: solid;">
                            <el-col :span="2">
                                <div style="width: 80px; height: 80px; margin: 10px;">
                                    <img :src="displayTable[index - 1].picture"
                                        style="width: 80px; height: 80px; border-radius: 5px;">
                                </div>
                            </el-col>
                            <el-col :span="22">
                                <div
                                    style="margin: 20px; margin-top: 10px; margin-bottom: 0; font-size: 16px; height: 45px; width: 80%; word-wrap: break-word; overflow: hidden;">
                                    {{ displayTable[index - 1].name }}
                                </div>
                                <div style="margin: 20px; margin-top: 5px; font-size: 18px;">
                                    <el-col :span="18">
                                        <em>¥</em> {{ displayTable[index - 1].price }}
                                    </el-col>
                                    <el-col :span="6" style="font-size: 14px;">
                                        <span>销量</span> {{ displayTable[index - 1].sales }}
                                    </el-col>
                                </div>
                            </el-col>
                        </el-link>
                    </el-row>
                </template>
                <template v-else>
                    <el-row v-for="index in pageSize"
                        style="width: 1000px; height: 120px; margin-left: 10px; margin-bottom: 0;">
                        <el-link :underline="false" :href="'/details/' + displayTable[index - 1].gid"
                            style="display: block; width: 100%; height: 100px; border-radius: 10px; border-width: 1px; border-style: solid;">
                            <el-col :span="2">
                                <div style="width: 80px; height: 80px; margin: 10px;">
                                    <img :src="displayTable[index - 1].picture"
                                        style="width: 80px; height: 80px; border-radius: 5px;">
                                </div>
                            </el-col>
                            <el-col :span="22">
                                <div
                                    style="margin: 20px; margin-top: 10px; margin-bottom: 0; font-size: 16px; height: 45px; width: 80%; word-wrap: break-word; overflow: hidden;">
                                    {{ displayTable[index - 1].name }}
                                </div>
                                <div style="margin: 20px; margin-top: 5px; font-size: 18px;">
                                    <el-col :span="18">
                                        <em>¥</em> {{ displayTable[index - 1].price }}
                                    </el-col>
                                    <el-col :span="6" style="font-size: 14px;">
                                        <span>销量</span> {{ displayTable[index - 1].sales }}
                                    </el-col>
                                </div>
                            </el-col>
                        </el-link>
                    </el-row>
                </template>
                <el-row style="margin-top: 10px;">
                    <el-pagination background layout="prev, pager, next" :total="pageNumber"
                        :current-page.sync="currentPage" @current-change="handleCurrentChange">
                    </el-pagination>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>

<script>

export default {
    name: 'search',
    data() {
        return {
            input: 'search',
            user: JSON.parse(localStorage.getItem("uInfo") || '{}'),
            dataTable: [],
            displayTable: [],
            currentPage: 1,
            pageNumber: 1,
            pageSize: 6
        }
    },
    mounted() {
        var text = localStorage.getItem("search");
        if (text != "") {
            this.input = text;
            localStorage.removeItem("search");
        }
        this.handleSearch();
    },
    methods: {
        login() {
            localStorage.setItem("target", '/search');
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
            var base = (this.currentPage - 1) * 6;
            if (this.currentPage < this.pageNumber / 10) {
                this.pageSize = 6;
                for (var i = 0; i < 6; i++) {
                    this.displayTable.push(this.dataTable[i + base]);
                }
            } else {
                if (this.dataTable.length % 6 == 0) {
                    this.pageSize = 6;
                } else {
                    this.pageSize = this.dataTable.length % 6;
                }
                for (var i = 0; i + base < this.dataTable.length; i++) {
                    this.displayTable.push(this.dataTable[i + base]);
                }
            }
        },
        handleSearch() {
            if (this.input == '') {
                this.$message.error("输入为空");
            } else {
                this.$request.post('/search', this.input).then(res => {
                    if (res.code == '200') {
                        this.displayTable = [];
                        this.dataTable = [];
                        this.currentPage = 1;
                        this.pageNumber = 1;
                        this.pageSize = 0;
                        this.dataTable = res.data;
                        var len = this.dataTable.length;
                        console.log(len);
                        if (len < 6) {
                            this.pageSize = len;
                        } else {
                            this.pageSize = 6;
                        }
                        this.pageNumber = ((len - len % 6) / 6 + 1) * 10;
                        if (len % 6 == 0) {
                            this.pageNumber -= 10;
                        }
                        for (var i = 0; i < this.pageSize; i++) {
                            this.displayTable.push(this.dataTable[i]);
                        }
                    } else {
                        this.$message.error(res.msg);
                    }
                });
            }
        }
    }
}
</script>

<style scoped></style>


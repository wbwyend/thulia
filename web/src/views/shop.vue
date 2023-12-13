<template>
    <div>
        <el-container style="height: auto; width: 100%; margin: auto;">
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
                                <i class="el-icon-tickets"
                                    style="margin-left: 8px; margin-top: 10px; font-size: 16px;"></i>
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
            <el-main style="width: 1064px; height: auto; margin: auto;">
                <el-row style="width: 100%; height: 80px; border-radius: 10px;
                                 border-color: rgb(0, 0, 0, 0.4); border-width: 1px; border-style: solid;">
                    <el-col :span="2">
                        <div style="margin: 10px;">
                            <el-avatar :size="60" :src="shop.avatar"></el-avatar>
                        </div>
                    </el-col>
                    <el-col :span="10">
                        <div style="font-size: 20px; overflow-wrap: normal; width: 100%; height: 80px; padding: 25px 0;">
                            {{ shop.shopname }}
                        </div>
                    </el-col>
                    <el-col :span="9">
                        <div style="width: 300px; height: 80px;"></div>
                    </el-col>
                    <el-col :span="3">
                        <el-popover placement="top-start" title="联系方式" width="200" trigger="hover">
                            <div>电话: {{ shop.phone }}</div>
                            <div>邮箱: {{ shop.email }}</div>
                            <el-link :underline="false" style="margin: auto; width: 90px; height: 30px; margin-top: 25px; border-radius: 10px;
                                 border-color: rgb(0, 0, 0, 0.4); border-width: 1px; border-style: solid;" slot="reference">
                                <el-col :span="4">
                                    <i class="el-icon-phone"></i>
                                </el-col>
                                <el-col :span="20">
                                    <div style="margin-left: 5px;">联系商家</div>
                                </el-col>
                            </el-link>
                        </el-popover>
                    </el-col>
                </el-row>
                <el-container style="width: 100%; height: auto; border-radius: 10px; flex-wrap: wrap;
                                 border-color: rgb(0, 0, 0, 0.4); border-width: 1px; border-style: solid;">
                    <div class="goodsRow">
                        <el-link v-if="displayTable.length >= 1" :underline="false" class="goodsCard"
                            :href="'/details/' + displayTable[0].gid">
                            <img :src="displayTable[0].picture" class="goodsImg">
                            <div class="goodsName"> {{ displayTable[0].name }} </div>
                        </el-link>
                        <el-link v-else :underline="false" class="goodsEmptyCard"></el-link>
                        <el-link v-if="displayTable.length >= 2" :underline="false" class="goodsCard"
                            :href="'/details/' + displayTable[1].gid">
                            <img :src="displayTable[1].picture" class="goodsImg">
                            <div class="goodsName"> {{ displayTable[1].name }} </div>
                        </el-link>
                        <el-link v-else :underline="false" class="goodsEmptyCard"></el-link>
                        <el-link v-if="displayTable.length >= 3" :underline="false" class="goodsCard"
                            :href="'/details/' + displayTable[2].gid">
                            <img :src="displayTable[2].picture" class="goodsImg">
                            <div class="goodsName"> {{ displayTable[2].name }} </div>
                        </el-link>
                        <el-link v-else :underline="false" class="goodsEmptyCard"></el-link>
                        <el-link v-if="displayTable.length >= 4" :underline="false" class="goodsCard"
                            :href="'/details/' + displayTable[3].gid">
                            <img :src="displayTable[3].picture" class="goodsImg">
                            <div class="goodsName"> {{ displayTable[3].name }} </div>
                        </el-link>
                        <el-link v-else :underline="false" class="goodsEmptyCard"></el-link>
                    </div>
                    <div class="goodsRow">
                        <el-link v-if="displayTable.length >= 5" :underline="false" class="goodsCard"
                            :href="'/details/' + displayTable[4].gid">
                            <img :src="displayTable[4].picture" class="goodsImg">
                            <div class="goodsName"> {{ displayTable[4].name }} </div>
                        </el-link>
                        <el-link v-else :underline="false" class="goodsEmptyCard"></el-link>
                        <el-link v-if="displayTable.length >= 6" :underline="false" class="goodsCard"
                            :href="'/details/' + displayTable[5].gid">
                            <img :src="displayTable[5].picture" class="goodsImg">
                            <div class="goodsName"> {{ displayTable[5].name }} </div>
                        </el-link>
                        <el-link v-else :underline="false" class="goodsEmptyCard"></el-link>
                        <el-link v-if="displayTable.length >= 7" :underline="false" class="goodsCard"
                            :href="'/details/' + displayTable[6].gid">
                            <img :src="displayTable[6].picture" class="goodsImg">
                            <div class="goodsName"> {{ displayTable[6].name }} </div>
                        </el-link>
                        <el-link v-else :underline="false" class="goodsEmptyCard"></el-link>
                        <el-link v-if="displayTable.length >= 8" :underline="false" class="goodsCard"
                            :href="'/details/' + displayTable[7].gid">
                            <img :src="displayTable[7].picture" class="goodsImg">
                            <div class="goodsName"> {{ displayTable[7].name }} </div>
                        </el-link>
                        <el-link v-else :underline="false" class="goodsEmptyCard"></el-link>
                    </div>
                    <div style="width: 100%; height: 20px;"></div>
                </el-container>
                <el-row style="margin-top: 20px;">
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
    data() {
        return {
            sid: this.$route.params.sid,
            user: JSON.parse(localStorage.getItem("uInfo") || '{}'),
            userId: JSON.parse(localStorage.getItem("uInfo") || '{}').uid || '0',
            shop: {},
            currentPage: 1,
            dataTable: [],
            pageNumber: 1,
            displayTable: []
        };
    },
    mounted() {
        this.$request.post('/shop/get/details', this.sid).then(res => {
            if (res.code === '200') {
                this.shop = res.data;
            } else {
                this.$message.error(res.msg);
                this.$router.push('/home');
            }
        });
        this.$request.post('/goods/get/shop', this.sid).then(res => {
            if (res.code === '200') {
                this.dataTable = res.data;
                var len = this.dataTable.length;
                this.pageNumber = 10 * ((len - len % 8) / 8 + 1);
                if (len < 8) {
                    this.dataTable.forEach(row => {
                        this.displayTable.push(row);
                    })
                } else {
                    for (var i = 0; i < 8; i++) {
                        this.displayTable.push(this.dataTable[i]);
                    }
                }
            } else {
                this.$message.error(res.msg);
            }
        });
    },
    methods: {
        handleCurrentChange() {
            this.displayTable = [];
            if (this.currentPage == this.pageNumber / 10) {
                for (var i = 0; i < this.dataTable.length % 8; i++) {
                    this.displayTable.push(this.dataTable[(this.currentPage - 1) * 8 + i]);
                }
            } else {
                for (var i = 0; i < 8; i++) {
                    this.displayTable.push(this.dataTable[(this.currentPage - 1) * 8 + i]);
                }
            }
        },
        login() {
            var target = '';
            target = target.concat('/shop/');
            target = target.concat(this.sid);
            localStorage.setItem("target", target);
            this.$router.push('/login');
        },
        logout() {
            localStorage.removeItem("uInfo");
            localStorage.removeItem("token");
            this.$router.go(0);
        }
    }
}
</script>

<style scoped>
.goodsImg {
    width: 200px;
    height: 200px;
    margin: 15px;
}

.goodsName {
    margin-left: 15px;
    width: 200px;
    height: 40px;
    overflow-x: hidden;
    overflow-y: hidden;
}

.goodsRow {
    width: 100%;
    height: 300px;
    margin: 10px;
    display: flex;
}

.goodsCard {
    flex: 1;
    border-radius: 5px;
    height: 300px;
    margin: 10px;
    border-color: rgb(0, 0, 0, 0.4);
    border-width: 1px;
    border-style: solid;
}

.goodsEmptyCard {
    flex: 1;
    border-radius: 5px;
    height: 300px;
    margin: 10px;
}
</style>
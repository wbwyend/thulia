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
            <el-main style="width: 1064px; height: 600px; margin: auto;">
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
                        <el-link :underline="false" :href="'/shop/' + shop.sid" style="margin: auto; width: 90px; height: 30px; margin-top: 25px; border-radius: 10px;
                                 border-color: rgb(0, 0, 0, 0.4); border-width: 1px; border-style: solid;">
                            <el-col :span="4">
                                <i class="el-icon-s-shop"></i>
                            </el-col>
                            <el-col :span="20">
                                <div style="margin-left: 5px;">进入商店</div>
                            </el-col>
                        </el-link>
                    </el-col>
                </el-row>
                <el-row style="width: 100%; height: 80%; border-radius: 10px;
                                 border-color: rgb(0, 0, 0, 0.4); border-width: 1px; border-style: solid;">
                    <el-col :span="9" style="padding: 10px;">
                        <el-image style="width: 350px; height: 350px" :src="goods.picture" :fit="cover"></el-image>
                    </el-col>
                    <el-col :span="15">
                        <el-main>
                            <el-form :model="buy" :rules="rules" ref="buyRef">
                                <el-form-item prop="name" style="margin-bottom: 20px;">
                                    <div style="font-size: 20px;">{{ goods.name }}</div>
                                </el-form-item>
                                <el-form-item label="价格" prop="price" style="margin-bottom: 20px;">
                                    <span style="font-size: 18px;">¥</span>
                                    <span style="font-size: 28px;">{{ goods.price }}</span>
                                </el-form-item>
                                <el-form-item label="配送" prop="region" style="margin-bottom: 20px;">
                                    <span>{{ shop.address }}</span>
                                    <span style="margin-left: 10px;">至</span>
                                    <el-cascader v-model="buy.region" :options="options" size="medium"
                                        :props="{ expandTrigger: 'hover' }"
                                        style="width: 120px; margin-left: 10px;"></el-cascader>
                                </el-form-item>
                                <el-form-item prop="number" label="数量" style="margin-bottom: 20px; width: 200px;">
                                    <el-col :span="4">
                                        <i class="el-icon-remove-outline" @click="minus"
                                            style="font-size: 22px; margin-top: 10px;"></i>
                                    </el-col>
                                    <el-col :span="8">
                                        <el-input v-model="buy.number" style="width: 60px;"></el-input>
                                    </el-col>
                                    <el-col :span="3">
                                        <i class="el-icon-circle-plus-outline" @click="add"
                                            style="font-size: 22px; margin-top: 10px;"></i>
                                    </el-col>
                                    <el-col :span="4">
                                        <span v-if="goods.surplus != '0'">有货</span>
                                        <span v-else>无货</span>
                                    </el-col>
                                </el-form-item>
                                <el-form-item>
                                    <el-button @click="inCart" style="width: 25%; height: 40px; font-size: 18px; border-radius: 10px;
                                         background-color: rgba(0, 0, 0, 0.06); border-width: 0px;">加入购物车</el-button>
                                    <el-button @click="createOrder" style="width: 25%; height: 40px; font-size: 18px; border-radius: 10px;
                                         background-color: rgba(0, 0, 0, 0.06); border-width: 0px;">立即购买</el-button>
                                </el-form-item>
                            </el-form>
                        </el-main>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>
<script>
export default {
    data() {
        return {
            gid: this.$route.params.gid,
            user: JSON.parse(localStorage.getItem("uInfo") || '{}'),
            userId: JSON.parse(localStorage.getItem("uInfo") || '{}').uid || '0',
            url: '/details/',
            goods: {},
            shop: {},
            cover: 'cover',
            buy: {
                region: ['北京'],
                number: 1
            },
            rules: {
            },
            options: [{
                value: '北京',
                label: '北京',
            }, {
                value: '天津',
                label: '天津',
            }, {
                value: '河北',
                label: '河北',
                children: [{
                    value: '石家庄',
                    label: '石家庄'
                }, {
                    value: '唐山',
                    label: '唐山'
                }, {
                    value: '秦皇岛',
                    label: '秦皇岛'
                }, {
                    value: '邯郸',
                    label: '邯郸'
                }, {
                    value: '邢台',
                    label: '邢台'
                }, {
                    value: '保定',
                    label: '保定'
                }, {
                    value: '张家口',
                    label: '张家口'
                }, {
                    value: '承德',
                    label: '承德'
                }, {
                    value: '沧州',
                    label: '沧州'
                }, {
                    value: '廊坊',
                    label: '廊坊'
                }, {
                    value: '衡水',
                    label: '衡水'
                }]
            }, {
                value: '山西',
                label: '山西',
                children: [{
                    value: '太原',
                    label: '太原'
                }, {
                    value: '大同',
                    label: '大同'
                }, {
                    value: '朔州',
                    label: '朔州'
                }, {
                    value: '忻州',
                    label: '忻州'
                }, {
                    value: '阳泉',
                    label: '阳泉'
                }, {
                    value: '吕梁',
                    label: '吕梁'
                }, {
                    value: '晋中',
                    label: '晋中'
                }, {
                    value: '长治',
                    label: '长治'
                }, {
                    value: '晋城',
                    label: '晋城'
                }, {
                    value: '临汾',
                    label: '临汾'
                }, {
                    value: '运城',
                    label: '运城'
                }]
            }]
        };
    },

    mounted() {
        this.$request.post('/goods/get/details', [this.gid, this.userId]).then(res => {
            if (res.code === '200') {
                this.goods = res.data;
                this.$request.post('/shop/get/details', this.goods.sid).then(res => {
                    if (res.code === '200') {
                        this.shop = res.data;
                    } else {
                        this.$message.error(res.msg);
                    }
                });
            } else {
                this.$message.error(res.msg)
            }
        });
        this.buy.region = this.handleDataToAddress();
    },
    methods: {
        handleAddressToData() {
            var result = '';
            result = result.concat(this.shopAddress[0]);
            if (this.shopAddress.length >= 2) {
                result = result.concat('/');
                result = result.concat(this.shopAddress[1]);
            }
            return result;
        },
        handleDataToAddress() {
            var result = [];
            var addr = '';
            var i = 0;
            addr = addr.concat(this.user.address);
            for (; i < addr.length; i++) {
                if (addr[i] == '/') {
                    break;
                }
            }
            if (i == addr.length) {
                result.push(addr);
            } else {
                result.push(addr.slice(0, i));
                result.push(addr.slice(i + 1))
            }
            return result;
        },
        login() {
            var target = '';
            target = target.concat('/details/');
            target = target.concat(this.gid);
            localStorage.setItem("target", target);
            this.$router.push('/login');
        },
        logout() {
            localStorage.removeItem("uInfo");
            localStorage.removeItem("token");
            this.$router.go(0);
        },
        minus() {
            if (this.buy.number > 0) {
                this.buy.number = this.buy.number - 1;
            }
        },
        add() {
            if (this.buy.number < this.goods.surplus) {
                this.buy.number = this.buy.number + 1;
            }
        },
        inCart() {
            if (parseInt(this.userId) == 0) {
                this.login();
            } else {
                var req = {
                    uid: this.userId,
                    gid: this.gid,
                    number: this.buy.number
                }
                this.$request.post('/cart/add', req).then(res => {
                    if (res.code == "200") {
                        this.$message.success("加入购物车成功");
                    } else {
                        this.$message.error(res.msg);
                    }
                });
            }
        },
        createOrder() {
            var examination = {
                gid: this.goods.gid,
                number: this.buy.number
            }
            this.$request.post('/goods/examine', [examination]).then(res => {
                if (res.code == "200") {
                    if (res.data == 'true') {
                        var order = {
                            uid: this.userId,
                            total: this.goods.price * this.buy.number,
                            ordername: '包含' + this.goods.name + 'X' + this.buy.number + '的订单'
                        }
                        this.$request.post('/order/add', order).then(res => {
                            if (res.code == "200") {
                                var oid = res.data;
                                var list = [];
                                list.push({
                                    uid: this.userId,
                                    sid: this.shop.sid,
                                    gid: this.gid,
                                    number: this.buy.number,
                                    oid: oid,
                                    total: this.goods.price * this.buy.number
                                });
                                this.$request.post('/purchase/add', list).then(result => {
                                    if (result.code == '200') {
                                        this.$message.success("订单生成成功");
                                        localStorage.setItem("order", oid);
                                        this.$router.push('/space/user/order');
                                    } else {
                                        this.$message.error(result.msg);
                                    }
                                });
                            } else {
                                this.$message.error(res.msg);
                            }
                        });
                    } else {
                        this.$message.error("库存量不足");
                    }
                } else {
                    this.$message.error(res.msg);
                }
            });
        }
    }
}
</script>
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
            <el-main style="width: 1064px; margin: auto;">
                <div style="width: 100%; height: auto;">
                    <el-table ref="multipleTable" :data="tableData" @selection-change="handleSelectionChange"
                        style="width: 100%; max-height: 80vh; overflow-y: scroll;">
                        <el-table-column type="selection" width="55">
                        </el-table-column>
                        <el-table-column label="商品" width="300">
                            <template slot-scope="scope">
                                <div style="width: 100%;">
                                    <el-col :span="6">
                                        <el-avatar shape="square" :size="40" :src="scope.row.picture"></el-avatar>
                                    </el-col>
                                    <el-col :span="18">
                                        <div @click="toDetails(scope.row)" style="overflow: hidden; height: 50px;">{{
                    scope.row.name }}</div>
                                    </el-col>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="商家">
                            <template slot-scope="scope">
                                <span style="overflow: hidden; height: 50px;">{{ scope.row.shopname }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="单价" min-width="100">
                            <template slot-scope="scope">
                                <span style="font-size: 14px;">¥</span>
                                <span style="font-size: 14px;">{{ scope.row.price }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="数量" min-width="100">
                            <template slot-scope="scope">
                                <div style="display: flex; width: 100px; height: 24px;">
                                    <button class="compute_box" @click="minus(scope.$index, scope.row)">-</button>
                                    <input v-model="scope.row.number" type="number" v-bind="listen(scope.row)"
                                        style="width: 60px; font-size: 14px; text-align: center; border-style: solid; border-radius: 0px;">
                                    <button class="compute_box" @click="add(scope.$index, scope.row)">+</button>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="小计" min-width="100">
                            <template slot-scope="scope">
                                <span style="font-size: 14px;">¥</span>
                                <span style="font-size: 14px;">{{ (scope.row.number * scope.row.price).toFixed(2)
                                    }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作">
                            <template slot-scope="scope">
                                <div style="margin: 1px;">
                                    <el-button size="mini" @click="handleSave(scope.$index, scope.row)">保存</el-button>
                                </div>
                                <div style="margin: 1px;">
                                    <el-button size="mini" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                                </div>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div style="width: 100%; height: 50px; margin-top: 20px; border-width: 1px; border-style: solid;
                 border-color: rgba(0, 0, 0, 0.1); border-radius: 5px;">
                        <el-col :span="17">
                            <span>
                                <el-link :underline="false" class="span_box"
                                    @click="toggleSelection(tableData)">全选</el-link>
                            </span>
                            <span>
                                <el-link :underline="false" class="span_box" @click="batchDelete">删除选中的商品</el-link>
                            </span>
                            <span>
                                <el-link :underline="false" class="span_box" @click="batchSave">保存全部</el-link>
                            </span>
                        </el-col>
                        <el-col :span="4">
                            <span>
                                <el-link :underline="false" class="span_box">总价:</el-link>
                            </span>
                            <span>
                                <el-link :underline="false" class="span_box"> ¥{{ totalPrice.toFixed(2) }} </el-link>
                            </span>
                        </el-col>
                        <el-col :span="3">
                            <div style="width: 100%; height: 50px%;">
                                <el-button type="info" plain style="width: 100%; height: 50px; border-width: 1px; border-style: solid;
                 border-color: rgba(0, 0, 0, 0.1); border-radius: 5px; font-size: 18px;"
                                    @click="toOrder">去结算</el-button>
                            </div>
                        </el-col>
                    </div>
                </div>
            </el-main>
        </el-container>

    </div>
</template>

<script>
export default {
    data() {
        return {
            user: JSON.parse(localStorage.getItem("uInfo") || '{}'),
            tableData: [],
            multipleSelection: [],
            totalPrice: 0.0,
            screenHeight: document.documentElement.clientHeight,
        }
    },
    beforeCreate() {

    },
    mounted() {
        var uid = JSON.parse(localStorage.getItem("uInfo") || '{}').uid;
        if (uid) {
            this.$request.post('/cart/get', uid).then(res => {
                if (res.code == '200') {
                    this.tableData = res.data;
                    this.totalPrice = this.getTotalPrice();
                } else if (res.code == '401') {
                    this.$message.error(res.msg);
                    this.$router.push('/login');
                }
                else {
                    this.$message.error(res.msg);
                    this.$router.push('/home');
                }
            });
        } else {
            this.$message.error('请登录');
            this.$router.push('/login');
        }
    },
    beforeDestroy() {

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
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        handleSave(index, row) {
            var cart = {
                uid: row.uid,
                gid: row.gid,
                number: row.number
            }
            this.$request.post('/cart/save', cart).then(res => {
                if (res.code == '200') {
                    this.tableData = res.data;
                    this.$message.success('保存成功');
                } else {
                    this.$message.error(res.msg);
                    this.$router.push('/home');
                }
            });
        },
        batchSave() {
            if (this.tableData.length == 0) {
                this.$message.warning("购物车为空");
            } else {
                var carts = [];
                this.tableData.forEach(row => {
                    var cart = {
                        uid: row.uid,
                        gid: row.gid,
                        number: row.number
                    }
                    carts.push(cart);
                })
                this.$refs.multipleTable.clearSelection();
                this.$request.post('/cart/batch/save', carts).then(res => {
                    if (res.code == '200') {
                        this.tableData = res.data;
                        this.$message.success('保存成功');
                    } else {
                        this.$message.error(res.msg);
                    }
                });
            }
        },
        handleDelete(index, row) {
            var cart = {
                uid: row.uid,
                gid: row.gid,
                number: row.number
            }
            this.$request.post('/cart/delete', cart).then(res => {
                if (res.code == '200') {
                    this.tableData = res.data;
                } else {
                    this.$message.error(res.msg);
                    this.$router.push('/home');
                }
            });
        },
        add(index, row) {
            if (row.number < 99) {
                row.number = parseInt(row.number) + 1;
            }
            this.totalPrice = this.getTotalPrice();
        },
        minus(index, row) {
            if (row.number > 1) {
                row.number = parseInt(row.number) - 1;
            }
        },
        listen(row) {
            if (row.number > 100) {
                this.$message.error('数量不能超过100');
                row.number = 100;
            }
            this.totalPrice = this.getTotalPrice();
        },
        toggleSelection(rows) {
            if (rows) {
                this.$refs.multipleTable.clearSelection();
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        batchDelete() {
            var carts = [];
            if (this.multipleSelection.length == 0) {
                this.$message.warning("未选择");
            } else {
                this.multipleSelection.forEach(row => {
                    var cart = {
                        uid: row.uid,
                        gid: row.gid,
                        number: row.number
                    }
                    carts.push(cart);
                })
                this.$refs.multipleTable.clearSelection();
                this.$request.post('/cart/batch/delete', carts).then(res => {
                    if (res.code == '200') {
                        this.tableData = res.data;
                    } else {
                        this.$message.error(res.msg);
                        this.$router.push('/home');
                    }
                });
            }
        },
        getTotalPrice() {
            var totalPrice = 0.0
            this.multipleSelection.forEach(row => {
                totalPrice += row.number * row.price;
            });
            return totalPrice;
        },
        toDetails(row) {
            this.$router.push('/details/' + row.gid);
        },
        toOrder() {
            if (this.totalPrice == 0.0) {
                this.$message.warning("未选择")
            } else {
                var examinations = []
                this.multipleSelection.forEach(row => {
                    examinations.push({
                        gid: row.gid,
                        number: row.number
                    });
                });
                this.$request.post('/goods/examine', examinations).then(res => {
                    if (res.code == '200' && res.data == "true") {
                        var order = {
                            uid: this.user.uid,
                            total: this.totalPrice,
                            ordername: '包含'
                        }
                        this.multipleSelection.forEach(row => {
                            order.ordername = order.ordername + row.name + 'x' + row.number;
                        });
                        order.ordername = order.ordername + "的订单";
                        this.$request.post('/order/add', order).then(res => {
                            if (res.code == "200") {
                                var oid = res.data;
                                var list = [];
                                this.multipleSelection.forEach(row => {
                                    list.push({
                                        uid: row.uid,
                                        sid: row.sid,
                                        gid: row.gid,
                                        number: row.number,
                                        oid: oid,
                                        total: row.price * row.number
                                    });
                                });
                                this.$request.post('/purchase/add', list).then(result => {
                                    if (result.code == '200') {
                                        this.$message.success("订单生成成功");
                                        localStorage.setItem("order", oid);
                                        this.batchDelete();
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
                });
            }
        }
    }
}
</script>

<style scoped>
.compute_box {
    width: 20px;
    border-width: 1px;
    border-style: solid;
    text-align: center;
}

.span_box {
    margin-top: 15px;
    margin-left: 20px;
}


input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
}
</style>
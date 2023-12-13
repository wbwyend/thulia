<template>
    <div style="width: 100%; margin-top: 20px;">
        <el-row style="height: 40px; width: 100%;">
            <div style="text-align: center; font-size: 24px;">我的订单</div>
        </el-row>
        <el-row style="height: 100px; width: 100%;">
            <el-container style="width: 100%;">
                <el-col :span="1">
                    <div></div>
                </el-col>
                <el-col :span="23">
                    <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" stripe height="480">
                        <el-table-column prop="name" label="订单名" width="300">
                            <template slot-scope="scope">
                                <el-link :underline="false">
                                    {{ scope.row.ordername }}
                                </el-link>
                            </template>
                        </el-table-column>
                        <el-table-column prop="name" label="订单号" width="80">
                            <template slot-scope="scope">
                                <el-link :underline="false">
                                    {{ scope.row.oid }}
                                </el-link>
                            </template>
                        </el-table-column>
                        <el-table-column prop="create_time" label="创建时间">
                            <template slot-scope="scope">
                                <el-link :underline="false">
                                    {{ scope.row.create_time }}
                                </el-link>
                            </template>
                        </el-table-column>
                        <el-table-column prop="create_time" label="状态" width="80">
                            <template slot-scope="scope">
                                <el-tag
                                    :type="scope.row.status === '未支付' ? 'danger' : (scope.row.status === '已删除' ? 'info' : 'success')">
                                    {{ scope.row.status }}
                                </el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column fixed="right" label="操作" width="120">
                            <template slot-scope="scope">
                                <div v-if="scope.row.status === '未支付'">
                                    <el-button type="text" size="small" @click="handlePay(scope.row)">支付</el-button>
                                    <el-button type="text" size="small" @click="handleDelete(scope.row)">删除</el-button>
                                </div>
                                <div v-else>
                                    <el-button type="text" size="small" @click="handleCheck(scope.row)">查看</el-button>
                                </div>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div style="margin-top: 20px;">
                        <el-dialog title="详情" :visible.sync="dialogTableVisible">
                            <el-table :data="gridData">
                                <el-table-column property="name" label="商品" width="400">
                                    <template slot-scope="scope">
                                        <div style="width: 100%;">
                                            <el-col :span="6">
                                                <el-avatar shape="square" :size="40" :src="scope.row.picture"></el-avatar>
                                            </el-col>
                                            <el-col :span="18">
                                                <div style="overflow: hidden; height: 50px;">
                                                    {{ scope.row.name }}</div>
                                            </el-col>
                                        </div>
                                    </template>
                                </el-table-column>
                                <el-table-column property="time" label="单价">
                                    <template slot-scope="scope">
                                        {{ (scope.row.total / scope.row.number).toFixed(2) }}
                                    </template>
                                </el-table-column>
                                <el-table-column property="time" label="数量">
                                    <template slot-scope="scope">
                                        {{ scope.row.number }}
                                    </template>
                                </el-table-column>
                                <el-table-column property="time" label="总价">
                                    <template slot-scope="scope">
                                        {{ (scope.row.total).toFixed(2) }}
                                    </template>
                                </el-table-column>
                            </el-table>
                            <div style="margin: 20px; margin-left: 75%; font-size: 20px;">
                                <span>合计 ¥</span>
                                <span> {{ totalPrice.toFixed(2) }} </span>
                            </div>
                            <div v-if="payOid != 0" style="display: flex; margin-top: 20px;">
                                <div style="flex: 2"></div>
                                <el-button style="flex: 1;" @click="cancelPay">取消</el-button>
                                <el-button type="primary" style="flex: 1;" @click="confirmPay">确认</el-button>
                            </div>
                            <div></div>
                        </el-dialog>
                        <el-dialog title="确认" :visible.sync="confirmTableVisible" width="20%" style="min-width: 100px;">
                            <div style="font-size: 16px; margin-bottom: 30px;">支付是否已经完成?</div>
                            <el-button type="primary" @click="$router.go(0)">确认</el-button>
                        </el-dialog>
                    </div>
                </el-col>
            </el-container>
        </el-row>
    </div>
</template>
<script>
export default {
    name: 'browse',
    data() {
        return {
            user: JSON.parse(localStorage.getItem('uInfo') || '{}'),
            tableData: [],
            gridData: [],
            dialogTableVisible: false,
            confirmTableVisible: false,
            payOid: 0,
            totalPrice: 0.0
        }
    },
    mounted() {
        this.$request.post('/order/get', this.user.uid).then(res => {
            if (res.code == '200') {
                this.tableData = res.data;
            } else {
                this.$message.error(res.msg);
                localStorage.setItem("target", '/space/user/order');
            }
        });
        if (localStorage.getItem("order")) {
            var oid = localStorage.getItem("order");
            localStorage.removeItem("order");
            this.$request.post('/purchase/select', parseInt(oid)).then(res => {
                if (res.code == '200') {
                    this.payOid = parseInt(oid);
                    this.gridData = res.data;
                    this.gridData.forEach(row => {
                        this.totalPrice += row.total;
                    });
                    this.dialogTableVisible = true;
                } else {
                    this.$message.error(res.msg);
                }
            })
        }
    },
    methods: {
        handlePay(row) {
            this.$request.post('/purchase/select', row.oid).then(res => {
                if (res.code == '200') {
                    this.payOid = row.oid;
                    this.gridData = res.data;
                    this.totalPrice = 0.0;
                    this.gridData.forEach(row => {
                        this.totalPrice += row.total;
                    });
                    this.dialogTableVisible = true;
                } else {
                    this.$message.error(res.msg);
                }
            })
        },
        confirmPay() {
            window.open(this.$baseUrl + "/alipay/pay?oid=" + this.payOid);
            this.dialogTableVisible = false;
            this.confirmTableVisible = true;
        },
        cancelPay() {
            this.dialogTableVisible = false;
            this.gridData = [];
            this.payOid = 0;
        },
        handleDelete(row) {
            this.$request.post('/order/delete?oid=' + row.oid + '&uid=' + this.user.uid).then(res => {
                if (res.code == '200') {
                    this.tableData = res.data;
                } else {
                    this.$message.error(res.msg);
                }
            })
        },
        handleCheck(row) {
            this.$request.post('/purchase/select', row.oid).then(res => {
                if (res.code == '200') {
                    this.payOid = 0;
                    this.gridData = res.data;
                    this.totalPrice = 0.0;
                    this.gridData.forEach(row => {
                        this.totalPrice += row.total;
                    });
                    this.dialogTableVisible = true;
                } else {
                    this.$message.error(res.msg);
                }
            })
        }
    }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: visible;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    border-color: rgba(0, 0, 0, 0.2);
    border-width: 1px;
    border-style: dotted;
    font-size: 28px;
    color: rgba(0, 0, 0, 0.5);
    border-radius: 6px;
    width: 150px;
    height: 150px;
    line-height: 150px;
    text-align: center;
}

.avatar {
    width: 150px;
    height: 150px;
    display: flex;
}
</style>
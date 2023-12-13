<template>
    <div style="width: 100%; margin-top: 20px;">
        <el-row style="height: 40px; width: 100%;">
            <div style="text-align: center; font-size: 24px;">商品购买记录</div>
        </el-row>
        <el-row style="width: 100%;">
            <el-container style="width: 100%;">
                <el-col :span="1">
                    <div></div>
                </el-col>
                <el-col :span="23">
                    <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" stripe height="480">
                        <el-table-column property="name" label="商品" width="250">
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
                        <el-table-column label="客户">
                            <template slot-scope="scope">
                                <span style="overflow: hidden; height: 50px;">{{ scope.row.username }}</span>
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
                        <el-table-column prop="time" label="购买时间">
                            <template slot-scope="scope">
                                {{ scope.row.time }}
                            </template>
                        </el-table-column>
                    </el-table>
                </el-col>
            </el-container>
        </el-row>
    </div>
</template>
<script>
export default {
    name: 'purchase',
    data() {
        return {
            shop: JSON.parse(localStorage.getItem('sInfo') || '{}'),
            tableData: []
        }
    },
    mounted() {
        this.$request.post('/purchase/get/shop', this.shop.sid).then(res => {
            if (res.code === '200') {
                this.tableData = res.data;
            } else {
                this.$message.error(res.msg);
            }
        });
    },
    methods: {
        
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
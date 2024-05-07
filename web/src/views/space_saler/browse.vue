<template>
    <div style="width: 100%; margin-top: 20px;">
        <el-row style="height: 40px; width: 100%;">
            <div style="text-align: center; font-size: 24px;">商品浏览记录</div>
        </el-row>
        <el-row style="height: 100px; width: 100%;">
            <el-container style="width: 100%;">
                <el-col :span="1">
                    <div></div>
                </el-col>
                <el-col :span="23">
                    <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" stripe height="480">
                        <el-table-column prop="name" label="商品名" width="400">
                        </el-table-column>
                        <el-table-column prop="count" label="浏览次数" sortable>
                        </el-table-column>
                        <el-table-column fixed="right" label="操作" width="100">
                            <template slot-scope="scope">
                                <el-button type="text" size="small"
                                    @click="handleEdit(scope.$index, scope.row)">查看具体记录</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div style="margin-top: 20px">
                        <el-dialog title="详情" :visible.sync="dialogTableVisible">
                            <el-table :data="gridData">
                                <el-table-column property="username" label="名称" width="200"></el-table-column>
                                <el-table-column property="time" label="时间"></el-table-column>
                            </el-table>
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
            saler: JSON.parse(localStorage.getItem('sInfo') || '{}'),
            tableData: [],
            gridData: [],
            dialogTableVisible: false,
            formLabelWidth: '120px'
        }
    },
    mounted() {
        var path = "/browse/get/saler?sid=" + this.saler.sid + "&saler=" + this.saler.id;
        this.$request.get(path).then(res => {
            if (res.code === '200') {
                this.tableData = res.data;
            } else if (res.code == '404') {
                this.$message.warning(res.msg);
            } else {
                this.$message.error(res.msg);
            }
        });
    },
    methods: {
        handleEdit(index, row) {
            this.$request.post('/browse/get/shop/details', row.gid).then(res => {
                if (res.code === '200') {
                    this.gridData = res.data;
                    this.dialogTableVisible = true;
                } else {
                    this.$message.error(res.msg);
                }
            });
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
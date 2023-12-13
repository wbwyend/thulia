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
                            <template slot-scope="scope">
                                <el-link :underline="false">
                                    <div @click="link(scope.$index, scope.row)"> {{ scope.row.name }} </div>
                                </el-link>
                            </template>
                        </el-table-column>
                        <el-table-column prop="shopname" label="商家" width="100">
                        </el-table-column>
                        <el-table-column prop="time" label="浏览时间">
                        </el-table-column>
                    </el-table>
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
            tableData: []
        }
    },
    mounted() {
        this.$request.post('/browse/get/user', this.user.uid).then(res => {
            if (res.code === '200') {
                this.tableData = res.data;
            } else {
                this.$message.error(res.msg);
            }
        });
    },
    methods: {
        link(index, row) {
            var url = '/details/' + row.gid;
            console.log(url);
            this.$router.push(url);
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
<template>
    <div style="width: 100%; margin-top: 20px;">
        <el-row style="height: 40px; width: 100%;">
            <div style="text-align: center; font-size: 24px;">销售人员管理</div>
        </el-row>
        <el-row style="height: 100px; width: 100%;">
            <el-container style="width: 100%;">
                <el-col :span="1">
                    <div></div>
                </el-col>
                <el-col :span="23">
                    <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" stripe height="480"
                        @selection-change="handleSelectionChange">
                        <el-table-column type="selection" width="50">
                        </el-table-column>
                        <el-table-column prop="id" label="id" width="75">
                        </el-table-column>
                        <el-table-column prop="name" label="名称" width="200">
                        </el-table-column>
                        <el-table-column prop="username" label="用户名" width="150" sortable>
                        </el-table-column>
                        <el-table-column prop="password" label="密码">
                        </el-table-column>
                        <el-table-column fixed="right" label="操作" width="90">
                            <template slot-scope="scope">
                                <el-button type="text" size="small"
                                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                                <el-button type="text" size="small"
                                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div style="margin-top: 20px">
                        <el-button @click="toggleSelection()">取消选择</el-button>
                        <el-button @click="batchDelete()">批量删除</el-button>
                        <el-button @click="dialogAddFormVisible = true">添加销售</el-button>
                        <el-dialog title="添加销售" :visible.sync="dialogAddFormVisible" style="">
                            <el-form :model="form" :rules="rules" ref="addRef">
                                <el-form-item label="销售名称" :label-width="formLabelWidth" prop="name">
                                    <el-input v-model="form.name" autocomplete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
                                    <el-input v-model="form.username" autocomplete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
                                    <el-input v-model="form.password" autocomplete="off"></el-input>
                                </el-form-item>
                            </el-form>
                            <div slot="footer" class="dialog-footer">
                                <el-button @click="cancelAdd">取 消</el-button>
                                <el-button type="primary" @click="addSalers">确 定</el-button>
                            </div>
                        </el-dialog>
                        <el-dialog title="编辑商品信息" :visible.sync="dialogEditFormVisible" style="">
                            <el-form :model="form" :rules="rules" ref="editRef">
                                <el-form :model="form" :rules="rules" ref="addRef">
                                    <el-form-item label="销售名称" :label-width="formLabelWidth" prop="name">
                                        <el-input v-model="form.name" autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
                                        <el-input v-model="form.username" autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
                                        <el-input v-model="form.password" autocomplete="off"></el-input>
                                    </el-form-item>
                                </el-form>
                            </el-form>
                            <div slot="footer" class="dialog-footer">
                                <el-button @click="cancelEdit">取 消</el-button>
                                <el-button type="primary" @click="editSalers">确 定</el-button>
                            </div>
                        </el-dialog>
                    </div>
                </el-col>
            </el-container>
        </el-row>
    </div>
</template>

<script>
export default {
    name: 'account',
    data() {
        return {
            shop: JSON.parse(localStorage.getItem('sInfo') || '{}'),
            tableData: [],
            multipleSelection: [],
            dialogAddFormVisible: false,
            dialogEditFormVisible: false,
            form: {
                name: '',
                username: '',
                password: '',
            },
            rules: {
                name: [
                    { required: true, message: '请输入名称', trigger: 'blur' },
                ],
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                ]
            },
            formLabelWidth: '120px'
        }
    },
    mounted() {
        this.$request.post('/saler/get/shop', this.shop.sid).then(res => {
            if (res.code === '200') {
                this.tableData = res.data;
                console.log(res.data);
            } else {
                this.$message.error(res.msg);
            }
        })
    },
    methods: {
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        batchDelete() {
            if (this.multipleSelection.length === 0) {
                this.$message({
                    type: 'warning',
                    message: '未选择'
                });
            } else {
                this.$confirm('此操作将删除该销售人员的ID, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$request.post('/saler/batch/delete/shop', this.multipleSelection).then(res => {
                        if (res.code === '200') {
                            this.$message({
                                type: 'success',
                                message: '操作成功!'
                            });
                            this.tableData = res.data;
                        } else {
                            this.$message.error(res.msg);
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消操作'
                    });
                });
            }
        },
        addSalers() {
            this.$refs['addRef'].validate((valid) => {
                if (valid) {
                    this.form.sid = this.shop.sid;
                    this.$request.post('/saler/add/shop', this.form).then(res => {
                        if (res.code === '200') {
                            this.$message.success('添加销售人员账号成功');
                            this.tableData = res.data;
                        } else {
                            this.$message.error(res.msg);
                        }
                    })
                    this.dialogAddFormVisible = false;
                    this.form = {};
                }
            })
        },
        cancelAdd() {
            this.dialogAddFormVisible = false;
            this.form = {};
        },
        handleDelete(index, row) {
            this.$confirm('此操作将删除该销售人员的账号, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$request.post('/saler/delete/shop', row).then(res => {
                    if (res.code === '200') {
                        this.$message({
                            type: 'success',
                            message: '操作成功!'
                        });
                        this.tableData = res.data;
                    } else {
                        this.$message.error(res.msg);
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消操作'
                });
            });
        },
        handleEdit(index, row) {
            this.dialogEditFormVisible = true;
            this.form = row;
        },
        cancelEdit() {
            this.dialogEditFormVisible = false;
            this.form = {};
        },
        editSalers() {
            this.$refs['editRef'].validate((valid) => {
                if (valid) {
                    this.form.sid = this.shop.sid;
                    this.$request.post('/saler/update/shop', this.form).then(res => {
                        if (res.code === '200') {
                            this.$message.success('修改销售人员信息成功');
                            this.tableData = res.data;
                        } else {
                            this.$message.error(res.msg);
                        }
                    })
                    this.dialogEditFormVisible = false;
                    this.form = {};
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
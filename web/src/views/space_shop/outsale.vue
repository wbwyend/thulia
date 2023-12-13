<template>
    <div style="width: 100%; margin-top: 20px;">
        <el-row style="height: 40px; width: 100%;">
            <div style="text-align: center; font-size: 24px;">下架商品管理</div>
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
                        <el-table-column prop="name" label="商品名" width="200">
                        </el-table-column>
                        <el-table-column prop="price" label="价格" width="75" sortable>
                        </el-table-column>
                        <el-table-column prop="surplus" label="存量" width="75" sortable>
                        </el-table-column>
                        <el-table-column prop="sales" label="销量" width="75" sortable>
                        </el-table-column>
                        <el-table-column prop="picture" label="商品图片">
                        </el-table-column>
                        <el-table-column fixed="right" label="操作" width="90">
                            <template slot-scope="scope">
                                <el-button type="text" size="small"
                                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                                <el-button type="text" size="small"
                                    @click="handleAdd(scope.$index, scope.row)">上架</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div style="margin-top: 20px">
                        <el-button @click="toggleSelection()">取消选择</el-button>
                        <el-button @click="batchAdd()">批量上架</el-button>
                        <el-dialog title="编辑商品信息" :visible.sync="dialogEditFormVisible" style="">
                            <el-form :model="form" :rules="rules" ref="editRef">
                                <el-form-item label="商品名称" :label-width="formLabelWidth" prop="name">
                                    <el-input v-model="form.name" autocomplete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="商品价格" :label-width="formLabelWidth" prop="price">
                                    <el-input v-model="form.price" autocomplete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="商品库存" :label-width="formLabelWidth" prop="surplus">
                                    <el-input v-model="form.surplus" autocomplete="off"></el-input>
                                </el-form-item>
                                <el-row style="width: 100%; height: 140px;">
                                    <el-container>
                                        <el-aside style="width: 120px; height: 200px;">
                                            <label class="el-form-item__label" style="width: 120px;">商品图片</label>
                                        </el-aside>
                                        <el-main style="padding: 0; display: flex;">
                                            <div style="flex: 2;">
                                                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                                            </div>
                                            <div style="flex: 5;">
                                                <el-upload :action="$baseUrl + '/file/upload/picture/goods'"
                                                    :show-file-list="false" :headers="{ token: shop.token }"
                                                    :on-success="handlePictureSuccess" :before-upload="beforePictureUpload">
                                                    <i class="el-icon-plus avatar-uploader-icon"></i>
                                                </el-upload>
                                            </div>
                                        </el-main>
                                    </el-container>
                                </el-row>
                            </el-form>
                            <div slot="footer" class="dialog-footer">
                                <el-button @click="cancelEdit">取 消</el-button>
                                <el-button type="primary" @click="editGoods">确 定</el-button>
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
    name: 'outsale',
    data() {
        return {
            shop: JSON.parse(localStorage.getItem('sInfo') || '{}'),
            imageUrl: '',
            tableData: [],
            multipleSelection: [],
            dialogAddFormVisible: false,
            dialogEditFormVisible: false,
            form: {
                name: '',
                price: '',
                surplus: '',
                sales: '',
                picture: '',
                sid: '',
                onsale: ''
            },
            rules: {
                name: [
                    { required: true, message: '请输入商品名称', trigger: 'blur' },
                ],
                price: [
                    { required: true, message: '请输入商品价格', trigger: 'blur' },
                ],
                surplus: [
                    { required: true, message: '请输入商品库存', trigger: 'blur' },
                ]
            },
            formLabelWidth: '120px'
        }
    },
    mounted() {
        this.$request.post('/goods/get/outsale/shop', this.shop.sid).then(res => {
            if (res.code === '200') {
                this.tableData = res.data;
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
        batchAdd() {
            if (this.multipleSelection.length === 0) {
                this.$message({
                    type: 'warning',
                    message: '未选择'
                });
            } else {
                this.$confirm('此操作将重新上架所选商品, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$request.post('/goods/batch/list/shop', this.multipleSelection).then(res => {
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
        handlePictureSuccess(response, file, fileList) {
            this.imageUrl = response.data
        },
        beforePictureUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isPNG = file.type === 'image/png';
            const isLt2M = file.size / 1024 / 1024 < 16;

            if (!isJPG && !isPNG) {
                this.$message.error('上传图片只能是 JPG 格式或 PNG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 16MB!');
            }
            return (isJPG || isPNG) && isLt2M;
        },
        handleAdd(index, row) {
            this.$confirm('此操作将重新上架该商品, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$request.post('/goods/list/shop', row).then(res => {
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
            this.imageUrl = row.picture;
        },
        cancelEdit() {
            this.dialogEditFormVisible = false;
            this.form = {};
            this.imageUrl = '';
        },
        editGoods() {
            this.$refs['editRef'].validate((valid) => {
                if (valid) {
                    this.form.picture = this.imageUrl;
                    this.form.sid = this.shop.sid;
                    this.form.onsale = 'false';
                    this.$request.post('/goods/update/shop', this.form).then(res => {
                        if (res.code === '200') {
                            this.$message.success('修改商品信息成功');
                            this.tableData = res.data;
                        } else {
                            this.$message.error(res.msg);
                        }
                    })
                    this.dialogEditFormVisible = false;
                    this.form = {};
                    this.imageUrl = '';
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
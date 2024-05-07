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
                        <el-table-column prop="name" label="商品名" width="180">
                        </el-table-column>
                        <el-table-column prop="cid" label="类型" width="100">
                            <template slot-scope="scope">
                                {{ options.at(scope.row.cid - 1).label }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="price" label="价格" width="75" sortable>
                        </el-table-column>
                        <el-table-column prop="surplus" label="存量" width="75" sortable>
                        </el-table-column>
                        <el-table-column prop="sales" label="销量" width="75" sortable>
                        </el-table-column>
                        <el-table-column prop="picture" label="商品图片">
                            <template slot-scope="scope">
                                <el-avatar shape="square" :size="60" :src="scope.row.picture"></el-avatar>
                            </template>
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
                                <el-form-item label="商品类型" :label-width="formLabelWidth" prop="cid">
                                    <el-select v-model="form.cid" placeholder="请选择">
                                        <el-option v-for="item in options" :key="item.value" :label="item.label"
                                            :value="item.value">
                                        </el-option>
                                    </el-select>
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
                onsale: '',
                saler: '',
                cid: ''
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
                ],
                cid: [
                    { required: true, message: '请选择商品类型', trigger: 'blur' },
                ]
            },
            formLabelWidth: '120px',
            options: [{
                value: 1,
                label: '家用电器'
            },
            {
                value: 2,
                label: '手机'
            },
            {
                value: 3,
                label: '运营商'
            },
            {
                value: 4,
                label: '数码'
            },
            {
                value: 5,
                label: '电脑'
            },
            {
                value: 6,
                label: '办公'
            },
            {
                value: 7,
                label: '文具用品'
            },
            {
                value: 8,
                label: '家居'
            },
            {
                value: 9,
                label: '家具'
            },
            {
                value: 10,
                label: '家装'
            },
            {
                value: 11,
                label: '厨具'
            },
            {
                value: 12,
                label: '男装'
            },
            {
                value: 13,
                label: '女装'
            },
            {
                value: 14,
                label: '童装'
            },
            {
                value: 15,
                label: '内衣'
            },
            {
                value: 16,
                label: '美妆'
            },
            {
                value: 17,
                label: '个护清洁'
            },
            {
                value: 18,
                label: '宠物'
            },
            {
                value: 19,
                label: '女鞋'
            },
            {
                value: 20,
                label: '箱包'
            },
            {
                value: 21,
                label: '钟表'
            },
            {
                value: 22,
                label: '珠宝'
            },
            {
                value: 23,
                label: '男鞋'
            },
            {
                value: 24,
                label: '运动'
            },
            {
                value: 25,
                label: '户外'
            },
            {
                value: 26,
                label: '房产'
            },
            {
                value: 27,
                label: '汽车'
            },
            {
                value: 28,
                label: '汽车用品'
            },
            {
                value: 29,
                label: '母婴'
            },
            {
                value: 30,
                label: '玩具乐器'
            },
            {
                value: 31,
                label: '食品'
            },
            {
                value: 32,
                label: '酒类'
            },
            {
                value: 33,
                label: '生鲜'
            },
            {
                value: 34,
                label: '特产'
            },
            {
                value: 35,
                label: '艺术'
            },
            {
                value: 36,
                label: '礼品鲜花'
            },
            {
                value: 37,
                label: '农牧园艺'
            },
            {
                value: 38,
                label: '京东买药'
            },
            {
                value: 39,
                label: '计生情趣'
            },
            {
                value: 40,
                label: '图书'
            },
            {
                value: 41,
                label: '文娱'
            },
            {
                value: 42,
                label: '教育'
            },
            {
                value: 43,
                label: '电子书'
            },
            {
                value: 44,
                label: '机票'
            },
            {
                value: 45,
                label: '酒店'
            },
            {
                value: 46,
                label: '旅游'
            },
            {
                value: 47,
                label: '生活'
            },
            {
                value: 48,
                label: '支付'
            },
            {
                value: 49,
                label: '白条'
            },
            {
                value: 50,
                label: '保险'
            },
            {
                value: 51,
                label: '企业金融'
            },
            {
                value: 52,
                label: '安装'
            },
            {
                value: 53,
                label: '维修'
            },
            {
                value: 54,
                label: '清洗'
            },
            {
                value: 55,
                label: '二手'
            },
            {
                value: 56,
                label: '五金机电'
            },
            {
                value: 56,
                label: '元器件'
            }

            ]
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
            this.imageUrl = response.substring(45, response.length - 16);
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
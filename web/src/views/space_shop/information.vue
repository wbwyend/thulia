<template>
    <div style="width: 100%; height: 650px; background-color: rgba(0, 0, 0, 0);">
        <el-container style="width: 800px; height: 650px;">
            <el-form :model="shop" :rules="rules" ref="modifyRef" label-width="80px"
                style="width: 100%; margin-left: 50px; margin-right: 50px; margin-top: 20px;">
                <el-row class="input" style="margin-bottom: 10px;">
                    <div style="text-align: center; font-size: 24px;">基本信息</div>
                </el-row>
                <el-row class="input">
                    <el-form-item label="账号" prop="username">
                        <el-input placeholder="账号" v-model="shop.username"></el-input>
                    </el-form-item>
                </el-row>
                <el-row class="input">
                    <el-form-item label="商家名" prop="shopname">
                        <el-input placeholder="商家名" v-model="shop.shopname"></el-input>
                    </el-form-item>
                </el-row>
                <el-row class="input">
                    <el-form-item label="电话" prop="phone">
                        <el-input placeholder="电话" v-model="shop.phone"></el-input>
                    </el-form-item>
                </el-row>
                <el-row class="input">
                    <el-form-item label="邮箱" prop="email">
                        <el-input placeholder="邮箱" v-model="shop.email"></el-input>
                    </el-form-item>
                </el-row>
                <el-row class="input">
                    <el-form-item label="地址" prop="address">
                        <el-cascader v-model="shopAddress" :options="options" size="medium"
                            :props="{ expandTrigger: 'hover' }"></el-cascader>
                    </el-form-item>
                </el-row>
                <el-row style="width: 100%; height: 180px;">
                    <el-container>
                        <el-aside style="width: 80px; height: 200px;">
                            <label class="el-form-item__label" style="width: 80px;">头像</label>
                        </el-aside>
                        <el-main style="padding: 0; display: flex;">
                            <div style="flex: 2;">
                                <img v-if="avataUrl" :src="avataUrl" class="avatar">
                            </div>
                            <div style="flex: 3;">
                                <el-upload :action="$baseUrl + '/file/upload/avatar/shop'" :show-file-list="false"
                                    :headers="{ token: shop.token }" :on-success="handleAvatarSuccess"
                                    :before-upload="beforeAvatarUpload">
                                    <i class="el-icon-plus avatar-uploader-icon"></i>
                                </el-upload>
                            </div>
                        </el-main>
                    </el-container>
                </el-row>
                <el-row style="width: 100%; height: 40px;">
                    <div style="margin: auto; width: 100%;">
                        <el-form-item>
                            <el-button @click="modify('modifyRef')"
                                style="width: 100%; height: 40px; font-size: 18px; border-radius: 1px; background-color: rgba(0, 0, 0, 0.06); border-width: 0px;">保存</el-button>
                        </el-form-item>
                    </div>
                </el-row>
            </el-form>
        </el-container>
    </div>
</template>
<script>
export default {
    name: 'information',
    data() {
        const validateEmail = (rule, value, callback) => {
            var emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/;
            if (value === '') {
                callback(new Error('请输入邮箱'));
            } else if (!emailReg.test(value)) {
                callback(new Error('邮箱格式错误'));
            } else {
                callback();
            }
        };
        return {
            shop: JSON.parse(localStorage.getItem('sInfo') || '{}'),
            avataUrl: JSON.parse(localStorage.getItem('sInfo') || '{}').avatar || '',
            shopAddress: [],
            rules: {
                username: [
                    { required: true, message: '请输入账号', trigger: 'blur' }
                ],
                shopname: [
                    { required: true, message: '请输入商家名', trigger: 'blur' }
                ],
                email: [
                    { required: true, validator: validateEmail, trigger: 'blur' }
                ],
                phone: [
                    { required: true, message: '请输入电话', trigger: 'blur' }
                ]
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
        }
    },
    mounted() {
        this.shopAddress = this.handleDataToAddress();
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
            addr = addr.concat(this.shop.address);
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
        handleAvatarSuccess(response, file, fileList) {
            this.avataUrl = response.data
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isPNG = file.type === 'image/png';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG && !isPNG) {
                this.$message.error('上传头像图片只能是 JPG 格式或 PNG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return (isJPG || isPNG) && isLt2M;
        },
        modify() {
            this.shop.address = this.handleAddressToData();
            this.$refs['modifyRef'].validate((valid) => {
                if (valid) {
                    this.shop.avatar = this.avataUrl;
                    this.$request.post('/shop/modify/information', this.shop).then(res => {
                        if (res.code === '200') {
                            this.$message.success('基本信息修改成功')
                            localStorage.setItem("sInfo", JSON.stringify(res.data));
                            localStorage.setItem("token", JSON.stringify(res.data.token));
                        } else {
                            this.$message.error(res.msg)
                        }
                    })
                }
            })
        }
    }
}
</script>
<style scoped >
.input {
    width: 100%;
    height: 40px;
}

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
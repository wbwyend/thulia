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
                                <el-link :underline="false" @click="toUrl('/home')"
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
                                <el-link :underline="false" @click="toUrl('/cart')"
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
                                <el-link :underline="false" @click="toUrl('/space/user/order')"
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
                                            <el-link :underline="false" @click="toUrl('/register')"
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
                                    <el-link :underline="false" @click="toUrl('/space/user')"
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
                        <div
                            style="font-size: 20px; overflow-wrap: normal; width: 100%; height: 80px; padding: 25px 0;">
                            {{ shop.shopname }}
                        </div>
                    </el-col>
                    <el-col :span="9">
                        <div style="width: 300px; height: 80px;"></div>
                    </el-col>
                    <el-col :span="3">
                        <el-link :underline="false" @click="toUrl('/shop/' + shop.sid)" style="margin: auto; width: 90px; height: 30px; margin-top: 25px; border-radius: 10px;
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
                                        style="width: 160px; margin-left: 10px;"></el-cascader>
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
            options: [{ value: '北京', label: '北京' },
            { value: '上海', label: '上海' },
            { value: '天津', label: '天津' },
            { value: '重庆', label: '重庆' },
            { value: '河北', label: '河北', children: [{ value: '石家庄', label: '石家庄' }, { value: '唐山', label: '唐山' }, { value: '秦皇岛', label: '秦皇岛' }, { value: '邯郸', label: '邯郸' }, { value: '邢台', label: '邢台' }, { value: '保定', label: '保定' }, { value: '张家口', label: '张家口' }, { value: '承德', label: '承德' }, { value: '沧州', label: '沧州' }, { value: '廊坊', label: '廊坊' }, { value: '衡水', label: '衡水' }] },
            { value: '山西', label: '山西', children: [{ value: '太原', label: '太原' }, { value: '大同', label: '大同' }, { value: '阳泉', label: '阳泉' }, { value: '长治', label: '长治' }, { value: '晋城', label: '晋城' }, { value: '朔州', label: '朔州' }, { value: '晋中', label: '晋中' }, { value: '运城', label: '运城' }, { value: '忻州', label: '忻州' }, { value: '临汾', label: '临汾' }, { value: '吕梁', label: '吕梁' }] },
            { value: '内蒙古', label: '内蒙古', children: [{ value: '呼和浩特', label: '呼和浩特' }, { value: '包头', label: '包头' }, { value: '乌海', label: '乌海' }, { value: '赤峰', label: '赤峰' }, { value: '通辽', label: '通辽' }, { value: '鄂尔多斯', label: '鄂尔多斯' }, { value: '呼伦贝尔', label: '呼伦贝尔' }, { value: '巴彦淖尔', label: '巴彦淖尔' }, { value: '乌兰察布', label: '乌兰察布' }] },
            { value: '辽宁', label: '辽宁', children: [{ value: '沈阳', label: '沈阳' }, { value: '大连', label: '大连' }, { value: '鞍山', label: '鞍山' }, { value: '抚顺', label: '抚顺' }, { value: '本溪', label: '本溪' }, { value: '丹东', label: '丹东' }, { value: '锦州', label: '锦州' }, { value: '营口', label: '营口' }, { value: '阜新', label: '阜新' }, { value: '辽阳', label: '辽阳' }, { value: '盘锦', label: '盘锦' }, { value: '铁岭', label: '铁岭' }, { value: '朝阳', label: '朝阳' }, { value: '葫芦岛', label: '葫芦岛' }] },
            { value: '吉林', label: '吉林', children: [{ value: '长春', label: '长春' }, { value: '吉林', label: '吉林' }, { value: '四平', label: '四平' }, { value: '辽源', label: '辽源' }, { value: '通化', label: '通化' }, { value: '白山', label: '白山' }, { value: '松原', label: '松原' }, { value: '白城', label: '白城' }] },
            { value: '黑龙江', label: '黑龙江', children: [{ value: '哈尔滨', label: '哈尔滨' }, { value: '齐齐哈尔', label: '齐齐哈尔' }, { value: '鸡西', label: '鸡西' }, { value: '鹤岗', label: '鹤岗' }, { value: '双鸭山', label: '双鸭山' }, { value: '大庆', label: '大庆' }, { value: '伊春', label: '伊春' }, { value: '佳木斯', label: '佳木斯' }, { value: '七台河', label: '七台河' }, { value: '牡丹江', label: '牡丹江' }, { value: '黑河', label: '黑河' }, { value: '绥化', label: '绥化' }] },
            { value: '江苏', label: '江苏', children: [{ value: '南京', label: '南京' }, { value: '无锡', label: '无锡' }, { value: '徐州', label: '徐州' }, { value: '常州', label: '常州' }, { value: '苏州', label: '苏州' }, { value: '南通', label: '南通' }, { value: '连云港', label: '连云港' }, { value: '淮安', label: '淮安' }, { value: '盐城', label: '盐城' }, { value: '扬州', label: '扬州' }, { value: '镇江', label: '镇江' }, { value: '泰州', label: '泰州' }, { value: '宿迁', label: '宿迁' }] },
            { value: '浙江', label: '浙江', children: [{ value: '杭州', label: '杭州' }, { value: '宁波', label: '宁波' }, { value: '温州', label: '温州' }, { value: '嘉兴', label: '嘉兴' }, { value: '湖州', label: '湖州' }, { value: '绍兴', label: '绍兴' }, { value: '金华', label: '金华' }, { value: '衢州', label: '衢州' }, { value: '舟山', label: '舟山' }, { value: '台州', label: '台州' }, { value: '丽水', label: '丽水' }] },
            { value: '安徽', label: '安徽', children: [{ value: '合肥', label: '合肥' }, { value: '芜湖', label: '芜湖' }, { value: '蚌埠', label: '蚌埠' }, { value: '淮南', label: '淮南' }, { value: '马鞍山', label: '马鞍山' }, { value: '淮北', label: '淮北' }, { value: '铜陵', label: '铜陵' }, { value: '安庆', label: '安庆' }, { value: '黄山', label: '黄山' }, { value: '阜阳', label: '阜阳' }, { value: '宿州', label: '宿州' }, { value: '滁州', label: '滁州' }, { value: '六安', label: '六安' }, { value: '宣城', label: '宣城' }, { value: '池州', label: '池州' }, { value: '亳州', label: '亳州' }] },
            { value: '福建', label: '福建', children: [{ value: '福州', label: '福州' }, { value: '厦门', label: '厦门' }, { value: '莆田', label: '莆田' }, { value: '三明', label: '三明' }, { value: '泉州', label: '泉州' }, { value: '漳州', label: '漳州' }, { value: '南平', label: '南平' }, { value: '龙岩', label: '龙岩' }, { value: '宁德', label: '宁德' }] },
            { value: '江西', label: '江西', children: [{ value: '南昌', label: '南昌' }, { value: '景德镇', label: '景德镇' }, { value: '萍乡', label: '萍乡' }, { value: '九江', label: '九江' }, { value: '抚州', label: '抚州' }, { value: '鹰潭', label: '鹰潭' }, { value: '赣州', label: '赣州' }, { value: '吉安', label: '吉安' }, { value: '宜春', label: '宜春' }, { value: '新余', label: '新余' }, { value: '上饶', label: '上饶' }] },
            { value: '山东', label: '山东', children: [{ value: '济南', label: '济南' }, { value: '青岛', label: '青岛' }, { value: '淄博', label: '淄博' }, { value: '枣庄', label: '枣庄' }, { value: '东营', label: '东营' }, { value: '烟台', label: '烟台' }, { value: '潍坊', label: '潍坊' }, { value: '济宁', label: '济宁' }, { value: '泰安', label: '泰安' }, { value: '威海', label: '威海' }, { value: '日照', label: '日照' }, { value: '临沂', label: '临沂' }, { value: '德州', label: '德州' }, { value: '聊城', label: '聊城' }, { value: '滨州', label: '滨州' }, { value: '菏泽', label: '菏泽' }] },
            { value: '河南', label: '河南', children: [{ value: '郑州', label: '郑州' }, { value: '开封', label: '开封' }, { value: '洛阳', label: '洛阳' }, { value: '平顶山', label: '平顶山' }, { value: '安阳', label: '安阳' }, { value: '鹤壁', label: '鹤壁' }, { value: '新乡', label: '新乡' }, { value: '焦作', label: '焦作' }, { value: '濮阳', label: '濮阳' }, { value: '许昌', label: '许昌' }, { value: '漯河', label: '漯河' }, { value: '三门峡', label: '三门峡' }, { value: '南阳', label: '南阳' }, { value: '商丘', label: '商丘' }, { value: '信阳', label: '信阳' }, { value: '周口', label: '周口' }, { value: '驻马店', label: '驻马店' }] },
            { value: '湖北', label: '湖北', children: [{ value: '武汉', label: '武汉' }, { value: '黄石', label: '黄石' }, { value: '十堰', label: '十堰' }, { value: '宜昌', label: '宜昌' }, { value: '襄阳', label: '襄阳' }, { value: '鄂州', label: '鄂州' }, { value: '荆门', label: '荆门' }, { value: '孝感', label: '孝感' }, { value: '荆州', label: '荆州' }, { value: '黄冈', label: '黄冈' }, { value: '咸宁', label: '咸宁' }, { value: '随州', label: '随州' }] },
            { value: '湖南', label: '湖南', children: [{ value: '长沙', label: '长沙' }, { value: '株洲', label: '株洲' }, { value: '湘潭', label: '湘潭' }, { value: '衡阳', label: '衡阳' }, { value: '邵阳', label: '邵阳' }, { value: '岳阳', label: '岳阳' }, { value: '常德', label: '常德' }, { value: '张家界', label: '张家界' }, { value: '益阳', label: '益阳' }, { value: '郴州', label: '郴州' }, { value: '永州', label: '永州' }, { value: '怀化', label: '怀化' }, { value: '娄底', label: '娄底' }] },
            { value: '广东', label: '广东', children: [{ value: '广州', label: '广州' }, { value: '韶关', label: '韶关' }, { value: '深圳', label: '深圳' }, { value: '珠海', label: '珠海' }, { value: '汕头', label: '汕头' }, { value: '佛山', label: '佛山' }, { value: '江门', label: '江门' }, { value: '湛江', label: '湛江' }, { value: '茂名', label: '茂名' }, { value: '肇庆', label: '肇庆' }, { value: '惠州', label: '惠州' }, { value: '梅州', label: '梅州' }, { value: '汕尾', label: '汕尾' }, { value: '河源', label: '河源' }, { value: '阳江', label: '阳江' }, { value: '清远', label: '清远' }, { value: '东莞', label: '东莞' }, { value: '中山', label: '中山' }, { value: '潮州', label: '潮州' }, { value: '揭阳', label: '揭阳' }, { value: '云浮', label: '云浮' }] },
            { value: '广西', label: '广西', children: [{ value: '南宁', label: '南宁' }, { value: '柳州', label: '柳州' }, { value: '桂林', label: '桂林' }, { value: '梧州', label: '梧州' }, { value: '北海', label: '北海' }, { value: '防城港', label: '防城港' }, { value: '钦州', label: '钦州' }, { value: '贵港', label: '贵港' }, { value: '玉林', label: '玉林' }, { value: '百色', label: '百色' }, { value: '贺州', label: '贺州' }, { value: '河池', label: '河池' }, { value: '来宾', label: '来宾' }, { value: '崇左', label: '崇左' }] },
            { value: '海南', label: '海南', children: [{ value: '海口', label: '海口' }, { value: '三亚', label: '三亚' }, { value: '三沙', label: '三沙' }, { value: '儋州', label: '儋州' }] },
            { value: '四川', label: '四川', children: [{ value: '成都', label: '成都' }, { value: '自贡', label: '自贡' }, { value: '攀枝花', label: '攀枝花' }, { value: '泸州', label: '泸州' }, { value: '德阳', label: '德阳' }, { value: '绵阳', label: '绵阳' }, { value: '广元', label: '广元' }, { value: '遂宁', label: '遂宁' }, { value: '内江', label: '内江' }, { value: '乐山', label: '乐山' }, { value: '南充', label: '南充' }, { value: '眉山', label: '眉山' }, { value: '宜宾', label: '宜宾' }, { value: '广安', label: '广安' }, { value: '达州', label: '达州' }, { value: '雅安', label: '雅安' }, { value: '巴中', label: '巴中' }, { value: '资阳', label: '资阳' }] },
            { value: '贵州', label: '贵州', children: [{ value: '贵阳', label: '贵阳' }, { value: '六盘水', label: '六盘水' }, { value: '遵义', label: '遵义' }, { value: '安顺', label: '安顺' }, { value: '毕节', label: '毕节' }, { value: '铜仁', label: '铜仁' }] },
            { value: '云南', label: '云南', children: [{ value: '昆明', label: '昆明' }, { value: '曲靖', label: '曲靖' }, { value: '玉溪', label: '玉溪' }, { value: '保山', label: '保山' }, { value: '昭通', label: '昭通' }, { value: '丽江', label: '丽江' }, { value: '普洱', label: '普洱' }, { value: '临沧', label: '临沧' }] },
            { value: '西藏', label: '西藏', children: [{ value: '拉萨', label: '拉萨' }, { value: '日喀则', label: '日喀则' }, { value: '昌都', label: '昌都' }, { value: '林芝', label: '林芝' }, { value: '山南', label: '山南' }, { value: '那曲', label: '那曲' }] },
            { value: '陕西', label: '陕西', children: [{ value: '西安', label: '西安' }, { value: '铜川', label: '铜川' }, { value: '宝鸡', label: '宝鸡' }, { value: '咸阳', label: '咸阳' }, { value: '渭南', label: '渭南' }, { value: '延安', label: '延安' }, { value: '汉中', label: '汉中' }, { value: '榆林', label: '榆林' }, { value: '安康', label: '安康' }, { value: '商洛', label: '商洛' }] },
            { value: '甘肃', label: '甘肃', children: [{ value: '兰州', label: '兰州' }, { value: '嘉峪关', label: '嘉峪关' }, { value: '金昌', label: '金昌' }, { value: '白银', label: '白银' }, { value: '天水', label: '天水' }, { value: '武威', label: '武威' }, { value: '张掖', label: '张掖' }, { value: '平凉', label: '平凉' }, { value: '酒泉', label: '酒泉' }, { value: '庆阳', label: '庆阳' }, { value: '定西', label: '定西' }, { value: '陇南', label: '陇南' }] },
            { value: '青海', label: '青海', children: [{ value: '西宁', label: '西宁' }, { value: '海东', label: '海东' }] },
            { value: '宁夏', label: '宁夏', children: [{ value: '银川', label: '银川' }, { value: '石嘴山', label: '石嘴山' }, { value: '吴忠', label: '吴忠' }, { value: '固原', label: '固原' }, { value: '中卫', label: '中卫' }] },
            { value: '新疆', label: '新疆', children: [{ value: '乌鲁木齐', label: '乌鲁木齐' }, { value: '克拉玛依', label: '克拉玛依' }, { value: '吐鲁番', label: '吐鲁番' }, { value: '哈密', label: '哈密' }] }
            ],
            startSec: 0,
            sent: false
        };
    },

    mounted() {
        this.$request.post('/goods/get/details', this.gid).then(res => {
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
        this.startSec = Date.now();
        window.onbeforeunload = () => {
            var browsHistory = {
                uid: this.userId,
                sid: this.goods.sid,
                gid: this.goods.gid,
                time: this.startSec,
                seconds: (Date.now() - this.startSec) / 1000
            };
            if (!this.sent) {
                this.$request.post('/browse/add', browsHistory).then(res => {
                    if (res.code === '200') {
                        this.sent = true;
                    } else {
                        this.$message.error(res.msg);
                    }
                });
            }

        }
    },
    beforeDestroy() {
        var browsHistory = {
            uid: this.userId,
            sid: this.goods.sid,
            gid: this.goods.gid,
            time: this.startSec,
            seconds: (Date.now() - this.startSec) / 1000
        };
        if (!this.sent) {
            this.$request.post('/browse/add', browsHistory).then(res => {
                if (res.code === '200') {
                    this.sent = true;
                } else {
                    this.$message.error(res.msg);
                }
            });
        }
    },
    methods: {
        toUrl(url) {
            this.$router.push(url);
        },
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
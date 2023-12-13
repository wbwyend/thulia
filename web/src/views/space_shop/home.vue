<template>
    <div style="width: 100%; height: 200px;">
        <el-container style="width: 100%; height: 500px; background-color: antiquewhite;">
            <el-row style="width: 100%; height: 100px; background-color: aliceblue; display: flex;">
                <div style="flex: 1; width: 80px; height: 80px; margin: 10px;">
                    <el-avatar :src="shop.avatar" style="margin: 10px; width: 60px; height: 60px;"></el-avatar>
                </div>
                <div style="flex: 8; height: 100px;">
                    <el-link :underline="false" herf="/space/user" style="height: 100px;">
                        <span style="font-size: 20px;">{{ shop.username }}</span>
                    </el-link>
                </div>
            </el-row>
        </el-container>
    </div>
</template>
<script>
export default {
    name: 'home',
    data() {
        return {
            shop: JSON.parse(localStorage.getItem('sInfo') ||'{}')
        }
    },
    mounted() {

    },
    methods: {
        
    }
}
</script>
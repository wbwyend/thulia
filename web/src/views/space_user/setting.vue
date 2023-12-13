<template>
    <div style="width: 100%; height: 200px;">
        <el-container style="width: 100%; height: 100px; background-color: antiquewhite;">
            <el-row style="width: 100%; height: 100px; background-color: aliceblue; display: flex;">
                <div style="flex: 1; width: 80px; height: 80px; margin: 10px;">
                    <el-avatar :src="user.avatar" style="margin: 10px; width: 60px; height: 60px;"></el-avatar>
                </div>
                <div style="flex: 7; height: 100px;">
                    <el-link :underline="false" herf="/space/user" style="height: 100px;">
                        <span style="font-size: 20px;">{{ user.username }}</span>
                    </el-link>
                </div>
                <div style="flex: 2; margin-top: 30px;">
                    <el-button @click="close">注销用户</el-button>
                </div>
            </el-row>
        </el-container>

    </div>
</template>
<script>
export default {
    name: 'setting',
    data() {
        return {
            user: JSON.parse(localStorage.getItem('uInfo') || '{}')
        }
    },
    mounted() {

    },
    methods: {
        close() {
            this.$confirm('此操作将永久注销你的账号, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$request.post('/user/delete', this.user.uid).then(res => {
                    if (res.code == '200') {
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        localStorage.removeItem('uInfo');
                        localStorage.removeItem('token');
                        this.$router.push('/home');
                    } else {
                        this.$message.error(res.msg);
                    }
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        }
    }
}
</script>
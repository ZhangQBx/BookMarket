<template>
    <div>
        <el-form :model="loginForm" :rules="fieldRules" ref="loginForm" label-position="left" label-width="0px" class="demo-ruleForm login-container">
            <h3 class="title">登录</h3>
            <el-form-item prop="account">
                <el-input type="text" v-model="loginForm.account" auto-complete="off" placeholder="账号"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item  style="width: 100%">
                <div class="action_box">
                    <el-button type="primary" style="width:48%;" @click.native.prevent="reg">注册</el-button>
                    <el-button type="primary" style="width:48%;" @click.native.prevent="login" :loading="loging">登录</el-button>
                </div>
            </el-form-item>
        </el-form>
        <el-dialog
        title="注册"
        :visible.sync="regDialogVisible"
        width="500px"
        center>
        <div>
            <el-form :model="regFrom" :rules="fieldRules" ref="regFrom" label-position="left" label-width="0px" >
                <el-form-item prop="account">
                    <el-input type="text" v-model="regFrom.account" auto-complete="off" placeholder="账号"></el-input>
                </el-form-item>
                <el-form-item prop="password1">
                    <el-input type="password" v-model="regFrom.password1" placeholder="密码"></el-input>
                </el-form-item>
                <el-form-item prop="password2">
                    <el-input type="password" v-model="regFrom.password2" placeholder="再次输入密码"></el-input>
                </el-form-item>
            </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="ergAction">注册</el-button>
  </span>
        </el-dialog>
    </div>

</template>

<style scoped>
.login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    
}
.title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
}
.remember {
    margin: 0px 0px 35px 0px;
}

.password {
    margin-top: 20px;
    margin-bottom: 20px;
}

.action_box {
    display: flex;

}

</style>

<script>
import qs from 'qs'

export default {
    name: 'Login',
    data() {
        return {
            loging: false,
            regFrom:{
                account: '',
                password1: '',
                password2: ''
            },
            loginForm: {
                account: '',
                password: ''
            },
            fieldRules: {
                account: [
                    {required: true, message: '请输入账号', trigger: 'blur'},
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                ],
                password1: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                ],
                password2: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                ]
            },
            checked: true,
            regDialogVisible: false
        }
    },
    methods: {
         isPoneAvailable: function(input) {
            var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
            if (!myreg.test(input)) {
                return false;
            } else {
                return true;
            }
        },

        login() {
            var that = this;

            if (that.loginForm.password.length == 0) {
                that.$message.error("账号或密码有误");
                return;
            }

            if (!this.isPoneAvailable(that.loginForm.account)) {
                that.$message.error("账号有误，请输入手机号");
                return;
            }

            var param = {
                phone: parseInt(that.loginForm.account),
                pwd: that.loginForm.password
            }
            this.axios.post('/v1/user/login',qs.stringify(param)
			).then((response) => {
                if (response.data.code == 1000) {

                    var user = {phone:that.loginForm.account}
                    localStorage.setItem('user',JSON.stringify(user))

                    that.$router.push('/')
                }else if (response.data.code == -1){
                    that.$message.error(response.data.msg);
                }else {
                    that.$message.error(response.data.error);
                }
			}).catch((response) => {
                that.$message.error("请求出错");
            })
        },
        reg() {
            this.regDialogVisible = true;
        },
        ergAction() {


            var that = this;

            if (that.regFrom.password1.length == 0  || that.regFrom.password2.length == 0 || that.regFrom.account.length == 0) {
                that.$message.error("账号或密码不能为空");
                return;
            }

            if (!this.isPoneAvailable(that.regFrom.account)) {
                that.$message.error("注册账号必须为手机号码");
                return;
            }

            if (that.regFrom.password1 != that.regFrom.password2) {
                that.$message.error("两次输入的密码不一致");
                return;
            }

            var param = {
                phone: that.regFrom.account,
                pwd: that.regFrom.password1
            }
            this.axios.post('/v1/user/register',qs.stringify(param)
			).then((response) => {
                if (response.data.code == 1000) {
                    that.regDialogVisible = false;
                }else if (response.data.code == -1){
                    this.$message.error(response.data.msg);
                }else {
                    this.$message.error(response.data.error);
                }
			}).catch((response) => {
                that.$message.error("请求出错");
            })
        }
    }    
}
</script>
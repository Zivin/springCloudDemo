<!--登录-->
<template>
  <el-form :model="loginForm" ref="loginForm" label-position="left" label-width="0px"
           class="demo-loginForm login-container">
    <h2 class="title">系统登录</h2>
    <el-form-item prop="account" :rules="this.$validator.validate('required')">
      <el-input type="text" v-model="loginForm.account" placeholder="账号"
                @keyup.enter.native="handleSubmit"></el-input>
    </el-form-item>
    <el-form-item prop="password" :rules="this.$validator.validate('required')">
      <el-input type="password" v-model="loginForm.password" placeholder="密码"
                @keyup.enter.native="handleSubmit"></el-input>
    </el-form-item>
    <el-form-item prop="verificationCode" :rules="this.$validator.validate('required')">
      <el-input type="text" v-model="loginForm.verificationCode" placeholder="验证码"
                style="width: 45%;" @keyup.enter.native="handleSubmit"></el-input>
      <img :src="this.verificationCodeUrl + '?verificationId=' + this.loginForm.verificationId"
           style="width: 50%; float: right;" @click="handleRefresh"/>
    </el-form-item>
    <el-form-item>
      <el-tooltip content="请不要在公共电脑使用此功能" placement="right">
        <el-checkbox v-model="rememberMe">记住密码</el-checkbox>
      </el-tooltip>
      <el-button type="text" @click="handleForget" style="float: right;">忘记密码</el-button>
    </el-form-item>
    <el-form-item>
      <el-button style="width:40%;" @click.native.prevent="handleReset">重置</el-button>
      <el-button type="primary" style="width:40%;float: right" @click.native.prevent="handleSubmit"
                 :loading="this.logining">
        登录
      </el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import {LOGIN, GET_VERIFICATION_CODE} from 'api/modules/login'
  import {setToken} from 'api/auth'
  import md5 from 'js-md5'
  import cookies from 'js-cookie'

  const cookieName = 'accountInfo';

  export default {
    data() {
      return {
        logining: false,
        loginForm: {
          account: '',
          password: '',
          verificationId: this.$util.getUuid(),
          verificationCode: ''
        },
        verificationCodeUrl: process.env.BASE_API + GET_VERIFICATION_CODE,
        rememberMe: false
      };
    },
    methods: {
      handleReset() {
        this.$refs.loginForm.resetFields();
        this.handleRefresh();
      },
      handleRefresh() {
        this.loginForm.verificationCode = '';
        this.loginForm.verificationId = this.$util.getUuid();
      },
      handleForget() {
        this.$message.info("忘记密码就再好好想想呗");
      },
      handleSubmit() {
        this.$refs.loginForm.validate((valid) => {
          if (!valid) {
            this.$message.error("请正确填写登录信息");
            return false;
          }
          this.logining = true;
          var loginParams = {
            account: this.loginForm.account,
            password: md5(this.loginForm.password),
            verificationId: this.loginForm.verificationId,
            verificationCode: this.loginForm.verificationCode
          };
          this.$http.post(LOGIN, loginParams, response => {
            this.logining = false;
            if (response.code == 0) {
              setToken(response.data.token);
              // 记住密码
              if (this.rememberMe) {
                cookies.set(cookieName, {
                  account: this.loginForm.account,
                  password: this.loginForm.password
                }, {expires: 30});
              } else {
                cookies.remove(cookieName);
              }
              this.$router.push({path: '/', query: {redirect: this.$route.params.redirect}});
            } else {
              this.handleRefresh();
            }
          }, error => {
            this.logining = false;
          });
        });
      }
    },
    mounted() {
      let accountInfo = cookies.getJSON(cookieName);
      if (accountInfo) {
        this.loginForm.account = accountInfo.account;
        this.loginForm.password = accountInfo.password;
        this.rememberMe = true;
      }
    }
  }
</script>

<style lang="scss" scoped>
  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 200px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    .title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #20a0ff;
    }
  }
</style>

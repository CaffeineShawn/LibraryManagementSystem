<template>
  <div v-if="!$store.state.isLoggedIn" class="container-fluid">
  <div class="text-end" >

    <div class="text-center">
      <h2  style="padding-bottom: 20px">User Panel</h2>
      <div class="mb-3 row">
        <label for="username" class="col-sm-4 col-form-label">Username</label>
        <div class="col-sm-4">
          <input type="text" v-model="username" class="form-control" id="username">
        </div>
      </div>
      <div class="mb-3 row">
        <label for="password" class="col-sm-4 col-form-label">Password</label>
        <div class="col-sm-4">
          <input type="password" v-model="password" class="form-control" id="password">
        </div>
      </div>
    </div>
<!--    </div>-->

    <div id="button-group" class="col text-center" style="margin-top: 30px">
      <button class="btn btn-outline-primary float-left"  type="submit"  style="margin-right: 40px"   @click="login" id="login"  >Login</button>
      <button class="btn btn-outline-danger"  style="margin-left: 40px"  @click="register" id="register" >Register</button>
    </div>
  </div>
  </div>
</template>

<script>

import {postRequest} from "@/api/RESTfulAPI";
import App from "@/App";

export default {
  name: "UserPanel",
  components: {
    'parent': App
  },
  data() {
    return {
      username:'',
      password:'',
      registerURL: '/user/register',
      loginURL: '/user/login',
      rootURL:'/user',
      notLoggedIn: true
    }

  },
  methods: {
    register(){
      let _this = this;
      console.log("reg")
      if (this.checkInput() == true) {
        postRequest(_this.registerURL, {
          username: _this.username,
          password: _this.password
        }).then(function (response) {
          alert("注册成功")
          console.log(response)

        }).catch(function (err) {
          alert("注册失败，该用户名已注册")
          console.log(err)
        })
      }
    },
    login(){
      console.log("log")
      if (this.checkInput() == true) {
        let _this = this
        postRequest(_this.loginURL, {
          username: _this.username,
          password: _this.password
        })
            .then(function (response) {
              _this.notLoggedIn = false;
              alert("登录成功")
              _this.$store.state.isLoggedIn = true
              _this.$store.state.currentUser = _this.username
              console.log(response)
              _this.getUserId()
              _this.$router.push("/books")
            }).catch(function (err) {
          alert("登录失败，请检查用户名和密码")
          console.log(err)
        })
      }
    },

    getUserId() {
      if (this.$store.state.currentUser != null) {
        let url = this.rootURL + "/" + this.$store.state.currentUser
        console.log("url:" +url)
        let _this = this
        this.getRequest(url).then(function (res) {
          _this.$store.state.currentUserId = res.data[0].id
          console.log(_this.$store.state.currentUserId)
        })
      }
    },
    checkInput() {
        let regex = "^[ ]+$"
        let re = new RegExp(regex)
      if (this.username == '' || this.password == '' || re.test(username) || re.test(password)) {
        alert("输入的用户名和密码不能为纯空格或空")
        return false
      } else {
        return true
      }
    }

  }
}
</script>

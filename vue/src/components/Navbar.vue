<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <a class="navbar-brand"  style="margin-right: 40px;padding-left: 20px">图书管理系统</a>
      </div>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#/login">Home</a>
          </li>

          <li class="nav-item">
            <a class="nav-link" href="#/books">Books</a>
          </li>

          <li class="nav-item">
            <a class="nav-link" href="#/search">Search</a>
          </li>

          <li class="nav-item">
            <a class="nav-link" href="#/books/add">Add</a>
          </li>




        </ul>
      </div>


<!--      <form class="d-flex" style="padding-right: 10px">-->
<!--        <input v-model="url" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">-->
<!--        <button class="btn btn-outline-success" type="submit">Search</button>-->
<!--      </form>-->
      <div id="loginStatus" style="padding-right: 20px">
      <span v-if="$store.state.isLoggedIn" >

              <button class="btn btn-outline-" @click="logout">用户{{$store.state.currentUser}}: Logout</button>


      </span>

      <span v-if="!$store.state.isLoggedIn" >

              <a>请登录</a>


      </span>
      </div>

    </div><!-- /.container-fluid -->


  </nav>
</template>

<script>


export default {
  name: "NavBar",
  data() {
    return {
      Title: null,
      url: null,
      authLevel:null,
    }
  },

  methods: {
    async logout() {
      this.$store.state.isLoggedIn = false
      this.$store.state.currentUser = false
      alert('Bye')
      await this.$router.push('login')
      this.$forceUpdate()
    },
    queryAuth() {
      if (this.$store.state.currentUser != null && this.$store.state.isLoggedIn) {
        let _this = this
        this.getRequest('/user/' + this.$store.state.currentUser + '/group')
            .then(res => {
              console.log(res.data)
              _this.authLevel = res.data
            }).catch(err => {
          console.log(err)
        })
      } else return false
    }
  },


}
</script>

<style scoped>

</style>
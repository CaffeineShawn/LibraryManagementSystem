<template>
<div class="container-fluid">
  <div >
  <table class=" table row-md-8">
    <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Author</th>
      <th scope="col">Title</th>
      <th scope="col">Quantity</th>
      <th scope="col">Operation</th>
    </tr>
    </thead>

    <tbody>

      <tr v-for="book in bookList" :key="book.id" >
        <th scope="row">{{  book.id  }}</th>
        <td >{{  book.author  }}</td>
        <td >{{  book.title  }}</td>
        <td >{{  book.quantity }}</td>
        <td >
          <div class="col justify-content-center" id="buttons-group-admin" v-if="authLevel == 'admin'">
          <a :href="'#/books/edit?id=' + book.id" class="btn btn-primary" style="margin-right: 20px" type="button" id="Edit"   >Edit</a>
            <button class="btn btn-danger" id="delete"  @click="deleteBook(book.id)">Delete</button>
          </div>

          <div class="col justify-content-center" id="buttons-group-default" v-if="authLevel == 'defaultUser'">
            <button @click="borrowBook(book.id)" class="btn btn-secondary" style="margin-right: 20px" type="button" id="borrow"   >Borrow</button>
            <button class="btn btn-light" id="return"  @click="returnBook(book.id)">Return</button>
          </div>
        </td>
      </tr>
    </tbody>




  </table>
<!--    <a @click="queryAuth">kkp</a>-->
  </div>
  <div class="row-md-4">
    <router-view></router-view>

  </div>
</div>
</template>

<script>




export default {
  name:'AllBooks',
  data() {
    return {
      authLevel: '',
      bookList: {
      },

    }
  },

  methods: {
    getBooks() {
      let _this = this;
      this.getRequest('/books')
          .then(function (response) {
            _this.bookList = response.data;
            console.log(_this.bookList)
          })
    },
    deleteBook(id) {
      if (this.$store.state.isLoggedIn == false) {
        alert('请先登录')
        this.$router.push('/login')
      } else {
        let _this = this;
        console.log('/books' + '/' + id)
        this.deleteRequest('/books' + '/' + id)
            .then(function (response) {
              console.log(response)
              alert('删除该书成功')
              _this.getBooks()
              _this.reload()
            }).catch(err => {
          console.log(err)

        })
      }
    },
    borrowBook(bookId) {
      if (this.$store.state.isLoggedIn == false) {
        alert('请先登录')
        this.$router.push('/login')
      } else {
        let _this = this;
        console.log(bookId)
        console.log(this.$store.state.currentUserId)

          this.postRequest('/books/borrow', {
            bookId: bookId,
            userId: this.$store.state.currentUserId,
            quantity: 1
          })
              .then(response => {
                if (response.data.bookId != null) {
                  alert('借书成功')
                } else {
                  alert('借书失败，您已借过该书或该书数量不足')
                }
                _this.getBooks();
                _this.reload()
              }).catch(res => {
            console.log(res)

          });
      }
    },

    returnBook(bookId) {
      if (this.$store.state.isLoggedIn == false) {
        alert('请先登录')
        this.$router.push('/login')
      } else {
        let _this = this;
        console.log(bookId)
        console.log(this.$store.state.currentUserId)
        this.deleteRequest('/books/borrow/' + this.$store.state.currentUserId +'/' +bookId)
            .then(function (response) {
              console.log(response)
              alert('还书成功')
              _this.getBooks()
              _this.reload()
            }).catch(err => {
          console.log(err)
          alert('您没有该书的借书记录！')
        })
      }
    },

    reload() {
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

  created() {
      this.getBooks()
      this.queryAuth()
  },
  watch: {
    $route: {
      handler: function(val, oldVal){
        let regex = "^/books/edit"
        let re = new RegExp(regex)
        // console.log("regex matched:"+re.test(oldVal.path))
        if (oldVal.path == "/books/add" || re.test(oldVal.path) || oldVal.path == "/books") {
          // console.log("getBooks")
          this.getBooks()
          this.$forceUpdate()
        }
      },
      // 深度观察监听
      deep: true
    }
  },

}
</script>
<style scoped>

</style>


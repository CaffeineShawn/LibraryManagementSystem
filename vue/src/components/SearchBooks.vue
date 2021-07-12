<template>
  <div align="center" class="container-fluid">

    <div class="input-group mb-3 text-left" style="max-width: 400px;padding-top: 100px">

      <input type="text" class="form-control" placeholder="请输入要搜索的标题关键字" id="titleLike" v-model="titleLike" >
      <button class="btn btn-outline-secondary" type="button" id="button-addon2"  @click="search">Search</button>
    </div>
    <h2 class="text-center" style="padding-top: 100px">搜索结果</h2>

    <div >
      <table class=" table row-md-8">
        <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">Author</th>
          <th scope="col">Title</th>
          <th scope="col">Quantity</th>

        </tr>
        </thead>

        <tbody>

        <tr v-for="book in books" :key="book.id" >
          <th scope="row">{{  book.id  }}</th>
          <td >{{  book.author  }}</td>
          <td >{{  book.title  }}</td>
          <td >{{  book.quantity }}</td>

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
import {getRequest} from "@/api/RESTfulAPI";

export default {
  name: "searchForBook",
  data() {
      return {
        titleLike:'',
        books: {

        }
      }

  },
  methods: {
    getBooks() {
      let _this = this;
      this.getRequest('/books')
          .then(function (response) {
            _this.books= response.data;
            console.log(_this.books)
          })
    },
    search() {
      let _this = this
      if (this.checkInput() == true) {
        getRequest('/books/titles/' + _this.titleLike)
            .then(res => {
              _this.books = res.data
              console.log(res.data)
            }).catch(err => {
          console.log(err)
        })
      }
    },
    checkInput() {
      let regex = "^[ ]+$"
      let re = new RegExp(regex)
      if (this.titleLike == '' || re.test(this.titleLike)) {
        alert("输入的标题关键字不能为纯空格或空")
        return false
      } else if (this.titleLike.length > 20){
        alert("输入的标题关键字长度不能大于20")
        return false
      }
      return true
    }
  },
  created() {
    this.getBooks()
  }

}
</script>

<style scoped>

</style>
<template>
  <div style="padding-top: 20px;">
    <h2 style="float: left;padding-left: 20px;">Edit Table</h2>
    <table class=" table" >
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
      <td>


      <th scope="row" style="position: absolute"><div class="text-center">{{id}}</div></th>
      <td>
        <input type="text"   v-model="author" class="form-control"  id="author">
      </td>
      <td>
        <input type="text"  v-model="title" class="form-control" id="title">
      </td>

      <td>
        <input type="text"  v-model="quantity" class="form-control" id="quantity">
      </td>
      <td>
        <button type="button" class="btn" @click="updateBook(id)">Submit</button>
        <button type="button" class="btn btn-outline-danger" @click="resetInput">Reset</button>
      </td>
      </tbody>
    </table>

  </div>
</template>

<script>
// eslint-disable-next-line
import {getRequest} from "@/api/RESTfulAPI";

export default {
  name: "EditBook",
  data() {
      return {
        id:'',
        author:'',
        title:'',
        quantity:''
      }
  },
  methods: {
    getBook(id) {
      let _this = this;
      this.getRequest('/books/' + id)
      .then(function (responese) {
        _this.author = responese.data.author
        _this.title = responese.data.title
        _this.quantity = responese.data.quantity
        _this.id = id
      })
    },
    resetInput() {
      this.author = ''
      this.title = ''
      this.quantity=''

    },
    checkedBook() {
      let regex = "^[ ]+$"
      let regexNum = "^[0-9]{1,5}$"

      let re = new RegExp(regex)
      let reNum = new RegExp(regexNum)

      if (re.test(this.quantity) || re.test(this.title) || re.test(this.author) || this.quantity==''||this.title==''||this.author=='') {
        alert('输入不能为空')
        return false
      } else if (reNum.test(this.quantity) == false || this.quantity == 0)  {
        alert('输入的数量错误: 必须为五位以内纯数字且大于0')
        return false
      }
      console.log("checked success")
      return true;
    },



    updateBook(id) {
      let _this = this
      if (this.checkedBook() == true) {
        this.putRequest('/books/' + id, {
          author: _this.author,
          title: _this.title,
          quantity: _this.quantity,
          id: _this.id
        }).then(res => {
          if (res.status == 200) {
            _this.$router.push("/books")
          }

        }).catch(err => {
          console.log(err)
        });
      }
    }




  },
  created() {
    console.log("修改组件中获取的id: "+this.$route.query.id)
    this.id = this.$route.query.id
    this.getBook(this.id)
  },
  watch: {
    // eslint-disable-next-line
    $route (to,from) {
      console.log(to.query.id)
      this.getBook(to.query.id)
      }
  }


}
</script>

<style scoped>

</style>
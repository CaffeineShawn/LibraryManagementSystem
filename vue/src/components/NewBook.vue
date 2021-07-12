/* eslint-disable */
<template>
  <div style="padding-top: 20px;">
    <h2 style="float: left;padding-left: 20px;">Add Book Table</h2>
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

      <th scope="row"></th>
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
        <button type="button" class="btn" @click="createBook">Submit</button>
        <button type="button" class="btn btn-outline-danger" @click="resetInput">Reset</button>
      </td>
      </tbody>
    </table>

  </div>


</template>

<script>
import {postRequest} from "@/api/RESTfulAPI";


export default {
  name: "NewBook",
  data() {
    return {
      author:'',
      title:'',
      quantity:''
    }
  },
  methods: {

    createBook() {
      let _this = this;
      if (_this.checkedBook() == true) {
        postRequest('/books', {
          author: _this.author,
          title: _this.title,
          quantity: _this.quantity
        }).then(function (response) {
          alert("增加图书成功")
          console.log(response.data.success)
            _this.$router.push("/books")
        })
            .catch(err => (console.log(err)));
      }
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
    resetInput() {
      this.author = ''
      this.title = ''
      this.quantity=''
    }

  }
}
</script>

<style scoped>

</style>
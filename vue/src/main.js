import Vue from 'vue'
import App from "@/App";
import axios from "axios"
import {getRequest} from "./api/RESTfulAPI"
import {putRequest} from "./api/RESTfulAPI"
import {postRequest} from "./api/RESTfulAPI"
import {deleteRequest} from "./api/RESTfulAPI"
import './assets/css/bootstrap.css'
import './assets/js/bootstrap'
import router from "./router"
import store from "./store"


Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.postRequest = postRequest;
Vue.config.productionTip = false
Vue.prototype.$axios = axios
axios.defaults.baseURL = 'http://localhost:8081/'

new Vue({
  el:'#app',
  store,
  router,
  components: {
    App
  },
  render: h => h(App)
}).$mount('#app')

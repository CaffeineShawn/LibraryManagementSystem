import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
    isLoggedIn: false,
    currentUser: '',
    currentUserId:''
}


export default new Vuex.Store({
    state
})
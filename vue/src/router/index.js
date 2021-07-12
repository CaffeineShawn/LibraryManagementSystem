import Vue from 'vue';
import VueRouter from "vue-router";

import Jumbotron from "@/components/Jumbotron";
import AllBooks from "@/components/AllBooks";
import NewBook from "@/components/NewBook";
import EditBook from "@/components/EditBook";
import store from "../store/index"
import searchForBook from "@/components/SearchBooks";
Vue.use(VueRouter)


const routes = [

        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            name:'home',
            component: Jumbotron,

        },
        {
        path: '/search',
        name:'search',
        component: searchForBook,

        },


        {
            path:'/books',
            name:'AllBooks',
            component: AllBooks,

            children: [
                {
                    path:'add',
                    name:'createBook',
                    component: NewBook,
                    meta: {
                        requiredLogin: true
                    },
                },
                {
                    path:'edit',
                    name:'editBook',
                    component: EditBook,
                    meta: {
                        requiredLogin: true
                    },
                },
            ]

        },
        // {
        //     path:'/books/add',
        //     name:'createBook',
        //     component: NewBook
        // },

        {
            path: '/login/success',
            name:'successLogin',
            component: AllBooks
        }

    ]




const router = new VueRouter({
    routes
});

router.beforeEach((to, from, next) => {
    console.log(store.state.isLoggedIn)
    if (to.meta.requiredLogin
        && store.state.isLoggedIn == false
    ) {
        next({path: '/login'});
        alert('请先登录！');
    } else {
        next()
    }
    // else if (to.meta.alreadyLogin
    //     && store.state.isLoggedIn == true
    // ) {
    //
    //     next();
    // }
});

export default router
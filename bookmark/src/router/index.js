import Vue from 'vue'
import Router from 'vue-router'

import Login from '@/components/Login'
import Home from '@/components/Home'
import Shop from '@/components/Shop'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/shop',
      name: 'Shop',
      component: Shop
    }
  ]
})

router.beforeEach((to,from,next) => {
  next()
  // let user = localStorage.getItem('user');
  // if (to.path == '/login') {
  //   if(user) {
  //     next({path:'/'})
  //   }else{
  //     next()
  //   }
  // }else {
  //   if (user) {
  //     next({path:'/login'})
  //   }else{
  //     next()
  //   }
  // }

})

export default router
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('../views/home.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/register.vue')
  },
  {
    path: '/login/shop',
    name: 'login_shop',
    component: () => import('../views/login_shop.vue')
  },
  {
    path: '/register/shop',
    name: 'register_shop',
    component: () => import('../views/register_shop.vue')
  },
  {
    path: '/space/shop',
    name: 'space_shop',
    component: () => import('../views/space_shop.vue'),
    redirect: '/space/shop/home', //重定向至主页
    children: [
      { path: 'home', name: 'home', component: () => import('../views/space_shop/home.vue') },
      { path: 'information', name: 'information', component: () => import('../views/space_shop/information.vue') },
      { path: 'goods', name: 'goods', component: () => import('../views/space_shop/goods.vue')},
      { path: 'report/browse', name: 'browse', component: () => import('../views/space_shop/browse.vue')},
      { path: 'report/purchase', name: 'purchase', component: () => import('../views/space_shop/purchase.vue')},
      { path: 'password', name: 'password', component: () => import('../views/space_shop/password.vue')},
      { path: 'goods/outsale', name: 'outsale', component: () => import('../views/space_shop/outsale.vue')}
    ]
  },
  {
    path: '/space/user',
    name: 'space_shop',
    component: () => import('../views/space_user.vue'),
    redirect: '/space/user/home', //重定向至主页
    children: [
      { path: 'home', name: 'home', component: () => import('../views/space_user/home.vue') },
      { path: 'information', name: 'information', component: () => import('../views/space_user/information.vue') },
      { path: 'report/browse', name: 'browse', component: () => import('../views/space_user/browse.vue')},
      { path: 'report/purchase', name: 'purchase', component: () => import('../views/space_user/purchase.vue')},
      { path: 'password', name: 'password', component: () => import('../views/space_user/password.vue')},
      { path: 'order', name: 'order', component: () => import('../views/space_user/order.vue')}
    ]
  },
  {
    path: '/details/:gid',
    component: () => import('../views/details.vue')
  },
  {
    path: '/cart',
    component: () => import('../views/cart.vue')
  },
  {
    path: '/shop/:sid',
    component: () => import('../views/shop.vue')
  },
  {
    path: '/search',
    component: () => import('../views/search.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

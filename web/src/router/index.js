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
    path: '/login/saler',
    name: 'login_saler',
    component: () => import('../views/login_saler.vue')
  },
  {
    path: '/space/saler',
    name: 'space_saler',
    component: () => import('../views/space_saler.vue'),
    redirect: '/space/saler/home', //重定向至主页
    children: [
      { path: 'home', name: 'home', component: () => import('../views/space_saler/home.vue') },
      { path: 'goods', name: 'goods', component: () => import('../views/space_saler/goods.vue')},
      { path: 'goods/outsale', name: 'outsale', component: () => import('../views/space_saler/outsale.vue')},
      { path: 'report/browse', name: 'browse', component: () => import('../views/space_saler/browse.vue')},
      { path: 'report/purchase', name: 'purchase', component: () => import('../views/space_saler/purchase.vue')}
    ]
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
      { path: 'report/category', name: 'category', component: () => import('../views/space_shop/category.vue')},
      { path: 'password', name: 'password', component: () => import('../views/space_shop/password.vue')},
      { path: 'goods/outsale', name: 'outsale', component: () => import('../views/space_shop/outsale.vue')},
      { path: 'saler/account', name: 'account', component: () => import('../views/space_shop/account.vue')},
      { path: 'saler/performance', name: 'performance', component: () => import('../views/space_shop/performance.vue')}
    ]
  },
  {
    path: '/space/user',
    name: 'space_user',
    component: () => import('../views/space_user.vue'),
    redirect: '/space/user/home', //重定向至主页
    children: [
      { path: 'home', name: 'home', component: () => import('../views/space_user/home.vue') },
      { path: 'information', name: 'information', component: () => import('../views/space_user/information.vue') },
      { path: 'report/browse', name: 'browse', component: () => import('../views/space_user/browse.vue')},
      { path: 'report/purchase', name: 'purchase', component: () => import('../views/space_user/purchase.vue')},
      { path: 'password', name: 'password', component: () => import('../views/space_user/password.vue')},
      { path: 'order', name: 'order', component: () => import('../views/space_user/order.vue')},
      { path: 'setting', name: 'setting', component: () => import('../views/space_user/setting.vue')}
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

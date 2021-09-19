import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Cadastro_Fornecedor from '../views/Cadastro_Fornecedor'
import Cadastro_Usuario from '../views/Cadastro_Usuario'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/Login',
    name: 'Login',
    component: Login
  },
  {
    path: '/Cadastro_fornecedor',
    name: 'Cadastro_Fornecedor',
    component: Cadastro_Fornecedor
  },
  {
    path: '/Cadastro_Usuario',
    name: 'Cadastro_Usuario',
    component: Cadastro_Usuario
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

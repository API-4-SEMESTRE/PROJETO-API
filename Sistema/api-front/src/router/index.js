import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Cadastro_Fornecedor from '../views/Cadastro_Fornecedor'
import Cadastro_Usuario from '../views/Cadastro_Usuario'

Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '*',
      redirect: '/login'
    },
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login,
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      meta: {requiresAuth: true}
    },
    {
      path: '/Cadastro_fornecedor',
      name: 'Cadastro_Fornecedor',
      component: Cadastro_Fornecedor,
      meta: {requiresAuth: true}
    },
    {
      path: '/Cadastro_Usuario',
      name: 'Cadastro_Usuario',
      component: Cadastro_Usuario,
      meta: {requiresAuth: true}
    }
  ]
})

router.beforeEach((to, from, next) =>{
  var currentUser = localStorage.getItem("returnLogin");
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);

  if (requiresAuth && !currentUser) next('login');
  else if (!requiresAuth && currentUser) next('home')
  else next();

})
export default router;

import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Cadastro_Fornecedor from '../views/Cadastro_Fornecedor'
import Cadastro_Usuario from '../views/Cadastro_Usuario'
import Visualizacao_Usuario from '../views/Visualizacao_Usuario'
import Criar_Conta from '../views/Criar_Conta'

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
    },
    {
      path: '/Visualizacao_Usuario',
      name: 'Visualizacao_Usuario',
      component: Visualizacao_Usuario,
      meta: {requiresAuth: true}
    },
    {
      path: '/Criar_Conta',
      name: 'Criar_Conta',
      component: Criar_Conta,
    }
  ]
})

router.beforeEach((to, from, next) =>{
  let currentUser = localStorage.getItem("returnLogin");
  let check = currentUser
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);

  if (requiresAuth && (!check == undefined)) next('login');
  else if (!requiresAuth && check) next('home')
  else next();

})
export default router;

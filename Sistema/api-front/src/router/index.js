import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Cadastro_Fornecedor from '../views/Cadastro_Fornecedor'
import Cadastro_Usuario from '../views/Cadastro_Usuario'
import Criar_Conta from '../views/Criar_Conta'
import Agendar_Evento from '../views/Agendar_Evento'
import Editar_Fornecedor from '../views/Editar_Fornecedor'

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
      meta: { requiresAuth: true }
    },
    {
      path: '/Cadastro_fornecedor',
      name: 'Cadastro_Fornecedor',
      component: Cadastro_Fornecedor,
      meta: { requiresAuth: true }
    },
    {
      path: '/Cadastro_Usuario',
      name: 'Cadastro_Usuario',
      component: Cadastro_Usuario,
      meta: { requiresAuth: true }
    },
    {
      path: '/Criar_Conta',
      name: 'Criar_Conta',
      component: Criar_Conta,
    },
    {
      path: '/Agendar_Evento',
      name: 'Agendar_Evento',
      component: Agendar_Evento,
      meta: { requiresAuth: true }
    },
    {
      path: '/Editar_Fornecedor',
      name: 'Editar_Fornecedor',
      component: Editar_Fornecedor,
      meta: { requiresAuth: true }
    },
  ]
})

router.beforeEach((to, from, next) => {
  var currentUser = localStorage.getItem("returnLogin");
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);

  if (requiresAuth && !currentUser) next('login');
  else if (!requiresAuth && currentUser) next('home')
  else next();

})
export default router;

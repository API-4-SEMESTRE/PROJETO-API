<template>
  <div id="app">
    <v-app>
      <v-main>
        <!--
        <v-app-bar color="#c74634" dark v-if="this.$route.path !== '/login'">
          <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
          <v-toolbar-title class="white--text">AgendHouse</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn icon v-on:click="cleanStorage()">
            <v-icon>mdi-exit-run</v-icon>
          </v-btn>
        </v-app-bar>
        -->

        <div v-if="this.$route.path !== '/login'">
          <div v-if="this.$route.path !== '/Login'">
            <div v-if="this.$route.path !== '/Criar_Conta'">
              <v-btn
                style="margin-top: 20px; margin-left: 20px"
                icon
                @click="drawer = true"
              >
                <v-icon large color="white">mdi-menu</v-icon>
              </v-btn>
            </div>
          </div>
        </div>

        <v-navigation-drawer
          v-model="drawer"
          absolute
          temporary
          width="250px"
          class="grey darken-3"
        >
          <v-list nav dense>
            <v-list-item-group active-class="red darken-3">
              <v-img src="./images/Logo_AgendHouse.png"></v-img>
              <v-divider></v-divider>
              <v-list-item
                v-for="(item, index) in menus"
                :key="index"
                :to="item.url"
              >
                <v-list-item-icon>
                  <v-icon color="white">{{ item.icon }}</v-icon>
                </v-list-item-icon>

                <v-list-item-title class="white--text">{{
                  item.conteudo
                }}</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <v-list-item-icon>
                  <v-icon color="white" v-on:click="cleanStorage()"
                    >mdi-exit-run</v-icon
                  >
                </v-list-item-icon>

                <v-list-item-title
                  class="white--text"
                  v-on:click="cleanStorage()"
                >
                  Sair
                </v-list-item-title>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-navigation-drawer>
        <router-view></router-view>
      </v-main>
    </v-app>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
  data: () => ({
    //logado: true,
    drawer: false,
    menus: [
      { conteudo: "Home", url: "/", icon: "mdi-home-variant" },
      {
        conteudo: "Usuário",
        url: "/Cadastro_usuario",
        icon: "mdi-account-plus",
      },
      {
        conteudo: "Cadastrar Fornecedor",
        url: "/Cadastro_fornecedor",
        icon: "mdi-layers-plus",
      },
      {
        conteudo: "Editar Fornecedor",
        url: "/Editar_Fornecedor",
        icon: "mdi-layers-edit",
      },
      {
        conteudo: "Agendar Evento",
        url: "/Agendar_Evento",
        icon: "mdi-calendar-multiselect",
      },
      {
        conteudo: "Visitante",
        url: "/Visitante",
        icon: "mdi-card-account-details",
      },
      {
        conteudo: "Relatorios",
        url: "/Relatorios",
        icon: "mdi-chart-areaspline",
      },
    ],
  }),
  methods: {
    cleanStorage() {
      localStorage.clear();
      window.location.reload(true);
    },
  },
};
</script>

<style>
#app {
  background-color: #181820;
}
/* Largura da barra de rolagem */
::-webkit-scrollbar {
  width: 0px;
}
</style>
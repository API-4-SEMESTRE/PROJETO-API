<template>
  <div id="app">
    <v-app>
      <v-main>
        <v-app-bar color="#c74634" dark v-if="this.$route.path !== '/login'">
          <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
          <v-toolbar-title class="white--text">AgendHouse</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn icon v-on:click="cleanStorage()">
            <v-icon>mdi-exit-run</v-icon>
          </v-btn>
        </v-app-bar>

        <v-navigation-drawer v-model="drawer" absolute temporary width="250px">
          <v-list nav dense>
            <v-list-item>
              <v-list-item-title>Menu</v-list-item-title>
            </v-list-item>
            <v-list-item-group active-class="blue--text text--darken-1">
              <v-list-item
                v-for="(item, index) in menus"
                :key="index"
                :to="item.url"
              >
                <v-list-item-icon>
                  <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-icon>

                <v-list-item-title>{{ item.conteudo }}</v-list-item-title>
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
        conteudo: "Cadastro de Fornecedor",
        url: "/Cadastro_fornecedor",
        icon: "mdi-package-variant",
      },
      {
        conteudo: "Cadastro de Usuário",
        url: "/Cadastro_usuario",
        icon: "mdi-account-plus",
      },
      {
        conteudo: "Visualização dos Usuários",
        url: "/Visualizacao_Usuario",
        icon: "mdi-account-group",
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
/* Retirar a barra de rolagem das telas */
::-webkit-scrollbar {
    width: 0px;
}
</style>
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

        <v-btn
          style="margin-top: 20px; margin-left: 20px"
          icon
          @click="drawer = true"
          v-if="this.$route.path !== '/login'"
        >
          <v-icon large color="white">mdi-menu</v-icon>
        </v-btn>

        <v-navigation-drawer
          v-model="drawer"
          absolute
          temporary
          width="250px"
          class="grey darken-3"
        >
          <v-list nav dense>
            <v-list-item-group active-class="red darken-3">
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
        conteudo: "Cadastro de Fornecedor",
        url: "/Cadastro_fornecedor",
        icon: "mdi-package-variant",
      },
      {
        conteudo: "Cadastro de Usuário",
        url: "/Cadastro_usuario",
        icon: "mdi-account-plus",
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
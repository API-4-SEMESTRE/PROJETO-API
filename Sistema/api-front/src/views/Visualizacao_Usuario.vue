<template>
  <div>
    <v-app>
      <v-main>
        <h1 style="text-align: center; margin-top: 20px">
          Visualização de Usuário
        </h1>
      </v-main>
      <v-card
        class="pa-2"
        tile
        outlined
        color="#DCDCDC"
        style="margin-top: 30px"
      >
        <v-card-text>
          <v-card>
            <v-card-title>
              Lista de Usuários
              <v-spacer></v-spacer>
              <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Pesquisar"
                single-line
                hide-details
              ></v-text-field>
            </v-card-title>
            <v-data-table
              :headers="headers"
              :items="lista_de_usuarios"
              :search="search"
            ></v-data-table>
          </v-card>
        </v-card-text>
      </v-card>
    </v-app>
  </div>
</template>


<script>
import Usuario from "../services/usuario";
import Swal from "sweetalert2";

export default {
  data: () => ({
    // Array com a lista de usuarios
    lista_de_usuarios: [],

    // Variavel que vai ser usada pra pesquisa da tabela
    search: "",

    // Array que contem as colunas da tabela
    headers: [
      {
        text: "CÓDIGO",
        align: "start",
        value: "cod",
      },
      { text: "NOME", value: "nome" },
      { text: "TIPO", value: "tipo" },
      { text: "EMAIL", value: "email" },
      { text: "DATA DE CRIAÇÃO", value: "date_create" },
      { text: "ATIVO", value: "active" },
      //{ text: "SENHA", value: "senha" },
    ],
  }),

  mounted() {
    this.exibir_usuario();
  },

  methods: {
    // Método pra exibir os usuarios
    exibir_usuario() {
      Usuario.listar_usuarios()
        .then((resposta_lista_usuarios) => {
          this.lista_de_usuarios = resposta_lista_usuarios.data;
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao carregar a tabela de usuários! - Erro: " +
              e.response.data.error,
            "error"
          );
        });
    },
  },
};
</script>
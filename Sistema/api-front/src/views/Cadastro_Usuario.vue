<template>
  <div>
    <v-app>
      <v-main>
        <h1 style="text-align: center; margin-top: 20px">
          Cadastro de Usuário
        </h1>
        <v-form
          style="padding-top: 30px"
          ref="form"
          v-model="valid"
          lazy-validation
          @submit.prevent="cadastrar_usuario"
        >
          <v-container class="ma-70" style="width: 40%; border: solid 1px">
            <v-row justify="center" align="stretch">
              <v-col cols="24">
                <span style="padding-top: 8px"> Nome Completo </span>
                <v-text-field
                  v-model="usuario.nome"
                  :rules="regra_nome"
                  outlined
                  required
                  dense
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row justify="center" align="stretch">
              <v-col cols="24">
                <span style="padding-top: 8px"> E-mail </span>
                <v-text-field
                  v-model="usuario.email"
                  :rules="regra_email"
                  outlined
                  required
                  dense
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row justify="center" align="stretch">
              <v-col cols="24">
                <span style="padding-top: 8px"> Senha </span>
                <v-text-field
                  v-model="usuario.senha"
                  :rules="regra_senha"
                  outlined
                  required
                  dense
                ></v-text-field>
              </v-col>
            </v-row>

            <v-btn
              class="mr-4"
              type="submit"
              :disabled="!valid"
              @click="validate"
              id="btn_cadastrar_usuario"
            >
              Cadastrar
            </v-btn>
          </v-container>
        </v-form>
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
    // Validando se os campos estão preenchidos e se são validos
    valid: true,
    regra_nome: [(v) => !!v || "O nome é obrigatório"],
    regra_email: [
      (v) => !!v || "O e-mail é obrigatório",
      (v) => /.+@.+\..+/.test(v) || "E-mail inválido",
    ],
    regra_senha: [(v) => !!v || "A senha é obrigatória"],

    // Array com a lista de usuarios
    lista_de_usuarios: [],

    // Criando o objeto que vai ser feito o POST
    usuario: {
      cod: "12",
      nome: "",
      tipo: "FUNCIONARIO",
      email: "",
      active: "True",
      senha: "",
    },

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
      { text: "SENHA", value: "senha" },
    ],
  }),

  mounted() {
    this.exibir_usuario();
  },

  methods: {
    // Método de cadastro de usuario
    cadastrar_usuario() {
      Usuario.salvar_usuario(this.usuario)
        .then((resposta_cadastro_usuario) => {
          this.usuario = {};
          Swal.fire(
            "Sucesso",
            "Usuário " +
              resposta_cadastro_usuario.data.nome +
              " cadastrado com sucesso!!!",
            "success"
          );
          this.exibir_usuario();
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao cadastrar o usuário! - Erro: " + e.response.data.error,
            "error"
          );
        });
    },
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
    // Método que valida se os campos estão preenchidos, se não estiverem ele bloqueia o botão CADASTRAR
    validate() {
      this.$refs.form.validate();
    },
  },
};
</script>

<style>
#btn_cadastrar_usuario {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: auto;
  background-color: #c74634;
  color: white;
  border: solid 1px black;
}
</style>
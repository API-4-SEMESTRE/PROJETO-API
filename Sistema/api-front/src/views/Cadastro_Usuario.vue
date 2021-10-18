<template>
  <v-app id="cadastro-usuario">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md12>
            <h1 style="text-align: center; color: white; margin-top: 15px">
              Usuários
            </h1>
            <v-layout align-center justify-center>
              <div>
                <v-card
                  class="pa-2"
                  tile
                  outlined
                  color="#272733"
                  style="margin-top: 20px"
                >
                  <v-card-text>
                    <template>
                      <v-data-table
                        :headers="headers"
                        :items="lista_de_usuarios"
                        :search="search"
                        sort-by="calories"
                        class="elevation-1"
                      >
                        <template v-slot:top>
                          <v-toolbar flat>
                            <v-toolbar-title>Lista de Usuários</v-toolbar-title>
                            <v-spacer></v-spacer>
                            <v-text-field
                              v-model="search"
                              append-icon="mdi-magnify"
                              label="Search"
                              single-line
                              hide-details
                            ></v-text-field>
                            <v-spacer></v-spacer>
                            <v-dialog v-model="dialog" max-width="500px">
                              <template v-slot:activator="{ on, attrs }">
                                <v-btn
                                  color="#C84634"
                                  class="white--text"
                                  dark
                                  v-bind="attrs"
                                  v-on="on"
                                >
                                  Novo Usuário
                                </v-btn>
                              </template>
                              <v-card style="background-color: #272733">
                                <v-card-title>
                                  <span class="text-h5 white--text">{{
                                    formTitle
                                  }}</span>
                                </v-card-title>
                                <v-card-text>
                                  <v-container>
                                    <v-form
                                      ref="form"
                                      v-model="valid"
                                      lazy-validation
                                      @submit.prevent="cadastrar_usuario"
                                    >
                                      <v-container>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Nome</span
                                            >
                                            <v-text-field
                                              label="Nome"
                                              v-model="usuario.nome"
                                              :rules="regra_nome"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                            ></v-text-field>
                                          </v-col>
                                        </v-row>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >E-mail</span
                                            >
                                            <v-text-field
                                              label="E-mail"
                                              v-model="usuario.email"
                                              :rules="regra_email"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                            ></v-text-field>
                                          </v-col>
                                        </v-row>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Senha</span
                                            >
                                            <v-text-field
                                              label="Senha"
                                              v-model="usuario.senha"
                                              :rules="regra_senha"
                                              background-color="#A9A9A9"
                                              single-line
                                              solo
                                              required
                                              dense
                                              password
                                              :append-icon="
                                                show1
                                                  ? 'mdi-eye'
                                                  : 'mdi-eye-off'
                                              "
                                              :type="
                                                show1 ? 'text' : 'password'
                                              "
                                              @click:append="show1 = !show1"
                                            ></v-text-field>
                                          </v-col>
                                        </v-row>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Tipo de Usuário</span
                                            >
                                            <v-select
                                              :items="tipo_usuario"
                                              label="Tipo de Usuário"
                                              v-model="usuario.tipo"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                              :rules="[
                                                (v) =>
                                                  !!v ||
                                                  'O tipo do usuário é obrigatório',
                                              ]"
                                            ></v-select>
                                          </v-col>
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Status do Usuário</span
                                            >
                                            <v-select
                                              :items="status_usuario"
                                              label="Status Usuário"
                                              v-model="usuario.active"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                              ::rules="[
                                                (v) =>
                                                  !!v.toString() ||
                                                  'O status do usuário é obrigatório',
                                              ]"
                                            ></v-select>
                                          </v-col>
                                        </v-row>
                                        <v-row>
                                          <v-col></v-col>
                                          <v-col>
                                            <v-btn
                                              text
                                              color="white"
                                              @click="close"
                                            >
                                              Cancelar
                                            </v-btn>
                                          </v-col>
                                          <v-col>
                                            <v-btn
                                              color="#C84634"
                                              class="white--text mr-4"
                                              type="submit"
                                              :disabled="!valid"
                                              @click="validate"
                                            >
                                              Salvar
                                            </v-btn>
                                          </v-col>
                                        </v-row>
                                      </v-container>
                                    </v-form>
                                  </v-container>
                                </v-card-text>
                              </v-card>
                            </v-dialog>
                            <v-dialog v-model="dialogDelete" max-width="540px">
                              <v-card color="#272733">
                                <v-card-title class="text-h5 white--text"
                                  >Tem certeza de que deseja excluir este
                                  item?</v-card-title
                                >
                                <v-card-actions>
                                  <v-spacer></v-spacer>
                                  <v-btn
                                    text
                                    color="white"
                                    @click="closeDelete"
                                  >
                                    Cancelar
                                  </v-btn>
                                  <v-btn
                                    color="#C84634"
                                    class="white--text mr-4"
                                    @click="deletar_usuario(usuario)"
                                    >Sim</v-btn
                                  >
                                  <v-spacer></v-spacer>
                                </v-card-actions>
                              </v-card>
                            </v-dialog>
                          </v-toolbar>
                        </template>
                        <template v-slot:item.actions="{ item }">
                          <v-icon class="mr-2" @click="editar_usuario(item)">
                            mdi-pencil
                          </v-icon>
                          <v-icon @click="deleteItem(item)">
                            mdi-delete
                          </v-icon>
                        </template>
                      </v-data-table>
                    </template>
                  </v-card-text>
                </v-card>
              </div>
            </v-layout>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>


<script>
import Usuario from "../services/usuario";
import Swal from "sweetalert2";

export default {
  data: () => ({
    // Validando se os campos do formulario estão preenchidos e se são validos
    valid: true,
    regra_nome: [(v) => !!v || "O nome é obrigatório"],
    regra_email: [
      (v) => !!v || "O e-mail é obrigatório",
      //(v) => /.+@.+\..+/.test(v) || "E-mail inválido",
      //(v) => /^[a-z0-9.]+@oracle.com$/.test(v) || "E-mail inválido",
    ],
    regra_senha: [(v) => !!v || "A senha é obrigatória"],

    // Criando a variavel pro icone de mostrar a senha
    show1: false,

    // Variavel que vai ser usada pra pesquisa da tabela
    search: "",

    // Criando os arrays que vão armazenar os conteudos dos selects de Status do Usuario e Tipo de Usuario
    status_usuario: [true, false],
    tipo_usuario: ["ADMIN", "COLABORADOR"],

    // Array aonde vai ser armazenado a lista de usuarios
    lista_de_usuarios: [],

    // Criando o objeto que vai ser feito o POST
    usuario: {
      cod: "",
      nome: "",
      tipo: "",
      email: "",
      active: "",
      senha: "",
    },

    // Variaveis referentes aos modais que abrem na tela, se for false ele não aparece na tela, se for true ele aparece na tela
    dialog: false,
    dialogDelete: false,

    // Array que vai armazenar as colunas da tabela
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
      { text: "Actions", value: "actions", sortable: false },
    ],

    // Vairavel que é usada pra identificar se o modal é de cadastro de usuario ou de edição do usuario
    editedIndex: -1,
  }),

  computed: {
    // Retornando "Novo Usuário" ou "Editar Usuário" dependendo de qual modal estiver
    // "Novo Usuário" = o usuario clicou no botão pra cadastrar um novo usuario
    // "Editar Usuário" = o usuario clicou no botão pra editar um usuario
    formTitle() {
      return this.editedIndex === -1 ? "Novo Usuário" : "Editar Usuário";
    },
  },

  watch: {
    // Fechando os modais
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
  },

  mounted() {
    // Chamando o método exibir_usuario()
    this.exibir_usuario();
  },

  methods: {
    // Método de cadastro de usuario
    cadastrar_usuario() {
      // Se o usuario não tiver um "cod" significa que esse usuario não existe então ele vai pra resquest de cadastro
      if (!this.usuario.cod) {
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
        this.close();
      } else {
        // Método de atualizar usuario
        // Se o usuario já tiver um "cod" ele já existe então ele vai pra request de atualizar
        Usuario.atualizar_usuario(this.usuario)
          .then((resposta_atualizar_usuario) => {
            this.usuario = {};
            Swal.fire(
              "Sucesso",
              "Usuário " +
                resposta_atualizar_usuario.data.nome +
                " atualizado com sucesso!!!",
              "success"
            );
            this.exibir_usuario();
          })
          .catch((e) => {
            Swal.fire(
              "Oops...",
              "Erro ao atualizar o usuário! - Erro: " + e.response.data.error,
              "error"
            );
          });
        this.close();
      }
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

    // Método que vai recuparar os dados da tabela e armazenar no objeto usuario
    editar_usuario(usuario) {
      this.editedIndex = this.lista_de_usuarios.indexOf(usuario);
      this.usuario = Object.assign({}, usuario);
      this.dialog = true;
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto usuario
    deleteItem(usuario) {
      this.editedIndex = this.lista_de_usuarios.indexOf(usuario);
      this.usuario = Object.assign({}, usuario);
      this.dialogDelete = true;
    },

    // Método pra excluir os usuarios
    deletar_usuario(usuario) {
      Usuario.excluir_usuario(usuario)
        .then((resposta_excluir_usuario) => {
          Swal.fire("Sucesso", "Usuário excluido com sucesso!!!", "success");
          resposta_excluir_usuario;
          this.exibir_usuario();
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao excluir o usuário! - Erro: " + e.response.data.error,
            "error"
          );
        });
      this.closeDelete();
    },

    // Método que vai fechar o modal "dialog"
    close() {
      this.dialog = false;
      this.usuario = {};
    },

    // Método que vai fechar o modal "dialogDelete"
    closeDelete() {
      this.dialogDelete = false;
      this.usuario = {};
    },
  },
};
</script>

<style>
#cadastro-usuario {
  background-color: #181820;
}
</style>
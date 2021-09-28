<template>
  <div>
    <h1 style="text-align: center; margin-top: 20px">Cadastro de Usuário</h1>
    <!--DATA TABLE-->
    <v-card class="pa-2" tile outlined color="#DCDCDC" style="margin-top: 20px">
      <v-card-text>
        <template>
          <v-data-table
            :headers="headers"
            :items="lista_de_usuarios"
            sort-by="calories"
            class="elevation-1"
          >
            <template v-slot:top>
              <v-toolbar flat>
                <v-toolbar-title>Lista de Usuários</v-toolbar-title>
                <v-divider class="mx-4" inset vertical></v-divider>
                <v-spacer></v-spacer>
                <v-dialog v-model="dialog" max-width="500px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      color="primary"
                      dark
                      class="mb-2"
                      v-bind="attrs"
                      v-on="on"
                    >
                      Novo Usuário
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="text-h5">{{ formTitle }}</span>
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
                                <v-text-field
                                  label="Nome Completo"
                                  v-model="usuario.nome"
                                  :rules="regra_nome"
                                  outlined
                                  required
                                  dense
                                ></v-text-field>
                              </v-col>
                            </v-row>
                            <v-row justify="center">
                              <v-col cols="24">
                                <v-text-field
                                  label="E-mail"
                                  v-model="usuario.email"
                                  :rules="regra_email"
                                  outlined
                                  required
                                  dense
                                ></v-text-field>
                              </v-col>
                            </v-row>
                            <v-row justify="center">
                              <v-col cols="24">
                                <v-text-field
                                  label="Senha"
                                  v-model="usuario.senha"
                                  :rules="regra_senha"
                                  outlined
                                  required
                                  dense
                                  password
                                  type="password"
                                ></v-text-field>
                              </v-col>
                            </v-row>
                            <v-row>
                              <v-col></v-col>
                              <v-col>
                                <v-btn color="error" @click="close">
                                  Cancelar
                                </v-btn>
                              </v-col>
                              <v-col>
                                <v-btn
                                  class="mr-4"
                                  type="submit"
                                  :disabled="!valid"
                                  @click="validate"
                                  id="btn_cadastrar_usuario"
                                  color="primary"
                                >
                                  Cadastrar
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
                  <v-card>
                    <v-card-title class="text-h5"
                      >Tem certeza de que deseja excluir este
                      item?</v-card-title
                    >
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="blue darken-1" text @click="closeDelete"
                        >Cancelar</v-btn
                      >
                      <v-btn
                        color="blue darken-1"
                        text
                        @click="deleteItemConfirm"
                        >Sim</v-btn
                      >
                      <v-spacer></v-spacer>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-toolbar>
            </template>
            <template v-slot:item.actions="{ item }">
              <v-icon small class="mr-2" @click="editItem(item)">
                mdi-pencil
              </v-icon>
              <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
            </template>
            <template v-slot:no-data>
              <v-btn color="primary" @click="initialize"> Reset </v-btn>
            </template>
          </v-data-table>
        </template>
      </v-card-text>
    </v-card>
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
      //(v) => /.+@.+\..+/.test(v) || "E-mail inválido",
      (v) => /^[a-z0-9.]+@oracle.com$/.test(v) || "E-mail inválido",
    ],
    regra_senha: [(v) => !!v || "A senha é obrigatória"],

    // Array com a lista de usuarios
    lista_de_usuarios: [],

    // Criando o objeto que vai ser feito o POST
    usuario: {
      nome: "",
      tipo: "COLABORADOR",
      email: "",
      active: "True",
      senha: "",
    },

    // DATA TABLE
    dialog: false,
    dialogDelete: false,
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
    desserts: [],
    editedIndex: -1,
    editedItem: {
      cod: 0,
      nome: "",
      tipo: "",
      email: "",
      date_create: "",
      active: "",
    },
    defaultItem: {
      cod: 0,
      nome: "",
      tipo: "",
      email: "",
      date_create: "",
      active: "",
    },
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Novo Usuário" : "Editar Usuário";
    },
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
  },

  created() {
    this.initialize();
  },

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
      this.close();
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

    //DATA TABLE

    editarUsuario(usuario) {
      this.usuario = usuario;
    },

    editItem(usuario) {
      this.usuario = this.lista_de_usuarios.indexOf(usuario);
      this.usuario = Object.assign({}, usuario);
      this.dialog = true;
    },

    deleteItem(item) {
      this.editedIndex = this.lista_de_usuarios.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogDelete = true;
    },

    deleteItemConfirm() {
      this.lista_de_usuarios.splice(this.editedIndex, 1);
      this.closeDelete();
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    closeDelete() {
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(
          this.lista_de_usuarios[this.editedIndex],
          this.editedItem
        );
      } else {
        this.lista_de_usuarios.push(this.editedItem);
      }
      this.close();
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
  border: solid 1px black;
}
</style>
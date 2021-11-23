<template>
  <v-app id="visitante">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md12>
            <h1 style="text-align: center; color: white; margin-top: 15px">
              Visitantes
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
                        :items="lista_de_visitantes"
                        :search="search"
                        sort-by="calories"
                        class="elevation-1"
                      >
                        <template v-slot:top>
                          <v-toolbar flat>
                            <v-toolbar-title
                              >Lista de Visitantes</v-toolbar-title
                            >
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
                                  Novo Visitante
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
                                      @submit.prevent="cadastrar_visitante"
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
                                              v-model="visitante.visnome"
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
                                              v-model="visitante.visemail"
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
                                              >CPF</span
                                            >
                                            <v-text-field
                                              label="CPF"
                                              v-mask="'###.###.###-##'"
                                              v-model="visitante.viscpf"
                                              :rules="regra_cpf"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                            ></v-text-field>
                                          </v-col>
                                        </v-row>
                                        <v-row>
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Estágio da Vacina</span
                                            >
                                            <v-select
                                              :items="items_vacina"
                                              label="Estágio da Vacina"
                                              v-model="visitante.visvacina"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                              ::rules="[
                                                (v) =>
                                                  !!v.toString() ||
                                                  'O estágio da vacina é obrigatório',
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
                                    @click="deletar_visitante(visitante)"
                                    >Sim</v-btn
                                  >
                                  <v-spacer></v-spacer>
                                </v-card-actions>
                              </v-card>
                            </v-dialog>
                          </v-toolbar>
                        </template>
                        <template v-slot:item.actions="{ item }">
                          <v-icon class="mr-2" @click="editar_visitante(item)">
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
import Visitante from "../services/visitante";
import Swal from "sweetalert2";

export default {
  data: () => ({
    // Validando se os campos do formulario estão preenchidos e se são validos
    valid: true,
    regra_nome: [(v) => !!v || "O nome é obrigatório"],
    regra_email: [
      (v) => !!v || "O e-mail é obrigatório",
      (v) => /.+@.+\..+/.test(v) || "E-mail inválido",
      //(v) => /^[a-z0-9.]+@oracle.com$/.test(v) || "E-mail inválido",
    ],
    regra_cpf: [(v) => !!v || "O CPF é obrigatório"],

    // Variavel que vai ser usada pra pesquisa da tabela
    search: "",

    // Array aonde vai ser armazenado a lista de usuarios
    lista_de_visitantes: [],

    items_vacina: ["Nenhuma Dose", "1 Dose", "100% Vacinado"],

    // Criando o objeto que vai ser feito o POST
    visitante: {
      viscod: "",
      visnome: "",
      visemail: "",
      viscpf: "",
      visvacina: ""
    },

    // Variaveis referentes aos modais que abrem na tela, se for false ele não aparece na tela, se for true ele aparece na tela
    dialog: false,
    dialogDelete: false,

    // Array que vai armazenar as colunas da tabela
    headers: [
      {
        text: "CÓDIGO",
        align: "start",
        value: "viscod",
      },
      { text: "NOME", value: "visnome" },
      { text: "EMAIL", value: "visemail" },
      { text: "CPF", value: "viscpf" },
      { text: "VACINA", value: "visvacina" },
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
      return this.editedIndex === -1 ? "Novo Visitante" : "Editar Visitante";
    },
    messageType() {
      return {
        color: this.valido ? "green" : "red",
      };
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
    // Chamando o método exibir_visitante()
    this.exibir_visitante();
  },

  methods: {
    // Método de cadastro de visitante
    cadastrar_visitante() {
      // Se o visitante não tiver um "viscod" significa que esse visitante não existe então ele vai pra resquest de cadastro
      if (!this.visitante.viscod) {
        Visitante.salvar_visitante(this.visitante)
          .then((resposta_cadastro_visitante) => {
            this.visitante = {};
            Swal.fire(
              "Sucesso",
              "Visitante " +
                resposta_cadastro_visitante.data.visnome +
                " cadastrado com sucesso!!!",
              "success"
            );
            this.exibir_visitante();
          })
          .catch((e) => {
            Swal.fire(
              "Oops...",
              "Erro ao cadastrar o visitante! - Erro: " + e.response.data.error,
              "error"
            );
          });
        this.close();
      } else {
        // Método de atualizar visitante
        // Se o visitante já tiver um "viscod" ele já existe então ele vai pra request de atualizar
        Visitante.atualizar_visitante(this.visitante)
          .then((resposta_atualizar_visitante) => {
            this.visitante = {};
            Swal.fire(
              "Sucesso",
              "Visitante " +
                resposta_atualizar_visitante.data.visnome +
                " atualizado com sucesso!!!",
              "success"
            );
            this.exibir_visitante();
          })
          .catch((e) => {
            Swal.fire(
              "Oops...",
              "Erro ao atualizar o visitante! - Erro: " + e.response.data.error,
              "error"
            );
          });
        this.close();
      }
    },
    // Método pra exibir os visitantes
    exibir_visitante() {
      Visitante.listar_visitante()
        .then((resposta_lista_visitantes) => {
          this.lista_de_visitantes = resposta_lista_visitantes.data;
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao carregar a tabela de visitantes! - Erro: " +
              e.response.data.error,
            "error"
          );
        });
    },

    // Método que valida se os campos estão preenchidos, se não estiverem ele bloqueia o botão CADASTRAR
    validate() {
      this.$refs.form.validate();
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto visitante
    editar_visitante(visitante) {
      this.editedIndex = this.lista_de_visitantes.indexOf(visitante);
      this.visitante = Object.assign({}, visitante);
      this.dialog = true;
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto visitante
    deleteItem(visitante) {
      this.editedIndex = this.lista_de_visitantes.indexOf(visitante);
      this.visitante = Object.assign({}, visitante);
      this.dialogDelete = true;
    },

    // Método pra excluir os visitantes
    deletar_visitante(visitante) {
      Visitante.excluir_visitante(visitante)
        .then((resposta_excluir_visitante) => {
          Swal.fire("Sucesso", "Visitante excluido com sucesso!!!", "success");
          resposta_excluir_visitante;
          this.exibir_visitante();
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao excluir o visitante! - Erro: " + e.response.data.error,
            "error"
          );
        });
      this.closeDelete();
    },

    // Método que vai fechar o modal "dialog"
    close() {
      this.dialog = false;
      this.visitante = {};
    },

    // Método que vai fechar o modal "dialogDelete"
    closeDelete() {
      this.dialogDelete = false;
      this.visitante = {};
    },
  },
};
</script>

<style>
#visitante {
  background-color: #181820;
}
</style>
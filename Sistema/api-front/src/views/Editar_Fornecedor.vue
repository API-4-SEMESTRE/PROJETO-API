<template>
  <v-app id="editar-fornecedor">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md12>
            <h1 style="text-align: center; color: white; margin-top: 15px">
              Editar Fornecedor
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
                        :headers="headers_contato"
                        :items="lista_de_contato"
                        :search="search"
                        sort-by="calories"
                        class="elevation-1"
                      >
                        <template v-slot:top>
                          <v-toolbar flat>
                            <v-toolbar-title>Lista de Contatos</v-toolbar-title>
                            <v-spacer></v-spacer>
                            <v-text-field
                              v-model="search"
                              append-icon="mdi-magnify"
                              label="Search"
                              single-line
                              hide-details
                            ></v-text-field>
                            <v-spacer></v-spacer>
                            <v-dialog v-model="dialogContato" max-width="500px">
                              <v-card style="background-color: #272733">
                                <v-card-title>
                                  <span class="text-h5 white--text"
                                    >Editar Contato</span
                                  >
                                </v-card-title>
                                <v-card-text>
                                  <v-container>
                                    <!-- CADASTRO CONTATO -->
                                    <v-form
                                      ref="form"
                                      v-model="validContato"
                                      lazy-validation
                                      @submit.prevent="cadastrar_contato"
                                    >
                                      <v-container>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Nome Completo</span
                                            >
                                            <v-text-field
                                              label="Nome Completo"
                                              v-model="contato.nomecon"
                                              :rules="regra_nome_completo"
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
                                              >Função</span
                                            >
                                            <v-text-field
                                              label="Função"
                                              v-model="contato.func_con"
                                              :rules="regra_funcao"
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
                                              >Telefone</span
                                            >
                                            <!-- <v-text-field
                                              label="Telefone"
                                              v-mask="'(##) #####-####'"
                                              v-model="contato.tel_con"
                                              :rules="regra_telefone"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                            ></v-text-field> -->
                                            <v-text-field
                                              label="Telefone"
                                              v-model="contato.tel_con"
                                              :rules="regra_telefone"
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
                                              v-model="contato.email_con"
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
                                              >Código Fornecedor</span
                                            >
                                            <v-text-field
                                              label="Código Fornecedor"
                                              v-model="contato.fornecod"
                                              :rules="regra_codigo_fornecedor"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                            ></v-text-field>
                                          </v-col>
                                        </v-row>
                                        <v-row>
                                          <v-col></v-col>
                                          <v-col>
                                            <v-btn
                                              text
                                              color="white"
                                              @click="closeContato"
                                            >
                                              Cancelar
                                            </v-btn>
                                          </v-col>
                                          <v-col>
                                            <v-btn
                                              color="#C84634"
                                              class="white--text mr-4"
                                              type="submit"
                                              :disabled="!validContato"
                                              @click="validateContato;"
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
                                    @click="deletar_contato(contato)"
                                    >Sim</v-btn
                                  >
                                  <v-spacer></v-spacer>
                                </v-card-actions>
                              </v-card>
                            </v-dialog>
                          </v-toolbar>
                        </template>
                        <template v-slot:item.actions="{ item }">
                          <v-icon class="mr-2" @click="editar_contato(item)">
                            mdi-pencil
                          </v-icon>
                          <v-icon @click="deletItemContato(item)">
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
import Contato from "../services/contato";
// import Fornecedor from "../services/fornecedor";
// import Endereco from "../services/endereco";
import Swal from "sweetalert2";
import axios from "axios";

export default {
  data: () => ({
    cep: "",
    data: {
      logradouro: "",
      bairro: "",
      localidade: "",
      uf: "",
    },
    tabs: null,
    // Validando se os campos estão preenchidos e se são validos
    valid: true,
    validContato: true,
    validFornecedor: true,
    validEndereco: true,
    regra_nome_empresa: [(v) => !!v || "O nome da empresa é obrigatório"],
    regra_cnpj: [(v) => !!v || "O CNPJ é obrigatório"],
    regra_ramo: [(v) => !!v || "O ramo de atividade é obrigatório"],
    regra_codigo_contato: [(v) => !!v || "O código de contato é obrigatório"],
    regra_cep: [(v) => !!v || "O CEP é obrigatório"],
    regra_rua: [(v) => !!v || "A rua é obrigatória"],
    regra_bairro: [(v) => !!v || "O bairro é obrigatório"],
    regra_cidade: [(v) => !!v || "A cidade é obrigatória"],
    regra_estado: [(v) => !!v || "O estado é obrigatório"],
    regra_numero: [(v) => !!v || "O número é obrigatório"],
    regra_codigo_fornecedor: [
      (v) => !!v || "O código de fornecedor é obrigatório",
    ],
    regra_nome_completo: [(v) => !!v || "O nome é obrigatório"],
    regra_funcao: [(v) => !!v || "A função é obrigatória"],
    regra_telefone: [(v) => !!v || "O telefone é obrigatório"],
    regra_email: [
      (v) => !!v || "O e-mail é obrigatório",
      (v) => /.+@.+\..+/.test(v) || "E-mail inválido",
    ],

    // Variaveis referentes aos modais que abrem na tela, se for false ele não aparece na tela, se for true ele aparece na tela
    dialog: false,
    dialogEndereco: false,
    dialogContato: false,
    dialogDelete: false,

    // Array com a lista de fornecedores
    lista_de_contato: [],

    // Criando o objeto que vai ser feito o POST
    fornecedor: {
      nomeforn: "",
      ramo_forn: "",
      cnpjforn: "",
      siteforn: "",
    },
    // Criando o objeto que vai ser feito o POST
    endereco: {
      rua_end: "",
      bairro_end: "",
      cep_end: "",
      cidade_end: "",
      estado_end: "",
      forncod: "",
      complemento_end: "",
      num_end: "",
    },
    // Criando o objeto que vai ser feito o POST
    contato: {
      nomecon: "",
      func_con: "",
      tel_con: "",
      email_con: "",
      fornecod: "",
    },

    // Variavel que vai ser usada pra pesquisa da tabela
    search: "",

    // Array que contem as colunas da tabela de fornecedores
    headers_contato: [
      {
        text: "CÓDIGO",
        align: "start",
        value: "concod",
      },
      { text: "NOME", value: "nomecon" },
      { text: "FUNÇÃO", value: "func_con" },
      { text: "TELEFONE", value: "tel_con" },
      { text: "EMAIL", value: "email_con" },
      { text: "CÓDIGO FORNECEDOR", value: "fornecod" },
      { text: "Actions", value: "actions", sortable: false },
    ],
  }),

  mounted() {
    this.exibir_contato();
  },

  watch: {
    // Fechando os modais
    dialog(val) {
      val || this.close();
    },
    dialogEndereco(val) {
      val || this.closeEndereco();
    },
    dialogContato(val) {
      val || this.closeContato();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
  },

  methods: {
    searchCep() {
      if (this.cep.length === 9) {
        axios
          .get(`https://viacep.com.br/ws/${this.cep}/json/`)
          .then((response) => (this.data = response.data))
          .catch((error) => console.log(error));
      }
    },
    // Método de cadastro de contato
    cadastrar_contato() {
      // Se o contato não tiver um "concod" significa que esse contato não existe então ele vai pra resquest de cadastro
      if (!this.contato.concod) {
        Contato.salvar_contato(this.contato)
          .then((resposta_cadastro_contato) => {
            this.contato = {};
            Swal.fire(
              "Sucesso",
              "Contato " +
                resposta_cadastro_contato.data.nomecon +
                " cadastrado com sucesso!!!",
              "success"
            );
            this.exibir_contato();
          })
          .catch((e) => {
            Swal.fire(
              "Oops...",
              "Erro ao cadastrar o usuário! - Erro: " + e.response.data.error,
              "error"
            );
          });
        this.closeContato();
      } else {
        // Método de atualizar contato
        // Se o contato já tiver um "concod" ele já existe então ele vai pra request de atualizar
        Contato.atualizar_contato(this.contato)
          .then((resposta_atualizar_contato) => {
            this.usuario = {};
            Swal.fire(
              "Sucesso",
              "Contato " +
                resposta_atualizar_contato.data.nomecon +
                " atualizado com sucesso!!!",
              "success"
            );
            this.exibir_contato();
          })
          .catch((e) => {
            Swal.fire(
              "Oops...",
              "Erro ao atualizar o contato! - Erro: " + e.response.data.error,
              "error"
            );
          });
        this.closeContato();
      }
    },
    // Método pra exibir os contatos
    exibir_contato() {
      Contato.listar_contato()
        .then((resposta_lista_contato) => {
          this.lista_de_contato = resposta_lista_contato.data;
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao carregar a tabela de contato! - Erro: " +
              e.response.data.error,
            "error"
          );
        });
    },
    // Método que valida se os campos estão preenchidos, se não estiverem ele bloqueia o botão CADASTRAR
    validateContato() {
      this.$refs.form.validateContato();
    },
    // Método que valida se os campos estão preenchidos, se não estiverem ele bloqueia o botão CADASTRAR
    validateFornecedor() {
      this.$refs.form.validateFornecedor();
    },
    // Método que valida se os campos estão preenchidos, se não estiverem ele bloqueia o botão CADASTRAR
    validateEndereco() {
      this.$refs.form.validateEndereco();
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto contato
    editar_contato(contato) {
      //this.editedIndex = this.lista_de_usuarios.indexOf(contato);
      this.contato = Object.assign({}, contato);
      this.dialogContato = true;
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto usuario
    deletItemContato(contato) {
      //   this.editedIndex = this.lista_de_usuarios.indexOf(usuario);
      this.contato = Object.assign({}, contato);
      this.dialogDelete = true;
    },

    // Método pra excluir os contatos
    deletar_contato(contato) {
      Contato.excluir_contato(contato)
        .then((resposta_excluir_contato) => {
          Swal.fire("Sucesso", "Contato excluido com sucesso!!!", "success");
          resposta_excluir_contato;
          this.exibir_contato();
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao excluir o contato! - Erro: " + e.response.data.error,
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

    // Método que vai fechar o modal "dialogDelete"
    closeEndereco() {
      this.dialogEndereco = false;
      this.usuario = {};
    },

    // Método que vai fechar o modal "dialogDelete"
    closeContato() {
      this.dialogContato = false;
      this.usuario = {};
    },
  },
};
</script>

<style>
#editar-fornecedor {
  background-color: #181820;
}
</style>
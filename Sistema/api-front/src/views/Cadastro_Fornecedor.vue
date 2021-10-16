<template>
  <v-app id="cadastro-fornecedor">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md12>
            <h1 style="text-align: center; color: white; margin-top: 15px">
              Fornecedores
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
                        :headers="headers_fornecedores"
                        :items="lista_fornecedores"
                        sort-by="calories"
                        class="elevation-1"
                      >
                        <template v-slot:top>
                          <v-toolbar flat>
                            <v-toolbar-title
                              >Lista de Fornecedores</v-toolbar-title
                            >
                            <v-divider class="mx-4" inset vertical></v-divider>
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
                                  Novo Fornecedor
                                </v-btn>
                              </template>
                              <v-card style="background-color: #272733">
                                <v-card-title>
                                  <span class="text-h5 white--text"
                                    >Dados da Empresa</span
                                  >
                                </v-card-title>
                                <v-card-text>
                                  <v-container>
                                    <!-- CADASTRO FORNECEDOR -->
                                    <v-form
                                      ref="form"
                                      v-model="validFornecedor"
                                      lazy-validation
                                      @submit.prevent="cadastrar_fornecedor"
                                    >
                                      <v-container>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Nome da Empresa</span
                                            >
                                            <v-text-field
                                              label="Nome da Empresa"
                                              v-model="fornecedor.nomeforn"
                                              :rules="regra_nome_empresa"
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
                                              >CNPJ</span
                                            >
                                            <v-text-field
                                              label="CNPJ"
                                              v-mask="'##.###.###/####-##'"
                                              v-model="fornecedor.cnpjforn"
                                              :rules="regra_cnpj"
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
                                              >Ramo de Atividade</span
                                            >
                                            <v-text-field
                                              label="Ramo de Atividade"
                                              v-model="fornecedor.ramo_forn"
                                              :rules="regra_ramo"
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
                                              >Site</span
                                            >
                                            <v-text-field
                                              label="Site"
                                              v-model="fornecedor.siteforn"
                                              single-line
                                              solo
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
                                              :disabled="!validFornecedor"
                                              @click="
                                                validateFornecedor;
                                                dialogEndereco =
                                                  !dialogEndereco;
                                              "
                                            >
                                              Prosseguir
                                            </v-btn>
                                          </v-col>
                                        </v-row>
                                      </v-container>
                                    </v-form>
                                  </v-container>
                                </v-card-text>
                              </v-card>
                            </v-dialog>
                            <v-dialog
                              v-model="dialogEndereco"
                              max-width="500px"
                            >
                              <v-card style="background-color: #272733">
                                <v-card-title>
                                  <span class="text-h5 white--text"
                                    >Dados do Endereço</span
                                  >
                                </v-card-title>
                                <v-card-text>
                                  <v-container>
                                    <!-- CADASTRO ENDEREÇO -->
                                    <v-form
                                      ref="form"
                                      v-model="validEndereco"
                                      lazy-validation
                                      @submit.prevent="cadastrar_endereco"
                                    >
                                      <v-container>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >CEP</span
                                            >
                                            <v-text-field
                                              label="CEP"
                                              v-mask="'########'"
                                              v-model="endereco.cep_end"
                                              :rules="regra_cep"
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
                                              >Rua</span
                                            >
                                            <v-text-field
                                              label="Rua"
                                              v-model="endereco.rua_end"
                                              :rules="regra_rua"
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
                                              >Bairro</span
                                            >
                                            <v-text-field
                                              label="Bairro"
                                              v-model="endereco.bairro_end"
                                              :rules="regra_bairro"
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
                                              >Cidade</span
                                            >
                                            <v-text-field
                                              label="Cidade"
                                              v-model="endereco.cidade_end"
                                              :rules="regra_cidade"
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
                                              >Estado</span
                                            >
                                            <v-text-field
                                              label="Estado"
                                              v-model="endereco.estado_end"
                                              :rules="regra_estado"
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
                                              >Número</span
                                            >
                                            <v-text-field
                                              label="Número"
                                              v-model="endereco.num_end"
                                              :rules="regra_numero"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                            ></v-text-field>
                                          </v-col>
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Complemento</span
                                            >
                                            <v-text-field
                                              label="Complemento"
                                              v-model="endereco.complemento_end"
                                              single-line
                                              solo
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
                                              v-model="endereco.forncod"
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
                                              @click="closeEndereco"
                                            >
                                              Cancelar
                                            </v-btn>
                                          </v-col>
                                          <v-col>
                                            <v-btn
                                              color="#C84634"
                                              class="white--text mr-4"
                                              type="submit"
                                              :disabled="!validEndereco"
                                              @click="
                                                validateEndereco;
                                                dialogContato = !dialogContato;
                                              "
                                            >
                                              Prosseguir
                                            </v-btn>
                                          </v-col>
                                        </v-row>
                                      </v-container>
                                    </v-form>
                                  </v-container>
                                </v-card-text>
                              </v-card>
                            </v-dialog>
                            <v-dialog v-model="dialogContato" max-width="500px">
                              <v-card style="background-color: #272733">
                                <v-card-title>
                                  <span class="text-h5 white--text"
                                    >Dados do Contato</span
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
                                            <v-text-field
                                              label="Telefone"
                                              v-mask="'(##) #####-####'"
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
import Contato from "../services/contato";
import Fornecedor from "../services/fornecedor";
import Endereco from "../services/endereco";
import Swal from "sweetalert2";

export default {
  data: () => ({
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
    lista_fornecedores: [],

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
    headers_fornecedores: [
      {
        text: "NOME EMPRESA",
        align: "start",
        value: "nome",
      },
      { text: "RAMO", value: "ramo" },
      { text: "CNPJ", value: "cnpj" },
      { text: "RUA", value: "rua" },
      { text: "NÚMERO", value: "numero" },
      { text: "BAIRRO", value: "bairro" },
      { text: "CIDADE", value: "numero" },
      { text: "UF", value: "uf" },
      //{ text: "COMPLEMENTO", value: "complemento" },
      { text: "CONTATO NOME", value: "contato_nome" },
      { text: "CONTATO FONE", value: "contato_fone" },
      { text: "CONTATO EMAIL", value: "contato_email" },
      { text: "ACTIONS", value: "actions", sortable: false },
    ],
  }),

  mounted() {
    this.exibir_fornecedores();
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
    // Método de cadastro de contato
    cadastrar_contato() {
      Contato.salvar_contato(this.contato)
        .then((resposta_cadastro_contato) => {
          this.contato = {};
          Swal.fire(
            "Sucesso",
            "Fornecedor " +
              resposta_cadastro_contato.data.nomecon +
              " cadastrado com sucesso!!!",
            "success"
          );
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao cadastrar o contato! - Erro: " + e.response.data.error,
            "error"
          );
        });
      this.exibir_fornecedores();
      this.closeContato();
    },
    // Método de cadastro de fornecedor
    cadastrar_fornecedor() {
      Fornecedor.salvar_fornecedor(this.fornecedor)
        .then((resposta_cadastro_fornecedor) => {
          this.fornecedor = {};
          // Swal.fire(
          //   "Sucesso",
          //   "Fornecedor " +
          //     resposta_cadastro_fornecedor.data.nomeforn +
          //     " cadastrado com sucesso!!!",
          //   "success"
          // );
          resposta_cadastro_fornecedor;
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao cadastrar o fornecedor! - Erro: " + e.response.data.error,
            "error"
          );
        });
      // this.exibir_fornecedores();
      this.close();
    },
    // Método de cadastro de endereço
    cadastrar_endereco() {
      Endereco.salvar_endereco(this.endereco)
        .then((resposta_cadastro_endereco) => {
          this.endereco = {};
          // Swal.fire(
          //   "Sucesso",
          //   "Endereço " +
          //     resposta_cadastro_endereco.data.rua_end +
          //     " cadastrado com sucesso!!!",
          //   "success"
          // );
          resposta_cadastro_endereco;
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao cadastrar o endereço! - Erro: " + e.response.data.error,
            "error"
          );
        });
      // this.exibir_fornecedores();
      this.closeEndereco();
    },
    // Método pra exibir os contatos
    exibir_fornecedores() {
      Fornecedor.listar_fornecedor()
        .then((resposta_lista_fornecedor) => {
          this.lista_fornecedores = resposta_lista_fornecedor.data;
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao carregar a tabela de fornecedores! - Erro: " +
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
#cadastro-fornecedor {
  background-color: #181820;
}
</style>
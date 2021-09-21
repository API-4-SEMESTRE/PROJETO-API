<template>
  <div>
    <v-app>
      <v-main>
        <h1 style="text-align: center; margin-top: 20px">
          Cadastro de Fornecedor
        </h1>
        <v-row>
          <v-col>
            <!-- CADASTRO CONTATO -->
            <v-form
              style="padding-top: 30px"
              ref="form"
              v-model="validContato"
              lazy-validation
              @submit.prevent="cadastrar_contato"
            >
              <v-container class="ma-70" style="width: 80%; border: solid 1px">
                <h2 style="text-align: center">Informações de Contato</h2>
                <v-row
                  justify="center"
                  align="stretch"
                  style="margin-top: 10px"
                >
                  <v-col cols="24">
                    <span> Nome Completo</span>
                    <v-text-field
                      v-model="contato.nomecon"
                      :rules="regra_nome_completo"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row>
                  <v-col cols="6">
                    <span> Função </span>
                    <v-text-field
                      v-model="contato.func_con"
                      :rules="regra_funcao"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6">
                    <span> Telefone </span>
                    <v-text-field
                      outlined
                      required
                      dense
                      v-model="contato.tel_con"
                      :rules="regra_telefone"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row justify="center" align="stretch">
                  <v-col cols="24">
                    <span>E-mail</span>
                    <v-text-field
                      outlined
                      required
                      dense
                      v-model="contato.email_con"
                      :rules="regra_email"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-btn
                  class="mr-4"
                  type="submit"
                  :disabled="!validContato"
                  @click="validateContato"
                  id="btn_cadastrar_contato"
                  color="primary"
                >
                  Prosseguir
                </v-btn>
              </v-container>
            </v-form>
          </v-col>
          <!-- CADASTRO FORNECEDOR -->
          <v-col>
            <v-form
              style="padding-top: 30px"
              ref="form"
              v-model="validFornecedor"
              lazy-validation
              @submit.prevent="cadastrar_fornecedor"
            >
              <v-container class="ma-70" style="width: 90%; border: solid 1px">
                <h2 style="text-align: center">Informações do Fornecedor</h2>
                <v-row
                  justify="center"
                  align="stretch"
                  style="margin-top: 10px"
                >
                  <v-col cols="24">
                    <span style="padding-top: 8px"> Nome da Empresa </span>
                    <v-text-field
                      v-model="fornecedor.nomeforn"
                      :rules="regra_nome_empresa"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <span> CNPJ</span>
                    <v-text-field
                      v-model="fornecedor.cnpjforn"
                      :rules="regra_cnpj"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6">
                    <span> Ramo de Atividade </span>
                    <v-text-field
                      v-model="fornecedor.ramo_forn"
                      :rules="regra_ramo"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <span> Código do Contato </span>
                    <v-text-field
                      v-model="fornecedor.con_cod"
                      :rules="regra_codigo_contato"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-btn
                  class="mr-4"
                  type="submit"
                  :disabled="!validFornecedor"
                  @click="validateFornecedor"
                  id="btn_cadastrar_contato"
                  color="primary"
                >
                  Prosseguir
                </v-btn>
              </v-container>
            </v-form>
          </v-col>
          <!-- CADASTRO ENDEREÇO -->
          <v-col>
            <v-form
              style="padding-top: 30px"
              ref="form"
              v-model="validEndereco"
              lazy-validation
              @submit.prevent="cadastrar_endereco"
            >
              <v-container class="ma-70" style="width: 90%; border: solid 1px">
                <h2 style="text-align: center">Informações do Endereço</h2>
                <v-row>
                  <v-col cols="6">
                    <span> CEP </span>
                    <v-text-field
                      v-model="endereco.cep_end"
                      :rules="regra_cep"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6">
                    <span> Rua </span>
                    <v-text-field
                      v-model="endereco.rua_end"
                      :rules="regra_rua"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <span> Bairro</span>
                    <v-text-field
                      v-model="endereco.bairro_end"
                      :rules="regra_bairro"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6">
                    <span> Cidade </span>
                    <v-text-field
                      v-model="endereco.cidade_end"
                      :rules="regra_cidade"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <span> Estado </span>
                    <v-text-field
                      v-model="endereco.estado_end"
                      :rules="regra_estado"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6">
                    <span> Número </span>
                    <v-text-field
                      outlined
                      required
                      dense
                      v-model="endereco.num_end"
                      :rules="regra_estado"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <span> Complemento </span>
                    <v-text-field 
                    outlined 
                    dense
                    v-model="endereco.complemento_end"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6">
                    <span> Código Fornecedor </span>
                    <v-text-field
                      v-model="endereco.forncod"
                      :rules="regra_codigo_fornecedor"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-btn
                  class="mr-4"
                  type="submit"
                  :disabled="!validEndereco"
                  @click="validateEndereco"
                  id="btn_cadastrar_contato"
                  color="primary"
                >
                  Cadastrar
                </v-btn>
              </v-container>
            </v-form>
          </v-col>
        </v-row>
      </v-main>
      <!-- TABELA CONTATO -->
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
              Lista de Contatos
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
              :headers="headers_contato"
              :items="lista_de_contato"
              :search="search"
            ></v-data-table>
          </v-card>
        </v-card-text>
      </v-card>
      <!-- TABELA FORNECEDOR -->
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
              Lista de Fornecedor
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
              :headers="headers_fornecedor"
              :items="lista_de_fornecedor"
              :search="search"
            ></v-data-table>
          </v-card>
        </v-card-text>
      </v-card>
      <!-- TABELA ENDEREÇO -->
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
              Lista de Endereços
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
              :headers="headers_endereco"
              :items="lista_de_endereco"
              :search="search"
            ></v-data-table>
          </v-card>
        </v-card-text>
      </v-card>
    </v-app>
  </div>
</template>


<script>
import Contato from "../services/contato";
import Fornecedor from "../services/fornecedor";
import Endereco from "../services/endereco";
import Swal from "sweetalert2";

export default {
  data: () => ({
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

    // Array com a lista de usuarios
    lista_de_contato: [],
    // Array com a lista de fornecedores
    lista_de_fornecedor: [],
    // Array com a lista de endereços
    lista_de_endereco: [],

    // Criando o objeto que vai ser feito o POST
    contato: {
      nomecon: "",
      func_con: "",
    },
    // Criando o objeto que vai ser feito o POST
    fornecedor: {
      nomeforn: "",
      ramo_forn: "",
      cnpjforn: "",
      con_cod: "",
    },
    // Criando o objeto que vai ser feito o POST
    endereco: {
      rua_end: "",
      bairro_end: "",
      cep_end: "",
      cidade_end: "",
      estado_end: "",
      forncod: "",
    },

    // Variavel que vai ser usada pra pesquisa da tabela
    search: "",

    // Array que contem as colunas da tabela de contato
    headers_contato: [
      {
        text: "CÓDIGO",
        align: "start",
        value: "concod",
      },
      { text: "NOME", value: "nomecon" },
      { text: "FUNÇÃO", value: "func_con" },
      { text: "TEL", value: "tel_con" },
      { text: "EMAIL", value: "email_con" },
    ],
    // Array que contem as colunas da fornecedor
    headers_fornecedor: [
      {
        text: "CÓDIGO",
        align: "start",
        value: "cod",
      },
      { text: "NOME FORNECEDOR", value: "nomeforn" },
      { text: "RAMO", value: "ramo_forn" },
      { text: "CNPJ", value: "cnpjforn" },
      { text: "CÓDIGO CONTATO", value: "con_cod" },
    ],
    // Array que contem as colunas do endereço
    headers_endereco: [
      {
        text: "CÓDIGO FORNECEDOR",
        align: "start",
        value: "forncod",
      },
      { text: "CEP", value: "cep_end" },
      { text: "RUA", value: "rua_end" },
      { text: "NUMERO", value: "num_end" },
      { text: "COMPLEMENTO", value: "complemento_end" },
      { text: "BAIRRO", value: "bairro_end" },
      { text: "CIDADE", value: "cidade_end" },
      { text: "ESTADO", value: "estado_end" },
    ],
  }),

  mounted() {
    this.exibir_contato();
    this.exibir_fornecedor();
    this.exibir_endereco();
  },

  methods: {
    // Método de cadastro de contato
    cadastrar_contato() {
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
            "Erro ao cadastrar o contato! - Erro: " + e.response.data.error,
            "error"
          );
        });
    },
    // Método de cadastro de fornecedor
    cadastrar_fornecedor() {
      Fornecedor.salvar_fornecedor(this.fornecedor)
        .then((resposta_cadastro_fornecedor) => {
          this.fornecedor = {};
          Swal.fire(
            "Sucesso",
            "Fornecedor " +
              resposta_cadastro_fornecedor.data.nomeforn +
              " cadastrado com sucesso!!!",
            "success"
          );
          this.exibir_fornecedor();
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao cadastrar o fornecedor! - Erro: " + e.response.data.error,
            "error"
          );
        });
    },
    // Método de cadastro de endereço
    cadastrar_endereco() {
      Endereco.salvar_endereco(this.endereco)
        .then((resposta_cadastro_endereco) => {
          this.endereco = {};
          Swal.fire(
            "Sucesso",
            "Endereço " +
              resposta_cadastro_endereco.data.rua_end +
              " cadastrado com sucesso!!!",
            "success"
          );
          this.exibir_endereco();
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao cadastrar o endereço! - Erro: " + e.response.data.error,
            "error"
          );
        });
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
            "Erro ao carregar a tabela de contatos! - Erro: " +
              e.response.data.error,
            "error"
          );
        });
    },
    // Método pra exibir os fornecedores
    exibir_fornecedor() {
      Fornecedor.listar_fornecedor()
        .then((resposta_lista_fornecedor) => {
          this.lista_de_fornecedor = resposta_lista_fornecedor.data;
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
    // Método pra exibir os endereços
    exibir_endereco() {
      Endereco.listar_endereco()
        .then((resposta_lista_endereco) => {
          this.lista_de_endereco = resposta_lista_endereco.data;
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao carregar a tabela de endereços! - Erro: " +
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
  },
};
</script>

<style>
#btn_cadastrar_contato {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: auto;
  border: solid 1px black;
}
</style>
<template>
  <div>
    <v-app-bar color="#c74634" dense dark style="border: solid 1px black">
      <v-avatar>
        <img
          src="https://cdn.vuetifyjs.com/images/john.jpg"
          alt="John"
          style="position: fixed; right: 0px; float: right; margin-bottom: 0"
        />
      </v-avatar>
    </v-app-bar>

    <v-app>
      <v-main>
        <h1 style="text-align: center; margin-top: 20px">
          Cadastro de Fornecedor
        </h1>
        <v-row>
          <v-col>
            <v-form
              style="padding-top: 30px"
              ref="form"
              v-model="valid"
              lazy-validation
              @submit.prevent="cadastrar_usuario"
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
                      v-model="nome_completo"
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
                      v-model="funcao"
                      :rules="regra_funcao"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6">
                    <span> Telefone </span>
                    <v-text-field
                      v-model="telefone"
                      :rules="regra_telefone"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row justify="center" align="stretch">
                  <v-col cols="24">
                    <span>E-mail</span>
                    <v-text-field
                      v-model="email"
                      :rules="regra_email"
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
                  id="btn_cadastrar_fornecedor"
                >
                  Cadastrar
                </v-btn>
              </v-container>
            </v-form>
          </v-col>
          <v-col>
            <v-form
              style="padding-top: 30px"
              ref="form"
              v-model="valid"
              lazy-validation
              @submit.prevent="cadastrar_usuario"
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
                      v-model="nome_empresa"
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
                      v-model="cnpj"
                      :rules="regra_cnpj"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6">
                    <span> Ramo de Atividade </span>
                    <v-text-field
                      v-model="ramo"
                      :rules="regra_ramo"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row>
                  <v-col cols="6">
                    <span> CEP </span>
                    <v-text-field
                      v-model="cep"
                      :rules="regra_cep"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6">
                    <span> Rua </span>
                    <v-text-field
                      v-model="rua"
                      :rules="regra_rua"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row>
                  <v-col cols="6" md="4">
                    <span> Bairro</span>
                    <v-text-field
                      v-model="bairro"
                      :rules="regra_bairro"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6" md="4">
                    <span> Cidade </span>
                    <v-text-field
                      v-model="cidade"
                      :rules="regra_cidade"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6" md="4">
                    <span> Estado </span>
                    <v-text-field
                      v-model="estado"
                      :rules="regra_estado"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row>
                  <v-col cols="6">
                    <span> Número </span>
                    <v-text-field
                      v-model="numero"
                      :rules="regra_numero"
                      outlined
                      required
                      dense
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6">
                    <span> Complemento </span>
                    <v-text-field
                      v-model="complemento"
                      outlined
                      dense
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-btn
                  class="mr-4"
                  type="submit"
                  :disabled="!valid"
                  @click="validate"
                  id="btn_cadastrar_fornecedor"
                >
                  Cadastrar
                </v-btn>
              </v-container>
            </v-form>
          </v-col>
        </v-row>
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
              Lista de Fornecedores
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
    regra_nome_empresa: [(v) => !!v || "O nome da empresa é obrigatório"],
    regra_cnpj: [(v) => !!v || "O CNPJ é obrigatório"],
    regra_ramo: [(v) => !!v || "O ramo de atividade é obrigatório"],
    regra_cep: [(v) => !!v || "O CEP é obrigatório"],
    regra_rua: [(v) => !!v || "A rua é obrigatória"],
    regra_bairro: [(v) => !!v || "O bairro é obrigatório"],
    regra_cidade: [(v) => !!v || "A cidade é obrigatória"],
    regra_estado: [(v) => !!v || "O estado é obrigatório"],
    regra_numero: [(v) => !!v || "O número é obrigatório"],
    regra_nome_completo: [(v) => !!v || "O nome é obrigatório"],
    regra_funcao: [(v) => !!v || "A função é obrigatória"],
    regra_telefone: [(v) => !!v || "O telefone é obrigatório"],
    regra_email: [
      (v) => !!v || "O e-mail é obrigatório",
      (v) => /.+@.+\..+/.test(v) || "E-mail inválido",
    ],

    // Array com a lista de usuarios
    lista_de_usuarios: [],

    // Criando o objeto que vai ser feito o POST
    usuario: {
      cod: "5",
      nome: "",
      tipo: "FUNCIONARIO",
      email: "",
      date_create: "2021-09-15T00:00:00.000+00:00",
      active: "1",
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
#btn_cadastrar_fornecedor {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: auto;
  background-color: #c74634;
  color: white;
  border: solid 1px black;
}
</style>
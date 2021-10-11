<template>
  <v-app id="criar-conta">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <h1 style="text-align: center; margin-bottom: 20px; color: white">
              Criar Conta
            </h1>
            <v-card class="elevation-12" id="card-login">
              <v-card-text>
                <v-form
                  ref="form"
                  v-model="valid"
                  lazy-validation
                  @submit.prevent="cadastrar_usuario"
                >
                  <v-container>
                    <v-row justify="center">
                      <v-col cols="24">
                        <span style="color: white; font-size: 18px">Nome</span>
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
                        <span style="color: white; font-size: 18px"
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
                        <span style="color: white; font-size: 18px">Senha</span>
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
                          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                          :type="show1 ? 'text' : 'password'"
                          @click:append="show1 = !show1"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col></v-col>
                      <v-col>
                        <v-btn text color="white" :to="{ name: 'Login' }"
                          >Cancelar</v-btn
                        >
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
              </v-card-text>
            </v-card>
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
    // Validando se os campos estão preenchidos e se são validos
    valid: true,
    regra_nome: [(v) => !!v || "O nome é obrigatório"],
    regra_email: [
      (v) => !!v || "O e-mail é obrigatório",
      //(v) => /.+@.+\..+/.test(v) || "E-mail inválido",
      (v) => /^[a-z0-9.]+@oracle.com$/.test(v) || "E-mail inválido",
    ],
    regra_senha: [(v) => !!v || "A senha é obrigatória"],

    // Criando a variavel pro icone de mostrar a senha
    show1: false,

    // Criando o objeto que vai ser feito o POST
    usuario: {
      nome: "",
      tipo: "COLABORADOR",
      email: "",
      active: "false",
      senha: "",
    },
  }),

  methods: {
    // Método de cadastro de usuario
    cadastrar_usuario() {
      Usuario.salvar_usuario(this.usuario)
        .then((resposta_cadastro_usuario) => {
          this.usuario = {};
          Swal.fire(
            "Sucesso",
            "Conta " +
              resposta_cadastro_usuario.data.nome +
              " criada com sucesso!!!",
            "success"
          );
          this.$router.push("Login");
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao criar conta! - Erro: " + e.response.data.error,
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
#criar-conta {
  background-color: #181820;
}
</style>
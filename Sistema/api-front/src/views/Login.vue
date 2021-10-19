<template>
  <v-app id="login">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-img
              src="../images/Logo_AgendHouse.png"
            ></v-img>
            <v-card class="elevation-12" id="card-login">
              <v-card-text>
                <v-form
                  ref="form"
                  v-model="valid"
                  lazy-validation
                  @submit.prevent="do_login"
                >
                  <span style="color: white; font-size: 18px">E-mail</span>
                  <v-text-field
                    label="Email"
                    v-model="usuario.email"
                    :rules="regra_email"
                    single-line
                    solo
                    required
                    dense
                    background-color="#A9A9A9"
                  ></v-text-field>
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
                  <v-btn
                    id="botao-login"
                    block
                    color="#C84634"
                    type="submit"
                    class="white--text"
                    :disabled="!valid"
                    @click="validate"
                    >Login</v-btn
                  >
                </v-form>
              </v-card-text>
            </v-card>
            <div
              style="
                margin-top: 50px;
                font-size: 18px;
                color: white;
                text-align: center;
              "
            >
              Ainda não tem uma conta? Se cadastre no sistema
              <v-btn
                color="#C84634"
                class="white--text"
                block
                style="margin-top: 20px"
                :to="{ name: 'Criar_Conta' }"
                >Criar Conta</v-btn
              >
            </div>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script src="../router/index.js"></script>
<script>
import Usuario from "../services/login";
import Swal from "sweetalert2";
const { currentUser } = require("../router/index");

export default {
  name: "login",
  data: () => ({
    usuario: {
      email: "",
      senha: "",
    },
    // Criando a variavel pro icone de mostrar a senha
    show1: false,

    // Validando se os campos do formulario estão preenchidos e se são validos
    valid: true,
    regra_email: [
      (v) => !!v || "O e-mail é obrigatório",
      (v) => /.+@.+\..+/.test(v) || "E-mail inválido",
      //(v) => /^[a-z0-9.]+@oracle.com$/.test(v) || "E-mail inválido",
    ],
    regra_senha: [(v) => !!v || "A senha é obrigatória"],
  }),
  methods: {
    do_login() {
      Usuario.login(this.usuario)
        .then((res) => {
          if (res.data.message === false) {
            Swal.fire("Oops...", "Usuário ou senha invalidos", "error");
          } else {
            localStorage.setItem("returnLogin", res.data.message);
            this.$router.replace("home");
          }
        })
        .catch(() => {
          Swal.fire("Oops...", "Usuário ou senha invalidos", "error");
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
#login {
  background-color: #181820;
}
#card-login {
  background-color: #272733;
}
</style>
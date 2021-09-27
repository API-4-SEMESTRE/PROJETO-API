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
                  password
                  type="password"
                ></v-text-field>
              </v-col>
            </v-row>

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
          </v-container>
        </v-form>
      </v-main>
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
      //(v) => /.+@.+\..+/.test(v) || "E-mail inválido",
      (v) => /^[a-z0-9.]+@oracle.com$/.test(v) || "E-mail inválido",
    ],
    regra_senha: [(v) => !!v || "A senha é obrigatória"],

    // Criando o objeto que vai ser feito o POST
    usuario: {
      nome: "",
      tipo: "COLABORADOR",
      email: "",
      active: "True",
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
  border: solid 1px black;
}
</style>
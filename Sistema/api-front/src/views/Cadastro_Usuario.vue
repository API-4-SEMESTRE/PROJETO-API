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
                ></v-text-field>
              </v-col>
            </v-row>

            <v-btn
              class="mr-4"
              type="submit"
              style="
                display: flex;
                justify-content: flex-end;
                align-items: center;
                margin: auto;
                background-color: #c74634;
                color: white;
                border: solid 1px black;
              "
              :disabled="!valid"
              @click="validate"
            >
              Cadastrar
            </v-btn>
          </v-container>
        </v-form>
      </v-main>
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th>CODIGO</th>
              <th>NOME</th>
              <th>TIPO</th>
              <th>EMAIL</th>
              <th>DATA CRIAÇÃO</th>
              <th>ATIVO</th>
              <th>SENHA</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="lista_users of lista_de_usuarios" :key="lista_users.cod">
              <td>{{ lista_users.cod }}</td>
              <td>{{ lista_users.nome }}</td>
              <td>{{ lista_users.tipo }}</td>
              <td>{{ lista_users.email }}</td>
              <td>{{ lista_users.date_create }}</td>
              <td>{{ lista_users.active }}</td>
              <td>{{ lista_users.senha }}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
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
      (v) => /.+@.+\..+/.test(v) || "E-mail inválido",
    ],
    regra_senha: [(v) => !!v || "A senha é obrigatória"],

    // Array com a lista de usuarios
    lista_de_usuarios: [],

    // Criando o objeto que vai ser feito o POST
    usuario: {
      cod: "5",
      nome: "",
      tipo: "ADMIN",
      email: "",
      date_create: "2021-09-15T00:00:00.000+00:00",
      active: "1",
      senha: "",
    },
  }),

  mounted() {
    this.exibir_usuario();
  },

  methods: {
    // Método de cadastro de usuario
    cadastrar_usuario() {
      Usuario.salvar_usuario(this.usuario).then((resposta_cadastro_usuario) => {
        this.usuario = {};
        Swal.fire(
          "Sucesso",
          "Usuario " +
            resposta_cadastro_usuario.data.nome +
            " cadastrado com sucesso!!!",
          "success"
        );
        /*alert(
          "Usuario " +
            resposta_cadastro_usuario.data.nome +
            " cadastrado com sucesso!!!"
        );*/
        this.exibir_usuario();
      });
    },
    // Método pra exibir os usuarios
    exibir_usuario() {
      Usuario.listar_usuarios().then((resposta_lista_usuarios) => {
        this.lista_de_usuarios = resposta_lista_usuarios.data;
      });
    },
    //
    validate() {
      this.$refs.form.validate();
    },
  },
};
</script>

<style>
</style>
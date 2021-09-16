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
        >
          <v-container class="ma-70" style="width: 40%; border: solid 1px">
            <v-row justify="center" align="stretch">
              <v-col cols="24">
                <span style="padding-top: 8px"> Nome Completo </span>
                <v-text-field
                  v-model="nome_completo"
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
                  v-model="email"
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
                  v-model="senha"
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

export default {
  data: () => ({
    // Validando se os campos estão preenchidos e se são validos
    valid: true,
    nome_completo: "",
    regra_nome: [(v) => !!v || "O nome é obrigatório"],
    email: "",
    regra_email: [
      (v) => !!v || "O e-mail é obrigatório",
      (v) => /.+@.+\..+/.test(v) || "E-mail inválido",
    ],
    senha: "",
    regra_senha: [(v) => !!v || "A senha é obrigatória"],

    // Array com a lista de usuarios
    lista_de_usuarios: [],
  }),

  mounted() {
    Usuario.listar_usuarios().then((resposta_lista_usuarios) => {
      console.log(resposta_lista_usuarios.data);
      this.lista_de_usuarios = resposta_lista_usuarios.data;
    });
  },
};
</script>

<style>
</style>
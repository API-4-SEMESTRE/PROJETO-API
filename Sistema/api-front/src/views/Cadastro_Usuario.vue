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
    </v-app>
  </div>
</template>


<script>
export default {
  data: () => ({
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
  }),
};
</script>

<style>
</style>
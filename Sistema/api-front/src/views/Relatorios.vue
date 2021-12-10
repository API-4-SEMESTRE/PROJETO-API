<template>
  <v-app id="relatorios">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md12>
            <h1 style="text-align: center; color: white; margin-top: 15px">
              Relatórios
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
                      <v-dialog v-model="dialog" max-width="600px">
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn
                            color="#C84634"
                            class="white--text"
                            dark
                            v-bind="attrs"
                            v-on="on"
                          >
                            Gerar CSV
                          </v-btn>
                        </template>
                        <v-card style="background-color: #272733">
                          <v-card-title>
                            <span class="text-h5 white--text"
                              >Escolher mês</span
                            >
                          </v-card-title>
                          <v-card-text>
                            <v-container>
                              <v-form
                                ref="form"
                                v-model="valid"
                                lazy-validation
                                @submit.prevent="gerarRelatorio(data, email)"
                              >
                                <v-container>
                                  <v-row justify="center">
                                    <v-col cols="24">
                                      <template>
                                        <v-row justify="center">
                                          <v-date-picker
                                            v-model="data"
                                            type="month"
                                            locale="brasil"
                                          ></v-date-picker>
                                        </v-row>
                                      </template>
                                    </v-col>
                                  </v-row>
                                  <v-row justify="center">
                                    <v-col cols="24">
                                      <span
                                        style="color: white; font-size: 18px"
                                        >E-mail</span
                                      >
                                      <v-text-field
                                        label="E-mail"
                                        v-model="email"
                                        :rules="regra_email"
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
                                      <v-btn text color="white" @click="close">
                                        Cancelar
                                      </v-btn>
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
                            </v-container>
                          </v-card-text>
                        </v-card>
                      </v-dialog>
                    </template>
                  </v-card-text></v-card
                >
              </div></v-layout
            ></v-flex
          ></v-layout
        ></v-container
      ></v-content
    ></v-app
  >
</template>

<script>
import Relatorio from "../services/relatorio";
import Swal from "sweetalert2";

export default {
  data: () => ({
    valid: true,
    dialog: false,
    data: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    regra_email: [
      (v) => !!v || "O e-mail é obrigatório",
      (v) => /.+@.+\..+/.test(v) || "E-mail inválido",
    ],
    email: "tairikjohnny@gmail.com",
    lista_de_relatorios: [],
    labels: ["12am", "3am", "6am", "9am", "12pm", "3pm", "6pm", "9pm"],
  }),
  methods: {
    // Método pra gerar o relatorio
    gerarRelatorio(data, email) {
      Relatorio.gerar_relatorio(data, email)
        .then((resposta_gera_relatorio) => {
          this.lista_de_relatorios = resposta_gera_relatorio.data;
          console.log(this.lista_de_relatorios);
          Swal.fire(
            "Sucesso",
            "Relatório do mês gerado com sucesso!!!",
            "success"
          );
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao gerar o relatório! - Erro: " + e.response.data.error,
            "error"
          );
        });
      this.close();
    },
    validate() {
      this.$refs.form.validate();
    },
    // Método que vai fechar o modal "dialog"
    close() {
      this.dialog = false;
      this.evento = {};
    },
  },
};
</script>

<style>
#relatorios {
  background-color: #181820;
}
</style>
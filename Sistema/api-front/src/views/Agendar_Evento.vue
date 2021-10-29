<template>
  <v-app id="agendar-evento">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md12>
            <h1 style="text-align: center; color: white; margin-top: 15px">
              Agendar Evento
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
                        :headers="headers"
                        :items="lista_de_eventos"
                        sort-by="calories"
                        class="elevation-1"
                      >
                        <template v-slot:top>
                          <v-toolbar flat>
                            <v-toolbar-title>Lista de Eventos</v-toolbar-title>
                            <v-divider class="mx-4" inset vertical></v-divider>
                            <v-spacer></v-spacer>
                            <v-dialog v-model="dialog" max-width="750px">
                              <template v-slot:activator="{ on, attrs }">
                                <v-btn
                                  color="#C84634"
                                  class="white--text"
                                  dark
                                  v-bind="attrs"
                                  v-on="on"
                                >
                                  Novo Evento
                                </v-btn>
                              </template>
                              <v-card style="background-color: #272733">
                                <v-card-title>
                                  <span class="text-h5 white--text">{{
                                    formTitle
                                  }}</span>
                                </v-card-title>
                                <v-card-text>
                                  <v-container>
                                    <v-form
                                      ref="form"
                                      v-model="valid"
                                      lazy-validation
                                      @submit.prevent="cadastrar_evento"
                                    >
                                      <v-container>
                                        <v-row justify="center">
                                          <v-col cols="3">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                                display: flex;
                                                flex-direction: row;
                                                justify-content: center;
                                                align-items: center;
                                              "
                                              >Data do Evento</span
                                            >
                                            <div
                                              style="
                                                display: flex;
                                                flex-direction: row;
                                                justify-content: center;
                                                align-items: center;
                                                margin-top: 20px;
                                              "
                                            >
                                              <v-menu
                                                ref="menu"
                                                v-model="menu"
                                                :close-on-content-click="false"
                                                :return-value.sync="date"
                                                transition="scale-transition"
                                                offset-y
                                                min-width="290px"
                                              >
                                                <template
                                                  v-slot:activator="{
                                                    on,
                                                    attrs,
                                                  }"
                                                >
                                                  <v-text-field
                                                    v-model="
                                                      computedDateFormatted
                                                    "
                                                    prepend-inner-icon="mdi-calendar"
                                                    readonly
                                                    v-bind="attrs"
                                                    v-on="on"
                                                    outlined
                                                    background-color="#A9A9A9"
                                                  ></v-text-field>
                                                </template>
                                                <v-date-picker
                                                  v-model="date"
                                                  no-title
                                                  scrollable
                                                >
                                                  <v-spacer></v-spacer>
                                                  <v-btn
                                                    text
                                                    color="primary"
                                                    @click="menu = false"
                                                    >Cancel</v-btn
                                                  >
                                                  <v-btn
                                                    text
                                                    color="primary"
                                                    @click="
                                                      $refs.menu.save(date)
                                                    "
                                                    >OK</v-btn
                                                  >
                                                </v-date-picker>
                                              </v-menu>
                                            </div>
                                          </v-col>
                                        </v-row>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                                display: flex;
                                                flex-direction: row;
                                                justify-content: center;
                                                align-items: center;
                                              "
                                              >Hora Inicio</span
                                            >
                                            <!-- <v-time-picker
                                              v-model="evento.horainicio"
                                              :allowed-hours="allowedHours"
                                              :allowed-minutes="allowedMinutes"
                                              class="mt-4"
                                              format="24hr"
                                              use-seconds
                                            ></v-time-picker> -->
                                            <v-menu
                                              ref="menu"
                                              v-model="menu2"
                                              :close-on-content-click="false"
                                              :nudge-right="40"
                                              :return-value.sync="horainicio"
                                              transition="scale-transition"
                                              offset-y
                                              max-width="290px"
                                              min-width="290px"
                                            >
                                              <template
                                                v-slot:activator="{ on, attrs }"
                                              >
                                                <v-text-field
                                                  v-model="evento.horainicio"
                                                  label="Picker in menu"
                                                  prepend-icon="mdi-clock-time-four-outline"
                                                  readonly
                                                  v-bind="attrs"
                                                  v-on="on"
                                                ></v-text-field>
                                              </template>
                                              <v-time-picker
                                                v-model="evento.horainicio"
                                                :allowed-hours="allowedHours"
                                                :allowed-minutes="
                                                  allowedMinutes
                                                "
                                                class="mt-4"
                                                format="24hr"
                                                use-seconds
                                                v-if="menu2"
                                                full-width
                                                @click:second="
                                                  $refs.menu.save(evento.horainicio)
                                                "
                                              ></v-time-picker>
                                            </v-menu>
                                          </v-col>
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                                display: flex;
                                                flex-direction: row;
                                                justify-content: center;
                                                align-items: center;
                                              "
                                              >Hora Fim</span
                                            >
                                            <!-- <v-time-picker
                                              v-model="evento.horafim"
                                              :allowed-hours="allowedHours"
                                              :allowed-minutes="allowedMinutes"
                                              class="mt-4"
                                              format="24hr"
                                              use-seconds
                                            ></v-time-picker> -->
                                            <v-menu
                                              ref="menu"
                                              v-model="menu3"
                                              :close-on-content-click="false"
                                              :nudge-right="40"
                                              :return-value.sync="horafim"
                                              transition="scale-transition"
                                              offset-y
                                              max-width="290px"
                                              min-width="290px"
                                            >
                                              <template
                                                v-slot:activator="{ on, attrs }"
                                              >
                                                <v-text-field
                                                  v-model="evento.horafim"
                                                  label="Picker in menu"
                                                  prepend-icon="mdi-clock-time-four-outline"
                                                  readonly
                                                  v-bind="attrs"
                                                  v-on="on"
                                                ></v-text-field>
                                              </template>
                                              <v-time-picker
                                                v-model="evento.horafim"
                                                :allowed-hours="allowedHours"
                                                :allowed-minutes="
                                                  allowedMinutes
                                                "
                                                class="mt-4"
                                                format="24hr"
                                                use-seconds
                                                v-if="menu3"
                                                full-width
                                                @click:second="
                                                  $refs.menu.save(evento.horafim)
                                                "
                                              ></v-time-picker>
                                            </v-menu>
                                          </v-col>
                                        </v-row>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Espaço</span
                                            >
                                            <v-select
                                              :items="espaco"
                                              label="Espaço"
                                              v-model="evento"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                              :rules="[
                                                (v) =>
                                                  !!v ||
                                                  'O espaço é obrigatório',
                                              ]"
                                            ></v-select>
                                          </v-col>
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Tipo de Evento</span
                                            >
                                            <v-select
                                              :items="tipo_evento"
                                              label="Tipo de Evento"
                                              v-model="evento"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                              ::rules="[
                                                (v) =>
                                                  !!v.toString() ||
                                                  'O tipo de evento é obrigatório',
                                              ]"
                                            ></v-select>
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
import Evento from "../services/evento";
import Swal from "sweetalert2";

export default {
  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    menu: false,
    menu2: false,
    menu3: false,

    time: null,
    time1: null,

    horainicio: "",
    horafim: "",
    tipo: "",

    // Validando se os campos do formulario estão preenchidos e se são validos
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

    // Criando os arrays que vão armazenar os conteudos dos selects de Status do Usuario e Tipo de Usuario
    tipo_evento: ["1 - SMB", "2 - Enterprise", "3 - Workshop", "4 - Palestra"],
    espaco: ["Openspace", "Lounge"],

    // Array aonde vai ser armazenado a lista de usuarios
    lista_de_eventos: [],

    // Criando o objeto que vai ser feito o POST
    evento: {
      dataeven: "",
      formato: "Pequeno",
      tipo: "Palestra",
      status: "PENDENTE",
      usucodcria: "86",
      usucodaprova: "85",
      horainicio: "",
      horafim: "",
    },

    // Variaveis referentes aos modais que abrem na tela, se for false ele não aparece na tela, se for true ele aparece na tela
    dialog: false,
    dialogDelete: false,

    // Array que vai armazenar as colunas da tabela
    headers: [
      {
        text: "CÓDIGO",
        align: "start",
        value: "codeven",
      },
      { text: "DATA EVENTO", value: "dataeven" },
      { text: "FORMATO", value: "formato" },
      { text: "TIPO", value: "tipo" },
      { text: "STATUS", value: "status" },
      { text: "DATA CRIAÇÃO", value: "datacria" },
      { text: "USUÁRIO CRIAÇÃO", value: "usucodcria" },
      { text: "USUÁRIO APROVAÇÃO", value: "usucodaprova" },
      { text: "HORA INICIO", value: "horainicio" },
      { text: "HORA FIM", value: "horafim" },
      { text: "Actions", value: "actions", sortable: false },
    ],

    // Vairavel que é usada pra identificar se o modal é de cadastro de usuario ou de edição do usuario
    editedIndex: -1,
  }),

  computed: {
    // Retornando "Novo Usuário" ou "Editar Usuário" dependendo de qual modal estiver
    // "Novo Usuário" = o usuario clicou no botão pra cadastrar um novo usuario
    // "Editar Usuário" = o usuario clicou no botão pra editar um usuario
    formTitle() {
      return this.editedIndex === -1 ? "Novo Evento" : "Editar Evento";
    },
    computedDateFormatted() {
      return this.formatDate(this.date);
    },
  },

  watch: {
    // Fechando os modais
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
    date() {
      this.dateFormatted = this.formatDate(this.date);
    },
  },

  mounted() {
    // Chamando o método exibir_evento()
    this.exibir_evento();
  },

  methods: {
    // Método de cadastro de evento
    cadastrar_evento() {
      // Se o evento não tiver um "codeven" significa que esse evento não existe então ele vai pra resquest de cadastro
      if (!this.evento.codeven) {
        this.evento.dataeven = this.dateFormatted;
        Evento.salvar_evento(this.evento)
          .then((resposta_cadastro_evento) => {
            this.evento = {};
            Swal.fire(
              "Sucesso",
              "Evento " +
                resposta_cadastro_evento.data.codeven +
                " cadastrado com sucesso!!!",
              "success"
            );
            this.exibir_evento();
          })
          .catch((e) => {
            Swal.fire(
              "Oops...",
              "Erro ao cadastrar o evento! - Erro: " + e.response.data.error,
              "error"
            );
          });
        this.close();
      } else {
        // Método de atualizar o evento
        // Se o evento já tiver um "codeven" ele já existe então ele vai pra request de atualizar
        Evento.atualizar_evento(this.evento)
          .then((resposta_atualizar_evento) => {
            this.evento = {};
            Swal.fire(
              "Sucesso",
              "Evento " +
                resposta_atualizar_evento.data.codeven +
                " atualizado com sucesso!!!",
              "success"
            );
            this.exibir_evento();
          })
          .catch((e) => {
            Swal.fire(
              "Oops...",
              "Erro ao atualizar o evento! - Erro: " + e.response.data.error,
              "error"
            );
          });
        this.close();
      }
    },
    // Método pra exibir os eventos
    exibir_evento() {
      Evento.listar_eventos()
        .then((resposta_lista_evento) => {
          this.lista_de_eventos = resposta_lista_evento.data;
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao carregar a tabela de eventos! - Erro: " +
              e.response.data.error,
            "error"
          );
        });
    },

    // Método que valida se os campos estão preenchidos, se não estiverem ele bloqueia o botão CADASTRAR
    validate() {
      this.$refs.form.validate();
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto usuario
    editar_usuario(usuario) {
      this.editedIndex = this.lista_de_eventos.indexOf(usuario);
      this.usuario = Object.assign({}, usuario);
      this.dialog = true;
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto usuario
    deleteItem(usuario) {
      this.editedIndex = this.lista_de_eventos.indexOf(usuario);
      this.usuario = Object.assign({}, usuario);
      this.dialogDelete = true;
    },

    // Método pra excluir os usuarios
    deletar_usuario(usuario) {
      Evento.excluir_usuario(usuario)
        .then((resposta_excluir_usuario) => {
          Swal.fire("Sucesso", "Usuário excluido com sucesso!!!", "success");
          resposta_excluir_usuario;
          this.exibir_evento();
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao excluir o usuário! - Erro: " + e.response.data.error,
            "error"
          );
        });
      this.closeDelete();
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
    formatDate(date) {
      if (!date) return null;

      const [year, month, day] = date.split("-");
      return `${day}/${month}/${year}`;
    },
  },
};
</script>

<style>
#agendar-evento {
  background-color: #181820;
}
</style>
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
                                              <!-- <v-menu
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
                                              </v-menu> -->
                                            </div>
                                            <datetime
                                              format="DD/MM/YYYY"
                                              width="50px"
                                              v-model="evento.dataeven"
                                            ></datetime>
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
                                            <v-time-picker
                                              v-model="evento.horainicio"
                                              :allowed-hours="allowedHours"
                                              :allowed-minutes="allowedMinutes"
                                              class="mt-4"
                                              format="24hr"
                                              use-seconds
                                            ></v-time-picker>
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
                                            <v-time-picker
                                              v-model="evento.horafim"
                                              :allowed-hours="allowedHours"
                                              :allowed-minutes="allowedMinutes"
                                              class="mt-4"
                                              format="24hr"
                                              use-seconds
                                            ></v-time-picker>
                                          </v-col>
                                        </v-row>
                                        <v-row justify="center">
                                          <!-- <v-col cols="24">
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
                                          </v-col> -->
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Prioridade dos Eventos</span
                                            >
                                            <v-select
                                              :items="tipo_evento"
                                              label="Prioridade dos Eventos"
                                              v-model="evento.tipo"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                              ::rules="[
                                                (v) =>
                                                  !!v.toString() ||
                                                  'A prioridade dos eventos é obrigatório',
                                              ]"
                                            ></v-select>
                                          </v-col>
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Status Evento</span
                                            >
                                            <v-select
                                              :items="status_evento"
                                              label="Status do Evento"
                                              v-model="evento.status"
                                              single-line
                                              solo
                                              required
                                              dense
                                              background-color="#A9A9A9"
                                              ::rules="[
                                                (v) =>
                                                  !!v.toString() ||
                                                  'O status do evento é obrigatório',
                                              ]"
                                            ></v-select>
                                          </v-col>
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Formato</span
                                            >
                                            <v-text-field
                                              label="Formato"
                                              v-model="evento.formato"
                                              single-line
                                              solo
                                              dense
                                              background-color="#A9A9A9"
                                            ></v-text-field>
                                          </v-col>
                                        </v-row>
                                        <v-row justify="center">
                                          <!-- <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Fornecedores</span
                                            >
                                            <v-select
                                              :items="items_fornecedores"
                                              label="Fornecedores"
                                              :search-input="
                                                retornaFornecedores
                                              "
                                              v-model="evento.fornecedores"
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
                                          </v-col> -->
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
                                    @click="deletar_evento(evento)"
                                    >Sim</v-btn
                                  >
                                  <v-spacer></v-spacer>
                                </v-card-actions>
                              </v-card>
                            </v-dialog>
                          </v-toolbar>
                        </template>
                        <template v-slot:item.actions="{ item }">
                          <v-icon class="mr-2" @click="editar_evento(item)">
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
                        :headers="headersFornecedores"
                        :items="lista_de_fornecedores_adicionados_evento"
                        sort-by="calories"
                        class="elevation-1"
                      >
                        <template v-slot:top>
                          <v-toolbar flat>
                            <v-toolbar-title
                              >Lista de Fornecedores</v-toolbar-title
                            >
                            <v-divider class="mx-4" inset vertical></v-divider>
                            <v-spacer></v-spacer>
                            <v-dialog
                              v-model="dialogFornecedor"
                              max-width="500px"
                            >
                              <template v-slot:activator="{ on, attrs }">
                                <v-btn
                                  color="#C84634"
                                  class="white--text"
                                  dark
                                  v-bind="attrs"
                                  v-on="on"
                                >
                                  Adicionar Fornecedor
                                </v-btn>
                              </template>
                              <v-card style="background-color: #272733">
                                <v-card-title>
                                  <span class="text-h5 white--text">{{
                                    formTitleFornecedores
                                  }}</span>
                                </v-card-title>
                                <v-card-text>
                                  <v-container>
                                    <v-form
                                      ref="form"
                                      v-model="validFornecedor"
                                      lazy-validation
                                      @submit.prevent="
                                        adicionar_fornecedor_evento
                                      "
                                    >
                                      <v-container>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Código evento</span
                                            >
                                            <v-text-field
                                              label="Código evento"
                                              v-model="
                                                fornecedor_evento.codeven
                                              "
                                              :rules="[
                                                (v) =>
                                                  !!v ||
                                                  'O código do evento é obrigatório',
                                              ]"
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
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Código Fornecedor</span
                                            >
                                            <v-text-field
                                              label="Código Fornecedor"
                                              v-model="
                                                fornecedor_evento.forncod
                                              "
                                              :rules="[
                                                (v) =>
                                                  !!v ||
                                                  'O código do fornecedor é obrigatório',
                                              ]"
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
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Descrição</span
                                            >
                                            <v-textarea
                                              v-model="
                                                fornecedor_evento.descricao
                                              "
                                              auto-grow
                                              outlined
                                              rows="1"
                                              row-height="15"
                                              background-color="#A9A9A9"
                                            ></v-textarea>
                                          </v-col>
                                        </v-row>
                                        <v-row>
                                          <v-col></v-col>
                                          <v-col>
                                            <v-btn
                                              text
                                              color="white"
                                              @click="closeFornecedor"
                                            >
                                              Cancelar
                                            </v-btn>
                                          </v-col>
                                          <v-col>
                                            <v-btn
                                              color="#C84634"
                                              class="white--text mr-4"
                                              type="submit"
                                              :disabled="!validFornecedor"
                                              @click="validateFornecedor"
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
                            <v-dialog
                              v-model="dialogDeleteFornecedor"
                              max-width="540px"
                            >
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
                                    @click="closeDeleteFornecedor"
                                  >
                                    Cancelar
                                  </v-btn>
                                  <v-btn
                                    color="#C84634"
                                    class="white--text mr-4"
                                    @click="
                                      deletar_fornecedor_adicionado_evento(
                                        evento
                                      )
                                    "
                                    >Sim</v-btn
                                  >
                                  <v-spacer></v-spacer>
                                </v-card-actions>
                              </v-card>
                            </v-dialog>
                            <v-dialog
                              v-model="dialogEditarFornecedor"
                              max-width="500px"
                            >
                              <v-card style="background-color: #272733">
                                <v-card-title>
                                  <span class="text-h5 white--text"
                                    >Editar Fonecedor</span
                                  >
                                </v-card-title>
                                <v-card-text>
                                  <v-container>
                                    <v-form
                                      ref="form"
                                      v-model="validFornecedor"
                                      lazy-validation
                                      @submit.prevent="
                                        editar_fornecedor_evento_metodo
                                      "
                                    >
                                      <v-container>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Código evento</span
                                            >
                                            <v-text-field
                                              label="Código evento"
                                              v-model="
                                                fornecedor_evento.codeven
                                              "
                                              :rules="[
                                                (v) =>
                                                  !!v ||
                                                  'O código do evento é obrigatório',
                                              ]"
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
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Código Fornecedor</span
                                            >
                                            <v-text-field
                                              label="Código Fornecedor"
                                              v-model="
                                                fornecedor_evento.forncod
                                              "
                                              :rules="[
                                                (v) =>
                                                  !!v ||
                                                  'O código do fornecedor é obrigatório',
                                              ]"
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
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Descrição</span
                                            >
                                            <v-textarea
                                              v-model="
                                                fornecedor_evento.descricao
                                              "
                                              auto-grow
                                              outlined
                                              rows="1"
                                              row-height="15"
                                              background-color="#A9A9A9"
                                            ></v-textarea>
                                          </v-col>
                                        </v-row>
                                        <v-row>
                                          <v-col></v-col>
                                          <v-col>
                                            <v-btn
                                              text
                                              color="white"
                                              @click="closeEditarFornecedor"
                                            >
                                              Cancelar
                                            </v-btn>
                                          </v-col>
                                          <v-col>
                                            <v-btn
                                              color="#C84634"
                                              class="white--text mr-4"
                                              type="submit"
                                              :disabled="!validFornecedor"
                                              @click="validateFornecedor"
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
                            <!-- <v-dialog
                              v-model="dialogDeleteFornecedor"
                              max-width="540px"
                            >
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
                                    @click="closeDeleteFornecedor"
                                  >
                                    Cancelar
                                  </v-btn>
                                  <v-btn
                                    color="#C84634"
                                    class="white--text mr-4"
                                    @click="
                                      deletar_fornecedor_adicionado_evento(
                                        evento
                                      )
                                    "
                                    >Sim</v-btn
                                  >
                                  <v-spacer></v-spacer>
                                </v-card-actions>
                              </v-card>
                            </v-dialog> -->
                          </v-toolbar>
                        </template>
                        <template v-slot:item.actions="{ item }">
                          <v-icon
                            class="mr-2"
                            @click="editar_fornecedor_evento(item)"
                          >
                            mdi-pencil
                          </v-icon>
                          <v-icon @click="deleteItemFornecedorEvento(item)">
                            mdi-delete
                          </v-icon>
                        </template>
                      </v-data-table>
                    </template>
                  </v-card-text>
                </v-card>
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
                        :headers="headersConvidados"
                        :items="lista_de_convidados"
                        sort-by="calories"
                        class="elevation-1"
                      >
                        <template v-slot:top>
                          <v-toolbar flat>
                            <v-toolbar-title
                              >Lista de Convidados</v-toolbar-title
                            >
                            <v-divider class="mx-4" inset vertical></v-divider>
                            <v-spacer></v-spacer>
                            <v-dialog
                              v-model="dialogConvidados"
                              max-width="500px"
                            >
                              <template v-slot:activator="{ on, attrs }">
                                <v-btn
                                  color="#C84634"
                                  class="white--text"
                                  dark
                                  v-bind="attrs"
                                  v-on="on"
                                >
                                  Adicionar Convidados
                                </v-btn>
                              </template>
                              <v-card style="background-color: #272733">
                                <v-card-title>
                                  <span class="text-h5 white--text">{{
                                    formTitleConvidados
                                  }}</span>
                                </v-card-title>
                                <v-card-text>
                                  <v-container>
                                    <v-form
                                      ref="form"
                                      v-model="validConvidados"
                                      lazy-validation
                                      @submit.prevent="adicionar_convidado"
                                    >
                                      <v-container>
                                        <v-row justify="center">
                                          <v-col cols="24">
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >Nome Convidado</span
                                            >
                                            <v-text-field
                                              label="Nome Convidado"
                                              v-model="evento.nome"
                                              :rules="[
                                                (v) =>
                                                  !!v ||
                                                  'O nome do convidado é obrigatório',
                                              ]"
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
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >E-mail Convidado</span
                                            >
                                            <v-text-field
                                              label="E-mail Convidado"
                                              v-model="evento.email"
                                              :rules="regra_email_convidado"
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
                                            <span
                                              style="
                                                color: white;
                                                font-size: 18px;
                                              "
                                              >CPF Convidado</span
                                            >
                                            <v-text-field
                                              label="CPF Convidado"
                                              v-model="evento.email"
                                              :rules="[
                                                (v) =>
                                                  !!v ||
                                                  'O CPF do convidado é obrigatório',
                                              ]"
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
                                            <v-btn
                                              text
                                              color="white"
                                              @click="closeConvidado"
                                            >
                                              Cancelar
                                            </v-btn>
                                          </v-col>
                                          <v-col>
                                            <v-btn
                                              color="#C84634"
                                              class="white--text mr-4"
                                              type="submit"
                                              :disabled="!validConvidados"
                                              @click="validateConvidados"
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
                            <v-dialog
                              v-model="dialogDeleteConvidado"
                              max-width="540px"
                            >
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
                                    @click="closeDeleteConvidado"
                                  >
                                    Cancelar
                                  </v-btn>
                                  <v-btn
                                    color="#C84634"
                                    class="white--text mr-4"
                                    @click="deletar_convidado(convidado)"
                                    >Sim</v-btn
                                  >
                                  <v-spacer></v-spacer>
                                </v-card-actions>
                              </v-card>
                            </v-dialog>
                          </v-toolbar>
                        </template>
                        <template v-slot:item.actions="{ item }">
                          <v-icon class="mr-2" @click="editar_convidado(item)">
                            mdi-pencil
                          </v-icon>
                          <v-icon @click="deleteItemConvidado(item)">
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
import datetime from "vuejs-datetimepicker";
// import Fornecedor from "../services/fornecedor";

export default {
  components: { datetime },
  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    menu: false,

    dataeven: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),

    horainicio: "",
    horafim: "",
    tipo: "",

    // Validando se os campos do formulario estão preenchidos e se são validos
    valid: true,
    validFornecedor: true,
    validConvidados: true,
    regra_email_convidado: [
      (v) => !!v || "O e-mail do convidado é obrigatório",
      (v) => /.+@.+\..+/.test(v) || "E-mail inválido",
      //(v) => /^[a-z0-9.]+@oracle.com$/.test(v) || "E-mail inválido",
    ],

    // Criando os arrays que vão armazenar os conteudos dos selects de Status do Usuario e Tipo de Usuario
    tipo_evento: ["SMB", "Enterprise", "Workshop", "Palestra"],
    espaco: ["Openspace", "Lounge"],
    status_evento: ["PENDENTE", "APROVADO", "REPROVADO"],
    items_fornecedores: [],

    // Arrays aonde vão ser armazenados os retornos GET
    lista_de_eventos: [],
    lista_de_fornecedores_adicionados_evento: [],
    lista_de_convidados: [],

    // Criando o objeto que vai ser feito o POST
    evento: {
      dataeven: "",
      formato: "",
      tipo: "",
      status: "",
      usucodcria: "1",
      usucodaprova: "1",
      horainicio: "",
      horafim: "",
    },
    fornecedor_evento: {
      codeven: "",
      descricao: "",
      forncod: "",
      id: {},
    },
    convidado: {},

    // Variaveis referentes aos modais que abrem na tela, se for false ele não aparece na tela, se for true ele aparece na tela
    dialog: false,
    dialogDelete: false,
    dialogFornecedor: false,
    dialogDeleteFornecedor: false,
    dialogEditarFornecedor: false,
    dialogConvidados: false,
    dialogDeleteConvidado: false,

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
      // { text: "USUÁRIO CRIAÇÃO", value: "usucodcria" },
      // { text: "USUÁRIO APROVAÇÃO", value: "usucodaprova" },
      { text: "HORA INICIO", value: "horainicio" },
      { text: "HORA FIM", value: "horafim" },
      { text: "Actions", value: "actions", sortable: false },
    ],
    headersFornecedores: [
      {
        text: "CÓDIGO EVENTO",
        align: "start",
        value: "codeven",
      },
      { text: "CÓDIGO FORNECEDOR", value: "forncod" },
      { text: "DESCRIÇÃO", value: "descricao" },
      { text: "Actions", value: "actions", sortable: false },
    ],
    headersConvidados: [
      {
        text: "CÓDIGO CONVIDADO",
        align: "start",
        value: "codeven",
      },
      { text: "NOME CONVIDADO", value: "dataeven" },
      { text: "E-MAIL CONVIDADO", value: "formato" },
      { text: "CPF CONVIDADO", value: "formato" },
      { text: "Actions", value: "actions", sortable: false },
    ],

    // Vairavel que é usada pra identificar se o modal é de cadastro de usuario ou de edição do usuario
    editedIndex: -1,
    editedIndexFornecedores: -1,
    editedIndexConvidados: -1,
  }),

  computed: {
    // Retornando "Novo Usuário" ou "Editar Usuário" dependendo de qual modal estiver
    // "Novo Usuário" = o usuario clicou no botão pra cadastrar um novo usuario
    // "Editar Usuário" = o usuario clicou no botão pra editar um usuario
    formTitle() {
      return this.editedIndex === -1 ? "Novo Evento" : "Editar Evento";
    },
    formTitleFornecedores() {
      return this.editedIndexFornecedores === -1
        ? "Adicionar Fornecedores"
        : "Editar Fornecedores";
    },
    formTitleConvidados() {
      return this.editedIndexConvidados === -1
        ? "Adicionar Convidados"
        : "Editar Convidados";
    },
    computedDateFormatted() {
      return this.formatDate(this.date);
    },
    retornaFornecedores: function () {
      return this.exibir_fornecedor();
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
    dialogFornecedor(val) {
      val || this.closeFornecedor();
    },
    dialogDeleteFornecedor(val) {
      val || this.closeDeleteFornecedor();
    },
    dialogConvidados(val) {
      val || this.closeConvidado();
    },
    dialogDeleteConvidado(val) {
      val || this.closeDeleteConvidado();
    },
    date() {
      this.dateFormatted = this.formatDate(this.date);
    },
  },

  mounted() {
    // Chamando o método exibir_evento()
    this.exibir_evento();
    this.exibir_fornecedor_evento();
  },

  methods: {
    // Método de cadastro de evento
    cadastrar_evento() {
      // Se o evento não tiver um "codeven" significa que esse evento não existe então ele vai pra resquest de cadastro
      if (!this.evento.codeven) {
        // this.evento.dataeven = this.dateFormatted;
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
    // Método pra excluir os eventos
    deletar_evento(evento) {
      Evento.excluir_evento(evento)
        .then((resposta_excluir_evento) => {
          Swal.fire("Sucesso", "Evento excluido com sucesso!!!", "success");
          resposta_excluir_evento;
          this.exibir_evento();
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao excluir o evento! - Erro: " + e.response.data.error,
            "error"
          );
        });
      this.closeDelete();
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
    // Método pra retornar somente os nomes dos fornecedores
    // exibir_fornecedor() {
    //   Fornecedor.listar_fornecedor_all()
    //     .then((resposta_lista_fornecedor) => {
    //       this.items_fornecedores = resposta_lista_fornecedor.data;
    //       let fornecedores = [];
    //       resposta_lista_fornecedor.data.forEach((forn) =>
    //         fornecedores.push(forn.nomeforn)
    //       );
    //       this.items_fornecedores = fornecedores;
    //     })
    //     .catch((e) => {
    //       Swal.fire(
    //         "Oops...",
    //         "Erro ao retornar os nomes dos fornecedores! - Erro: " +
    //           e.response.data.error,
    //         "error"
    //       );
    //     });
    // },

    // Método de cadastro do FORNECEDOR DO EVENTO
    adicionar_fornecedor_evento() {
      Evento.salvar_fornecedor_evento(this.fornecedor_evento)
        .then((resposta_cadastro_fornecedor_evento) => {
          this.fornecedor_evento = {};
          Swal.fire(
            "Sucesso",
            "Fornecedor " +
              resposta_cadastro_fornecedor_evento.data.forncod +
              " adicionado com sucesso ao evento " +
              resposta_cadastro_fornecedor_evento.data.codeven,
            "success"
          );
          this.exibir_fornecedor_evento();
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao adicionar o fornecedor ao evento! - Erro: " +
              e.response.data.error,
            "error"
          );
        });
      this.closeFornecedor();
    },

    // EDITAR FORNECEDOR DO EVENTO
    editar_fornecedor_evento_metodo() {
      Evento.atualizar_fornecedor_evento(this.fornecedor_evento)
        .then((resposta_atualizar_fornecedor_evento) => {
          this.fornecedor_evento = {};
          Swal.fire(
            "Sucesso",
            "Fornecedor " +
              resposta_atualizar_fornecedor_evento.data.forncod +
              " do evento " +
              resposta_atualizar_fornecedor_evento.data.codeven +
              " atualizado com sucesso!!!",
            "success"
          );
          this.exibir_fornecedor_evento();
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao atualizar o fornecedor do evento! - Erro: " +
              e.response.data.error,
            "error"
          );
        });
      this.closeEditarFornecedor();
    },

    // Método pra exibir os fornecedores dos eventos
    exibir_fornecedor_evento() {
      Evento.listar_fornecedor_eventos()
        .then((resposta_lista_fornecedor_evento) => {
          this.lista_de_fornecedores_adicionados_evento =
            resposta_lista_fornecedor_evento.data;
        })
        .catch((e) => {
          Swal.fire(
            "Oops...",
            "Erro ao carregar a tabela de fornecedores dos eventos! - Erro: " +
              e.response.data.error,
            "error"
          );
        });
    },

    // Método que valida se os campos estão preenchidos, se não estiverem ele bloqueia o botão CADASTRAR
    validate() {
      this.$refs.form.validate();
    },
    validateFornecedor() {
      this.$refs.form.validateFornecedor();
    },
    validateConvidados() {
      this.$refs.form.validateConvidados();
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto evento
    editar_evento(evento) {
      this.editedIndex = this.lista_de_eventos.indexOf(evento);
      this.evento = Object.assign({}, evento);
      this.dialog = true;
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto fornecedor
    editar_fornecedor_evento(fornecedor_evento) {
      this.editedIndexFornecedores =
        this.lista_de_fornecedores_adicionados_evento.indexOf(fornecedor_evento);
      this.fornecedor_evento = Object.assign({}, fornecedor_evento);
      this.dialogEditarFornecedor = true;
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto convidado
    editar_convidado(convidado) {
      this.editedIndexConvidados = this.lista_de_convidados.indexOf(convidado);
      this.convidado = Object.assign({}, convidado);
      this.dialogConvidados = true;
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto evento
    deleteItem(evento) {
      this.editedIndex = this.lista_de_eventos.indexOf(evento);
      this.evento = Object.assign({}, evento);
      this.dialogDelete = true;
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto fornecedor
    deleteItemFornecedorEvento(fornecedor) {
      this.editedIndexFornecedores =
        this.lista_de_fornecedores_adicionados_evento.indexOf(fornecedor);
      this.fornecedor = Object.assign({}, fornecedor);
      this.dialogDeleteFornecedor = true;
    },

    // Método que vai recuparar os dados da tabela e armazenar no objeto convidado
    deleteItemConvidado(convidado) {
      this.editedIndexConvidados = this.lista_de_convidados.indexOf(convidado);
      this.convidado = Object.assign({}, convidado);
      this.dialogDeleteConvidado = true;
    },

    // Método que vai fechar o modal "dialog"
    close() {
      this.dialog = false;
      this.evento = {};
    },

    // Método que vai fechar o modal "dialogFornecedor"
    closeFornecedor() {
      this.dialogFornecedor = false;
      this.fornecedor = {};
    },

    // Método que vai fechar o modal "dialogFornecedor"
    closeEditarFornecedor() {
      this.dialogEditarFornecedor = false;
      this.fornecedor_evento = {};
    },

    // Método que vai fechar o modal "dialogConvidados"
    closeConvidado() {
      this.dialogConvidados = false;
      this.convidado = {};
    },

    // Método que vai fechar o modal "dialogDelete"
    closeDelete() {
      this.dialogDelete = false;
      this.evento = {};
    },

    // Método que vai fechar o modal "dialogDeleteFornecedor"
    closeDeleteFornecedor() {
      this.dialogDeleteFornecedor = false;
      this.fornecedor = {};
    },

    // Método que vai fechar o modal "dialogDeleteConvidado"
    closeDeleteConvidado() {
      this.dialogDeleteConvidado = false;
      this.convidado = {};
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
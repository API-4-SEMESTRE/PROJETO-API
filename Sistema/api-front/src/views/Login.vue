<template>
   <v-app id="login">
      <v-content>
         <v-container fluid fill-height>
            <v-layout align-center justify-center>
               <v-flex xs12 sm8 md4>
                  <v-card class="elevation-12">
                     <v-card-text>
                        <v-form
                        ref="form"
                        lazy-validation
                        @submit.prevent="do_login">
                           <v-text-field
                              v-model="usuario.email"
                              name="email"
                              label="email"
                              type="text"
                           ></v-text-field>
                           <v-text-field
                              v-model="usuario.senha"
                              id="senha"
                              name="senha"
                              label="senha"
                              type="password"
                           ></v-text-field>
                               <v-btn block color="#c74634" type="submit">Login</v-btn>
                        </v-form>
                     </v-card-text>
                  </v-card>
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
            senha: ""
        }
    }),
    methods: {
      do_login() {
        Usuario.login(this.usuario)
            .then((res) => {
                if (res.data.message === false) {
                  Swal.fire(
                      "Oops...",
                      "Usuário ou senha invalidos",
                      "error"
                  );
                }
                else {
                    localStorage.setItem("returnLogin", res.data.message);
                    this.$router.replace('home');

                }
            })
            .catch(() => {
              Swal.fire(
                  "Oops...",
                  "Usuário ou senha invalidos",
                  "error"
              );
            });
      }
    }
}
</script>
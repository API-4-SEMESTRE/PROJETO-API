<template>
<div>
    <v-app-bar color="#c74634" dense dark style="border: solid 1px black">
    <v-avatar>
      <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" style="position: fixed; right:0px; float:right; margin-bottom: 0;">      
    </v-avatar>

    </v-app-bar>
 
    <v-app>
            <v-main >
                <v-form style="padding-top: 30px">
                    <v-container class= "ma-70" style="width:40%; border:solid  1px" >
                            <v-row justify="center" align="stretch" >
                                <v-col cols="24">
                                    <span style="padding-top: 8px"> Nome da Empresa </span>
                                    <v-text-field v-model="empresa" outlined :rules="rules" dense >  </v-text-field>
                                </v-col>
                            </v-row>
                            
                            <v-row>
                                <v-col cols="6">
                                    <span> CNPJ</span>
                                    <v-text-field v-model="cnpj" outlined  dense>  </v-text-field>
                                </v-col>
                                <v-col cols="6">
                                    <span> Ramo de Atividade </span>
                                    <v-text-field v-model="ramo" outlined  dense>  </v-text-field>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="6">
                                    <span> CEP </span>
                                    <v-text-field v-model="cep" outlined  dense>  </v-text-field>
                                </v-col>
                                <v-col cols="6">
                                    <span> Rua </span>
                                    <v-text-field v-model="rua" outlined  dense>  </v-text-field>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="6" md="4">
                                    <span> Bairro</span>
                                    <v-text-field v-model="bairro" outlined  dense>  </v-text-field>
                                </v-col>
                                <v-col cols="6" md="4">
                                    <span> Cidade </span>
                                    <v-text-field v-model="cidade" outlined  dense>  </v-text-field>
                                </v-col>
                                 <v-col cols="6" md="4">
                                    <span> Estado </span>
                                    <v-text-field v-model="estado" outlined  dense>  </v-text-field>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="6">
                                    <span> Numero </span>
                                    <v-text-field v-model="numero" outlined  dense>  </v-text-field>
                                </v-col>
                                <v-col cols="6">
                                    <span> Complemento </span>
                                    <v-text-field v-model="complemento" outlined  dense>  </v-text-field>
                                </v-col>
                            </v-row>

                            <p> Contato responsável </p>

                            <v-row justify="center" align="stretch" >
                                <v-col cols="24">
                                    <span> Nome Completo</span>
                                    <v-text-field v-model="nome" outlined :rules="rules" dense>  </v-text-field>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="6">
                                    <span> Função </span>
                                    <v-text-field v-model="cargo" outlined  dense>  </v-text-field>
                                </v-col>
                                <v-col cols="6">
                                    <span> Telefone </span>
                                    <v-text-field v-model="telefone" outlined  dense>  </v-text-field>
                                </v-col>
                            </v-row>

                            <v-row justify="center" align="stretch" >
                                <v-col cols="24">
                                    <span> Email</span>
                                    <v-text-field v-model="email" outlined :rules="rules" dense>  </v-text-field>
                                </v-col>
                            </v-row>
                              
                            <v-btn class="mr-4"  type="submit"  style="display: flex; justify-content: flex-end; align-items: center; margin: auto; background-color: #c74634; color: white; border:solid 1px black">
                                Salvar
                            </v-btn>
                            
                    </v-container>
                </v-form>
            </v-main>
    </v-app>
</div>
</template>


<script>
  export default {
    data () {
      return {
        empresa: '',
        cnpj:'',
        ramo:'',
        cep:'',
        rua:'',
        bairro:'',
        cidade:'',
        estado:'',
        numero:'',
        complemento:''

     
      }
    },
  }
</script>

<style>

</style>
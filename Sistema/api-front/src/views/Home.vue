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
    <h1 style="text-align: center; margin-top: 20px; font-size: 45px">AgendHome</h1>
    <div id="cards-home">
      <v-row>
        <v-col>
          <v-card class="mx-auto" max-width="344" elevation="5">
            <v-card-text>
              <p class="text-h4 text--primary">Resumo</p>
              <div class="text--primary">
                O projeto a ser desenvolvido vai ser um sistema para
                agendamentos de eventos na Casa Oracle buscando solucionar os
                obstáculos da pandemia do Covid-19.
              </div>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col>
          <v-card class="mx-auto" max-width="344" elevation="5">
            <v-card-text>
              <p class="text-h4 text--primary">Projeto</p>
              <div class="text--primary">
                Projeto realizado em parceria com a Oracle Corporation uma
                empresa que atua na área da Computação e Informática juntamente
                com a Faculdade de Tecnologia de São José dos Campos Professor
                Jessen Vidal.
              </div>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col>
          <v-card class="mx-auto" max-width="344" elevation="5">
            <v-card-text>
              <p class="text-h4 text--primary">Desafio</p>
              <div class="text--primary">
                Desafio proposto pela Oracle Corporation - "No escritório de São
                Paulo, temos um espaço de inovação aberta chamado Casa Oracle,
                que é um local para realização de eventos internos/externos,
                workshops e palestras. Dentro do contexto das restrições
                sanitárias, nosso desafio é criar uma plataforma para o
                gerenciamento da utilização do espaço, onde nossos colaboradores
                poderão fazer a solicitação de agendamento, definindo os
                convidados e recursos necessários para o evento."
              </div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
      <h2 style="text-align: center; margin-top: 20px; font-size: 30px">Integrantes</h2>
      <v-row style="margin-top: 20px">
        <v-col>
          <v-card class="mx-auto" max-width="300" elevation="5">
            <v-img
              class="white--text align-end"
              height="300px"
              src="../images/tairik.jpeg"
            >
            </v-img>
            <v-card-text class="text--primary">
              <h2 style="text-align: center">Tairik Johnny</h2>
              <h3 style="text-align: center">Scrum Master</h3>
            </v-card-text>

            <v-card-actions>
              <div class="text-center">
                <v-btn
                  color="blue"
                  class="ma-2 white--text"
                  aling="center"
                  href="https://linkedin.com/in/tairik-nishimura/"
                  target="_blank"
                >
                  LinkedIn
                  <v-icon large> mdi-linkedin </v-icon>
                </v-btn>
              </div>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col>
          <v-card class="mx-auto" max-width="300" elevation="5">
            <v-img
              class="white--text align-end"
              height="300px"
              src="../images/rafaela.jpeg"
            >
            </v-img>
            <v-card-text class="text--primary">
              <h2 style="text-align: center">Rafaela Aparecida</h2>
              <h3 style="text-align: center">Product Owner</h3>
            </v-card-text>

            <v-card-actions>
              <div class="text-center">
                <v-btn
                  color="blue"
                  class="ma-2 white--text"
                  aling="center"
                  href="https://www.linkedin.com/in/rafaela-carnaval-70a506138/"
                  target="_blank"
                >
                  LinkedIn
                  <v-icon large> mdi-linkedin </v-icon>
                </v-btn>
              </div>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col>
          <v-card class="mx-auto" max-width="300" elevation="5">
            <v-img
              class="white--text align-end"
              height="300px"
              src="../images/tairik.jpeg"
            >
            </v-img>
            <v-card-text class="text--primary">
              <h2 style="text-align: center">Wallace Caetano</h2>
              <h3 style="text-align: center">Front-end Developer</h3>
            </v-card-text>

            <v-card-actions>
              <div class="text-center">
                <v-btn
                  color="blue"
                  class="ma-2 white--text"
                  aling="center"
                  href="https://linkedin.com/in/wallace-caetano/"
                  target="_blank"
                >
                  LinkedIn
                  <v-icon large> mdi-linkedin </v-icon>
                </v-btn>
              </div>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-card class="mx-auto" max-width="300" elevation="5">
            <v-img
              class="white--text align-end"
              height="300px"
              src="../images/fabricio.jpeg"
            >
            </v-img>
            <v-card-text class="text--primary">
              <h2 style="text-align: center">Fabrício Adriel</h2>
              <h3 style="text-align: center">Front-end Developer</h3>
            </v-card-text>

            <v-card-actions>
              <div class="text-center">
                <v-btn
                  color="blue"
                  class="ma-2 white--text"
                  aling="center"
                  href="https://www.linkedin.com/in/fabricioadriel/"
                  target="_blank"
                >
                  LinkedIn
                  <v-icon large> mdi-linkedin </v-icon>
                </v-btn>
              </div>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col>
          <v-card class="mx-auto" max-width="300" elevation="5">
            <v-img
              class="white--text align-end"
              height="300px"
              src="../images/devanir.jpeg"
            >
            </v-img>
            <v-card-text class="text--primary">
              <h2 style="text-align: center">Devanir Ramos</h2>
              <h3 style="text-align: center">Back-end Developer</h3>
            </v-card-text>

            <v-card-actions>
              <div class="text-center">
                <v-btn
                  color="blue"
                  class="ma-2 white--text"
                  aling="center"
                  href="https://linkedin.com/in/devanir-ramos-junior/"
                  target="_blank"
                >
                  LinkedIn
                  <v-icon large> mdi-linkedin </v-icon>
                </v-btn>
              </div>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col>
          <v-card class="mx-auto" max-width="300" elevation="5">
            <v-img
              class="white--text align-end"
              height="300px"
              src="../images/breno.jpeg"
            >
            </v-img>
            <v-card-text class="text--primary">
              <h2 style="text-align: center">Breno Gabriel</h2>
              <h3 style="text-align: center">Back-end Developer</h3>
            </v-card-text>

            <v-card-actions>
              <div class="text-center">
                <v-btn
                  color="blue"
                  class="ma-2 white--text"
                  aling="center"
                  href="https://www.linkedin.com/in/breno-m-52a300141"
                  target="_blank"
                >
                  LinkedIn
                  <v-icon large> mdi-linkedin </v-icon>
                </v-btn>
              </div>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-card class="mx-auto" max-width="300" elevation="5">
            <v-img
              class="white--text align-end"
              height="300px"
              src="../images/tairik.jpeg"
            >
            </v-img>
            <v-card-text class="text--primary">
              <h2 style="text-align: center">Alex Costa</h2>
              <h3 style="text-align: center">Back-end Developer</h3>
            </v-card-text>

            <v-card-actions>
              <div class="text-center">
                <v-btn
                  color="blue"
                  class="ma-2 white--text"
                  aling="center"
                  href="linkedin.com/in/alex-costa-ba3439187"
                  target="_blank"
                >
                  LinkedIn
                  <v-icon large> mdi-linkedin </v-icon>
                </v-btn>
              </div>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col>
          <v-card class="mx-auto" max-width="300" elevation="5">
            <v-img
              class="white--text align-end"
              height="300px"
              src="../images/alan.jpeg"
            >
            </v-img>
            <v-card-text class="text--primary">
              <h2 style="text-align: center">Alan Lucas</h2>
              <h3 style="text-align: center">Database Developer</h3>
            </v-card-text>

            <v-card-actions>
              <div class="text-center">
                <v-btn
                  color="blue"
                  class="ma-2 white--text"
                  aling="center"
                  href="https://www.linkedin.com/in/alan-bezerra/"
                  target="_blank"
                >
                  LinkedIn
                  <v-icon large> mdi-linkedin </v-icon>
                </v-btn>
              </div>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col>
          <v-card class="mx-auto" max-width="300" elevation="5">
            <v-img
              class="white--text align-end"
              height="300px"
              src="../images/gabriel.jpeg"
            >
            </v-img>
            <v-card-text class="text--primary">
              <h2 style="text-align: center">Gabriel Timoteo</h2>
              <h3 style="text-align: center">Database Developer</h3>
            </v-card-text>

            <v-card-actions>
              <div class="text-center">
                <v-btn
                  color="blue"
                  class="ma-2 white--text"
                  aling="center"
                  href="www.linkedin.com/in/gabriel-timoteo-santos"
                  target="_blank"
                >
                  LinkedIn
                  <v-icon large> mdi-linkedin </v-icon>
                </v-btn>
              </div>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </div>
</template>


<script>
</script>

<style>
#cards-home {
  margin-top: 20px;
}
</style>
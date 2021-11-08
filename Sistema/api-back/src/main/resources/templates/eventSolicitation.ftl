<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Solicitação de Evento</title>
</head>
<body>
    <div style="text-align: center">
        <br>
        <img src="https://github.com/API-4-SEMESTRE/PROJETO-API/blob/dev-sprint02/Documenta%C3%A7%C3%A3o/Fotos-Readme/LogoDev.png?raw=true"
             alt="AgendHouse" class="center" width="200"/><br>
        <h3>Olá ${adminNome}!</h3>
    </div>

    <div class="center-justify" style="text-align: justify; position: relative; width: 400px; margin: 0 auto">
        Um novo evento acabou de ser criado como "PENDENTE"! Segue as informações do evento:<br><br>
        Por: ${pessoaNome};<br>
        Tipo: ${tipo} - ${formato};<br>
        Data: ${data} - ${horaIni} às ${horaFim}.<br><br>
        Para verificar e aprovar o evento <a href="http://localhost:8080/login">clique aqui(8080) - LINK PADRÃO ATÉ TER A PÁGINA</a><br><br>
    </div>

    <div style="text-align: center">
        Serviços AgendHouse.
    </div>
</body>
</html>
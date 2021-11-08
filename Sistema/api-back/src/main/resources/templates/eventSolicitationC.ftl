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
        <h3>Olá ${pessoaNome}!</h3>
    </div>

    <div class="center-justify" style="text-align: justify; position: relative; width: 400px; margin: 0 auto">
        Você acabou de solicitar um novo evento. Segue as informações dele:<br><br>
        Por: ${pessoaNome};<br>
        Tipo: ${tipo} - ${formato};<br>
        Data: ${data} - ${horaIni} às ${horaFim}.<br><br>
        Agora aguarde, um admin deve verificar o evento e atualizar o status para "APROVADO" ou "REPROVADO"<br>
        Você será notificado por email quando a mudança ocorrer.<br><br>
    </div>

    <div style="text-align: center">
        Serviços AgendHouse.
    </div>
</body>
</html>
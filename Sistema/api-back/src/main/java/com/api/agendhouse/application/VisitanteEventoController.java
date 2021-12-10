package com.api.agendhouse.application;

import com.api.agendhouse.domain.email.EmailService;
import com.api.agendhouse.domain.usuario.UsuarioService;
import com.api.agendhouse.domain.visitante.VisitanteEvento;
import com.api.agendhouse.domain.visitante.VisitanteEventoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("visitanteEvento")
public class VisitanteEventoController {

    @Autowired
    private JavaMailSender mailSender;
    private final VisitanteEventoService visitanteEventoService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Autowired
    public VisitanteEventoController(VisitanteEventoService visitanteEventoService, EmailService emailService, UsuarioService usuarioService) {
        this.visitanteEventoService = visitanteEventoService;
        this.emailService = emailService;
        this.usuarioService = usuarioService;
    }

    @PostMapping(value="/add")
    @ApiOperation(value="Adiciona os dados de um visitante")
    public ResponseEntity<VisitanteEvento> addVisitante (
            @RequestBody VisitanteEvento visitanteEvento) {

        visitanteEventoService.add(visitanteEvento);
        return ResponseEntity.ok(visitanteEvento);
    }

    @GetMapping(value="/all")
    @ApiOperation(value="Retorna todos os visitantes")
    public ResponseEntity<List<VisitanteEvento>> findAll() {

        var list = visitanteEventoService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="Deleta um registro")
    public ResponseEntity<Boolean> delete(
            @RequestBody VisitanteEvento visevent) {

        var vis = visitanteEventoService.delete(visevent);
        return ResponseEntity.ok(vis);
    }

    @GetMapping(value="/mail")
    @ApiOperation(value="Procura os visitantes de um evento e envia um email para cada")
    public ResponseEntity<List<String>> findByCodeven(
            @RequestParam Long codeven) {

        var list = visitanteEventoService.findByCodeven(codeven);
        List<String> emails = new ArrayList<>();
        for (VisitanteEvento visEve : list) {
            emails.add(visEve.getVisitante().getVisemail());
        }
        var criador = usuarioService.findByCod(list.get(0).getEvento().getUsucodcria());
        var cartas = emailService.eventInvite(list.get(0).getEvento(), criador, emails.toArray(new String[0]));
        for (MimeMessage carta : cartas) {
            mailSender.send(carta);
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(emails);
    }
}

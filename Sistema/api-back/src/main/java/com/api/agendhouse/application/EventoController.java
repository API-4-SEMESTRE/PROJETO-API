package com.api.agendhouse.application;

import com.api.agendhouse.domain.email.EmailService;
import com.api.agendhouse.domain.evento.Evento;
import com.api.agendhouse.domain.evento.EventoService;
import com.api.agendhouse.domain.evento.EventoStatus;
import com.api.agendhouse.domain.usuario.UsuarioService;
import com.api.agendhouse.domain.usuario.UsuarioTipo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.websocket.server.ServerEndpoint;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin("*")
@RestController
@Api("Eventos")
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private JavaMailSender mailSender;

    private final EmailService emailService;

    private final EventoService eventoService;

    private final UsuarioService usuarioService;

    @Autowired
    public EventoController(EmailService emailService, EventoService eventoService, UsuarioService usuarioService) {
        this.emailService = emailService;
        this.eventoService = eventoService;
        this.usuarioService = usuarioService;
    }

    @ApiOperation("Adiciona um evento na plataforma.")
    @PostMapping("/add")
    public ResponseEntity<Evento> addEvento (
            @RequestBody Evento evento) throws ParseException {

        var usuarioCheckTipo = usuarioService.findByCod(evento.getUsucodcria());
        if (usuarioCheckTipo.getTipo().equals(UsuarioTipo.COLABORADOR)) {
            evento.setStatus(EventoStatus.PENDENTE);
            eventoService.add(evento);
            var admins = usuarioService.findByTipo(UsuarioTipo.ADMIN);
            var cartas = emailService.eventRequestC(admins, evento, usuarioCheckTipo);
            for (MimeMessage carta : cartas) {
                mailSender.send(carta);
            }
        }
        else {
            evento.setStatus(EventoStatus.APROVADO);
            eventoService.add(evento);
            var carta = emailService.eventRequestA(evento, usuarioCheckTipo);
            mailSender.send(carta);
        }

        return ResponseEntity.ok(evento);
    }

    @ApiOperation("Busca todos os eventos na plataforma.")
    @GetMapping("/all")
    public ResponseEntity<List<Evento>> findAll() {

        var eventos = eventoService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(eventos);
    }

    @ApiOperation("Busca os eventos cadastrados por um usu??rio.")
    @GetMapping("/findByUsu")
    public ResponseEntity<List<Evento>> findByUsu(
            @RequestParam Long usucod) {

        var eventos = eventoService.findByUsu(usucod);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(eventos);
    }

    @ApiOperation("Busca um evento cadastrado.")
    @GetMapping("/findByCod")
    public ResponseEntity<Evento> findByCod(
            @RequestParam Long codeven) {

        var evento = eventoService.findByCod(codeven);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(evento);
    }

    @ApiOperation("Mostra se o hor??rio selecionado est?? dispon??vel.")
    @GetMapping("/disponiveis")
    public ResponseEntity<Boolean> disponiveis(
            @RequestParam String datahorainicio, String datahorafim) {
        var eventos = eventoService.disponivel(datahorainicio, datahorafim);
        if (eventos == null){
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.ok(false);
        }
    }

    @ApiOperation("Atualiza um registro de evento")
    @PutMapping("/update")
    public ResponseEntity<Evento> update(
            @RequestBody Evento evento) {

        var oldEvento = eventoService.findByCod(evento.getCodeven());
        var updatedEvento = eventoService.update(evento);
        if (!updatedEvento.getStatus().equals(oldEvento.getStatus())) {
            var criador = usuarioService.findByCod(updatedEvento.getUsucodcria());
            var carta = emailService.eventUpdate(updatedEvento, criador);
            mailSender.send(carta);
        }
        return ResponseEntity.ok(updatedEvento);
    }

    @ApiOperation("Remove um evento")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestBody Evento evento) {

        var deletedEvento = eventoService.delete(evento);
        return ResponseEntity.ok(deletedEvento);
    }

    @ApiOperation("Envia emails para convidados do evento")
    @PostMapping("/invite")
    public ResponseEntity<Boolean> invite(
            @RequestParam Long codeven,
            @RequestParam String emails) {

        var evento = eventoService.findByCod(codeven);
        var criador = usuarioService.findByCod(evento.getUsucodcria());
        var listEmails = emails.trim().split("[\\s,;]");
        var cartas = emailService.eventInvite(evento, criador, listEmails);

        for (MimeMessage carta : cartas) {
            mailSender.send(carta);
        }

        return ResponseEntity.ok(true);
    }

    @GetMapping("/report")
    public ResponseEntity<Map<String, Integer>> csv(
            @RequestParam @DateTimeFormat(pattern="yyyy-MM") Date data,
            @RequestParam String email) throws FileNotFoundException {

        var file = EventoService.generateFullCsv(data);
        var carta = emailService.csv(email, data, file);
        mailSender.send(carta);
        file.delete();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(eventoService.getValues());
    }
}


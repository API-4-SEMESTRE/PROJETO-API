package com.api.agendhouse.application;

import com.api.agendhouse.domain.evento.Evento;
import com.api.agendhouse.domain.evento.EventoService;
import com.api.agendhouse.domain.evento.EventoStatus;
import com.api.agendhouse.domain.evento.EventoTipo;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/evento")
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping("/add")
    public ResponseEntity<Evento> addEvento (
            @RequestBody Evento evento) throws ParseException {

        eventoService.add(evento);

        return ResponseEntity.ok(evento);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Evento>> findAll() {

        var eventos = eventoService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(eventos);
    }

    @GetMapping("/findByUsu")
    public ResponseEntity<List<Evento>> findByUsu(
            @RequestParam Long usucod) {

        var eventos = eventoService.findByUsu(usucod);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(eventos);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<Boolean> disponiveis(
            @RequestParam String datahorainicio, String datahorafim) {
        var eventos = eventoService.disponivel(datahorainicio, datahorafim);
        System.out.println(eventos);
        if (eventos == null){
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.ok(false);
        }
    }
}

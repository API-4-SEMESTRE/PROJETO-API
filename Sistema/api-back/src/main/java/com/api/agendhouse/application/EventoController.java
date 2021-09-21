package com.api.agendhouse.application;

import com.api.agendhouse.domain.evento.Evento;
import com.api.agendhouse.domain.evento.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody Evento evento) {

        eventoService.add(evento);

        return ResponseEntity.ok(evento);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Evento>> findAll() {

        var eventos = eventoService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(eventos);
    }

}

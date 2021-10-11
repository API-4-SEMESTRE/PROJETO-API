package com.api.agendhouse.application;

import com.api.agendhouse.domain.evento.Evento;
import com.api.agendhouse.domain.evento.EventoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin("*")
@RestController
@Api("Eventos")
@RequestMapping("/evento")
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @ApiOperation("Adiciona um evento na plataforma.")
    @PostMapping("/add")
    public ResponseEntity<Evento> addEvento (
            @RequestBody Evento evento) throws ParseException {

        eventoService.add(evento);

        return ResponseEntity.ok(evento);
    }

    @ApiOperation("Busca todos os eventos na plataforma.")
    @GetMapping("/all")
    public ResponseEntity<List<Evento>> findAll() {

        var eventos = eventoService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(eventos);
    }

    @ApiOperation("Busca os eventos cadastrados por um usuário.")
    @GetMapping("/findByUsu")
    public ResponseEntity<List<Evento>> findByUsu(
            @RequestParam Long usucod) {

        var eventos = eventoService.findByUsu(usucod);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(eventos);
    }

    @ApiOperation("Mostra se o horário selecionado está disponível.")
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

    @ApiOperation("Atualiza um registro de evento")
    @PutMapping("/update")
    public ResponseEntity<Evento> update(
            @RequestBody Evento evento) {

        var updatedEvento = eventoService.update(evento);
        return ResponseEntity.ok(updatedEvento);
    }

    @ApiOperation("Remove um evento")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestBody Evento evento) {

        var deletedEvento = eventoService.delete(evento);
        return ResponseEntity.ok(deletedEvento);
    }
}


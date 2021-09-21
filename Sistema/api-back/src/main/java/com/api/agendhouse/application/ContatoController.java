package com.api.agendhouse.application;

import com.api.agendhouse.domain.fornecedor.Contato;
import com.api.agendhouse.domain.fornecedor.ContatoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/contato")
public class ContatoController {
    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping("/add")
    public ResponseEntity<Contato> addContato (
            @RequestBody Contato contato) {

        contatoService.add(contato);

        return ResponseEntity.ok(contato);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contato>> findAll() {

        var contatos = contatoService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(contatos);
    }
}

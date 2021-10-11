package com.api.agendhouse.application;

import com.api.agendhouse.domain.fornecedor.Contato;
import com.api.agendhouse.domain.fornecedor.ContatoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@Api("Contato do fornecedor")
@RequestMapping("/contato")
public class ContatoController {
    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @ApiOperation("Adiciona um contato na plataforma.")
    @PostMapping("/add")
    public ResponseEntity<Contato> addContato (
            @RequestBody Contato contato) {

        contatoService.add(contato);
        return ResponseEntity.ok(contato);
    }

    @ApiOperation("Busca todos os contatos cadastrados na plataforma.")
    @GetMapping("/all")
    public ResponseEntity<List<Contato>> findAll() {

        var contatos = contatoService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(contatos);
    }

    @ApiOperation("Atualiza um registro de contato")
    @PutMapping("/update")
    public ResponseEntity<Contato> update(
            @RequestBody Contato contato) {

        var updatedContato = contatoService.update(contato);
        return ResponseEntity.ok(updatedContato);
    }

    @ApiOperation("Remove um contato")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestBody Contato contato) {

        var deletedContato = contatoService.delete(contato);
        return ResponseEntity.ok(deletedContato);
    }
}

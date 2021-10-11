package com.api.agendhouse.application;


import com.api.agendhouse.domain.fornecedor.Endereco;
import com.api.agendhouse.domain.fornecedor.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@Api("Endereço do fornecedor")
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @ApiOperation("Adiciona um endereço na plataforma.")
    @PostMapping("/add")
    public ResponseEntity<Endereco> addEndereco (
            @RequestBody Endereco endereco) {
        System.out.println(endereco.toString());
        enderecoService.add(endereco);

        return ResponseEntity.ok(endereco);
    }

    @ApiOperation("Busca todos os endereços cadastrados na plataforma.")
    @GetMapping("/all")
    public ResponseEntity<List<Endereco>> findAll() {

        var enderecos = enderecoService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(enderecos);
    }

    @ApiOperation("Adiciona um endereço com o codigo do fornecedor")
    @PostMapping("/addforn")
    public ResponseEntity<Endereco> addFor(
            @RequestParam String cnpj,
            @RequestBody Endereco endereco) {

        var newEndereco = enderecoService.addFor(cnpj, endereco);
        return ResponseEntity.ok(newEndereco);
    }

    @ApiOperation("Atualiza um registro de endereco")
    @PutMapping("/update")
    public ResponseEntity<Endereco> update(
            @RequestBody Endereco endereco) {

        var updatedEndereco = enderecoService.update(endereco);
        return ResponseEntity.ok(updatedEndereco);
    }

    @ApiOperation("Remove um endereco")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestBody Endereco endereco) {

        var deletedEndereco = enderecoService.delete(endereco);
        return ResponseEntity.ok(deletedEndereco);
    }
}

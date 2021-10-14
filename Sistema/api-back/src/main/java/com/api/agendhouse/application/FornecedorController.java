package com.api.agendhouse.application;

import java.util.ArrayList;
import java.util.List;


import com.api.agendhouse.domain.DTO.FornecedorDTOFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.agendhouse.domain.fornecedor.*;

@CrossOrigin("*")
@RestController
@Api("Fornecedor")
@RequestMapping("/fornecedor")
public class FornecedorController {
    private final FornecedorService fornecedorService;
    private final ContatoService contatoService;
    private final EnderecoService enderecoService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService,
                                ContatoService contatoService,
                                EnderecoService enderecoService) {

        this.fornecedorService = fornecedorService;
        this.contatoService = contatoService;
        this.enderecoService = enderecoService;
    }

    @ApiOperation("Adiciona um fornecedor na plataforma.")
    @PostMapping("/add")
    public ResponseEntity<Fornecedor> addFornecedor (
            @RequestBody Fornecedor fornecedor) {
        System.out.println(fornecedor.toString());
        try
        {
            fornecedorService.add(fornecedor);
            return ResponseEntity.ok(fornecedor);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @ApiOperation("Busca todos os fornecedores cadastrados na plataforma.")
    @GetMapping("/all")
    public ResponseEntity<List<Fornecedor>> findAll() {

        var fornecedores = fornecedorService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fornecedores);
    }

    @ApiOperation("Busca todos os fornecedores, contatos e endereços cadastrados na plataforma.")
    @GetMapping("/full")
    public ResponseEntity<List> findFull() {

        List lista = new ArrayList();
        lista.add(contatoService.findAll());
        lista.add(fornecedorService.findAll());
        lista.add(enderecoService.findAll());

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(lista);
    }

    @ApiOperation("Atualiza um registro de fornecedor")
    @PutMapping("/update")
    public ResponseEntity<Fornecedor> update(
            @RequestBody Fornecedor fornecedor) {

        var updatedFornecedor = fornecedorService.update(fornecedor);
        return ResponseEntity.ok(updatedFornecedor);
    }

    @ApiOperation("Remove um fornecedor")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestBody Fornecedor fornecedor) {

        var deletedFornecedor = fornecedorService.delete(fornecedor);
        return ResponseEntity.ok(deletedFornecedor);
    }

    @ApiOperation("Lista os fornecedores com dados de cadastro")
    @GetMapping("/listaFornecedores")
    public ResponseEntity<List<FornecedorDTOFilter>> GetFornecedores(){
        var fornecedoresApresentar = fornecedorService.listFornecedores();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fornecedoresApresentar);
    }



//    @PostMapping("/addcon")
//    public ResponseEntity<Fornecedor> addCon(
//            @RequestParam String nome,
//            @RequestBody Fornecedor fornecedor) {
//
//        var newFornecedor = fornecedorService.addCon(nome, fornecedor);
//
//        return ResponseEntity.ok(newFornecedor);
//    }
}

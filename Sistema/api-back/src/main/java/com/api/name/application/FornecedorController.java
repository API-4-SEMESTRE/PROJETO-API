package com.api.name.application;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.name.domain.fornecedor.*;

@CrossOrigin("*")
@RestController
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

    @GetMapping("/all")
    public ResponseEntity<List<Fornecedor>> findAll() {

        var fornecedores = fornecedorService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fornecedores);
    }

    @GetMapping("/full")
    public ResponseEntity<List> findFull() {

        List lista = new ArrayList();
        lista.add(contatoService.findAll());
        lista.add(fornecedorService.findAll());
        lista.add(enderecoService.findAll());

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(lista);
    }

    @PostMapping("/addcon")
    public ResponseEntity<Fornecedor> addCon(
            @RequestParam String nome,
            @RequestBody Fornecedor fornecedor) {

        var newFornecedor = fornecedorService.addCon(nome, fornecedor);

        return ResponseEntity.ok(newFornecedor);

    }
}

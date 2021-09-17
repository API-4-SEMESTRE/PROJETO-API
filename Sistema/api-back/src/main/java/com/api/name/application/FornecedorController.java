package com.api.name.application;

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

    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {

        this.fornecedorService = fornecedorService;
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
}

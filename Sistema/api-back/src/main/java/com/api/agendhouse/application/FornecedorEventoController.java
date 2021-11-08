package com.api.agendhouse.application;

import com.api.agendhouse.domain.fornecedor.FornecedorEvento;
import com.api.agendhouse.domain.fornecedor.FornecedorEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/fornecedorEvento")
public class FornecedorEventoController {
    private final FornecedorEventoService fornecedorEventoService;

    @Autowired
    public FornecedorEventoController(FornecedorEventoService fornecedorEventoService) {
        this.fornecedorEventoService = fornecedorEventoService;
    }

    @PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<FornecedorEvento> addFornece (
            @RequestBody FornecedorEvento FornecedorEvento) {
        try
        {
            fornecedorEventoService.add(FornecedorEvento);
            return ResponseEntity.ok(FornecedorEvento);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FornecedorEvento>> findAll() {

        var fornece = fornecedorEventoService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fornece);
    }

    @PutMapping("/update")
    public ResponseEntity<FornecedorEvento> update(
            @RequestBody FornecedorEvento fornecedorEvento) {

        var fornece = fornecedorEventoService.update(fornecedorEvento);
        return ResponseEntity.ok(fornecedorEvento);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestBody FornecedorEvento fornecedorEvento) {

        var fornece = fornecedorEventoService.delete(fornecedorEvento);
        return ResponseEntity.ok(fornece);
    }
}

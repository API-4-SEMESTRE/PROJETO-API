package com.api.agendhouse.application;


import com.api.agendhouse.domain.fornecedor.Endereco;
import com.api.agendhouse.domain.fornecedor.EnderecoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/add")
    public ResponseEntity<Endereco> addEndereco (
            @RequestBody Endereco endereco) {
        System.out.println(endereco.toString());
        enderecoService.add(endereco);

        return ResponseEntity.ok(endereco);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Endereco>> findAll() {

        var enderecos = enderecoService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(enderecos);
    }

    @PostMapping("/addforn")
    public ResponseEntity<Endereco> addFor(
            @RequestParam String cnpj,
            @RequestBody Endereco endereco) {

        var newEndereco = enderecoService.addFor(cnpj, endereco);

        return ResponseEntity.ok(newEndereco);

    }
}

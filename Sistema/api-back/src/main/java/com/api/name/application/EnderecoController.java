package com.api.name.application;


import com.api.name.domain.fornecedor.Endereco;
import com.api.name.domain.fornecedor.EnderecoService;
import com.api.name.domain.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

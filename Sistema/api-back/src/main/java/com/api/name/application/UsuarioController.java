package com.api.name.application;

import com.api.name.domain.usuario.Usuario;
import com.api.name.domain.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/add")
    public ResponseEntity<Usuario> addUsuario (
            @RequestBody Usuario usuario) {
        System.out.println(usuario.toString());
        usuarioService.add(usuario);

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> findAll() {

        var usuarios = usuarioService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(usuarios);
    }

}

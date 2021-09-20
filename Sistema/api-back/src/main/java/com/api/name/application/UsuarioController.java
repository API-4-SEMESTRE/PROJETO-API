package com.api.name.application;

import com.api.name.domain.usuario.Usuario;
import com.api.name.domain.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
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

        usuarioService.add(usuario);

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> findAll() {

        var usuarios = usuarioService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(usuarios);
    }

    @GetMapping("/find")
    public ResponseEntity<Usuario> findByEmail(
            @RequestParam String email) {

        var usuario = usuarioService.findByEmail(email);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(usuario);
    }


    @PostMapping("/login")
    public ResponseEntity<Boolean> login(
            @RequestBody Usuario usuario) {

        var check = usuarioService.login(usuario);
        return ResponseEntity.ok(check);
    }
}

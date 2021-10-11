package com.api.agendhouse.application;

import com.api.agendhouse.domain.email.EmailService;
import com.api.agendhouse.domain.usuario.Usuario;
import com.api.agendhouse.domain.usuario.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@Api(value = "Usuário")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private JavaMailSender mailSender;

    private final EmailService emailService;

    private final UsuarioService usuarioService;


    @Autowired
    public UsuarioController(EmailService emailService, UsuarioService usuarioService) {
        this.emailService = emailService;
        this.usuarioService = usuarioService;
    }

    @ApiOperation(value = "Insere um usuário na plataforma")
    @PostMapping("/add")
    public ResponseEntity<Usuario> addUsuario (
            @RequestBody Usuario usuario) {

        usuarioService.add(usuario);
        var carta = emailService.registration(usuario);
        mailSender.send(carta);

        return ResponseEntity.ok(usuario);
    }
    @ApiOperation(value = "Busca todos os usuários cadastrados na plataforma")
    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> findAll() {

        var usuarios = usuarioService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(usuarios);
    }

    @ApiOperation(value = "Busca um usuário cadastrado na platafoprma pelo Id")
    @GetMapping("/find")
    public ResponseEntity<Usuario> findByEmail(
            @RequestParam String email) throws Exception {

        var usuario = usuarioService.findByEmail(email);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(usuario);
    }


    @ApiOperation(value = "Login na plataforma")
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(
            @RequestBody Usuario usuario) throws Exception {

        var check = usuarioService.login(usuario);
        return ResponseEntity.ok(check);
    }

    @ApiOperation(value = "Atualiza dos dados do usuário")
    @PutMapping("/update")
    public ResponseEntity<Usuario> update(
            @RequestBody Usuario usuario) {

        var updatedUsuario = usuarioService.update(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @ApiOperation(value = "Remove um usuário")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestBody Usuario usuario) {

        var deletedusuario = usuarioService.delete(usuario);
        return ResponseEntity.ok(deletedusuario);
    }

}

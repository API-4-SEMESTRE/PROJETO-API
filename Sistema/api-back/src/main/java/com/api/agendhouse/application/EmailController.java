package com.api.agendhouse.application;

import com.api.agendhouse.domain.email.EmailService;
import com.api.agendhouse.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Boolean> sendMail (
            @RequestBody Usuario usuario) {

        var carta = emailService.registration(usuario);
        mailSender.send(carta);
        return ResponseEntity.ok(true);
    }

}

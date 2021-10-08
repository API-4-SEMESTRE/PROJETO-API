package com.api.agendhouse.application;

import com.api.agendhouse.domain.DTO.UsuarioDTO;
import com.api.agendhouse.domain.usuario.Usuario;
import com.api.agendhouse.domain.usuario.UsuarioService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.api.agendhouse.config.JwtTokenUtil;
import com.api.agendhouse.domain.jwt.JwtRequest;
import com.api.agendhouse.domain.jwt.JwtResponse;
import com.api.agendhouse.config.JwtUserDetailsService;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@CrossOrigin("*")
@RequestMapping("/autenticacao")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UsuarioService userService;

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Usuario authenticationRequest) throws Exception {
        //authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());
        final UsuarioDTO userDetails = userService
                .loadUserByUser(authenticationRequest);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

package com.api.agendhouse.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario add(Usuario usuario) {

        usuario.setDate_create(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAllByOrderByCodAsc();
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }


    public boolean login(Usuario usuario) {

        var check = findByEmail(usuario.getEmail());

        if (usuario.getSenha().equals(check.getSenha())) {
            return true;
        }

        return false;
    }

    @Transactional
    public Usuario updateActive(String email) {
        var usuario = usuarioRepository.findByEmail(email);
        if (usuario.isActive()) {
            usuario.setActive(false);
        } else {
            usuario.setActive(true);
        }
        usuarioRepository.save(usuario);
        return usuario;
    }
}

package com.api.name.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        usuario.setDate_create(new Date());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {

        return usuarioRepository.findAllByOrderByCodAsc();
    }

    public List<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}

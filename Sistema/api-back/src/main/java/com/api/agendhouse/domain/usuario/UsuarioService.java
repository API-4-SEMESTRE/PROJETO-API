package com.api.agendhouse.domain.usuario;

import com.api.agendhouse.domain.DTO.UsuarioDTO;
import com.api.agendhouse.domain.criptografia.Criptografar;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario add(Usuario usuario) {
        usuario.setDate_create(LocalDateTime.now());
        usuario.setSenha(Criptografar.criptografar(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAllByOrderByCodAsc();
    }

    public Usuario findByEmail(String email) throws Exception
    {
        try{
            return usuarioRepository.findByEmail(email);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }


    public boolean login(Usuario usuario) throws Exception {
        var check = findByEmail(usuario.getEmail());

        usuario.setSenha(Criptografar.criptografar(usuario.getSenha()));

        if (usuario.getSenha().equals(check.getSenha())) {
            return true;
        }

        return false;
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        if (usuario.getCod() == null) {
            usuario.setDate_create(LocalDateTime.now());
            usuario.setSenha(Criptografar.criptografar(usuario.getSenha()));
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public boolean delete(Usuario usuario) {
        try {
            usuarioRepository.delete(usuario);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = findByEmail(email);
        if (user.getEmail().equals(email)) {
            return new User(email, user.getNome(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public UsuarioDTO loadUserByUser(Usuario usuario) throws Exception {

        var user = findByEmail(usuario.getEmail());
        var senhaValidar = Criptografar.criptografar(usuario.getSenha());

        if (user.getEmail().equals(usuario.getEmail()) && user.getSenha().equals(senhaValidar)) {
            UsuarioDTO userDto = new UsuarioDTO();
            userDto.setEmail(usuario.getEmail());
            userDto.setNome(user.getNome());
            userDto.setTipo(user.getTipo().toString());
            return userDto;
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com as credenciais fornecidas");
        }
    }

    public List<Usuario> findByTipo(UsuarioTipo tipo) {
        var usuarios = usuarioRepository.findByTipo(tipo);
        return usuarios;
    }

    public Usuario findByCod(Long cod) {
        return usuarioRepository.findByCod(cod);
    }
}

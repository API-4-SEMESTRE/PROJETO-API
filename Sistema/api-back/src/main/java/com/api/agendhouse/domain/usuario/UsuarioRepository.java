package com.api.agendhouse.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAllByOrderByCodAsc();

    Usuario findByEmail(String email);

    Usuario findByCod (Long cod);
}

package com.api.agendhouse.domain.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    List<Contato> findAllByOrderByConcodAsc();

    Contato findByNomecon(String nome);
    Contato getByFornecod(Long id);
}

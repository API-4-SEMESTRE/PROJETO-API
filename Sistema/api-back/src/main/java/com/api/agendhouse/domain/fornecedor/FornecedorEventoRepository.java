package com.api.agendhouse.domain.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedorEventoRepository extends JpaRepository<FornecedorEvento, Long> {

    List<FornecedorEvento> findByCodeven(Long codeven);

    List<FornecedorEvento> findAllByOrderByEventoAsc();

}

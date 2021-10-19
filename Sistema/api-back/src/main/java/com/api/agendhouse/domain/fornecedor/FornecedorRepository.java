package com.api.agendhouse.domain.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    List<Fornecedor> findAllByOrderByCodAsc();
    List<Fornecedor> findAll();

    Fornecedor findByCnpjforn(String cnpj);
}

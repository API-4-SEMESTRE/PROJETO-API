package com.api.name.domain.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForneceRepository extends JpaRepository<Fornece, Long> {
}

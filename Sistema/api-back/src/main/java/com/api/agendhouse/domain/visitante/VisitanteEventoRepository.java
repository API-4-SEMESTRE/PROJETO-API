package com.api.agendhouse.domain.visitante;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitanteEventoRepository extends JpaRepository<VisitanteEvento, Long> {

    List<VisitanteEvento> findByCodeven(Long codenve);

    List<VisitanteEvento> findAllByOrderByCodevenAsc();
}

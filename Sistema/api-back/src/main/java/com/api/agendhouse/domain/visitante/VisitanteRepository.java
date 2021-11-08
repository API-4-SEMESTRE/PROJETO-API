package com.api.agendhouse.domain.visitante;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitanteRepository extends JpaRepository<Visitante, Long> {

    List<Visitante> findAllByOrderByViscodAsc();

    Visitante findByViscod(Long viscod);
}

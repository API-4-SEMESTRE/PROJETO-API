package com.api.agendhouse.domain.evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findAllByOrderByCodevenAsc();

    List<Evento> findByUsucodcriaOrderByCodevenAsc(Long usucod);
}

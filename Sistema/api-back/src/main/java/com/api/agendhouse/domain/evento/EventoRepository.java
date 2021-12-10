package com.api.agendhouse.domain.evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findAllByOrderByCodevenAsc();

    List<Evento> findAllByOrderByDataevenAsc();

    List<Evento> findByUsucodcriaOrderByCodevenAsc(Long usucod);

    @Query(value = "SELECT * FROM evento WHERE eve_hora_fim > :datahorainicio AND eve_hora_inicio < :datahorafim", nativeQuery = true)
    Evento findEventoByHorainicioAndHorafimNamedParams(@Param("datahorainicio") String datahorainicio,
                                      @Param("datahorafim") String datahorafim);

    Evento findByCodeven(Long codeven);
}

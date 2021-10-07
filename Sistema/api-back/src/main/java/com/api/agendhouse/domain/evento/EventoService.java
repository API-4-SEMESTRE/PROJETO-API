package com.api.agendhouse.domain.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    private EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Transactional
    public Evento add(Evento evento) {
        evento.setDatacria(LocalDateTime.now());
        return eventoRepository.save(evento);
    }

    public List<Evento> findAll() {
        return eventoRepository.findAllByOrderByCodevenAsc();
    }

    public List<Evento> findByUsu(Long usucod) {
        return eventoRepository.findByUsucodcriaOrderByCodevenAsc(usucod);
    }

    public Evento disponivel(String datahorainicio, String datahorafim) {
        return eventoRepository.findEventoByHorainicioAndHorafimNamedParams(datahorainicio, datahorafim);
    }
}

package com.api.name.domain.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        evento.setData_cria(new Date());
        return eventoRepository.save(evento);
    }

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }
}

package com.api.agendhouse.domain.visitante;

import com.api.agendhouse.domain.evento.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VisitanteEventoService {

    private final VisitanteEventoRepository visitanteEventoRepository;
    private final VisitanteService visitanteService;
    private final EventoService eventoService;

    @Autowired
    public VisitanteEventoService(VisitanteEventoRepository visitanteEventoRepository, VisitanteService visitanteService, EventoService eventoService) {
        this.visitanteEventoRepository = visitanteEventoRepository;
        this.visitanteService = visitanteService;
        this.eventoService = eventoService;
    }

    @Transactional
    public VisitanteEvento add(VisitanteEvento visitanteEvento) {
        visitanteEvento.setEvento(eventoService.findByCod(visitanteEvento.getCodeven()));
        visitanteEvento.setVisitante(visitanteService.findByCod(visitanteEvento.getViscod()));
        return visitanteEventoRepository.save(visitanteEvento);
    }

    public List<VisitanteEvento> findAll() {
        return visitanteEventoRepository.findAllByOrderByCodevenAsc();
    }

    public List<VisitanteEvento> findByCodeven(Long codeven) {
        var list = visitanteEventoRepository.findByCodeven(codeven);
        return visitanteEventoRepository.findByCodeven(codeven);
    }

}

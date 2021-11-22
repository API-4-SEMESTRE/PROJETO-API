package com.api.agendhouse.domain.fornecedor;

import com.api.agendhouse.domain.evento.EventoService;
import com.api.agendhouse.domain.visitante.VisitanteEventoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FornecedorEventoService {
    private FornecedorEventoRepository fornecedorEventoRepository;
    private EventoService eventoService;
    private FornecedorService fornecedorService;

    @Autowired
    public FornecedorEventoService(FornecedorEventoRepository fornecedorEventoRepository, EventoService eventoService, FornecedorService fornecedorService) {
        this.fornecedorEventoRepository = fornecedorEventoRepository;
        this.eventoService = eventoService;
        this.fornecedorService = fornecedorService;
    }

    @Transactional
    public FornecedorEvento add(FornecedorEvento fornecedorEvento) {
        fornecedorEvento.setEvento(eventoService.findByCod(fornecedorEvento.getCodeven()));
        fornecedorEvento.setFornecedor(fornecedorService.findByCod(fornecedorEvento.getForncod()));
        return fornecedorEventoRepository.save(fornecedorEvento);
    }

    public List<FornecedorEvento> findAll() {
        return fornecedorEventoRepository.findAllByOrderByEventoAsc();
    }

//    @Transactional
//    public FornecedorEvento update(FornecedorEvento fornecedorEvento) {
//        var check = fornecedorEventoRepository.findAllByOrderByEventoAsc();
//        if (check.contains(fornecedorEvento)) {
//            fornecedorEvento.setEvento(eventoService.findByCod(fornecedorEvento.getCodeven()));
//            fornecedorEvento.setFornecedor(fornecedorService.findByCod(fornecedorEvento.getForncod()));
//            delete(fornecedorEvento);
//            return fornecedorEventoRepository.save(fornecedorEvento);
//        }
//        return null;
//    }

    @Transactional
    public Boolean delete(FornecedorEvento fornecedorEvento) {
        try {
            var fornevenKey = new FornecedorEventoKey();
            fornevenKey.setCodeven(fornecedorEvento.getCodeven());
            fornevenKey.setForncod(fornecedorEvento.getForncod());
            fornecedorEvento.setId(fornevenKey);
            fornecedorEventoRepository.delete(fornecedorEvento);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

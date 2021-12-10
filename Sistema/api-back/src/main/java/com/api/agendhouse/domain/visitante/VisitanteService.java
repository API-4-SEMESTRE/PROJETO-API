package com.api.agendhouse.domain.visitante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VisitanteService {

    private VisitanteRepository visitanteRepository;

    @Autowired
    public VisitanteService(VisitanteRepository visitanteRepository) {
        this.visitanteRepository = visitanteRepository;
    }

    @Transactional
    public Visitante add(Visitante visitante) {
        var rawCpf = visitante.getViscpf();
        visitante.setViscpf(rawCpf.replaceAll("[.-]", ""));
        return visitanteRepository.save(visitante);
    }

    public List<Visitante> findAll() {
        var visitantes = visitanteRepository.findAllByOrderByViscodAsc();
        for (Visitante visitante : visitantes) {
            if (visitante.getViscpf().length() > 10) {
                var rawCpf = visitante.getViscpf();
                var sub1 = rawCpf.substring(0, 3);
                var sub2 = rawCpf.substring(3, 6);
                var sub3 = rawCpf.substring(6, 9);
                var sub4 = rawCpf.substring(9);
                visitante.setViscpf(sub1 + "." + sub2 + "." + sub3 + "-" + sub4);

            }
        }
        return visitantes;
    }

    public Visitante findByCod(Long viscod) {
        return visitanteRepository.findByViscod(viscod);
    }

    public Visitante update(Visitante visitante) {
        var check = visitanteRepository.findByViscod(visitante.getViscod());
        if (check == null) {
            return null;
        }
        var rawCpf = visitante.getViscpf();
        visitante.setViscpf(rawCpf.replaceAll("[.-]", ""));
        visitanteRepository.save(visitante);
        return visitante;
    }

    public Boolean delete(Visitante visitante) {
        try {
            visitanteRepository.delete(visitante);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

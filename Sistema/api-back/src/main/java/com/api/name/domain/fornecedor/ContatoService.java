package com.api.name.domain.fornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContatoService {
    private ContatoRepository contatoRepository;

    @Autowired
    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @Transactional
    public Contato add(Contato contato) {

        return contatoRepository.save(contato);
    }

    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }

}

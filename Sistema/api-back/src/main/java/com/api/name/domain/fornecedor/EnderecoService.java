package com.api.name.domain.fornecedor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EnderecoService {
    private EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public Endereco add(Endereco endereco) {

        return enderecoRepository.save(endereco);
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }
}

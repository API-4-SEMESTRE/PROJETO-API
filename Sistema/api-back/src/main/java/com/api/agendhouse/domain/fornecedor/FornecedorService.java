package com.api.agendhouse.domain.fornecedor;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {
    private FornecedorRepository fornecedorRepository;
    private ContatoRepository contatoRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository, ContatoRepository contatoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.contatoRepository = contatoRepository;
    }

    @Transactional
    public Fornecedor add(Fornecedor fornecedor) {

        return fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAllByOrderByCodAsc();
    }

    @Transactional
    public Fornecedor addCon(String nome, Fornecedor fornecedor) {
        var contato = contatoRepository.findByNomecon(nome);
        fornecedor.setCon_cod(contato.getConcod());
        fornecedorRepository.save(fornecedor);
        return fornecedor;
    }
}

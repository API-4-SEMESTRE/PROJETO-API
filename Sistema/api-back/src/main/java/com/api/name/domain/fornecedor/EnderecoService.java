package com.api.name.domain.fornecedor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EnderecoService {
    private EnderecoRepository enderecoRepository;
    private FornecedorRepository fornecedorRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository, FornecedorRepository fornecedorRepository) {
        this.enderecoRepository = enderecoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Transactional
    public Endereco add(Endereco endereco) {

        return enderecoRepository.save(endereco);
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Endereco addFor(Long cnpj, Endereco endereco) {
        var fornecedor = fornecedorRepository.findByCnpjforn(cnpj);
        endereco.setForncod(fornecedor.getCod());
        enderecoRepository.save(endereco);
        return endereco;
    }
}

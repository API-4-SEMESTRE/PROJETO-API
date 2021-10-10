package com.api.agendhouse.domain.fornecedor;


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
        return enderecoRepository.findAllByOrderByForncodAsc();
    }

    @Transactional
    public Endereco addFor(String cnpj, Endereco endereco) {
        var fornecedor = fornecedorRepository.findByCnpjforn(cnpj);
        endereco.setForncod(fornecedor.getCod());
        enderecoRepository.save(endereco);
        return endereco;
    }

    @Transactional
    public Endereco update(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public boolean delete(Endereco endereco) {
        try {
            enderecoRepository.delete(endereco);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

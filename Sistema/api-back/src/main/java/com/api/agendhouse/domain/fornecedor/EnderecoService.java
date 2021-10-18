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
        endereco.setCep_end((endereco.getCep_end().replace("-", "")));
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> findAll() {
        var enderecos = enderecoRepository.findAllByOrderByForncodAsc();
        for (Endereco endereco : enderecos) {
            var rawCep = endereco.getCep_end();
            var sub = rawCep.substring(0, 5);
            var rest = rawCep.substring(5);
            endereco.setCep_end(sub + "-" + rest);
        }
        return enderecos;
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

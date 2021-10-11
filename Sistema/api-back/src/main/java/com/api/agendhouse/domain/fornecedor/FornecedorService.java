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
        var rawCnpj = fornecedor.getCnpjforn();
        fornecedor.setCnpjforn(rawCnpj.replaceAll("[./-]", ""));
        return fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> findAll() {
        var fornecedores = fornecedorRepository.findAllByOrderByCodAsc();
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getCnpjforn().length() > 13) {
                var rawCnpj = fornecedor.getCnpjforn();
                var sub1 = rawCnpj.substring(0, 2);
                var sub2 = rawCnpj.substring(2, 5);
                var sub3 = rawCnpj.substring(5, 8);
                var sub4 = rawCnpj.substring(8, 12);
                var sub5 = rawCnpj.substring(12);
                fornecedor.setCnpjforn(sub1 + "." + sub2 + "." + sub3 + "/" + sub4 + "-" + sub5);
            }

        }
        return fornecedores;
    }

    @Transactional
    public Fornecedor update(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @Transactional
    public boolean delete(Fornecedor fornecedor) {
        try {
            fornecedorRepository.delete(fornecedor);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

//    @Transactional
//    public Fornecedor addCon(String nome, Fornecedor fornecedor) {
//        var contato = contatoRepository.findByNomecon(nome);
//        fornecedor.setCon_cod(contato.getConcod());
//        fornecedorRepository.save(fornecedor);
//        return fornecedor;
//    }
}

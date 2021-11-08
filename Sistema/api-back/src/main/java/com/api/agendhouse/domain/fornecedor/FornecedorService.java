package com.api.agendhouse.domain.fornecedor;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.api.agendhouse.domain.DTO.FornecedorDTOFilter;
import com.api.agendhouse.domain.fornecedor.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;
    private final ContatoRepository contatoRepository;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository, ContatoRepository contatoRepository, EnderecoRepository enderecoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.contatoRepository = contatoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public Fornecedor add(Fornecedor fornecedor) {
        var rawCnpj = fornecedor.getCnpjforn();
        fornecedor.setCnpjforn(rawCnpj.replaceAll("[./-]", ""));
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor findByCod(Long cod) {
        return fornecedorRepository.findByCod(cod);
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
        fornecedor.setCnpjforn(fornecedor.getCnpjforn().replaceAll("[./-]", ""));
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


    public List<FornecedorDTOFilter> listFornecedores() {
        var listaFornecedores = new ArrayList<FornecedorDTOFilter>();

        var fornecedores = fornecedorRepository.findAll();
        for (Fornecedor fornecedor: fornecedores) {
            FornecedorDTOFilter listaRetorno = new FornecedorDTOFilter();
            listaRetorno.setId_fornecedor(fornecedor.getCod());
            listaRetorno.setNome(fornecedor.getNomeforn());
            listaRetorno.setRamo(fornecedor.getRamo_forn());
            listaRetorno.setCnpj(cnpjPretty(fornecedor.getCnpjforn()));

            var endereco = enderecoRepository.getByForncod(fornecedor.getCod());
            if (endereco != null){
                //listaRetorno.setId_endereco(endereco.getCodeEnd);
                listaRetorno.setRua(endereco.getRua_end());
                listaRetorno.setNumero(endereco.getNum_end().toString());
                listaRetorno.setBairro(endereco.getBairro_end());
                listaRetorno.setCidade(endereco.getCidade_end());
                listaRetorno.setUf(endereco.getEstado_end());
                listaRetorno.setComplemento(endereco.getComplemento_end());
            }

            var contato = contatoRepository.getByFornecod(fornecedor.getCod());
            if (contato != null){
                listaRetorno.setId_contato(contato.getConcod());
                listaRetorno.setContato_nome(contato.getNomecon());
                listaRetorno.setContato_email(contato.getEmail_con());
                listaRetorno.setContato_fone(ContatoService.telPretty(contato.getTel_con()));
            }
            listaFornecedores.add(listaRetorno);
        }

        return listaFornecedores;
    }

    public String cnpjPretty(String cnpj) {
        if (cnpj.length() > 13) {
            var sub1 = cnpj.substring(0, 2);
            var sub2 = cnpj.substring(2, 5);
            var sub3 = cnpj.substring(5, 8);
            var sub4 = cnpj.substring(8, 12);
            var sub5 = cnpj.substring(12);
            cnpj = (sub1 + "." + sub2 + "." + sub3 + "/" + sub4 + "-" + sub5);
        }
        return cnpj;
    }

//    @Transactional
//    public Fornecedor addCon(String nome, Fornecedor fornecedor) {
//        var contato = contatoRepository.findByNomecon(nome);
//        fornecedor.setCon_cod(contato.getConcod());
//        fornecedorRepository.save(fornecedor);
//        return fornecedor;
//    }
}

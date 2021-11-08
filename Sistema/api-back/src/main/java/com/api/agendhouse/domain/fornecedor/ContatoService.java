package com.api.agendhouse.domain.fornecedor;

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
        var rawTel = contato.getTel_con();
        contato.setTel_con(rawTel.replaceAll("[\\s()-]", ""));
        return contatoRepository.save(contato);
    }

    public List<Contato> findAll() {
        var contatos = contatoRepository.findAllByOrderByConcodAsc();
        for (Contato contato : contatos) {
            if (contato.getTel_con().length() > 9) {
                var rawTel = contato.getTel_con();
                var ddd = "(" + rawTel.substring(0, 2) + ") ";
                var number = rawTel.substring(2, 7) + "-" + rawTel.substring(7);
                contato.setTel_con(ddd + number);
            }
        }
        return contatos;
    }

    @Transactional
    public Contato update(Contato contato) {
        var rawTel = contato.getTel_con();
        contato.setTel_con(rawTel.replaceAll("[\\s()-]", ""));
        return contatoRepository.save(contato);
    }

    @Transactional
    public boolean delete(Contato contato) {
        try {
            contatoRepository.delete(contato);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String telPretty(String tel) {
        if (tel.length() > 9) {
            var ddd = "(" + tel.substring(0, 2) + ") ";
            var number = tel.substring(2, 7) + "-" + tel.substring(7);
            tel = (ddd + number);
        }
        return tel;
    }

}

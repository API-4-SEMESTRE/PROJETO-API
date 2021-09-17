package com.api.name.domain.fornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ForneceService {
    private ForneceRepository forneceRepository;

    @Autowired
    public ForneceService(ForneceRepository forneceRepository) {
        this.forneceRepository = forneceRepository;
    }

    @Transactional
    public Fornece add(Fornece fornece) {

        return forneceRepository.save(fornece);
    }

    public List<Fornece> findAll() {
        return forneceRepository.findAll();
    }
}

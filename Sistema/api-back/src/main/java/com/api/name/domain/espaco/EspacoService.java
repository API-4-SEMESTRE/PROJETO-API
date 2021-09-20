package com.api.name.domain.espaco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EspacoService {
	
	private EspacoRepository espacoRepository;
	
	@Autowired
	public EspacoService(EspacoRepository espacoRepository) {
		this.espacoRepository = espacoRepository;
	}
	
	@Transactional
	public Espaco add(Espaco espaco) {
		return espacoRepository.save(espaco);
	}
	
	public List<Espaco> findAll(){
		return espacoRepository.findAll();
	}

	

}

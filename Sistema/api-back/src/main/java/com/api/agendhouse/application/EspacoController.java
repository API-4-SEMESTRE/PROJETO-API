package com.api.agendhouse.application;

import com.api.agendhouse.domain.espaco.Espaco;
import com.api.agendhouse.domain.espaco.EspacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/espaco")
public class EspacoController {
	
	private final EspacoService espacoService;
	
	@Autowired
	public EspacoController(EspacoService espacoService) {
		this.espacoService = espacoService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Espaco> addEspaco(
			@RequestBody Espaco espaco){
		
		espacoService.add(espaco);
		
		return ResponseEntity.ok(espaco);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Espaco>> findAll() {

        var espacos = espacoService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(espacos);
    }
}

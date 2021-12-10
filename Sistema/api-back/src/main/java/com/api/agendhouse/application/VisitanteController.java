package com.api.agendhouse.application;

import com.api.agendhouse.domain.visitante.Visitante;
import com.api.agendhouse.domain.visitante.VisitanteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("visitante")
public class VisitanteController {

    private final VisitanteService visitanteService;

    @Autowired
    public VisitanteController(VisitanteService visitanteService) {
        this.visitanteService = visitanteService;
    }

    @PostMapping(value="/add")
    @ApiOperation(value="Adiciona os dados de um visitante")
    public ResponseEntity<Visitante> addVisitante (
            @RequestBody Visitante visitante) {

        visitanteService.add(visitante);
        return ResponseEntity.ok(visitante);
    }

    @GetMapping(value="/all")
    @ApiOperation(value="Retorna todos os visitantes")
    public ResponseEntity<List<Visitante>> findAll() {

        var visitantes = visitanteService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(visitantes);
    }

    @PutMapping("/update")
    @ApiOperation(value="Atualiza um visitante")
    public ResponseEntity<Visitante> updateVisitante (
            @RequestBody Visitante visitante) {

        var vis = visitanteService.update(visitante);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(vis);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="Deleta um visitante")
    public ResponseEntity<Boolean> deleteVisitante (
            @RequestBody Visitante visitante) {

        var vis = visitanteService.delete(visitante);
        return ResponseEntity.ok(vis);
    }

}

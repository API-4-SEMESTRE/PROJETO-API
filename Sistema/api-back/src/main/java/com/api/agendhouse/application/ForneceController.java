package com.api.agendhouse.application;

import com.api.agendhouse.domain.fornecedor.Fornece;
import com.api.agendhouse.domain.fornecedor.ForneceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/fornece")
public class ForneceController {
    private final ForneceService forneceService;

    @Autowired

    public ForneceController(ForneceService forneceService) {
        this.forneceService = forneceService;
    }

    @PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Fornece> addFornece (
            @RequestBody Fornece fornece) {
        System.out.println(fornece.toString());
        try
        {
            forneceService.add(fornece);
            return ResponseEntity.ok(fornece);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Fornece>> findAll() {

        var fornece = forneceService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fornece);
    }
}

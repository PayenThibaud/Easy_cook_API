package org.example.contraintealimentaireservice.controller;

import org.example.contraintealimentaireservice.dto.ContrainteDTOSend;
import org.example.contraintealimentaireservice.service.ContrainteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contrainte")
public class ContrainteController {
    @Autowired
    private ContrainteService contrainteService;

    @GetMapping
    public ResponseEntity<List<ContrainteDTOSend>> getAll(){
        return ResponseEntity.ok(contrainteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContrainteDTOSend> getById(@PathVariable int id){
        return ResponseEntity.ok(contrainteService.getById(id));
    }
}
